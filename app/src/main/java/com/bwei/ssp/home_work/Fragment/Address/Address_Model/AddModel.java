package com.bwei.ssp.home_work.Fragment.Address.Address_Model;

import com.bwei.ssp.home_work.Fragment.Address.Addbean.Address_bean;
import com.bwei.ssp.home_work.Fragment.Address.Addbean.MorenBean;
import com.bwei.ssp.home_work.Fragment.Address.Addbean.NewAddbean;
import com.bwei.ssp.home_work.Model.Imodel;
import com.bwei.ssp.home_work.Utils.RetrofitManager;

import io.reactivex.Observable;

/**
 * Created by lenovo on 2018/1/8.
 */

public class AddModel implements Imodel {
    public Observable<Address_bean>setData(){
        Add_Model add_model = RetrofitManager.getInstance().create(Add_Model.class);
       return add_model.setData();
    }

     public Observable<NewAddbean>setNewBean(String uid,String name,String mobile,String addr,String status){
         Add_Model add_model = RetrofitManager.getInstance().create(Add_Model.class);
         return  add_model.setNewbean(uid,name,mobile,addr,status);
     }

     public Observable<MorenBean>setMorenbean(String uid,String status,String addrid){
         Add_Model add_model = RetrofitManager.getInstance().create(Add_Model.class);
        return  add_model.setMorenbean(uid,status,addrid);
     }
}
