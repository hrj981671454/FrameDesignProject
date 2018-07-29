package kotlin.studio.com.myapplication.sql.dao;

/**
 * Description:
 * Copyright  : Copyright (c) 2016
 * Company    : Android
 * Author     : 关羽
 * Date       : 2018-07-20 14:24
 */
public class LoginInfoDao extends BaseDao {
    @Override
    public String createDataBase() {
        return "create table if not exists loginInfo(name varchar(20),password varchar(20),age Integer(10))";
    }


    public String updateDataBase(){
        return "alert table download add column test ";
    }
}
