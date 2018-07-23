package kotlin.studio.com.myapplication.sql.dao;

import java.util.List;

/**
 * Description:
 * Copyright  : Copyright (c) 2016
 * Company    : Android
 * Author     : 关羽
 * Date       : 2018-07-18 16:00
 */
public class UserDao extends BaseDao {
    @Override
    public String createDataBase() {
        return "create table if not exists tb_user(name varchar(20),password varchar(20))";
    }

}
