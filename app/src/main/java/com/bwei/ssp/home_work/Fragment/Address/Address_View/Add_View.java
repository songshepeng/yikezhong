package com.bwei.ssp.home_work.Fragment.Address.Address_View;

import com.bwei.ssp.home_work.Fragment.Address.Addbean.Address_bean;
import com.bwei.ssp.home_work.Fragment.Address.Addbean.MorenBean;
import com.bwei.ssp.home_work.Fragment.Address.Addbean.NewAddbean;
import com.bwei.ssp.home_work.View.Iview;

/**
 * Created by lenovo on 2018/1/4.
 */

public interface Add_View extends Iview{
    void  AddData(Address_bean bean);
    void  AddNewBean(NewAddbean addbean);
    void AddMorenBean(MorenBean morenBean);
}
