package com.yidian.chengshengda.main.fragment.adv;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.yidian.chengshengda.R;
import com.yidian.chengshengda.base.BaseFragment;
import com.yidian.chengshengda.base.BasePresenter;

import butterknife.BindView;


//已售出
public class FragmentSell extends BaseFragment {
    @BindView(R.id.rc_sell)
    RecyclerView rcSell;

    @Override
    protected void getid(View view) {

    }
    @Override
    protected int getResId() {
        return R.layout.fragment_adv_sell;
    }
    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void getData() {

    }
}
