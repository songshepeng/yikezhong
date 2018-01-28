package com.bwei.ssp.home_work.Login.view;

import com.bwei.ssp.home_work.Login.bean.Userbean;
import com.bwei.ssp.home_work.View.Iview;

/**
 * Created by lenovo on 2017/12/8.
 */

public interface Myview  extends Iview{
    void getYes(Userbean userbean);
    void getNo(Throwable throwable);
}
