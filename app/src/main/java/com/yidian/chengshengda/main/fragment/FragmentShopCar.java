package com.yidian.chengshengda.main.fragment;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.leaf.library.StatusBarUtil;
import com.liaoinstan.springview.container.AliFooter;
import com.liaoinstan.springview.container.AliHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.yidian.chengshengda.R;
import com.yidian.chengshengda.base.BaseFragment;
import com.yidian.chengshengda.base.BasePresenter;
import com.yidian.chengshengda.details.SiteDeletails;
import com.yidian.chengshengda.main.adapter.ShopCarAdapter;
import com.yidian.chengshengda.main.bean.DeleteShopcarBean;
import com.yidian.chengshengda.main.bean.ShopcarBean;
import com.yidian.chengshengda.setpwd.bean.SetPwdBean;
import com.yidian.chengshengda.utils.NetUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.LinkedHashMap;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class FragmentShopCar extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_manager)
    TextView tvManager;
    @BindView(R.id.tv_finish)
    TextView tvFinish;
    @BindView(R.id.rc_shopcar)
    RecyclerView rcShopcar;
    @BindView(R.id.check_all)
    CheckBox checkAll;
    @BindView(R.id.tv_allprice)
    TextView tvAllprice;
    @BindView(R.id.bt_commit)
    Button btCommit;
    @BindView(R.id.ll_price)
    LinearLayout llPrice;
    @BindView(R.id.bt_delete)
    Button btDelete;
    @BindView(R.id.sv)
    SpringView sv;
    @BindView(R.id.rl_noshopcar)
    RelativeLayout rlNoshopcar;
    int i = 1;
    @BindView(R.id.rl_bottom)
    RelativeLayout rlBottom;
    private List<ShopcarBean.ObjectBean.ListBean> list;
    private int  totalPrice;
    String idStr = "";
    String month = "";
    private PopupWindow mPopupWindow1;
    private String phone;

    @Override
    protected void getid(View view) {

    }

    @Override
    protected int getResId() {
        return R.layout.fragment_shop_car;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void getData() {
        //设置状态栏颜色及字体颜色
        StatusBarUtil.setGradientColor(getActivity(), toolbar);
        StatusBarUtil.setDarkMode(getActivity());

        tvManager.setOnClickListener(this);
        tvFinish.setOnClickListener(this);
        btCommit.setOnClickListener(this);
        btDelete.setOnClickListener(this);


        //获取购物车数据
        getShopCarData(5, i, 15);

        sv.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                i = 1;
                getShopCarData(5, i, 15);
                checkAll.setChecked(false);
                totalPrice = 0;
                tvAllprice.setText("¥0");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        sv.onFinishFreshAndLoad();
                    }
                }, 1000);
            }

            @Override
            public void onLoadmore() {
                i++;
                getShopCarData(5, i, 15);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        sv.onFinishFreshAndLoad();
                    }
                }, 1000);
            }
        });
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        getShopCarData(5, i, 15);
        checkAll.setChecked(false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //管理
            case R.id.tv_manager:
                //隐藏管理 显示完成
                tvManager.setVisibility(View.GONE);
                tvFinish.setVisibility(View.VISIBLE);
                //隐藏价格 显示删除
                llPrice.setVisibility(View.GONE);
                btDelete.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_finish:
                //点击完成 隐藏完成显示管理
                tvManager.setVisibility(View.VISIBLE);
                tvFinish.setVisibility(View.GONE);
                //隐藏价格 显示删除
                llPrice.setVisibility(View.VISIBLE);
                btDelete.setVisibility(View.GONE);
                break;
            //提交商品 弹出联系方式
            case R.id.bt_commit:
                for (ShopcarBean.ObjectBean.ListBean bean : list) {
                    if(bean.isPersonChecked()){
                        int stationId = bean.getStationId();
                        int monthTime = bean.getMonthTime();
                        idStr = idStr + stationId+",";
                        month = month + monthTime+",";
                    }
                }
                String str = idStr.substring(0,idStr.length()-1);
                String monthstr = month.substring(0,idStr.length()-1);
                showDialog();
                NetUtils.getInstance().getApis()
                        .doAddOrder("http://192.168.10.104:8081/order/insertOrderList",49,str,monthstr)
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
                                    Toast.makeText(getContext(), "订单提交成功", Toast.LENGTH_SHORT).show();

                                    // TODO: 2020/10/31 0031 删除购物车里的数据
                                    NetUtils.getInstance().getApis()
                                            .deleteShopCar("http://192.168.10.104:8081/shopping/deleteModel",2,str)
                                            .subscribeOn(Schedulers.io())
                                            .observeOn(AndroidSchedulers.mainThread())
                                            .subscribe(new Observer<DeleteShopcarBean>() {
                                                @Override
                                                public void onSubscribe(Disposable d) {

                                                }

                                                @Override
                                                public void onNext(DeleteShopcarBean deleteShopcarBean) {
                                                    Toast.makeText(getContext(), ""+deleteShopcarBean.getMsg(), Toast.LENGTH_SHORT).show();
                                                    if(deleteShopcarBean.getType().equals("OK")){
                                                        //删除成功刷新页面
                                                        getShopCarData(2,1,15);
                                                    }
                                                }

                                                @Override
                                                public void onError(Throwable e) {

                                                }

                                                @Override
                                                public void onComplete() {

                                                }
                                            });
                                    //弹出联系方式并创建订单
                                    //添加成功后处理
                                    mPopupWindow1 = new PopupWindow();
                                    mPopupWindow1.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                                    mPopupWindow1.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
                                    View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_phone, null);

                                    TextView tv_name = view.findViewById(R.id.tv_name);
                                    TextView tv_number = view.findViewById(R.id.tv_number);
                                    TextView tv_call = view.findViewById(R.id.tv_call);

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
                                    mPopupWindow1.setContentView(view);
                                    mPopupWindow1.setBackgroundDrawable(new BitmapDrawable());
                                    mPopupWindow1.setFocusable(true);
                                    mPopupWindow1.setOutsideTouchable(true);
                                    mPopupWindow1.setOnDismissListener(new PopupWindow.OnDismissListener() {
                                        @Override
                                        public void onDismiss() {
                                            setWindowAlpa(false);
                                        }
                                    });
                                    show1(view);
                                }
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });
                break;
            //删除购物车中数据
            case R.id.bt_delete:
                for (ShopcarBean.ObjectBean.ListBean bean : list) {
                    if(bean.isPersonChecked()){
                        int stationId = bean.getStationId();
                        idStr = idStr + stationId+",";
                    }
                }
                String str1 = idStr.substring(0,idStr.length()-1);
                Log.e("xxx",str1);
                // TODO: 2020/10/31 0031 删除购物车里的数据
                NetUtils.getInstance().getApis()
                        .deleteShopCar("http://192.168.10.104:8081/shopping/deleteModel",2,str1)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<DeleteShopcarBean>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(DeleteShopcarBean deleteShopcarBean) {
                                Toast.makeText(getContext(), ""+deleteShopcarBean.getMsg(), Toast.LENGTH_SHORT).show();
                                if(deleteShopcarBean.getType().equals("OK")){
                                    //删除成功刷新页面
                                    getShopCarData(2,1,15);
                                }
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });
                break;
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void calculationCountAndPrice(String i) {
        //是否所有的条目都被选中
        boolean isAllChecked = true;
        totalPrice = 0;
        for (ShopcarBean.ObjectBean.ListBean bean : list) {
            if (!bean.isPersonChecked()) {
                isAllChecked = false;
            } else {
                totalPrice += (bean.getStationMoney() * bean.getMonthTime());
            }
        }
        tvAllprice.setText("¥"+totalPrice);
        checkAll.setChecked(isAllChecked);
    }
    @Override
    public void onResume() {
        super.onResume();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        Log.d("xxx", "onResume");
    }

    public void getShopCarData(int id, int page, int size) {
        showDialog();
        // TODO: 2020/10/31 0031 查询购物车
        NetUtils.getInstance().getApis()
                .getShopCar("http://192.168.10.104:8081/shopping/selectShopping", 2, page, size)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ShopcarBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext(ShopcarBean shopcarBean) {
                        hideDialog();
                            list = shopcarBean.getObject().getList();
                            //拿到购物车数据
                            if (list.size() > 0 && list != null) {
                                sv.setHeader(new AliHeader(getContext()));


                                sv.setVisibility(View.VISIBLE);
                                rlNoshopcar.setVisibility(View.GONE);
                                rlBottom.setVisibility(View.VISIBLE);

                                //设置适配器
                                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
                                rcShopcar.setLayoutManager(linearLayoutManager);
                                ShopCarAdapter shopCarAdapter = new ShopCarAdapter(getContext(), list);
                                rcShopcar.setAdapter(shopCarAdapter);

                                checkAll.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        // 先获取是否被选中,在onClick中拿到的是已经改变过的值
                                        for (ShopcarBean.ObjectBean.ListBean bean : list) {
                                            bean.setPersonChecked(checkAll.isChecked());
                                        }
                                        //刷新列表
                                        shopCarAdapter.notifyDataSetChanged();
                                        //重新计算价格
                                        calculationCountAndPrice("123");
                                    }
                                });


                            } else {
                                rlNoshopcar.setVisibility(View.VISIBLE);
                                sv.setVisibility(View.GONE);
                                rlBottom.setVisibility(View.GONE);

                            }
                            if (list.size() > 20) {
                                sv.setFooter(new AliFooter(getContext()));
                            }
                    }

                    @Override
                    public void onError(Throwable e) {
                        hideDialog();
                        Toast.makeText(getContext(), "请求失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    //设置透明度
    public void setWindowAlpa(boolean isopen) {
        if (Build.VERSION.SDK_INT < 11) {
            return;
        }

        final Window window = getActivity().getWindow();
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
        if (mPopupWindow1 != null && mPopupWindow1.isShowing()) {
            mPopupWindow1.dismiss();
        }
    }
}
