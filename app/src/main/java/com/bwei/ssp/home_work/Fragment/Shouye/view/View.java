package com.bwei.ssp.home_work.Fragment.Shouye.view;

import com.bwei.ssp.home_work.Fragment.Shouye.bean.Shouye_Bean;
import com.bwei.ssp.home_work.Fragment.Shouye.bean.Shouye_Xbanner_Bean;
import com.bwei.ssp.home_work.View.Iview;

/**
 * Created by lenovo on 2017/12/8.
 */

public interface View  extends Iview{
   void getData(Shouye_Bean bean);
   void getBannerData(Shouye_Xbanner_Bean bean);
}
