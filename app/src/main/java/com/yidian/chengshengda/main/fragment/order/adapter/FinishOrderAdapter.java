package com.yidian.chengshengda.main.fragment.order.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.yidian.chengshengda.R;
import com.yidian.chengshengda.custom.CustomDialog;
import com.yidian.chengshengda.custom.CustomRoundAngleImageView;
import com.yidian.chengshengda.main.fragment.order.bean.DeleteOrderBean;
import com.yidian.chengshengda.main.fragment.order.bean.OrderInfoBean;
import com.yidian.chengshengda.main.fragment.order.bean.SaveDeleteBean;
import com.yidian.chengshengda.utils.NetUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class FinishOrderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context context;
    private final List<OrderInfoBean.ObjectBean.ListBean> list;

    public FinishOrderAdapter(Context context, List<OrderInfoBean.ObjectBean.ListBean> list) {

        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_order_finish, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        OrderInfoBean.ObjectBean.ListBean listBean = list.get(position);
        ((ViewHolder)holder).tvSitesDistance.setText(listBean.getPlace());
        int allMoney = (int) listBean.getAllMoney();
        ((ViewHolder)holder).tvAllprice.setText("￥"+allMoney);
        ((ViewHolder)holder).tvPrice.setText(listBean.getMoney()+"元/月");

        if(listBean.getRealMoney()!=null){
            ((ViewHolder)holder).tvRealMoney.setVisibility(View.VISIBLE);

            int realMoney = (int) listBean.getRealMoney();
            ((ViewHolder)holder).tvRealMoney.setText("￥"+realMoney);
            ((ViewHolder)holder).tvAllprice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG); // 设置中划线并加清晰

        }else{
            ((ViewHolder)holder).tvRealMoney.setVisibility(View.GONE);
            ((ViewHolder)holder).tvAllprice.getPaint().setFlags(0);
        }
        String stationImg = listBean.getStationImg();
        if(stationImg==null){
            Glide.with(context).load(R.mipmap.headimg).into(((ViewHolder)holder).ivImg);
        }else{
            String[] split = stationImg.split(",");
            if(split.length>0 && split!=null){
                Glide.with(context).load("http://81.71.121.177:8081/station/getImage?head="+split[0]).into(((ViewHolder)holder).ivImg);
            }
        }
        ((ViewHolder)holder).tvDeleteorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialog.Builder builder = new CustomDialog.Builder(context);

                builder.setPositiveButton("删除", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        int id = listBean.getId();
                        dialog.dismiss();
                        //设置你的操作事项
                        // TODO: 2020/7/18 调用删除接口 删除订单
                        NetUtils.getInstance().getApis()
                                .doDeleteOrder(id)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Observer<DeleteOrderBean>() {
                                    @Override
                                    public void onSubscribe(Disposable d) {

                                    }

                                    @Override
                                    public void onNext(DeleteOrderBean deleteOrderBean) {
                                        if(deleteOrderBean.getType().equals("OK")){
                                            Toast.makeText(context, "删除成功", Toast.LENGTH_SHORT).show();
                                            SaveDeleteBean saveDeleteBean = new SaveDeleteBean();
                                            saveDeleteBean.setDelete("删除成功");

                                            EventBus.getDefault().post(saveDeleteBean);
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
                });
                builder.setNegativeButton("取消",
                        new android.content.DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                builder.create().show();
            }
        });
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_img)
        CustomRoundAngleImageView ivImg;
        @BindView(R.id.tv_sites_distance)
        TextView tvSitesDistance;
        @BindView(R.id.tv_price)
        TextView tvPrice;
        @BindView(R.id.tv_allprice)
        TextView tvAllprice;
        @BindView(R.id.tv_real_money)
        TextView tvRealMoney;
        @BindView(R.id.tv_deleteorder)
        TextView tvDeleteorder;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
