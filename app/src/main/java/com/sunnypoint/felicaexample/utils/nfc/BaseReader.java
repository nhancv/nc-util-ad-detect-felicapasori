package com.sunnypoint.felicaexample.utils.nfc;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.sunnypoint.felicaexample.utils.Utils;
import com.sunnypoint.felicaexample.utils.nfc.util.FelicaPushUtil;


public abstract class BaseReader implements ReaderPolicy, Application.ActivityLifecycleCallbacks {

    /**
     * Message id to delay detect reader connection. This message will be sent
     * after reader unplug or screen resume.
     */
    protected static final int MSG_DETECT_USB_CONNECTION = 500;

    /**
     * Delay time to detect usb connection
     */
    protected static final int DETECT_DELAY_TIME = 500;

    /**
     * Activity has implemented ReaderCallback.
     */
    protected Activity mActivity;

    /**
     * Handler in activity
     */
    protected Handler mHandler;

    /**
     * Id of the detected card (Felica idm bytes or Mifare Uid bytes)
     */
    protected byte[] mIDM;

    /**
     * The flag indicates the card has been detected (Only process one card at
     * the same time
     */
    protected boolean mIsTapped;

    /**
     * Indicate idle screen has user focus. If false, we don't need poll message
     * delay to detect usb connection.
     */
    protected boolean mScreenForeground;

    /**
     * Indicate use device has been detach. If true, we don't show request usb
     * permission dialog.
     */
    protected boolean mReaderDetached;

    /**
     * The flag indicates NFC-100 Reader is opened or not. If opened we must
     * close when activity stop
     */
    protected boolean mReaderOpened;

    protected int mPolledType;

    /**
     * Error code for debug
     */
    protected int mErrorCode;

    public BaseReader(Activity activity, Handler handler) {
        if (activity == null || handler == null) {
            throw new IllegalArgumentException("Activity and handler require not null");
        }
        if (activity instanceof ReaderCallback) {
            mActivity = activity;
            mHandler = handler;
        } else {
            throw new IllegalArgumentException("Activity must implement ReaderCallback");
        }
    }

    /**
     * @return true if tag/card was discovered
     */
    public boolean getTapped() {
        return mIsTapped;
    }

    /**
     * Set true if tag/card was discovered
     */
    public void setTapped(boolean value) {
        mIsTapped = value;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        destroyReader();
    }

    @Override
    public void onActivityPaused(Activity activity) {
        mScreenForeground = false;
    }

    @Override
    public void onActivityResumed(Activity activity) {
        mScreenForeground = true;
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    public void onActivityNewIntent(Activity activity) {

    }

    public void onFinishActivity(Activity activity) {

    }

    public abstract void handleMessageHandler(Message msg);

    public byte[] getPushData() {
        String hexIdm = Utils.getInstance().getUserFID();
        String url = "http://google.com?fid=" + hexIdm;
        return FelicaPushUtil.getPushData(url, null, mIDM);
    }

    @Override
    public int getPolledType() {
        return mPolledType;
    }

    public String getPolledTypeString() {
        switch (mPolledType) {
            case Nfc100.POLLED_FELICA:
                return "F";
            case Nfc100.POLLED_TYPEA:
                return "A";
            default:
                return "O";
        }
    }

    public int getErrorCode() {
        return mErrorCode;
    }

    @Override
    public byte[] getIDM() {
        return mIDM;
    }
}
