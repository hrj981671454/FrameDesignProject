package kotlin.studio.com.myapplication.sql.update;

import kotlin.studio.com.myapplication.app.App;
import kotlin.studio.com.myapplication.sql.bean.User;
import kotlin.studio.com.myapplication.sql.dao.UserDao;
import kotlin.studio.com.myapplication.sql.factory.DaoManagerFactory;
import kotlin.studio.com.myapplication.utils.FileUtil;

/**
 * Created by david on 20/1/2017.
 */

public enum PrivateDataBaseEnums {

    /**
     * 存放本地数据库的路径
     */
    database(App.getInstance().getDataBasePath() + "/database/");

    /**
     * 文件存储的文件路径
     */
    private String value;

    PrivateDataBaseEnums(String value) {
        this.value = value;
    }

    public String getValue() {
        UserDao userDao = DaoManagerFactory.getInstance().getDataHelper(UserDao.class, User.class);
        if (userDao != null) {
            User currentUser = userDao.queryLoginStatus();
            if (currentUser != null) {
                Boolean fileIsExist = FileUtil.isFileExist(this.value);
                if (!fileIsExist) {
                    FileUtil.createFile(this.value);
                }
                return this.value + "/login.db";
            } else {
                Boolean fileIsExist = FileUtil.isFileExist(this.value);
                if (!fileIsExist) {
                    FileUtil.createFile(this.value);
                }
                return this.value + "/login.db";
            }
        }
        return this.value;
    }
}
