package com.bwei.ssp.home_work.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.bwei.ssp.home_work.Fragment.Shouye.Home_pagActivity;
import com.bwei.ssp.home_work.Fragment.Shouye.SearchActivity;
import com.bwei.ssp.home_work.Fragment.Shouye.Souye_Madapter.Liushi_Adapter;
import com.bwei.ssp.home_work.Fragment.Shouye.Souye_Madapter.Shouye_adapter;
import com.bwei.ssp.home_work.Fragment.Shouye.bean.Grild_Bean;
import com.bwei.ssp.home_work.Fragment.Shouye.bean.Shouye_Bean;
import com.bwei.ssp.home_work.Fragment.Shouye.bean.Shouye_Xbanner_Bean;
import com.bwei.ssp.home_work.Fragment.Shouye.presenter.Presenter;
import com.bwei.ssp.home_work.R;
import com.stx.xhb.xbanner.XBanner;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by lenovo on 2017/12/6.
 */

public class shouye extends Fragment implements com.bwei.ssp.home_work.Fragment.Shouye.view.View {

    @InjectView(R.id.syis)
    ImageButton sys;
    @InjectView(R.id.search)
    EditText search;
    @InjectView(R.id.xbanner)
    XBanner xbanner;
    @InjectView(R.id.rlv)
    RecyclerView rlv;
    @InjectView(R.id.pmd)
    ViewFlipper pmd;
    @InjectView(R.id.tv_hour)
    TextView tv_hour;
    @InjectView(R.id.tv_minute)
    TextView tv_minute;
    @InjectView(R.id.tv_second)
    TextView tv_second;
    @InjectView(R.id.ll_xsqg)
    LinearLayout llXsqg;
    @InjectView(R.id.xlv)
    RecyclerView xlv;
    private Presenter p;
    private List<Shouye_Xbanner_Bean.DataBean> data;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = View.inflate(getActivity(), R.layout.shouye, null);
        ButterKnife.inject(this, v);
        p = new Presenter(this);
        p.getJs();
        p.getXjs();
        rlv.setLayoutManager(new GridLayoutManager(getContext(), 5));
        List<Grild_Bean> list = new ArrayList<>();
        list.add(new Grild_Bean("天猫", R.drawable.g1));
        list.add(new Grild_Bean("聚划算", R.drawable.g2));
        list.add(new Grild_Bean("天猫国际", R.drawable.g3));
        list.add(new Grild_Bean("外卖", R.drawable.g4));
        list.add(new Grild_Bean("天猫超市", R.drawable.g5));
        list.add(new Grild_Bean("充值中心", R.drawable.g6));
        list.add(new Grild_Bean("飞猪旅行", R.drawable.g7));
        list.add(new Grild_Bean("领金币", R.drawable.g8));
        list.add(new Grild_Bean("拍卖", R.drawable.g9));
        list.add(new Grild_Bean("分类", R.drawable.g10));
        Shouye_adapter shouye_adapter = new Shouye_adapter(getContext(), list);
        rlv.setAdapter(shouye_adapter);
        pmd.addView(View.inflate(getActivity(), R.layout.pmd, null));
        return v;
    }

    @Override
    public void getData(Shouye_Bean bean) {
    }

    @Override
    public void getBannerData(Shouye_Xbanner_Bean bean) {
        final List<String> Image = new ArrayList<>();
        List<String> tit = new ArrayList<>();
        data = bean.getData();
        for (int i = 0; i < data.size(); i++) {
            Image.add(data.get(i).getIcon());
            tit.add(data.get(i).getTitle());

        }

        xbanner.setData(Image, tit);
        xbanner.setmAdapter(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, View view, int position) {
                Glide.with(getActivity()).load(Image.get(position)).into((ImageView) view);
            }
        });

        xbanner.setOnItemClickListener(new XBanner.OnItemClickListener() {
            @Override
            public void onItemClick(XBanner banner, int position) {
                int type = data.get(position).getType();
                if (type == 0) {
                    String url = data.get(position).getUrl();
                    Intent intent = new Intent(getActivity(), Home_pagActivity.class);
                    intent.putExtra("url", url);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(getActivity(), Home_pagActivity.class);
                    intent.putExtra("url", "https://lmnz.tmall.com/p/rd584898.htm?ali_trackid=17_c536f8fa218a5101cedb52728bcbac41&spm=a231o.7076277/a.19985496532.1");
                    startActivity(intent);
                }
            }
        });

        xlv.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        Liushi_Adapter liushi_adapter = new Liushi_Adapter(getActivity(), bean.getTuijian().getList());
        xlv.setAdapter(liushi_adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        xbanner.startAutoPlay();
    }
    @Override
    public void onStop() {
        super.onStop();
        xbanner.stopAutoPlay();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 001) {
            if (data != null) {
                Bundle extras = data.getExtras();
                if (extras == null) {
                    return;
                }
                if (extras.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String string = extras.getString(CodeUtils.RESULT_STRING);
                    Toast.makeText(getContext(), "解析结果:" + string, Toast.LENGTH_LONG).show();
                } else if (extras.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(getActivity(), "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }

        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.syis, R.id.search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.syis:
                Intent intent = new Intent(getActivity(), CaptureActivity.class);
                startActivityForResult(intent, 001);
                break;
            case R.id.search:
                Intent intent1 = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent1);
                break;
        }
    }
}
