package com.bwei.ssp.home_work.Fragment.me;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwei.ssp.home_work.Fragment.me.Me_bean.Me_bean;
import com.bwei.ssp.home_work.MainActivity;
import com.bwei.ssp.home_work.Okhttputils.GsonObjectCallback;
import com.bwei.ssp.home_work.Okhttputils.OkHttp3Utils;
import com.bwei.ssp.home_work.R;

import java.io.IOException;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import okhttp3.Call;

public class MeActivity extends AppCompatActivity {

    @InjectView(R.id.me_back)
    ImageView me_back;
    @InjectView(R.id.me_phone)
    TextView me_phone;
    @InjectView(R.id.me_name)
    TextView me_name;
    @InjectView(R.id.finsh)
    Button but;
    @InjectView(R.id.img)
    ImageView img;

    //相机权限
    public static final String CAMERA_PERMISSION = Manifest.permission.CAMERA;
    //相机权限请求码
    public static final int CAMERA_REQUEST_CODE = 100;
    public static final int CAMERA_SETTING_CODE = 10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);
        ButterKnife.inject(this);
        OkHttp3Utils.doGet("http://120.27.23.105/user/getUserInfo?uid=2856", new GsonObjectCallback<Me_bean>() {
            @Override
            public void onUi(Me_bean me_bean) {
                me_phone.setText(me_bean.getData().getMobile());
                me_name.setText(me_bean.getData().getNickname());
            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });

    }

    @OnClick({R.id.me_back, R.id.finsh})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.me_back:
                MeActivity.this.finish();
                break;
            case R.id.finsh:
                Intent intent = new Intent(MeActivity.this, MainActivity.class);
                startActivity(intent);
                break;
        }
    }

    @OnClick(R.id.img)
    public void onViewClicked() {
        if (ContextCompat.checkSelfPermission(this, CAMERA_PERMISSION) == PackageManager.PERMISSION_GRANTED) {
            gotoCamera();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{CAMERA_PERMISSION}, CAMERA_REQUEST_CODE);
        }
    }
    private void gotoCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            //如果是相机的权限请求码，就做相机的逻辑操作
            case CAMERA_REQUEST_CODE:
                //遍历所有的权限
                for (int i = 0; i < permissions.length; i++) {
                    //如果是相机权限的
                    if (permissions[i].equalsIgnoreCase(CAMERA_PERMISSION)) {
                        //如果相机权限被用户同意
                        if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                            gotoCamera();
                        } else {
                            //如果用户选择了不再提示，这个返回值是false  ，，  这个返回值是true，需要我们自己提示
                            if (ActivityCompat.shouldShowRequestPermissionRationale(this, CAMERA_PERMISSION)) {
                                //用户没有勾选
                                showSetPermissionPathTip();
                            } else {
                                //用户勾选了不再提示
                                showRationalePermissionTip();
                            }
                        }
                    }
                }

                break;
        }
    }
    private void showRationalePermissionTip() {
        new AlertDialog.Builder(this)
                .setTitle("说明")
                .setMessage("拍照需要相机权限是否打开")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        showSetPermissionPathTip();
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("取消", null)
                .show();
    }
    private void showSetPermissionPathTip() {
        new AlertDialog.Builder(this)
                .setTitle("提示")
                .setMessage("请于设置页面-permission-相机")
                .setPositiveButton("立即开启", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        gotoSetting();
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("取消", null)
                .show();
    }
    private void gotoSetting() {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, CAMERA_SETTING_CODE);
    }
    //当用户从设置页面返回应用的时候，会回调onActivityResult方法
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case CAMERA_SETTING_CODE:
                //再次检查一下用户是否打开了相机权限
                if (ContextCompat.checkSelfPermission(this, CAMERA_PERMISSION) == PackageManager.PERMISSION_GRANTED) {
                    gotoCamera();
                }
                break;
        }

    }

}
