package com.toast.wang.shuadan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.toast.wang.toastutils.ToastUtils;

public class MainActivity extends AppCompatActivity {
    private String url;
    private  String times;
    private int i=1;
    private WebView webView;
    private AppCompatEditText et_url;//网址
    private AppCompatEditText et_times;//次数
    private AppCompatButton btn_start;//开始
    private AppCompatTextView tv_times;//次数
    private AppCompatButton btn_stop;//停止
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initDatas();
    }
    private void initDatas() {
        WebSettings webSettings = webView.getSettings();
        webView.setVerticalScrollBarEnabled(false); //垂直不显示
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if(i<Integer.parseInt(times))
                {
                    i++;
                    webView.reload();
                    tv_times.setText("已完成"+i+"次");
                }
                else {

                }
            }
        });
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i=1;
             url=et_url.getText().toString().trim();
             times=et_times.getText().toString().trim();
             if(url.isEmpty()||times.isEmpty()){
                 ToastUtils.showShortToast(MainActivity.this,"网址或者次数不能为空");
             }else {
                 webView.loadUrl(url);
             }

            }
        });
        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webView.stopLoading();
                i=Integer.parseInt(times);
            }
        });

    }
    private void initViews() {
        webView=findViewById(R.id.web);
        et_url=findViewById(R.id.et_url);
        et_times=findViewById(R.id.et_times);
        btn_start=findViewById(R.id.btn_start);
        tv_times=findViewById(R.id.tv_times);
        btn_stop=findViewById(R.id.btn_stop);
    }

}
