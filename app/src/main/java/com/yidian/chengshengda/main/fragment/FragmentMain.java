package com.yidian.chengshengda.main.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.maps.model.NaviPara;
import com.amap.api.maps.model.Polygon;
import com.amap.api.maps.model.PolygonOptions;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.district.DistrictItem;
import com.amap.api.services.district.DistrictResult;
import com.amap.api.services.district.DistrictSearch;
import com.amap.api.services.district.DistrictSearchQuery;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.amap.api.services.weather.LocalWeatherForecastResult;
import com.amap.api.services.weather.LocalWeatherLive;
import com.amap.api.services.weather.LocalWeatherLiveResult;
import com.amap.api.services.weather.WeatherSearch;
import com.amap.api.services.weather.WeatherSearchQuery;
import com.leaf.library.StatusBarUtil;
import com.yidian.chengshengda.R;
import com.yidian.chengshengda.details.SiteDeletails;
import com.yidian.chengshengda.main.activity.SeleteAreaActivity;
import com.yidian.chengshengda.main.adapter.PoiAdapter;
import com.yidian.chengshengda.main.bean.AllStationBean;
import com.yidian.chengshengda.main.bean.SaveAreaBean;
import com.yidian.chengshengda.main.bean.SendLocationBean;
import com.yidian.chengshengda.regist.activity.RegistActivity;
import com.yidian.chengshengda.utils.KeyBoardUtils;
import com.yidian.chengshengda.utils.NetUtils;
import com.yidian.chengshengda.utils.SPUtil;
import com.yidian.chengshengda.utils.StringUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


