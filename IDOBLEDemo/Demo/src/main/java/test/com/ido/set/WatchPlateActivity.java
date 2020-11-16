package test.com.ido.set;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

//import com.ido.ble.common.GsonUtil;
//import com.ido.ble.protocol.cmd.BLECmdUtils;
//import com.ido.ble.watch.custom.AutoSetWatchPlateManager;
//import com.ido.ble.watch.custom.WatchPlateSetConfig;
//import com.ido.ble.watch.custom.callback.WatchPlateCallBack;
//import com.ido.ble.watch.custom.callback.WatchPlateCallBackManager;
//import com.ido.ble.watch.custom.model.WatchPlateScreenInfo;

import java.io.File;
import java.util.List;

import test.com.ido.R;
import test.com.ido.connect.BaseAutoConnectActivity;
import test.com.ido.utils.DataUtils;

public class WatchPlateActivity extends BaseAutoConnectActivity {
    private String filePath;
    private TextView tvFilePath, tvState, tvOperateTv, tvUniqueID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_plate);

        tvFilePath = findViewById(R.id.file_path_tv);
        tvState = findViewById(R.id.state_tv);
        tvOperateTv = findViewById(R.id.operate_info_tv);
        tvUniqueID = findViewById(R.id.unique_id_tv);

//        WatchPlateCallBackManager.getManager().registerOperateCallBack(iOperateCallBack);


        filePath = DataUtils.getInstance().getWatchPlateFilePath();
        if (TextUtils.isEmpty(filePath)){
            tvFilePath.setText("请先选择表盘压缩包(.zip)");
        }else {
            tvFilePath.setText(filePath);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        WatchPlateCallBackManager.getManager().unregisterOperateCallBack(iOperateCallBack);
    }

    public void selectFile(View view){
        openFileChooser();
    }

    public void startSet(View view){
//        WatchPlateSetConfig config = new WatchPlateSetConfig();
//        File file = new File(filePath);
//        config.uniqueID = file.getName().replace(".zip", "");
//        config.filePath = filePath;
//        config.stateListener = new WatchPlateCallBack.IAutoSetPlateCallBack() {
//            @Override
//            public void onStart() {
//                tvState.setText("start");
//            }
//
//            @Override
//            public void onProgress(int progress) {
//                tvState.setText("progress = " + progress);
//            }
//
//            @Override
//            public void onSuccess() {
//                tvState.setText("success");
//            }
//
//            @Override
//            public void onFailed() {
//                tvState.setText("failed");
//            }
//        };
//        AutoSetWatchPlateManager.getManager().start(config);
    }



    private static final int SELECT_FILE_REQ = 1;
    private void openFileChooser() {
        final Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("application/zip");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            // file browser has been found on the device
            startActivityForResult(intent, SELECT_FILE_REQ);
        }
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        if (resultCode != RESULT_OK)
            return;

        switch (requestCode) {
            case SELECT_FILE_REQ: {
                // and read new one
                final Uri uri = data.getData();
                /*
                 * The URI returned from application may be in 'file' or 'content' schema. 'File' schema allows us to create a File object and read details from if
                 * directly. Data from 'Content' schema must be read by Content Provider. To do that we are using a Loader.
                 */
                if (uri.getScheme().equals("file") || uri.getScheme().equals("content")) {
                    // the direct path to the file has been returned
                    final String path = uri.getPath();
                    filePath = path;
                    tvFilePath.setText(path);
                    DataUtils.getInstance().saveWatchPlateFilePath(path);
                }
            }

        }
    }



//    WatchPlateCallBack.IOperateCallBack iOperateCallBack = new WatchPlateCallBack.IOperateCallBack() {
//        @Override
//        public void onGetPlateList(List<String> uniqueIDList) {
//            tvOperateTv.setText(GsonUtil.toJson(uniqueIDList));
//        }
//
//        @Override
//        public void onGetScreenInfo(WatchPlateScreenInfo screenInfo) {
//            tvOperateTv.setText(GsonUtil.toJson(screenInfo));
//        }
//
//        @Override
//        public void onGetCurrentPlate(String uniqueID) {
//            tvOperateTv.setText("当前使用的表盘："  + uniqueID);
//        }
//
//        @Override
//        public void onSetPlate(boolean isSuccess) {
//            tvOperateTv.setText(isSuccess ? "设置成功" : "设置失败");
//        }
//
//        @Override
//        public void onDeletePlate(boolean isSuccess) {
//            tvOperateTv.setText(isSuccess ? "删除成功" : "删除失败");
//        }
//    };
//
//    public void getPlateList(View view){
//        BLECmdUtils.getWatchPlateList();
//    }
//
//    public void getScreenInfo(View view){
//        BLECmdUtils.getWatchPlateScreenInfo();
//    }
//
//    public void getCurrentPlate(View view){
//        BLECmdUtils.getCurrentWatchPlate();
//    }
//
//    public void deletePlate(View view){
//        BLECmdUtils.deleteWatchPlate(tvUniqueID.getText().toString());
//    }
//
//    public void setPlate(View view){
//        BLECmdUtils.setWatchPlate(tvUniqueID.getText().toString());
//    }
}
