package com.bwei.ssp.home_work.Fragment.shopcate;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwei.ssp.home_work.Fragment.shopcate.bean.Bean;
import com.bwei.ssp.home_work.Fragment.shopcate.bean.CountPriceBean;
import com.bwei.ssp.home_work.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by lenovo on 2017/12/16.
 */

public class Myadapter extends BaseExpandableListAdapter {
    private Handler handler;
    private Context context;
    private List<Bean.DataBean> group_list;
    private List<List<Bean.DataBean.ListBean>>child_list;

    public Myadapter(Handler handler, Context context, List<Bean.DataBean> group_list, List<List<Bean.DataBean.ListBean>> child_list) {
        this.handler = handler;
        this.context = context;
        this.group_list = group_list;
        this.child_list = child_list;
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
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupHolder holder;
        if(convertView==null){
            convertView = View.inflate(context, R.layout.group_item, null);
            holder = new GroupHolder();
            holder.group_checked=convertView.findViewById(R.id.group_checked);
            holder.group_text=convertView.findViewById(R.id.group_text);
            convertView.setTag(holder);
        }else {
            holder= (GroupHolder) convertView.getTag();
        }
        final Bean.DataBean dataBean = group_list.get(groupPosition);
        holder.group_checked.setChecked(dataBean.isGroupChecked());
        holder.group_text.setText(dataBean.getSellerName());
        holder.group_checked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //改变当前以及列表的状态，不与初始化的相等
                dataBean.setGroupChecked(!dataBean.isGroupChecked());
                //根据当前一级状态改变二级列表的值
                changeChildState(groupPosition,dataBean.isGroupChecked());
                //通过判断一级的是否选中，来设置是否为全部选中
                changeAllState(isAllGroupChecked());
                //发送价格数量
                sendPriceAndCount();
                notifyDataSetChanged();
            }
        });

        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, int childPosition, boolean isLastChild, View view, ViewGroup parent) {
         ChildHolder holder;
        if(view==null){
            view = View.inflate(context, R.layout.child_item, null);
            holder=new ChildHolder();

            holder.text_add = view.findViewById(R.id.text_add);
            holder.text_num = view.findViewById(R.id.text_num);
            holder.text_jian = view.findViewById(R.id.text_jian);
            holder.text_title = view.findViewById(R.id.text_title);
            holder.text_price = view.findViewById(R.id.text_price);
            holder.image_good = view.findViewById(R.id.image_good);
            holder.check_child = view.findViewById(R.id.check_child);

            view.setTag(holder);
        }else {
            holder = (ChildHolder) view.getTag();
        }
        final Bean.DataBean.ListBean listBean = child_list.get(groupPosition).get(childPosition);
        holder.text_num.setText(listBean.getNum()+"");//......注意
        holder.text_price.setText("¥"+listBean.getPrice());
        holder.text_title.setText(listBean.getTitle());
        ImageLoader.getInstance().displayImage(listBean.getImages().split("!")[0],holder.image_good);
        holder.check_child.setChecked(listBean.getSelected()==0? false:true);
        holder.check_child.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listBean.setSelected(listBean.getSelected() ==0? 1:0);
                sendPriceAndCount();
                if(listBean.getSelected()==1){
                   if(isAllChildSelected(groupPosition)){
                       //如果全部选中改变当前状态
                       changeGroupState(groupPosition,true);
                       //.确定是否改变全选
                       changeAllState(isAllGroupChecked());
                   }

                }else {
                    //如果没有选中改变一下当前组的状态
                    changeGroupState(groupPosition,false);
                    //.确定是否改变全选
                    changeAllState(isAllGroupChecked());
                }
                //刷新适配器
                notifyDataSetChanged();
            }
        });
        holder.text_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listBean.setNum(listBean.getNum()+1);
                if (listBean.getSelected()==1){
                    sendPriceAndCount();
                }
                notifyDataSetChanged();
            }
        });
        holder.text_jian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = listBean.getNum();

                if (num == 1){
                    return;
                }

                listBean.setNum(num-1);
                if (listBean.getSelected() == 1){
                    sendPriceAndCount();
                }

                notifyDataSetChanged();
            }
        });
        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
        //子条目是否能点击
    }

   //通过二级列表 改变一级列表的状态设置父控件是否选中
    private void changeGroupState(int groupPosition, boolean b) {


        group_list.get(groupPosition).setGroupChecked(b);

    }

    //子布局是否选中
  //二级列表是否全部选中
    private boolean isAllChildSelected(int groupPosition) {
        List<Bean.DataBean.ListBean> listBeans = child_list.get(groupPosition);

        for (int i=0;i<listBeans.size();i++){
            if (listBeans.get(i).getSelected() == 0){

                return false;
            }
        }

        return true;
    }

