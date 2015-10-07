package com.sunnypoint.felicaexample.views;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.sunnypoint.felicaexample.R;
import com.sunnypoint.felicaexample.utils.SoundManager;
import com.sunnypoint.felicaexample.utils.Utils;
import com.sunnypoint.felicaexample.utils.nfc.BaseReader;
import com.sunnypoint.felicaexample.utils.nfc.PasoriReaderNonRooted;
import com.sunnypoint.felicaexample.utils.nfc.ReaderCallback;
import com.sunnypoint.felicaexample.utils.nfc.ReaderUtils;

public class MainActivity extends AppCompatActivity implements ReaderCallback {

    /**
     * Message id to delay show error dialog after failed reading usb. Delay to
     * prevent showing error dialog without usb connected
     */
    private static final int MSG_SHOW_READER_ERROR_DIALOG = 101;
    /**
     * Message to OFF one_tap_mode option after don't touch in 5s
     */
    private static final int MSG_ON_OFF_ONE_TAP_MODE = 104;
    /**
     * Delay time to detect usb connection
     */
    private static final int SHOW_READER_ERROR_DELAY_TIME = 250;
    private String TAG = MainActivity.class.getName();
    private BaseReader mReader;

    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(android.os.Message msg) {
            // Util.e("IdleActivity.mHandler.handleMessage() " + msg.what);
            switch (msg.what) {
                case MSG_SHOW_READER_ERROR_DIALOG:
                    mHandler.removeMessages(MSG_SHOW_READER_ERROR_DIALOG);
                    if (!isFinishing()) {
                        BaseReader reader = (BaseReader) msg.obj;
                        if (reader != null && reader instanceof PasoriReaderNonRooted && reader.isReaderAvailable()) {
                            int errorCode = reader.getErrorCode();
                            showDialogAnnounce(getApplicationContext(), "Error", errorCode + "", false);
                        }
                    }
                    break;

                case MSG_ON_OFF_ONE_TAP_MODE:
                    mHandler.removeMessages(MSG_ON_OFF_ONE_TAP_MODE);

                    break;

                default:
                    mReader.handleMessageHandler(msg);
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Utils.getInstance().setContext(getApplicationContext());
        mReader = ReaderUtils.getReader(this, mHandler);
        //Setup Felica
        Log.i(TAG, Thread.currentThread().getStackTrace()[2].getMethodName().toString());
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        SoundManager.getInstance().init(getApplicationContext());
        mReader.createReader();

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: ");
        mReader.onActivityResumed(this);

        mReader.startPolling("REQUEST_CHECKIN -> ACTION_API_ERROR");

    }


    @Override
    protected void onDestroy() {
        Log.i(TAG, "onDestroy: ");
        mHandler.removeMessages(MSG_ON_OFF_ONE_TAP_MODE);
        super.onDestroy();
    }


    /**
     * Check and send felica id to server.
     */
    private void sendFidToServer() {
        // Show output
        Log.i(TAG, "sendFidToServer()");
        if (Utils.getInstance().getUserFID().equals("6300000000000000")) {
            Toast.makeText(getApplicationContext(), "Please tap again and hold the phone on the reader longer", Toast.LENGTH_SHORT).show();
            mReader.setTapped(false);
            mReader.startPolling("sendFidToServer() Invalid FID");

        } else {
            if (!mReader.getTapped()) {
                // mIsTapped = true;
                mReader.setTapped(true);
            }
//            mReader.startPolling("sendFidToServer() again");
        }
    }


    public void showDialogAnnounce(Context context, String title, String msg, boolean isError) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.view_dialog_layout, null, false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(view);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));

        TextView txtTitle = (TextView) dialog.findViewById(R.id.txt_dialog_title);
        txtTitle.setText(title);

        TextView txtMessage = (TextView) dialog.findViewById(R.id.txt_dialog_message);
        txtMessage.setText(msg);
        Button btnOk = (Button) dialog.findViewById(R.id.btn_ok);
        btnOk.setOnClickListener(v -> {
            if (isError == false) {
//                bus.post(new SPMADataEvent(this, null, SPMAEventCode.NEXT));
            } else {
//                bus.post(new BusInfo(this, null, "bookerror"));
            }
            dialog.dismiss();
        });
        dialog.show();

    }

    //implement readerCallBack
    @Override
    public void onReaderCreated(BaseReader reader, boolean success) {

    }

    @Override
    public void onTagDiscovered(BaseReader reader, boolean success) {
        mHandler.removeMessages(MSG_ON_OFF_ONE_TAP_MODE);
        if (!success) {
            if (reader instanceof PasoriReaderNonRooted) {
                // Close error alert dialog

                if (reader.isReaderAvailable()) {
                    // Usb reader is connecting, send delay to check detach and
                    // show
                    // error dialog if needed
                    mHandler.removeMessages(MSG_SHOW_READER_ERROR_DIALOG);
                    mHandler.sendMessageDelayed(mHandler.obtainMessage(MSG_SHOW_READER_ERROR_DIALOG, reader), SHOW_READER_ERROR_DELAY_TIME);
                }
            }

        } else {
            // Play sound effect to notify user the touch successful
            SoundManager.getInstance().play();
            sendFidToServer();
            // Close error alert dialog

        }
    }

    @Override
    public void onReaderDetached(BaseReader reader) {
        if (reader instanceof PasoriReaderNonRooted) {
            // Dismiss reader error dialog

        }
    }
}
