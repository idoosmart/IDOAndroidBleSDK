package test.com.ido.connect;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ido.ble.BLEManager;

import test.com.ido.R;

public class ConnectManageActivity extends BaseAutoConnectActivity {

    private TextView tvConnectState;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect_manage);
        tvConnectState = (TextView) findViewById(R.id.connect_state_tv);
        statusEt = findViewById(R.id.connect_test_status);
        newStateEt = findViewById(R.id.connect_test_newState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        tvConnectState.setText(getState());
    }

    public void disConnect(View view){
        BLEManager.disConnect();
    }

    public void autoConnect(View view){
        BLEManager.autoConnect();
    }

    public void checkState(View view){
        Toast.makeText(this,  getState(), Toast.LENGTH_SHORT).show();
        tvConnectState.setText(getState());
    }
    private String getState(){
        if (BLEManager.isConnected()){
            return "ok";
        }else {
            return "break";
        }
    }


    private EditText statusEt, newStateEt;
    public void onTest(View view){
        int status = Integer.parseInt(statusEt.getText().toString());
        int newState = Integer.parseInt(newStateEt.getText().toString());
    }

    public void onDeviceManager(View view){
        startActivity(new Intent(this, DeviceManageActivity.class));
    }
}
