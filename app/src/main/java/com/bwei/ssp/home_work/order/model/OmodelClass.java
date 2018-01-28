package com.bwei.ssp.home_work.order.model;


import com.bwei.ssp.home_work.Okhttputils.GsonObjectCallback;
import com.bwei.ssp.home_work.Okhttputils.OkHttp3Utils;
import com.bwei.ssp.home_work.order.bean.Order_bean;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;

/**
 * Created by lenovo on 2017/12/21.
 */

public class OmodelClass implements Omodel{
    OnSendOrderbeanListener listener;

    public void setListener(OnSendOrderbeanListener listener) {
        this.listener = listener;
    }

    public interface OnSendOrderbeanListener{
    void  onSendOrder(Order_bean bean);
}

    @Override
    public void setData() {
        HashMap<String, String> map = new HashMap<>();
        map.put("uid","71");
        OkHttp3Utils.doPost("https://www.zhaoapi.cn/product/getOrders", map, new GsonObjectCallback<Order_bean>() {
            @Override
            public void onUi(Order_bean bean) {
                listener.onSendOrder(bean);
            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });
    }
}
