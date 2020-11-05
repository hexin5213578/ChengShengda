package com.yidian.chengshengda.changepwd;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.PagerAdapter;

import com.leaf.library.StatusBarUtil;
import com.yidian.chengshengda.R;
import com.yidian.chengshengda.base.BaseAvtivity;
import com.yidian.chengshengda.base.BasePresenter;
import com.yidian.chengshengda.base.Common;
import com.yidian.chengshengda.setpwd.bean.SetPwdBean;
import com.yidian.chengshengda.utils.KeyBoardUtils;
import com.yidian.chengshengda.utils.NetUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ChangePwdActivity extends BaseAvtivity implements View.OnClickListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.et_old_pwd)
    EditText etOldPwd;
    @BindView(R.id.et_new_pwd)
    EditText etNewPwd;
    @BindView(R.id.et_new_pwd2)
    EditText etNewPwd2;
    @BindView(R.id.bt_confirm)
    Button btConfirm;
    private String userId;

    @Override
    protected int getResId() {
        return R.layout.activity_change_pwd;
    }

    @Override
    protected void getData() {
        //设置状态栏背景及字体颜色
        StatusBarUtil.setGradientColor(this,toolbar);
        StatusBarUtil.setDarkMode(this);
        back.setOnClickListener(this);
        btConfirm.setOnClickListener(this);

        userId = Common.getUserId();

    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.bt_confirm:

                KeyBoardUtils.closeKeyboard(ChangePwdActivity.this);

                //获取密码校验完毕后 发起更改密码的网络请求
                String oldpwd = etOldPwd.getText().toString();
                String pwd1 = etNewPwd.getText().toString();
                String pwd2 = etNewPwd2.getText().toString();
                if(pwd1.equals(pwd2)){
                    //发起网络请求
                    showDialog();
                    NetUtils.getInstance().getApis()
                            .doSetPwd(Integer.parseInt(userId),oldpwd,pwd1)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Observer<SetPwdBean>() {
                                @Override
                                public void onSubscribe(Disposable d) {

                                }

                                @Override
                                public void onNext(SetPwdBean setPwdBean) {
                                    hideDialog();
                                    if (setPwdBean.getType().equals("OK")){
                                        Toast.makeText(ChangePwdActivity.this, "密码修改成功", Toast.LENGTH_SHORT).show();

                                        finish();

                                    }else{
                                        Toast.makeText(ChangePwdActivity.this, ""+setPwdBean.getMsg(), Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onError(Throwable e) {
                                    hideDialog();
                                }

                                @Override
                                public void onComplete() {

                                }
                            });
                }else{
                    Toast.makeText(this, "两次输入密码不一致", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
