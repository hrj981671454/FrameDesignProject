package kotlin.studio.com.myapplication.sql.bean;

import kotlin.studio.com.myapplication.sql.DbField;
import kotlin.studio.com.myapplication.sql.DbTable;

/**
 * Description:
 * Copyright  : Copyright (c) 2016
 * Company    : Android
 * Author     : 关羽
 * Date       : 2018-07-20 14:23
 */
@DbTable("download")
public class Download {

    @DbField("name")
    public String name;

    @DbField("password")
    public String password;

    @DbField("age")
    public int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

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
