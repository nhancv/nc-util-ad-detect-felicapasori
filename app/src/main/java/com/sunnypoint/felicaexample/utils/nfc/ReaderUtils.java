package com.sunnypoint.felicaexample.utils.nfc;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.nfc.NfcAdapter;
import android.os.Build;
import android.os.Handler;
import android.util.Log;

import com.sunnypoint.felicaexample.utils.Utils;

import java.util.concurrent.atomic.AtomicInteger;

public class ReaderUtils {
    private static final String TAG = "ReaderUtils";
    private static AtomicInteger sNfcState;

    public static BaseReader getReader(Activity activity, Handler handler) {
        return new PasoriReaderNonRooted(activity, handler);
    }

    public static boolean isAndroidReaderAvailable() {
        return (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) && (NfcAdapter.getDefaultAdapter(Utils.getInstance().getContext()) != null);
    }

    @SuppressLint("InlinedApi")
    public static synchronized void checkNfcState(Context context) {
        if (isAndroidReaderAvailable()) {
            NfcAdapter adapter = NfcAdapter.getDefaultAdapter(context);
            if (adapter.isEnabled()) {
                setNfcState(NfcAdapter.STATE_ON);
                Log.i(TAG, "checkNfcState()..STATE_ON");
            }
        }
    }

    public static synchronized AtomicInteger getNfcState() {
        return sNfcState;
    }

    public static synchronized void setNfcState(int state) {
        if (isAndroidReaderAvailable()) {
            if (sNfcState == null) {
                sNfcState = new AtomicInteger();
            }
            sNfcState.set(state);
        }
    }

}
