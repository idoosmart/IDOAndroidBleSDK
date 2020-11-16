package test.com.ido.get;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ido.ble.BLEManager;
import com.ido.ble.callback.GetDeviceInfoCallBack;
import com.ido.ble.protocol.model.ActivityDataCount;
import com.ido.ble.protocol.model.BasicInfo;
import com.ido.ble.protocol.model.BatteryInfo;
import com.ido.ble.protocol.model.DeviceSummarySoftVersionInfo;
import com.ido.ble.protocol.model.HIDInfo;
import com.ido.ble.protocol.model.LiveData;
import com.ido.ble.protocol.model.NoticeSwitchInfo;
import com.ido.ble.protocol.model.SNInfo;
import com.ido.ble.protocol.model.SupportFunctionInfo;
import com.ido.ble.protocol.model.SystemTime;

import test.com.ido.R;
import test.com.ido.connect.BaseAutoConnectActivity;
import test.com.ido.utils.JsonUtils;

public class GetInfoActivity extends BaseAutoConnectActivity {

    private TextView tvResult;
    private GetDeviceInfoCallBack.ICallBack iCallBack = new GetDeviceInfoCallBack.ICallBack() {
        @Override
        public void onGetBasicInfo(BasicInfo basicInfo) {
            if (basicInfo != null){
                tvResult.setText(basicInfo.toString());
            }else {
                tvResult.setText("get failed!!!");
            }
        }

        @Override
        public void onGetFunctionTable(SupportFunctionInfo supportFunctionInfo) {
            if (supportFunctionInfo != null){
                tvResult.setText(JsonUtils.format(supportFunctionInfo.toString()));
            }else {
                tvResult.setText("get failed!!!");
            }
        }

        @Override
        public void onGetTime(SystemTime time) {
            if (time != null){
                tvResult.setText(time.toString());
            }else {
                tvResult.setText("get failed!!!");
            }
        }

        @Override
        public void onGetMacAddress(String macAddress) {
            if (macAddress != null){
                tvResult.setText(macAddress);
            }else {
                tvResult.setText("get failed!!!");
            }
        }

        @Override
        public void onGetBatteryInfo(BatteryInfo batteryInfo) {
            if (batteryInfo != null){
                tvResult.setText(batteryInfo.toString());
            }else {
                tvResult.setText("get failed!!!");
            }
        }

        @Override
        public void onGetSNInfo(SNInfo snInfo) {
            if (snInfo != null){
                tvResult.setText(snInfo.toString());
            }else {
                tvResult.setText("get failed!!!");
            }
        }

        @Override
        public void onGetNoticeCenterSwitchStatus(NoticeSwitchInfo info) {
            if (info != null){
                tvResult.setText(info.toString());
            }else {
                tvResult.setText("get failed!!!");
            }
        }

        @Override
        public void onGetLiveData(LiveData liveData) {
            if (liveData != null){
                tvResult.setText(liveData.toString());
            }else {
                tvResult.setText("get failed!!!");
            }
        }

        @Override
        public void onGetHIDInfo(HIDInfo hidInfo) {
            if (hidInfo != null){
                tvResult.setText(hidInfo.toString());
            }else {
                tvResult.setText("get failed!!!");
            }
        }

        @Override
        public void onGetActivityCount(ActivityDataCount activityDataCount) {
            if (activityDataCount != null){
                tvResult.setText("" + activityDataCount.toString());
            }else {
                tvResult.setText("get failed!!!");
            }
        }

        @Override
        public void onGetDeviceSummarySoftVersionInfo(DeviceSummarySoftVersionInfo info) {
            if (info != null){
                tvResult.setText("" + info.toString());
            }else {
                tvResult.setText("get failed!!!");
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_info);
        tvResult = (TextView) findViewById(R.id.get_info_result);

        BLEManager.registerGetDeviceInfoCallBack(iCallBack);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BLEManager.unregisterGetDeviceInfoCallBack(iCallBack);
    }

    public void getBasicInfo(View v){
        tvResult.setText("getBasicInfo...");
        BLEManager.getBasicInfo();
    }

    public void getFunction(View v){
        tvResult.setText("getSupportFunctionInfo...");
        BLEManager.getFunctionTables();
    }

    public void getTime(View v){
        tvResult.setText("getTime...");
//        BLEManager.getTime();
    }

    public void getMacAddress(View v){
        tvResult.setText("getMacAddress...");
        BLEManager.getMacAddress();
    }

    public void getBatteryInfo(View v){
        tvResult.setText("getBatteryInfo...");
//        BLEManager.getBatteryInfo();
    }

    public void getSN(View v){
        tvResult.setText("getSN...");
//        BLEManager.getSNInfo();
    }

    public void getNoticeSwitchStatus(View v){
        tvResult.setText("getNoticeSwitchStatus...");
//        BLEManager.getNoticeSwitchState();
    }

    public void getLiveData(View v){
        tvResult.setText("getLiveData...");
        BLEManager.getLiveData();
    }

    public void getHidInfo(View v){
        tvResult.setText("getHidInfo...");
        BLEManager.getHIDInfo();
    }

    public void getActivityCount(View v){
        tvResult.setText("getActivityCount...");
        BLEManager.getActivityCount();
    }

}
