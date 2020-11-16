package test.com.ido;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.ido.ble.BLEManager;
import com.ido.ble.callback.RebootCallback;

import test.com.ido.app2device.AppControlDeviceActivity;
import test.com.ido.appsenddata.AppSendDataActivity;
import test.com.ido.connect.BaseAutoConnectActivity;
import test.com.ido.connect.ConnectManageActivity;
import test.com.ido.device2app.DeviceControlAppActivity;
import test.com.ido.dfu.MainUpgradeActivity;
import test.com.ido.exgdata.ExchangeDataMainActivity;
import test.com.ido.get.GetInfoActivity;
import test.com.ido.gps.GpsMainActivity;
import test.com.ido.localdata.MainLocalDataActivity;
import test.com.ido.logoutput.bluetooth.BluetoothLogoutManager;
import test.com.ido.notice.PhoneNoticeActivity;
import test.com.ido.set.MainSetActivity;
import test.com.ido.set.WatchPlateActivity;
import test.com.ido.sync.SyncDataActivity;
import test.com.ido.unbind.UnbindActivity;

public class HomeActivity extends BaseAutoConnectActivity {


    private boolean isRebooting = false;
    private Handler mHander = new Handler();

    private RebootCallback.ICallBack iCallBack = new RebootCallback.ICallBack() {
        @Override
        public void onSuccess() {
            Toast.makeText(HomeActivity.this, R.string.reboot_device_tip_ok, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onFailed() {
            Toast.makeText(HomeActivity.this, R.string.reboot_device_tip_failed, Toast.LENGTH_SHORT).show();
        }
    };



    @Override
    protected void initCompleted() {
        if (isRebooting){
            BLEManager.startSyncHealthData();
            isRebooting = false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        BLEManager.registerRebootCallBack(iCallBack);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BLEManager.unregisterRebootCallBack(iCallBack);

//        BLEManager.destroy();

        BluetoothLogoutManager.getManager().stop();

        System.exit(0);
    }

    public void unbind(View v) {
        startActivity(new Intent(this, UnbindActivity.class));
    }

    public void connectManage(View v) {
        startActivity(new Intent(this, ConnectManageActivity.class));
    }

    public void getInfo(View v) {
        startActivity(new Intent(this, GetInfoActivity.class));
    }

    public void setPara(View v) {
        startActivity(new Intent(this, MainSetActivity.class));
    }

    public void notice(View v) {
        startActivity(new Intent(this, PhoneNoticeActivity.class));
    }

    public void appControlDevice(View v) {
        startActivity(new Intent(this, AppControlDeviceActivity.class));
    }

    public void deviceControlApp(View v) {
        startActivity(new Intent(this, DeviceControlAppActivity.class));
    }

    public void syncData(View v) {
        startActivity(new Intent(this, SyncDataActivity.class));
    }

    public void appSendData(View v) {
        startActivity(new Intent(this, AppSendDataActivity.class));
    }

    public void exchangeData(View v) {
        startActivity(new Intent(this, ExchangeDataMainActivity.class));
    }

    public void reboot(View v) {
        BLEManager.reBoot();
        isRebooting = true;
    }

    public void upgrade(View view){
        //进入升级模式前，先获取一下电量信息
        BLEManager.getBasicInfo();
        startActivity(new Intent(this, MainUpgradeActivity.class));
    }

    public void gps(View view){
        startActivity(new Intent(this, GpsMainActivity.class));
    }
    public void getLocalData(View v){
        startActivity(new Intent(this, MainLocalDataActivity.class));
    }

    public void watchPlate(View view){
        startActivity(new Intent(this, WatchPlateActivity.class));
    }

}
