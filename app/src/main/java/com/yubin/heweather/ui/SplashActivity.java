package com.yubin.heweather.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.yubin.heweather.R;

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
        mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                MainActivity.show(mContext);
                finish();
            }
        },2000);
    }


}
