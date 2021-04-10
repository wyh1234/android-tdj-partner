package com.tdjpartner.viewmodel;

import com.tdjpartner.common.RequestPresenter;
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
    Observable<IronStatisticsDetails> loadData() {
//        Map<String, Object> map = new HashMap<>();
//        map.put("userId", 258787);
//        map.put("tab", 0);

        Map<String, Object> map = new HashMap<>();
        map.put("userId", UserUtils.getInstance().getLoginBean().getLoginUserId());
        map.put("userId", 25716);
//        if (isDay) {
//            map.put("dayDate", GeneralUtils.getTimeFilter(date));
//        } else {
//            map.put("monthTime", GeneralUtils.getMonthFilter(date));
//        }
        map.put("dayDate", "2021-04");
        map.put("keyword", "");
        map.put("userType", 1);
        map.put("pn", 1);
        map.put("ps", 999);

//        return RequestPresenter.ironStatisticsDetails(map);
        return null;
    }
}
