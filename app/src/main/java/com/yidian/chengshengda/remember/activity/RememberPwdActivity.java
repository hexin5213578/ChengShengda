package com.yidian.chengshengda.remember.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.CountDownTimer;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.leaf.library.StatusBarUtil;
import com.yidian.chengshengda.R;
import com.yidian.chengshengda.base.BaseAvtivity;
import com.yidian.chengshengda.base.BasePresenter;
import com.yidian.chengshengda.base.Common;
import com.yidian.chengshengda.main.bean.UpdateShopcarMonthBean;
import com.yidian.chengshengda.regist.bean.GetPhoneCodeBean;
import com.yidian.chengshengda.utils.NetUtils;
import com.yidian.chengshengda.utils.StringUtil;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RememberPwdActivity extends BaseAvtivity implements View.OnClickListener {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.tv_getcode)
    TextView tvGetcode;
    @BindView(R.id.et_pwd1)
    EditText etPwd1;
    @BindView(R.id.iv_see_pwd1)
    ImageView ivSeePwd1;
    @BindView(R.id.bt_confirm)
    Button btConfirm;
    private CountDownTimer mTimer;
    private boolean isplayer =false;
    private String phone;
    private String auth;
    private String userId;

    @Override
    protected int getResId() {
        return R.layout.activity_remember_pwd;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void getData() {
        StatusBarUtil.setTransparentForWindow(this);

        userId = Common.getUserId();

        back.setOnClickListener(this);
        tvGetcode.setOnClickListener(this);
        btConfirm.setOnClickListener(this);

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
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                //返回登录页
                finish();
                break;
                //确认修改密码
            case R.id.bt_confirm:
                // TODO: 2020/10/6 0006 调用修改密码接口  修改成功后跳转至登录页
                String code = etCode.getText().toString();
                String pwd = etPwd1.getText().toString();
                if(code.equals(auth)){
                    //调用修改密码的接口
                    NetUtils.getInstance().getApis()
                            .rememberpwd(Integer.parseInt(userId),pwd)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Observer<UpdateShopcarMonthBean>() {
                                @Override
                                public void onSubscribe(Disposable d) {

                                }

                                @Override
                                public void onNext(UpdateShopcarMonthBean updateShopcarMonthBean) {
                                    if(updateShopcarMonthBean.getType().equals("OK")){
                                        finish();
                                    }
                                }

                                @Override
                                public void onError(Throwable e) {

                                }

                                @Override
                                public void onComplete() {

                                }
                            });

                }else{
                    Toast.makeText(this, "验证码输入有误", Toast.LENGTH_SHORT).show();
                }
                break;
                //获取验证码
            case R.id.tv_getcode:
                phone = etPhone.getText().toString();

                if(StringUtil.checkPhoneNumber(phone)){
                    countDownTime();
                    NetUtils.getInstance().getApis()
                            .getPhoneCode(phone)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Observer<GetPhoneCodeBean>() {
                                @Override
                                public void onSubscribe(Disposable d) {

                                }

                                @Override
                                public void onNext(GetPhoneCodeBean getPhoneCodeBean) {
                                    if(getPhoneCodeBean.getType().equals("OK")){
                                       auth = getPhoneCodeBean.getObject();
                                        Toast.makeText(RememberPwdActivity.this, "验证码发送成功", Toast.LENGTH_SHORT).show();
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
                break;
        }
    }
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
                isplayer  =false;
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
