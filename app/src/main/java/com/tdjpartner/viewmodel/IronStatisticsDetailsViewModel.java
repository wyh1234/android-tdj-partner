package com.tdjpartner.viewmodel;

import com.tdjpartner.common.RequestPresenter;
import com.tdjpartner.http.BaseObserver;
import com.tdjpartner.model.AfterSaleInfoData;
import com.tdjpartner.model.IronStatisticsDetails;
import com.tdjpartner.utils.GeneralUtils;
import com.tdjpartner.utils.cache.UserUtils;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;

/**
 * Created by LFM on 2021/3/20.
 */
public class IronStatisticsDetailsViewModel extends IronViewModel<IronStatisticsDetails> {
    @Override
    Observable<IronStatisticsDetails> loadData(Map<String, Object> map) {
        return RequestPresenter.ironStatisticsDetails(map);
    }
}
