package com.bwei.ssp.home_work.Fragment.Classification.fenlei_presenter;

import com.bwei.ssp.home_work.Fragment.Classification.bean.CommodityBean;
import com.bwei.ssp.home_work.Fragment.Classification.bean.Son_Bean;
import com.bwei.ssp.home_work.Fragment.Classification.fellei_View.Fenlei_View;
import com.bwei.ssp.home_work.Fragment.Classification.fenlei_model.Fenlei_moclass;
import com.bwei.ssp.home_work.Presenter.Ipersenyy;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lenovo on 2017/12/13.
 */

public class Fenlei_pre extends Ipersenyy<Fenlei_View> {

    private Fenlei_moclass fenlei_moclass;

    @Override
    protected void init() {
        fenlei_moclass = new Fenlei_moclass();
    }
    public Fenlei_pre(Fenlei_View view) {
        super(view);
    }

    public void getJs(){
        Observable<CommodityBean> commodityBeanObservable = fenlei_moclass.setCommBean();
        commodityBeanObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CommodityBean>() {
                    @Override
                    public void accept(CommodityBean commodityBean) throws Exception {
                        view.getConnBean(commodityBean);
                    }
                });
    }

    public void getSonJs(String cid){
        Observable<Son_Bean> son_beanObservable = fenlei_moclass.setSonBean(cid);
        son_beanObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Son_Bean>() {
                    @Override
                    public void accept(Son_Bean bean) throws Exception {
                        view.getSonBean(bean);
                    }
                });

    }



}
