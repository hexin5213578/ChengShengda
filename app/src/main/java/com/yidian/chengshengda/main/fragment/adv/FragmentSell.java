package com.yidian.chengshengda.main.fragment.adv;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.liaoinstan.springview.container.AliFooter;
import com.liaoinstan.springview.container.AliHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.yidian.chengshengda.R;
import com.yidian.chengshengda.base.BaseFragment;
import com.yidian.chengshengda.base.BasePresenter;
import com.yidian.chengshengda.main.fragment.adv.adapter.SellSitesAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


//已售出
public class FragmentSell extends BaseFragment {
    @BindView(R.id.rc_sell)
    RecyclerView rcSell;
    @BindView(R.id.sv)
    SpringView sv;

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


        sv.setHeader(new AliHeader(getContext()));
        sv.setFooter(new AliFooter(getContext()));

        //下拉刷新上拉加载监听
        sv.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {

            }

            @Override
            public void onLoadmore() {

            }
        });
        // TODO: 2020/10/28 0028 获取已售出站点信息

        getSites(1,1);
    }
    public void getSites(int page,int pageSize){
        List<String> list = new ArrayList<>();
        list.add("梦阳");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        rcSell.setLayoutManager(linearLayoutManager);
        SellSitesAdapter sellSitesAdapter = new SellSitesAdapter(getContext(),list);
        rcSell.setAdapter(sellSitesAdapter);
    }
}
