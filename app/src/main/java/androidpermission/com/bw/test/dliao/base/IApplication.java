package androidpermission.com.bw.test.dliao.base;

import android.app.Application;

import com.mob.MobApplication;

/**
 * Created by Administrator on 2017/7/4.
 */

public class IApplication extends MobApplication{

    public static IApplication application;
    @Override
    public void onCreate() {
        super.onCreate();
        application=this;
    }

}
