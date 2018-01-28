package com.bwei.ssp.home_work.Login.model;

import com.bwei.ssp.home_work.Login.bean.Userbean;
import com.bwei.ssp.home_work.Model.Imodel;
import com.bwei.ssp.home_work.Utils.RetrofitManager;

import io.reactivex.Observable;

/**
 * Created by lenovo on 2018/1/8.
 */

public class LogeModel implements Imodel {
      public Observable<Userbean>setJs(String mobile,String password){
          Mymodel mymodel = RetrofitManager.getInstance().create(Mymodel.class);
          return  mymodel.setdata(mobile,password);
      }
}
