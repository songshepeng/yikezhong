package com.bwei.ssp.home_work.Fragment.Shouye.Souye_Madapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwei.ssp.home_work.Fragment.Shouye.bean.Shouye_Xbanner_Bean;
import com.bwei.ssp.home_work.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by lenovo on 2017/12/18.
 */

public class Liushi_Adapter extends RecyclerView.Adapter<Liushi_Adapter.Myhoder> {
    Context context;
    List<Shouye_Xbanner_Bean.TuijianBean.ListBean>list;

    public Liushi_Adapter(Context context, List<Shouye_Xbanner_Bean.TuijianBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public Myhoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.liushi_item, null);
        Myhoder myhoder = new Myhoder(view);
        return myhoder;
    }

    @Override
    public void onBindViewHolder(Myhoder holder, int position) {
        Shouye_Xbanner_Bean.TuijianBean.ListBean listBean = list.get(position);
        holder.tv.setText(listBean.getTitle());
        ImageLoader.getInstance().displayImage(listBean.getImages().split("!")[0],holder.img);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Myhoder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView tv ;
        public Myhoder(View itemView) {
            super(itemView);
           img = itemView.findViewById(R.id.liushi_img);
           tv = itemView.findViewById(R.id.liushi_tv);
        }
    }
}
