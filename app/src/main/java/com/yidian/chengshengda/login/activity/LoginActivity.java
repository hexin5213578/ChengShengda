package com.yidian.chengshengda.login.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.google.gson.Gson;
import com.leaf.library.StatusBarUtil;
import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.Constants;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import com.yidian.chengshengda.R;
import com.yidian.chengshengda.base.App;
import com.yidian.chengshengda.base.BaseAvtivity;
import com.yidian.chengshengda.base.BasePresenter;
import com.yidian.chengshengda.login.bean.LoginBean;
import com.yidian.chengshengda.main.MainActivity;
import com.yidian.chengshengda.regist.activity.RegistActivity;
import com.yidian.chengshengda.remember.activity.RememberPwdActivity;
import com.yidian.chengshengda.setpwd.SetPwdActivity;
import com.yidian.chengshengda.utils.NetUtils;
import com.yidian.chengshengda.utils.SPUtil;
import com.yidian.chengshengda.utils.StringUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.tencent.connect.common.Constants.PACKAGE_QQ;


public class LoginActivity extends BaseAvtivity implements View.OnClickListener, AMapLocationListener {

    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_pwd1)
    EditText etPwd1;
    @BindView(R.id.iv_see_pwd1)
    ImageView ivSeePwd1;
    @BindView(R.id.tv_to_regist)
    TextView tvToRegist;
    @BindView(R.id.tv_reme_pwd)
    TextView tvRemePwd;
    @BindView(R.id.bt_login)
    Button btLogin;
    @BindView(R.id.rl_qq_login)
    RelativeLayout rlQqLogin;
    @BindView(R.id.rl_wechat_login)
    RelativeLayout rlWechatLogin;
    private double latitude;
    private double longitude;
    //声明mlocationClient对象
    AMapLocationClient mlocationClient;
    //声明mLocationOption对象
    AMapLocationClientOption mLocationOption = null;
    //还需要一个IUiListener 的实现类（LogInListener implements IUiListener）
    private String wechatName;
    private String wechatHeadimgurl;
    private IWXAPI mWXApi;
    private static final String WX_AppId = "wxe3fcbe8a55cd33ff";
    private Tencent mTencent;
    private static final String TAG = "LoginActivity";
    private String openId;
    //还需要一个IUiListener 的实现类（LogInListener implements IUiListener）
    private IUiListener listener;
    private String wxOpenId;
    private String avatar;
    private String nickName;

    @Override
    protected int getResId() {
        return R.layout.activity_login;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void getData() {
        StatusBarUtil.setTransparentForWindow(this);

        tvToRegist.setOnClickListener(this);
        tvRemePwd.setOnClickListener(this);
        btLogin.setOnClickListener(this);
        rlQqLogin.setOnClickListener(this);
        rlWechatLogin.setOnClickListener(this);
        //密码明文密文切换
        ivSeePwd1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        etPwd1.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        ivSeePwd1.setImageResource(R.mipmap.eyeopen);
                        break;
                    case MotionEvent.ACTION_UP:
                        etPwd1.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        ivSeePwd1.setImageResource(R.mipmap.eyeclose);
                        break;
                }
                return true;
            }
        });

        //注册微信
        initWX();


        //腾讯AppId(替换你自己App Id)、上下文
        mTencent = Tencent.createInstance("101913220", this);

        //开启权限
        Request();
        //开启定位
        doLocation();
    }


    public void doLocation() {
        mlocationClient = new AMapLocationClient(this);
        //初始化定位参数
        mLocationOption = new AMapLocationClientOption();
        //设置定位监听
        mlocationClient.setLocationListener(this);
        //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(2000);
        //设置定位参数
        mlocationClient.setLocationOption(mLocationOption);
        // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
        // 注意设置合适的定位时间的间隔（最小间隔支持为1000ms），并且在合适时间调用stopLocation()方法来取消定位请求
        // 在定位结束后，在合适的生命周期调用onDestroy()方法
        // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
        //启动定位
        mlocationClient.startLocation();
    }

    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        if (amapLocation != null) {
            if (amapLocation.getErrorCode() == 0) {
                //定位成功回调信息，设置相关消息
                amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                //获取纬度
                latitude = amapLocation.getLatitude();
                //获取经度
                longitude = amapLocation.getLongitude();
                amapLocation.getAccuracy();//获取精度信息
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date(amapLocation.getTime());
                df.format(date);//定位时间
            } else {
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                Log.e("AmapError", "location Error, ErrCode:"
                        + amapLocation.getErrorCode() + ", errInfo:"
                        + amapLocation.getErrorInfo());
            }
        }
    }
    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
    //安卓10.0定位权限
    public void Request() {
        if (Build.VERSION.SDK_INT >= 23) {
            int request = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
            if (request != PackageManager.PERMISSION_GRANTED)//缺少权限，进行权限申请
            {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 100);
                return;//
            } else {

            }
        } else {

        }
    }

    //参数 requestCode是我们在申请权限的时候使用的唯一的申请码
    //String[] permission则是权限列表，一般用不到
    //int[] grantResults 是用户的操作响应，包含这权限是够请求成功
    //由于在权限申请的时候，我们就申请了一个权限，所以此处的数组的长度都是1
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            } else if (grantResults[0] == PackageManager.PERMISSION_DENIED) {

                Toast.makeText(this, "权限申请失败，用户拒绝权限", Toast.LENGTH_SHORT).show();
            }
        }
    }
    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_to_regist:
                //跳转注册页
                Intent intent = new Intent(LoginActivity.this, RegistActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.tv_reme_pwd:
                startActivity(new Intent(LoginActivity.this, RememberPwdActivity.class));
                //跳转忘记密码页
                break;
            case R.id.bt_login:
                //调用登录接口
                String phone = etPhone.getText().toString();
                String pwd = etPwd1.getText().toString();
                if (StringUtil.checkPhoneNumber(phone)) {
                    if (StringUtil.checkPassword(pwd)) {
                        if (longitude == 0.0 && latitude == 0.0) {
                            Toast.makeText(LoginActivity.this, "正在获取当前位置信息，请稍后再试", Toast.LENGTH_SHORT).show();
                        } else {
                            NetUtils.getInstance().getApis().doPwdLogin(phone, pwd, 1, longitude, latitude)
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(new Observer<LoginBean>() {
                                        @Override
                                        public void onSubscribe(Disposable d) {

                                        }

                                        @Override
                                        public void onNext(LoginBean loginBean) {
                                            String type = loginBean.getType();
                                            Toast.makeText(LoginActivity.this, ""+loginBean.getMsg(), Toast.LENGTH_SHORT).show();
                                            if(type.equals("OK")){
                                                LoginBean.ObjectBean object = loginBean.getObject();
                                                String id = String.valueOf(object.getId());
                                                //记录登录后的信息
                                                SPUtil.getInstance().saveData(LoginActivity.this, SPUtil.FILE_NAME, SPUtil.USER_ID, id);
                                                SPUtil.getInstance().saveData(LoginActivity.this, SPUtil.FILE_NAME, SPUtil.KEY_PHONE, phone);
                                                SPUtil.getInstance().saveData(LoginActivity.this, SPUtil.FILE_NAME, SPUtil.IS_LOGIN, "0");
                                                //登录成功跳转至主页
                                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                            }
                                        }
                                        @Override
                                        public void onError(Throwable e) {

                                        }
                                        @Override
                                        public void onComplete() {

                                        }
                                    });
                        }
                        }
                }
                break;
            case R.id.rl_qq_login:
                //QQ登录
                if(longitude ==0.0  && latitude ==0.0){
                    //调取定位权限
                    Toast.makeText(LoginActivity.this, "正在获取当前位置信息，请稍后再试", Toast.LENGTH_SHORT).show();

                    Request();
                }else{
                    //注意：此段非必要，如果手机未安装应用则会跳转网页进行授权
                    if (!hasApp(LoginActivity.this, PACKAGE_QQ)) {
                        Toast.makeText(LoginActivity.this, "未安装QQ应用",
                                Toast.LENGTH_SHORT).show();
                        return;
                    }
                    //如果session无效，就开始做登录操作
                    if (!mTencent.isSessionValid()) {
                        doQlogin();
                    }
                }
                break;
            case R.id.rl_wechat_login:
                    doWechatLogin();
                break;
        }
    }
    private void initWX() {
        mWXApi = WXAPIFactory.createWXAPI(LoginActivity.this, WX_AppId,false);
        mWXApi.registerApp(WX_AppId);
    }
    //微信登录
    public void doWechatLogin(){

        if (!mWXApi.isWXAppInstalled()) {
            Toast.makeText(LoginActivity.this, "您的设备未安装微信客户端", Toast.LENGTH_SHORT).show();
        } else {
            final SendAuth.Req req = new SendAuth.Req();
            req.scope = "snsapi_userinfo";
            req.state = "wechat_sdk_demo_test";
            mWXApi.sendReq(req);
        }
    }
    //微信回调信息
    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sp = getSharedPreferences("wechatInfo", MODE_PRIVATE);
        String responseInfo = sp.getString("responseInfo", "");

        if (!responseInfo.isEmpty()) {
            try {
                JSONObject jsonObject = new JSONObject(responseInfo);
                wechatName = jsonObject.getString("nickname");
                wechatHeadimgurl = jsonObject.getString("headimgurl");
                wxOpenId = jsonObject.getString("openid");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            SharedPreferences.Editor editor = getSharedPreferences("wechatInfo", MODE_PRIVATE).edit();
            editor.clear();
            editor.commit();

            Log.e("xxx","openid"+wxOpenId+"    name"+wechatName+"    头像"+wechatHeadimgurl);


            SPUtil.getInstance().saveData(LoginActivity.this, SPUtil.FILE_NAME, SPUtil.USER_NAME, wechatName);
            SPUtil.getInstance().saveData(LoginActivity.this, SPUtil.FILE_NAME, SPUtil.HEAD_IMG, wechatHeadimgurl);

            //将登录状态改为已经登录
            SPUtil.getInstance().saveData(LoginActivity.this, SPUtil.FILE_NAME, SPUtil.IS_LOGIN, "0");

            //调用微信登录接口
            NetUtils.getInstance().getApis().doWechatLogin(2,wxOpenId,longitude,latitude)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<LoginBean>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(LoginBean loginBean) {
                            Toast.makeText(LoginActivity.this, ""+loginBean.getMsg(), Toast.LENGTH_SHORT).show();

                            if (loginBean.getType().equals("OK")) {
                                LoginBean.ObjectBean object = loginBean.getObject();
                                String id = String.valueOf(object.getId());
                                SPUtil.getInstance().saveData(LoginActivity.this, SPUtil.FILE_NAME, SPUtil.USER_ID, id);

                                //跳转到主页
                                startActivity(new Intent(LoginActivity.this,MainActivity.class));
                            }
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != mlocationClient) {
            //页面销毁停止定位
            mlocationClient.stopLocation();
            mlocationClient.onDestroy();
        }
    }
    //QQ登录
    private void doQlogin() {
        listener = new IUiListener() {
            @Override
            public void onComplete(Object object) {

                Log.e(TAG, "登录成功: " + object.toString() );

                JSONObject jsonObject = (JSONObject) object;
                try {
                    //得到token、expires、openId等参数
                    String token = jsonObject.getString(Constants.PARAM_ACCESS_TOKEN);
                    String expires = jsonObject.getString(Constants.PARAM_EXPIRES_IN);
                    openId = jsonObject.getString(Constants.PARAM_OPEN_ID);

                    mTencent.setAccessToken(token, expires);
                    mTencent.setOpenId(openId);
                    Log.e(TAG, "token: " + token);
                    Log.e(TAG, "expires: " + expires);
                    Log.e(TAG, "openId: " + openId);

                    //获取个人信息
                    getQQInfo();
                } catch (Exception e) {
                }
            }

            @Override
            public void onError(UiError uiError) {
                //登录失败
                Log.e(TAG, "登录失败" + uiError.errorDetail);
                Log.e(TAG, "登录失败" + uiError.errorMessage);
                Log.e(TAG, "登录失败" + uiError.errorCode + "");

            }

            @Override
            public void onCancel() {
                //登录取消
                Log.e(TAG, "登录取消");

            }
        };
        //context上下文、第二个参数SCOPO 是一个String类型的字符串，表示一些权限
        //应用需要获得权限，由“,”分隔。例如：SCOPE = “get_user_info,add_t”；所有权限用“all”
        //第三个参数事件监听器
        mTencent.login(this, "all", listener);
        //注销登录
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //腾讯QQ回调
        Tencent.onActivityResultData(requestCode, resultCode, data, listener);
        if (requestCode == Constants.REQUEST_API) {
            if (resultCode == Constants.REQUEST_LOGIN) {
                Tencent.handleResultData(data, listener);
            }
        }
    }
    /**
     * 获取QQ个人信息
     */
    private void getQQInfo() {
        //获取基本信息
        QQToken qqToken = mTencent.getQQToken();
        UserInfo info = new UserInfo(this, qqToken);
        info.getUserInfo(new IUiListener() {
            @Override
            public void onComplete(Object object) {
                try {
                    Log.e(TAG, "个人信息：" + object.toString());
                    //头像
                    avatar = ((JSONObject) object).getString("figureurl_2");
                    nickName = ((JSONObject) object).getString("nickname");
                    //将用户信息存入SP

                    SPUtil.getInstance().saveData(LoginActivity.this, SPUtil.FILE_NAME, SPUtil.USER_NAME, nickName);
                    SPUtil.getInstance().saveData(LoginActivity.this, SPUtil.FILE_NAME, SPUtil.HEAD_IMG, avatar);

                    //将登录状态改为已经登录
                    SPUtil.getInstance().saveData(LoginActivity.this, SPUtil.FILE_NAME, SPUtil.IS_LOGIN, "0");

                    //获取完用户信息调用QQ登录接口
                    NetUtils.getInstance().getApis().doQqLogin(3, openId, longitude, latitude)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Observer<LoginBean>() {
                                @Override
                                public void onSubscribe(Disposable d) {

                                }

                                @Override
                                public void onNext(LoginBean loginBean) {
                                    Toast.makeText(LoginActivity.this, ""+loginBean.getMsg(), Toast.LENGTH_SHORT).show();


                                    if (loginBean.getType().equals("OK")) {
                                        LoginBean.ObjectBean object = loginBean.getObject();
                                        String id = String.valueOf(object.getId());
                                        SPUtil.getInstance().saveData(LoginActivity.this, SPUtil.FILE_NAME, SPUtil.USER_ID, id);

                                        //跳转到主页
                                        startActivity(new Intent(LoginActivity.this,MainActivity.class));

                                    }
                                }

                                @Override
                                public void onError(Throwable e) {

                                }

                                @Override
                                public void onComplete() {

                                }
                            });

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(UiError uiError) {
            }

            @Override
            public void onCancel() {
            }
        });
    }

    /**
     * true 安装了相应包名的app
     */
    private boolean hasApp(Context context, String packName) {
        boolean is = false;
        List<PackageInfo> packages = context.getPackageManager()
                .getInstalledPackages(0);
        for (int i = 0; i < packages.size(); i++) {
            PackageInfo packageInfo = packages.get(i);
            String packageName = packageInfo.packageName;
            if (packageName.equals(packName)) {
                is = true;
            }
        }
        return is;
    }
}
