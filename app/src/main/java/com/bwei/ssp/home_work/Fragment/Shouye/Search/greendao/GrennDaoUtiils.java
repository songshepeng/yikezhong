package com.bwei.ssp.home_work.Fragment.Shouye.Search.greendao;

import android.content.Context;

import com.bwei.ssp.home_work.Fragment.Shouye.Search.database.DaoMaster;
import com.bwei.ssp.home_work.Fragment.Shouye.Search.database.DaoSession;

/**
 * Created by lenovo on 2017/12/29.
 */


public class GrennDaoUtiils  {
    public static DaoSession daoSession;
    public  static void initDesesion(Context context){
        daoSession= DaoMaster.newDevSession(context,"taobao.db");
    }
    public  static DaoSession getDaoSession(){
        return daoSession;
    }
}
