package com.yidian.chengshengda.regist.activity;

import android.content.Intent;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.leaf.library.StatusBarUtil;
import com.yidian.chengshengda.R;
import com.yidian.chengshengda.base.BaseAvtivity;
import com.yidian.chengshengda.base.BasePresenter;
import com.yidian.chengshengda.login.activity.LoginActivity;
import com.yidian.chengshengda.regist.bean.GetPhoneCodeBean;
import com.yidian.chengshengda.setpwd.SetPwdActivity;
import com.yidian.chengshengda.utils.NetUtils;
import com.yidian.chengshengda.utils.StringUtil;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RegistActivity extends BaseAvtivity implements View.OnClickListener {

    @BindView(R.id.tv_go_login)
    TextView goLogin;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.tv_getcode)
    TextView tvGetcode;
    @BindView(R.id.bt_regist)
    Button btRegist;
    private CountDownTimer mTimer;
    private boolean  isplayer = false;
    private String phone;
    private String auth;

    @Override
    protected int getResId() {
        return R.layout.activity_regist;
    }

    @Override
    protected void getData() {
        goLogin.setOnClickListener(this);
        tvGetcode.setOnClickListener(this);
        btRegist.setOnClickListener(this);
        StatusBarUtil.setTransparentForWindow(this);

    }
    @Override
    protected BasePresenter initPresenter() {
        return null;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_go_login:
                startActivity(new Intent(RegistActivity.this, LoginActivity.class));
                finish();
                break;
            case R.id.tv_getcode:
                phone = etPhone.getText().toString();
                if(StringUtil.checkPhoneNumber(phone)){
                    //调用倒计时方法
                    countDownTime();
                    NetUtils.getInstance().getApis().getPhoneCode(phone)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Observer<GetPhoneCodeBean>() {
                                @Override
                                public void onSubscribe(Disposable d) {

                                }

                                @Override
                                public void onNext(GetPhoneCodeBean getPhoneCodeBean) {
                                    auth = getPhoneCodeBean.getObject();
                                }

                                @Override
                                public void onError(Throwable e) {

                                }

                                @Override
                                public void onComplete() {

                                }
                            });
                }
                break;
            case R.id.bt_regist:
                String code = etCode.getText().toString();
                if(StringUtil.checkPhoneNumber(phone)){
                    if(StringUtil.checkSms(code)){
                        //判断输入的验证码与获取的验证码是否一致
                        if(code.equals(auth)){
                            //一致的话跳转到设置密码页
                            Intent intent = new Intent(RegistActivity.this, SetPwdActivity.class);
                            intent.putExtra("phone",phone);
                            startActivity(intent);

                        }else{
                            Toast.makeText(this, "验证码输入有误", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                break;
        }
    }
    //倒计时获取验证码
    private void countDownTime() {
        //用安卓自带的CountDownTimer实现
        mTimer = new CountDownTimer(60 * 1000, 1000) {
             @Override
             public void onTick(long millisUntilFinished) {
                 tvGetcode.setText(millisUntilFinished / 1000 + "秒重发");
                 isplayer = true;
             }

             @Override
             public void onFinish() {
                 tvGetcode.setEnabled(true);
                 tvGetcode.setText("获取验证码");
                 isplayer = false;
                 cancel();
             }
         };
        mTimer.start();
        tvGetcode.setEnabled(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(isplayer){
            mTimer.cancel();
        }
    }
}