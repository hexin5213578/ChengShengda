package com.yidian.chengshengda.main.fragment.adv.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.route.DistanceItem;
import com.amap.api.services.route.DistanceResult;
import com.amap.api.services.route.DistanceSearch;
import com.bumptech.glide.Glide;
import com.yidian.chengshengda.R;
import com.yidian.chengshengda.details.SiteDeletails;
import com.yidian.chengshengda.custom.CustomRoundAngleImageView;
import com.yidian.chengshengda.main.fragment.adv.bean.NosellSiteBean;
import com.yidian.chengshengda.utils.SPUtil;
import com.yidian.chengshengda.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NoSellSitesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context context;
    private final List<NosellSiteBean.ObjectBean.ListBean> list;
    private float distance;


    public NoSellSitesAdapter(Context context, List<NosellSiteBean.ObjectBean.ListBean> list) {

        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_nosellsites, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        NosellSiteBean.ObjectBean.ListBean listBean = list.get(position);

        //获取定位的经纬度
        String lat = SPUtil.getInstance().getData(context, SPUtil.FILE_NAME, SPUtil.Lat);
        String lng = SPUtil.getInstance().getData(context, SPUtil.FILE_NAME, SPUtil.lng);

        //获取站点的经纬度
        double lat1 = listBean.getLat();
        double lng1 = listBean.getLng();

        //赋值
        ((ViewHolder)holder).tvPrice.setText(listBean.getStationMoney()+"元/月");
        ((ViewHolder)holder).tvSitesDistance.setText(listBean.getPlace());

        //计算两点间的距离
        DistanceSearch distanceSearch = new DistanceSearch(context);

        LatLonPoint start = new LatLonPoint(Double.valueOf(lat), Double.valueOf(lng));
        LatLonPoint end = new LatLonPoint(lat1, lng1);

        distanceSearch.setDistanceSearchListener(new DistanceSearch.OnDistanceSearchListener() {
            @Override
            public void onDistanceSearched(DistanceResult distanceResult, int i) {
                if(i==1000){
                    List<DistanceItem> distanceResults = distanceResult.getDistanceResults();
                    distance = distanceResults.get(0).getDistance();

                    if(distance<1000){
                        ((ViewHolder)holder).tvDistance.setText(Integer.valueOf(String.valueOf(distance))+"m");
                    }
                    else if(distance>=1000){
                        ((ViewHolder)holder).tvDistance.setText(StringUtil.round1(String.valueOf(distance/1000))+"km");
                    }

                }
            }
        });
        DistanceSearch.DistanceQuery distanceQuery = new DistanceSearch.DistanceQuery();
        List<LatLonPoint> latLonPoints = new ArrayList<LatLonPoint>();
        latLonPoints.add(start);
        //设置起点终点
        distanceQuery.setOrigins(latLonPoints);
        distanceQuery.setDestination(end);
        //设置测量方式，支持直线和驾车
        distanceQuery.setType(DistanceSearch.TYPE_DISTANCE);
        distanceSearch.calculateRouteDistanceAsyn(distanceQuery);

        String stationImg = listBean.getStationImg();

        if(stationImg==null){


        }else {
            String[] split = stationImg.split(",");

            Glide.with(context).load(split[0]).into(((ViewHolder)holder).ivImg);
        }

        ((ViewHolder) holder).rlItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stationNum = listBean.getStationNum();
                Intent intent = new Intent(context, SiteDeletails.class);
                intent.putExtra("id", stationNum);
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
        @BindView(R.id.tv_price)
        TextView tvPrice;
        @BindView(R.id.tv_distance)
        TextView tvDistance;
        @BindView(R.id.rl_item)
        RelativeLayout rlItem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
