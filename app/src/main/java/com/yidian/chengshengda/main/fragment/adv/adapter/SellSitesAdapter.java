package com.yidian.chengshengda.main.fragment.adv.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.yidian.chengshengda.R;
import com.yidian.chengshengda.details.SiteDeletails;
import com.yidian.chengshengda.custom.CustomRoundAngleImageView;
import com.yidian.chengshengda.main.fragment.adv.bean.SitesBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SellSitesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context context;
    private final List<SitesBean.ObjectBean> list;


    public SellSitesAdapter(Context context, List<SitesBean.ObjectBean> list) {

        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_sellsites, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        SitesBean.ObjectBean objectBean = list.get(position);
        ((ViewHolder) holder).tvSitesDistance.setText(objectBean.getPlace());
        ((ViewHolder) holder).tvBusiness.setText(objectBean.getNickName());
        ((ViewHolder) holder).tvTime.setText(objectBean.getEndTime());
        ((ViewHolder) holder).tvPrice.setText(objectBean.getStationMoney()+"元/月");

            String stationImg = objectBean.getStationImg();

            if(stationImg==null){


            }else {
                String[] split = stationImg.split(",");

                Glide.with(context).load(split[0]).into(((ViewHolder)holder).ivImg);
            }
        ((ViewHolder)holder).rlItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stationNum = String.valueOf(objectBean.getStationNum());
                Intent intent = new Intent(context, SiteDeletails.class);
                intent.putExtra("id",stationNum);
                context.startActivity(intent);
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
        @BindView(R.id.tv_business)
        TextView tvBusiness;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.rl_item)
        RelativeLayout rlItem;
        @BindView(R.id.tv_price)
        TextView tvPrice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
