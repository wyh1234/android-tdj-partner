package com.tdjpartner.common;


import com.tdjpartner.http.BaseResponse;
import com.tdjpartner.model.BaiFangHistory;
import com.tdjpartner.model.Bank;
import com.tdjpartner.model.BankList;
import com.tdjpartner.model.BannerEntity;
import com.tdjpartner.model.ClientDetails;
import com.tdjpartner.model.ClientInfo;
import com.tdjpartner.model.ClientSeachInfo;
import com.tdjpartner.model.CouponsStatistics;
import com.tdjpartner.model.DiscountCoupon;
import com.tdjpartner.model.DropOuting;
import com.tdjpartner.model.EarningsHistory;
import com.tdjpartner.model.GoodsInfo;
import com.tdjpartner.model.HomePageFuncationButton;
import com.tdjpartner.model.IntegralShop;
import com.tdjpartner.model.InvitationHistory;
import com.tdjpartner.model.MyCountMoney;
import com.tdjpartner.model.OrderDetail;
import com.tdjpartner.model.OrderList;
import com.tdjpartner.model.PartnerCheck;
import com.tdjpartner.model.PartnerCheckDetails;
import com.tdjpartner.model.PartnerMessageInfo;
import com.tdjpartner.model.PartnerMessageItemInfo;
import com.tdjpartner.model.RentingInfos;
import com.tdjpartner.model.SelectPerson;
import com.tdjpartner.model.SettingPerson;
import com.tdjpartner.model.StoreInfo;
import com.tdjpartner.model.TeamOverView;
import com.tdjpartner.model.ToMakeMoney;
import com.tdjpartner.model.UserInfo;
import com.tdjpartner.model.WithdrawDetalis;

import java.util.List;
import java.util.Map;

import io.reactivex.Completable;
import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by Administrator on 2018/3/23.
 */

public interface ApiService {




    //缓存一个小时
    @Headers("Cache-Control: public, max-age=3600")
    @GET("appapi/index/banner/id/1?cmd=home_slider_top&limit=5")
    Observable<BaseResponse<List<BannerEntity>>> getBanner();

    @Streaming
    @GET
    Observable<ResponseBody> downLoad(@Url String url);

    @GET("product/commendCategory")
    Observable<BaseResponse<List<HomePageFuncationButton>>> commendCategory(@Query("site") int site);


    /*
     * from请求
     *
     *
     * */
    @FormUrlEncoded
    @POST("tdj-store/store/commodity/queryList")
    Observable<BaseResponse<RentingInfos>> get_rentinglist(@FieldMap Map<String, Object> map);



    @GET("tdj-store/store/commodity/queryList")
    Observable<String> get(@QueryMap Map<String, String> maps);


    /*
    * json请求
    *
    *
    * */

    @POST("tdj-store/store/commodity/queryList")
    Observable<BaseResponse<IntegralShop>> commodity_queryList(@Body RequestBody body);



    //采购商登录
    @Headers({"url_type:weather"})
    @FormUrlEncoded
    @POST("customer/login")
    Observable<BaseResponse<UserInfo>> loginData(@FieldMap Map<String, Object> params);


    //发送短信
    @Headers({"url_type:weather"})
    @FormUrlEncoded
    @POST("common/sendSms")
    Observable<BaseResponse<Object>> smsCode(@FieldMap Map<String, Object> params);


    //忘记密码
    @Headers({"url_type:weather"})
    @FormUrlEncoded
    @PUT("customer/password/forget")
    Observable<BaseResponse<Object>> forget_pwd( @FieldMap Map<String, Object> params);


    /*
     * 消息请求
     *
     *
     * */
    @Headers({"url_type:xuming"})
    @POST("tdj-partner/partner/pushMessage/findListCountByUserId")
    Observable<BaseResponse<List<PartnerMessageInfo>>> pushMessage(@Body RequestBody body);

    /*
     * 消息请求item
     *
     *
     * */
    @Headers({"url_type:xuming"})
    @POST("tdj-partner/partner/pushMessage/findListByParam")
    Observable<BaseResponse<PartnerMessageItemInfo>> pushMessage_item(@Body RequestBody body);

    //图片上传
    @Headers({"url_type:weather"})
    @Multipart
    @POST("image/uploadImgUrl")
    Observable<BaseResponse<String>>  imageUpload(@Part MultipartBody.Part part);

    /*
     *实名认证
     *
     *
     * */
    @Headers({"url_type:xuming"})
    @POST("tdj-partner/partner/userCard/addUserCard")
    Observable<BaseResponse<Integer>> addUserCard(@Body RequestBody body);

    /*
     *获取银行卡信息
     *
     *
     * */
    @Headers({"url_type:xuming"})
    @POST("tdj-partner/partner/bankAccount/selectDefaultByUserId")
    Observable<BaseResponse<Bank>> bankAccount(@Body RequestBody body);

