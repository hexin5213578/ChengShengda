package com.yidian.chengshengda.welcome;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.leaf.library.StatusBarUtil;
import com.yidian.chengshengda.R;
import com.yidian.chengshengda.base.App;
import com.yidian.chengshengda.base.BaseAvtivity;
import com.yidian.chengshengda.base.BasePresenter;
import com.yidian.chengshengda.base.Common;
import com.yidian.chengshengda.login.activity.LoginActivity;
import com.yidian.chengshengda.main.MainActivity;
import com.yidian.chengshengda.regist.activity.RegistActivity;
import com.yidian.chengshengda.utils.SPUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WelcomeActivity extends BaseAvtivity {


    @BindView(R.id.rl_jump)
    TextView rlJump;
    private String isLogin;

    @Override
    protected int getResId() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void getData() {
        StatusBarUtil.setTransparentForWindow(WelcomeActivity.this);
        rlJump.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                isLogin = SPUtil.getInstance().getData(WelcomeActivity.this, SPUtil.FILE_NAME, SPUtil.IS_LOGIN);

                if(isLogin !=null){
                    if(isLogin.equals("0")){
                        Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }else{
                        Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }else{
                    Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                isLogin = SPUtil.getInstance().getData(WelcomeActivity.this, SPUtil.FILE_NAME, SPUtil.IS_LOGIN);

                if(isLogin !=null){
                    if(isLogin.equals("0")){
                        Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }else{
                        Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }else{
                    Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        }, 2000);//2秒后执行Runnable中的run方法
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

}
