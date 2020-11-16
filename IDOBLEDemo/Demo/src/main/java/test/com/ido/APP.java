package test.com.ido;


import android.app.Application;
import android.content.Context;

import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.facebook.stetho.Stetho;
import com.ido.ble.BLEManager;

import test.com.ido.crash.CrashHandler;


/**
 * Created by Zhouzj on 2017/9/29.
 * 
 */

public class APP extends Application {
    private static Application application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        BLEManager.onApplicationCreate(this);

        CrashHandler.getInstance().init(this);

        // 添加facebookc插件
        Stetho.initializeWithDefaults(this);

        //百度地图
        SDKInitializer.initialize(this);
        SDKInitializer.setCoordType(CoordType.BD09LL);


    }

    public static Context getAppContext(){
        return application.getApplicationContext();
    }

}
