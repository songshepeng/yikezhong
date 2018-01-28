package com.bwei.ssp.home_work.Fragment.Shouye.Search.presenter;

import com.bwei.ssp.home_work.Fragment.Shouye.Search.bean.Bean;
import com.bwei.ssp.home_work.Fragment.Shouye.Search.model.ModelClass;
import com.bwei.ssp.home_work.Fragment.Shouye.Search.view.view;

import java.lang.ref.WeakReference;

/**
 * Created by lenovo on 2017/12/19.
 */

public class presenter implements ModelClass.OnGetDatalistener {
   view sview;
    ModelClass modelClass;
    private WeakReference viewWeakReference;

    public presenter(view sview) {
        this.sview = sview;
        this.modelClass=new ModelClass();
    }


    public void  getJs(String keywords,int page){
           modelClass.setData(keywords,page);
        modelClass.setListener(this);
    }

    @Override
    public void onGetData(Bean bean) {
        sview.getData(bean);
    }

}

