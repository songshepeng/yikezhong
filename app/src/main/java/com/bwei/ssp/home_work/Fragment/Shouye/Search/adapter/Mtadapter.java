package com.bwei.ssp.home_work.Fragment.Shouye.Search.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwei.ssp.home_work.Fragment.Shouye.Search.bean.Bean;
import com.bwei.ssp.home_work.R;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by lenovo on 2017/12/19.
 */

public class Mtadapter extends XRecyclerView.Adapter<Mtadapter.Myhoder>{
    Context context;
    List<Bean.DataBean>list;
    int type;

    public Mtadapter(Context context, List<Bean.DataBean> list, int type) {
        this.context = context;
        this.list = list;
        this.type = type;
    }

    @Override
    public Myhoder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (type==1){
            View v = View.inflate(context, R.layout.line_item, null);
            Myhoder myhoder = new Myhoder(v);
            return myhoder;
        }else if (type==2){
            View v = View.inflate(context, R.layout.grild, null);
            Myhoder myhoder = new Myhoder(v);
            return myhoder;

        }
        return null;
    }

    @Override
    public void onBindViewHolder(Myhoder holder, int position) {
        holder.title.setText(list.get(position).getTitle());
        holder.price.setText("原价：￥"+list.get(position).getBargainPrice());
        holder.price.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG); //下划线
        holder.price.getPaint().setAntiAlias(true);//抗锯齿
        holder.newprice.setText("折扣价：￥"+list.get(position).getPrice());
        ImageLoader.getInstance().displayImage(list.get(position).getImages().split("!")[0],holder.img);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Myhoder extends XRecyclerView.ViewHolder{
        ImageView img;
        TextView title,price,newprice;
        public Myhoder(View itemView) {
            super(itemView);
           img = itemView.findViewById(R.id.img);
           title= itemView.findViewById(R.id.title);
            price=itemView.findViewById(R.id.price);
            newprice=itemView.findViewById(R.id.newprice);
        }
    }
}
