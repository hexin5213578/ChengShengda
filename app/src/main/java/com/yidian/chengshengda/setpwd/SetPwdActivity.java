package com.yidian.chengshengda.setpwd;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.leaf.library.StatusBarUtil;
import com.yidian.chengshengda.R;
import com.yidian.chengshengda.base.BaseAvtivity;
import com.yidian.chengshengda.base.BasePresenter;
import com.yidian.chengshengda.base.Common;
import com.yidian.chengshengda.login.bean.LoginBean;
import com.yidian.chengshengda.main.MainActivity;
import com.yidian.chengshengda.regist.bean.RegistBean;
import com.yidian.chengshengda.utils.NetUtils;
import com.yidian.chengshengda.utils.SPUtil;
import com.yidian.chengshengda.utils.StringUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SetPwdActivity extends BaseAvtivity implements View.OnClickListener {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.et_pwd1)
    EditText etPwd1;
    @BindView(R.id.iv_see_pwd1)
    ImageView ivSeePwd1;
    @BindView(R.id.et_pwd2)
    EditText etPwd2;
    @BindView(R.id.iv_see_pwd2)
    ImageView ivSeePwd2;
    @BindView(R.id.bt_login)
    Button btLogin;
    private String phone;

    private String pwd1;

    @Override
    protected int getResId() {
        return R.layout.activity_setpwd;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void getData() {
        StatusBarUtil.setTransparentForWindow(this);

        //绑定单击事件
        back.setOnClickListener(this);
        btLogin.setOnClickListener(this);
        //获取传递的手机号
        Intent intent = getIntent();
        phone = intent.getStringExtra("phone");

        //密码明文密文切换
        ivSeePwd1.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
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
        ivSeePwd2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        etPwd2.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        ivSeePwd2.setImageResource(R.mipmap.eyeopen);
                        break;
                    case MotionEvent.ACTION_UP:
                        etPwd2.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        ivSeePwd2.setImageResource(R.mipmap.eyeclose);
                        break;
                }
                return true;
            }
        });
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.bt_login:
                //调用注册接口
                pwd1 = etPwd1.getText().toString();
                String pwd2 = etPwd2.getText().toString();

                if (StringUtil.checkPassword(pwd1) && StringUtil.checkPassword(pwd2)) {
                    if (pwd1.equals(pwd2)) {
                        NetUtils.getInstance().getApis().doRegist(phone, pwd1)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Observer<RegistBean>() {
                                    @Override
                                    public void onSubscribe(Disposable d) {

                                    }

                                    @Override
                                    public void onNext(RegistBean registBean) {
                                        if(registBean.getType().equals("OK")){
                                         //判断注册成功调用登录接口
                                            showDialog();
                                            NetUtils.getInstance().getApis().doPwdLogin(phone, pwd1, 1)
                                                        .subscribeOn(Schedulers.io())
                                                        .observeOn(AndroidSchedulers.mainThread())
                                                        .subscribe(new Observer<LoginBean>() {
                                                            @Override
                                                            public void onSubscribe(Disposable d) {

                                                            }
                                                            @Override
                                                            public void onNext(LoginBean loginBean) {
                                                                hideDialog();
                                                                Toast.makeText(SetPwdActivity.this, ""+loginBean.getMsg(), Toast.LENGTH_SHORT).show();
                                                                String type = loginBean.getType();
                                                                if(type.equals("OK")){
                                                                    LoginBean.ObjectBean object = loginBean.getObject();
                                                                    String id = String.valueOf(object.getId());
                                                                    String phoneNum = object.getPhoneNum();
                                                                    //记录登录后的信息
                                                                    SPUtil.getInstance().saveData(SetPwdActivity.this, SPUtil.FILE_NAME, SPUtil.USER_ID, id);
                                                                    SPUtil.getInstance().saveData(SetPwdActivity.this, SPUtil.FILE_NAME, SPUtil.KEY_PHONE, phoneNum);
                                                                    SPUtil.getInstance().saveData(SetPwdActivity.this, SPUtil.FILE_NAME, SPUtil.IS_LOGIN, "0");
                                                                    //登录成功跳转至主页
                                                                    startActivity(new Intent(SetPwdActivity.this, MainActivity.class));
                                                                    finish();
                                                                }
                                                            }
                                                            @Override
                                                            public void onError(Throwable e) {
                                                                hideDialog();
                                                                Toast.makeText(SetPwdActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                                                            }
                                                            @Override
                                                            public void onComplete() {

                                                            }
                                                        });
                                        }
                                    }
                                    @Override
                                    public void onError(Throwable e) {

                                    }
                                    @Override
                                    public void onComplete() {

                                    }
                                });
                    } else {
                        Toast.makeText(this, "两次输入的密码不一致", Toast.LENGTH_SHORT).show();
                    }
                }

                break;
        }
    }
}
