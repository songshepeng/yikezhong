package com.bwei.ssp.home_work.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwei.ssp.home_work.Fragment.Address.AddressActivity;
import com.bwei.ssp.home_work.Fragment.me.MeActivity;
import com.bwei.ssp.home_work.R;
import com.bwei.ssp.home_work.order.OrderActivity;
import com.facebook.drawee.backends.pipeline.Fresco;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by lenovo on 2017/12/6.
 */

public class wode extends Fragment {

    @InjectView(R.id.touxiang)
    ImageView touxiang;
    @InjectView(R.id.me_order)
    TextView me_order;
    @InjectView(R.id.name_tv)
    TextView nameTv;
    @InjectView(R.id.manny)
    TextView manny;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Fresco.initialize(getContext());
        View v = View.inflate(getActivity(), R.layout.fragment4, null);
        ButterKnife.inject(this, v);
        Uri parse = Uri.parse("http://img0.imgtn.bdimg.com/it/u=675395133,1700956425&fm=27&gp=0.jpg");
        touxiang.setImageURI(parse);
        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.touxiang, R.id.me_order})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.touxiang:
                Intent integer = new Intent(getActivity(), MeActivity.class);
                startActivity(integer);
                break;
            case R.id.me_order:
                Intent integer1 = new Intent(getActivity(), OrderActivity.class);
                startActivity(integer1);
                break;
        }
    }

    @OnClick(R.id.name_tv)
    public void onViewClicked() {

    }

    @OnClick(R.id.manny)
    public void onmannyViewClicked() {
           startActivity(new Intent(getActivity(), AddressActivity.class));
    }
}
