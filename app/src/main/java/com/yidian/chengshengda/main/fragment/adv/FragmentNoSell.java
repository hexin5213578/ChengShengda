package com.yidian.chengshengda.main.fragment.adv;

import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.liaoinstan.springview.container.AliFooter;
import com.liaoinstan.springview.container.AliHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.yidian.chengshengda.R;
import com.yidian.chengshengda.base.BaseFragment;
import com.yidian.chengshengda.base.BasePresenter;
import com.yidian.chengshengda.main.fragment.adv.adapter.NoSellSitesAdapter;
import com.yidian.chengshengda.main.fragment.adv.adapter.SellSitesAdapter;
import com.yidian.chengshengda.main.fragment.adv.bean.NosellSiteBean;
import com.yidian.chengshengda.main.fragment.adv.bean.SitesBean;
import com.yidian.chengshengda.utils.NetUtils;

import java.util.List;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


//未售出

public class FragmentNoSell extends BaseFragment {
    @BindView(R.id.rc_nosellsite)
    RecyclerView rcNosellsite;
    @BindView(R.id.sv)
    SpringView sv;
    int page = 1;
    @Override
    protected void getid(View view) {

    }

    @Override
    protected int getResId() {
        return R.layout.fragment_adv_nosell;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void getData() {
        sv.setHeader(new AliHeader(getContext()));


        // TODO: 2020/10/28 0028 获取已售出站点信息
        getSites(1,20);
        //下拉刷新上拉加载监听
        sv.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                page=1;
                getSites(page,20);

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
        showDialog();
        NetUtils.getInstance().getApis()
                .getNosellSite("http://192.168.10.106:8081/station/selectStationStatus",1,page,pageSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NosellSiteBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(NosellSiteBean sitesBean) {
                        hideDialog();
                        String type = sitesBean.getType();
                        List<NosellSiteBean.ObjectBean.ListBean> list = sitesBean.getObject().getList();

                        if(type.equals("OK")){
                            if(list.size()>0 && list!=null){
                                //设置适配器
                                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
                                rcNosellsite.setLayoutManager(linearLayoutManager);
                                NoSellSitesAdapter noSellSitesAdapter = new NoSellSitesAdapter(getContext(), list);
                                rcNosellsite.setAdapter(noSellSitesAdapter);
                            }
                            if(list.size()>20){
                                sv.setFooter(new AliFooter(getContext()));
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        hideDialog();
                        Toast.makeText(getContext(), "请求失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
