package com.bwei.ssp.home_work.Fragment.Shop.Shop_presenter;

import com.bwei.ssp.home_work.Fragment.Shop.Shop_model.Shop_mclass;
import com.bwei.ssp.home_work.Fragment.Shop.Sop_view.Shop_view;
import com.bwei.ssp.home_work.Fragment.Shop.bean.Shop_Bean;
import com.bwei.ssp.home_work.Presenter.Ipersenyy;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lenovo on 2017/12/13.
 */

public class Shop_presenter extends Ipersenyy<Shop_view> {

    private Shop_mclass shop_mclass;
    public Shop_presenter(Shop_view view) {
        super(view);
    }
    @Override
    protected void init() {
        shop_mclass = new Shop_mclass();
    }
    public  void getJs(int pscid){
        Observable<Shop_Bean> shop_beanObservable = shop_mclass.setData(pscid+"");
        shop_beanObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Shop_Bean>() {
                    @Override
                    public void accept(Shop_Bean bean) throws Exception {
                        view.getData(bean);
                    }
                });

    }

}
