package com.yidian.chengshengda.main;

import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.yidian.chengshengda.R;
import com.yidian.chengshengda.base.BaseAvtivity;
import com.yidian.chengshengda.base.BasePresenter;
import com.yidian.chengshengda.main.fragment.FragmentAdv;
import com.yidian.chengshengda.main.fragment.FragmentMain;
import com.yidian.chengshengda.main.fragment.FragmentMine;
import com.yidian.chengshengda.main.fragment.FragmentShopCar;

import butterknife.BindView;

public class MainActivity extends BaseAvtivity implements RadioGroup.OnCheckedChangeListener {

    @BindView(R.id.flContent)
    FrameLayout flContent;
    @BindView(R.id.rbHome)
    RadioButton rbHome;
    @BindView(R.id.rbAdv)
    RadioButton rbAdv;
    @BindView(R.id.rbShopCar)
    RadioButton rbShopCar;
    @BindView(R.id.rbMine)
    RadioButton rbMine;
    @BindView(R.id.rgMenu)
    RadioGroup rgMenu;
    @BindView(R.id.rl_main)
    RelativeLayout rlMain;
    RadioButton[] rbs = new RadioButton[4];
    private FragmentManager fmManager;
    /**
     * 当前展示的Fragment
     */
    private Fragment currentFragment;
    /**
     * 上次点击返回按钮的时间戳
     */
    private long firstPressedTime;

    /**
     * 创建Fragment实例
     */
    private FragmentMain fragmentMain;
    private FragmentAdv fragmentAdv;
    private FragmentShopCar fragmentShopCar;
    private FragmentMine fragmentmine;


    @Override
    protected int getResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void getData() {

        rbs[0] = rbHome;
        rbs[1] = rbAdv;
        rbs[2] = rbShopCar;
        rbs[3] = rbMine;

        for (RadioButton rb : rbs) {
            //给每个RadioButton加入drawable限制边距控制显示大小
            Drawable[] drawables = rb.getCompoundDrawables();
            //获取drawables
            Rect rt = new Rect(0, 0, 70, 70);
            //定义一个Rect边界
            drawables[1].setBounds(rt);

            //添加限制给控件
            rb.setCompoundDrawables(null, drawables[1], null, null);
        }

        fmManager = getSupportFragmentManager();
        rgMenu.setOnCheckedChangeListener(this);
        //创建fragment实例
        fragmentMain = new FragmentMain();
        fragmentAdv = new FragmentAdv();
        fragmentShopCar = new FragmentShopCar();
        fragmentmine = new FragmentMine();
        /**
         * 首次进入加载第一个界面
         */
        rbHome.setChecked(true);
        //设置状态栏颜色



    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rbHome:
                replace(fragmentMain);
                break;
            case R.id.rbAdv:
                replace(fragmentAdv);
                break;
            case R.id.rbShopCar:
                replace(fragmentShopCar);
                break;
            case R.id.rbMine:
                replace(fragmentmine);
                break;
            default:
                break;
        }
    }
    /**
     * 切换页面显示fragment
     *
     * @param to 跳转到的fragment
     */
    private void replace(Fragment to) {
        if (to == null || to == currentFragment) {
            // 如果跳转的fragment为空或者跳转的fragment为当前fragment则不做操作
            return;
        }
        if (currentFragment == null) {
            // 如果当前fragment为空,即为第一次添加fragment
            fmManager.beginTransaction()
                    .add(R.id.flContent, to)
                    .commitAllowingStateLoss();
            currentFragment = to;
            return;
        }
        // 切换fragment
        FragmentTransaction transaction = fmManager.beginTransaction().hide(currentFragment);
        if (!to.isAdded()) {
            transaction.add(R.id.flContent, to);
        } else {
            transaction.show(to);
        }
        transaction.commitAllowingStateLoss();
        currentFragment = to;
    }

    //定义一个变量，来标识是否退出
    private static int isExit = 0;

    //实现按两次后退才退出
    Handler handler = new Handler() {
        @SuppressLint("HandlerLeak")
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit--;
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            isExit++;
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if (isExit < 2) {
            Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
            //利用handler延迟发送更改状态信息
            handler.sendEmptyMessageDelayed(0, 2000);
        } else {
            super.onBackPressed();
        }
    }

}