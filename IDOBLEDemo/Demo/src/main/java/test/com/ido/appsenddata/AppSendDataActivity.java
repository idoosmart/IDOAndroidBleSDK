package test.com.ido.appsenddata;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ido.ble.BLEManager;
import com.ido.ble.callback.AppSendDataCallBack;
import com.ido.ble.protocol.model.Menstrual;
import com.ido.ble.protocol.model.MenstrualRemind;
import com.ido.ble.protocol.model.WeatherInfo;

import test.com.ido.R;
import test.com.ido.connect.BaseAutoConnectActivity;
import test.com.ido.utils.JsonUtils;

public class AppSendDataActivity extends BaseAutoConnectActivity {

    private EditText etJsonData, etDataType;
    private AppSendDataCallBack.ICallBack iCallBack = new AppSendDataCallBack.ICallBack() {
        @Override
        public void onSuccess(AppSendDataCallBack.DataType type) {
            Toast.makeText(AppSendDataActivity.this, R.string.app_send_data_tip_ok, Toast.LENGTH_LONG).show();
        }

        @Override
        public void onFailed(AppSendDataCallBack.DataType type) {
            Toast.makeText(AppSendDataActivity.this, R.string.app_send_data_tip_failed, Toast.LENGTH_LONG).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_send_data);

        initView();
        BLEManager.registerAppSendDataCallBack(iCallBack);

    }

    private void initView() {
        etJsonData = (EditText) findViewById(R.id.weather_para_et);
        etDataType = findViewById(R.id.data_type_tv);

        etDataType.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                dataType = s.toString();
                if (dataType.equals(TYPE_WEATHER)){
                    createWeatherJsonData();
                }else if(dataType.equals(TYPE_MENSTRUAL)){
                    createMenstrualJsonData();
                }else if (dataType.equals(TYPE_MENSTRUAL_REMIND)){
                    createMenstrualRemindJsonData();
                }else {
                    etJsonData.setText("");
                }
            }


        });
    }

    private String dataType;
    private static final String TYPE_WEATHER = "1";
    private static final String TYPE_MENSTRUAL = "2";
    private static final String TYPE_MENSTRUAL_REMIND = "3";

    private void createMenstrualJsonData(){
        Menstrual menstrual = new Menstrual();
        etJsonData.setText(JsonUtils.format(new Gson().toJson(menstrual)));
    }

    private void createMenstrualRemindJsonData() {
        MenstrualRemind menstrualRemind = new MenstrualRemind();
        etJsonData.setText(JsonUtils.format(new Gson().toJson(menstrualRemind)));
    }
    private void createWeatherJsonData(){
        WeatherInfo weatherInfo = new WeatherInfo();
        weatherInfo.type = WeatherInfo.WEATHER_TYPE_SNOW;
        weatherInfo.temp = 9;
        weatherInfo.max_temp = 33;
        weatherInfo.min_temp =  2;
        weatherInfo.humidity = 32;
        weatherInfo.uv_intensity = 23;
        weatherInfo.aqi = 3;

        WeatherInfo.WeatherFutureInfo futureInfo = new WeatherInfo.WeatherFutureInfo();
        futureInfo.type = WeatherInfo.WEATHER_TYPE_CLOUDY;
        futureInfo.max_temp = 55;
        futureInfo.min_temp = 44;
        weatherInfo.future[0] = futureInfo;

        WeatherInfo.WeatherFutureInfo futureInfo1 = new WeatherInfo.WeatherFutureInfo();
        futureInfo1.type = WeatherInfo.WEATHER_TYPE_CLOUDY;
        futureInfo1.max_temp = 66;
        futureInfo1.min_temp = 77;
        weatherInfo.future[1] = futureInfo1;

        WeatherInfo.WeatherFutureInfo futureInfo2 = new WeatherInfo.WeatherFutureInfo();
        futureInfo2.type = WeatherInfo.WEATHER_TYPE_CLOUDY;
        futureInfo2.max_temp = 88;
        futureInfo2.min_temp = 99;
        weatherInfo.future[2] = futureInfo2;
        etJsonData.setText(JsonUtils.format(new Gson().toJson(weatherInfo)));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BLEManager.unregisterAppSendDataCallBack(iCallBack);
    }

    public void sendJsonData(View v){
        if (dataType.equals(TYPE_WEATHER)) {
            WeatherInfo weatherInfo = new Gson().fromJson(etJsonData.getText().toString(), WeatherInfo.class);
            BLEManager.setWeatherData(weatherInfo);
        }else if (dataType.equals(TYPE_MENSTRUAL)){
            Menstrual menstrual = new Gson().fromJson(etJsonData.getText().toString(), Menstrual.class);
            BLEManager.setMenstrual(menstrual);
        }else if (dataType.equals(TYPE_MENSTRUAL_REMIND)){
            MenstrualRemind menstrualRemind = new Gson().fromJson(etJsonData.getText().toString(), MenstrualRemind.class);
            BLEManager.setMenstrualRemind(menstrualRemind);
        }
    }



}
