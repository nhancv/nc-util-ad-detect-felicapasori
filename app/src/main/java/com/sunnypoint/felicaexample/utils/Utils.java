package com.sunnypoint.felicaexample.utils;

import android.content.Context;

import com.sunnypoint.felicaexample.utils.nfc.BaseReader;

import java.util.Locale;

/**
 * Created by NhanCao on 07-Oct-15.
 */
public class Utils {
    private static Utils instance = new Utils();
    public String userFID = "";
    public String accountId = "";
    public String readerType = "";
    public String fidType = "";
    private Context context;

    public static Utils getInstance() {
        return instance;
    }

    public static StringBuffer getHexString(byte[] data) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < data.length; i++) {
            String hex = Integer.toHexString(0xFF & data[i]);
            if (hex.length() == 1) {
                // could use a for loop, but we're only dealing with a single
                // byte
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString;

    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public String getUserFID() {
        return userFID;
    }

    public void setUserFID(String userFID, BaseReader reader) {
        setUserFID(userFID, null, reader);
    }

    public void setUserFID(String userFID, String accountId, BaseReader reader) {
        this.userFID = userFID.toUpperCase(Locale.getDefault());
        this.accountId = accountId;
        if (reader != null) {
            readerType = reader.getName();
            fidType = reader.getPolledTypeString();
        } else {
            readerType = "";
            fidType = "ID";
        }
    }
}
