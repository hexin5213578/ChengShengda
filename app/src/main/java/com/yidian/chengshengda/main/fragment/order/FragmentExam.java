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
import com.yidian.chengshengda.main.fragment.order.bean.OrderInfoBean;
import com.yidian.chengshengda.utils.NetUtils;

import java.util.List;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

//审核中订单
public class FragmentExam extends BaseFragment {
    @BindView(R.id.rc_order)
    RecyclerView rcOrder;
    @BindView(R.id.sv)
    SpringView sv;
    @BindView(R.id.iv_noorder)
    ImageView ivNoorder;
    int page = 1;
    private String id;

    @Override
    protected void getid(View view) {

    }

    @Override
    protected int getResId() {
        return R.layout.fragment_order_exam;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void getData() {
        sv.setHeader(new AliHeader(getContext()));
        id = Common.getUserId();

        getOrderInfo(Integer.parseInt(id),page,15);
        //刷新监听
        sv.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                page=1;
                getOrderInfo(Integer.parseInt(id),page,15);
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
                getOrderInfo(Integer.parseInt(id),page,15);
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
                .getOrderInfo(id,0,page,size)
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

                            ExamOrderAdapte examOrder = new ExamOrderAdapte(getContext(), list);
                            rcOrder.setAdapter(examOrder);

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
}
