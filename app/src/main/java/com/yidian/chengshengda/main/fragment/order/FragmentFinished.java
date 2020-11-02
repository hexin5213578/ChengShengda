package com.yidian.chengshengda.main.fragment.order;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.liaoinstan.springview.widget.SpringView;
import com.yidian.chengshengda.R;
import com.yidian.chengshengda.base.BaseFragment;
import com.yidian.chengshengda.base.BasePresenter;
import com.yidian.chengshengda.main.fragment.order.adapter.FinishOrderAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

//已结束订单

public class FragmentFinished extends BaseFragment {
    @BindView(R.id.rc_order)
    RecyclerView rcOrder;
    @BindView(R.id.sv)
    SpringView sv;

    @Override
    protected void getid(View view) {

    }

    @Override
    protected int getResId() {
        return R.layout.fragment_order_finished;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void getData() {
        //布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        rcOrder.setLayoutManager(linearLayoutManager);
        //创建数据
        List<String> list = new ArrayList<>();
        list.add("梦阳");
        FinishOrderAdapter orderAdapter = new FinishOrderAdapter(getContext(), list);
        rcOrder.setAdapter(orderAdapter);
    }
}
