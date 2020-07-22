package com.tdjpartner.mvp.model;


import com.tdjpartner.common.RequestPresenter;
import com.tdjpartner.http.BaseResponse;
import com.tdjpartner.model.BankList;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;

/**
 * 登入数据
 * Created by wanyh on 2017/9/11.
 */

public class LoginModel implements IModel {
    public Observable<BaseResponse<List<BankList>>> login(Map<String, Object> map) {


        return RequestPresenter.getApiService().selectBankInfoList(RequestPresenter.jsonData(map));

    }

}
