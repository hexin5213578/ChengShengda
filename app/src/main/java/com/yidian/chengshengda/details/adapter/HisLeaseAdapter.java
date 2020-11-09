package com.yidian.chengshengda.details.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.yidian.chengshengda.R;
import com.yidian.chengshengda.details.bean.StationDetailsBean;
import com.yidian.chengshengda.custom.CustomRoundAngleImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HisLeaseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final Context context;
    private final List<StationDetailsBean.ObjectBean.StationOldInfoBean> list;

    public HisLeaseAdapter(Context context, List<StationDetailsBean.ObjectBean.StationOldInfoBean> list) {

        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_his_lease, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        StationDetailsBean.ObjectBean.StationOldInfoBean stationOldInfoBean = list.get(position);
        ((ViewHolder)holder).tvBusiness.setText(stationOldInfoBean.getNickName());
        ((ViewHolder)holder).tvPrice.setText(stationOldInfoBean.getStationMoney()+"元/月");
        ((ViewHolder)holder).tvSitesDistance.setText(stationOldInfoBean.getPlace());
        ((ViewHolder)holder).tvTime.setText(stationOldInfoBean.getEndTime());

        String stationImg = stationOldInfoBean.getStationImg();

        if(stationImg==null){


        }else {
            String[] split = stationImg.split(",");
            Glide.with(context).load("http://81.71.121.177:8081/station/getImage?head="+split[0]).into(((ViewHolder)holder).ivImg);
        }
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
        @BindView(R.id.tv_business)
        TextView tvBusiness;
        @BindView(R.id.tv_price)
        TextView tvPrice;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.rl_item)
        RelativeLayout rlItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
