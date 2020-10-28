package com.yidian.chengshengda.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.leaf.library.StatusBarUtil;
import com.yidian.chengshengda.R;
import com.yidian.chengshengda.base.BaseAvtivity;
import com.yidian.chengshengda.base.BasePresenter;
import com.yidian.chengshengda.main.adapter.AreaAdapter;
import com.yidian.chengshengda.main.bean.SaveAreaBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


//选择区域
public class SeleteAreaActivity extends BaseAvtivity implements View.OnClickListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.tv_mylocation)
    TextView tvMylocation;
    @BindView(R.id.tv_kexuan)
    TextView tvKexuan;
    @BindView(R.id.rc_area)
    RecyclerView rcArea;
    String[] str = {"渝中区", "万州区", "涪陵区", "大渡口区", "沙坪坝区", "九龙坡区", "南岸区", "北碚区", "綦江区", "大足区",
            "渝北区", "巴南区", "黔江区", "长寿区", "江津区", "合川区", "永川区", "南川区", "璧山区", "铜梁区", "潼南区", "荣昌区", "开州区", "梁平区", "武隆区"
            , "城口县", "丰都县", "垫江县", "忠县", "云阳县", "奉节县", "巫山县", "巫溪县", "石柱土家族自治县", "秀山土家族苗族自治县", "酉阳土家族苗族自治县", "彭水苗族土家族自治县"};
    private List<String> list;

    @Override
    protected int getResId() {
        return R.layout.activity_selectarea;
    }

    @Override
    protected void getData() {
        StatusBarUtil.setGradientColor(this,toolbar);
        StatusBarUtil.setDarkMode(this);

        Intent intent = getIntent();
        String area = intent.getStringExtra("area");
        //设置当前定位点
        tvMylocation.setText(area);


        back.setOnClickListener(this);
        title.setText("定位");
        list = new ArrayList<>();
        for (int i =0;i<str.length;i++){
            list.add(str[i]);
        }
        //创建适配器
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        rcArea.setLayoutManager(gridLayoutManager);
        AreaAdapter areaAdapter = new AreaAdapter(this,list);
        rcArea.setAdapter(areaAdapter);
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                finish();
                break;
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getArea(SaveAreaBean saveAreaBean){
        String area = saveAreaBean.getArea();
        if(!TextUtils.isEmpty(area)){
            finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().unregister(this);
        }
    }
}
