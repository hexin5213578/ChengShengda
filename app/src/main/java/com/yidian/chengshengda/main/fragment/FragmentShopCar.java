package com.yidian.chengshengda.main.fragment;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.leaf.library.StatusBarUtil;
import com.yidian.chengshengda.R;
import com.yidian.chengshengda.base.BaseFragment;
import com.yidian.chengshengda.base.BasePresenter;

import butterknife.BindView;

public class FragmentShopCar extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_manager)
    TextView tvManager;
    @BindView(R.id.tv_finish)
    TextView tvFinish;
    @BindView(R.id.rc_shopcar)
    RecyclerView rcShopcar;
    @BindView(R.id.check_all)
    CheckBox checkAll;
    @BindView(R.id.tv_allprice)
    TextView tvAllprice;
    @BindView(R.id.bt_commit)
    Button btCommit;
    @BindView(R.id.ll_price)
    LinearLayout llPrice;
    @BindView(R.id.bt_delete)
    Button btDelete;

    @Override
    protected void getid(View view) {

    }

    @Override
    protected int getResId() {
        return R.layout.fragment_shop_car;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void getData() {
        //设置状态栏颜色及字体颜色
        StatusBarUtil.setGradientColor(getActivity(),toolbar);
        StatusBarUtil.setDarkMode(getActivity());

        tvManager.setOnClickListener(this);
        tvFinish.setOnClickListener(this);
        btCommit.setOnClickListener(this);
        btDelete.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //管理
            case R.id.tv_manager:
                //隐藏管理 显示完成
                tvManager.setVisibility(View.GONE);
                tvFinish.setVisibility(View.VISIBLE);
                //隐藏价格 显示删除
                llPrice.setVisibility(View.GONE);
                btDelete.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_finish:
                //点击完成 隐藏完成显示管理
                tvManager.setVisibility(View.VISIBLE);
                tvFinish.setVisibility(View.GONE);
                //隐藏价格 显示删除
                llPrice.setVisibility(View.VISIBLE);
                btDelete.setVisibility(View.GONE);
                break;
                //提交商品 弹出联系方式
            case R.id.bt_commit:


                break;
                //删除购物车中数据
            case R.id.bt_delete:



                break;
        }
    }

}
