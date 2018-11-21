package com.yubin.heweather.ui.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yubin.heweather.App;
import com.yubin.heweather.R;
import com.yubin.heweather.bean.CityBean;
import com.yubin.heweather.bean.CityBeanDao;
import com.yubin.heweather.bean.WeatherBean;
import com.yubin.heweather.config.Constant;
import com.yubin.heweather.ui.base.BaseViewHolder;
import com.yubin.heweather.utils.Basepreference;
import com.yubin.heweather.utils.ChangeCityEvent;
import com.yubin.heweather.utils.DialogHelper;
import com.yubin.heweather.utils.ImageLoader;
import com.yubin.heweather.utils.RxBus;
import com.yubin.heweather.utils.XLog;

import java.util.List;

import butterknife.BindView;

/**
 * author : Yubin.Ying
 * time : 2018/11/21
 */
public class CityAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = CityAdapter.class.getSimpleName();

    private List<CityBean> cityData;
    private Context mContext;

    public CityAdapter(List<CityBean> cityData) {
        this.cityData = cityData;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        mContext = viewGroup.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_choose_city, viewGroup, false);
        return new MultiCityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {
        ((MultiCityViewHolder) holder).bind(cityData.get(i));
    }

    @Override
    public int getItemCount() {
        if (cityData != null) {
            return cityData.size();
        }
        return 0;
    }

    class MultiCityViewHolder extends BaseViewHolder<CityBean> {
        @BindView(R.id.cardView)
        CardView mCardView;
        @BindView(R.id.multi_choose)
        ImageView multiChoose;
        @BindView(R.id.multi_city)
        TextView multiCity;
        @BindView(R.id.multi_area)
        TextView multiArea;

        MultiCityViewHolder(View itemView) {
            super(itemView);
        }

        protected void bind(CityBean cityBean) {
            try {
                multiCity.setText(cityBean.getCity());
                multiArea.setText(cityBean.getDiscrict());
                if (Basepreference.getString(Constant.USE_CITY).equals(cityBean.getCity())
                        && Basepreference.getString(Constant.USE_DISTRICT).equals(cityBean.getDiscrict())) {
                    multiChoose.setVisibility(View.VISIBLE);
                }else {
                    multiChoose.setVisibility(View.INVISIBLE);
                }
                mCardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Basepreference.putString(Constant.USE_CITY,cityBean.getCity());
                        Basepreference.putString(Constant.USE_DISTRICT,cityBean.getDiscrict());
                        RxBus.getDefault().post(new ChangeCityEvent());
                        notifyDataSetChanged();
                    }
                });
                mCardView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        DialogHelper.getConfirmDialog(mContext, "是否删除这个城市", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                CityBeanDao cityBeanDao = App.getAppInstance().getDaoSession().getCityBeanDao();
                                CityBean deleteCity = cityBeanDao.queryBuilder().limit(1).where(CityBeanDao.Properties.Discrict.eq(cityBean.getDiscrict())).unique();
                                if (Basepreference.getString(Constant.USE_CITY).equals(cityBean.getCity())
                                        && Basepreference.getString(Constant.USE_DISTRICT).equals(cityBean.getDiscrict())) {
                                    String locationCity = Basepreference.getString(Constant.LOCATION_CITY);
                                    String locationArea = Basepreference.getString(Constant.LOCATION_DISTRICT);
                                    Basepreference.putString(Constant.USE_CITY, locationCity);
                                    Basepreference.putString(Constant.LOCATION_DISTRICT, locationArea);
                                }
                                cityBeanDao.delete(deleteCity);
                                cityData.remove(deleteCity);
                                notifyDataSetChanged();
                            }
                        }).show();
                        return true;
                    }
                });
            } catch (Exception e) {
                XLog.d(TAG, e.toString());
            }
        }
    }
}
