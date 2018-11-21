package com.yubin.heweather.ui;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.tbruyelle.rxpermissions2.RxPermissions;
import com.yubin.heweather.R;
import com.yubin.heweather.config.Constant;
import com.yubin.heweather.utils.Basepreference;

import butterknife.BindView;
import butterknife.ButterKnife;
import android.os.Handler;

public class SplashActivity extends AppCompatActivity {

    @BindView(R.id.splash_iv)
    ImageView splashIv;
    private Handler mHandler;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        mContext = this;
        checkPermissions();
        mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                MainActivity.show(mContext);
                finish();
            }
        }, 2000);
    }

    private void checkPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            final RxPermissions rxPermissions = new RxPermissions(this);
            rxPermissions.request(Constant.REQUEST_PERMISSIONS).subscribe(granted -> {
                if (granted) { // Always true pre-M
                    //mLocation.startLocation();
                } else {
                    // Oups permission denied
                }
            });
        }
    }


}
