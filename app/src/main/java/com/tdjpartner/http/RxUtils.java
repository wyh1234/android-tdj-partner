package com.tdjpartner.http;

import com.apkfuns.logutils.LogUtils;

import io.reactivex.FlowableTransformer;
import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/6/13.
 */

public class RxUtils {
    /**
     * 统一线程处理
     *
     * @param <T> 指定的泛型类型
     * @return FlowableTransformer
     */
    public static <T> FlowableTransformer<T, T> rxFlSchedulerHelper() {
        return flowable -> flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 统一线程处理
     *
     * @param <T> 指定的泛型类型
     * @return ObservableTransformer
     */
    public static <T> ObservableTransformer<T, T> rxSchedulerHelper() {
        return observable -> observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 统一返回结果处理
     *
     * @param <T> 指定的泛型类型
     * @return ObservableTransformer
     */
    public static <T> ObservableTransformer<BaseResponse<T>, T> handleResult() {
        return httpResponseObservable ->
                httpResponseObservable.flatMap((Function<BaseResponse<T>, Observable<T>>) baseResponse -> {
                    if (baseResponse.isSuccess()) {
                        return createData(baseResponse);
                    } else {
                        //请求失败；
                        return Observable.error(new ApiException(baseResponse.getCode(), baseResponse.getMsg()));
                    }
                });
    }

    /**
     * 得到 Observable
     *
     * @param <T> 指定的泛型类型
     * @return Observable
     */
    private static <T> Observable<T> createData(final BaseResponse<T> baseResponse) {
        return Observable.create(emitter -> {
            try {
                if (baseResponse.getData() == null) {
                    emitter.onNext((T) baseResponse.getMsg());
                } else {
                    emitter.onNext(baseResponse.getData());
                }
                emitter.onComplete();
            } catch (Exception e) {
                LogUtils.e(baseResponse.getData());
                emitter.onError(e);
            }
        });
    }

    /**
     * 泛型转换工具方法 eg:object ==> map<String, String>
     *
     * @param object Object
     * @param <T>    转换得到的泛型对象
     * @return T
     */
    @SuppressWarnings("unchecked")
    public static <T> T cast(Object object) {
        return (T) object;
    }


    /**
     * 统一返回结果处理
     * BaseResponse
     *
     * @return ObservableTransformer
     */
    public static ObservableTransformer<BaseResponse, BaseResponse> handleResults() {
        return httpResponseObservable ->
                httpResponseObservable.flatMap((Function<BaseResponse, Observable<BaseResponse>>) baseResponse -> {
                    if (baseResponse.getCode() == 0
                            && baseResponse.getData() != null) {
                        return createDatas(baseResponse);
                    } else {
                        return Observable.error(new OtherException());
                    }
                });
    }


    /**
     * 得到 Observable
     * BaseResponse
     *
     * @return Observable
     */
    private static Observable<BaseResponse> createDatas(final BaseResponse baseResponse) {
        return Observable.create(emitter -> {
            try {
                emitter.onNext(baseResponse);
                emitter.onComplete();
            } catch (Exception e) {
                emitter.onError(e);
            }
        });
    }

}
