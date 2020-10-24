package com.yidian.chengshengda.main.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.maps.model.PolygonOptions;
import com.amap.api.maps.model.Polyline;
import com.amap.api.maps.model.PolylineOptions;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.district.DistrictItem;
import com.amap.api.services.district.DistrictResult;
import com.amap.api.services.district.DistrictSearch;
import com.amap.api.services.district.DistrictSearchQuery;
import com.leaf.library.StatusBarUtil;
import com.yidian.chengshengda.R;
import com.yidian.chengshengda.base.BaseFragment;
import com.yidian.chengshengda.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentMain extends Fragment implements DistrictSearch.OnDistrictSearchListener, LocationSource, AMapLocationListener {
    @BindView(R.id.map)
    MapView map;
    private View view;
    private AMap aMap;
    private String[] split;
    MyLocationStyle myLocationStyle;
    OnLocationChangedListener mListener;
    //初始化定位
    AMapLocationClient mlocationClient;
    AMapLocationClientOption mLocationOption;

    String[] str = {"渝中区", "万州区", "涪陵区", "大渡口区", "江北区", "沙坪坝区", "九龙坡区", "南岸区", "北碚区", "綦江区", "大足区",
            "渝北区", "巴南区", "黔江区", "长寿区", "江津区", "合川区", "永川区", "南川区", "璧山区", "铜梁区", "潼南区", "荣昌区", "开州区", "梁平区", "武隆区"};
    private Marker marker;
    private double latitude;
    private double longitude;
    private Marker marker1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getContext(), R.layout.fragment_main, null);
        ButterKnife.bind(this, view);

        //设置背景透明
        StatusBarUtil.setTransparentForWindow(getActivity());
        //设置状态栏字体黑色
        StatusBarUtil.setDarkMode(getActivity());

        map.onCreate(savedInstanceState);
        if (aMap == null) {
            aMap = map.getMap();
        }

        aMap.setTrafficEnabled(false);// 显示实时交通状况
        //地图模式可选类型：MAP_TYPE_NORMAL,MAP_TYPE_SATELLITE,MAP_TYPE_NIGHT
        aMap.setMapType(AMap.MAP_TYPE_NORMAL);// 卫星地图模式


       /* for (int i = 0; i < str.length; i++) {
            getPoi(str[i]);
        }*/

       getPoi("渝中区");
        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_MAP_ROTATE_NO_CENTER);

        BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromResource(R.mipmap.my_location);
        myLocationStyle.myLocationIcon(bitmapDescriptor);
        myLocationStyle.strokeColor(Color.argb(0, 0, 0, 0));// 设置圆形的边框颜色
        myLocationStyle.radiusFillColor(Color.argb(0, 0, 0, 0));// 设置圆形的填充颜色

        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style

        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。

        // 设置定位监听
        aMap.setLocationSource(this);
        // 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
        aMap.setMyLocationEnabled(true);
        // 设置定位的类型为定位模式，有定位、跟随或地图根据面向方向旋转几种
        aMap.setMyLocationType(AMap.LOCATION_TYPE_LOCATE);



        aMap.setInfoWindowAdapter(new AMap.InfoWindowAdapter() {

            private View infoWindow;

            @Override
            public View getInfoWindow(Marker marker) {

                infoWindow = LayoutInflater.from(getContext()).inflate(
                            R.layout.custom_info_window, null);
                    TextView title = infoWindow.findViewById(R.id.title);
                    title.setText(marker.getTitle());
                    TextView snippt = infoWindow.findViewById(R.id.snippet);
                    snippt.setText(marker.getSnippet());
                return infoWindow;
            }

            @Override
            public View getInfoContents(Marker marker) {
                return null;

            }
        });
        return view;
    }

    @Override
    public void onDistrictSearched(DistrictResult districtResult) {
        ArrayList<DistrictItem> district = districtResult.getDistrict();
        if(district.size()>0){
            DistrictItem districtItem = district.get(0);
            String keywords = districtResult.getQuery().getKeywords();
            //Log.e("xxx",keywords);
            switch (keywords) {
                case "渝中区":
                case "铜梁区":
                    setColor(districtItem,156,39,176);
                    break;
                case "万州区":
                case "梁平区":
                case "璧山区":
                    setbg(districtItem, 81,45,168);
                    break;
                case "涪陵区":
                case "南川区":
                    setbg(districtItem, 48,63,159);
                    break;
                case "大渡口区":
                case "永川区":
                case "开州区":
                    setbg(districtItem, 0,80,71);
                    break;
                case "江北区":
                case "合川区":
                case "潼南区":
                    setbg(districtItem, 115,0,45);
                    break;
                case "沙坪坝区":
                case "江津区":
                    setbg(districtItem, 76,0,108);
                    break;
                case "九龙坡区":
                case "长寿区":
                    setbg(districtItem, 57,106,0);
                    break;
                case "南岸区":
                case "黔江区":
                    setbg(districtItem, 116,120,0);
                    break;
                case "北碚区":
                case "巴南区":
                case "荣昌区":
                    setbg(districtItem, 251,192,45);
                    break;
                case "綦江区":
                case "渝北区":
                    setbg(districtItem,255,0,0);
                    break;
                case "大足区":
                case "武隆区":
                    setbg(districtItem, 0,0,255);
                    break;
            }
        }

    }

    //激活定位
    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
        mListener = onLocationChangedListener;
        if (mlocationClient == null) {
            mlocationClient = new AMapLocationClient(getActivity());
            //初始化定位参数
            mLocationOption = new AMapLocationClientOption();
            //设置定位回调监听
            mlocationClient.setLocationListener(this);
            //设置为高精度定位模式
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);

            //设置定位参数
            mLocationOption.setInterval(100000);
            mlocationClient.setLocationOption(mLocationOption);
            // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
            // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
            // 在定位结束后，在合适的生命周期调用onDestroy()方法
            // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
            mlocationClient.startLocation();//启动定位
        }
    }

    @Override
    public void deactivate() {
        mListener = null;
        if (mlocationClient != null) {
            mlocationClient.stopLocation();
            mlocationClient.onDestroy();
        }
        mlocationClient = null;
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (mListener != null && aMapLocation != null) {
            if (aMapLocation != null
                    && aMapLocation.getErrorCode() == 0) {
                mListener.onLocationChanged(aMapLocation);// 显示系统小蓝点
                latitude = aMapLocation.getLatitude();
                longitude = aMapLocation.getLongitude();

                //画点
                LatLng latLng1 = new LatLng(34.09706047517685,115.29895295697752);
                MarkerOptions markerOptions = new MarkerOptions()
                        .title("站点位置XXXX,id为"+456789).snippet("(未出售)")
                        .position(latLng1)
                        .icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(),R.mipmap.sall)));
                Marker marker = aMap.addMarker(markerOptions);
                marker.showInfoWindow();

                LatLng latLng2 = new LatLng(34.07706047517685, 115.29795295697752);
                Marker marker2 = aMap.addMarker(new MarkerOptions().
                        position(latLng2).
                        icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.sell))).
                        setFlat(false).title("站点位置XXXX,id为" + 123456).snippet("(已出售)"));
                marker2.showInfoWindow();

                Log.e("xxx",latitude+"  "+longitude+"");
                //地圖縮放
                aMap.moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(new LatLng(latitude,longitude), 15, 0, 0)));
            } else {
                String errText = "定位失败," + aMapLocation.getErrorCode() + ": " + aMapLocation.getErrorInfo();
                Log.e("AmapErr", errText);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        map.onDestroy();
        if (null != mlocationClient) {
            mlocationClient.onDestroy();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        map.onResume();
    }
    @Override
    public void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        map.onPause();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        map.onSaveInstanceState(outState);
    }

    public void getPoi(String name) {
        DistrictSearch search = new DistrictSearch(getContext());
        DistrictSearchQuery query = new DistrictSearchQuery();
        query.setKeywords(name);//传入关键字
        query.setShowBoundary(true);//是否返回边界值
        search.setQuery(query);
        search.setOnDistrictSearchListener(this);//绑定监听器
        search.searchDistrictAnsy();//开始搜索
    }

    public void setColor(DistrictItem districtItem, int r,int g,int b) {
        String[] strings = districtItem.districtBoundary();

        for (int i = 0; i < strings.length; i++) {
            split = strings[i].split(";");
        }
        List<LatLng> latLngs = new ArrayList<LatLng>();

        for (int j = 0; j < split.length; j++) {
            String[] split = this.split[j].split(",");
            latLngs.add(new LatLng(Double.valueOf(split[1]), Double.valueOf(split[0])));
            //Log.e("xxx",Double.valueOf(split[0])+"   "+Double.valueOf(split[1]));
        }
        Log.e("xxx", latLngs.size() + "");
        Polyline polyline = aMap.addPolyline(new PolylineOptions().
                addAll(latLngs).width(10).color(Color.argb(100, r, g, b)));
    }

    public void setbg(DistrictItem districtItem, int r,int g,int b) {
        String[] strings = districtItem.districtBoundary();
        for (int i = 0; i < strings.length; i++) {
            split = strings[i].split(";");
        }
        Log.e("xxx",split.length+"");

        // 声明 多边形参数对象
        PolygonOptions polygonOptions = new PolygonOptions();
        // 添加 多边形的每个顶点（顺序添加）
        for (int j = 0; j < 6; j++) {
            String[] split = this.split[j].split(",");
            LatLng latLng = new LatLng(Double.valueOf(split[1]), Double.valueOf(split[0]));
            polygonOptions.add(latLng);
            //Log.e("xxx",Double.valueOf(split[0])+"   "+Double.valueOf(split[1]));
        }
        polygonOptions // 多边形的边框
        .fillColor(Color.argb(40,r,g,b)).
                strokeColor(Color.argb(0,11,164,233));   // 多边形的填充色
    }

}
