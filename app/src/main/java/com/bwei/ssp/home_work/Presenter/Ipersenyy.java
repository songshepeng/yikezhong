package com.bwei.ssp.home_work.Presenter;

import com.bwei.ssp.home_work.View.Iview;

/**
 * Created by lenovo on 2018/1/8.
 */

public abstract class Ipersenyy <T extends Iview> {
    protected   T view;

    public Ipersenyy(T view) {
        this.view = view;
        //用来创建modle
        init();
    }

    protected abstract void init();

}
