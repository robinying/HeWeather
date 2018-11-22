package com.yubin.heweather.ui.webview;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;

import com.yubin.heweather.R;
import com.yubin.heweather.ui.base.BaseAppCompatActivity;

public class WebviewActivity extends BaseAppCompatActivity {

    public static final String URL = "URL";

    public static final String TITLE = "title";

    private WebviewFragment webviewFragment;

    public static void show(Context context, String url, String title) {
        if (context != null) {
            context.startActivity(new Intent(context, WebviewActivity.class)
                    .putExtra(URL, url)
                    .putExtra(TITLE, title));
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            webviewFragment = (WebviewFragment) getSupportFragmentManager().getFragment(savedInstanceState, WebviewFragment.class.getSimpleName());
        } else {
            webviewFragment = WebviewFragment.newInstance();
        }
        replaceFragment(R.id.main_view, webviewFragment);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_webview;
    }

    @Override
    protected void initView() {
        super.initView();
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void bindEvent() {
        super.bindEvent();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (webviewFragment.isAdded()) {
            getSupportFragmentManager().putFragment(outState, WebviewFragment.class.getSimpleName(), webviewFragment);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //To make the AgentWeb handle the "On BackPress" logic .
        if (webviewFragment.onFragmentKeyDown(keyCode, event)) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
