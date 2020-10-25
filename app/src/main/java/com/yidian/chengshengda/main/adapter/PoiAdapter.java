package com.yidian.chengshengda.main.adapter;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.NaviPara;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.yidian.chengshengda.R;
import com.yidian.chengshengda.main.bean.SendLocationBean;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PoiAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final Context context;
    private final ArrayList<PoiItem> list;
    private LatLng latLng;


    public PoiAdapter(Context context, ArrayList<PoiItem> list) {

        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_poi_search, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        PoiItem poiItem = list.get(position);

        //名字
        String title = poiItem.getTitle();
        //地址
        String snippet = poiItem.getSnippet();
        //经纬度
        LatLonPoint latLonPoint = poiItem.getLatLonPoint();
        double latitude = latLonPoint.getLatitude();
        double longitude = latLonPoint.getLongitude();
        latLng = new LatLng(latitude,longitude);

        ((ViewHolder)holder).tvName.setText(title+"");
        ((ViewHolder)holder).tvAddress.setText(snippet+"");


        //路线点击事件
        ((ViewHolder)holder).llArriveTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 构造导航参数
                NaviPara naviPara = new NaviPara();
                // 设置终点位置
                naviPara.setTargetPoint(latLng);
                // 设置导航策略，这里是避免拥堵
                naviPara.setNaviStyle(NaviPara.DRIVING_AVOID_CONGESTION);

                // 调起高德地图导航
                try {
                    AMapUtils.openAMapNavi(naviPara, context);
                } catch (com.amap.api.maps.AMapException e) {

                    // 如果没安装会进入异常，调起下载页面
                    AMapUtils.getLatestAMapApp(context);

                }
            }
        });
        //条目点击事件
        ((ViewHolder)holder).rlItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //将坐标信息传递到类
                SendLocationBean sendLocationBean = new SendLocationBean();
                sendLocationBean.setTitle(title);
                sendLocationBean.setAddress(snippet);
                sendLocationBean.setLatLng(latLng);

                EventBus.getDefault().post(sendLocationBean);

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_address)
        TextView tvAddress;
        @BindView(R.id.ll_arrive_to)
        LinearLayout llArriveTo;
        @BindView(R.id.rl_item)
        RelativeLayout rlItem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    /**
     * 判断高德地图app是否已经安装
     */
    public boolean getAppIn() {
        PackageInfo packageInfo = null;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(
                    "com.autonavi.minimap", 0);
        } catch (PackageManager.NameNotFoundException e) {
            packageInfo = null;
            e.printStackTrace();
        }
        // 本手机没有安装高德地图app
        if (packageInfo != null) {
            return true;
        }
        // 本手机成功安装有高德地图app
        else {
            return false;
        }
    }
}
