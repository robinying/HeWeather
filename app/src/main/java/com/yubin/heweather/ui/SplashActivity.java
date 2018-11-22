package com.yubin.heweather.ui;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.tbruyelle.rxpermissions2.RxPermissions;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.PermissionListener;
import com.yanzhenjie.permission.RationaleListener;
import com.yubin.heweather.R;
import com.yubin.heweather.config.Constant;
import com.yubin.heweather.utils.Basepreference;

import butterknife.BindView;
import butterknife.ButterKnife;
import android.os.Handler;

import java.util.List;

public class SplashActivity extends AppCompatActivity {

    @BindView(R.id.splash_iv)
    ImageView splashIv;
    private Handler mHandler;
    private Context mContext;
    private final int REQUEST_PERMISSIONS =100;
    private boolean jumpToMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        mContext = this;
        checkPermissions();
        jumpToMain = Basepreference.getBoolean(Constant.JUMP_TO_MAIN,false);
        mHandler = new Handler();
        if (jumpToMain) {
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    MainActivity.show(mContext);
                    finish();
                }
            }, 2000);
        }
    }

    private void checkPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (AndPermission.hasPermission(SplashActivity.this, Constant.REQUEST_PERMISSIONS)) {
            } else {
                AndPermission.with(this)
                        .requestCode(REQUEST_PERMISSIONS)
                        .permission(Constant.REQUEST_PERMISSIONS)
                        .callback(new PermissionListener() {
                            @Override
                            public void onSucceed(int requestCode,  List<String> grantPermissions) {
                                Basepreference.putBoolean(Constant.JUMP_TO_MAIN,true);
                                mHandler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        MainActivity.show(mContext);
                                        finish();
                                    }
                                }, 1000);
                            }

                            @Override
                            public void onFailed(int requestCode, List<String> deniedPermissions) {
                                AndPermission.defaultSettingDialog(SplashActivity.this, REQUEST_PERMISSIONS)
                                        .setTitle("权限申请失败")
                                        .setMessage("您已禁用 \"读写手机存储\"和\"定位\" 权限，请在设置中授权！")
                                        .setPositiveButton("好，去设置")
                                        .show();
                            }
                        })
                        .rationale(new RationaleListener() {
                            @Override
                            public void showRequestPermissionRationale(int requestCode, Rationale rationale) {
                                AndPermission.rationaleDialog(SplashActivity.this, rationale).show();
                            }
                        })
                        .start();
            }
        }
    }


}
