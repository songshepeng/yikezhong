package com.bwei.ssp.home_work.Fragment.Address.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.bwei.ssp.home_work.Fragment.Address.Addbean.Address_bean;
import com.bwei.ssp.home_work.Fragment.Address.Addbean.MorenBean;
import com.bwei.ssp.home_work.Fragment.Address.Addbean.NewAddbean;
import com.bwei.ssp.home_work.Fragment.Address.Address_Presenter.Add_Presenter;
import com.bwei.ssp.home_work.Fragment.Address.Address_View.Add_View;
import com.bwei.ssp.home_work.R;

import java.util.List;

/**
 * Created by lenovo on 2018/1/4.
 */

public class AddAdapter extends RecyclerView.Adapter<AddAdapter.Myhoder> implements Add_View{
    Context context;
     List<Address_bean.DataBean> list;

    public AddAdapter(Context context, List<Address_bean.DataBean> list) {
        this.context = context;
        this.list = list;
    }
    cheOchick listen;

    public void setListen(cheOchick listen) {
        this.listen = listen;
    }

    @Override
    public Myhoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.address_item, null);
        Myhoder myhoder = new Myhoder(inflate);
        return myhoder;
    }


    @Override
    public void onBindViewHolder(final Myhoder holder, final int position) {
        final Address_bean.DataBean dataBean = list.get(position);
        holder.add_address.setText(dataBean.getAddr());
        holder.add_name.setText(dataBean.getName());
        holder.add_phone.setText(dataBean.getMobile()+"");
                if (dataBean.getStatus()==1){
                    holder.che.setChecked(true);
                }

        if(dataBean.getStatus()==0){
            holder.che.setChecked(false);
        }
        final Add_Presenter add_presenter = new Add_Presenter( this);
        holder.che.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listen.onCheonchick(v,position);
                if (holder.che.isChecked()){
                    // list.clear();
                add_presenter.getMorenBean("2856","0",dataBean.getAddrid()+"");

                    Toast.makeText(context,"点击了"+dataBean.getStatus(),Toast.LENGTH_LONG).show();

                }else if (!holder.che.isChecked()){
                  add_presenter.getMorenBean("2856","1",dataBean.getAddrid()+"");

                    Toast.makeText(context,"点击了"+dataBean.getStatus(),Toast.LENGTH_LONG).show();

                }

            }
        });

    }
  public  interface cheOchick{
      void onCheonchick(View v,int Position);
  }
    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void AddData(Address_bean bean) {

    }

    @Override
    public void AddNewBean(NewAddbean addbean) {

    }

    @Override
    public void AddMorenBean(MorenBean morenBean) {

    }


    public class Myhoder extends RecyclerView.ViewHolder{
        TextView add_name,add_address,add_phone;
        CheckBox che;
        public Myhoder(View itemView) {
            super(itemView);
            add_name= itemView.findViewById(R.id.add_name);
            add_address= itemView.findViewById(R.id.add_address);
            add_phone=itemView.findViewById(R.id.add_phone);
            che=itemView.findViewById(R.id.add_che);
        }
    }
}
