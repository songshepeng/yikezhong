package com.bwei.ssp.home_work.order.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bwei.ssp.home_work.R;
import com.bwei.ssp.home_work.order.bean.Order_bean;

import java.util.List;

/**
 * Created by lenovo on 2017/12/21.
 */

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.Myhoder> {
    Context context;
    List<Order_bean.DataBean>list;
    int type;

    public OrderAdapter(Context context, List<Order_bean.DataBean> list, int type) {
        this.context = context;
        this.list = list;
        this.type = type;
    }
    setOncliclItemListener listener;

    public void setListener(setOncliclItemListener listener) {
        this.listener = listener;
    }

    @Override
    public OrderAdapter.Myhoder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (type==1){
            View view = View.inflate(context, R.layout.daipay_item, null);
            Myhoder myhoder = new Myhoder(view);
            return  myhoder;
        }else if(type==2){
            View view = View.inflate(context, R.layout.yipay_item, null);
            Myhoder myhoder = new Myhoder(view);
            return  myhoder;
        }else  if(type==3){
            View view = View.inflate(context, R.layout.nopay_item, null);
            Myhoder myhoder = new Myhoder(view);
            return  myhoder;
        }

        return null;
    }

    @Override
    public void onBindViewHolder(OrderAdapter.Myhoder holder, final int position) {
         holder.price.setText("价格："+list.get(position).getPrice()+"");
        holder.time.setText("创建时间："+list.get(position).getCreatetime()+"");
        holder.title.setText(list.get(position).getTitle()+"");
        holder.but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClictListener(v,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface setOncliclItemListener{
        void onClictListener(View view, int Position);
    }

    public class Myhoder extends RecyclerView.ViewHolder{
        TextView title,price,time;
        Button but;

        public Myhoder(View itemView) {
            super(itemView);
            title=  itemView.findViewById(R.id.title);
            price= itemView.findViewById(R.id.price);
            time= itemView.findViewById(R.id.time);
           but= itemView.findViewById(R.id.order_but);
        }
    }
}
