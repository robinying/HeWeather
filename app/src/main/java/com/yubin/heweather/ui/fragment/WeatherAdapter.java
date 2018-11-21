package com.yubin.heweather.ui.fragment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yubin.heweather.R;
import com.yubin.heweather.bean.DailyForecastBean;
import com.yubin.heweather.bean.LifestyleBean;
import com.yubin.heweather.bean.WeatherBean;
import com.yubin.heweather.ui.base.BaseViewHolder;
import com.yubin.heweather.utils.Basepreference;
import com.yubin.heweather.utils.ImageLoader;
import com.yubin.heweather.utils.TimeUitl;
import com.yubin.heweather.utils.XLog;

import java.util.Locale;

import butterknife.BindView;

/**
 * author : Yubin.Ying
 * time : 2018/11/20
 */
public class WeatherAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static String TAG = WeatherAdapter.class.getSimpleName();

    private Context mContext;
    private static final int TYPE_NOW = 0;
    private static final int TYPE_HOURLY = 1;
    private static final int TYPE_SUGGESTION = 2;
    private static final int TYPE_FORCAST = 3;

    private WeatherBean.HeWeather6Bean mWeatherData;

    public WeatherAdapter(WeatherBean.HeWeather6Bean weatherData) {
        this.mWeatherData = weatherData;
    }

    public void updateData(WeatherBean.HeWeather6Bean weatherData) {
        mWeatherData = weatherData;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        mContext = viewGroup.getContext();
        switch (viewType) {
            case TYPE_NOW:
                return new NowWeatherViewHolder(
                        LayoutInflater.from(mContext).inflate(R.layout.item_now_tmp, viewGroup, false));
            case TYPE_HOURLY:
                return new HoursWeatherViewHolder(
                        LayoutInflater.from(mContext).inflate(R.layout.item_hourly, viewGroup, false));
            case TYPE_SUGGESTION:
                return new SuggestionViewHolder(
                        LayoutInflater.from(mContext).inflate(R.layout.item_suggestion, viewGroup, false));
            case TYPE_FORCAST:
                return new ForecastViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_forcast, viewGroup, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int itemType = getItemViewType(position);
        switch (itemType) {
            case TYPE_NOW:
                ((NowWeatherViewHolder) holder).bind(mWeatherData);
                break;
            case TYPE_HOURLY:
                ((HoursWeatherViewHolder) holder).bind(mWeatherData);
                break;
            case TYPE_SUGGESTION:
                ((SuggestionViewHolder) holder).bind(mWeatherData);
                break;
            case TYPE_FORCAST:
                ((ForecastViewHolder) holder).bind(mWeatherData);
                break;
            default:
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                return TYPE_NOW;
            case 1:
                return TYPE_HOURLY;
            case 2:
                return TYPE_SUGGESTION;
            case 3:
                return TYPE_FORCAST;
        }
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return mWeatherData.getStatus() != null ? 4 : 0;
    }

    /**
     * 当前天气情况
     */
    class NowWeatherViewHolder extends BaseViewHolder<WeatherBean.HeWeather6Bean> {

        @BindView(R.id.weather_icon)
        ImageView weatherIcon;
        @BindView(R.id.temp_flu)
        TextView tempFlu;
        @BindView(R.id.temp_max)
        TextView tempMax;
        @BindView(R.id.temp_min)
        TextView tempMin;
        @BindView(R.id.location_city)
        TextView locationCity;
        @BindView(R.id.location_discrtict)
        TextView locationDiscrtict;

        NowWeatherViewHolder(View itemView) {
            super(itemView);
        }

        protected void bind(WeatherBean.HeWeather6Bean weather) {
            try {
                tempFlu.setText(String.format("%s℃", weather.getNow().getTmp()));
                tempMax.setText(
                        String.format("↑ %s ℃", weather.getDaily_forecast().get(0).getTmp_max()));
                tempMin.setText(
                        String.format("↓ %s ℃", weather.getDaily_forecast().get(0).getTmp_min()));

                ImageLoader.load(itemView.getContext(),
                        Basepreference.getInt(weather.getNow().getCond_txt(), R.drawable.ic_unknown_weather),
                        weatherIcon);
                locationCity.setText(weather.getBasic().getParent_city());
                locationDiscrtict.setText(weather.getBasic().getLocation());
            } catch (Exception e) {
                XLog.d(TAG, e.toString());
            }
        }
    }

    /**
     * 当日小时预告
     */
    private class HoursWeatherViewHolder extends BaseViewHolder<WeatherBean.HeWeather6Bean> {
        private LinearLayout itemHourInfoLayout;
        private TextView[] mClock = new TextView[mWeatherData.getHourly().size()];
        private TextView[] mTemp = new TextView[mWeatherData.getHourly().size()];
        private TextView[] mPop = new TextView[mWeatherData.getHourly().size()];
        private TextView[] mWind = new TextView[mWeatherData.getHourly().size()];

        HoursWeatherViewHolder(View itemView) {
            super(itemView);
            itemHourInfoLayout = (LinearLayout) itemView.findViewById(R.id.item_hour_info_linearlayout);

            for (int i = 0; i < mWeatherData.getHourly().size(); i++) {
                View view = View.inflate(mContext, R.layout.itme_hour_line, null);
                mClock[i] = (TextView) view.findViewById(R.id.one_clock);
                mTemp[i] = (TextView) view.findViewById(R.id.one_temp);
                mPop[i] = (TextView) view.findViewById(R.id.one_pop);
                mWind[i] = (TextView) view.findViewById(R.id.one_wind);
                itemHourInfoLayout.addView(view);
            }
        }

        protected void bind(WeatherBean.HeWeather6Bean weatherData) {

            try {
                for (int i = 0; i < weatherData.getHourly().size(); i++) {
                    //s.subString(s.length-3,s.length);
                    //第一个参数是开始截取的位置，第二个是结束位置。
                    String mDate = weatherData.getHourly().get(i).getTime();
                    mClock[i].setText(
                            mDate.substring(mDate.length() - 5, mDate.length()));
                    mTemp[i].setText(
                            String.format("%s℃", weatherData.getHourly().get(i).getTmp()));
                    mPop[i].setText(
                            String.format("%s%%", weatherData.getHourly().get(i).getPop())
                    );
                    mWind[i].setText(
                            String.format("%sKm/h", weatherData.getHourly().get(i).getWind_spd())
                    );
                }
            } catch (Exception e) {
                XLog.d(TAG,e.toString());
            }
        }
    }

    /**
     * 当日建议
     */
    class SuggestionViewHolder extends BaseViewHolder<WeatherBean.HeWeather6Bean> {
        @BindView(R.id.cloth_brief)
        TextView clothBrief;
        @BindView(R.id.cloth_txt)
        TextView clothTxt;
        @BindView(R.id.sport_brief)
        TextView sportBrief;
        @BindView(R.id.sport_txt)
        TextView sportTxt;
        @BindView(R.id.travel_brief)
        TextView travelBrief;
        @BindView(R.id.travel_txt)
        TextView travelTxt;
        @BindView(R.id.flu_brief)
        TextView fluBrief;
        @BindView(R.id.flu_txt)
        TextView fluTxt;
        @BindView(R.id.uv_brief)
        TextView uvBrief;
        @BindView(R.id.uv_txt)
        TextView uvText;
        @BindView(R.id.comf_brief)
        TextView comfBrief;
        @BindView(R.id.comf_txt)
        TextView comfTxt;
        @BindView(R.id.air_brief)
        TextView airBrief;
        @BindView(R.id.air_txt)
        TextView airTxt;
        @BindView(R.id.cw_brief)
        TextView cwBrief;
        @BindView(R.id.cw_txt)
        TextView cwTxt;
        SuggestionViewHolder(View itemView) {
            super(itemView);
        }

        protected void bind(WeatherBean.HeWeather6Bean weather) {
            try {
                for (int i = 0; i < weather.getLifestyle().size(); i++) {
                    LifestyleBean lifestyleBean = weather.getLifestyle().get(i);
                    switch(lifestyleBean.getType()){
                        case "comf":
                            comfBrief.setText(String.format(Locale.CHINA,"舒适指数---%s",lifestyleBean.getBrf()));
                            comfTxt.setText(lifestyleBean.getTxt());
                            break;
                        case "drsg":
                            clothBrief.setText(String.format(Locale.CHINA,"穿衣指数---%s",lifestyleBean.getBrf()));
                            clothTxt.setText(lifestyleBean.getTxt());
                            break;
                        case "sport":
                            sportBrief.setText(String.format(Locale.CHINA,"运动指数---%s", lifestyleBean.getBrf()));
                            sportTxt.setText(lifestyleBean.getTxt());
                            break;
                        case "trav":
                            travelBrief.setText(String.format(Locale.CHINA,"旅游指数---%s",lifestyleBean.getBrf()));
                            travelTxt.setText(lifestyleBean.getTxt());
                            break;
                        case "flu":
                            fluBrief.setText(String.format(Locale.CHINA,"感冒指数---%s", lifestyleBean.getBrf()));
                            fluTxt.setText(lifestyleBean.getTxt());
                            break;
                        case "uv":
                            uvBrief.setText(String.format(Locale.CHINA,"防晒指数---%s", lifestyleBean.getBrf()));
                            uvText.setText(lifestyleBean.getTxt());
                            break;
                        case "air":
                            airBrief.setText(String.format(Locale.CHINA,"空气指数---%s", lifestyleBean.getBrf()));
                            airTxt.setText(lifestyleBean.getTxt());
                            break;
                        case "cw":
                            cwBrief.setText(String.format(Locale.CHINA,"洗车指数---%s", lifestyleBean.getBrf()));
                            cwTxt.setText(lifestyleBean.getTxt());
                            break;
                    }

                }
            } catch (Exception e) {
                XLog.e(TAG,e.toString());
            }
        }
    }

    /**
     * 未来天气
     */
    class ForecastViewHolder extends BaseViewHolder<WeatherBean.HeWeather6Bean> {
        private LinearLayout forecastLinear;
        private TextView[] forecastDate = new TextView[mWeatherData.getDaily_forecast().size()];
        private TextView[] forecastTemp = new TextView[mWeatherData.getDaily_forecast().size()];
        private TextView[] forecastTxt = new TextView[mWeatherData.getDaily_forecast().size()];
        private TextView[] forecastCond = new TextView[mWeatherData.getDaily_forecast().size()];
        private ImageView[] forecastIcon = new ImageView[mWeatherData.getDaily_forecast().size()];

        ForecastViewHolder(View itemView) {
            super(itemView);
            forecastLinear = (LinearLayout) itemView.findViewById(R.id.forecast_linear);
            for (int i = 0; i < mWeatherData.getDaily_forecast().size(); i++) {
                View view = View.inflate(mContext, R.layout.item_forecast_line, null);
                forecastDate[i] = (TextView) view.findViewById(R.id.forecast_date);
                forecastTemp[i] = (TextView) view.findViewById(R.id.forecast_temp);
                forecastTxt[i] = (TextView) view.findViewById(R.id.forecast_txt);
                forecastIcon[i] = (ImageView) view.findViewById(R.id.forecast_icon);
                forecastCond[i] = (TextView) view.findViewById(R.id.forecast_cond_tv);
                forecastLinear.addView(view);
            }
        }

        protected void bind(WeatherBean.HeWeather6Bean weather) {
            try {
                //今日 明日
                forecastDate[0].setText("今日");
                forecastDate[1].setText("明日");
                for (int i = 0; i < mWeatherData.getDaily_forecast().size(); i++) {
                    DailyForecastBean dailyForecastBean = mWeatherData.getDaily_forecast().get(i);
                    if (i > 1) {
                        try {
                            forecastDate[i].setText(
                                    TimeUitl.dayForWeek(dailyForecastBean.getDate()));
                        } catch (Exception e) {
                            XLog.e(TAG,e.toString());
                        }
                    }
                    ImageLoader.load(mContext,
                            Basepreference.getInt(weather.getDaily_forecast().get(i).getCond_txt_d(), R.drawable.ic_cloud),
                            forecastIcon[i]);
                    forecastCond[i].setText(dailyForecastBean.getCond_txt_d());
                    forecastTemp[i].setText(
                            String.format("%s℃ - %s℃",
                                    dailyForecastBean.getTmp_min(),
                                    dailyForecastBean.getTmp_max()));
                    forecastTxt[i].setText(
                            String.format(" %s级 %s %s km/h。 降水几率 %s%%。",
                                    dailyForecastBean.getWind_sc(),
                                    dailyForecastBean.getWind_dir(),
                                    dailyForecastBean.getWind_spd(),
                                    dailyForecastBean.getPop()));
                }
            } catch (Exception e) {
                XLog.e(TAG,e.toString());
            }
        }
    }
}
