package com.yubin.heweather.utils;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.TextView;
import android.widget.Toast;

import com.allenliu.versionchecklib.callback.APKDownloadListener;
import com.allenliu.versionchecklib.core.http.HttpParams;
import com.allenliu.versionchecklib.core.http.HttpRequestMethod;
import com.allenliu.versionchecklib.v2.AllenVersionChecker;
import com.allenliu.versionchecklib.v2.builder.DownloadBuilder;
import com.allenliu.versionchecklib.v2.builder.UIData;
import com.allenliu.versionchecklib.v2.callback.CustomVersionDialogListener;
import com.allenliu.versionchecklib.v2.callback.RequestVersionListener;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.JsonObject;
import com.yubin.heweather.R;
import com.yubin.heweather.config.Constant;
import com.yubin.heweather.view.BaseDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

/**
 * author : Yubin.Ying
 * time : 2018/11/22
 */
public class CheckUpdate {
    private DownloadBuilder builder;
    private Activity mActivity;
    private String downloadUrl;
    private final String TAG = CheckUpdate.class.getSimpleName();

    public CheckUpdate(Activity activity) {
        mActivity = activity;
    }

    public void checkUpdate() {
        HttpParams httpParam = new HttpParams();
        httpParam.put("api_token", Constant.FIR_TOKEN);
        builder = AllenVersionChecker
                .getInstance()
                .requestVersion()
                .setRequestMethod(HttpRequestMethod.GET)
                .setRequestParams(httpParam)
                .setRequestUrl("http://api.fir.im/apps/latest/5bf65613ca87a8761fd30c2e")
                .request(new RequestVersionListener() {
                    @Nullable
                    @Override
                    public UIData onRequestVersionSuccess(String result) {
                        JSONObject jsonObject = string2JSON(result);
                        String versionshort = jsonObject.optString("versionShort");
                        downloadUrl = jsonObject.optString("installUrl");
                        String currentverison = TDevice.getVersionName();
                        XLog.d(TAG,"versionshort="+versionshort +"  downloadUrl ="+downloadUrl);
                        if (currentverison.compareTo(versionshort) < 0) {
                            return crateUIData();
                        } else {
                            Toast.makeText(mActivity, "已经是最新版本", Toast.LENGTH_SHORT).show();
                            return null;
                        }

                    }

                    @Override
                    public void onRequestVersionFailure(String message) {
                        Toast.makeText(mActivity, message, Toast.LENGTH_SHORT).show();
                    }
                });
        builder.setApkDownloadListener(new APKDownloadListener() {
            @Override
            public void onDownloading(int progress) {

            }

            @Override
            public void onDownloadSuccess(File file) {
                ToastUtils.showShort(file.getPath());
            }

            @Override
            public void onDownloadFail() {

            }
        });
        builder.setCustomVersionDialogListener(createCustomDialog());
        builder.executeMission(mActivity);
    }

    private CustomVersionDialogListener createCustomDialog() {
        return (context, versionBundle) -> {
            BaseDialog baseDialog = new BaseDialog(context, R.style.BaseDialog, R.layout.custom_dialog_layout);
            TextView textView = baseDialog.findViewById(R.id.tv_msg);
            textView.setText(versionBundle.getContent());
            baseDialog.setCanceledOnTouchOutside(true);
            return baseDialog;
        };
    }

    /**
     * @return
     * @important 使用请求版本功能，可以在这里设置downloadUrl
     * 这里可以构造UI需要显示的数据
     * UIData 内部是一个Bundle
     */
    private UIData crateUIData() {
        UIData uiData = UIData.create();
        uiData.setTitle(mActivity.getString(R.string.update_title));
        uiData.setDownloadUrl(downloadUrl);
        uiData.setContent(mActivity.getString(R.string.updatecontent));
        return uiData;
    }

    private JSONObject string2JSON(String str) {
        try {
            return new JSONObject(str);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new JSONObject();
    }
}
