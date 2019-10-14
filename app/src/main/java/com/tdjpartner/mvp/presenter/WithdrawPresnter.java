package com.tdjpartner.mvp.presenter;

import com.tdjpartner.base.BasePresenter;
import com.tdjpartner.common.RequestPresenter;
import com.tdjpartner.http.BaseObserver;
import com.tdjpartner.model.Bank;
import com.tdjpartner.mvp.model.Model;
import com.tdjpartner.ui.activity.WithdrawActivity;
import com.tdjpartner.utils.GeneralUtils;

import java.util.Map;

public class WithdrawPresnter extends BasePresenter<Model, WithdrawActivity> {
    @Override
    public Model loadModel() {
        return null;
    }
    public void bankAccount(Map<String,Object> map){
        getIView().addSubscribe(RequestPresenter.bankAccount(map, new BaseObserver<Bank>(getIView().getContext(), true) {
            @Override
            protected void onSuccess(Bank bank) {
                getIView().bankAccountSuccess(bank);


            }

            @Override
            protected void onFailed(Throwable e) {

            }
        }));

    }
    public void cashWithdrawalFlow(Map<String,Object> map){
        getIView().addSubscribe(RequestPresenter.cashWithdrawalFlow(map, new BaseObserver<Integer>(getIView().getContext(), true) {
            @Override
            protected void onSuccess(Integer data) {
                if (data==1){
                    GeneralUtils.showToastshort("申请提现成功！请等待平台审核");
                    getIView().cashWithdrawalFlowSuccess();
                }



            }

            @Override
            protected void onFailed(Throwable e) {

            }
        }));

    }
}
