package com.bwei.ssp.home_work.order.Presenter;


import com.bwei.ssp.home_work.order.bean.Order_bean;
import com.bwei.ssp.home_work.order.model.OmodelClass;
import com.bwei.ssp.home_work.order.view.Oview;

/**
 * Created by lenovo on 2017/12/21.
 */

public class Opresenter implements OmodelClass. OnSendOrderbeanListener {
    Oview oview;
    OmodelClass omodelClass;

    public Opresenter(Oview oview) {
        this.oview = oview;
        this.omodelClass =new OmodelClass();
    }
  public  void getJs(){
      omodelClass.setData();
      omodelClass.setListener(this);
  }
    @Override
    public void onSendOrder(Order_bean bean) {
        oview.getData(bean);
    }
}
