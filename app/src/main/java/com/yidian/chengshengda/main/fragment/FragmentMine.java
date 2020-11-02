package com.yidian.chengshengda.main.fragment;

import android.content.Intent;
import android.content.res.Resources;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.tabs.TabLayout;
import com.leaf.library.StatusBarUtil;
import com.yidian.chengshengda.R;
import com.yidian.chengshengda.base.BaseFragment;
import com.yidian.chengshengda.base.BasePresenter;
import com.yidian.chengshengda.main.fragment.order.FragmentFailed;
import com.yidian.chengshengda.main.fragment.order.FragmentFinished;
import com.yidian.chengshengda.main.fragment.order.FragmentLeased;
import com.yidian.chengshengda.setup.SetupActivity;
import com.yidian.chengshengda.setup.bean.UserInfoBean;
import com.yidian.chengshengda.utils.NetUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class FragmentMine extends BaseFragment {
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.iv_headimg)
    ImageView ivHeadimg;
    @BindView(R.id.iv_setup)
    ImageView ivSetup;
    @BindView(R.id.tv_name)
    TextView tvName;
    private List<String> tabs = new ArrayList<>();
    List<Fragment> fragments = new ArrayList<>();

    @Override
    protected void getid(View view) {

    }

    @Override
    protected int getResId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void getData() {
        //设置状态栏颜色与字体颜色

        StatusBarUtil.setGradientColor(getActivity(), toolbar);
        StatusBarUtil.setDarkMode(getActivity());

        tabs.add("租赁中");
        tabs.add("已失败");
        tabs.add("已结束");
        tab.addTab(tab.newTab().setText(tabs.get(0)));
        tab.addTab(tab.newTab().setText(tabs.get(1)));
        tab.addTab(tab.newTab().setText(tabs.get(2)));


        FragmentLeased fragmentLeased = new FragmentLeased();
        FragmentFailed fragmentFailed = new FragmentFailed();
        FragmentFinished fragmentFinished = new FragmentFinished();

        fragments.add(fragmentLeased);
        fragments.add(fragmentFailed);
        fragments.add(fragmentFinished);

        //设置的单击事件
        ivSetup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到设置页
                startActivity(new Intent(getContext(), SetupActivity.class));
            }
        });

        //设置基本信息
        getUserInfo(1);

        //设置tab的长度
        tab.post(new Runnable() {
            @Override
            public void run() {
                setIndicator(tab, 35, 35);
            }
        });
        //设置tab监听
        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                View customView = tab.getCustomView();
                if (customView == null) {
                    tab.setCustomView(R.layout.tab_text_layout);
                }
                TextView textView = tab.getCustomView().findViewById(android.R.id.text1);
                textView.setTextAppearance(getContext(), R.style.TabLayoutTextSelected);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                View customView = tab.getCustomView();
                if (customView == null) {
                    tab.setCustomView(R.layout.tab_text_layout);
                }
                TextView textView = tab.getCustomView().findViewById(android.R.id.text1);
                textView.setTextAppearance(getContext(), R.style.TabLayoutTextUnSelected);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        //设置适配器
        MyAdapter myAdapter = new MyAdapter(getChildFragmentManager());
        vp.setAdapter(myAdapter);
        //绑定tab与vp
        tab.setupWithViewPager(vp);

    }

    //设置tab栏下划线长度
    public void setIndicator(TabLayout tabs, int leftDip, int rightDip) {
        Class<?> tabLayout = tabs.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayout.getDeclaredField("slidingTabIndicator");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        tabStrip.setAccessible(true);
        LinearLayout llTab = null;
        try {
            llTab = (LinearLayout) tabStrip.get(tabs);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip, Resources.getSystem().getDisplayMetrics());
        int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip, Resources.getSystem().getDisplayMetrics());

        for (int i = 0; i < llTab.getChildCount(); i++) {
            View child = llTab.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            params.leftMargin = left;
            params.rightMargin = right;
            child.setLayoutParams(params);
            child.invalidate();
        }


    }

    public class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return tabs.get(position);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("xxx","onResume()");
        getUserInfo(1);
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("xxx","onPause()");
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.e("xxx","onHiddenChanged()");
    }

    //获取用户信息
    public void getUserInfo(int id){
        //获取用户信息
        showDialog();
        NetUtils.getInstance().getApis()
                .getUserInfo("http://192.168.10.104:8081/user/selectUserInfoByIdx",49)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserInfoBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UserInfoBean userInfoBean) {
                        hideDialog();
                        UserInfoBean.ObjectBean object = userInfoBean.getObject();
                        if(userInfoBean.getType().equals("OK")){
                            tvName.setText(object.getNickName());

                            String headImg = object.getHeadImg();
                            if(headImg==null || headImg.equals("")){
                                //头像为空  设置默认头像
                                Glide.with(getActivity()).load(R.mipmap.head).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(ivHeadimg);
                            }else{
                                //加载头像
                                Glide.with(getActivity()).load(headImg).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(ivHeadimg);
                            }
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
    }
}
