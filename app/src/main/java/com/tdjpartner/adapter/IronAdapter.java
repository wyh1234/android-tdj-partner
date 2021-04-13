package com.tdjpartner.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LFM on 2021/3/12.
 */
public class IronAdapter extends ArrayAdapter<List<String>> {

    public interface InitView {
        void initView(List<String> data, View convertView);
    }

    public static final class Builder {
        private  int resource;
        private View.OnClickListener onClickListener;
        private InitView initView;
        private List<Integer> childIds = new ArrayList<>();

        public Builder setResource(int resource) {
            this.resource = resource;
            return this;
        }

        public Builder setOnClickListener(View.OnClickListener onClickListener) {
            this.onClickListener = onClickListener;
            return this;
        }

        public Builder setInitView(InitView initView) {
            this.initView = initView;
            return this;
        }

        public Builder addChildId(int... id){
            for (int n : id) {
                childIds.add(n);
            }
            return this;
        }

        public IronAdapter build(@NonNull Context context){
            return new IronAdapter(context, resource, initView, childIds, onClickListener);
        }
    }

    private final int mResource;
    private final View.OnClickListener mOnClickListener;
    private final InitView mInitView;
    private final List<Integer> mChildIds;

    private IronAdapter(@NonNull Context context, int resource, InitView initView, List<Integer> childIds, View.OnClickListener onClickListener) {
        super(context, resource);
        mResource = resource;
        mChildIds = childIds;
        mInitView = initView;
        mOnClickListener = onClickListener;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        System.out.println("~~" + getClass().getSimpleName() + ".getView~~");

        convertView = LayoutInflater.from(getContext()).inflate(mResource, parent, false);

        if(mInitView != null) mInitView.initView(getItem(position), convertView);

        if (!mChildIds.isEmpty() && mOnClickListener != null) {
            for (int id : mChildIds) {
                convertView.findViewById(id).setOnClickListener(mOnClickListener);
            }
        }

        return convertView;
    }
}
