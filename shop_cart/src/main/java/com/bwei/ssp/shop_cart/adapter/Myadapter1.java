package com.bwei.ssp.shop_cart.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bwei.ssp.shop_cart.R;
import com.bwei.ssp.shop_cart.bean.Bean;
import com.bwei.ssp.shop_cart.bean.CountPriceBean;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.List;

/**
 * Created by lenovo on 2017/12/18.
 */

public class Myadapter1  extends BaseExpandableListAdapter{
    Context context;
    List<Bean.DataBean>group_list;
    List<List<Bean.DataBean.ListBean>>child_list;
    Handler handler;

    public Myadapter1(Context context, List<Bean.DataBean> group_list, List<List<Bean.DataBean.ListBean>> child_list, Handler handler) {
        this.context = context;
        this.group_list = group_list;
        this.child_list = child_list;
        this.handler = handler;
    }

    @Override
    public int getGroupCount() {
        return group_list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return child_list.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return group_list.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return child_list.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View view, ViewGroup parent) {
        GroupHolder holder;
        if (view==null){
            view=  View.inflate(context, R.layout.group_item,null);
            holder=new GroupHolder();
            holder.group_check=view.findViewById(R.id.group_checked);
            holder.group_text=view.findViewById(R.id.group_text);
            view.setTag(holder);
        }else {
            holder = (GroupHolder)view.getTag();
        }
        final Bean.DataBean dataBean = group_list.get(groupPosition);
        holder.group_text.setText(dataBean.getSellerName());
        holder.group_check.setChecked(dataBean.isGroupChecked());
        holder.group_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //设置一级列表的状态值
                dataBean.setGroupChecked(!dataBean.isGroupChecked());
                //根据当前一级状态改变子二级列表的所有状态
                changeAllchildstate(groupPosition,dataBean.isGroupChecked());
                //通过判断一级的是否选中，来设置是否为全部选中
                changeAllState(isAllGroupChecked());
                //改变全部的状态计算价格
                sendPriceAndCount();
                notifyDataSetChanged();
            }
        });
        return view;
    }

    private void sendPriceAndCount() {
        double price=0;
        int count=0 ;
        for (int i = 0; i <group_list.size() ; i++) {
            List<Bean.DataBean.ListBean> list = group_list.get(i).getList();
            for (int j = 0; j <list.size() ; j++) {
                Bean.DataBean.ListBean listBean = list.get(j);
                if (listBean.getSelected()==1){
                    price+=listBean.getPrice()*listBean.getNum();
                    count+=listBean.getNum();
                }
            }
        }

        CountPriceBean countPriceBean = new CountPriceBean(price, count);
        Message msg = Message.obtain();
        msg.what=0;
        msg.obj=countPriceBean;
        handler.sendMessage(msg);
    }

    private void changeAllState(boolean allGroupChecked) {
        Message msg = Message.obtain();
        msg.what=1;
        msg.obj=allGroupChecked;
        handler.sendMessage(msg);
    }

    private boolean isAllGroupChecked() {

        for (int i = 0; i < group_list.size(); i++) {
             if(!group_list.get(i).isGroupChecked()){
                 return false;
             }
        }
        return true;
    }

    private void changeAllchildstate(int groupPosition, boolean groupChecked) {
        List<Bean.DataBean.ListBean> listBeen = child_list.get(groupPosition);
        for (int i = 0; i <listBeen.size() ; i++) {
            listBeen.get(i).setSelected(groupChecked?1:0);
        }
    }

    @Override
    public View getChildView(final int groupPosition, int childPosition, boolean isLastChild, View view, ViewGroup parent) {

        final ChildHolder holder;
        if(view==null){
            view = View.inflate(context, R.layout.child_item, null);
            holder=new ChildHolder();

            holder.text_add = view.findViewById(R.id.text_add);
            holder.text_num = view.findViewById(R.id.text_num);
            holder.text_jian = view.findViewById(R.id.text_jian);
            holder.text_title = view.findViewById(R.id.text_title);
            holder.text_price = view.findViewById(R.id.text_price);
            holder.image_good = view.findViewById(R.id.image_good);
            holder.child_check = view.findViewById(R.id.check_child);

            view.setTag(holder);
        }else {
            holder = (ChildHolder) view.getTag();
        }
        final Bean.DataBean.ListBean listBean = child_list.get(groupPosition).get(childPosition);
        holder.text_num.setText(listBean.getNum()+"");//......注意
        holder.text_price.setText("¥"+listBean.getPrice());
        holder.text_title.setText(listBean.getTitle());
        ImageLoaderConfiguration aDefault = ImageLoaderConfiguration.createDefault(context);
        ImageLoader.getInstance().init(aDefault);
        ImageLoader.getInstance().displayImage(listBean.getImages().split("!")[0],holder.image_good);
        holder.child_check.setChecked(listBean.getSelected()==0? false:true);
        holder.child_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listBean.setSelected(listBean.getSelected()==0? 1:0);
                Toast.makeText(context,"嗲"+ holder.child_check.isChecked(),Toast.LENGTH_LONG).show();
                sendPriceAndCount();
                if (listBean.getSelected()==1){
                    if(isAllChildStabie(groupPosition)){
                           changeGroupstabie(groupPosition,true);
                         changeAllState(isAllGroupChecked());

                    }
                }else {
                    changeGroupstabie(groupPosition,false);
                    changeAllState(isAllGroupChecked());

                }
                 notifyDataSetChanged();
            }

        });
        return view;
    }


    public void setIfCheckAll(boolean checked) {
        //本方法是为了通过主类里的checkbox改变一级二级列表的值
        for (int i = 0; i < group_list.size(); i++) {
            Bean.DataBean dataBean = group_list.get(i);
            //设置组上面的checkBox是否选中
            dataBean.setGroupChecked(checked);

            List<Bean.DataBean.ListBean> listBeans = dataBean.getList();
            for (int j = 0; j < listBeans.size(); j++) {
                //改变是否选中的状态...数据应该变的是
                listBeans.get(j).setSelected(checked ? 1 : 0);
            }

        }
        //计算价钱和数量并且发送到mainActivity显示
        sendPriceAndCount();

        //刷新适配器
        notifyDataSetChanged();
    }

    private void changeGroupstabie(int groupPosition, boolean b) {
        group_list.get(groupPosition).setGroupChecked(b);
    }

    private boolean isAllChildStabie(int groupPosition) {

        List<Bean.DataBean.ListBean> listBeen = child_list.get(groupPosition);
        for (int i = 0; i <listBeen.size() ; i++) {
            if (listBeen.get(i).getSelected()==0){
                return false;
            }
        }

        return true;
    }


    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    class GroupHolder{
        CheckBox group_check;
        TextView group_text;
    }
    class ChildHolder{
        CheckBox child_check;
        ImageView image_good;
        TextView  text_title;
        TextView text_price;
        TextView text_jian;
        TextView text_num;
        TextView text_add;
    }
}
