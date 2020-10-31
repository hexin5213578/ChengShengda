package com.yidian.chengshengda.welcome;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

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

        if (Build.VERSION.SDK_INT >= 23) {
            int request = ContextCompat.checkSelfPermission(WelcomeActivity.this, Manifest.permission.ACCESS_FINE_LOCATION);
            if (request != PackageManager.PERMISSION_GRANTED)//缺少权限，进行权限申请
            {
                ActivityCompat.requestPermissions(WelcomeActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 100);
            }else{

            }
        }

        StatusBarUtil.setTransparentForWindow(WelcomeActivity.this);
        rlJump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isLogin = SPUtil.getInstance().getData(WelcomeActivity.this, SPUtil.FILE_NAME, SPUtil.IS_LOGIN);
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);
              /*  if(isLogin !=null){
                    if(isLogin.equals("0")){

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
                }*/
            }
        });


       /* Handler handler = new Handler();
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
        }, 2000);//2秒后执行Runnable中的run方法*/
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }
    //参数 requestCode是我们在申请权限的时候使用的唯一的申请码
    //String[] permission则是权限列表，一般用不到
    //int[] grantResults 是用户的操作响应，包含这权限是够请求成功
    //由于在权限申请的时候，我们就申请了一个权限，所以此处的数组的长度都是1
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==100){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(WelcomeActivity.this, "权限申请成功", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(WelcomeActivity.this,MainActivity.class));
            } else if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                Toast.makeText(WelcomeActivity.this, "权限申请失败，用户拒绝权限", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
