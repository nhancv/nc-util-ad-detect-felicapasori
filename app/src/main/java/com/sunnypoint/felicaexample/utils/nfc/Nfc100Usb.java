package com.sunnypoint.felicaexample.utils.nfc;

import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbManager;
import android.util.Log;

import java.util.Random;


/**
 * Handle NFC100 usb connection
 */
public class Nfc100Usb extends Nfc100 {

    /**
     * Debug TAG
     */
    private static final String TAG = "Nfc100Usb";

    /**
     * Max retry initialize nfc100 device
     */
    private static final int MAX_RETRY = 3;

    /**
     * 0: Not available
     */
    private int mPolledType;

    /**
     * Flag indicates polling
     */
    private boolean mIsPolling;

    /**
     * Usb connect manager
     */
    private UsbManager mManager;

    /**
     * Current connecting usb device
     */
    private UsbDevice mDevice;
    private UsbInterface mInterface;
    private UsbDeviceConnection mConnection;
    private UsbEndpoint mInput;
    private UsbEndpoint mOutput;

    public Nfc100Usb(UsbManager manager) {
        mManager = manager;
    }

    public void setDevice(UsbDevice device) {
        mDevice = device;
    }

    public int getPolledType() {
        return mPolledType;
    }

    @Override
    public byte getSupportedCommandType() {
        return NFC100_SUPPORTED_COMMAND_TYPE;
    }

    @Override
    public int open() {
        if (mDevice == null) {
            return ICS_ERROR_IO;
        }

        if (mDevice.getInterfaceCount() != 1) {
            return ICS_ERROR_IO;
        }
        mInterface = mDevice.getInterface(0);
        if (mInterface == null) {
            return ICS_ERROR_IO;
        }

        if (mInterface.getEndpointCount() != 2) {
            return ICS_ERROR_IO;
        }
        mOutput = mInterface.getEndpoint(0);
        mInput = mInterface.getEndpoint(1);
        if (mOutput == null || mInput == null) {
            return ICS_ERROR_IO;
        }

        mConnection = mManager.openDevice(mDevice);
        if (mConnection == null) {
            return ICS_ERROR_IO;
        }

        if (!mConnection.claimInterface(mInterface, true)) {
            return ICS_ERROR_IO;
        }

        for (int i = 0; i < MAX_RETRY; i++) {
            int rc = nfc100_initialize_device(NFC100_DEFAULT_TIMEOUT);
            if (rc == ICS_ERROR_SUCCESS) {
                return rc;
            }
            Log.e(TAG, "nfc100_initialize_device error=" + rc + ", retry=" + (i + 1));
            if (i == MAX_RETRY - 1) {
                return rc;
            }
        }
        return ICS_ERROR_IO;
    }

    @Override
    public int close() {
        mDevice = null;
        mIsPolling = false;
        nfc100_rf_off(NFC100_DEFAULT_TIMEOUT);
        mConnection.releaseInterface(mInterface);
        mConnection.close();
        return ICS_ERROR_SUCCESS;
    }

    @Override
    public byte[] polling() {
        boolean rfOn = false;
        mPolledType = 0;
        mIsPolling = true;
        mIDM = new byte[8];
        Random rd = new Random();

        while (mIsPolling) {
            if (!rfOn) {
                int rc = nfc100_rf_on(NFC100_DEFAULT_TIMEOUT);
                if (rc == ICS_ERROR_SUCCESS) {
                    rfOn = true;
                }
            }

            if (nfc100_felica_polling(NFC100_DEFAULT_TIMEOUT) == ICS_ERROR_SUCCESS) {
                if (((mIDM[0] == 0x01) && (mIDM[1] == (byte) 0xfe)) || ((mIDM[0] == 0x02) && (mIDM[1] == (byte) 0xfe))
                        || ((mIDM[0] == 0x03) && (mIDM[1] == (byte) 0xfe))) {
                    Log.e(TAG, "felica_cc_polling: ICS_ERROR_FELICA_RANDOM_ID_NOT_ACCEPTED");
                    nfc100_rf_off(NFC100_DEFAULT_TIMEOUT);
                    rfOn = false;
                } else {
                    mPolledType = POLLED_FELICA;
                    break;
                }
            } else if (nfc100_typea_polling(NFC100_DEFAULT_TIMEOUT) == ICS_ERROR_SUCCESS) {
                if (mSAK != 0x08) {
                    Log.e(TAG, "nfc100_typea_polling: ICS_ERROR_MIFARE_NOT_ACCEPTED");
                    nfc100_rf_off(NFC100_DEFAULT_TIMEOUT);
                    rfOn = false;
                } else {
                    mPolledType = POLLED_TYPEA;
                    break;
                }
            }

            if (!mIsPolling) {
                break;
            }

            try {
                Thread.sleep(100 + rd.nextInt(100));
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }

        mIsPolling = false;
        if (mPolledType != POLLED_FELICA && mPolledType != POLLED_TYPEA) {
            return null;
        }
        return mIDM;
    }

    @Override
    public int write(int length, int timeout) {
        logDump("write", mSendBuffer, 0, length);
        mReceiveLength = mConnection.bulkTransfer(mOutput, mSendBuffer, length, timeout);
        return ICS_ERROR_SUCCESS;
    }

    @Override
    public int read(int timeout) {
        mReceiveLength = mConnection.bulkTransfer(mInput, mReceiveBuffer, NFC100_COMMAND_BUF_LEN, timeout);
        logDump("read", mReceiveBuffer, 0, mReceiveLength);
        return ICS_ERROR_SUCCESS;
    }
}
