package com.yidian.chengshengda.setup;

import android.Manifest;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.leaf.library.StatusBarUtil;
import com.yidian.chengshengda.R;
import com.yidian.chengshengda.base.App;
import com.yidian.chengshengda.base.BaseAvtivity;
import com.yidian.chengshengda.base.BasePresenter;
import com.yidian.chengshengda.changepwd.ChangePwdActivity;
import com.yidian.chengshengda.login.activity.LoginActivity;
import com.yidian.chengshengda.setpwd.SetPwdActivity;
import com.yidian.chengshengda.utils.DataCleanManager;
import com.yidian.chengshengda.utils.SPUtil;
import com.yidian.chengshengda.utils.StringUtil;

import org.lym.image.select.PictureSelector;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SetupActivity extends BaseAvtivity implements View.OnClickListener {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.iv_headimg)
    ImageView ivHeadimg;
    @BindView(R.id.rl_img)
    RelativeLayout rlImg;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.rl_name)
    RelativeLayout rlName;
    @BindView(R.id.rl_set_pwd)
    RelativeLayout rlSetPwd;
    @BindView(R.id.tv_huancun)
    TextView tvHuancun;
    @BindView(R.id.rl_clean)
    RelativeLayout rlClean;
    @BindView(R.id.tv_banben)
    TextView tvBanben;
    @BindView(R.id.rl_banben)
    RelativeLayout rlBanben;
    @BindView(R.id.tv_zhuxiao)
    TextView tvZhuxiao;
    @BindView(R.id.tv_cancle)
    TextView tvCancle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private String path;
    private PopupWindow mPopupWindow;

    @Override
    protected int getResId() {
        return R.layout.activity_setup;
    }

    @Override
    protected void getData() {
        //设置状态栏颜色与字体颜色
        StatusBarUtil.setGradientColor(this, toolbar);
        StatusBarUtil.setDarkMode(this);

        back.setOnClickListener(this);
        rlImg.setOnClickListener(this);
        rlName.setOnClickListener(this);
        rlSetPwd.setOnClickListener(this);
        rlClean.setOnClickListener(this);
        rlBanben.setOnClickListener(this);
        tvZhuxiao.setOnClickListener(this);
        tvCancle.setOnClickListener(this);

        //申请开启内存卡权限
        if ((Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) && (this.checkSelfPermission
                (Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
            //请求权限
            this.requestPermissions(new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }

        //获取当前应用版本号
        String appVersionName = "";
        try {
            PackageInfo packageInfo = this.getApplicationContext()
                    .getPackageManager()
                    .getPackageInfo(this.getPackageName(), 0);
            appVersionName = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("", e.getMessage());
        }
        tvBanben.setText(appVersionName+"");

        //获取缓存
        String totalCacheSize = DataCleanManager.getTotalCacheSize(this);
        tvHuancun.setText(totalCacheSize);

    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
                //返回
            case R.id.back:

                finish();
                break;
                //更换头像
            case R.id.rl_img:


                PictureSelector
                        .with(this)
                        .selectSpec()
                        .setOpenCamera()
                        .needCrop()
                        .setOutputX(200)
                        .setOutputY(200)
                        //开启拍照功能一定得设置该属性，为了兼容Android7.0相机拍照问题
                        //在manifest文件中也需要注册该provider
                        .setAuthority("com.yidian.chengshengda.utils.MyFileProvider")
                        .startForResult(100);
                break;
                //更改昵称
            case R.id.rl_name:
                showSelect();
                break;
                //跳转修改密码页
            case R.id.rl_set_pwd:
                startActivity(new Intent(this, ChangePwdActivity.class));
                break;
                //清除缓存
            case R.id.rl_clean:
                showSelect1();


                break;
                //查看版本
            case R.id.rl_banben:
                // TODO: 2020/10/21 0021 跳转到版本页
                break;
                //注销账号
            case R.id.tv_zhuxiao:

                break;
                //退出登录
            case R.id.tv_cancle:
                //清除登录信息 返回登录页
                SPUtil.getInstance().saveData(SetupActivity.this, SPUtil.FILE_NAME, SPUtil.IS_LOGIN, "1");
                startActivity(new Intent(SetupActivity.this, LoginActivity.class));
                finish();
                break;
        }
    }
    //图片回调
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == Activity.RESULT_OK) {
            if (null != data) {
                //图片单选和多选数据都是以ArrayList的字符串数组返回的。
                List<String> paths = PictureSelector.obtainPathResult(data);
                path = paths.get(0);
                Glide.with(this).load(path).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(ivHeadimg);
            }
        }
    }
    // 弹出选择规格
    public void showSelect() {
        //创建popwiondow弹出框
        mPopupWindow = new PopupWindow();
        mPopupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_changename,null);
        EditText et_name = view.findViewById(R.id.et_name);
        TextView tv_rem = view.findViewById(R.id.tv_Rem);


        //将用户名 回显到输入框
        et_name.setText(tvName.getText().toString());

        //文本输入前 获取长度赋值给长度计算
        int length = et_name.getText().length();
        tv_rem.setText(length+"/10");
        //输入框监听器
        et_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //文本输入结束后 获取长度赋值给长度计算
                int length = et_name.getText().length();
                tv_rem.setText(length+"/10");
            }
        });
        //取消
        view.findViewById(R.id.tv_cancle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              dismiss();
            }
        });
        view.findViewById(R.id.tv_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取输入框文本  判空发起更换昵称的网络请求  请求成功关闭弹出框
                String s = et_name.getText().toString();
                if(!TextUtils.isEmpty(s)){
                    
                }else{
                    Toast.makeText(SetupActivity.this, "昵称不能为空", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //popwindow设置属性
        mPopupWindow.setContentView(view);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setWindowAlpa(false);
            }
        });
        show(view);
    }

    // 弹出选择规格
    public void showSelect1() {
        //创建popwiondow弹出框
        mPopupWindow = new PopupWindow();
        mPopupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_cleanmanager,null);

        //取消
        view.findViewById(R.id.tv_cancle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        view.findViewById(R.id.tv_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //清除缓存
                DataCleanManager.clearAllCache(SetupActivity.this);
                //重新获取缓存
                tvHuancun.setText("");
                dismiss();
            }
        });

        //popwindow设置属性
        mPopupWindow.setContentView(view);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setWindowAlpa(false);
            }
        });
        show(view);
    }

    // 设置透明度
    public void setWindowAlpa(boolean isopen) {
        if (android.os.Build.VERSION.SDK_INT < 11) {
            return;
        }
        final Window window = this.getWindow();
        final WindowManager.LayoutParams lp = window.getAttributes();
        window.setFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND, WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        ValueAnimator animator;
        if (isopen) {
            animator = ValueAnimator.ofFloat(1.0f, 0.5f);
        } else {
            animator = ValueAnimator.ofFloat(0.5f, 1.0f);
        }
        animator.setDuration(400);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @TargetApi(Build.VERSION_CODES.HONEYCOMB)
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float alpha = (float) animation.getAnimatedValue();
                lp.alpha = alpha;
                window.setAttributes(lp);
            }
        });
        animator.start();
    }

    /**
     * 显示PopupWindow
     */
    private void show(View v) {
        if (mPopupWindow != null && !mPopupWindow.isShowing()) {
            mPopupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);
        }
        setWindowAlpa(true);
    }


    /**
     * 消失PopupWindow
     */
    public void dismiss() {
        if (mPopupWindow != null && mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
        }
    }
}
