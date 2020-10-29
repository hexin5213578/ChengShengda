package com.yidian.chengshengda.details;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.leaf.library.StatusBarUtil;
import com.yidian.chengshengda.R;
import com.yidian.chengshengda.base.BaseAvtivity;
import com.yidian.chengshengda.base.BasePresenter;
import com.yidian.chengshengda.details.adapter.HisLeaseAdapter;
import com.yidian.chengshengda.details.bean.StationDetailsBean;
import com.yidian.chengshengda.utils.NetUtils;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SiteDeletails extends BaseAvtivity implements View.OnClickListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.xbn)
    Banner xbn;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_issall)
    TextView tvIssall;
    @BindView(R.id.tv_lease_count)
    TextView tvLeaseCount;
    @BindView(R.id.rc_history)
    RecyclerView rcHistory;
    @BindView(R.id.rl_noHistory)
    RelativeLayout rlNoHistory;
    @BindView(R.id.tv_add_shopcar)
    TextView tvAddShopcar;
    @BindView(R.id.tv_commit)
    TextView tvCommit;
    private List<String> imgList;
    private String[] split;
    private ImageLoader loader;

    @Override
    protected int getResId() {
        return R.layout.activity_sitedeletails;
    }

    @Override
    protected void getData() {
        StatusBarUtil.setGradientColor(this, toolbar);
        StatusBarUtil.setDarkMode(this);

        back.setOnClickListener(this);
        tvAddShopcar.setOnClickListener(this);
        tvCommit.setOnClickListener(this);
        imgList = new ArrayList<>();
        loader = new MyLoader();
        //拿到传递过来的站点编号
        Intent intent =
                getIntent();
        String id = intent.getStringExtra("id");
        //获取对应id下的站点详情
        NetUtils.getInstance().getApis()
                .getStationDetails("http://192.168.10.111:8081/station/selectStation",id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<StationDetailsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @SuppressLint("ResourceAsColor")
                    @Override
                    public void onNext(StationDetailsBean stationDetailsBean) {
                        String type = stationDetailsBean.getType();
                        List<StationDetailsBean.ObjectBean.StationsBean> stations = stationDetailsBean.getObject().getStations();

                        if(type.equals("OK")){
                            if(stations.size()>0 && stations!=null){
                                StationDetailsBean.ObjectBean.StationsBean stationsBean = stations.get(0);

                                String place = stationsBean.getPlace();
                                int money = stationsBean.getStationMoney();
                                int status = stationsBean.getStatus();
                                String stationImg = stationsBean.getStationImg();


                                tvPrice.setText(money+"/月");
                                tvAddress.setText(place);
                                if(stationImg==null){
                                    //如果图片为空
                                    List<Integer> imgList1 = new ArrayList<>();
                                    imgList1.add(R.mipmap.welcome);
                                    //xbn设置
                                    xbn.setIndicatorGravity(BannerConfig.CENTER);
                                    xbn.setImageLoader(loader);
                                    xbn.isAutoPlay(false);
                                    //设置图片加载地址
                                    xbn.setImages(imgList1)
                                            //开始调用的方法，启动轮播图。
                                            .start();
                                }else{

                                    split = stationImg.split(",");
                                    if(split.length>0 && split!=null){
                                        Log.e("xxx","图片数组长度为"+split.length);
                                        for (int i = 0; i< split.length; i++){
                                            imgList.add(split[i]);
                                        }
                                        if(imgList.size()>0 && imgList!=null){
                                            //轮播图设置数据
                                            //设置指示器的位置，小点点，居中显示
                                            xbn.setIndicatorGravity(BannerConfig.CENTER);
                                            xbn.setImageLoader(loader);
                                            xbn.isAutoPlay(false);
                                            //设置图片加载地址
                                            xbn.setImages(imgList)
                                                    //开始调用的方法，启动轮播图。
                                                    .start();
                                        }
                                    }
                                }

                                //判断当前状态
                                if(status==1){
                                    tvIssall.setTextColor(getResources().getColor(R.color.color_0BA4E9));
                                    tvIssall.setText("未租出");
                                    tvIssall.setBackground(getResources().getDrawable(R.drawable.details_bg));
                                }else if(status==2){
                                    tvIssall.setTextColor(getResources().getColor(R.color.red_an));
                                    tvIssall.setText("已租出");
                                    tvIssall.setBackground(getResources().getDrawable(R.drawable.details_bg_sell));
                                }
                            }
                            List<StationDetailsBean.ObjectBean.StationOldInfoBean> stationOldInfo = stationDetailsBean.getObject().getStationOldInfo();
                            //获取历史租赁次数
                            tvLeaseCount.setText(stationOldInfo.size()+"次已租");

                            //判断租赁次数
                            if(stationOldInfo.size()>0 && stationOldInfo!=null){
                                rlNoHistory.setVisibility(View.GONE);
                                rcHistory.setVisibility(View.VISIBLE);
                                //创建历史租赁的适配器
                                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SiteDeletails.this, RecyclerView.VERTICAL, false);
                                rcHistory.setLayoutManager(linearLayoutManager);
                                HisLeaseAdapter hisLeaseAdapter = new HisLeaseAdapter(SiteDeletails.this, stationOldInfo);
                                rcHistory.setAdapter(hisLeaseAdapter);
                            }else{
                                rlNoHistory.setVisibility(View.VISIBLE);
                                rcHistory.setVisibility(View.GONE);
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

    }
    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    //绑定单击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
                //返回
            case R.id.back:
                finish();
                break;
                //加入购物车
            case R.id.tv_add_shopcar:
                // TODO: 2020/10/26 0026 选择规格
                break;
                //提交
            case R.id.tv_commit:

                break;
        }
    }
    /**
     * 网络加载图片
     * 使用了Glide图片加载框架
     */
    private class MyLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context.getApplicationContext())
                    .load((String) path)
                    .into(imageView);
        }
    }
}
