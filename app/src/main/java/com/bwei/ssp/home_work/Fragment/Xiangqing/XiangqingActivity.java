package com.bwei.ssp.home_work.Fragment.Xiangqing;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bwei.ssp.home_work.Fragment.Xiangqing.bean.Add_Bean;
import com.bwei.ssp.home_work.Fragment.Xiangqing.bean.Xiang_Bean;
import com.bwei.ssp.home_work.Fragment.Xiangqing.presenter.Xq_presenter;
import com.bwei.ssp.home_work.Fragment.Xiangqing.view.Xq_view;
import com.bwei.ssp.home_work.Okhttputils.GsonObjectCallback;
import com.bwei.ssp.home_work.Okhttputils.OkHttp3Utils;
import com.bwei.ssp.home_work.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.stx.xhb.xbanner.XBanner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import okhttp3.Call;

public class XiangqingActivity extends AppCompatActivity implements Xq_view {

    @InjectView(R.id.img)
    XBanner img;
    @InjectView(R.id.title)
    TextView title;
    @InjectView(R.id.subhead)
    TextView subhead;
    @InjectView(R.id.price)
    TextView price;
    @InjectView(R.id.newprice)
    TextView newprice;
    @InjectView(R.id.add)
    Button add;
    @InjectView(R.id.by)
    Button by;
    @InjectView(R.id.webb)
    WebView web;
    private int pid1;
    private int sellerid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiangqing);
        ButterKnife.inject(this);
        price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        Xq_presenter xq_presenter = new Xq_presenter(this);
        Intent intent = getIntent();
        int pid = intent.getIntExtra("pid", 1);
        xq_presenter.getJS(pid);
    }

    @Override
    public void getData(final Xiang_Bean bean) {
        Xiang_Bean.DataBean data = bean.getData();
        ImageLoaderConfiguration cf = ImageLoaderConfiguration.createDefault(XiangqingActivity.this);
        ImageLoader.getInstance().init(cf);
        int length = data.getImages().split("!").length;
        Log.e("length", length + "");
        final List<String> imgs = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            imgs.add(data.getImages().split("!")[i]);
        }
        img.setData(imgs, null);
        img.setmAdapter(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, View view, int position) {
                Glide.with(XiangqingActivity.this).load(imgs.get(position)).into((ImageView) view);
            }
        });
        img.setPageChangeDuration(1000);
        title.setText(data.getTitle());
        subhead.setText(data.getSubhead());
        price.setText(data.getPrice() + "");
        newprice.setText(data.getBargainPrice() + "");
        final String detailUrl = bean.getData().getDetailUrl();
        web.loadUrl(detailUrl);
        pid1 = bean.getData().getPid();
        sellerid = bean.getSeller().getSellerid();
    }

    @OnClick(R.id.add)
    public void onViewClicked() {
        HashMap<String, String> addmap = new HashMap<>();
        addmap.put("uid", "2856");
        addmap.put("source", "android");
        addmap.put("pid", pid1 + "");

        Log.e("guc", pid1 + "");
        OkHttp3Utils.doPost("http://120.27.23.105/product/addCart", addmap, new GsonObjectCallback<Add_Bean>() {
            @Override
            public void onUi(Add_Bean add_bean) {
                Toast.makeText(XiangqingActivity.this, add_bean.getMsg(), Toast.LENGTH_LONG).show();

                Log.e("guc", add_bean.getMsg());
            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });

        HashMap<String, String> suchmap = new HashMap<>();
        suchmap.put("uid", "2856");
        suchmap.put("source", "android");
        suchmap.put("sellerid", sellerid + "");
        suchmap.put("pid", pid1 + "");
//                suchmap.put("num","10");
//                suchmap.put("selected","1");
        OkHttp3Utils.doPost("http://120.27.23.105/product/updateCarts", suchmap, new GsonObjectCallback<Add_Bean>() {
            @Override
            public void onUi(Add_Bean add_bean) {
                Toast.makeText(XiangqingActivity.this, "刷新购物车", Toast.LENGTH_LONG).show();
                Log.e("guc", add_bean.getMsg());
            }
            @Override
            public void onFailed(Call call, IOException e) {
            }
        });

    }
}
