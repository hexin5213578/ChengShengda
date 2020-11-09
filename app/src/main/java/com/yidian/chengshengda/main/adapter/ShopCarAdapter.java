package com.yidian.chengshengda.main.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.yidian.chengshengda.R;
import com.yidian.chengshengda.custom.CustomCountView;
import com.yidian.chengshengda.custom.CustomRoundAngleImageView;
import com.yidian.chengshengda.main.bean.ShopcarBean;
import com.yidian.chengshengda.main.bean.UpdateShopcarMonthBean;
import com.yidian.chengshengda.utils.NetUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ShopCarAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final Context context;
    private final List<ShopcarBean.ObjectBean.ListBean> list;



    public ShopCarAdapter(Context context, List<ShopcarBean.ObjectBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_shopcar, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ShopcarBean.ObjectBean.ListBean listBean = list.get(position);
        int userId = listBean.getUserId();
        int stationId = listBean.getStationId();
        ((ViewHolder) holder).tvMonthCount.setText(listBean.getMonthTime()+"月");
        ((ViewHolder) holder).tvSitesDistance.setText(listBean.getPlace());
        ((ViewHolder) holder).tvPrice.setText("￥"+listBean.getStationMoney());
        String stationImg = listBean.getStationImg();
        //判断图片是否为空
        if(stationImg==null){

        }else{
            String[] split = stationImg.split(",");
            Glide.with(context).load("http://81.71.121.177:8081/station/getImage?head="+split[0]).into(((ViewHolder)holder).ivImg);
        }
        //根据 bean 类中的是否选中，更改checkBox状态
        ((ViewHolder)holder).rbChecked.setChecked(listBean.isPersonChecked());
        ((ViewHolder)holder).rbChecked.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //必须是先赋值，再通知
                listBean.setPersonChecked(isChecked);
                EventBus.getDefault().post("123");
            }
        });
        //设置数量
        ((ViewHolder)holder).ccvItemShopCar.setCount(listBean.getMonthTime());
        ((ViewHolder)holder).ccvItemShopCar.setOnCountChangedListener(new CustomCountView.OnCountChangedListener() {
            @Override
            public void onCountChanged(int count) {
                listBean.setMonthTime(count);
                EventBus.getDefault().post("123");
                ((ViewHolder) holder).tvMonthCount.setText(count+"月");

                //修改购物车里的站牌租期
                // TODO: 2020/10/31 0031 修改购物车数据
                NetUtils.getInstance().getApis()
                        .updateShopCar(userId,stationId,count)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<UpdateShopcarMonthBean>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }
                            @Override
                            public void onNext(UpdateShopcarMonthBean updateShopcarMonthBean) {

                            }
                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.rb_checked)
        CheckBox rbChecked;
        @BindView(R.id.iv_img)
        CustomRoundAngleImageView ivImg;
        @BindView(R.id.tv_sites_distance)
        TextView tvSitesDistance;
        @BindView(R.id.tv_month_count)
        TextView tvMonthCount;
        @BindView(R.id.ccv_item_shop_car)
        CustomCountView ccvItemShopCar;
        @BindView(R.id.tv_price)
        TextView tvPrice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
