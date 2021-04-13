package com.tdjpartner.viewmodel;

import com.tdjpartner.base.BaseViewModel;
import com.tdjpartner.common.RequestPresenter;
import com.tdjpartner.model.AfterSaleInfoData;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.annotations.Nullable;

/**
 * Created by LFM on 2021/3/20.
 */
public class AfterSalesViewModel extends BaseViewModel<AfterSaleInfoData> {
    @Override
    protected Observable<AfterSaleInfoData> loadData(@Nullable Map<String, Object> map) {
        return RequestPresenter.getafterSalesTask(map);
    }
}
