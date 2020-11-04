package com.yidian.chengshengda.main.fragment.order;

import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.liaoinstan.springview.container.AliFooter;
import com.liaoinstan.springview.container.AliHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.yidian.chengshengda.R;
import com.yidian.chengshengda.base.BaseFragment;
import com.yidian.chengshengda.base.BasePresenter;
import com.yidian.chengshengda.base.Common;
import com.yidian.chengshengda.main.fragment.order.adapter.ExamOrderAdapte;
import com.yidian.chengshengda.main.fragment.order.adapter.FinishOrderAdapter;
import com.yidian.chengshengda.main.fragment.order.bean.OrderInfoBean;
import com.yidian.chengshengda.main.fragment.order.bean.SaveDeleteBean;
import com.yidian.chengshengda.utils.NetUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

//已结束订单

public class FragmentFinished extends BaseFragment {
    @BindView(R.id.rc_order)
    RecyclerView rcOrder;
    @BindView(R.id.sv)
    SpringView sv;
    @BindView(R.id.iv_noorder)
    ImageView ivNoorder;
    int page = 1;
    int id ;
    private FinishOrderAdapter finishOrderAdapter;

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
        sv.setHeader(new AliHeader(getContext()));

        id = Integer.parseInt(Common.getUserId());

        getOrderInfo(id,page,15);
        //刷新监听
        sv.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                page=1;
                getOrderInfo(id,page,15);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        sv.onFinishFreshAndLoad();
                    }
                }, 1000);
            }

            @Override
            public void onLoadmore() {
                page++;
                getOrderInfo(id,page,15);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        sv.onFinishFreshAndLoad();
                    }
                }, 1000);
            }
        });
    }
    //获取订单信息
    public void getOrderInfo(int id,int page,int size){
        NetUtils.getInstance().getApis()
                .getOrderInfo(id,2,page,size)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<OrderInfoBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(OrderInfoBean orderInfoBean) {
                        List<OrderInfoBean.ObjectBean.ListBean> list =
                                orderInfoBean.getObject().getList();

                        if(list.size()>0 && list!=null){
                            ivNoorder.setVisibility(View.GONE);
                            sv.setVisibility(View.VISIBLE);
                            //设置适配器
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
                            rcOrder.setLayoutManager(linearLayoutManager);

                            finishOrderAdapter = new FinishOrderAdapter(getContext(), list);
                            rcOrder.setAdapter(finishOrderAdapter);

                        }else{
                            sv.setVisibility(View.GONE);
                            ivNoorder.setVisibility(View.VISIBLE);
                        }
                        if(list.size()>15){
                            sv.setFooter(new AliFooter(getContext()));
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
    //接受删除回调
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getDelete(SaveDeleteBean saveDeleteBean){
        String delete = saveDeleteBean.getDelete();
        if(delete.equals("删除成功")){
            //刷新适配器
            getOrderInfo(id,page,15);
            finishOrderAdapter.notifyDataSetChanged();
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().unregister(this);
        }
    }
}
