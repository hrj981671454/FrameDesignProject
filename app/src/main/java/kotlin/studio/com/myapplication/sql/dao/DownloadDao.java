package kotlin.studio.com.myapplication.sql.dao;

import java.util.ArrayList;

/**
 * Description:
 * Copyright  : Copyright (c) 2016
 * Company    : Android
 * Author     : 关羽
 * Date       : 2018-07-20 14:24
 */
public class DownloadDao extends BaseDao {
    @Override
    public String createDataBase() {
        return "create table if not exists download(name varchar(20),password varchar(20),age Integer(10))";
    }

    @Override
    public ArrayList query(Object where) {
        return super.query(where);
    }
}
