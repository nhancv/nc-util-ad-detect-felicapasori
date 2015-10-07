package com.sunnypoint.felicaexample.utils.nfc;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.os.Build;
import android.util.Log;

@TargetApi(Build.VERSION_CODES.KITKAT)
public class NfcStateChangedReceiver extends BroadcastReceiver {

    private static final String TAG = "NfcStateChangedReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        final String action = intent.getAction();

        if (action.equals(NfcAdapter.ACTION_ADAPTER_STATE_CHANGED)) {
            final int state = intent.getIntExtra(NfcAdapter.EXTRA_ADAPTER_STATE, NfcAdapter.STATE_OFF);
            switch (state) {
                case NfcAdapter.STATE_OFF:
                    Log.i(TAG, "NfcAdapter.ACTION_ADAPTER_STATE_CHANGED..STATE_OFF");
                    break;
                case NfcAdapter.STATE_TURNING_OFF:
                    Log.i(TAG, "NfcAdapter.ACTION_ADAPTER_STATE_CHANGED..STATE_TURNING_OFF");
                    break;
                case NfcAdapter.STATE_ON:
                    Log.i(TAG, "NfcAdapter.ACTION_ADAPTER_STATE_CHANGED..STATE_ON");
                    break;
                case NfcAdapter.STATE_TURNING_ON:
                    Log.i(TAG, "NfcAdapter.ACTION_ADAPTER_STATE_CHANGED..STATE_TURNING_ON");
                    break;
            }
            ReaderUtils.setNfcState(state);

        } else if (action.equals(Intent.ACTION_BOOT_COMPLETED)) {
            Log.d(TAG, "ACTION_BOOT_COMPLETED ");
            ReaderUtils.checkNfcState(context);
        }
    }

}
