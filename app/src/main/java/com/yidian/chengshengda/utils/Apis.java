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
import com.yidian.chengshengda.regist.bean.CheckPhoneBean;
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
    @GET("userInfo/sendSms")
    Observable<GetPhoneCodeBean> getPhoneCode(@Query("phoneNum")String phone);

    //注册
    @GET("userInfo/register")
    Observable<RegistBean> doRegist(@Query("phoneNum")String phone, @Query("password")String pwd);

    //校验是否已经注册
    @GET("userInfo/selectUserInfoByPhoneNum")
    Observable<CheckPhoneBean> doCheckPhone(@Query("phoneNum") String phone);

    //忘记密码
    @GET("userInfo/updatePasswordByAuth")
    Observable<UpdateShopcarMonthBean> rememberpwd(@Query("userId")int userid,@Query("password") String pwd);

    //密码登录
    @GET("userInfo/userLogin")
    Observable<LoginBean> doPwdLogin(@Query("phoneNum")String phone, @Query("password")String pwd, @Query("accountType") int type);

    //QQ登录
    @GET("userInfo/userLogin")
    Observable<LoginBean> doQqLogin(@Query("accountType") int type,@Query("qqOpenID")String qqId);

    //微信登录
    @GET("userInfo/userLogin")
    Observable<LoginBean> doWechatLogin(@Query("accountType") int type,@Query("weChatOpenId")String wechatId);


    //获取所有站点信息
    @GET("station/selStation")
    Observable<AllStationBean> getAllStation();

    //根据ID查询站点详情
    @GET("station/selectStation")
    Observable<StationDetailsBean> getStationDetails(@Query("stationNum") String num);

    //查询租出的站点
    @GET("station/selectStationStatus")
    Observable<SitesBean> getSitesfromStatus(@Query("status") int status,@Query("pageNum") int pageNum,@Query("pageSize")int pageSize);

    //查询未租出的站点
    @GET("station/selectStationStatus")
    Observable<NosellSiteBean> getNosellSite( @Query("status") int status, @Query("pageNum") int pageNum, @Query("pageSize")int pageSize);

    //加入购物车
    @GET("shopping/insertModel")
    Observable<SaveShopCarBean> joinShopcar(@Query("userId")int userid,@Query("stationId")int stationid,@Query("monthTime")int monthcount);

    //查询购物车
    @GET("shopping/selectShopping")
    Observable<ShopcarBean> getShopCar(@Query("userId")int userid,@Query("pageNum")int page,@Query("pageSize") int size);

    //删除购物车
    @GET("shopping/deleteModel")
    Observable<DeleteShopcarBean> deleteShopCar(@Query("userId")int userid,@Query("stationId")String stationId);

    //修改购物车里的站牌租期
    @GET("shopping/updateShopping")
    Observable<UpdateShopcarMonthBean> updateShopCar(@Query("userId")int userid,@Query("stationId") int stationid,@Query("monthTime")int time);

    //查询id查找用户信息
    @GET("user/selectUserInfoByIdx")
    Observable<UserInfoBean> getUserInfo(@Query("userId")int userid);

    //修改用户密码
    @GET("userInfo/updatePassword")
    Observable<SetPwdBean> doSetPwd(@Query("userId") int userid,@Query("oldPassword")String oldPwd,@Query("password")String pwd);

    //修改用户头像
    @GET("userInfo/updateUserInfo")
    Observable<SetPwdBean> doSetHeadImg(@Query("id")int id,@Query("headImg")String headimg);

    //修改用户昵称
    @GET("userInfo/updateUserInfo")
    Observable<SetPwdBean> dosetNikeName(@Query("id")int id,@Query("nickName")String nikeName);

    //添加订单
    @GET("order/insertOrderList")
    Observable<SetPwdBean> doAddOrder(@Query("userId")int id,@Query("stationNum")String stationnum,@Query("month")String month);

    //查询订单
    @GET("order/selectOrderByUserIdx")
    Observable<OrderInfoBean> getOrderInfo(@Query("userId")int id,@Query("orderType")int type,@Query("page")int page,@Query("pageSize")int pagesize);

    //通过订单id删除单个订单
    @GET("order/delOrderByIdx")
    Observable<DeleteOrderBean> doDeleteOrder(@Query("id")int orderid);

}
