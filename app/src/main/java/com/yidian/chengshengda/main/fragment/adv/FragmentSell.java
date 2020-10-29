package com.yidian.chengshengda.main.fragment.adv;

import android.os.Handler;
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
import com.yidian.chengshengda.main.fragment.adv.bean.SitesBean;
import com.yidian.chengshengda.utils.NetUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


//已售出
public class FragmentSell extends BaseFragment {
    @BindView(R.id.rc_sell)
    RecyclerView rcSell;
    @BindView(R.id.sv)
    SpringView sv;
    int page = 1;
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


        // TODO: 2020/10/28 0028 获取已售出站点信息
        getSites(1,20);
        //下拉刷新上拉加载监听
        sv.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                getSites(1,20);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        sv.onFinishFreshAndLoad();
                    }
                }, 1000);
            }

            /**
             * @param
             */
            @Override
            public void onLoadmore() {
                page++;
                getSites(page,20);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        sv.onFinishFreshAndLoad();
                    }
                }, 1000);
            }
        });

    }
    public void getSites(int page,int pageSize){
        NetUtils.getInstance().getApis()
                .getSitesfromStatus("http://192.168.10.111:8081/station/selectStationStatus",2,page,pageSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SitesBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SitesBean sitesBean) {
                        String type = sitesBean.getType();
                        List<SitesBean.ObjectBean> object = sitesBean.getObject();
                        if(type.equals("OK")){
                            if(object.size()>0 && object!=null){
                                //设置适配器
                                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
                                rcSell.setLayoutManager(linearLayoutManager);
                                SellSitesAdapter sellSitesAdapter = new SellSitesAdapter(getContext(), object);
                                rcSell.setAdapter(sellSitesAdapter);
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
