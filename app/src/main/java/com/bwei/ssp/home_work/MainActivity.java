package com.bwei.ssp.home_work;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bwei.ssp.home_work.Acticity.ZhuceActivity;
import com.bwei.ssp.home_work.Acticity.ZhujiemianActivity;
import com.bwei.ssp.home_work.Login.bean.Bean;
import com.bwei.ssp.home_work.Login.bean.Userbean;
import com.bwei.ssp.home_work.Login.presenter.Presenter;
import com.bwei.ssp.home_work.Login.view.Myview;
import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements Myview {
    @InjectView(R.id.ed_phone)
    EditText ed_pone;
    @InjectView(R.id.ed_pass)
    EditText ed_pass;
    @InjectView(R.id.zc)
    TextView zc;
    @InjectView(R.id.login)
    Button login;
    @InjectView(R.id.qq)
    ImageView qq;

    private Tencent mTencent;
    private String APP_ID = "222222";
    private IUiListener loginListener;
    private String SCOPE = "all";


    private IUiListener userInfoListener;
    private String TAG;
    private UserInfo mUserInfo;

    private Presenter p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        p = new Presenter(this);
    }

    @OnClick({R.id.zc, R.id.login, R.id.qq})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.zc:
                Intent intent = new Intent(MainActivity.this, ZhuceActivity.class);
                startActivity(intent);
                break;
            case R.id.login:
                p.getData(new Bean(ed_pone.getText().toString(), ed_pass.getText().toString()));
                break;
            case R.id.qq:
                initQqLogin();
                mTencent.login(MainActivity.this, SCOPE, loginListener);
                break;
        }
    }
    private void initQqLogin() {
        mTencent = Tencent.createInstance(APP_ID, this);
        loginListener = new IUiListener() {
            @Override
            public void onComplete(Object o) {
                //登录成功后回调该方法
                Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "response:" + o);
                JSONObject obj = (JSONObject) o;
                try {
                    String openID = obj.getString("openid");
                    String accessToken = obj.getString("access_token");
                    String expires = obj.getString("expires_in");
                    mTencent.setOpenId(openID);
                    mTencent.setAccessToken(accessToken, expires);
                    QQToken qqToken = mTencent.getQQToken();
                    mUserInfo = new UserInfo(getApplicationContext(), qqToken);
                    mUserInfo.getUserInfo(new IUiListener() {
                        @Override
                        public void onComplete(Object response) {
                            Log.e(TAG, "登录成功" + response.toString());
                            Intent intent = new Intent(MainActivity.this, ZhujiemianActivity.class);
                            intent.putExtra("json", response.toString());
                            startActivity(intent);
                        }

                        @Override
                        public void onError(UiError uiError) {
                            Log.e(TAG, "登录失败" + uiError.toString());
                        }

                        @Override
                        public void onCancel() {
                            Log.e(TAG, "登录取消");

                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(UiError uiError) {
                //登录失败后回调该方法
                Toast.makeText(MainActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                Log.e("LoginError:", uiError.toString());
            }

            @Override
            public void onCancel() {
                //取消登录后回调该方法
                Toast.makeText(MainActivity.this, "取消登录", Toast.LENGTH_SHORT).show();
            }
        };

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.REQUEST_LOGIN) {
            if (resultCode == -1) {
//                Tencent.onActivityResultData(requestCode, resultCode, data, loginListener);
//                Tencent.handleResultData(data, loginListener);

                Tencent.onActivityResultData(requestCode, resultCode, data, loginListener);
                Tencent.handleResultData(data, loginListener);
                UserInfo info = new UserInfo(this, mTencent.getQQToken());
                info.getUserInfo(userInfoListener);
            }
        }
    }



    @Override
    public void getYes(Userbean userbean) {
        Toast.makeText(MainActivity.this, "成功", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(MainActivity.this, ZhujiemianActivity.class);
        startActivity(intent);
    }

    @Override
    public void getNo(Throwable throwable) {
        Toast.makeText(MainActivity.this, "失败", Toast.LENGTH_LONG).show();
    }
}
