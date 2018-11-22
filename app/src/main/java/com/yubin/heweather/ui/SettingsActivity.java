package com.yubin.heweather.ui;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yubin.heweather.R;
import com.yubin.heweather.config.Constant;
import com.yubin.heweather.ui.base.BaseAppCompatActivity;
import com.yubin.heweather.ui.webview.WebviewActivity;
import com.yubin.heweather.utils.CheckUpdate;
import com.yubin.heweather.utils.FileSizeUtil;
import com.yubin.heweather.utils.FileUtil;
import com.yubin.heweather.utils.ImageLoader;
import com.yubin.heweather.utils.TDevice;
import com.yubin.heweather.view.ItemView;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SettingsActivity extends BaseAppCompatActivity {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.clear_cache)
    ItemView clearCache;
    @BindView(R.id.version_settings)
    ItemView versionSettings;
    @BindView(R.id.update_settings)
    ItemView updateSettings;
    @BindView(R.id.github_iv)
    ImageView githubIv;
    @BindView(R.id.github_tv)
    TextView githubTv;
    @BindView(R.id.github_ll)
    LinearLayout githubLl;

    public static void show(Context context) {
        if (context != null) {
            context.startActivity(new Intent(context, SettingsActivity.class));
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_settings;
    }

    @Override
    protected void initView() {
        super.initView();
        setSupportActionBar(toolbar);

        setTitle(R.string.title_activity_settings);
        clearCache.setSubTypeTitleVal(FileSizeUtil.getAutoFileOrFilesSize(Constant.NET_CACHE));
        versionSettings.setSubTypeTitleVal(TDevice.getVersionName());
    }

    @Override
    protected void initData() {
        super.initData();
    }



    @OnClick({R.id.clear_cache, R.id.version_settings, R.id.update_settings, R.id.github_ll})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.clear_cache:
                ImageLoader.clear(this);
                Observable.just(FileUtil.delete(new File(Constant.NET_CACHE)))
                        .filter(aBoolean -> aBoolean)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnNext(aBoolean -> {
                            clearCache.setSubTypeTitleVal(FileSizeUtil.getAutoFileOrFilesSize(Constant.NET_CACHE));
                            Snackbar.make(view, "缓存已清除", Snackbar.LENGTH_SHORT).show();
                        })
                        .subscribe();
                break;
            case R.id.version_settings:
                break;
            case R.id.update_settings:
                CheckUpdate checkUpdate = new CheckUpdate(this);
                checkUpdate.checkUpdate();
                break;
            case R.id.github_ll:
                WebviewActivity.show(this,"https://github.com/robinying/HeWeather","Github");
                break;
        }
    }
}
