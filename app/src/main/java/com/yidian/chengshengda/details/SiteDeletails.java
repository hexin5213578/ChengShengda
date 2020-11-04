package com.yidian.chengshengda.details;

import android.Manifest;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.leaf.library.StatusBarUtil;
import com.yidian.chengshengda.R;
import com.yidian.chengshengda.base.BaseAvtivity;
import com.yidian.chengshengda.base.BasePresenter;
import com.yidian.chengshengda.base.Common;
import com.yidian.chengshengda.custom.CustomRoundAngleImageView;
import com.yidian.chengshengda.details.adapter.HisLeaseAdapter;
import com.yidian.chengshengda.details.bean.StationDetailsBean;
import com.yidian.chengshengda.main.MainActivity;
import com.yidian.chengshengda.main.bean.SaveShopCarBean;
import com.yidian.chengshengda.setpwd.bean.SetPwdBean;
import com.yidian.chengshengda.utils.NetUtils;
import com.yidian.chengshengda.welcome.WelcomeActivity;
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

import static com.yidian.chengshengda.base.App.getContext;

public class SiteDeletails extends BaseAvtivity implements View.OnClickListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.back)
    LinearLayout back;
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
    private int status;
    int flag;
    private PopupWindow mPopupWindow;
    private String place;
    private int money;
    private String stationImg;
    private SeekBar pro_month;
    private SeekBar pro_year;
    int month = 0;
    int year = 0;
    private PopupWindow mPopupWindow1;
    private String id;
    private String phone;
    private String userId;

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
        id = intent.getStringExtra("id");

        userId = Common.getUserId();

        //获取对应id下的站点详情
        showDialog();
        // TODO: 2020/10/31 0031 根据ID查询站点详情
        NetUtils.getInstance().getApis()
                .getStationDetails(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<StationDetailsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @SuppressLint("ResourceAsColor")
                    @Override
                    public void onNext(StationDetailsBean stationDetailsBean) {
                        hideDialog();
                        String type = stationDetailsBean.getType();
                        List<StationDetailsBean.ObjectBean.StationsBean> stations = stationDetailsBean.getObject().getStations();

                        if (type.equals("OK")) {
                            if (stations.size() > 0 && stations != null) {
                                StationDetailsBean.ObjectBean.StationsBean stationsBean = stations.get(0);

                                place = stationsBean.getPlace();
                                money = stationsBean.getStationMoney();
                                status = stationsBean.getStatus();
                                stationImg = stationsBean.getStationImg();

                                tvPrice.setText(money + "/月");
                                tvAddress.setText(place);
                                if (stationImg == null) {
                                    //如果图片为空
                                    List<Integer> imgList1 = new ArrayList<>();
                                    imgList1.add(R.mipmap.welcome);
                                    imgList1.add(R.mipmap.welcome);
                                    imgList1.add(R.mipmap.location);
                                    //xbn设置
                                    xbn.setIndicatorGravity(BannerConfig.CENTER);
                                    xbn.setImageLoader(loader);
                                    xbn.isAutoPlay(false);
                                    //设置图片加载地址
                                    xbn.setImages(imgList1)
                                            //开始调用的方法，启动轮播图。
                                            .start();
                                } else {

                                    split = stationImg.split(",");
                                    if (split.length > 0 && split != null) {
                                        Log.e("xxx", "图片数组长度为" + split.length);
                                        for (int i = 0; i < split.length; i++) {
                                            imgList.add(split[i]);
                                        }
                                        if (imgList.size() > 0 && imgList != null) {
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
                                if (status == 1) {
                                    tvIssall.setTextColor(getResources().getColor(R.color.color_0BA4E9));
                                    tvIssall.setText("未租出");
                                    tvIssall.setBackground(getResources().getDrawable(R.drawable.details_bg));
                                } else if (status == 2) {
                                    tvIssall.setTextColor(getResources().getColor(R.color.red_an));
                                    tvIssall.setText("已租出");
                                    tvIssall.setBackground(getResources().getDrawable(R.drawable.details_bg_sell));
                                }
                            }
                            List<StationDetailsBean.ObjectBean.StationOldInfoBean> stationOldInfo = stationDetailsBean.getObject().getStationOldInfo();
                            //获取历史租赁次数
                            tvLeaseCount.setText(stationOldInfo.size() + "次已租");

                            //判断租赁次数
                            if (stationOldInfo.size() > 0 && stationOldInfo != null) {
                                rlNoHistory.setVisibility(View.GONE);
                                rcHistory.setVisibility(View.VISIBLE);
                                //创建历史租赁的适配器
                                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SiteDeletails.this, RecyclerView.VERTICAL, false);
                                rcHistory.setLayoutManager(linearLayoutManager);
                                HisLeaseAdapter hisLeaseAdapter = new HisLeaseAdapter(SiteDeletails.this, stationOldInfo);
                                rcHistory.setAdapter(hisLeaseAdapter);
                            } else {
                                rlNoHistory.setVisibility(View.VISIBLE);
                                rcHistory.setVisibility(View.GONE);
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        hideDialog();
                        Toast.makeText(SiteDeletails.this, "请求失败", Toast.LENGTH_SHORT).show();
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
                //判断是否已出租
                if (status == 1) {
                    //给标记赋值
                    flag = 1;
                    showSelect();
                } else {
                    Toast.makeText(this, "站点已出租", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.tv_commit:
                if (status == 1) {
                    //给标记赋值
                    flag = 2;
                    showSelect();
                } else {
                    Toast.makeText(this, "站点已出租", Toast.LENGTH_SHORT).show();
                }
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
                    .load(path)
                    .into(imageView);
        }
    }

    //重新进入时隐藏弹出框
    @Override
    protected void onRestart() {
        super.onRestart();
        dismiss();
    }

    //弹出选择规格
    public void showSelect() {
        //创建popwiondow弹出框
        mPopupWindow = new PopupWindow();
        mPopupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        View view = LayoutInflater.from(this).inflate(R.layout.dailog_select_spec, null);

        CustomRoundAngleImageView img = view.findViewById(R.id.iv_img);
        TextView tv_distance = view.findViewById(R.id.tv_sites_distance);
        TextView tv_price = view.findViewById(R.id.tv_price);

        TextView tv_yearcount = view.findViewById(R.id.tv_year_count);
        TextView tv_monthcount = view.findViewById(R.id.tv_month_count);
        TextView tv_year_title = view.findViewById(R.id.tv_year_title);
        LinearLayout iv_cancle = view.findViewById(R.id.iv_cancle);
        pro_month = view.findViewById(R.id.pro_month);
        pro_year = view.findViewById(R.id.pro_year);
        TextView tv_month = view.findViewById(R.id.tv_month);
        TextView tv_year = view.findViewById(R.id.tv_year);
        TextView tv_money = view.findViewById(R.id.tv_money);
        Button bt_confirm = view.findViewById(R.id.bt_confirm);

        if (stationImg == null) {
            //如果图片为空
            Glide.with(this).load(R.mipmap.headimg).into(img);
        } else {

            split = stationImg.split(",");
            if (split.length > 0 && split != null) {
                Glide.with(this).load(split[0]).into(img);
            }
        }
        tv_distance.setText(place);
        tv_price.setText("￥" + money + "");
        iv_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        tv_money.setText(money + "元");

        pro_month.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tv_month.setText((progress + 1) + "月");
                //计算总价
                tv_monthcount.setText(progress + 1 + "");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                month = seekBar.getProgress();

                year = pro_year.getProgress();
                //设置总价
                tv_money.setText((((month + 1) * money) + (year * 12 * money)) + "元");
            }
        });
        pro_year.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tv_year.setText((progress) + "年");
                if (progress == 0) {
                    tv_year_title.setVisibility(View.GONE);
                    tv_yearcount.setVisibility(View.GONE);
                } else {
                    tv_year_title.setVisibility(View.VISIBLE);
                    tv_yearcount.setVisibility(View.VISIBLE);
                    tv_yearcount.setText(progress + "");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                year = seekBar.getProgress();
                month = pro_month.getProgress();
                //设置总价
                tv_money.setText((((month + 1) * money) + (year * 12 * money)) + "元");
            }
        });

        bt_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //关闭选择规格弹出框
                dismiss();
                int count = (pro_year.getProgress() * 12) + (pro_month.getProgress() + 1);

                //加入购物车
                if (flag == 1) {
                    Log.e("xxx", "加入购物车" + count + "");
                    //调用加入购物车的接口
                    showDialog();
                    // TODO: 2020/10/31 0031 加入购物车
                    NetUtils.getInstance().getApis().joinShopcar(Integer.parseInt(userId), Integer.valueOf(id), count)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Observer<SaveShopCarBean>() {
                                @Override
                                public void onSubscribe(Disposable d) {

                                }

                                @Override
                                public void onNext(SaveShopCarBean saveShopCarBean) {
                                    hideDialog();
                                    Toast.makeText(SiteDeletails.this, "" + saveShopCarBean.getMsg(), Toast.LENGTH_SHORT).show();

                                    String type = saveShopCarBean.getType();
                                    if (type.equals("OK")) {

                                    }
                                }

                                @Override
                                public void onError(Throwable e) {
                                    hideDialog();
                                    Toast.makeText(SiteDeletails.this, "加入购物车失败", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onComplete() {

                                }
                            });
                } else {
                    //提交
                    showDialog();
                    NetUtils.getInstance().getApis()
                            .doAddOrder(Integer.parseInt(userId),id,String.valueOf(count))
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Observer<SetPwdBean>() {
                                @Override
                                public void onSubscribe(Disposable d) {

                                }

                                @Override
                                public void onNext(SetPwdBean setPwdBean) {
                                    hideDialog();
                                    if(setPwdBean.getType().equals("OK")){
                                        Toast.makeText(SiteDeletails.this, "订单提交成功", Toast.LENGTH_SHORT).show();

                                        //弹出联系方式并创建订单
                                        //添加成功后处理
                                        mPopupWindow1 = new PopupWindow();
                                        mPopupWindow1.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                                        mPopupWindow1.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
                                        View view2 = LayoutInflater.from(SiteDeletails.this).inflate(R.layout.dialog_phone, null);

                                        TextView tv_name = view2.findViewById(R.id.tv_name);
                                        TextView tv_number = view2.findViewById(R.id.tv_number);
                                        TextView tv_call = view2.findViewById(R.id.tv_call);

                                        tv_name.setText("冯坤");
                                        tv_number.setText("15652578310");
                                        tv_call.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                phone = tv_number.getText().toString();
                                                callPhone(phone);
                                            }
                                        });
                                        //popwindow设置属性
                                        mPopupWindow1.setContentView(view2);
                                        mPopupWindow1.setBackgroundDrawable(new BitmapDrawable());
                                        mPopupWindow1.setFocusable(true);
                                        mPopupWindow1.setOutsideTouchable(true);
                                        mPopupWindow1.setOnDismissListener(new PopupWindow.OnDismissListener() {
                                            @Override
                                            public void onDismiss() {
                                                setWindowAlpa(false);
                                            }
                                        });

                                        ((ViewGroup) view).removeAllViews();

                                        show1(view2);
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
        });

        //popwindow设置属性
        mPopupWindow.setAnimationStyle(R.style.popwindow_anim_style);
        mPopupWindow.setContentView(view);
        mPopupWindow.setFocusable(true);
        Bitmap bmp = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.dialog_select_time_bg);
        Drawable drawable = new BitmapDrawable(getContext().getResources(), bmp);
        // 不带参的方法已经deprecated
        mPopupWindow.setBackgroundDrawable(drawable);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setWindowAlpa(false);
            }
        });
        show(view);
    }

    //设置透明度
    public void setWindowAlpa(boolean isopen) {
        if (Build.VERSION.SDK_INT < 11) {
            return;
        }
        final Window window = this.getWindow();
        final WindowManager.LayoutParams lp = window.getAttributes();
        window.setFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND, WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        ValueAnimator animator;
        if (isopen) {
            animator = ValueAnimator.ofFloat(1.0f, 0.5f);
        } else {
            animator = ValueAnimator.ofFloat(0.5f, 1.0f);
        }
        animator.setDuration(400);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @TargetApi(Build.VERSION_CODES.HONEYCOMB)
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float alpha = (float) animation.getAnimatedValue();
                lp.alpha = alpha;
                window.setAttributes(lp);
            }
        });
        animator.start();
    }

    /**
     * 显示PopupWindow
     */
    private void show(View v) {
        if (mPopupWindow != null && !mPopupWindow.isShowing()) {
            mPopupWindow.showAtLocation(v, Gravity.BOTTOM, 0, 0);
        }
        setWindowAlpa(true);
    }

    private void show1(View v) {
        if (mPopupWindow1 != null && !mPopupWindow1.isShowing()) {
            mPopupWindow1.showAtLocation(v, Gravity.CENTER_HORIZONTAL, 0, 0);
        }
        setWindowAlpa(true);
    }

    /**
     * 消失PopupWindow
     */
    public void dismiss() {
        if (mPopupWindow != null && mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
        }
    }

}
