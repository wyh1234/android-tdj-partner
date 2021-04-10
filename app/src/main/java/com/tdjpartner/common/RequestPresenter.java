package com.tdjpartner.common;

import com.apkfuns.logutils.LogUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tdjpartner.http.BaseObserver;
import com.tdjpartner.http.GodObserver;
import com.tdjpartner.http.RetrofitServiceManager;
import com.tdjpartner.http.RxUtils;
import com.tdjpartner.http.interceptor.PostJsonBody;
import com.tdjpartner.model.AfterSaleInfoData;
import com.tdjpartner.model.AfterSales;
import com.tdjpartner.model.AppVersion;
import com.tdjpartner.model.BaiFangHistory;
import com.tdjpartner.model.Bank;
import com.tdjpartner.model.BankList;
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
import com.tdjpartner.model.IntegralShop;
import com.tdjpartner.model.InvitationHistory;
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
import com.tdjpartner.model.WithdrawDetalis;
import com.tdjpartner.utils.GeneralUtils;
import com.tdjpartner.utils.cache.UserUtils;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static okhttp3.MultipartBody.FORM;

public class RequestPresenter {
    public static ApiService getApiService() {
        return RetrofitServiceManager.getInstance().creat(ApiService.class);
    }

    public static Disposable commendCategory(BaseObserver<List<HomePageFuncationButton>> baseObserver) {
        return getApiService().commendCategory(1).compose(RxUtils.rxSchedulerHelper()).compose(RxUtils.handleResult()).subscribeWith(baseObserver);
    }

    public static Disposable get_rentinglist(BaseObserver<RentingInfos> baseObserver, Map<String, Object> map) {
        return getApiService().get_rentinglist(map).compose(RxUtils.rxSchedulerHelper()).compose(RxUtils.handleResult()).subscribeWith(baseObserver);
    }

    public static Disposable commodity_queryList(Map<String, Object> map, BaseObserver<IntegralShop> baseObserver) {
        return getApiService().commodity_queryList(jsonData(map)).compose(RxUtils.rxSchedulerHelper()).compose(RxUtils.handleResult()).subscribeWith(baseObserver);
    }

    public static Disposable pushMessage(Map<String, Object> map, BaseObserver<List<PartnerMessageInfo>> baseObserver) {
        return getApiService().pushMessage(jsonData(map)).compose(RxUtils.rxSchedulerHelper()).compose(RxUtils.handleResult()).subscribeWith(baseObserver);
    }

    public static Disposable coupons_statistics(Map<String, Object> map, BaseObserver<CouponsStatistics> baseObserver) {
        return getApiService().coupons_statistics(jsonData(map)).compose(RxUtils.rxSchedulerHelper()).compose(RxUtils.handleResult()).subscribeWith(baseObserver);
    }


    public static Disposable pushMessage_item(Map<String, Object> map, BaseObserver<PartnerMessageItemInfo> baseObserver) {
        return getApiService().pushMessage_item(jsonData(map)).compose(RxUtils.rxSchedulerHelper()).compose(RxUtils.handleResult()).subscribeWith(baseObserver);
    }

    //采购商登录
    public static Disposable loginData(Map<String, Object> params, BaseObserver<UserInfo> baseObserver) {
        return getApiService().loginData(params).compose(RxUtils.rxSchedulerHelper()).compose(RxUtils.handleResult()).subscribeWith(baseObserver);
    }

    //采购商登录
    public static Disposable smsCode(Map<String, Object> params, BaseObserver<Object> baseObserver) {
        return getApiService().smsCode(params).compose(RxUtils.rxSchedulerHelper()).compose(RxUtils.handleResult()).subscribeWith(baseObserver);
    }

    public static Disposable forget_pwd(Map<String, Object> params, BaseObserver<Object> baseObserver) {
        return getApiService().forget_pwd(params).compose(RxUtils.rxSchedulerHelper()).compose(RxUtils.handleResult()).subscribeWith(baseObserver);
    }

    //图片上传
    public static Disposable imageUpload(String fileName, byte[] imageByte, BaseObserver<String> baseObserver) {
        return getApiService().imageUpload(getMultipartBody_part(fileName, imageByte)).compose(RxUtils.rxSchedulerHelper()).compose(RxUtils.handleResult()).subscribeWith(baseObserver);

    }

    public static Disposable afterSalesApplication(Map<String, Object> params, BaseObserver<AfterSales> baseObserver) {
        return getApiService().afterSalesApplication(jsonData(params)).compose(RxUtils.rxSchedulerHelper()).compose(RxUtils.handleResult()).subscribeWith(baseObserver);

    }

