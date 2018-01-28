package com.bwei.ssp.home_work.Fragment.Classification.fenlei_model;

import com.bwei.ssp.home_work.Fragment.Classification.bean.CommodityBean;
import com.bwei.ssp.home_work.Fragment.Classification.bean.Son_Bean;
import com.bwei.ssp.home_work.Model.Imodel;
import com.bwei.ssp.home_work.Utils.RetrofitManager;

import io.reactivex.Observable;

/**
 * Created by lenovo on 2017/12/13.
 */

public class Fenlei_moclass implements Imodel {
    public Observable<CommodityBean>setCommBean(){
        Fenlei_Model fenlei_model = RetrofitManager.getInstance().create(Fenlei_Model.class);
        return  fenlei_model.setCommBean();
    }

    public Observable<Son_Bean>setSonBean(String cid){
        Fenlei_Model fenlei_model = RetrofitManager.getInstance().create(Fenlei_Model.class);
        return  fenlei_model.setSonBean(cid);
    }
}
