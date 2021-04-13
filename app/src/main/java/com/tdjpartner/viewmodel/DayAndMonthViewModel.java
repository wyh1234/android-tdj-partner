package com.tdjpartner.viewmodel;

import com.tdjpartner.base.BaseViewModel;
import com.tdjpartner.common.RequestPresenter;
import com.tdjpartner.model.AfterSaleInfoData;
import com.tdjpartner.model.IronDayAndMonthData;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.annotations.Nullable;

/**
 * Created by LFM on 2021/4/12.
 */
public class DayAndMonthViewModel extends BaseViewModel<IronDayAndMonthData> {
    @Override
    protected Observable<IronDayAndMonthData> loadData(@Nullable Map<String, Object> map) {
        return RequestPresenter.ironDayAndMonthData(map);
    }
}
