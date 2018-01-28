package com.bwei.ssp.home_work.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bwei.ssp.home_work.Fragment.shopcate.Myadapter;
import com.bwei.ssp.home_work.Fragment.shopcate.bean.Bean;
import com.bwei.ssp.home_work.Fragment.shopcate.bean.CountPriceBean;
import com.bwei.ssp.home_work.Fragment.shopcate.view.MyExpandableListView;
import com.bwei.ssp.home_work.Okhttputils.GsonObjectCallback;
import com.bwei.ssp.home_work.Okhttputils.OkHttp3Utils;
import com.bwei.ssp.home_work.R;
import com.bwei.ssp.home_work.order.OrderActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Created by lenovo on 2017/12/6.
 */

public class gwc extends Fragment {

    @InjectView(R.id.but)
    TextView bt;
    @InjectView(R.id.expandableListView)
    MyExpandableListView expandableListView;
    @InjectView(R.id.all_check)
    CheckBox all_check;
    @InjectView(R.id.price)
    TextView price;
    @InjectView(R.id.del)
    Button del;
    @InjectView(R.id.buy)
    Button buy;

    //通过Handler接收适配器的值改变主线程的UI
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                CountPriceBean countPriceBean = (CountPriceBean) msg.obj;

                //设置
                price.setText("合计:¥" + countPriceBean.getPrice());
                buy.setText("去结算(" + countPriceBean.getCount() + ")");
            } else if (msg.what == 1) {//改变全选
                boolean flag = (boolean) msg.obj;

                all_check.setChecked(flag);
            }
        }
    };


    private Myadapter myadapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = View.inflate(getActivity(), R.layout.gwc, null);
        ButterKnife.inject(this, v);
        expandableListView.setGroupIndicator(null);//暂时用不到此属性所以为false
        HashMap<String, String> map = new HashMap<>();
        map.put("uid", "2856");
        map.put("source", "android");
        OkHttp3Utils.doPost("http://120.27.23.105/product/getCarts", map, new GsonObjectCallback<Bean>() {
            @Override
            public void onUi(Bean bean) {
                if (bean.getData() != null) {
                    Log.e("**", bean.getMsg());
                    List<Bean.DataBean> data = bean.getData();
                    List<List<Bean.DataBean.ListBean>> listChilds = new ArrayList<>();
                    for (int i = 0; i < data.size(); i++) {
                        listChilds.add(data.get(i).getList());
                    }
                    myadapter = new Myadapter(handler, getActivity(), data, listChilds);
                    expandableListView.setAdapter(myadapter);
                    for (int i = 0; i < data.size(); i++) {
                        //设置一级列表默认展开
                        expandableListView.expandGroup(i);
                    }
                    all_check.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            myadapter.setIfCheckAll(all_check.isChecked());
                        }
                    });
                }
            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });

        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick(R.id.buy)
    public void onViewClicked() {
        Intent integer = new Intent(getActivity(), OrderActivity.class);
        startActivity(integer);
    }
}
