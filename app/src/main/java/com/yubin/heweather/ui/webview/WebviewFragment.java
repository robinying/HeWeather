package com.yubin.heweather.ui.webview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;

import com.just.agentweb.AgentWeb;
import com.yubin.heweather.R;
import com.yubin.heweather.ui.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * author : Yubin.Ying
 * time : 2018/11/22
 */
public class WebviewFragment extends BaseFragment {
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.webview_container)
    FrameLayout webviewContainer;
    private AgentWeb agentWeb;
    private String url;
    private String title;

    public WebviewFragment() {

    }

    public static WebviewFragment newInstance() {
        return new WebviewFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_webview;
    }

    @Override
    protected void initView(View root) {
        super.initView(root);
        WebviewActivity activity = (WebviewActivity) getActivity();
        Intent intent = getActivity().getIntent();
        url = intent.getStringExtra(WebviewActivity.URL);
        title = intent.getStringExtra(WebviewActivity.TITLE);
        activity.setSupportActionBar(toolBar);
        getActivity().setTitle(title);
        loadUrl(url);
    }


    private void loadUrl(String url) {
        agentWeb = AgentWeb.with(this)
                .setAgentWebParent(webviewContainer, new FrameLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()
                .createAgentWeb()
                .ready()
                .go(url);

        WebView webView = agentWeb.getWebCreator().getWebView();
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);
        settings.setUseWideViewPort(true);
        settings.setLoadsImagesAutomatically(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

    }

    public boolean onFragmentKeyDown(int keyCode, KeyEvent event) {
        return agentWeb.handleKeyEvent(keyCode, event);
    }

    @Override
    public void onResume() {
        if (agentWeb != null) {
            agentWeb.getWebLifeCycle().onResume();
        }
        super.onResume();
    }

    @Override
    public void onPause() {
        if (agentWeb != null) {
            agentWeb.getWebLifeCycle().onPause();
        }
        super.onPause();
    }

    @Override
    public void onDestroy() {
        if (agentWeb != null) {
            agentWeb.getWebLifeCycle().onDestroy();
        }
        super.onDestroy();
    }

}
