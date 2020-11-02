package com.yidian.chengshengda.utils;

import com.yidian.chengshengda.details.bean.StationDetailsBean;
import com.yidian.chengshengda.login.bean.LoginBean;
import com.yidian.chengshengda.main.bean.AllStationBean;
import com.yidian.chengshengda.main.bean.DeleteShopcarBean;
import com.yidian.chengshengda.main.bean.SaveShopCarBean;
import com.yidian.chengshengda.main.bean.ShopcarBean;
import com.yidian.chengshengda.main.bean.UpdateShopcarMonthBean;
import com.yidian.chengshengda.main.fragment.adv.bean.NosellSiteBean;
import com.yidian.chengshengda.main.fragment.adv.bean.SitesBean;
import com.yidian.chengshengda.main.fragment.order.bean.DeleteOrderBean;
import com.yidian.chengshengda.main.fragment.order.bean.OrderInfoBean;
import com.yidian.chengshengda.regist.bean.GetPhoneCodeBean;
import com.yidian.chengshengda.regist.bean.RegistBean;
import com.yidian.chengshengda.setpwd.bean.SetPwdBean;
import com.yidian.chengshengda.setup.bean.UserInfoBean;

import java.net.URL;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface Apis {

    //发送验证码
    @GET("user/sendSms")
    Observable<GetPhoneCodeBean> getPhoneCode(@Query("phoneNum")String phone);

    //注册
    @GET("user/register")
    Observable<RegistBean> doRegist(@Query("phoneNum")String phone, @Query("password")String pwd);

    //密码登录
    @GET("user/userLogin")
    Observable<LoginBean> doPwdLogin(@Query("phoneNum")String phone, @Query("password")String pwd, @Query("accountType") int type, @Query("lng")double lng, @Query("lat")double lat);

    //QQ登录
    @GET("user/userLogin")
    Observable<LoginBean> doQqLogin(@Query("accountType") int type,@Query("qqOpenID")String qqId,@Query("lng")double lng,@Query("lat")double lat);

    //微信登录
    @GET("user/userLogin")
    Observable<LoginBean> doWechatLogin(@Query("accountType") int type,@Query("weChatOpenId")String wechatId,@Query("lng")double lng,@Query("lat")double lat);


    //获取所有站点信息
    @GET()
    Observable<AllStationBean> getAllStation(@Url String url);

    //根据ID查询站点详情
    @GET()
    Observable<StationDetailsBean> getStationDetails(@Url String url , @Query("stationNum") String num);

    //查询租出的站点
    @GET()
    Observable<SitesBean> getSitesfromStatus(@Url String url,@Query("status") int status,@Query("pageNum") int pageNum,@Query("pageSize")int pageSize);

    //查询未租出的站点
    @GET()
    Observable<NosellSiteBean> getNosellSite(@Url String url, @Query("status") int status, @Query("pageNum") int pageNum, @Query("pageSize")int pageSize);

    //加入购物车
    @GET()
    Observable<SaveShopCarBean> joinShopcar(@Url String url,@Query("userId")int userid,@Query("stationId")int stationid,@Query("monthTime")int monthcount);

    //查询购物车
    @GET()
    Observable<ShopcarBean> getShopCar(@Url String url,@Query("userId")int userid,@Query("pageNum")int page,@Query("pageSize") int size);

    //删除购物车
    @GET()
    Observable<DeleteShopcarBean> deleteShopCar(@Url String url,@Query("userId")int userid,@Query("stationId")String stationId);

    //修改购物车里的站牌租期
    @GET()
    Observable<UpdateShopcarMonthBean> updateShopCar(@Url String url,@Query("userId")int userid,@Query("stationId") int stationid,@Query("monthTime")int time);

    //查询id查找用户信息
    @GET()
    Observable<UserInfoBean> getUserInfo(@Url String url,@Query("userId")int userid);

    //修改用户密码
    @GET()
    Observable<SetPwdBean> doSetPwd(@Url String url,@Query("userId") int userid,@Query("oldPassword")String oldPwd,@Query("password")String pwd);

    //修改用户头像
    @GET()
    Observable<SetPwdBean> doSetHeadImg(@Url String url,@Query("id")int id,@Query("headImg")String headimg);

    //修改用户昵称
    @GET()
    Observable<SetPwdBean> dosetNikeName(@Url String url,@Query("id")int id,@Query("nickName")String nikeName);

    //添加订单
    @GET()
    Observable<SetPwdBean> doAddOrder(@Url String url,@Query("userId")int id,@Query("stationNum")String stationnum,@Query("month")String month);

    //查询订单
    @GET()
    Observable<OrderInfoBean> getOrderInfo(@Url String url,@Query("userId")int id,@Query("page")int page,@Query("pageSize")int pagesize);

    //通过id删除单个订单
    @GET()
    Observable<DeleteOrderBean> doDeleteOrder(@Url String url,@Query("id")int id);

}
