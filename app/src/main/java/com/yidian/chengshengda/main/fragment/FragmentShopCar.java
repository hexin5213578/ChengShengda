package com.yidian.chengshengda.main.fragment;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.leaf.library.StatusBarUtil;
import com.liaoinstan.springview.container.AliFooter;
import com.liaoinstan.springview.container.AliHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.yidian.chengshengda.R;
import com.yidian.chengshengda.base.BaseFragment;
import com.yidian.chengshengda.base.BasePresenter;
import com.yidian.chengshengda.main.bean.ShopcarBean;
import com.yidian.chengshengda.utils.NetUtils;

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
        sv.setHeader(new AliHeader(getContext()));
        //设置状态栏颜色及字体颜色
        StatusBarUtil.setGradientColor(getActivity(), toolbar);
        StatusBarUtil.setDarkMode(getActivity());

        tvManager.setOnClickListener(this);
        tvFinish.setOnClickListener(this);
        btCommit.setOnClickListener(this);
        btDelete.setOnClickListener(this);

        //获取购物车数据
        getShopCarData(5,10,15);
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


                break;
            //删除购物车中数据
            case R.id.bt_delete:


                break;
        }
    }

    public void getShopCarData(int id,int page, int size) {
        showDialog();
        NetUtils.getInstance().getApis()
                .getShopCar("http://192.168.10.106:8081/shopping/selectShopping", 2, page, size)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ShopcarBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ShopcarBean shopcarBean) {
                        hideDialog();
                        String type = shopcarBean.getType();
                        if (type.equals("OK")) {
                            List<ShopcarBean.ObjectBean.ListBean> list =
                                    shopcarBean.getObject().getList();
                            //拿到购物车数据
                            if (list.size() > 0 && list != null) {
                                sv.setVisibility(View.VISIBLE);
                                rlNoshopcar.setVisibility(View.GONE);
                                //设置适配器



                            }else{
                                rlNoshopcar.setVisibility(View.VISIBLE);
                                sv.setVisibility(View.GONE);
                            }
                            if (list.size() > 20) {
                                sv.setFooter(new AliFooter(getContext()));
                            }
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
}
