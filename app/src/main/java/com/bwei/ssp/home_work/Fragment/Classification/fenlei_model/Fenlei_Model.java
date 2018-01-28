package com.bwei.ssp.home_work.Fragment.Classification.fenlei_model;

import com.bwei.ssp.home_work.Fragment.Classification.bean.CommodityBean;
import com.bwei.ssp.home_work.Fragment.Classification.bean.Son_Bean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by lenovo on 2017/12/13.
 */

public interface Fenlei_Model {

    @GET("product/getCatagory")
    Observable<CommodityBean>setCommBean();

    @GET("product/getProductCatagory")
    Observable<Son_Bean>setSonBean(@Query("cid") String cid);


}
