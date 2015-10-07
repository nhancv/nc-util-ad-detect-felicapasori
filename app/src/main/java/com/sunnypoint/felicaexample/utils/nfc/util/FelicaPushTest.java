package com.sunnypoint.felicaexample.utils.nfc.util;


import android.util.Log;

import com.felicanetworks.mfc.PushSegment;
import com.felicanetworks.mfc.PushStartBrowserSegment;
import com.sunnypoint.felicaexample.utils.nfc.PushCommand;

import net.kazzz.felica.FeliCaException;
import net.kazzz.felica.lib.FeliCaLib.IDm;

public class FelicaPushTest {

    final static byte[] idm_ = new byte[]{1, 41, 0, 2, -83, 16, 94, 21};
    private static final String TAG = FelicaPushTest.class.getSimpleName();
    static String url = "http://google.com";
    static String browserParam = null;

    public static PushSegment getPushSegment() {
        Log.v(TAG, "getPushSegment");

        final PushStartBrowserSegment segment;
        segment = new PushStartBrowserSegment(url, browserParam);

        Log.v(TAG, "Segment:" + segment.getURL() + "Param:" + segment.getBrowserStartupParam());

        return segment;
    }

    public static byte[] getPushData() {
        Log.v(TAG, "getPushData");
        Log.v(TAG, "Idm:" + getString(idm_));

        PushSegment segment = getPushSegment();

        try {
            final PushCommand pushCommand = PushCommand.create(new IDm(idm_), segment);
            final byte[] pushData = pushCommand.getBytes();

            Log.v(TAG, "Push Data hexString:" + getString(pushData));

            return pushData;
        } catch (FeliCaException e) {
            Log.v(TAG, "Create Push Command Error");
            return null;
        }

    }

    public static StringBuffer getString(byte[] byteArray) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < byteArray.length; i++) {
            String hex = Integer.toHexString(0xFF & byteArray[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString;
    }

}
