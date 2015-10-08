package com.sunnypoint.felicaexample.utils.nfc;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.sunnypoint.felicaexample.model.LogInfo;
import com.sunnypoint.felicaexample.utils.Configs;
import com.sunnypoint.felicaexample.utils.Utils;

public class PasoriReaderNonRooted extends BaseReader {

    /**
     * Debug tag
     */
    private static final String TAG = "PasoriReaderNonRooted";

    /**
     * ACTION for usb permission
     */
    private static final String ACTION_USB_PERMISSION = "com.lcl.sunnypoints.USB_PERMISSION";

    /**
     * Java lock used to synchronize access to usb reader
     */
    private static final Object LOCK = PasoriReaderNonRooted.class;

    /**
     * Usb connect manager
     */
    private UsbManager mManager;

    /**
     * Intent for requesting usb permission
     */
    private PendingIntent mPermissionIntent;

    /**
     * Keep task instance to force interrupt when close reader.
     */
    private AsyncTask<UsbDevice, Void, byte[]> mOpenTask;

    /**
     * Handle nfc100 usb connection
     */
    private Nfc100Usb mNfc100Usb;
    /**
     * Receive usb and screen actions
     */
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (ACTION_USB_PERMISSION.equals(action)) {
                Log.v(TAG, "ACTION_USB_PERMISSION");
                LogInfo.getInstance().setMsg(TAG+ ": ACTION_USB_PERMISSION");
                synchronized (this) {
                    UsbDevice device = intent.getParcelableExtra(UsbManager.EXTRA_DEVICE);
                    if (device != null) {
                        mHandler.removeMessages(MSG_DETECT_USB_CONNECTION);
                        mReaderDetached = false;
                        if (intent.getBooleanExtra(UsbManager.EXTRA_PERMISSION_GRANTED, false)) {
                            // Open reader
                            mOpenTask = new OpenTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, device);
                        } else {
                            // Request usb permission again
                            startPolling("ACTION_USB_PERMISSION denied");
                        }
                    }
                }

            } else if (UsbManager.ACTION_USB_DEVICE_DETACHED.equals(action)) {
                Log.v(TAG, "ACTION_USB_DEVICE_DETACHED");
                LogInfo.getInstance().setMsg(TAG+ ": ACTION_USB_DEVICE_DETACHED");
                UsbDevice device = intent.getParcelableExtra(UsbManager.EXTRA_DEVICE);
                if (device != null) {
                    mReaderDetached = true;
                    // Usb has been detach, so we try to poll message to
                    // detect usb connection
                    closeReader("ACTION_USB_DEVICE_DETACHED");

                    // Try reconnect device
                    startPolling("ACTION_USB_DEVICE_DETACHED");

                    // Dismiss reader error dialog
                    ((ReaderCallback) mActivity).onReaderDetached(PasoriReaderNonRooted.this);
                }
            }
        }
    };

    public PasoriReaderNonRooted(Activity activity, Handler handler) {
        super(activity, handler);
    }

    @Override
    public void createReader() {
        Log.v(TAG, "createReader()");
        LogInfo.getInstance().setMsg(TAG + ": Create Reader");
        // Get USB manager
        mIDM = new byte[8];
        mManager = (UsbManager) mActivity.getSystemService(Context.USB_SERVICE);
        mPermissionIntent = PendingIntent.getBroadcast(mActivity, 0, new Intent(ACTION_USB_PERMISSION), 0);
        mNfc100Usb = new Nfc100Usb(mManager);
        // Start polling
        startPolling("createReader");
    }

    @Override
    public void destroyReader() {

    }

    @Override
    public boolean isSupported(Object device) {
        if (device != null) {
            UsbDevice dev = (UsbDevice) device;
            int vendorId = dev.getVendorId();
            int productId = dev.getProductId();
            if (vendorId == Configs.VENDOR_ID) {
                for (int i = 0; i < Configs.PRODUCT_IDS.length; i++) {
                    if (productId == Configs.PRODUCT_IDS[i]) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean isReaderAvailable() {
        for (UsbDevice device : mManager.getDeviceList().values()) {
            if (isSupported(device)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public byte[] openReader(String portName) {
        synchronized (LOCK) {
            if (!mReaderOpened) {
                Log.v(TAG, "openReader() Begin opening " + portName);
                LogInfo.getInstance().setMsg(TAG+ ": openReader() Begin opening " + portName);
                int openCode = mNfc100Usb.open();
                if (openCode == Nfc100.ICS_ERROR_SUCCESS) {
                    mReaderOpened = true;
                    Log.v(TAG, "openReader() OK");
                    LogInfo.getInstance().setMsg(TAG + ": openReader() OK");
                } else {
                    byte[] openResult = new byte[]{(byte) openCode};
                    Log.e(TAG, "openReader() Not OK " + openCode);
                    LogInfo.getInstance().setMsg(TAG + ": openReader() Not OK " + openCode);
                    // Return open error
                    return openResult;
                }
            }
        }
        return null;
    }

    @Override
    public void closeReader(String log) {
        Log.i(TAG, "closeReader() from " + log);
        LogInfo.getInstance().setMsg(TAG + ": Close Reader from " + log);
        synchronized (LOCK) {
            mHandler.removeMessages(MSG_DETECT_USB_CONNECTION);
            boolean isOpened = mReaderOpened;
            mReaderOpened = false;
            if (isOpened) {
                mNfc100Usb.close();
            }
            if (mOpenTask != null) {
                mOpenTask.cancel(true);
                mOpenTask = null;
            }
        }
    }

    @Override
    public void startPolling(String log) {
        if (log != null) {
            Log.i(TAG, "startPolling() from " + log);
            LogInfo.getInstance().setMsg(TAG + ": Start Polling from " + log);
        }
        // Check mOpenTask is null to warrant once OpenTask only run at the same
        // time
        UsbDevice detectedDevice = null;
        if (!mActivity.isFinishing() && mScreenForeground && mManager != null && mOpenTask == null) {
            for (UsbDevice device : mManager.getDeviceList().values()) {
                if (isSupported(device)) {
                    detectedDevice = device;
                    break;
                }
            }

            if (detectedDevice == null) {
                // No pasori device founded => Send detect message
                // Log.v(TAG,
                // "startPolling() not found any devices => send MSG_DETECT_USB_CONNECTION delay");
                mHandler.removeMessages(MSG_DETECT_USB_CONNECTION);
                mHandler.sendEmptyMessageDelayed(MSG_DETECT_USB_CONNECTION, DETECT_DELAY_TIME);

            } else {
                Log.v(TAG, "startPolling() found and connect to device " + detectedDevice);
                LogInfo.getInstance().setMsg(TAG + ": Start polling found and connect to device " + detectedDevice);
                mHandler.removeMessages(MSG_DETECT_USB_CONNECTION);
                mReaderDetached = false;
                mManager.requestPermission(detectedDevice, mPermissionIntent);
            }
        } else {
            Log.v(TAG, "startPolling() CAN'T START >> isFinishing: " + mActivity.isFinishing() + ", mScreenForeground: " + mScreenForeground + ", mManager: "
                    + (mManager != null) + ", mOpenTask: " + (mOpenTask != null));
            LogInfo.getInstance().setMsg(TAG + ": startPolling() CAN'T START >> isFinishing: " + mActivity.isFinishing() + ", mScreenForeground: " + mScreenForeground + ", mManager: "
                    + (mManager != null) + ", mOpenTask: " + (mOpenTask != null));
        }
    }

    @Override
    public void stopPolling(String log) {
        Log.v(TAG, log + " => stopPolling()");
        LogInfo.getInstance().setMsg(TAG + ": => stopPolling()");
        closeReader(log);
    }

    @Override
    public void pushUrl(Object tag) {
        new Thread("pushingThread") {
            @Override
            public void run() {
                Log.v(TAG, "pushURL()");
                LogInfo.getInstance().setMsg(TAG+ ": pushURL()");
                long begin = System.currentTimeMillis();
                boolean result = false;
                try {
                    byte[] command = getPushData();
                    int rc = mNfc100Usb.nfc100_felica_push(command);
                    result = (rc == Nfc100.ICS_ERROR_SUCCESS);
                } catch (Exception e) {
                    Log.e(TAG, "Error when pushing URL. " + e.getMessage());
                    LogInfo.getInstance().setMsg(TAG + ": Error when pushing URL. " + e.getMessage());
                    e.printStackTrace();

                    // Log error
                } finally {
                    long duration = System.currentTimeMillis() - begin;
                    Log.i(TAG, "pushURL() result=" + result + ", time=" + duration);
                    LogInfo.getInstance().setMsg(TAG + ": pushURL() result=" + result + ", time=" + duration);

                    // Log push status
                }
            }
        }.start();
    }

    @Override
    public void onActivityResumed(Activity activity) {
        super.onActivityResumed(activity);
        // Register usb_device_detached event
        IntentFilter filter = new IntentFilter();
        filter.addAction(ACTION_USB_PERMISSION);
        filter.addAction(UsbManager.ACTION_USB_DEVICE_DETACHED);
        mActivity.registerReceiver(mReceiver, filter);

        // Send delay to waiting for ACTION_USB_PERMISSION
        Log.i(TAG, "Send MSG_DETECT_USB_CONNECTION from onResume()");
        LogInfo.getInstance().setMsg(TAG+ ": Send MSG_DETECT_USB_CONNECTION from onResume()");
        mHandler.removeMessages(MSG_DETECT_USB_CONNECTION);
        mHandler.sendEmptyMessageDelayed(MSG_DETECT_USB_CONNECTION, DETECT_DELAY_TIME);
    }

    @Override
    public void onActivityPaused(Activity activity) {
        super.onActivityPaused(activity);

        // Unregister usb_device_detached event
        mActivity.unregisterReceiver(mReceiver);

        // Close reader
        closeReader("onPause");
    }

    @Override
    public void handleMessageHandler(Message msg) {
        switch (msg.what) {
            case MSG_DETECT_USB_CONNECTION:
                startPolling(null);
                break;
        }
    }

    public int getErrorCode() {
        return mErrorCode;
    }

    @Override
    public String getName() {
        return "RCS380";
    }

    private class OpenTask extends AsyncTask<UsbDevice, Void, byte[]> {

        @Override
        protected byte[] doInBackground(UsbDevice... params) {
            Log.v(TAG, "OpenTask.doInBackground()");
            LogInfo.getInstance().setMsg(TAG+ ": OpenTask.doInBackground()");
            try {
                // Open reader
                mNfc100Usb.setDevice(params[0]);
                String portName = params[0].getDeviceName();
                byte[] openResult = openReader(portName);
                if (openResult != null) {
                    // Return open error
                    return openResult;
                }

                mIDM = mNfc100Usb.polling();
                mPolledType = mNfc100Usb.getPolledType();
                if (mIDM != null) {
                    String hexIdm = Utils.getHexString(mIDM).toString();
                    Log.i(TAG, "OpenTask -> IDM: " + hexIdm);
                    LogInfo.getInstance().setMsg(TAG+ ": OpenTask -> IDM: " + hexIdm);
                    Utils.getInstance().setUserFID(hexIdm, PasoriReaderNonRooted.this);
                    return mIDM;
                }

            } catch (Exception e) {
                Log.e(TAG, "OpenTask error when polling.." + e.getMessage());
                LogInfo.getInstance().setMsg(TAG+ ": OpenTask error when polling.." + e.getMessage());
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(byte[] card_info) {
            // Release openTask references to can open later if communication
            // failed
            mOpenTask = null;

            Log.v(TAG, "PasoriReaderNonRooted.OpenTask: onPostExecute()");
            LogInfo.getInstance().setMsg(TAG+ ": PasoriReaderNonRooted.OpenTask: onPostExecute()");
            if (card_info == null) {
                // Unknown error code
                mErrorCode = Nfc100.ICS_ERROR_UNKNOWN;
                ((ReaderCallback) mActivity).onTagDiscovered(PasoriReaderNonRooted.this, false);

            } else {
                if (card_info.length == 1) {
                    if (card_info[0] != Nfc100.ICS_ERROR_SKIP_OPEN) {
                        // Some error occur when open NFC-100 Reader. Show
                        // alert dialog to notify user re-plug usb device
                        // (consider..)
                        mErrorCode = card_info[0];
                        ((ReaderCallback) mActivity).onTagDiscovered(PasoriReaderNonRooted.this, false);
                        // showReaderErrorDialog();
                    } else {
                        // TODO Can we open reader continue? Show error dialog?
                    }

                } else {
                    Log.i(TAG, "IDM:" + Utils.getHexString(mIDM));
                    LogInfo.getInstance().setMsg(TAG + ": IDM:" + Utils.getHexString(mIDM));
                    if (mNfc100Usb.getPolledType() == Nfc100.POLLED_FELICA) { // Felica
                        pushUrl(null);
                    }
                    mErrorCode = Nfc100.ICS_ERROR_SUCCESS;
                    ((ReaderCallback) mActivity).onTagDiscovered(PasoriReaderNonRooted.this, true);
                }
            }
        }
    }
}
