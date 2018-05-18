package org.zzy.driver.mvp.ui.activity;

import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import com.zzy.quick.mvp.ui.BaseActivity;
import com.zzy.quick.utils.NetworkUtils;

import org.zzy.driver.R;

import butterknife.BindView;

/**
 * 加载HTML
 * Created by zhou on 2018/5/16.
 */

public class WebViewLoadActivity extends BaseActivity {

    private static final String APP_CACAHE_DIRNAME = "/webcache";
    @BindView(R.id.ll_layout)
    LinearLayout llLayout;

    private WebSettings settings;
    private String url;
    private String title;
    private WebView mWebView;

    @Override
    public Object newPresenter() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_webview;
    }

    @Override
    public void initView() {
        super.initView();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mWebView = new WebView(getApplicationContext());
        mWebView.setLayoutParams(params);
        llLayout.addView(mWebView);
    }

    @Override
    public void initData() {
        url = getIntent().getStringExtra("url");
        title = getIntent().getStringExtra("title");
        getTopbar().setTitle(title);
        getTopbar().setLeftImageListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        setWebView();
        mWebView.loadUrl(url);
    }

    /**
     * 初始化WebView
     */
    private void setWebView() {
        settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setRenderPriority(WebSettings.RenderPriority.HIGH); //提高渲染等级
        mWebView.getSettings().setBlockNetworkImage(true);//把图片加载放在最后来加载渲染webView
        if (NetworkUtils.isConnected()) {
            //当前有可用网络
            settings.setCacheMode(WebSettings.LOAD_DEFAULT);  //设置 缓存模式( 根据cache-control决定是否从网络上取数据。)
        } else {
            //当前没有可用网络
            settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);  //设置 缓存模式(只要本地有，无论是否过期，或者no-cache，都使用缓存中的数据。)
        }

        //开启 database storage API 功能
        settings.setDatabaseEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setSupportZoom(true);
        settings.setAllowFileAccess(true);
        // 设置是否可缩放
        settings.setBuiltInZoomControls(true);
        settings.setLoadWithOverviewMode(true);
        settings.setSupportZoom(false);
        mWebView.requestFocus();
        String cacheDirPath = getFilesDir().getAbsolutePath() + APP_CACAHE_DIRNAME;
        //设置数据库缓存路径
        settings.setDatabasePath(cacheDirPath);
        //设置  Application Caches 缓存目录a
        settings.setAppCachePath(cacheDirPath);
        //开启 Application Caches 功能
        settings.setAppCacheEnabled(true);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //  重写此方法表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边
//                Map<String, String> map = new HashMap<String, String>();
//                map.put("Cache-Control", "only-if-cached");
//                map.put("if-Modified-Since", "Thu Aug 27 08:12:24 GMT+08:00 2015");
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });

    }


    @Override
    public void showError(String msg) {

    }

    @Override
    protected void onStop() {
        super.onStop();
        settings.setJavaScriptEnabled(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mWebView!=null){
            mWebView.loadDataWithBaseURL(null,"","text/html","utf-8",null);
            mWebView.clearHistory();
            //LinearLayout删除WebView
            ((ViewGroup)mWebView.getParent()).removeView(mWebView);
            mWebView.destroy();
            mWebView=null;
        }
    }


}
