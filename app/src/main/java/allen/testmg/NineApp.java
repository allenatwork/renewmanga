package allen.testmg;

import android.app.Application;

/**
 * Created by Allen on 07-Feb-17.
 */

public class NineApp extends Application {
//    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
//        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "FbVideos-db");
//        Database database = helper.getWritableDb();
//        daoSession = new DaoMaster(database).newSession();
    }

//    public DaoSession getDaoSession() {
//        return daoSession;
//    }
}