    /*
     *获取银行卡列表
     *
     *
     * */
    @Headers({"url_type:xuming"})
    @POST("tdj-partner/partner/bankAccount/selectBankInfoList")
    Observable<BaseResponse<List<BankList>>> selectBankInfoList(@Body RequestBody body);

    /*
     *绑定银行卡
     *
     *
     * */
    @Headers({"url_type:xuming"})
    @POST("tdj-partner/partner/bankAccount/addBankAccount")
    Observable<BaseResponse<Bank>> addBankAccount(@Body RequestBody body);
    /*
     *提现
     *
     *
     * */
    @Headers({"url_type:xuming"})
    @POST("tdj-partner/partner/cashWithdrawalFlow/addUserCashWithdrawalFlow")
    Observable<BaseResponse<Integer>> cashWithdrawalFlow(@Body RequestBody body);

    /*
     *提现
     *
     *
     * */
    @Headers({"url_type:xuming"})
    @POST("tdj-partner/partner/cashWithdrawalFlow/findCashWithdrawalFlowList")
    Observable<BaseResponse<WithdrawDetalis>> findCashWithdrawalFlowList(@Body RequestBody body);

    /**
     * 采购商 - 用户信息刷新
     * <p/>
     * url	方法	说明
     * /customer/refreshInfo	GET	刷新用户信息
     * 请求参数
     * <p/>
     * 参数名	类型	必须(1是/0否)	说明
     * entityId	int	1	主账号身份时取登录信息的entityId，子账户时取subUserId
     * uniqueId	String	1	登录时uniqueId设备号
     *  flag //0-刷新门店信息 1-获取当前登陆用户信息
     */
    @Headers({"url_type:weather"})
    @GET("customer/refreshInfo")
    Observable<BaseResponse<UserInfo>>  customer_refreshInfo(@Query("site") int site, @Query("flag") int flag, @Query("entityId") int entityId, @Query("loginUserId") int loginUserId, @Query("uniqueId") String uniqueId);


    @Headers({"url_type:weather"})
    @FormUrlEncoded
    @POST("pushMessage/logout/delete/token")
    Observable<BaseResponse<Object>> pushMessageLogout(@Field("site") int site, @Field("userType") int userType, @Field("phoneNumber") String phoneNumber);

    /*
     *即将掉落
     *
     *
     * */
    @Headers({"url_type:xuming"})
    @POST("tdj-report/report/customer/downList")
    Observable<BaseResponse<DropOuting>> downList(@Body RequestBody body);

    /*
     *公海跟进
     *
     *
     * */
    @Headers({"url_type:xuming"})
    @POST("tdj-report/report/customer/followList")
    Observable<BaseResponse<DropOuting>> followList(@Body RequestBody body);

    /*
     *跟进
     *
     *
     * */
    @Headers({"url_type:xuming"})
    @POST("tdj-partner/partner/internationalWaters/want")
    Observable<BaseResponse<Integer>> internationalWaters(@Body RequestBody body);

    /*
     *跟进
     *
     *
     * */
    @Headers({"url_type:xuming"})
    @POST("tdj-partner/partner/collect/products")
    Observable<BaseResponse<GoodsInfo>> collect_products(@Body RequestBody body);


    /*
     *跟进
     *
     *
     * */
    @Headers({"url_type:xuming"})
    @POST("tdj-partner/partner/collect/stores")
    Observable<BaseResponse<StoreInfo>> collect_stores(@Body RequestBody body);


    /*
     *订单记录
     *
     *
     * */
    @Headers({"url_type:xuming"})
    @POST("tdj-partner/partner/order/pageList")
    Observable<BaseResponse<OrderList>> order_pageList(@Body RequestBody body);

    /*
     *订单详情
     *
     *
     * */
    @Headers({"url_type:xuming"})
    @POST("tdj-partner/partner/order/findOne")
    Observable<BaseResponse<OrderDetail>> findOne(@Body RequestBody body);

    /*
     *券
     *
     *
     * */
    @Headers({"url_type:xuming"})
    @POST("tdj-partner/partner/coupons/findByUser")
    Observable<BaseResponse<DiscountCoupon>> coupons_findByUser(@Body RequestBody body);

    /*
     *拜访记录
     *
     *
     * */
    @Headers({"url_type:xuming"})
    @POST("tdj-partner/partner/call/list")
    Observable<BaseResponse<BaiFangHistory>> call_list(@Body RequestBody body);
    /*
     *.我要拜访
     *
     *
     * */
    @Headers({"url_type:xuming"})
    @POST("tdj-partner/partner/call/insert")
    Observable<BaseResponse<Integer>> call_insert(@Body RequestBody body);

    /*
     *.统计代金券
     *
     *
     * */
    @Headers({"url_type:xuming"})
    @POST("tdj-partner/partner/coupons/statistics")
    Observable<BaseResponse<CouponsStatistics>> coupons_statistics(@Body RequestBody body);