    public static Disposable after_details_order(int orderItemId, BaseObserver<RefundDetail> baseObserver) {
        return getApiService().after_details_order(orderItemId, UserUtils.getInstance().getLoginBean().getSite()).compose(RxUtils.rxSchedulerHelper()).compose(RxUtils.handleResult()).subscribeWith(baseObserver);

    }

    public static Disposable deleteSalesAppByEntityId(int entityId, int orderId, int orderItemId, BaseObserver<DeleteSalesAppByEntityId> baseObserver) {
        return getApiService().deleteSalesAppByEntityId(UserUtils.getInstance().getLoginBean().getSite(), entityId, orderId, orderItemId).compose(RxUtils.rxSchedulerHelper()).compose(RxUtils.handleResult()).subscribeWith(baseObserver);

    }

    public static Disposable findPageByCSIdList(int pn, int id, String startTime, String endTime, BaseObserver<PageByCSIdList> baseObserver) {
        return getApiService().findPageByCSIdList(UserUtils.getInstance().getLoginBean().getSite(), 0,
                id, pn, 10, startTime, endTime)
                .compose(RxUtils.rxSchedulerHelper()).
                        compose(RxUtils.handleResult()).subscribeWith(baseObserver);
    }

    public static Disposable addUserCard(Map<String, Object> map, BaseObserver<Integer> baseObserver) {
        return getApiService().addUserCard(jsonData(map)).compose(RxUtils.rxSchedulerHelper()).compose(RxUtils.handleResult()).subscribeWith(baseObserver);
    }

    public static Disposable bankAccount(Map<String, Object> map, BaseObserver<Bank> baseObserver) {
        return getApiService().bankAccount(jsonData(map)).compose(RxUtils.rxSchedulerHelper()).compose(RxUtils.handleResult()).subscribeWith(baseObserver);
    }

    public static Disposable selectBankInfoList(Map<String, Object> map, BaseObserver<List<BankList>> baseObserver) {
        return getApiService().selectBankInfoList(jsonData(map)).compose(RxUtils.rxSchedulerHelper()).compose(RxUtils.handleResult()).subscribeWith(baseObserver);
    }

    public static Disposable addBankAccount(Map<String, Object> map, BaseObserver<Bank> baseObserver) {
        return getApiService().addBankAccount(jsonData(map)).compose(RxUtils.rxSchedulerHelper()).compose(RxUtils.handleResult()).subscribeWith(baseObserver);
    }

    public static Disposable cashWithdrawalFlow(Map<String, Object> map, BaseObserver<Integer> baseObserver) {
        return getApiService().cashWithdrawalFlow(jsonData(map)).compose(RxUtils.rxSchedulerHelper()).compose(RxUtils.handleResult()).subscribeWith(baseObserver);
    }

    public static Disposable findCashWithdrawalFlowList(Map<String, Object> map, BaseObserver<WithdrawDetalis> baseObserver) {
        return getApiService().findCashWithdrawalFlowList(jsonData(map)).compose(RxUtils.rxSchedulerHelper()).compose(RxUtils.handleResult()).subscribeWith(baseObserver);
    }

    public static Disposable customer_refreshInfo(int entityId, int loginUserId, BaseObserver<UserInfo> callback) {
        return getApiService().customer_refreshInfo(UserUtils.getInstance().getLoginBean().getSite(), 1, entityId, loginUserId, GeneralUtils.getAndroidId()).compose(RxUtils.rxSchedulerHelper()).compose(RxUtils.handleResult()).subscribeWith(callback);
    }

    public static Disposable pushMessageLogout(int userType, String phoneNumber, BaseObserver<Object> callback) {
        return getApiService().pushMessageLogout(UserUtils.getInstance().getLoginBean().getSite(), userType, phoneNumber).compose(RxUtils.rxSchedulerHelper()).compose(RxUtils.handleResult()).subscribeWith(callback);
    }

    public static Disposable downList(Map<String, Object> map, BaseObserver<DropOuting> callback) {
        return getApiService().downList(jsonData(map)).compose(RxUtils.rxSchedulerHelper()).compose(RxUtils.handleResult()).subscribeWith(callback);
    }

    public static Disposable followList(Map<String, Object> map, BaseObserver<DropOuting> callback) {
        return getApiService().followList(jsonData(map)).compose(RxUtils.rxSchedulerHelper()).compose(RxUtils.handleResult()).subscribeWith(callback);
    }

