package app;

import android.app.Application;

import com.bwie.test.yuetextthree.gen.DaoMaster;
import com.bwie.test.yuetextthree.gen.DaoSession;
import com.bwie.test.yuetextthree.gen.UserDao;
import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by 笔片 on 2017/11/23.
 */

public class MyApp extends Application{
    public static UserDao userDao;
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(this, "lenvess.db", null);
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
        DaoSession daoSession = daoMaster.newSession();
        userDao = daoSession.getUserDao();
    }
}
