package com.bwei.ssp.fanjialinaaa.GreenDao;

import android.content.Context;

import com.bwei.ssp.fanjialinaaa.database.DaoMaster;
import com.bwei.ssp.fanjialinaaa.database.DaoSession;

/**
 * Created by lenovo on 2017/12/28.
 */

public class utils {
    private static  DaoSession daoseesion ;


    public static void init(Context context){
        daoseesion = DaoMaster.newDevSession(context,"shujuku.db");

    }

    public static DaoSession getDaoseesion() {
        return daoseesion;
    }
}
