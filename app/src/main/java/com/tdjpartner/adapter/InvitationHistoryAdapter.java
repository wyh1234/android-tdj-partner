package com.tdjpartner.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tdjpartner.model.InvitationHistory;

import java.util.List;

public class InvitationHistoryAdapter extends BaseQuickAdapter<InvitationHistory, BaseViewHolder> {
    public InvitationHistoryAdapter(int layoutResId, @Nullable List<InvitationHistory> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, InvitationHistory invitationHistory) {

    }
}
