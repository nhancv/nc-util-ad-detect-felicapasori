package com.sunnypoint.felicaexample.views;

import android.databinding.DataBindingUtil;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.sunnypoint.felicaexample.R;
import com.sunnypoint.felicaexample.databinding.ActivityMainBinding;
import com.sunnypoint.felicaexample.model.LogInfo;
import com.sunnypoint.felicaexample.utils.SoundManager;
import com.sunnypoint.felicaexample.utils.Utils;
import com.sunnypoint.felicaexample.utils.nfc.BaseReader;
import com.sunnypoint.felicaexample.utils.nfc.PasoriReaderNonRooted;
import com.sunnypoint.felicaexample.utils.nfc.ReaderCallback;
import com.sunnypoint.felicaexample.utils.nfc.ReaderUtils;
import com.sunnypoint.ripplebackground.RippleBackground;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
                         LogInfo.getInstance().setMsg("Error "+errorCode);
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

    @Bind(R.id.vBackground)
    RippleBackground vBackground;
    @Bind(R.id.txtLogView)
    TextView txtLogView;

    @Bind({R.id.btnPolling})
    List<View> viewList;
    static final ButterKnife.Action<View> DISABLE = new ButterKnife.Action<View>() {
        @Override
        public void apply(View view, int index) {
            view.setEnabled(false);
        }
    };
    static final ButterKnife.Setter<View, Boolean> ENABLED = new ButterKnife.Setter<View, Boolean>() {
        @Override
        public void set(View view, Boolean value, int index) {
            view.setEnabled(value);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setLogView(LogInfo.getInstance());
//        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Utils.getInstance().setContext(getApplicationContext());
        mReader = ReaderUtils.getReader(this, mHandler);
        mReader.createReader();
        Log.i(TAG, Thread.currentThread().getStackTrace()[2].getMethodName().toString());
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        SoundManager.getInstance().init(getApplicationContext());

        txtLogView.setMovementMethod(new ScrollingMovementMethod());


    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: ");
        mReader.onActivityResumed(this);
    }

    @OnClick(R.id.btnPolling)
    public void btnPollingOnClick(View view) {
        ButterKnife.apply(viewList, ENABLED, false);
        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    ButterKnife.apply(viewList, ENABLED, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 1000);
//        ButterKnife.apply(viewList, View.ALPHA, 0.0f);

        vBackground.startRippleAnimation();
        LogInfo.getInstance().setMsg("Start Polling");
        mReader.onActivityPaused(this);
        mReader.onActivityResumed(this);
        mReader.stopPolling("Stop to Create");
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
            Toast.makeText(getApplicationContext(), "Please tap again and hold the phone on the reader longer", Toast.LENGTH_LONG).show();
            mReader.setTapped(false);
            mReader.startPolling("sendFidToServer() Invalid FID");

        } else {
            if (!mReader.getTapped()) {
                mReader.setTapped(true);
            }
        }
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
            vBackground.stopRippleAnimation();
            LogInfo.getInstance().setMsg("Successful: "+ Utils.getInstance().getUserFID() + " has been detected.");
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
