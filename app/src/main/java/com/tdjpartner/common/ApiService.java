package com.tdjpartner.common;


import com.tdjpartner.http.BaseResponse;
import com.tdjpartner.model.AfterDetailData;
import com.tdjpartner.model.AfterSaleInfoData;
import com.tdjpartner.model.AfterSales;
import com.tdjpartner.model.AppVersion;
import com.tdjpartner.model.BaiFangHistory;
import com.tdjpartner.model.Bank;
import com.tdjpartner.model.BankList;
import com.tdjpartner.model.BannerEntity;
import com.tdjpartner.model.ClientDetails;
import com.tdjpartner.model.ClientInfo;
import com.tdjpartner.model.ClientSeachInfo;
import com.tdjpartner.model.CouponsStatistics;
import com.tdjpartner.model.DegreeOfSatisfaction;
import com.tdjpartner.model.DeleteSalesAppByEntityId;
import com.tdjpartner.model.DiscountCoupon;
import com.tdjpartner.model.DistinctList;
import com.tdjpartner.model.DriverLocation;
import com.tdjpartner.model.DropOuting;
import com.tdjpartner.model.EarningsHistory;
import com.tdjpartner.model.GoodsInfo;
import com.tdjpartner.model.HomeData;
import com.tdjpartner.model.HomeDataDetails;
import com.tdjpartner.model.HomePageFuncationButton;
import com.tdjpartner.model.HotelAuditInfo;
import com.tdjpartner.model.HotelAuditPageList;
import com.tdjpartner.model.IntegralShop;
import com.tdjpartner.model.InvitationHistory;
import com.tdjpartner.model.IronDayAndMonthData;
import com.tdjpartner.model.IronHomeData;
import com.tdjpartner.model.IronHomeTopData;
import com.tdjpartner.model.IronStatisticsDetails;
import com.tdjpartner.model.MyCountMoney;
import com.tdjpartner.model.MyTeam;
import com.tdjpartner.model.NewHomeData;
import com.tdjpartner.model.NewMyTeam;
import com.tdjpartner.model.OrderDetail;
import com.tdjpartner.model.OrderList;
import com.tdjpartner.model.PageByCSIdList;
import com.tdjpartner.model.ParentList;
import com.tdjpartner.model.PartnerCheck;
import com.tdjpartner.model.PartnerCheckDetails;
import com.tdjpartner.model.PartnerMessageInfo;
import com.tdjpartner.model.PartnerMessageItemInfo;
import com.tdjpartner.model.RefundDetail;
import com.tdjpartner.model.RentingInfos;
import com.tdjpartner.model.SelectPerson;
import com.tdjpartner.model.SettingPerson;
import com.tdjpartner.model.StoreInfo;
import com.tdjpartner.model.TeamOverView;
import com.tdjpartner.model.ToMakeMoney;
import com.tdjpartner.model.UserInfo;
import com.tdjpartner.model.V3HomeData;
import com.tdjpartner.model.WithdrawDetalis;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
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
    Observable<BaseResponse<Object>> forget_pwd(@FieldMap Map<String, Object> params);


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
    Observable<BaseResponse<String>> imageUpload(@Part MultipartBody.Part part);


    //发送短信
    @Headers({"url_type:xuming"})
    @POST("tdj-partner/partner/afterSalesApplication/create")
    Observable<BaseResponse<AfterSales>> afterSalesApplication(@Body RequestBody requestBody);


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
     * flag //0-刷新门店信息 1-获取当前登陆用户信息
     */
    @Headers({"url_type:weather"})
    @GET("customer/refreshInfo")
    Observable<BaseResponse<UserInfo>> customer_refreshInfo(@Query("site") int site, @Query("flag") int flag, @Query("entityId") int entityId, @Query("loginUserId") int loginUserId, @Query("uniqueId") String uniqueId);


    @Headers({"url_type:weather"})
    @GET("afterSalesApplication/findPageByCSIdList")
    Observable<BaseResponse<PageByCSIdList>> findPageByCSIdList(@Query("site") int site, @Query("type") int type,
                                                                @Query("id") int id,
                                                                @Query("pn") int pn,
                                                                @Query("ps") int ps, @Query("startTime") String startTime,
                                                                @Query("endTime") String endTime);


    @Headers({"url_type:weather"})
    @GET("order/getDriverLocation")
    Observable<BaseResponse<DriverLocation>> getDriverLocation(@Query("site") int site, @Query("orderNo") String orderNo, @Query("driverTel") String driverTel, @Query("customerAddrId") int customerAddrId);

    @Headers({"url_type:weather"})
    @FormUrlEncoded
    @POST("afterSalesApplication/deleteSalesAppByEntityId")
    Observable<BaseResponse<DeleteSalesAppByEntityId>> deleteSalesAppByEntityId(@Field("site") int site,
                                                                                @Field("entityId") int entityId, @Field("orderId") int orderId,
                                                                                @Field("orderItemId") int orderItemId);

    @Headers({"url_type:weather"})
    @FormUrlEncoded
    @POST("fund/shippingEvaluation/getDegreeOfSatisfaction")
    Observable<BaseResponse<DegreeOfSatisfaction>> getDegreeOfSatisfaction(@Query("site") int site, @Query("driverId") int driverId, @Query("extOrderId") String extOrderId);


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
     *是否售后
     *
     *
     * */
    @Headers({"url_type:xuming"})
    @POST("tdj-partner/partner/order/isApplyAfterSales")
    Observable<BaseResponse<Boolean>> isApplyAfterSales(@Body RequestBody body);


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
    Observable<BaseResponse<List<ClientInfo>>> hotelMap(@Body RequestBody body);


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
    Observable<BaseResponse<PartnerCheck>> verifyList(@Body RequestBody body);


    /*
     *.获取我的个人资金信息  总收益  已提现  剩余金额
     *
     *
     * */
    @Headers({"url_type:xuming"})
    @POST("tdj-partner/partner/amountAnalysisRecords/myCountMoney")
    Observable<BaseResponse<MyCountMoney>> myCountMoney(@Body RequestBody body);

    /*
     *.审核明细
     *
     *
     * */
    @Headers({"url_type:xuming"})
    @POST("tdj-partner/partner/userApply/verifyDetail")
    Observable<BaseResponse<PartnerCheckDetails>> verifyDetail(@Body RequestBody body);

    /*
     *.审核明细
     *
     *
     * */
    @Headers({"url_type:xuming"})
    @POST("tdj-partner/partner/userApply/clickVerify")
    Observable<BaseResponse<Integer>> clickVerify(@Body RequestBody body);


    /*
     *.专员列表
     *
     *
     * */
    @Headers({"url_type:xuming"})
    @POST("tdj-report/report/customer/setManagerList")
    Observable<BaseResponse<SettingPerson>> managerList(@Body RequestBody body);

    /*
     *.客户详情
     *
     *
     * */
    @Headers({"url_type:xuming"})
    @POST("tdj-report/report/customer/customerInfo")
    Observable<BaseResponse<ClientDetails>> client_details(@Body RequestBody body);

    /*
     *.选择专员列表
     *
     *
     * */
    @Headers({"url_type:xuming"})
    @POST("tdj-partner/partner/userRelations/managerList")
    Observable<BaseResponse<SelectPerson>> userRelations_managerList(@Body RequestBody body);


    /*
     *.选择专员列表
     *
     *
     * */
    @Headers({"url_type:xuming"})
    @POST("tdj-partner/partner/userRelations/setManager")
    Observable<BaseResponse<Integer>> userRelations_setManager(@Body RequestBody body);

    /*
     *.选择专员列表
     *
     *
     * */
    @Headers({"url_type:xuming"})
    @POST("tdj-partner/partner/userRelations/modifyAvatarUrl")
    Observable<BaseResponse<Integer>> modifyAvatarUrl(@Body RequestBody body);

    /*
     *.选择专员列表
     *
     *
     * */
    @Headers({"url_type:xuming"})
    @POST("tdj-partner/partner/worship/insert")
    Observable<BaseResponse<Integer>> worship(@Body RequestBody body);

    /*
     *.
     *
     *
     * */
    @Headers({"url_type:xuming"})
    @POST("tdj-report/report/teamOverView/day")
    Observable<BaseResponse<TeamOverView>> teamOverView_day(@Body RequestBody body);

    /*
     *.
     *
     *
     * */
    @Headers({"url_type:xuming"})
    @POST("tdj-report/report/teamOverView/month")
    Observable<BaseResponse<TeamOverView>> teamOverView_month(@Body RequestBody body);

    /*
     *.
     *
     *
     * */
    @Headers({"url_type:xuming"})
    @POST("tdj-report/report/teamOverView/all")
    Observable<BaseResponse<TeamOverView>> teamOverView_all(@Body RequestBody body);

    /*
     *.
     *
     *
     * */
    @Headers({"url_type:xuming"})
    @POST("tdj-report/report/customer/homeData")
    Observable<BaseResponse<IronHomeData>> ironHomeData(@Body RequestBody body);

    @Headers({"url_type:xuming"})
    @POST("tdj-report/report/customer/homeData")
    Observable<BaseResponse<HomeData>> homeData(@Body RequestBody body);

    @Headers({"url_type:xuming"})
    @POST("tdj-report/report/teamOverView/homePageTop")
    Observable<BaseResponse<NewHomeData>> newhomeData(@Body RequestBody body);


    /*
     *.
     *
     *
     * */
    @Headers({"url_type:xuming"})
    @POST("tdj-report/report/customer/homeDataDetails")
    Observable<BaseResponse<HomeDataDetails>> homeDataDetails(@Body RequestBody body);

    /*
     *.
     *
     *
     * */
    @Headers({"url_type:xuming"})
    @POST("tdj-report/report/teamOverView/myTeamHistoryPartnerList")
    Observable<BaseResponse<MyTeam>> myTeamPartnerList(@Body RequestBody body);


    @Headers({"url_type:xuming"})
    @POST("tdj-report/report/teamOverView/memberList")
    Observable<BaseResponse<List<NewMyTeam>>> memberList(@Body RequestBody body);


    @Headers({"url_type:xuming"})
    @POST("tdj-report/report/teamOverView/teamCity")
    Observable<BaseResponse<List<NewMyTeam>>> teamCity(@Body RequestBody body);


    /*
     *.
     *
     *
     * */
    @Headers({"url_type:xuming"})
    @POST("tdj-partner/partner/version/check")
    Observable<BaseResponse<AppVersion>> version_check(@Body RequestBody body);


    /*
     *.
     *
     *
     * */
    @Headers({"url_type:xuming"})
    @POST("tdj-report/report/teamOverView/myTeamPartnerSelectList")
    Observable<BaseResponse<List<String>>> myTeamPartnerSelectList(@Body RequestBody body);


    /*
     *.
     *
     *
     * */
    @Headers({"url_type:xuming"})
    @POST("tdj-partner/partner/call/distinctList")
    Observable<BaseResponse<DistinctList>> distinctList(@Body RequestBody body);

    /*
     *.
     *
     *
     * */
    @Headers({"url_type:xuming"})
    @POST("tdj-partner/partner/call/parentList")
    Observable<BaseResponse<ParentList>> parentList(@Body RequestBody body);


    @Headers({"url_type:weather"})
    @GET("afterSalesApplication/findAfterSalesAppyDetailByItemId/{orderItemId}")
    Observable<BaseResponse<RefundDetail>> after_details_order(@Path("orderItemId") int orderItemId, @Query("site") int site);


    /****************创客3.0********************/
    //首页日月统计
    @Headers({"url_type:xuming"})
    @POST("tdj-report/report/customer/homeData")
    Observable<BaseResponse<V3HomeData>> v3HomeData(@Body RequestBody body);

    //首页排行榜
    @Headers({"url_type:xuming"})
    @POST("tdj-report/report/teamOverView/homePageTop")
    Observable<BaseResponse<IronHomeTopData>> ironHomeTopData(@Body RequestBody body);

    //铁军统计
    @Headers({"url_type:xuming"})
    @POST("tdj-report/report/customer/homeDataDetails")
    Observable<BaseResponse<IronStatisticsDetails>> ironStatisticsDetails(@Body RequestBody body);

    //日月统计
    @Headers({"url_type:xuming"})
    @POST("/tdj-report/report/teamOverView/dayAndMonthData")
    Observable<BaseResponse<IronDayAndMonthData>> ironDayAndMonthData(@Body RequestBody body);

    //酒店审核
    @Headers({"url_type:xuming"})
    @POST("tdj-partner/partner/hotelAudit/pageList")
    Observable<BaseResponse<HotelAuditPageList>> hotelAuditPageList(@Body RequestBody body);
    @Headers({"url_type:xuming"})
    @POST("tdj-partner/partner/hotelAudit/info")
    Observable<BaseResponse<HotelAuditInfo>> hotelAuditInfo(@Body RequestBody body);
    @Headers({"url_type:xuming"})
    @POST("tdj-partner/partner/hotelAudit/reject")
    Observable<BaseResponse<String>> hotelAuditReject(@Body RequestBody body);
    @Headers({"url_type:xuming"})
    @POST("tdj-partner/partner/hotelAudit/pass")
    Observable<BaseResponse<String>> hotelAuditPass(@Body RequestBody body);

    //网军DB售后
    @Headers({"url_type:xuming"})
    @POST("tdj-partner/partner/afterSalesApplication/getafterSalesTask")
    Observable<BaseResponse<AfterSaleInfoData>> getafterSalesTask(@Body RequestBody body);
    @Headers({"url_type:xuming"})
    @POST("tdj-partner/partner/afterSalesApplication/afterDetail")
    Observable<BaseResponse<AfterDetailData>> afterDetail(@Body RequestBody body);
    @Headers({"url_type:xuming"})
    @POST("/tdj-partner/partner/afterSalesApplication/modifyAfterSalePhoto")
    Observable<BaseResponse<AfterDetailData>> modifyAfterSalePhoto(@Body RequestBody body);
    @Headers({"url_type:xuming"})
    @POST("tdj-partner/partner/afterSalesApplication/difficulty")
    Observable<BaseResponse<AfterDetailData>> difficulty(@Body RequestBody body);
}
