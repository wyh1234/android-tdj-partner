package com.tdjpartner.viewmodel;

import com.tdjpartner.base.BaseViewModel;
import com.tdjpartner.common.RequestPresenter;
import com.tdjpartner.model.IronStatisticsDetails;

import java.util.Map;

import io.reactivex.Observable;

/**
 * Created by LFM on 2021/3/20.
 */
public class StatisticsDetailsViewModel extends BaseViewModel<IronStatisticsDetails> {
    @Override
    protected Observable<IronStatisticsDetails> loadData(Map<String, Object> map) {
        return RequestPresenter.ironStatisticsDetails(map);
    }
}
