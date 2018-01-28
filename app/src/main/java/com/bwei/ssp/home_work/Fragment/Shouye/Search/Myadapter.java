package com.bwei.ssp.home_work.Fragment.Shouye.Search;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwei.ssp.home_work.Fragment.Shouye.Search.greendao.SearchContent;
import com.bwei.ssp.home_work.R;

import java.util.List;

/**
 * Created by lenovo on 2017/12/25.
 */

public class Myadapter extends RecyclerView.Adapter<Myadapter.Myhoder> {
    Context context;
    List<SearchContent>list;

    public Myadapter(Context context, List<SearchContent> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public Myadapter.Myhoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.rlv_item, null);
        Myhoder myhoder = new Myhoder(view);
        return myhoder;
    }

    @Override
    public void onBindViewHolder(Myadapter.Myhoder holder, int position) {
               holder.item_button.setText(list.get(position).getBut_text());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Myhoder extends RecyclerView.ViewHolder {
        TextView item_button;
        public Myhoder(View itemView) {
            super(itemView);
            item_button= itemView.findViewById(R.id.item_but);
        }
    }
}
