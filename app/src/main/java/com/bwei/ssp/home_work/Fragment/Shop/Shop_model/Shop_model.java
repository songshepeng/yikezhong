package com.bwei.ssp.home_work.Fragment.Shop.Shop_model;

import com.bwei.ssp.home_work.Fragment.Shop.bean.Shop_Bean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by lenovo on 2017/12/13.
 */

public interface Shop_model {
     @GET("product/getProducts")
    Observable<Shop_Bean> setData(@Query("pscid")String pscid);
}