    public static Disposable getDriverLocation(String orderNo, String driverTel, int customerAddrId, BaseObserver<DriverLocation> callback) {
        return getApiService().getDriverLocation(UserUtils.getInstance().getLoginBean().getSite(), orderNo, driverTel, customerAddrId).compose(RxUtils.rxSchedulerHelper()).compose(RxUtils.handleResult()).subscribeWith(callback);
    }

    public static Disposable getDegreeOfSatisfaction(int driverId, String extOrderId, BaseObserver<DegreeOfSatisfaction> callback) {
        return getApiService().getDegreeOfSatisfaction(UserUtils.getInstance().getLoginBean().getSite(), driverId, extOrderId).compose(RxUtils.rxSchedulerHelper()).compose(RxUtils.handleResult()).subscribeWith(callback);

    }

    public static Disposable internationalWaters(Map<String, Object> map, BaseObserver<Integer> callback) {
        return getApiService().internationalWaters(jsonData(map)).compose(RxUtils.rxSchedulerHelper()).compose(RxUtils.handleResult()).subscribeWith(callback);
    }

    public static Disposable collect_products(Map<String, Object> map, BaseObserver<GoodsInfo> callback) {
        return getApiService().collect_products(jsonData(map)).compose(RxUtils.rxSchedulerHelper()).compose(RxUtils.handleResult()).subscribeWith(callback);
    }

    public static Disposable collect_stores(Map<String, Object> map, BaseObserver<StoreInfo> callback) {
        return getApiService().collect_stores(jsonData(map)).compose(RxUtils.rxSchedulerHelper()).compose(RxUtils.handleResult()).subscribeWith(callback);
    }

    public static Disposable order_pageList(Map<String, Object> map, BaseObserver<OrderList> callback) {
        return getApiService().order_pageList(jsonData(map)).compose(RxUtils.rxSchedulerHelper()).compose(RxUtils.handleResult()).subscribeWith(callback);
    }

    public static Disposable findOne(Map<String, Object> map, BaseObserver<OrderDetail> callback) {
        return getApiService().findOne(jsonData(map)).compose(RxUtils.rxSchedulerHelper()).compose(RxUtils.handleResult()).subscribeWith(callback);
    }

    public static Disposable isApplyAfterSales(Map<String, Object> map, BaseObserver<Boolean> callback) {
        return getApiService().isApplyAfterSales(jsonData(map)).compose(RxUtils.rxSchedulerHelper()).compose(RxUtils.handleResult()).subscribeWith(callback);
    }

    public static Disposable coupons_findByUser(Map<String, Object> map, BaseObserver<DiscountCoupon> callback) {
        return getApiService().coupons_findByUser(jsonData(map)).compose(RxUtils.rxSchedulerHelper()).compose(RxUtils.handleResult()).subscribeWith(callback);
    }

    public static Disposable call_list(Map<String, Object> map, BaseObserver<BaiFangHistory> callback) {
        return getApiService().call_list(jsonData(map)).compose(RxUtils.rxSchedulerHelper()).compose(RxUtils.handleResult()).subscribeWith(callback);
    }

    public static Disposable call_insert(Map<String, Object> map, BaseObserver<Integer> callback) {
        return getApiService().call_insert(jsonData(map)).compose(RxUtils.rxSchedulerHelper()).compose(RxUtils.handleResult()).subscribeWith(callback);
    }

    public static Disposable amountAnalysisRecords(Map<String, Object> map, BaseObserver<ToMakeMoney> callback) {
        return getApiService().amountAnalysisRecords(jsonData(map)).compose(RxUtils.rxSchedulerHelper()).compose(RxUtils.handleResult()).subscribeWith(callback);
    }

    public static Disposable myCustomerList(Map<String, Object> map, BaseObserver<InvitationHistory> callback) {
        return getApiService().myCustomerList(jsonData(map)).compose(RxUtils.rxSchedulerHelper()).compose(RxUtils.handleResult()).subscribeWith(callback);
    }

    public static Disposable earning_info(Map<String, Object> map, BaseObserver<EarningsHistory> callback) {
        return getApiService().earning_info(jsonData(map)).compose(RxUtils.rxSchedulerHelper()).compose(RxUtils.handleResult()).subscribeWith(callback);
    }

    public static Disposable customer_hotelMap(Map<String, Object> map, BaseObserver<ClientSeachInfo> callback) {
        return getApiService().customer_hotelMap(jsonData(map)).compose(RxUtils.rxSchedulerHelper()).compose(RxUtils.handleResult()).subscribeWith(callback);
    }

