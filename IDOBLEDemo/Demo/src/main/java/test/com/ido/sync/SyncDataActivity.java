package test.com.ido.sync;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ido.ble.BLEManager;
import com.ido.ble.business.sync.SyncPara;
import com.ido.ble.callback.SyncCallBack;
import com.ido.ble.callback.SyncV3CallBack;
import com.ido.ble.data.manage.database.HealthActivity;
import com.ido.ble.data.manage.database.HealthBloodPressed;
import com.ido.ble.data.manage.database.HealthBloodPressedItem;
import com.ido.ble.data.manage.database.HealthHeartRate;
import com.ido.ble.data.manage.database.HealthHeartRateItem;
import com.ido.ble.data.manage.database.HealthHeartRateSecond;
import com.ido.ble.data.manage.database.HealthHeartRateSecondItem;
import com.ido.ble.data.manage.database.HealthPressure;
import com.ido.ble.data.manage.database.HealthPressureItem;
import com.ido.ble.data.manage.database.HealthSleep;
import com.ido.ble.data.manage.database.HealthSleepItem;
import com.ido.ble.data.manage.database.HealthSpO2;
import com.ido.ble.data.manage.database.HealthSpO2Item;
import com.ido.ble.data.manage.database.HealthSport;
import com.ido.ble.data.manage.database.HealthSportItem;
import com.ido.ble.data.manage.database.HealthSwimming;

import java.util.List;

import test.com.ido.R;
import test.com.ido.connect.BaseAutoConnectActivity;

public class SyncDataActivity extends BaseAutoConnectActivity {

    private TextView tvSyncResult;
    private SyncCallBack.IActivityCallBack iActivityCallBack = new SyncCallBack.IActivityCallBack() {
        @Override
        public void onStart() {
            tvSyncResult.setText("start sync activity data");
        }

        @Override
        public void onStop() {
            tvSyncResult.setText("sync activity data stop!");
        }

        @Override
        public void onSuccess() {
            tvSyncResult.setText("sync activity data success!");
        }

        @Override
        public void onFailed() {
            tvSyncResult.setText("sync activity failed");
        }

        @Override
        public void onGetActivityData(HealthActivity healthActivity) {

        }
    };

    private SyncCallBack.IConfigCallBack iConfigCallBack = new SyncCallBack.IConfigCallBack() {
        @Override
        public void onStart() {
            tvSyncResult.setText("start sync config");
        }

        @Override
        public void onStop() {
            tvSyncResult.setText("sync config stop!");
        }

        @Override
        public void onSuccess() {
            tvSyncResult.setText("sync config data success!");
        }

        @Override
        public void onFailed() {
            tvSyncResult.setText("sync config failed");
        }
    };

    private SyncCallBack.IHealthCallBack iHealthCallBack = new SyncCallBack.IHealthCallBack() {
        @Override
        public void onStart() {
            tvSyncResult.setText("start sync health data");
        }

        @Override
        public void onProgress(int progress) {
            tvSyncResult.setText("sync health data progress = " + progress + "%");
        }

        @Override
        public void onStop() {
            tvSyncResult.setText("sync health data stop!");
        }

        @Override
        public void onSuccess() {
            tvSyncResult.setText("sync health data success!");
        }

        @Override
        public void onFailed() {
            tvSyncResult.setText("sync health data failed");
        }

        @Override
        public void onGetSportData(HealthSport healthSport, List<HealthSportItem> items, boolean isSectionItemData) {

        }

        @Override
        public void onGetSleepData(HealthSleep healthSleep, List<HealthSleepItem> items) {

        }

        @Override
        public void onGetHeartRateData(HealthHeartRate healthHeartRate, List<HealthHeartRateItem> items, boolean isSectionItemData) {

        }

        @Override
        public void onGetBloodPressureData(HealthBloodPressed healthBloodPressed, List<HealthBloodPressedItem> items, boolean isSectionItemData) {

        }
    };

    private SyncV3CallBack.ICallBack iSyncV3CallBack = new SyncV3CallBack.ICallBack() {
        @Override
        public void onStart() {
            tvSyncResult.setText("start sync v3 data");
        }

        @Override
        public void onProgress(int progress) {
            tvSyncResult.setText("sync v3 data progress = " + progress + "%");
        }

        @Override
        public void onStop() {
            tvSyncResult.setText("sync v3 data stop!");
        }

        @Override
        public void onSuccess() {
            tvSyncResult.setText("sync v3 data success!");
        }

        @Override
        public void onFailed() {
            tvSyncResult.setText("sync v3 data failed");
        }

        @Override
        public void onGetHealthSpO2Data(HealthSpO2 healthSpO2, List<HealthSpO2Item> itemList, boolean isSectionItemData) {

        }

        @Override
        public void onGetHealthPressureData(HealthPressure healthPressure, List<HealthPressureItem> itemList, boolean isSectionItemData) {

        }

        @Override
        public void onGetHealthHeartRateSecondData(HealthHeartRateSecond healthHeartRateSecond, boolean isSectionItemData) {

        }

        @Override
        public void onGetHealthSwimmingData(HealthSwimming healthSwimming) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sync_data);
        tvSyncResult = (TextView) findViewById(R.id.sync_result);
        BLEManager.registerSyncConfigCallBack(iConfigCallBack);
        BLEManager.registerSyncHealthCallBack(iHealthCallBack);
        BLEManager.registerSyncActivityCallBack(iActivityCallBack);
        BLEManager.registerSyncV3CallBack(iSyncV3CallBack);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BLEManager.unregisterSyncConfigCallBack(iConfigCallBack);
        BLEManager.unregisterSyncHealthCallBack(iHealthCallBack);
        BLEManager.unregisterSyncActivityCallBack(iActivityCallBack);
        BLEManager.unregisterSyncV3CallBack(iSyncV3CallBack);
    }

    public void syncConfigData(View view){
        BLEManager.startSyncConfigInfo();
    }
    public void stopSyncConfigData(View view){
        BLEManager.stopSyncConfigInfo();
    }



    public void syncHealthData(View view){
        BLEManager.startSyncHealthData();
    }
    public void stopSyncHealthData(View view){
        BLEManager.stopSyncHealthData();
    }


    public void syncActivityData(View view){
        BLEManager.startSyncActivityData();
    }
    public void stopSyncActivityData(View view){
        BLEManager.stopSyncActivityData();
    }

    public void syncV3Data(View view){
        BLEManager.startSyncV3Health();
    }

    public void stopSyncV3Data(View view){
        BLEManager.stopSyncV3Health();
    }

    public void syncAllData(View view){
        SyncPara syncPara = new SyncPara();
        syncPara.isNeedSyncConfigData = false;
        BLEManager.syncAllData(syncPara);
    }
}
