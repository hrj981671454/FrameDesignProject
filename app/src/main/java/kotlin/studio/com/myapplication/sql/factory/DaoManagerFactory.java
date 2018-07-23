package kotlin.studio.com.myapplication.sql.factory;

import android.database.sqlite.SQLiteDatabase;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import kotlin.studio.com.myapplication.app.App;
import kotlin.studio.com.myapplication.sql.dao.BaseDao;
import kotlin.studio.com.myapplication.utils.FileUtil;

/**
 * Description:
 * Copyright  : Copyright (c) 2016
 * Company    : Android
 * Author     : 关羽
 * Date       : 2018-07-18 15:33
 */
public class DaoManagerFactory {

    private String         path;
    private SQLiteDatabase sqLiteDatabase;
    private Map<String, BaseDao> map = Collections.synchronizedMap(new HashMap<String, BaseDao>());

    private static DaoManagerFactory instance = new DaoManagerFactory(new File(App.getInstance().getDataBasePath(), "yianju.db"));

    public static DaoManagerFactory getInstance() {
        return instance;
    }

    private DaoManagerFactory(File file) {
        this.path = file.getAbsolutePath();
        Boolean fileIsExist = FileUtil.isFileExist(path);
        if (!fileIsExist) {
            FileUtil.createFile(path);
        }
        openDatabase();
    }

    public synchronized <T extends BaseDao<M>, M> T getDataHelper(Class<T> clazz, Class<M> entity) {
        BaseDao baseDao = null;
        if (map.get(clazz.getSimpleName()) != null) {
            return (T) map.get(clazz.getSimpleName());
        }
        try {
            baseDao = clazz.newInstance();
            baseDao.init(entity, sqLiteDatabase);
            map.put(clazz.getSimpleName(), baseDao);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return (T) baseDao;
    }


    private void openDatabase() {
        this.sqLiteDatabase = SQLiteDatabase.openOrCreateDatabase(path, null);
    }
}
