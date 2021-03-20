package com.tdjpartner.viewmodel;

import com.tdjpartner.common.RequestPresenter;
import com.tdjpartner.model.AfterSaleInfoData;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;

/**
 * Created by LFM on 2021/3/20.
 */
public class AfterSalesViewModel extends IronViewModel<AfterSaleInfoData> {
    @Override
    Observable<AfterSaleInfoData> loadData() {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", 258787);
        map.put("tab", 0);

        return RequestPresenter.getafterSalesTask(map);
    }
}