    /*
     *.去赚钱接口
     *
     *
     * */
    @Headers({"url_type:xuming"})
    @POST("tdj-partner/partner/amountAnalysisRecords/toMakeMoney")
    Observable<BaseResponse<ToMakeMoney>> amountAnalysisRecords(@Body RequestBody body);
    /*
     *.邀请记录
     *
     *
     * */
    @Headers({"url_type:xuming"})
    @POST("tdj-partner/partner/amountAnalysisRecords/myCustomerList")
    Observable<BaseResponse<InvitationHistory>> myCustomerList(@Body RequestBody body);
    /*
     *.收益记录
     *
     *
     * */
    @Headers({"url_type:xuming"})
    @POST("tdj-partner/partner/amountAnalysisRecords/info")
    Observable<BaseResponse<EarningsHistory>> earning_info(@Body RequestBody body);

    /*
     *.地图
     *
     *
     * */
    @Headers({"url_type:xuming"})
    @POST("tdj-report/report/customer/hotelMap")
    Observable<BaseResponse<List<ClientInfo> >> hotelMap(@Body RequestBody body);


    /*
     *.地图搜索
     *
     *
     * */
    @Headers({"url_type:xuming"})
    @POST("tdj-report/report/customer/hotelSearch")
    Observable<BaseResponse<ClientSeachInfo>> customer_hotelMap(@Body RequestBody body);

    /*
     *.审核列表
     *
     *
     * */
    @Headers({"url_type:xuming"})
    @POST("tdj-partner/partner/userApply/verifyList")
    Observable<BaseResponse<PartnerCheck>> verifyList(@Body  RequestBody body);


    /*
     *.获取我的个人资金信息  总收益  已提现  剩余金额
     *
     *
     * */
    @Headers({"url_type:xuming"})
    @POST("tdj-partner/partner/amountAnalysisRecords/myCountMoney")
    Observable<BaseResponse<MyCountMoney>> myCountMoney(@Body  RequestBody body);

    /*
     *.审核明细
     *
     *
     * */
    @Headers({"url_type:xuming"})
    @POST("tdj-partner/partner/userApply/verifyDetail")
    Observable<BaseResponse<PartnerCheckDetails>> verifyDetail(@Body  RequestBody body);

    /*
     *.审核明细
     *
     *
     * */
    @Headers({"url_type:xuming"})
    @POST("tdj-partner/partner/userApply/clickVerify")
    Observable<BaseResponse<Integer>> clickVerify(@Body  RequestBody body);


    /*
     *.专员列表
     *
     *
     * */
    @Headers({"url_type:xuming"})
    @POST("tdj-report/report/customer/setManagerList")
    Observable<BaseResponse<SettingPerson>> managerList(@Body  RequestBody body);

    /*
     *.客户详情
     *
     *
     * */
    @Headers({"url_type:xuming"})
    @POST("tdj-report/report/customer/customerInfo")
    Observable<BaseResponse<ClientDetails>> client_details(@Body  RequestBody body);

    /*
     *.选择专员列表
     *
     *
     * */
    @Headers({"url_type:xuming"})
    @POST("tdj-partner/partner/userRelations/managerList")
    Observable<BaseResponse<SelectPerson>> userRelations_managerList(@Body  RequestBody body);


    /*
     *.选择专员列表
     *
     *
     * */
    @Headers({"url_type:xuming"})
    @POST("tdj-partner/partner/userRelations/setManager")
    Observable<BaseResponse<Integer>> userRelations_setManager(@Body  RequestBody body);

    /*
     *.选择专员列表
     *
     *
     * */
    @Headers({"url_type:xuming"})
    @POST("tdj-partner/partner/userRelations/modifyAvatarUrl")
    Observable<BaseResponse<Integer>> modifyAvatarUrl(@Body  RequestBody body);

    /*
     *.选择专员列表
     *
     *
     * */
    @Headers({"url_type:xuming"})
    @POST("tdj-partner/partner/worship/insert")
    Observable<BaseResponse<Integer>> worship(@Body  RequestBody body);
    /*
     *.
     *
     *
     * */
    @Headers({"url_type:xuming"})
    @POST("tdj-report/report/teamOverView/day")
    Observable<BaseResponse<TeamOverView>> teamOverView_day(@Body  RequestBody body);
    /*
     *.
     *
     *
     * */
    @Headers({"url_type:xuming"})
    @POST("tdj-report/report/teamOverView/month")
    Observable<BaseResponse<TeamOverView>> teamOverView_month(@Body  RequestBody body);

    /*
     *.
     *
     *
     * */
    @Headers({"url_type:xuming"})
    @POST("tdj-report/report/teamOverView/all")
    Observable<BaseResponse<TeamOverView>> teamOverView_all(@Body  RequestBody body);
}
