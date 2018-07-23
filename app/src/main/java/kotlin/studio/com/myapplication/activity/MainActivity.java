package kotlin.studio.com.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kotlin.studio.com.myapplication.R;
import kotlin.studio.com.myapplication.inject.ContentView;
import kotlin.studio.com.myapplication.inject.OnClick;
import kotlin.studio.com.myapplication.inject.OnLongClick;
import kotlin.studio.com.myapplication.inject.ViewInject;
import kotlin.studio.com.myapplication.sql.bean.Download;
import kotlin.studio.com.myapplication.sql.dao.DownloadDao;
import kotlin.studio.com.myapplication.sql.factory.DaoManagerFactory;
import kotlin.studio.com.myapplication.utils.LogsUtils;

/**
 * Description:
 * Copyright  : Copyright (c) 2016
 * Company    : Android
 * Author     : 关羽
 * Date       : 2018-07-17 14:44
 */
@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity {

    @ViewInject(R.id.text_ioc)
    Button   textView;
    @ViewInject(R.id.text_ioc2)
    Button   textView2;
    @ViewInject(R.id.text_ioc3)
    Button   textView3;
    @ViewInject(R.id.text_ioc4)
    Button   textView4;
    @ViewInject(R.id.text_ioc5)
    Button   textView5;
    @ViewInject(R.id.text_ioc6)
    Button   textView6;
    @ViewInject(R.id.lvList)
    ListView lvList;
    private DownloadDao mDao;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDao = DaoManagerFactory.getInstance().getDataHelper(DownloadDao.class, Download.class);
    }

    @OnClick(R.id.text_ioc)
    public void click(View view) {
        for (int i = 0; i < 10; i++) {
            Download download = new Download();
            download.setName("何仁杰" + i);
            download.setPassword("123456" + i);
            download.setAge(i);
            mDao.insert(download);
        }
    }

    @OnClick(R.id.text_ioc2)
    public void click2(View view) {
        Download download = new Download();
        download.setName("111111");
        download.setPassword("888888");
        download.setAge(666666);

        Download where = new Download();
        where.setName("何仁杰2");
        where.setPassword("1234562");
        where.setAge(2);
        mDao.update(download, where);
    }

    @OnClick(R.id.text_ioc3)
    public void click3(View view) {
        Download download = new Download();
        download.setName("111111");
        download.setPassword("888888");
        download.setAge(666666);
        ArrayList<Download> arrayList = mDao.query(download, null, null, null);
        List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < arrayList.size(); i++) {
            HashMap<String, Object> item = new HashMap<String, Object>();
            item.put("name", arrayList.get(i).getName());
            item.put("password", arrayList.get(i).getPassword());
            item.put("age", arrayList.get(i).getAge());
            data.add(item);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(this,
                data,
                R.layout.item_list,
                new String[]{"name", "password", "age"},
                new int[]{R.id.tvName, R.id.tvPassword, R.id.tvAge});
        lvList.setAdapter(simpleAdapter);
    }

    @OnClick(R.id.text_ioc4)
    public void click4(View view) {
        Download download = new Download();
        boolean b = mDao.deleteAll(download);
        LogsUtils.logI(getClass(), b + "");
    }


    @OnLongClick(R.id.text_ioc)
    public boolean onLongClick(View view) {
        Toast.makeText(MainActivity.this, "我被点了", Toast.LENGTH_LONG).show();
        return false;
    }


    @OnClick(R.id.text_ioc5)
    public void click5(View view) {

    }

    @OnClick(R.id.text_ioc6)
    public void click6(View view) {
        startActivity(new Intent(this, HttpActivity.class));
    }


}
