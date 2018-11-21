package com.yubin.heweather.ui.fragment;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yubin.heweather.R;
import com.yubin.heweather.bean.AirBean;
import com.yubin.heweather.bean.AirNowCityBean;
import com.yubin.heweather.bean.AirNowStationBean;
import com.yubin.heweather.bean.DailyForecastBean;
import com.yubin.heweather.bean.WeatherBean;
import com.yubin.heweather.ui.base.BaseViewHolder;
import com.yubin.heweather.utils.Basepreference;
import com.yubin.heweather.utils.ImageLoader;
import com.yubin.heweather.utils.TimeUitl;
import com.yubin.heweather.utils.XLog;

import org.w3c.dom.Text;

import java.util.Locale;

import butterknife.BindView;

/**
 * author : Yubin.Ying
 * time : 2018/11/20
 */
public class AirAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static String TAG = AirAdapter.class.getSimpleName();

    private Context mContext;
    private static final int TYPE_NOW_CITY = 0;
    private static final int TYPE_NOW_STATION = 1;

    private AirBean.HeWeather6Bean airData;

    public AirAdapter(AirBean.HeWeather6Bean airData) {
        this.airData = airData;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        switch (viewType) {
            case TYPE_NOW_CITY:
                return new NowAirCityViewHolder(
                        LayoutInflater.from(mContext).inflate(R.layout.item_now_city, parent, false));
            case TYPE_NOW_STATION:
                return new NowStationViewHolder(
                        LayoutInflater.from(mContext).inflate(R.layout.item_station, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int itemType = getItemViewType(position);
        switch (itemType) {
            case TYPE_NOW_CITY:
                ((NowAirCityViewHolder) holder).bind(airData);
                break;
            case TYPE_NOW_STATION:
                ((NowStationViewHolder) holder).bind(airData);
                break;
            default:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return airData.getStatus() != null ? 2 : 0;
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                return TYPE_NOW_CITY;
            case 1:
                return TYPE_NOW_STATION;

        }
        return super.getItemViewType(position);
    }

    /*
    *当前城市的空气质量
     */
    class NowAirCityViewHolder extends BaseViewHolder<AirBean.HeWeather6Bean> {

        @BindView(R.id.air_city_aqi)
        TextView airCityAqi;
        @BindView(R.id.air_city_qlty)
        TextView airCityQlty;
        @BindView(R.id.cont_tv)
        TextView contTv;
        @BindView(R.id.pm25_num)
        TextView pm25Num;
        @BindView(R.id.so2_num)
        TextView so2Num;
        @BindView(R.id.co_num)
        TextView coNum;
        @BindView(R.id.pm10_num)
        TextView pm10Num;
        @BindView(R.id.no2_num)
        TextView no2Num;
        @BindView(R.id.o3_num)
        TextView o3Num;
        @BindView(R.id.push_time)
        TextView pushTime;

        NowAirCityViewHolder(View itemView) {
            super(itemView);
        }

        protected void bind(AirBean.HeWeather6Bean airData) {
            try {
                AirNowCityBean airNowCityBean = airData.getAir_now_city();
                String airAqi = String.format(Locale.CHINA, "空气质量指数--%s", airNowCityBean.getAqi());
                SpannableStringBuilder ssb = new SpannableStringBuilder(airAqi);
                airCityQlty.setText(airNowCityBean.getQlty());
                switch (airNowCityBean.getQlty()){
                    case "优":
                        airCityQlty.setTextColor(mContext.getResources().getColor(R.color.green_500));
                        ssb.setSpan(new ForegroundColorSpan(mContext.getResources().getColor(R.color.green_500)),8,airAqi.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        airCityAqi.setText(ssb);
                        break;
                    case"良":
                        airCityQlty.setTextColor(mContext.getResources().getColor(R.color.amber_500));
                        ssb.setSpan(new ForegroundColorSpan(mContext.getResources().getColor(R.color.amber_500)),8,airAqi.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        airCityAqi.setText(ssb);
                        break;
                    case "轻度污染":
                        airCityQlty.setTextColor(mContext.getResources().getColor(R.color.orange_500));
                        ssb.setSpan(new ForegroundColorSpan(mContext.getResources().getColor(R.color.orange_500)),8,airAqi.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        airCityAqi.setText(ssb);
                        break;
                    case "中度污染":
                        airCityQlty.setTextColor(mContext.getResources().getColor(R.color.orange_500));
                        ssb.setSpan(new ForegroundColorSpan(mContext.getResources().getColor(R.color.orange_500)),8,airAqi.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        airCityAqi.setText(ssb);
                        break;
                    case "重度污染":
                        airCityQlty.setTextColor(mContext.getResources().getColor(R.color.pink_500));
                        ssb.setSpan(new ForegroundColorSpan(mContext.getResources().getColor(R.color.pink_500)),8,airAqi.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        airCityAqi.setText(ssb);
                        break;
                    case "严重污染":
                        airCityQlty.setTextColor(mContext.getResources().getColor(R.color.drak_red));
                        ssb.setSpan(new ForegroundColorSpan(mContext.getResources().getColor(R.color.drak_red)),8,airAqi.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        airCityAqi.setText(ssb);
                        break;
                }
                pm25Num.setText(airNowCityBean.getPm25());
                so2Num.setText(airNowCityBean.getSo2());
                coNum.setText(airNowCityBean.getCo());
                pm10Num.setText(airNowCityBean.getPm10());
                no2Num.setText(airNowCityBean.getNo2());
                o3Num.setText(airNowCityBean.getO3());
                pushTime.setText(String.format("数据更新时间：%s",airNowCityBean.getPub_time()));
            } catch (Exception e) {
                XLog.d(TAG, e.toString());
            }
        }
    }

    /**
     * 监测站的空气质量
     */
    class NowStationViewHolder extends BaseViewHolder<AirBean.HeWeather6Bean> {
        private LinearLayout nowStation;
        private TextView[] stationName = new TextView[airData.getAir_now_station().size()];
        private TextView[] stationQlty = new TextView[airData.getAir_now_station().size()];
        private TextView[] stationAqi = new TextView[airData.getAir_now_station().size()];
        private TextView[] stationMain = new TextView[airData.getAir_now_station().size()];

        NowStationViewHolder(View itemView) {
            super(itemView);
            nowStation = (LinearLayout) itemView.findViewById(R.id.item_now_station);
            for (int i = 0; i < airData.getAir_now_station().size(); i++) {
                View view = View.inflate(mContext, R.layout.item_station_line, null);
                stationName[i] = (TextView) view.findViewById(R.id.station_name);
                stationQlty[i] = (TextView) view.findViewById(R.id.station_qlty_tv);
                stationAqi[i] = (TextView) view.findViewById(R.id.station_aqi);
                stationMain[i] = (TextView) view.findViewById(R.id.station_main_txt);
                nowStation.addView(view);
            }
        }

        protected void bind(AirBean.HeWeather6Bean airData) {
            try {
                for (int i = 0; i < airData.getAir_now_station().size(); i++) {
                    AirNowStationBean airNowStationBean = airData.getAir_now_station().get(i);
                    stationName[i].setText(String.format(Locale.CHINA, "监测站:%s", airNowStationBean.getAir_sta()));
                    stationQlty[i].setText(airNowStationBean.getQlty());
                    stationAqi[i].setText(airNowStationBean.getAqi());
                    stationMain[i].setText(String.format(Locale.CHINA, "PM25: %s, PM10: %s, NO2: %s, SO2: %s, CO: %s, O3: %s",
                            airNowStationBean.getPm25(), airNowStationBean.getPm10(), airNowStationBean.getNo2(), airNowStationBean.getSo2(), airNowStationBean.getCo(),
                            airNowStationBean.getO3()));
                }
            } catch (Exception e) {
                XLog.e(TAG,e.toString());
            }
        }
    }

}
