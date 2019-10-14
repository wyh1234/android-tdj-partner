package com.tdjpartner.common;


import com.tdjpartner.http.BaseResponse;
import com.tdjpartner.model.Bank;
import com.tdjpartner.model.BankList;
import com.tdjpartner.model.BannerEntity;
import com.tdjpartner.model.HomePageFuncationButton;
import com.tdjpartner.model.IntegralShop;
import com.tdjpartner.model.PartnerMessageInfo;
import com.tdjpartner.model.PartnerMessageItemInfo;
import com.tdjpartner.model.RentingInfos;
import com.tdjpartner.model.UserInfo;

import java.util.List;
import java.util.Map;

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
    @POST("partner/pushMessage/findListCountByUserId")
    Observable<BaseResponse<List<PartnerMessageInfo>>> pushMessage(@Body RequestBody body);

    /*
     * 消息请求item
     *
     *
     * */
    @Headers({"url_type:xuming"})
    @POST("partner/pushMessage/findListByParam")
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
    @POST("partner/userCard/addUserCard")
    Observable<BaseResponse<Integer>> addUserCard(@Body RequestBody body);

    /*
     *获取银行卡信息
     *
     *
     * */
    @Headers({"url_type:xuming"})
    @POST("partner/bankAccount/selectDefaultByUserId")
    Observable<BaseResponse<Bank>> bankAccount(@Body RequestBody body);

    /*
     *获取银行卡列表
     *
     *
     * */
    @Headers({"url_type:xuming"})
    @POST("partner/bankAccount/selectBankInfoList")
    Observable<BaseResponse<List<BankList>>> selectBankInfoList(@Body RequestBody body);

    /*
     *绑定银行卡
     *
     *
     * */
    @Headers({"url_type:xuming"})
    @POST("partner/bankAccount/addBankAccount")
    Observable<BaseResponse<Bank>> addBankAccount(@Body RequestBody body);
    /*
     *提现
     *
     *
     * */
    @Headers({"url_type:xuming"})
    @POST("partner/cashWithdrawalFlow/addUserCashWithdrawalFlow")
    Observable<BaseResponse<Integer>> cashWithdrawalFlow(@Body RequestBody body);


}