//设置总价
    private  void  sendPriceAndCount(){
        double price=0;
        int count=0 ;
        for (int i = 0; i <group_list.size() ; i++) {
            List<Bean.DataBean.ListBean> list = group_list.get(i).getList();
            for (int j = 0; j <list.size() ; j++) {
                Bean.DataBean.ListBean listBean = list.get(j);
                if(listBean.getSelected()==1){
                    //当二级列表选中时计算价格，和物品的个数
                    price += listBean.getPrice()* listBean.getNum();
                    //单价*数目
                    count += listBean.getNum();
                }
            }
        }
        CountPriceBean countPriceBean = new CountPriceBean(price, count);
        //实例化 购物车计算价钱的bean类，把得到的总数总价钱存入
        Message msg = Message.obtain();
        msg.what = 0;
        //设置标识符待主类接收
        msg.obj = countPriceBean;

        handler.sendMessage(msg);
        //把值存入handler值 等待接收
    }


 //设置父控件全选
    private  boolean isAllGroupChecked(){

        for (int i = 0; i <group_list.size() ; i++) {
            //isGroupChecked 初始值为false当点击的时候为yrue所以 ！=isGroupChecked就是false
            if(!group_list.get(i).isGroupChecked()){
                return false;
            }
        }
       return true;
    }

    //设置所以元素全选
    public void changeAllState(boolean allGroupChecked){
        //通过得到一级列表的所有状态设置 全选的值
        Message msg = Message.obtain();
         msg.what=1;
        msg.obj=allGroupChecked;
        handler.sendMessage(msg);
        //主要就是实现 通过一级二级的状态改变主类里checkbox的值
    }


    public void changeChildState(int groupPosition, boolean groupChecked){
        List<Bean.DataBean.ListBean> listBeen = child_list.get(groupPosition);
        //通过一级列表的状态 设置子二级列表的全部状态
        for (int i = 0; i <listBeen.size() ; i++) {
          listBeen.get(i).setSelected(groupChecked? 1:0);
        }
    }

    public void setIfCheckAll(boolean checked) {
       //本方法是为了通过主类里的checkbox改变一级二级列表的值
        for (int i = 0;i<group_list.size();i++){
            Bean.DataBean dataBean = group_list.get(i);
            //设置组上面的checkBox是否选中
            dataBean.setGroupChecked(checked);

            List<Bean.DataBean.ListBean> listBeans = dataBean.getList();
            for (int j = 0; j< listBeans.size(); j++){
                //改变是否选中的状态...数据应该变的是
                listBeans.get(j).setSelected(checked? 1:0);
            }

        }
        //计算价钱和数量并且发送到mainActivity显示
        sendPriceAndCount();
        //刷新适配器
        notifyDataSetChanged();

    }
    private class GroupHolder{
        CheckBox group_checked;
        TextView group_text;
    }

    private class ChildHolder{
        CheckBox check_child;
        ImageView image_good;
        TextView text_title;
        TextView text_price;
        TextView text_jian;
        TextView text_num;
        TextView text_add;
    }

}
