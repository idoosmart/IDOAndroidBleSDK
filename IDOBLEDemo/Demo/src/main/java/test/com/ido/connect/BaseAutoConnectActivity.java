package test.com.ido.connect;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

import com.ido.ble.BLEManager;
import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.ble.callback.ConnectCallBack;

import test.com.ido.R;
import test.com.ido.dfu.MainDfuActivity;
import test.com.ido.logoutput.LogOutput;

/**
 * @author: zhouzj
 * @date: 2017/10/23 11:38
 */

public class BaseAutoConnectActivity extends Activity {

    private ProgressDialog progressDialog;
    protected void showProgressDialog(String title){
        if (progressDialog != null && progressDialog.isShowing()){
            progressDialog.dismiss();
        }
        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setTitle(title);
        progressDialog.show();
    }

    protected void closeProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
    private ConnectCallBack.ICallBack connectCallBack = new ConnectCallBack.ICallBack() {

        @Override
        public void onConnectStart() {
            setTitle(getString(R.string.connect_state_start));
            onNotConnect();
        }

        @Override
        public void onConnecting() {
            setTitle(getString(R.string.connect_state_ing));
            onNotConnect();
        }

        @Override
        public void onRetry(int count) {

        }

        @Override
        public void onConnectSuccess() {
            setTitle(getString(R.string.connect_state_ok));
            onConnected();
        }

        @Override
        public void onConnectFailed() {
            setTitle(getString(R.string.connect_state_failed));
            onNotConnect();
        }

        @Override
        public void onConnectBreak() {
            setTitle(getString(R.string.connect_state_break));
            onNotConnect();
        }

        @Override
        public void onInDfuMode(BLEDevice bleDevice) {
            setTitle(getString(R.string.connect_state_dfu));
//            handleDfuState(bleDevice);
        }

        @Override
        public void onInitCompleted() {

            Toast.makeText(BaseAutoConnectActivity.this, "device init ok", Toast.LENGTH_SHORT).show();
            initCompleted();
        }

    };

    private AlertDialog dfuAlertDialog;
    protected void handleDfuState(BLEDevice bleDevice){
        if (dfuAlertDialog != null && dfuAlertDialog.isShowing()){
            return;
        }
        dfuAlertDialog = new AlertDialog.Builder(this).setTitle("提示")
                .setMessage(bleDevice.mDeviceName +"处于升级模式，是否去升级？").setNegativeButton("否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(BaseAutoConnectActivity.this, MainDfuActivity.class));
                    }
                }).create();
        dfuAlertDialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.base_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.log_out_by_self:
                LogOutput.enableSelf(this.getApplicationContext(), getIntent().getComponent());
                break;
            case R.id.log_out_by_service:
                LogOutput.enableService(this.getApplication());
                break;
            case R.id.log_out_by_bluetooth:
                LogOutput.enableBluetooth(this.getApplicationContext());
                break;
            case R.id.install_tool:
                LogOutput.installTool(this);
                break;
            case R.id.share_tool:
                LogOutput.shareTool(this);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_home);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        BLEManager.registerConnectCallBack(connectCallBack);

        getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (BLEManager.isConnected()) {
                    setTitle(getString(R.string.connect_state_ok));
                } else {
                    setTitle(getString(R.string.connect_state_start));
                    BLEManager.autoConnect();
                }
            }
        }, 300);


    }

    @Override
    protected void onStop() {
        super.onStop();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        BLEManager.unregisterConnectCallBack(connectCallBack);
    }

    protected void onConnected() {

    }

    protected void onNotConnect() {

    }

    protected void initCompleted(){

    }

}
