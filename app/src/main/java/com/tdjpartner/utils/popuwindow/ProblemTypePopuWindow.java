package com.tdjpartner.utils.popuwindow;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tdjpartner.R;
import com.tdjpartner.adapter.ProblemTypeAdapter;
import com.tdjpartner.common.PublicCache;
import com.tdjpartner.model.AfterSalesType;
import com.tdjpartner.model.ProblemType;
import com.tdjpartner.utils.CustomerData;
import com.tdjpartner.utils.GridSpacingItemDecoration;

import java.util.ArrayList;
import java.util.List;

import razerdp.basepopup.BasePopupWindow;

public class ProblemTypePopuWindow extends BasePopupWindow implements BaseQuickAdapter.OnItemClickListener {
    private View popupView;
    private RecyclerView rv_recyclerView;
    private ProblemTypeAdapter problemTypeAdapter;
    private Button bt_cancel,bt_ok;
    private ProblemTypePopuWindowListener listener;
    private ProblemType problemType;

    public void setProblemTypePopuWindowListener(ProblemTypePopuWindowListener listener) {
        this.listener = listener;
    }
    public interface ProblemTypePopuWindowListener {
        void onProblemTypeOk(ProblemType problemType);
    }
    public ProblemTypePopuWindow(Context context) {
        super(context);
        rv_recyclerView=popupView.findViewById(R.id.rv_recyclerView);
        bt_cancel=popupView.findViewById(R.id.bt_cancel);
        bt_ok=popupView.findViewById(R.id.bt_ok);
        rv_recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        rv_recyclerView.addItemDecoration(new GridSpacingItemDecoration(3,28,false,13));
        problemTypeAdapter=new ProblemTypeAdapter(R.layout.after_problem_popu_item_layout,getList(PublicCache.getAfterSaleProblem()));
        rv_recyclerView.setAdapter(problemTypeAdapter);
        problemTypeAdapter.setOnItemClickListener(this);
        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dismiss();
            }

        });
        bt_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                listener.onProblemTypeOk(problemType);

            }
        });


    }

    @Override
    protected Animation initShowAnimation() {
        return null;
    }

    @Override
    public View getClickToDismissView() {
        return getPopupWindowView();
    }

    @Override
    public View onCreatePopupView() {
        popupView = createPopupById(R.layout.problem_type_popu_layout);
        return popupView;
    }

    @Override
    public View initAnimaView() {
        return findViewById(R.id.popup_anima);
    }
    private List<ProblemType> getList(CustomerData<Integer, String, String> customerData) {
        List<ProblemType> list = new ArrayList<>();
        for (int i = 0; i < customerData.size(); i++) {
            ProblemType problemType = new ProblemType();
            problemType.setTypeName(customerData.getValueAtIndex(i));
            problemType.setType(customerData.getKeyAtIndex(i));
            problemType.setFlag(false);
            list.add(problemType);
        }
        return list;
    }

    @Override
    public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        for (ProblemType problemType:(List<ProblemType>)baseQuickAdapter.getData()){
            problemType.setFlag(false);
        }
        ((ProblemType) baseQuickAdapter.getData().get(i)).setFlag(true);
        problemType=((ProblemType) baseQuickAdapter.getData().get(i));

        problemTypeAdapter.notifyDataSetChanged();
    }
}
