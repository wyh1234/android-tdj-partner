package com.tdjpartner.viewmodel;

import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.ViewModel;
import android.net.ParseException;

import com.apkfuns.logutils.LogUtils;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.tdjpartner.common.RequestPresenter;
import com.tdjpartner.http.ApiException;
import com.tdjpartner.utils.GeneralUtils;

import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;

import java.io.NotSerializableException;
import java.lang.reflect.ParameterizedType;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.annotations.Nullable;
import io.reactivex.disposables.CompositeDisposable;
import retrofit2.HttpException;

/**
 * Created by LFM on 2021/4/15.
 */
public class NetworkViewModel extends ViewModel {

    private Map<Class, MediatorLiveData> register;
    private Map<TypeToken, MediatorLiveData> registerForTypeToken;
    private CompositeDisposable compositeDisposable;

    public <D> MediatorLiveData<D> loadingWithNewLiveData(Class<D> dClass, @Nullable Map<String, Object> map) {
        loadData(dClass, map);
        MediatorLiveData<D> mediatorLiveData = new MediatorLiveData<>();
        getRegister().put(dClass, mediatorLiveData); //每次都使用新实例替换注册器中的旧实例
        return mediatorLiveData;
    }

    public MediatorLiveData loadingWithNewLiveData(TypeToken typeToken, @Nullable Map<String, Object> map) {
        loadData(typeToken, map);
        MediatorLiveData mediatorLiveData = new MediatorLiveData();
        getRegisterForTypeToken().put(typeToken, mediatorLiveData); //每次都使用新实例替换注册器中的旧实例
        return mediatorLiveData;
    }

    public <D> MediatorLiveData<D> loadingWithNewLiveData(Class<D> dClass, String fileName, byte[] bytes) {

        getCompositeDisposable().add(RequestPresenter.uploading(fileName, bytes).subscribe(this::onNext, this::onError));

        MediatorLiveData<D> mediatorLiveData = new MediatorLiveData<>();
        getRegister().put(dClass, mediatorLiveData); //每次都使用新实例替换注册器中的旧实例
        return mediatorLiveData;
    }

    public <D> MediatorLiveData<D> loading(Class<D> dClass, @Nullable Map<String, Object> map) {
        if (getRegister().containsKey(dClass)) {
            if (getRegister().get(dClass).hasObservers()) loadData(dClass, map);
            return getRegister().get(dClass);
        } else {
            return loadingWithNewLiveData(dClass, map);
        }
    }

    private <D> void loadData(TypeToken typeToken, @Nullable Map<String, Object> map) {
        getCompositeDisposable().add(RequestPresenter.loading(typeToken, map).subscribe(this::onNext, this::onError));
    }

    private <D> void loadData(Class<D> dClass, @Nullable Map<String, Object> map) {
        getCompositeDisposable().add(RequestPresenter.loading(dClass, map).subscribe(this::onNext, this::onError));
    }

    @Override
    protected void onCleared() {
        System.out.println("~~" + getClass().getSimpleName() + ".onCleared~~");
        if (compositeDisposable != null) compositeDisposable.dispose();
        register.clear();
    }

    private CompositeDisposable getCompositeDisposable() {
        if (compositeDisposable == null) compositeDisposable = new CompositeDisposable();
        return compositeDisposable;
    }

    public Map<Class, MediatorLiveData> getRegister() {
        if (register == null) register = new HashMap<>();
        return register;
    }

    public Map<TypeToken, MediatorLiveData> getRegisterForTypeToken() {
        if (registerForTypeToken == null) registerForTypeToken = new HashMap<>();
        return registerForTypeToken;
    }

    public void onError(Throwable e) {
        LogUtils.e(e);

        if (e instanceof ApiException) {
            //处理API错误
            ApiException apiException = (ApiException) e;
            LogUtils.e("[" + apiException.getCode() + "]" + apiException.getMsg());

            if (apiException.getCode() == 4 || apiException.getCode() == 901) {
                GeneralUtils.showToastshort(apiException.getMsg());
//                if (ClickCheckedUtil.onClickChecked(1000))
//                    EventBus.getDefault().post(new LoginLoseEfficacyEvent());
                return;
            } else if (apiException.getCode() == 0) {
//                onNext(null);
            } else {
                GeneralUtils.showToastshort(apiException.getMsg());
            }

        } else if (e instanceof HttpException) {
            GeneralUtils.showToastshort("网络错误");
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof JsonSyntaxException
                || e instanceof JsonSerializer
                || e instanceof NotSerializableException
                || e instanceof ParseException) {
            GeneralUtils.showToastshort("数据解析错误");
        } else if (e instanceof ClassCastException) {
            GeneralUtils.showToastshort("类型转换错误");
        } else if (e instanceof ConnectException) {
            GeneralUtils.showToastshort("连接失败");
        } else if (e instanceof javax.net.ssl.SSLHandshakeException) {
            GeneralUtils.showToastshort("证书验证失败");
        } else if (e instanceof ConnectTimeoutException) {
            GeneralUtils.showToastshort("连接超时");
        } else if (e instanceof java.net.SocketTimeoutException) {
            GeneralUtils.showToastshort("连接超时");
        } else if (e instanceof UnknownHostException) {
            GeneralUtils.showToastshort("无法解析该域名");
        } else if (e instanceof NullPointerException) {
            GeneralUtils.showToastshort("NullPointerException");
        } else {
            GeneralUtils.showToastshort("未知错误");
        }
    }

    public <T> void onNext(T t) {

        if (getRegister().containsKey(t.getClass()) && getRegister().get(t.getClass()).hasObservers()) {
            getRegister().get(t.getClass()).postValue(t);
        } else if (!getRegisterForTypeToken().isEmpty()) {

            boolean isSuccess = false;
            for (TypeToken typeToken : getRegisterForTypeToken().keySet()) {
                if (t.getClass().equals(typeToken.getRawType())) {
                    //Collection
                    if (Collection.class.isAssignableFrom(t.getClass())) {
                        Collection collection = (Collection) t;
                        if (!collection.isEmpty()) {
                            ParameterizedType parameterized = (ParameterizedType) typeToken.getType();
                            if (collection.toArray()[0].getClass().equals(parameterized.getActualTypeArguments()[0])) {
                                getRegisterForTypeToken().get(typeToken).postValue(t);
                                isSuccess = true;
                                break;
                            } else {
                                continue;
                            }
                        }
                    }

                    //Map
//                    if (Collection.class.isAssignableFrom(t.getClass())) {
//                        Collection collection = (Collection) t;
//                        if (!collection.isEmpty()) {
//                            ParameterizedType parameterized = (ParameterizedType) typeToken.getType();
//                            if (collection.toArray()[0].getClass().equals(parameterized.getActualTypeArguments()[0])) {
//                                getRegisterForTypeToken().get(typeToken).postValue(t);
//                                isSuccess = true;
//                                break;
//                            } else {
//                                continue;
//                            }
//                        }
//                    }

                }
                break;
            }
            if (!isSuccess) GeneralUtils.showToastshort("操作失败，未知数据");

        } else {
            GeneralUtils.showToastshort("操作失败，未知数据");
        }
    }
}
