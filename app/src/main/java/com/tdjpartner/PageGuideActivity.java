package com.tdjpartner;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.tdjpartner.adapter.GuidePageAdapter;
import com.tdjpartner.base.BaseActivity;
import com.tdjpartner.mvp.presenter.IPresenter;
import com.tdjpartner.ui.activity.LoginActivity;
import com.tdjpartner.utils.cache.DataUtils;
import com.tdjpartner.utils.statusbar.Eyes;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/5/4.
 */

public class PageGuideActivity extends BaseActivity {
    @BindView(R.id.viewPager)
    ViewPager viewPager;
     List<View> pageViews;


    //导航页图片资源
    public int[] guides = new int[] { R.mipmap.guide1,
            R.mipmap.guide2, R.mipmap.guide3, R.mipmap.guide4};
    @Override
    protected IPresenter loadPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        pageViews = new ArrayList<View>();
        LayoutInflater inflat = LayoutInflater.from(this);
        View item = inflat.inflate(R.layout.pageguide, null);
        for (int i=0;i<guides.length;i++) {
            item = inflat.inflate(R.layout.pageguide, null);
            item.setBackgroundResource(guides[i]);
            pageViews.add(item);
        }
        //经过遍历，此时item是最后一个view，设置button
        ImageView imageview = (ImageView) item.findViewById(R.id.imageView);
        imageview.setVisibility(View.GONE);
        item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataUtils.getInstance().setFirstStartup(true);
                Intent intent = new Intent(PageGuideActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        viewPager.setAdapter(new GuidePageAdapter(pageViews));
    }


    @Override
    protected void initView() {
        Eyes.translucentStatusBar(this,true);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.page_guide_layout;
    }
}
