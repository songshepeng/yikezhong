package com.bwei.ssp.home_work.Fragment.Shouye.Search;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.bwei.ssp.home_work.Fragment.Shouye.Search.adapter.Mtadapter;
import com.bwei.ssp.home_work.Fragment.Shouye.Search.bean.Bean;
import com.bwei.ssp.home_work.Fragment.Shouye.Search.presenter.presenter;
import com.bwei.ssp.home_work.Fragment.Shouye.Search.view.view;
import com.bwei.ssp.home_work.R;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

public class SousuoActivity extends AppCompatActivity implements view {
    private XRecyclerView xlv;
    int page =1;
    private Mtadapter mtadapter;
    private List<Bean.DataBean> data;
    private com.bwei.ssp.home_work.Fragment.Shouye.Search.presenter.presenter presenter;
    private String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sousuo);
        xlv = (XRecyclerView) findViewById(R.id.xlv);
        Intent intent = getIntent();
        key = intent.getStringExtra("key");
       // Toast.makeText(SousuoActivity.this,""+key,Toast.LENGTH_LONG).show();
        presenter = new presenter(this);
        presenter.getJs(key,page);

    }

    @Override
    public void getData(Bean bean) {
        data = bean.getData();
        mtadapter = new Mtadapter(SousuoActivity.this, data, 1);
        xlv.setLayoutManager(new LinearLayoutManager(SousuoActivity.this));
        xlv.setAdapter(mtadapter);
        xlv.setFootView(View.inflate(SousuoActivity.this,R.layout.foot,null));
        //上拉加载下拉刷新
        xlv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page=1;
                data.clear();
                presenter.getJs(key,page);
                xlv.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                page=page++;
                presenter.getJs(key,page);
                xlv.loadMoreComplete();
            }
        });
    }
}
