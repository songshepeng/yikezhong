package com.bwei.ssp.home_work.Fragment.Shop.Shop_model;

import com.bwei.ssp.home_work.Fragment.Shop.bean.Shop_Bean;
import com.bwei.ssp.home_work.Model.Imodel;
import com.bwei.ssp.home_work.Utils.RetrofitManager;

import io.reactivex.Observable;

/**
 * Created by lenovo on 2017/12/13.
 */

public class Shop_mclass  implements Imodel {

     public Observable<Shop_Bean>setData(String pscid){
         Shop_model shop_model = RetrofitManager.getInstance().create(Shop_model.class);
         return  shop_model.setData(pscid);
     }


}
