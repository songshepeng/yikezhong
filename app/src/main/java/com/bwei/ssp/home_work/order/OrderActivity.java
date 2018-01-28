package com.bwei.ssp.home_work.order;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bwei.ssp.home_work.R;
import com.bwei.ssp.home_work.order.Presenter.Opresenter;
import com.bwei.ssp.home_work.order.adapter.OrderAdapter;
import com.bwei.ssp.home_work.order.bean.Order_bean;
import com.bwei.ssp.home_work.order.view.Oview;
import com.bwei.ssp.home_work.pay.PayActivity;

import java.util.List;

public class OrderActivity extends AppCompatActivity implements Oview {

    private Opresenter opresenter;
    private ImageView immg;
    private Button daipay,yipay,nopay;
    private OrderAdapter orderAdapter;
    private RecyclerView rlv;
    private List<Order_bean.DataBean> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        opresenter = new Opresenter(this);
        opresenter.getJs();
        immg = (ImageView) findViewById(R.id.img);
        daipay = (Button) findViewById(R.id.daipay);
         yipay=(Button) findViewById(R.id.yipay);
         nopay= (Button) findViewById(R.id.nopay);
        rlv = (RecyclerView) findViewById(R.id.rlv);

        View view = View.inflate(this, R.layout.prowind, null);
        final PopupWindow popupWindow = new PopupWindow(view, RecyclerView.LayoutParams.WRAP_CONTENT, RecyclerView.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        //点击外边消失
        popupWindow.setOutsideTouchable(true);
        //设置此参数获得焦点，否则无法点击
       // popupWindow.setFocusable(true);
         immg.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if(popupWindow.isShowing()){
                     popupWindow.dismiss();
                 }else{
                     popupWindow.showAsDropDown(v);
                 }
             }
         });
        TextView dai = view.findViewById(R.id.dai);
          dai.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  orderAdapter = new OrderAdapter(OrderActivity.this, data, 1);
                  rlv.setLayoutManager(new LinearLayoutManager(OrderActivity.this));
                  rlv.setAdapter(orderAdapter);
                  orderAdapter.setListener(new OrderAdapter.setOncliclItemListener() {
                      @Override
                      public void onClictListener(View view, int Position) {
                           startActivity(new Intent(OrderActivity.this, PayActivity.class));
                      }
                  });

              }
          });
        daipay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderAdapter = new OrderAdapter(OrderActivity.this, data, 1);
                rlv.setLayoutManager(new LinearLayoutManager(OrderActivity.this));
                rlv.setAdapter(orderAdapter);
                orderAdapter.setListener(new OrderAdapter.setOncliclItemListener() {
                    @Override
                    public void onClictListener(View view, int Position) {
                   startActivity(new Intent(OrderActivity.this,PayActivity.class));
                    }
                });
            }
        });
        TextView yi = view.findViewById(R.id.yi);
         yi.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 orderAdapter = new OrderAdapter(OrderActivity.this, data, 2);
                 rlv.setLayoutManager(new LinearLayoutManager(OrderActivity.this));
                 rlv.setAdapter(orderAdapter);

                 orderAdapter.setListener(new OrderAdapter.setOncliclItemListener() {
                     @Override
                     public void onClictListener(View view, int Position) {
                         Toast.makeText(OrderActivity.this,"查看订单",Toast.LENGTH_LONG).show();

                     }
                 });
             }
         });
        yipay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderAdapter = new OrderAdapter(OrderActivity.this, data, 2);
                rlv.setLayoutManager(new LinearLayoutManager(OrderActivity.this));
                rlv.setAdapter(orderAdapter);

                orderAdapter.setListener(new OrderAdapter.setOncliclItemListener() {
                    @Override
                    public void onClictListener(View view, int Position) {
                        Toast.makeText(OrderActivity.this,"查看订单",Toast.LENGTH_LONG).show();

                    }
                });
            }
        });
        TextView no = view.findViewById(R.id.no);
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderAdapter = new OrderAdapter(OrderActivity.this, data, 3);
                rlv.setLayoutManager(new LinearLayoutManager(OrderActivity.this));
                rlv.setAdapter(orderAdapter);
                orderAdapter.setListener(new OrderAdapter.setOncliclItemListener() {
                    @Override
                    public void onClictListener(View view, int Position) {
                        Toast.makeText(OrderActivity.this,"已取消的查看订单",Toast.LENGTH_LONG).show();

                    }
                });
            }
        });
        nopay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderAdapter = new OrderAdapter(OrderActivity.this, data, 3);
                rlv.setLayoutManager(new LinearLayoutManager(OrderActivity.this));
                rlv.setAdapter(orderAdapter);
                orderAdapter.setListener(new OrderAdapter.setOncliclItemListener() {
                    @Override
                    public void onClictListener(View view, int Position) {
                        Toast.makeText(OrderActivity.this,"已取消的查看订单",Toast.LENGTH_LONG).show();

                    }
                });
            }
        });
    }

    @Override
    public void getData(Order_bean bean) {
        data = bean.getData();
        orderAdapter = new OrderAdapter(OrderActivity.this, data, 1);
        rlv.setLayoutManager(new LinearLayoutManager(OrderActivity.this));
        rlv.setAdapter(orderAdapter);
        orderAdapter.setListener(new OrderAdapter.setOncliclItemListener() {
            @Override
            public void onClictListener(View view, int Position) {
                startActivity(new Intent(OrderActivity.this,PayActivity.class));
            }
        });
    }
}
