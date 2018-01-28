package com.bwei.ssp.home_work.Fragment.Address;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.bwei.ssp.home_work.Fragment.Address.Addbean.Address_bean;
import com.bwei.ssp.home_work.Fragment.Address.Addbean.MorenBean;
import com.bwei.ssp.home_work.Fragment.Address.Addbean.NewAddbean;
import com.bwei.ssp.home_work.Fragment.Address.Address_Presenter.Add_Presenter;
import com.bwei.ssp.home_work.Fragment.Address.Address_View.Add_View;
import com.bwei.ssp.home_work.Fragment.Address.adapter.AddAdapter;
import com.bwei.ssp.home_work.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class AddressActivity extends AppCompatActivity implements Add_View {

    @InjectView(R.id.add_rlv)
    RecyclerView addRlv;
    @InjectView(R.id.add_but)
    Button addBut;
    private Add_Presenter add_presenter;
    private AddAdapter addAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        ButterKnife.inject(this);
        add_presenter = new Add_Presenter(this);
        add_presenter.getBean();
    }



    @Override
    protected void onResume() {
        super.onResume();
      add_presenter.getBean();

    }

    @Override
    public void AddData(Address_bean bean) {
        Log.e("add", bean.getMsg() + "321");
        addRlv.setLayoutManager(new LinearLayoutManager(AddressActivity.this));
        addAdapter = new AddAdapter(AddressActivity.this, bean.getData());
        addAdapter.notifyDataSetChanged();
        addRlv.setAdapter(addAdapter);
        addAdapter.setListen(new AddAdapter.cheOchick() {
            @Override
            public void onCheonchick(View v, int Position) {

                add_presenter.getBean();
                addAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void AddNewBean(NewAddbean addbean) {

    }

    @Override
    public void AddMorenBean(MorenBean morenBean) {

    }

    @OnClick(R.id.add_but)
    public void onViewClicked() {
        startActivity(new Intent(AddressActivity.this,NewAddActivity.class));

    }
}
