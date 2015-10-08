package com.sunnypoint.felicaexample.utils.nfc.util;


import android.util.Log;

import com.felicanetworks.mfc.PushSegment;
import com.felicanetworks.mfc.PushStartBrowserSegment;
import com.sunnypoint.felicaexample.model.LogInfo;
import com.sunnypoint.felicaexample.utils.nfc.PushCommand;

import net.kazzz.felica.FeliCaException;
import net.kazzz.felica.lib.FeliCaLib.IDm;

public class FelicaPushUtil {

    private static final String TAG = FelicaPushUtil.class.getSimpleName();

    public static PushSegment getPushSegment(String url, String browserParam) {
        Log.v(TAG, "getPushSegment");
        LogInfo.getInstance().setMsg(TAG + ": getPushSegment");

        final PushStartBrowserSegment segment;
        segment = new PushStartBrowserSegment(url, browserParam);

        Log.v(TAG, "Segment:" + segment.getURL() + "Param:" + segment.getBrowserStartupParam());
        LogInfo.getInstance().setMsg(TAG + ": Segment:" + segment.getURL() + "Param:" + segment.getBrowserStartupParam());

        return segment;
    }

    public static byte[] getPushData(PushSegment segment, byte[] idm_) {
        Log.v(TAG, "getPushData");
        LogInfo.getInstance().setMsg(TAG + ": getPushData");
        Log.v(TAG, "Idm:" + getString(idm_));
        LogInfo.getInstance().setMsg(TAG + ": Idm:" + getString(idm_));

        try {
            final PushCommand pushCommand = PushCommand.create(new IDm(idm_), segment);
            final byte[] pushData = pushCommand.getBytes();
            Log.v(TAG, "Push Data hexString:" + getString(pushData));
            LogInfo.getInstance().setMsg(TAG + ": Push Data hexString:" + getString(pushData));
            return pushData;
        } catch (FeliCaException e) {
            Log.v(TAG, "Create Push Command Error");
            LogInfo.getInstance().setMsg(TAG + ": Create Push Command Error");
            return null;
        }

    }

    public static byte[] getPushData(String url, String browserParam, byte[] idm_) {
        Log.v(TAG, "getPushData");
        LogInfo.getInstance().setMsg(TAG + ": getPushData");
        Log.v(TAG, "Idm:" + getString(idm_));
        LogInfo.getInstance().setMsg(TAG + ": Idm:" + getString(idm_));

        PushSegment segment = FelicaPushUtil.getPushSegment(url, browserParam);
        try {
            final PushCommand pushCommand = PushCommand.create(new IDm(idm_), segment);
            final byte[] pushData = pushCommand.getBytes();

            Log.v(TAG, "Push Data hexString:" + getString(pushData));
            LogInfo.getInstance().setMsg(TAG + ": Push Data hexString:" + getString(pushData));

            return pushData;
        } catch (FeliCaException e) {
            Log.v(TAG, "Create Push Command Error");
            LogInfo.getInstance().setMsg(TAG + ": Create Push Command Error");

            return null;
        }

    }

    public static StringBuffer getString(byte[] byteArray) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < byteArray.length; i++) {
            String hex = Integer.toHexString(0xFF & byteArray[i]);
            if (hex.length() == 1) {
                // could use a for loop, but we're only dealing with a single
                // byte
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString;
    }

}