    public static Disposable hotelMap(Map<String, Object> map, BaseObserver<List<ClientInfo>> callback) {
        return getApiService().hotelMap(jsonData(map)).compose(RxUtils.rxSchedulerHelper()).compose(RxUtils.handleResult()).subscribeWith(callback);
    }

    public static Disposable verifyList(Map<String, Object> map, BaseObserver<PartnerCheck> callback) {
        return getApiService().verifyList(jsonData(map)).compose(RxUtils.rxSchedulerHelper()).compose(RxUtils.handleResult()).subscribeWith(callback);
    }

    public static Disposable myCountMoney(Map<String, Object> map, BaseObserver<MyCountMoney> callback) {
        return getApiService().myCountMoney(jsonData(map)).compose(RxUtils.rxSchedulerHelper()).compose(RxUtils.handleResult()).subscribeWith(callback);
    }

    public static Disposable verifyDetail(Map<String, Object> map, BaseObserver<PartnerCheckDetails> callback) {
        return getApiService().verifyDetail(jsonData(map)).compose(RxUtils.rxSchedulerHelper()).compose(RxUtils.handleResult()).subscribeWith(callback);
    }

    public static Disposable clickVerify(Map<String, Object> map, BaseObserver<Integer> callback) {
        return getApiService().clickVerify(jsonData(map)).compose(RxUtils.rxSchedulerHelper()).compose(RxUtils.handleResult()).subscribeWith(callback);
    }

    public static Disposable managerList(Map<String, Object> map, BaseObserver<SettingPerson> callback) {
        return getApiService().managerList(jsonData(map)).compose(RxUtils.rxSchedulerHelper()).compose(RxUtils.handleResult()).subscribeWith(callback);
    }

    public static Disposable client_details(Map<String, Object> map, BaseObserver<ClientDetails> callback) {
        return getApiService().client_details(jsonData(map)).compose(RxUtils.rxSchedulerHelper()).compose(RxUtils.handleResult()).subscribeWith(callback);
    }

    public static Disposable userRelations_managerList(Map<String, Object> map, BaseObserver<SelectPerson> callback) {
        return getApiService().userRelations_managerList(jsonData(map)).compose(RxUtils.rxSchedulerHelper()).compose(RxUtils.handleResult()).subscribeWith(callback);
    }

    public static Disposable userRelations_setManager(Map<String, Object> map, BaseObserver<Integer> callback) {
        return getApiService().userRelations_setManager(jsonData(map)).compose(RxUtils.rxSchedulerHelper()).compose(RxUtils.handleResult()).subscribeWith(callback);
    }

    public static Disposable modifyAvatarUrl(Map<String, Object> map, BaseObserver<Integer> callback) {
        return getApiService().modifyAvatarUrl(jsonData(map)).compose(RxUtils.rxSchedulerHelper()).compose(RxUtils.handleResult()).subscribeWith(callback);
    }

    public static Disposable worship(Map<String, Object> map, BaseObserver<Integer> callback) {
        return getApiService().worship(jsonData(map)).compose(RxUtils.rxSchedulerHelper()).compose(RxUtils.handleResult()).subscribeWith(callback);
    }

    public static Disposable teamOverView_day(Map<String, Object> map, BaseObserver<TeamOverView> callback) {
        return getApiService().teamOverView_day(jsonData(map)).compose(RxUtils.rxSchedulerHelper()).compose(RxUtils.handleResult()).subscribeWith(callback);
    }

    public static Disposable teamOverView_month(Map<String, Object> map, BaseObserver<TeamOverView> callback) {
        return getApiService().teamOverView_month(jsonData(map)).compose(RxUtils.rxSchedulerHelper()).compose(RxUtils.handleResult()).subscribeWith(callback);
    }

    public static Disposable teamOverView_all(Map<String, Object> map, BaseObserver<TeamOverView> callback) {
        return getApiService().teamOverView_all(jsonData(map)).compose(RxUtils.rxSchedulerHelper()).compose(RxUtils.handleResult()).subscribeWith(callback);
    }

