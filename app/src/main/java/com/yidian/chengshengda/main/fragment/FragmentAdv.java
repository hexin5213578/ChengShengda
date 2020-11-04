package com.yidian.chengshengda.main.fragment;

import android.os.Build;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.leaf.library.StatusBarUtil;
import com.yidian.chengshengda.R;
import com.yidian.chengshengda.base.BaseFragment;
import com.yidian.chengshengda.base.BasePresenter;
import com.yidian.chengshengda.main.fragment.adv.FragmentNoSell;
import com.yidian.chengshengda.main.fragment.adv.FragmentSell;

import butterknife.BindView;

public class FragmentAdv extends BaseFragment implements RadioGroup.OnCheckedChangeListener {
    @BindView(R.id.rbSell)
    RadioButton rbSell;
    @BindView(R.id.rbNoSell)
    RadioButton rbNoSell;
    @BindView(R.id.rgTab)
    RadioGroup rgTab;
    @BindView(R.id.rl_top)
    RelativeLayout rlTop;
    @BindView(R.id.flContent)
    FrameLayout flContent;
    RadioButton[] rbs = new RadioButton[2];
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private FragmentManager fmManager;

    /**
     * 当前展示的Fragment
     */
    private Fragment currentFragment;

    /**
     * 创建Fragment实例
     */
    private FragmentSell fragmentSell;
    private FragmentNoSell fragmentNoSell;

    @Override
    protected void getid(View view) {

    }

    @Override
    protected int getResId() {
        return R.layout.fragment_adv;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void getData() {
        //设置状态栏颜色与字体颜色

        StatusBarUtil.setGradientColor(getActivity(), toolbar);
        StatusBarUtil.setDarkMode(getActivity());

        rbs[0] = rbSell;
        rbs[1] = rbNoSell;
        fmManager = getChildFragmentManager();
        rgTab.setOnCheckedChangeListener(this);
        //创建fragment实例
        fragmentSell = new FragmentSell();
        fragmentNoSell = new FragmentNoSell();
        /**
         * 首次进入加载第一个界面
         */
        rbSell.setChecked(true);
        rbSell.setTextSize(18);
        rbSell.setTextAppearance(R.style.txt_bold);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            //设置切换页面及选中字体大小
            case R.id.rbSell:
                replace(fragmentSell);
                rbSell.setTextSize(18);
                rbSell.setTextAppearance(R.style.txt_bold);

                rbNoSell.setTextSize(16);
                rbNoSell.setTextAppearance(R.style.txt_nomal);
                break;
            case R.id.rbNoSell:
                replace(fragmentNoSell);
                rbNoSell.setTextSize(18);
                rbNoSell.setTextAppearance(R.style.txt_bold);

                rbSell.setTextSize(16);
                rbSell.setTextAppearance(R.style.txt_nomal);
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
}
