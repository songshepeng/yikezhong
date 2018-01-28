package com.bwei.ssp.home_work.Login.model;

import com.bwei.ssp.home_work.Login.bean.Userbean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;


/**
 * Created by lenovo on 2017/12/8.
 */

public interface Mymodel  {
      @GET("user/login")
      Observable<Userbean>setdata(@Query("mobile") String mobile, @Query("password") String password);


}
