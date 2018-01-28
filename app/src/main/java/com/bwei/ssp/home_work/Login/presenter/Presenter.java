package com.bwei.ssp.home_work.Login.presenter;

import com.bwei.ssp.home_work.Login.bean.Bean;
import com.bwei.ssp.home_work.Login.bean.Userbean;
import com.bwei.ssp.home_work.Login.model.LogeModel;
import com.bwei.ssp.home_work.Login.view.Myview;
import com.bwei.ssp.home_work.Presenter.Ipersenyy;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
/**
 * Created by lenovo on 2017/12/8.
 */
public class Presenter  extends Ipersenyy <Myview>{


    private LogeModel logeModel;

    public Presenter(Myview view) {
        super(view);
    }
    @Override
    protected void init() {
        logeModel = new LogeModel();
    }
    public void getData(Bean bean){


        Observable<Userbean> userbeanObservable = logeModel.setJs(bean.getName(), bean.getPass());

        userbeanObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Userbean>() {
                               @Override
                               public void accept(Userbean userbean) throws Exception {
                                   view.getYes(userbean);
                               }
                           }
                        , new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                view.getNo(throwable);
                            }
                        }
                );
    }



}
