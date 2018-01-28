package com.bwei.ssp.fanjialinaaa.GreenDao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by lenovo on 2017/12/28.
 */

@Entity(active = true)
public class lin {
    //设置自增
    @Id(autoincrement = true)
    Long id;
    String name;
    String age;
    int  tecath;




}
