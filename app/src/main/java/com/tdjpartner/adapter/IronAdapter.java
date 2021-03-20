package com.tdjpartner.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.tdjpartner.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LFM on 2021/3/12.
 */
public class IronAdapter extends ArrayAdapter<List<String>> {

    public interface IView{
        void initView(List<String> data, View convertView);
    }

    public static final class Builder {
        private  int resource;
        private View.OnClickListener onClickListener;
        private IView iView;
        private List<Integer> childIds = new ArrayList<>();

        public Builder setResource(int resource) {
            this.resource = resource;
            return this;
        }

        public Builder setOnClickListener(View.OnClickListener onClickListener) {
            this.onClickListener = onClickListener;
            return this;
        }

        public Builder setIView(IView iView) {
            this.iView = iView;
            return this;
        }

        public Builder addChildId(int... id){
            for (int n : id) {
                childIds.add(n);
            }
            return this;
        }

        public IronAdapter build(@NonNull Context context){
            return new IronAdapter(context, resource, iView, childIds, onClickListener);
        }
    }

    private final int mResource;
    private final View.OnClickListener mOnClickListener;
    private final IView mIView;
    private final List<Integer> mChildIds;

    private IronAdapter(@NonNull Context context, int resource, IView iView, List<Integer> childIds, View.OnClickListener onClickListener) {
        super(context, resource);
        mResource = resource;
        mChildIds = childIds;
        mIView = iView;
        mOnClickListener = onClickListener;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        convertView = LayoutInflater.from(getContext()).inflate(mResource, parent, false);

        if(mIView != null) mIView.initView(getItem(position), convertView);

        if (!mChildIds.isEmpty() && mOnClickListener != null) {
            for (int id : mChildIds) {
                convertView.findViewById(id).setOnClickListener(mOnClickListener);
            }
        }

        return convertView;
    }
}
