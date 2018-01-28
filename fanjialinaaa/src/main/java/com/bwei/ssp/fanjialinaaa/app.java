package com.bwei.ssp.fanjialinaaa;

import android.app.Application;

import com.bwei.ssp.fanjialinaaa.GreenDao.utils;

/**
 * Created by lenovo on 2017/12/28.
 */

public class app extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        utils.init(this);
    }
}
