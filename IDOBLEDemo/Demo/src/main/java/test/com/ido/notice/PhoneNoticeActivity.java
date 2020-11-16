package test.com.ido.notice;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.ido.ble.BLEManager;
import com.ido.ble.callback.PhoneMsgNoticeCallBack;
import com.ido.ble.protocol.model.IncomingCallInfo;
import com.ido.ble.protocol.model.NewMessageInfo;

import test.com.ido.R;
import test.com.ido.connect.BaseAutoConnectActivity;

public class PhoneNoticeActivity extends BaseAutoConnectActivity {

    private EditText etIncomingName, etIncomingPhoneNumber;
    private EditText etNewMsgContent, etNewMsgName, etNewMsgNumber;
    private RadioButton rbSMS,rbEmail, rbWX;
    private PhoneMsgNoticeCallBack.ICallBack iCallBack = new PhoneMsgNoticeCallBack.ICallBack() {
        @Override
        public void onCalling() {
            Toast.makeText(PhoneNoticeActivity.this, R.string.phone_notice_tip_msg_ok, Toast.LENGTH_LONG).show();
        }

        @Override
        public void onStopCall() {
            Toast.makeText(PhoneNoticeActivity.this, R.string.phone_notice_tip_msg_ok, Toast.LENGTH_LONG).show();
        }

        @Override
        public void onNewMessage() {
            Toast.makeText(PhoneNoticeActivity.this, R.string.phone_notice_tip_msg_ok, Toast.LENGTH_LONG).show();
        }

        @Override
        public void onUnReadMessage() {
            Toast.makeText(PhoneNoticeActivity.this, R.string.phone_notice_tip_msg_ok, Toast.LENGTH_LONG).show();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_notice);
        BLEManager.registerPhoneMsgNoticeCallBack(iCallBack);
        initView();
    }

    private void initView() {
        etIncomingName = (EditText) findViewById(R.id.phone_notice_incoming_name_et);
        etIncomingPhoneNumber = (EditText) findViewById(R.id.phone_notice_incoming_phone_number_et);

        etNewMsgName = (EditText) findViewById(R.id.phone_notice_new_msg_name_et);
        etNewMsgNumber = (EditText) findViewById(R.id.phone_notice_new_msg_number_et);
        etNewMsgContent = (EditText) findViewById(R.id.phone_notice_new_msg_content_et);

        rbSMS = (RadioButton) findViewById(R.id.phone_notice_new_msg_type_sms_rb);
        rbEmail = (RadioButton) findViewById(R.id.phone_notice_new_msg_type_email_rb);
        rbWX = (RadioButton) findViewById(R.id.phone_notice_new_msg_type_wx_rb);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BLEManager.unregisterPhoneMsgNoticeCallBack(iCallBack);
    }

    public void noticeIncomingCall(View v){
        IncomingCallInfo incomingCallInfo = new IncomingCallInfo();
        incomingCallInfo.name = etIncomingName.getText().toString();
        incomingCallInfo.phoneNumber = etIncomingPhoneNumber.getText().toString();

        BLEManager.setIncomingCallInfo(incomingCallInfo);
    }

    public void setStopInComingCall(View v){
        BLEManager.setStopInComingCall();

    }

    public void noticeNewMsg(View v){
        NewMessageInfo newMessageInfo = new NewMessageInfo();

        if (rbSMS.isChecked()){
            newMessageInfo.type = NewMessageInfo.TYPE_SMS;
        }else if (rbEmail.isChecked()){
            newMessageInfo.type = NewMessageInfo.TYPE_EMAIL;
        }else if (rbWX.isChecked()){
            newMessageInfo.type = NewMessageInfo.TYPE_WX;
        }
        newMessageInfo.name = etNewMsgName.getText().toString();
        newMessageInfo.number = etNewMsgNumber.getText().toString();
        newMessageInfo.content = etNewMsgContent.getText().toString();
        BLEManager.setNewMessageDetailInfo(newMessageInfo);
    }

    public void noticeUnreadMsg(View v){

    }
}
