package com.bwei.ssp.fanjialinaaa;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.bwei.ssp.fanjialinaaa.GreenDao.fan;
import com.bwei.ssp.fanjialinaaa.GreenDao.utils;
import com.bwei.ssp.fanjialinaaa.database.DaoSession;

public class MainActivity extends AppCompatActivity {

    private DaoSession daoseesion;
    private com.bwei.ssp.fanjialinaaa.database.fanDao fanDao;
    private com.bwei.ssp.fanjialinaaa.database.linDao linDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        daoseesion = utils.getDaoseesion();
        fanDao = daoseesion.getFanDao();
        linDao = daoseesion.getLinDao();

    }
    public void add(View view){

        fan tecath = new fan(null, "张老师", 21);
        fan tecath2 = new fan(null, "李老师", 22);
        fan tecath3 = new fan(null, "王老师", 23);
        fanDao.insertInTx(tecath,tecath2,tecath3);



    }
    public void delete(View view){

    }

    public void qeury(View view){

    }

    public void update(View view){

    }



}
