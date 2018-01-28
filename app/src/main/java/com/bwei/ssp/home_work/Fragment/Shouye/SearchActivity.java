package com.bwei.ssp.home_work.Fragment.Shouye;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import com.bwei.ssp.home_work.Fragment.Shouye.Search.Myadapter;
import com.bwei.ssp.home_work.Fragment.Shouye.Search.SousuoActivity;
import com.bwei.ssp.home_work.Fragment.Shouye.Search.database.DaoSession;
import com.bwei.ssp.home_work.Fragment.Shouye.Search.database.SearchContentDao;
import com.bwei.ssp.home_work.Fragment.Shouye.Search.greendao.GrennDaoUtiils;
import com.bwei.ssp.home_work.Fragment.Shouye.Search.greendao.SearchContent;
import com.bwei.ssp.home_work.R;
import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechRecognizer;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class SearchActivity extends AppCompatActivity {

    @InjectView(R.id.seach)
    SearchView search;
    @InjectView(R.id.but)
    Button but;
    @InjectView(R.id.xlv)
    RecyclerView rlv;
    @InjectView(R.id.clear)
    Button clear;
    List<SearchContent> list = new ArrayList<>();
    @InjectView(R.id.back)
    ImageView back;
    @InjectView(R.id.showDialogs)
    Button showDialogs;

    private Toast mToast;
    private Myadapter myadapter;
    private DaoSession daoSession;
    private SearchContentDao searchContentDao;
    private List<SearchContent> searchContents;
    // 语音听写对象
    private SpeechRecognizer mIat;
    // 语音听写UI
    private RecognizerDialog mIatDialog;
    // 用HashMap存储听写结果
    private HashMap<String, String> mIatResults = new LinkedHashMap<String, String>();
    // 引擎类型
    private String mEngineType = SpeechConstant.TYPE_CLOUD;



    @SuppressLint("ShowToast")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ButterKnife.inject(this);
        SpeechUtility.createUtility(this, SpeechConstant.APPID + "=5a5210ed");
        // 初始化识别无UI识别对象
        // 使用SpeechRecognizer对象，可根据回调消息自定义界面；
        mIat = SpeechRecognizer.createRecognizer(this, mInitListener);
        // 初始化听写Dialog，如果只使用有UI听写功能，无需创建SpeechRecognizer
        // 使用UI听写功能，请根据sdk文件目录下的notice.txt,放置布局文件和图片资源
        mIatDialog = new RecognizerDialog(SearchActivity.this, mInitListener);
        this.showDialogs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogs.setText("123");// 清空显示内容
                mIatResults.clear();
                //  myRecognize();
                // 显示听写对话框
                mIatDialog.show();
                showTip(getString(R.string.text_begin));
            }
        });
        daoSession = GrennDaoUtiils.getDaoSession();
        searchContentDao = daoSession.getSearchContentDao();
        searchContents = searchContentDao.loadAll();
        myadapter = new Myadapter(SearchActivity.this, searchContents);
        myadapter.notifyDataSetChanged();
        rlv.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL));
        rlv.setAdapter(myadapter);

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //Toast.makeText(SearchActivity.this, "****" + query, Toast.LENGTH_LONG).show();
                SearchContent searchContent = new SearchContent(null, query);
                searchContentDao.insertInTx(searchContent);
                searchContents = searchContentDao.loadAll();
                myadapter = new Myadapter(SearchActivity.this, searchContents);
                myadapter.notifyDataSetChanged();
                rlv.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL));
                rlv.setAdapter(myadapter);
                clear.setVisibility(View.VISIBLE);
                Intent intent = new Intent(SearchActivity.this, SousuoActivity.class);
                intent.putExtra("key", query);
                startActivity(intent);
                return false;
            }

            @Override
            public boolean onQueryTextChange(final String newText) {

                but.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SearchContent searchContent = new SearchContent(null, newText);
                        searchContentDao.insertInTx(searchContent);
                        searchContents = searchContentDao.loadAll();
                        myadapter = new Myadapter(SearchActivity.this, searchContents);
                        myadapter.notifyDataSetChanged();
                        rlv.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL));
                        rlv.setAdapter(myadapter);
                        clear.setVisibility(View.VISIBLE);
                        Intent intent = new Intent(SearchActivity.this, SousuoActivity.class);
                        intent.putExtra("key", newText);
                        startActivity(intent);
                        // Toast.makeText(MainActivity.this,""+newText,Toast.LENGTH_LONG).show();
                    }
                });

                return true;
            }
        });
    }

    @OnClick(R.id.clear)
    public void onViewClicked() {

        searchContentDao.deleteAll();
        searchContents.clear();
        myadapter.notifyDataSetChanged();
        clear.setVisibility(View.GONE);

    }

    @OnClick(R.id.back)
    public void onViewbackClicked() {
        finish();
    }

    /**
     * 初始化监听器。
     */
    private InitListener mInitListener = new InitListener() {

        @Override
        public void onInit(int code) {
            Log.d("TAG", "SpeechRecognizer init() code = " + code);
            if (code != ErrorCode.SUCCESS) {
                showTip("初始化失败，错误码：" + code);
            }
        }
    };
    private void showTip(final String str) {
        this.mToast.makeText(this,str,Toast.LENGTH_SHORT).show();
    }
    private void myRecognize() {
        // 清空参数
        mIat.setParameter(SpeechConstant.PARAMS, null);
        // 设置听写引擎
        mIat.setParameter(SpeechConstant.ENGINE_TYPE, mEngineType);
        // 设置返回结果格式
        mIat.setParameter(SpeechConstant.RESULT_TYPE, "json");
        //设置引擎为转写
        mIatDialog.setParameter(SpeechConstant.DOMAIN, "iat");
        //设置识别语言为中文
        mIatDialog.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
        //设置方言为普通话
        mIatDialog.setParameter(SpeechConstant.ACCENT, "mandarin");
        //设置录音采样率为
        mIatDialog.setParameter(SpeechConstant.SAMPLE_RATE, "16000");
        //设置监听对象
        mIatDialog.setListener(recognizerDialogListener);
        // 设置语音前端点:静音超时时间，即用户多长时间不说话则当做超时处理
        mIat.setParameter(SpeechConstant.VAD_BOS, "4000");
        // 设置语音后端点:后端点静音检测时间，即用户停止说话多长时间内即认为不再输入， 自动停止录音
        mIat.setParameter(SpeechConstant.VAD_EOS, "4000");
        // 设置标点符号,设置为"0"返回结果无标点,设置为"1"返回结果有标点
        mIat.setParameter(SpeechConstant.ASR_PTT, "1");
        // 设置听写结果是否结果动态修正，为“1”则在听写过程中动态递增地返回结果，否则只在听写结束之后返回最终结果
        // 注：该参数暂时只对在线听写有效
        mIat.setParameter(SpeechConstant.ASR_DWA, "0");
    }

    private RecognizerDialogListener recognizerDialogListener = new RecognizerDialogListener() {
        //当说话说完后，会回调该方法，JSON字符串在result
        @Override
        public void onResult(RecognizerResult recognizerResult, boolean isLast) {
            Log.d("TAG", recognizerResult.getResultString());
            printResult(recognizerResult);
        }

        @Override
        public void onError(SpeechError error) {
            // TODO Auto-generated method stub
        }

    };

    private void printResult(RecognizerResult results) {
        String text = JsonParser.parseIatResult(results.getResultString());
        String sn = null;
        // 读取json结果中的sn字段
        try {
            JSONObject resultJson = new JSONObject(results.getResultString());
            sn = resultJson.optString("sn");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mIatResults.put(sn, text);
        StringBuffer resultBuffer = new StringBuffer();
        for (String key : mIatResults.keySet()) {
            resultBuffer.append(mIatResults.get(key));
        }
        showDialogs.setText(resultBuffer.toString());
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 退出时释放连接
        mIat.cancel();
        mIat.destroy();
    }

}
