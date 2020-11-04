package com.yidian.chengshengda.main.fragment.order.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.yidian.chengshengda.R;
import com.yidian.chengshengda.custom.CustomRoundAngleImageView;
import com.yidian.chengshengda.main.fragment.order.bean.OrderInfoBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ExamOrderAdapte extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final Context context;
    private final List<OrderInfoBean.ObjectBean.ListBean> list;

    public ExamOrderAdapte(Context context, List<OrderInfoBean.ObjectBean.ListBean> list) {

        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_order_exam, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        OrderInfoBean.ObjectBean.ListBean listBean = list.get(position);
        ((ViewHolder)holder).tvName.setText(listBean.getPlace());
        int allMoney = (int) listBean.getAllMoney();
        ((ViewHolder)holder).allMoney.setText("￥"+allMoney);
        ((ViewHolder)holder).tvPrice.setText(listBean.getMoney()+"元/月");

        if(listBean.getRealMoney()!=null){
            ((ViewHolder)holder).tvRealMoney.setVisibility(View.VISIBLE);

            Object realMoney = listBean.getRealMoney();
            ((ViewHolder)holder).tvRealMoney.setText("￥"+realMoney);

            ((ViewHolder)holder).allMoney.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG); // 设置中划线并加清晰
        }else{
            ((ViewHolder)holder).tvRealMoney.setVisibility(View.GONE);
            ((ViewHolder)holder).allMoney.getPaint().setFlags(0);
        }
        String stationImg = listBean.getStationImg();
        if(stationImg==null){
            Glide.with(context).load(R.mipmap.headimg).into(((ViewHolder)holder).ivImg);
        }else{
            String[] split = stationImg.split(",");
            if(split.length>0 && split!=null){
                Glide.with(context).load(split[0]).into(((ViewHolder)holder).ivImg);
            }
        }
        ((ViewHolder)holder).tvLeasedTime.setText("租赁至"+listBean.getEndTime());
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_img)
        CustomRoundAngleImageView ivImg;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_price)
        TextView tvPrice;
        @BindView(R.id.all_money)
        TextView allMoney;
        @BindView(R.id.tv_real_money)
        TextView tvRealMoney;
        @BindView(R.id.tv_leased_time)
        TextView tvLeasedTime;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
