package com.bwei.ssp.home_work.Fragment.Shouye.Search.model;

import android.util.Log;

import com.bwei.ssp.home_work.Fragment.Shouye.Search.bean.Bean;
import com.bwei.ssp.home_work.Okhttputils.GsonObjectCallback;
import com.bwei.ssp.home_work.Okhttputils.OkHttp3Utils;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;

/**
 * Created by lenovo on 2017/12/19.
 */

public class ModelClass implements model {
    OnGetDatalistener listener;
public  interface  OnGetDatalistener{
    void  onGetData(Bean bean);
}

    public void setListener(OnGetDatalistener listener) {
        this.listener = listener;
    }

    @Override
    public void setData(String keywords,int page) {
        HashMap<String, String> map = new HashMap<>();
        map.put("keywords",keywords);
        map.put("source","android");
        map.put("page",page+"");

        OkHttp3Utils.doPost("http://120.27.23.105/product/searchProducts", map, new GsonObjectCallback<Bean>() {
            @Override
            public void onUi(Bean bean) {
                Log.e("***", bean.getMsg());
                listener.onGetData(bean);
            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });
    }
}
