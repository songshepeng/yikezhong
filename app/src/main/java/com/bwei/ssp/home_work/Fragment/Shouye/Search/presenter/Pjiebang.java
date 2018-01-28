package com.bwei.ssp.home_work.Fragment.Shouye.Search.presenter;

/**
 * Created by lenovo on 2017/12/19.
 */

public interface Pjiebang<T>  {
    //绑定 绑定展示的数据
    void attach(T view);
    //解绑
    void detach();
}