//首页
public class FragmentMain extends Fragment implements DistrictSearch.OnDistrictSearchListener, LocationSource, AMapLocationListener, WeatherSearch.OnWeatherSearchListener, View.OnClickListener, PoiSearch.OnPoiSearchListener {
    @BindView(R.id.map)
    MapView map;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_city)
    TextView tvCity;
    @BindView(R.id.tv_weather)
    TextView tvWeather;
    @BindView(R.id.ll_location)
    LinearLayout llLocation;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.ll_search)
    LinearLayout llSearch;
    @BindView(R.id.rl_top)
    RelativeLayout rlTop;
    @BindView(R.id.tv_cancle)
    TextView tvCancle;
    @BindView(R.id.rc_search)
    RecyclerView rcSearch;
    @BindView(R.id.rc_nest)
    NestedScrollView rcNest;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.bt_arrive)
    Button btArrive;
    @BindView(R.id.rl_dialog)
    RelativeLayout rlDialog;
    @BindView(R.id.bt_cancle)
    Button btcancle;
    private View view;
    private AMap aMap;
    private String[] split;
    MyLocationStyle myLocationStyle;
    OnLocationChangedListener mListener;
    //初始化定位
    AMapLocationClient mlocationClient;
    AMapLocationClientOption mLocationOption;
    private double latitude;
    private double longitude;
    //天气查询
    private WeatherSearchQuery mquery;
    private WeatherSearch mweathersearch;
    private String keyWord;
    private PoiSearch poiSearch;
    private String city;
    private PoiAdapter poiAdapter;
    private String district;
    private PolygonOptions polygonOptions;
    private Polygon polygon;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getContext(), R.layout.fragment_main, null);
        ButterKnife.bind(this, view);

        StatusBarUtil.setGradientColor(getActivity(), toolbar);
        //设置状态栏字体黑色
        StatusBarUtil.setDarkMode(getActivity());

        //权限
        Request();
        map.onCreate(savedInstanceState);
        if (aMap == null) {
            aMap = map.getMap();
        }

        UiSettings uiSettings =  aMap.getUiSettings();
        uiSettings.setLogoBottomMargin(-100);


        tvCancle.setOnClickListener(this);
        llLocation.setOnClickListener(this);
        llSearch.setOnClickListener(this);
        etSearch.setOnClickListener(this);
        //输入框文本监听
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                keyWord = etSearch.getText().toString();
                if ("".equals(keyWord)) {
                    Toast.makeText(getContext(), "请输入关键字", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    doSearchQuery();
                    if (poiAdapter != null) {
                        poiAdapter.notifyDataSetChanged();
                    }
                }
            }
        });

        //输入框焦点监听
        etSearch.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    KeyBoardUtils.openKeyBoard(etSearch);
                    tvCancle.setVisibility(View.VISIBLE);
                    rcNest.setVisibility(View.VISIBLE);
                } else {
                    KeyBoardUtils.closeKeyboard(etSearch);
                    tvCancle.setVisibility(View.GONE);
                    rcNest.setVisibility(View.GONE);
                }
            }
        });


        aMap.setTrafficEnabled(false);// 显示实时交通状况
        //地图模式可选类型：MAP_TYPE_NORMAL,MAP_TYPE_SATELLITE,MAP_TYPE_NIGHT
        aMap.setMapType(AMap.MAP_TYPE_NORMAL);// 卫星地图模式


        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_MAP_ROTATE_NO_CENTER);

        //定位点
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

                String[] split = marker.getTitle().split(",");
                title.setText(split[0]);

                TextView snippt = infoWindow.findViewById(R.id.snippet);
                snippt.setText(marker.getSnippet());
                return infoWindow;
            }

            @Override
            public View getInfoContents(Marker marker) {
                return null;
            }
        });

        //获取所有站点信息
        NetUtils.getInstance().getApis().getAllStation("http://192.168.10.111:8081/station/selStation")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AllStationBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(AllStationBean allStationBean) {
                        if(allStationBean.getType().equals("OK")){
                            List<AllStationBean.ObjectBean> object = allStationBean.getObject();
                            if(object.size()>0 && object!=null){
                                for (int i =0;i<object.size();i++){
                                    AllStationBean.ObjectBean objectBean = object.get(i);
                                    DrawPoint(objectBean.getLat(),objectBean.getLng(),objectBean.getStatus(),Integer.valueOf(objectBean.getStationNum()),objectBean.getStationInfo());
                                }
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        //绑定信息窗点击事件
        aMap.setOnInfoWindowClickListener(listener);
        return view;
    }

    //画点
    public void DrawPoint(double latitude, double longitude, int type, int id,String title) {

        LatLng latLng = new LatLng(latitude, longitude);

        if (type == 1) {
            MarkerOptions markerOptions = new MarkerOptions()
                    .title(title+ id).snippet("(未出售)")
                    .position(latLng)
                    .icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.sall)));
            Marker marker = aMap.addMarker(markerOptions);
        } else if(type==2){
            Marker marker2 = aMap.addMarker(new MarkerOptions().
                    position(latLng).
                    icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.sell))).
                    setFlat(false).title(title + id).snippet("(已出售)"));
        }
    }
    //infowindow单击事件
    AMap.OnInfoWindowClickListener listener = new AMap.OnInfoWindowClickListener() {

        @Override
        public void onInfoWindowClick(Marker arg0) {
            String numbers = StringUtil.getNumbers(arg0.getTitle());


            //将点击的站点ID传递到 站点详情
            Intent intent = new Intent(getContext(), SiteDeletails.class);
            intent.putExtra("id",numbers);
            startActivity(intent);
        }
    };
    @Override
    public void onDistrictSearched(DistrictResult districtResult) {
        ArrayList<DistrictItem> district = districtResult.getDistrict();
        if (district.size() > 0) {

            DistrictItem districtItem = district.get(0);

            //设置背景填充
            setbg(districtItem, 156, 39, 176);
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
            mLocationOption.setInterval(500000);

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

    /**
     * 开始进行poi搜索
     */
    protected void doSearchQuery() {
        PoiSearch.Query query = new PoiSearch.Query(keyWord, "", city);// 第一个参数表示搜索字符串，第二个参数表示poi搜索类型，第三个参数表示poi搜索区域（空字符串代表全国）
        query.setPageSize(20);// 设置每页最多返回多少条poiitem
        query.setPageNum(0);// 设置查第一页

        poiSearch = new PoiSearch(getActivity(), query);
        poiSearch.setOnPoiSearchListener(this);
        poiSearch.searchPOIAsyn();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getLocation(SendLocationBean bean) {
        //展示弹出框  关闭搜索框及列表
        rlDialog.setVisibility(View.VISIBLE);
        tvCancle.setVisibility(View.GONE);
        rcNest.setVisibility(View.GONE);

        //关闭 软键盘
        KeyBoardUtils.closeKeyboard(etSearch);

        LatLng latLng = bean.getLatLng();
        String title = bean.getTitle();
        String address = bean.getAddress();

        tvName.setText(title);
        tvAddress.setText(address);

        //缩放到选中点
        aMap.moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(latLng, 18, 0, 0)));

        //画点
        MarkerOptions markerOptions = new MarkerOptions()
                .position(latLng)
                .icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.biaoji)));
        Marker marker = aMap.addMarker(markerOptions);
        marker.setClickable(false);
        btcancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //关闭
                rlDialog.setVisibility(View.GONE);
                marker.destroy();
                //缩放到我的位置
                aMap.moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(new LatLng(latitude,longitude), 16, 0, 0)));
            }
        });
        //导航
        btArrive.setOnClickListener(new View.OnClickListener() {
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
                    AMapUtils.openAMapNavi(naviPara, getContext());
                } catch (com.amap.api.maps.AMapException e) {

                    // 如果没安装会进入异常，调起下载页面
                    AMapUtils.getLatestAMapApp(getContext());
                }
            }
        });
    }

    /**
     * 判断高德地图app是否已经安装
     */
    public boolean getAppIn() {
        PackageInfo packageInfo = null;
        try {
            packageInfo = getContext().getPackageManager().getPackageInfo(
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
    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (mListener != null && aMapLocation != null) {
            if (aMapLocation != null
                    && aMapLocation.getErrorCode() == 0) {
                mListener.onLocationChanged(aMapLocation);// 显示系统小蓝点
                latitude = aMapLocation.getLatitude();
                longitude = aMapLocation.getLongitude();

                city = aMapLocation.getCity();

                district = aMapLocation.getDistrict();
                tvCity.setText(district);

                //检索参数为城市和天气类型，实况天气为WEATHER_TYPE_LIVE、天气预报为WEATHER_TYPE_FORECAST
                mquery = new WeatherSearchQuery(district, WeatherSearchQuery.WEATHER_TYPE_LIVE);
                mweathersearch = new WeatherSearch(getActivity());
                mweathersearch.setOnWeatherSearchListener(this);
                mweathersearch.setQuery(mquery);
                mweathersearch.searchWeatherAsyn(); //异步搜索


                Log.e("xxx", "当前经纬度为" + latitude + "  " + longitude + "");
                SPUtil.getInstance().saveData(getContext(),SPUtil.FILE_NAME,SPUtil.Lat,String.valueOf(latitude));
                SPUtil.getInstance().saveData(getContext(),SPUtil.FILE_NAME,SPUtil.lng,String.valueOf(longitude));

                //地圖縮放
                aMap.moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(new LatLng(latitude, longitude), 15, 0, 0)));

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
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        map.onResume();
        // 声明 多边形参数对象
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        Log.e("xxx","onResume()");
    }

    @Override
    public void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        map.onPause();
        mlocationClient.stopLocation();
        Log.e("xxx","onPause()");

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        map.onSaveInstanceState(outState);
        Log.e("xxx","onSaveInstanceState");

    }

    //获取边界坐标
    public void getPoi(String name) {
        DistrictSearch search = new DistrictSearch(getContext());
        DistrictSearchQuery query = new DistrictSearchQuery();
        query.setKeywords(name);//传入关键字
        query.setShowBoundary(true);//是否返回边界值
        search.setQuery(query);
        search.setOnDistrictSearchListener(this);//绑定监听器
        search.searchDistrictAnsy();//开始搜索
    }



    //设置填充色
    public void setbg(DistrictItem districtItem, int r, int g, int b) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String[] strings = districtItem.districtBoundary();
                for (int i = 0; i < strings.length; i++) {
                    split = strings[i].split(";");
                }
                Log.e("xxx", "当前区共有" + split.length + "个坐标");

                polygonOptions = new PolygonOptions();

                // 添加 多边形的每个顶点（顺序添加）
                for (int j = 0; j < split.length; j++) {
                    String[] split1 = split[j].split(",");
                    LatLng latLng = new LatLng(Double.valueOf(split1[1]), Double.valueOf(split1[0]));
                    polygonOptions.add(latLng);
                    //Log.e("xxx",Double.valueOf(split[0])+"   "+Double.valueOf(split[1]));
                }
                //查询结束后跳转到指定区域
                String s = split[split.length/2];
                String[] location = s.split(",");
                //地圖縮放
                aMap.moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(new LatLng(Double.valueOf(location[1]), Double.valueOf(location[0])), 9, 0, 0)));
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        polygonOptions // 多边形的边框
                                .fillColor(Color.argb(60, r, g, b)).
                                strokeColor(Color.argb(0, 11, 164, 233));   // 多边形的填充色
                        polygon = aMap.addPolygon(polygonOptions);
                    }
                });
            }
        }).start();
        polygon.remove();
    }
    //天气回调
    @Override
    public void onWeatherLiveSearched(LocalWeatherLiveResult localWeatherLiveResult, int i) {
        LocalWeatherLive liveResult = localWeatherLiveResult.getLiveResult();
        if (i == 1000) {
            if (localWeatherLiveResult != null && localWeatherLiveResult.getLiveResult() != null) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //天气赋值
                        tvWeather.setText(liveResult.getWeather() + liveResult.getTemperature() + "°");
                    }
                });
            }
        }
    }

    @Override
    public void onWeatherForecastSearched(LocalWeatherForecastResult localWeatherForecastResult, int i) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_search:
            case R.id.et_search:
                //获取焦点  弹起键盘
                etSearch.setFocusable(true);
                etSearch.setFocusableInTouchMode(true);
                etSearch.requestFocus();
                etSearch.findFocus();
                KeyBoardUtils.openKeyBoard(etSearch);
                //显示取消 及列表 隐藏地图
                tvCancle.setVisibility(View.VISIBLE);
                rcNest.setVisibility(View.VISIBLE);
                break;
            case R.id.ll_location:
                //跳转到选择定位点
                Intent intent = new Intent(getContext(), SeleteAreaActivity.class);
                intent.putExtra("area",district);
                startActivity(intent);

                break;
            case R.id.tv_cancle:
                //取消 失去焦点 隐藏列表
                etSearch.setFocusable(false);
                etSearch.setFocusableInTouchMode(false);
                etSearch.requestFocus();
                etSearch.setText("");
                KeyBoardUtils.closeKeyboard(etSearch);
                tvCancle.setVisibility(View.GONE);
                rcNest.setVisibility(View.GONE);
                break;
        }
    }
    //选择地区回调
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getArea(SaveAreaBean saveAreaBean){
        String area = saveAreaBean.getArea();
        if(!TextUtils.isEmpty(area)){

            getPoi(area);
            Toast.makeText(getActivity(), ""+area, Toast.LENGTH_SHORT).show();
        }
    }

    //查询结果
    @Override
    public void onPoiSearched(PoiResult poiResult, int i) {
        Log.e("xxx", i + "");
        if (i == 1000) {
            //解析result获取POI信息
            ArrayList<PoiItem> pois = poiResult.getPois();
            if (pois.size() > 0 && pois != null) {
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
                rcSearch.setLayoutManager(linearLayoutManager);
                poiAdapter = new PoiAdapter(getContext(), pois);
                rcSearch.setAdapter(poiAdapter);
            } else {
                //如果查询失败 关键词不匹配 设置推荐关键字
                Toast.makeText(getContext(), "关键字有误", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {

    }
    //安卓10.0定位权限
    public void Request() {
        if (Build.VERSION.SDK_INT >= 23) {
            int request = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION);
            if (request != PackageManager.PERMISSION_GRANTED)//缺少权限，进行权限申请
            {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 100);
                return;//
            } else {

            }
        } else {

        }
    }
}
