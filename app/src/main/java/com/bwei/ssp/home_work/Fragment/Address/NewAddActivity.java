package com.bwei.ssp.home_work.Fragment.Address;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bwei.ssp.home_work.Fragment.Address.Addbean.Address_bean;
import com.bwei.ssp.home_work.Fragment.Address.Addbean.MorenBean;
import com.bwei.ssp.home_work.Fragment.Address.Addbean.NewAddbean;
import com.bwei.ssp.home_work.Fragment.Address.Address_Presenter.Add_Presenter;
import com.bwei.ssp.home_work.Fragment.Address.Address_View.Add_View;
import com.bwei.ssp.home_work.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class NewAddActivity extends AppCompatActivity implements Add_View {

    @InjectView(R.id.new_back)
    ImageView newBack;
    @InjectView(R.id.new_name)
    EditText newName;
    @InjectView(R.id.new_phone)
    EditText newPhone;
    @InjectView(R.id.new_addr)
    EditText newAddr;
    @InjectView(R.id.new_check)
    CheckBox newCheck;
    @InjectView(R.id.new_but)
    Button newBut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_add);
        ButterKnife.inject(this);
    }

    @OnClick({R.id.new_back, R.id.new_but})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.new_back:
                finish();
                break;
            case R.id.new_but:
                String name = newName.getText().toString();
                String addr = newAddr.getText().toString();
                String mobile = newPhone.getText().toString();
                Add_Presenter add_presenter = new Add_Presenter( this);
                add_presenter.getNewBean("2856", name, mobile, addr,"0");
                finish();
                break;
        }
    }

    @Override
    public void AddData(Address_bean bean) {

    }

    @Override
    public void AddNewBean(NewAddbean addbean) {
        Toast.makeText(NewAddActivity.this, addbean.getMsg(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void AddMorenBean(MorenBean morenBean) {

    }

    @OnClick(R.id.new_check)
    public void onViewClicked() {

    }
}
