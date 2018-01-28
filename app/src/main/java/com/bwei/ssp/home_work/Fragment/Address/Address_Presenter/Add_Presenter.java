package com.bwei.ssp.home_work.Fragment.Address.Address_Presenter;

import com.bwei.ssp.home_work.Fragment.Address.Addbean.Address_bean;
import com.bwei.ssp.home_work.Fragment.Address.Addbean.MorenBean;
import com.bwei.ssp.home_work.Fragment.Address.Addbean.NewAddbean;
import com.bwei.ssp.home_work.Fragment.Address.Address_Model.AddModel;
import com.bwei.ssp.home_work.Fragment.Address.Address_View.Add_View;
import com.bwei.ssp.home_work.Presenter.Ipersenyy;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lenovo on 2018/1/4.
 */

public class Add_Presenter extends Ipersenyy<Add_View> {


    private AddModel addModel;

    public Add_Presenter(Add_View view) {
        super(view);
    }
    @Override
    protected void init() {
        addModel = new AddModel();
    }
    public void getBean(){


        Observable<Address_bean> address_beanObservable = addModel.setData();
        address_beanObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Address_bean>() {
                    @Override
                    public void accept(Address_bean bean) throws Exception {
                        view.AddData(bean);
                    }
                });
    }

    public void getNewBean(String uid,String name,String mobile,String addr,String status){


        Observable<NewAddbean> newAddbeanObservable = addModel.setNewBean(uid, name, addr, mobile,status);
        newAddbeanObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<NewAddbean>() {
                    @Override
                    public void accept(NewAddbean bean) throws Exception {
                        view.AddNewBean(bean);
                    }
                });
    }

    public void getMorenBean(String uid,String status,String addrid){



        Observable<MorenBean> morenBeanObservable = addModel.setMorenbean(uid, status, addrid);
        morenBeanObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MorenBean>() {
                    @Override
                    public void accept(MorenBean bean) throws Exception {
                        view.AddMorenBean(bean);
                    }
                });
    }


}