    public static Disposable homeData(Map<String, Object> map, BaseObserver<HomeData> callback) {
        return getApiService().homeData(jsonData(map)).compose(RxUtils.rxSchedulerHelper()).compose(RxUtils.handleResult()).subscribeWith(callback);
    }
    public static Disposable ironHomeData(Map<String, Object> map, BaseObserver<IronHomeData> callback) {
        return getApiService().ironHomeData(jsonData(map)).compose(RxUtils.rxSchedulerHelper()).compose(RxUtils.handleResult()).subscribeWith(callback);
    }
    public static Disposable ironHomeTopData(Map<String, Object> map, BaseObserver<IronHomeTopData> callback) {
        return getApiService().ironHomeTopData(jsonData(map)).compose(RxUtils.rxSchedulerHelper()).compose(RxUtils.handleResult()).subscribeWith(callback);
    }
    public static Disposable newhomeData(Map<String, Object> map, BaseObserver<NewHomeData> callback) {
        return getApiService().newhomeData(jsonData(map)).compose(RxUtils.rxSchedulerHelper()).compose(RxUtils.handleResult()).subscribeWith(callback);
    }

    public static Disposable ironStatisticsDetails(Map<String, Object> map, BaseObserver<IronStatisticsDetails> callback) {
        return getApiService().ironStatisticsDetails(jsonData(map)).compose(RxUtils.rxSchedulerHelper()).compose(RxUtils.handleResult()).subscribeWith(callback);
    }
    public static Disposable homeDataDetails(Map<String, Object> map, BaseObserver<HomeDataDetails> callback) {
        return getApiService().homeDataDetails(jsonData(map)).compose(RxUtils.rxSchedulerHelper()).compose(RxUtils.handleResult()).subscribeWith(callback);
    }

    public static Disposable myTeamPartnerList(Map<String, Object> map, BaseObserver<MyTeam> callback) {
        return getApiService().myTeamPartnerList(jsonData(map)).compose(RxUtils.rxSchedulerHelper()).compose(RxUtils.handleResult()).subscribeWith(callback);
    }

    public static Disposable memberList(Map<String, Object> map, BaseObserver<List<NewMyTeam>> callback) {
        return getApiService().memberList(jsonData(map)).compose(RxUtils.rxSchedulerHelper()).compose(RxUtils.handleResult()).subscribeWith(callback);
    }

    public static Disposable teamCity(Map<String, Object> map, BaseObserver<List<NewMyTeam>> callback) {
        return getApiService().teamCity(jsonData(map)).compose(RxUtils.rxSchedulerHelper()).compose(RxUtils.handleResult()).subscribeWith(callback);
    }

    public static Disposable version_check(Map<String, Object> map, BaseObserver<AppVersion> callback) {
        return getApiService().version_check(jsonData(map)).compose(RxUtils.rxSchedulerHelper()).compose(RxUtils.handleResult()).subscribeWith(callback);
    }

    public static Disposable myTeamPartnerSelectList(Map<String, Object> map, BaseObserver<List<String>> callback) {
        return getApiService().myTeamPartnerSelectList(jsonData(map)).compose(RxUtils.rxSchedulerHelper()).compose(RxUtils.handleResult()).subscribeWith(callback);
    }

    public static Disposable distinctList(Map<String, Object> map, BaseObserver<DistinctList> callback) {
        return getApiService().distinctList(jsonData(map)).compose(RxUtils.rxSchedulerHelper()).compose(RxUtils.handleResult()).subscribeWith(callback);
    }

    public static Disposable parentList(Map<String, Object> map, BaseObserver<ParentList> callback) {
        return getApiService().parentList(jsonData(map)).compose(RxUtils.rxSchedulerHelper()).compose(RxUtils.handleResult()).subscribeWith(callback);
    }

    public static MultipartBody.Part getMultipartBody_part(String fileName, byte[] content) {
        // 创建 RequestBody，用于封装构建RequestBody
        RequestBody requestFile = RequestBody.create(FORM, content);
        return MultipartBody.Part.createFormData("image", fileName, requestFile);
    }

//    public static RequestBody  jsonData(Map<String ,Object> map){//
//        map.put("versionType", "Android");
//        map.put("versionCode", String.valueOf(GeneralUtils.getVersionCode()));
//        map.put("versionName",GeneralUtils.getVersionName());
//        map.put("uniqueId",GeneralUtils.getAndroidId());
//        return RequestBody.create(JSON, new Gson().toJson(map));
//    }

    public static RequestBody jsonData(Map<String, Object> map) {
        return PostJsonBody.create(new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create()
                .toJson(map));
    }


    /****************创客3.0********************/
    public static Observable<AfterSaleInfoData> getafterSalesTask(Map<String, Object> map) {
        return getApiService().getafterSalesTask(jsonData(map)).compose(RxUtils.rxSchedulerHelper()).compose(RxUtils.handleResult());
    }

}
