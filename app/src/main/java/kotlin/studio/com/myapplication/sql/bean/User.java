package kotlin.studio.com.myapplication.sql.bean;

import kotlin.studio.com.myapplication.sql.DbField;
import kotlin.studio.com.myapplication.sql.DbTable;

/**
 * Description:
 * Copyright  : Copyright (c) 2016
 * Company    : Android
 * Author     : 关羽
 * Date       : 2018-07-18 15:55
 */
@DbTable("tb_user")
public class User {

    @DbField("name")
    public String name;

    @DbField("password")
    public String password;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
