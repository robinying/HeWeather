package com.yubin.heweather;

import android.app.Application;

import com.yubin.heweather.bean.DaoMaster;
import com.yubin.heweather.bean.DaoSession;
import com.yubin.heweather.model.greendao.GreenDaoOpenHelper;
import com.yubin.heweather.utils.ContextUtils;
import com.yubin.heweather.utils.XLog;

import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.query.QueryBuilder;

/**
 * author : Yubin.Ying
 * time : 2018/11/19
 */
public class App extends Application {
    private static App appInstance;
    private DaoSession daoSession;
    @Override
    public void onCreate() {
        super.onCreate();
       appInstance = this;
        ContextUtils.init(getApplicationContext());
        if (!BuildConfig.DEBUG) {
            XLog.closeLog();
        }
        initDao();
    }

    public static App getAppInstance(){
        return appInstance;
    }

    private void initDao() {
        QueryBuilder.LOG_SQL = BuildConfig.DEBUG;
        QueryBuilder.LOG_VALUES = BuildConfig.DEBUG;
//        SQLiteDatabase.loadLibs(this);
//        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, ENCRYPTED ? "notes-db-encrypted" : "notes-db");
        GreenDaoOpenHelper helper = new GreenDaoOpenHelper(this,  "heweather.db", null);
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();

    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
