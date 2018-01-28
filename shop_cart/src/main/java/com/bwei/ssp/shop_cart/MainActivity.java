package com.bwei.ssp.shop_cart;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.bwei.ssp.shop_cart.adapter.Myadapter1;
import com.bwei.ssp.shop_cart.bean.Bean;
import com.bwei.ssp.shop_cart.bean.CountPriceBean;
import com.bwei.ssp.shop_cart.okhttp.OkHttpUtils;
import com.bwei.ssp.shop_cart.okhttp.OnUiCallback;
import com.bwei.ssp.shop_cart.view.MyExpandableListView;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class MainActivity extends AppCompatActivity {

    private MyExpandableListView expandableListView;
    private CheckBox all_check;
    private TextView price;
    private Button buy;
    private Handler handler =new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0){
                CountPriceBean countPriceBean = (CountPriceBean) msg.obj;

                //设置
                price.setText("合计:¥"+countPriceBean.getPrice());
                buy.setText("去结算("+countPriceBean.getCount()+")");
            }else  if (msg.what == 1){//改变全选
                boolean flag = (boolean) msg.obj;

                all_check.setChecked(flag);
            }
        }
    };
    private Myadapter1 myadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        expandableListView = (MyExpandableListView) findViewById(R.id.expandableListView);
        all_check = (CheckBox) findViewById(R.id.all_check);
        price = (TextView) findViewById(R.id.price);
        buy = (Button) findViewById(R.id.buy);
        expandableListView.setGroupIndicator(null);
        getData();
    }

    private void getData() {
        OkHttpUtils.getInstance().doGet("http://120.27.23.105/product/getCarts?uid=2856", new OnUiCallback() {
            @Override
            public void onFailed(Call call, IOException e) {

            }

            @Override
            public void onSuccess(String result) throws IOException {
                if (result!=null){
              getJs(result);
                }
            }
        });
    }

    private void getJs(String result) {
        Log.e("**", result);
        Gson gson = new Gson();
        if(result!=null){
            Bean bean = gson.fromJson(result, Bean.class);
            List<Bean.DataBean> data = bean.getData();
            List<List<Bean.DataBean.ListBean>> listChilds = new ArrayList<>();
            for (int i = 0; i < data.size(); i++) {
                listChilds.add(data.get(i).getList());
            }
            myadapter = new Myadapter1( MainActivity.this, data, listChilds,handler);
            expandableListView.setAdapter(myadapter);
            for (int i=0;i<data.size();i++){
                expandableListView.expandGroup(i);
            }

            all_check.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myadapter.setIfCheckAll(all_check.isChecked());
                }
            });
        }else{
            Toast.makeText(MainActivity.this,"天哪请求数据失败！",Toast.LENGTH_LONG).show();
        }

    }


}
