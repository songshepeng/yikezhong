package com.bwei.ssp.home_work.Fragment.Address.Address_Model;

import com.bwei.ssp.home_work.Fragment.Address.Addbean.Address_bean;
import com.bwei.ssp.home_work.Fragment.Address.Addbean.MorenBean;
import com.bwei.ssp.home_work.Fragment.Address.Addbean.NewAddbean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by lenovo on 2018/1/4.
 */

public interface Add_Model {
    String url = "user/getAddrs?uid=2856";
    @GET(url)
    Observable<Address_bean>setData( );
    @GET("user/addAddr")
    Observable<NewAddbean>setNewbean(@Query("uid") String uid,@Query("name")String name,@Query("addr")String addr,@Query("mobile")String mobile,@Query("status")String status);
    @GET("user/setAddr")
    Observable<MorenBean>setMorenbean(@Query("uid") String uid, @Query("status")String status, @Query("addrid")String addrid);

}
