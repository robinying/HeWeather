package com.yubin.heweather.model.greendao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.yubin.heweather.bean.CityBeanDao;
import com.yubin.heweather.bean.DaoMaster;

import org.greenrobot.greendao.database.Database;

/**
 * author : Yubin.Ying
 * time : 2018/11/21
 */
public class GreenDaoOpenHelper extends DaoMaster.OpenHelper {
    public GreenDaoOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        MigrationHelper.migrate(db, new MigrationHelper.ReCreateAllTableListener() {
            @Override
            public void onCreateAllTables(Database db, boolean ifNotExists) {
                DaoMaster.createAllTables(db, ifNotExists);
            }

            @Override
            public void onDropAllTables(Database db, boolean ifExists) {
                DaoMaster.dropAllTables(db, ifExists);
            }
        }, CityBeanDao.class);
    }
}
