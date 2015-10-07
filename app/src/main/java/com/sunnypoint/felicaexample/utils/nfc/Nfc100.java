package com.sunnypoint.felicaexample.utils.nfc;

public abstract class Nfc100 {

    // public static final String TAG = "Nfc100";
    public static final int NFC100_MAX_TRANSMIT_DATA_LEN = 1000;
    public static final int NFC100_MAX_COMMAND_LEN = (3 + NFC100_MAX_TRANSMIT_DATA_LEN);
    public static final int NFC100_COMMAND_BUF_LEN = 1024;
    public static final int NFC100_MAX_RECEIVE_DATA_LEN = NFC100_MAX_TRANSMIT_DATA_LEN;
    public static final int NFC100_MAX_RESPONSE_LEN = (3 + NFC100_MAX_RECEIVE_DATA_LEN);

    public static final int NFC100_CANCEL_COMMAND_ACK_TIMEOUT = 10; /* ms */
    public static final int NFC100_CANCEL_COMMAND_PURGE_TIMEOUT = 5; /* ms */

    public static final int NFC100_DEFAULT_TIMEOUT = 400; /* ms */
    public static final int POLLED_FELICA = 1;
    public static final int POLLED_TYPEA = 2;

    public static final int NFC100_COMMAND_POS = 8;
    public static final int NFC100_RESPONSE_POS = 8;

    public static final byte NFC100_COMMAND_CODE = (byte) 0xd6;
    public static final byte NFC100_RESPONSE_CODE = (byte) 0xd7;

    /* RF communication */
    public static final byte NFC100_CMD_IN_SET_RF = 0x00;
    public static final byte NFC100_RES_IN_SET_RF = 0x01;
    public static final byte NFC100_CMD_IN_SET_PROTOCOL = 0x02;
    public static final byte NFC100_RES_IN_SET_PROTOCOL = 0x03;
    public static final byte NFC100_CMD_IN_COMM_RF = 0x04;
    public static final byte NFC100_RES_IN_COMM_RF = 0x05;
    public static final byte NFC100_CMD_SWITCH_RF = 0x06;
    public static final byte NFC100_RES_SWITCH_RF = 0x07;
    public static final byte NFC100_CMD_TG_SET_RF = 0x40;
    public static final byte NFC100_RES_TG_SET_RF = 0x41;
    public static final byte NFC100_CMD_TG_SET_PROTOCOL = 0x42;
    public static final byte NFC100_RES_TG_SET_PROTOCOL = 0x43;
    public static final byte NFC100_CMD_TG_SET_AUTO = 0x44;
    public static final byte NFC100_RES_TG_SET_AUTO = 0x45;
    public static final byte NFC100_CMD_TG_SET_RF_OFF = 0x46;
    public static final byte NFC100_RES_TG_SET_RF_OFF = 0x47;
    public static final byte NFC100_CMD_TG_COMM_RF = 0x48;
    public static final byte NFC100_RES_TG_COMM_RF = 0x49;
    public static final byte NFC100_CMD_GET_COMMAND_TYPE = 0x28;
    public static final byte NFC100_RES_GET_COMMAND_TYPE = 0x29;
    public static final byte NFC100_CMD_SET_COMMAND_TYPE = 0x2a;
    public static final byte NFC100_RES_SET_COMMAND_TYPE = 0x2b;

    /* The status code of NFC Port-100 command (except InCommRF/TgCommRF) */
    public static final byte NFC100_DEV_STATUS_SUCCESS = 0x00;
    public static final byte NFC100_DEV_STATUS_PARAMETER_ERROR = 0x01;
    public static final byte NFC100_DEV_STATUS_PB_ERROR = 0x02;
    public static final byte NFC100_DEV_STATUS_RFCA_ERROR = 0x03;
    public static final byte NFC100_DEV_STATUS_TEMPERATURE_ERROR = 0x04;
    public static final byte NFC100_DEV_STATUS_PWD_ERROR = 0x05;
    public static final byte NFC100_DEV_STATUS_RECEIVE_ERROR = 0x06;
    public static final byte NFC100_DEV_STATUS_COMMANDTYPE_ERROR = 0x07;

    /* The status code of InCommRF/TgCommRF command */
    public static final int NFC100_RF_STATUS_SUCCESS = 0x00000000;
    public static final int NFC100_RF_STATUS_PROTOCOL_ERROR = 0x00000001;
    public static final int NFC100_RF_STATUS_PARITY_ERROR = 0x00000002;
    public static final int NFC100_RF_STATUS_CRC_ERROR = 0x00000004;
    public static final int NFC100_RF_STATUS_COLLISION_ERROR = 0x00000008;
    public static final int NFC100_RF_STATUS_OVERFLOW_ERROR = 0x00000010;
    public static final int NFC100_RF_STATUS_TEMPERATURE_ERROR = 0x00000040;
    public static final int NFC100_RF_STATUS_REC_TIMEOUT_ERROR = 0x00000080;
    public static final int NFC100_RF_STATUS_CRYPTO1_ERROR = 0x00000100;
    public static final int NFC100_RF_STATUS_RFCA_ERROR = 0x00000200;
    public static final int NFC100_RF_STATUS_RF_OFF = 0x00000400;
    public static final int NFC100_RF_STATUS_TRA_TIMEOUT_ERROR = 0x00000800;
    public static final int NFC100_RF_STATUS_RECEIVELENGTH_ERROR = 0x80000000;

    public static final int ICS_ERROR_UNKNOWN = -1;
    public static final int ICS_ERROR_SUCCESS = 0;
    public static final int ICS_ERROR_NOT_SUPPORTED = 1;
    public static final int ICS_ERROR_NOT_IMPLEMENTED = 2;
    public static final int ICS_ERROR_NOT_INITIALIZED = 3;
    public static final int ICS_ERROR_NOT_OPENED = 4;
    public static final int ICS_ERROR_ALREADY_OPENED = 5;
    public static final int ICS_ERROR_INVALID_PARAM = 6;
    public static final int ICS_ERROR_ILLEGAL_MODE = 7;
    public static final int ICS_ERROR_FATAL = 8;
    public static final int ICS_ERROR_IO = 9;
    public static final int ICS_ERROR_NO_RESOURCES = 10;
    public static final int ICS_ERROR_BUSY = 11;
    public static final int ICS_ERROR_PERMISSION = 12;
    public static final int ICS_ERROR_TIMEOUT = 13;
    public static final int ICS_ERROR_FRAME_CRC = 14;
    public static final int ICS_ERROR_INVALID_RESPONSE = 15;
    public static final int ICS_ERROR_INVALID_FRAME = 15; /* obsolete */
    public static final int ICS_ERROR_SYNTAX = 16;
    public static final int ICS_ERROR_BUF_OVERFLOW = 17;
    public static final int ICS_ERROR_DATA_TRANS_START = 18;
    public static final int ICS_ERROR_DATA_TRANS_END = 19;
    public static final int ICS_ERROR_NOT_STARTED = 20;
    public static final int ICS_ERROR_ALREADY_STARTED = 21;
    public static final int ICS_ERROR_SEQUENCE = 22;
    public static final int ICS_ERROR_DESELECTED = 23;
    public static final int ICS_ERROR_RELEASED = 24;
    public static final int ICS_ERROR_RF_OFF = 25;

    public static final int ICS_ERROR_NOT_EXIST = 26;
    public static final int ICS_ERROR_ALREADY_EXIST = 27;
    public static final int ICS_ERROR_IGNORE = 28;
    public static final int ICS_ERROR_STATUS_FLAG1 = 29;
    public static final int ICS_ERROR_STATUS_FLAG = 30;
    public static final int ICS_ERROR_SN_OVERFLOW = 31;
    public static final int ICS_ERROR_INVALID_DATA = 32;

    public static final int ICS_ERROR_DISCONNECTED = 33;
    public static final int ICS_ERROR_SHUTDOWN = 34;
    public static final int ICS_ERROR_MANY_ERRORS = 35;
    public static final int ICS_ERROR_NOT_CONNECTED = 36;

    public static final int ICS_ERROR_DEV_BUSY = 37;
    public static final int ICS_ERROR_DEVICE = 38;

    // binhvt
    public static final int ICS_ERROR_INVALID_PARAM_DEVICE = 39;
    public static final int ICS_ERROR_INVALID_PARAM_PORTNAME = 40;
    public static final int ICS_ERROR_INVALID_PARAM_RAWFUNCTION = 41;

    public static final int ICS_ERROR_PERMISSION_BINH_TEST = 42;
    public static final int ICS_ERROR_MIFARE_NOT_ACCEPTED = 43;
    public static final int ICS_ERROR_FELICA_RANDOM_ID_NOT_ACCEPTED = 43;
    public static final int ICS_ERROR_SKIP_OPEN = 44;

    /* Supported command type *//* 0-63 */
    public static final byte NFC100_SUPPORTED_COMMAND_TYPE = 0;

    /* RBT setting numbers */
    public static final byte NFC100_RBT_INITIATOR_ISO18092_212K = 0x01;
    public static final byte NFC100_RBT_INITIATOR_ISO18092_424K = 0x01;
    public static final byte NFC100_RBT_INITIATOR_ISO14443A_106K = 0x02;
    public static final byte NFC100_RBT_INITIATOR_ISO14443B_106K = 0x03;
    public static final byte NFC100_RBT_INITIATOR_ISO14443B_212K = 0x03;
    public static final byte NFC100_RBT_INITIATOR_ISO14443B_424K = 0x03;
    public static final byte NFC100_RBT_INITIATOR_ISO14443B_848K = 0x03;
    public static final byte NFC100_RBT_INITIATOR_ISO14443A_212K = 0x04;
    public static final byte NFC100_RBT_INITIATOR_ISO14443A_424K = 0x05;
    public static final byte NFC100_RBT_INITIATOR_ISO14443A_848K = 0x06;

    /* InSetRF parameter */
    public static final byte NFC100_RF_INITIATOR_ISO18092_212K = 0x01;
    public static final byte NFC100_RF_INITIATOR_ISO18092_424K = 0x02;
    public static final byte NFC100_RF_INITIATOR_ISO18092_106K = 0x03;
    public static final byte NFC100_RF_INITIATOR_ISO14443A_106K = 0x03;
    public static final byte NFC100_RF_INITIATOR_ISO14443A_212K = 0x04;
    public static final byte NFC100_RF_INITIATOR_ISO14443A_424K = 0x05;
    public static final byte NFC100_RF_INITIATOR_ISO14443A_848K = 0x06;
    public static final byte NFC100_RF_INITIATOR_ISO14443B_106K = 0x07;
    public static final byte NFC100_RF_INITIATOR_ISO14443B_212K = 0x08;
    public static final byte NFC100_RF_INITIATOR_ISO14443B_424K = 0x09;
    public static final byte NFC100_RF_INITIATOR_ISO14443B_848K = 0x0a;

    public static final byte NFC100_DEFAULT_RF_RBT_TX = NFC100_RBT_INITIATOR_ISO18092_212K;
    public static final byte NFC100_DEFAULT_RF_RBT_RX = NFC100_RBT_INITIATOR_ISO18092_212K;
    public static final byte NFC100_DEFAULT_RF_SPEED_TX = NFC100_RF_INITIATOR_ISO18092_212K;
    public static final byte NFC100_DEFAULT_RF_SPEED_RX = NFC100_RF_INITIATOR_ISO18092_212K;

    /* FELICA */
    public static final int FELICA_CC_STUB_NFC100_IN_SET_PROTOCOL_TIMEOUT = 100;
    public static final int FELICA_CC_STUB_NFC100_IN_SET_RF_TIMEOUT = 100;

    /* Default RBT */
    public static final byte FELICA_CC_STUB_NFC100_RBT = NFC100_RBT_INITIATOR_ISO18092_212K;
    public static final byte FELICA_CC_STUB_NFC100_SPEED = NFC100_RF_INITIATOR_ISO18092_212K;

    public static final byte[] NFC100_FELICA_DEFAULT_PROTOCOL = {
            //
            0x00, 0x15, /* Initial guard time = 21 (20.4) */
            0x01, 0x01, /* Add CRC = 1 */
            0x02, 0x01, /* Check CRC = 1 */
            0x03, 0x00, /* Multi card = 0 */
            0x04, 0x00, /* Add parity = 0 */
            0x05, 0x00, /* Check parity = 0 */
            0x06, 0x00, /* Bitwise anticollision receiving mode = 0 */
            0x07, 0x08, /* Valid bit number for last transmit byte = 8 */
            0x08, 0x00, /* Crypto1 = 0 */
            0x09, 0x00, /* Add SOF = 0 */
            0x0a, 0x00, /* Check SOF = 0 */
            0x0b, 0x00, /* Add EOF = 0 */
            0x0c, 0x00, /* Check EOF = 0 */
            0x0e, 0x04, /* Deaf time = 4 */
            0x0f, 0x00, /* Continuous receiving mode = 0 */
            0x10, 0x00, /* Min len for CRM = 0 */
            0x11, 0x00, /* Type 1 Tag frame = 0 */
            0x12, 0x00, /* RFCA = 0 */
            0x13, 0x06 /* Guard time at initiator = 6 */
    };
    public static final byte[] FELICA_POLLING_PARAM = {(byte) 0xFF, (byte) 0xFF, 0x00, 0x00};

    /* TYPE A */
    /* InSetRF Timeout */
    public static final int NFC100_TYPEA_IN_SET_RF_TIMEOUT = 500; /* ms */

    /* InSetProtocol Timeout */
    public static final int NFC100_TYPEA_IN_SET_PROTOCOL_TIMEOUT = 500; /* ms */

    public static final int NFC100_TYPEA_FDT_LISTEN_MAX = 5; /* ms */

    public static final byte NFC100_TYPEA_CMD_REQA = 0x26;

    public static final byte[] NFC100_TYPEA_ATQA_PROTOCOL = {
            //
            0x01, /* Add CRC */
            0x00, 0x02, /* Check CRC */
            0x00, 0x04, /* Add parity */
            0x00, 0x05, /* Check parity */
            0x01, 0x06, /* Bitwise anticollision receiving mode */
            0x00, 0x07, /* Valid bit number for last transmit byte */
            0x07, 0x08, /* Crypto1 */
            0x00};

    public static final byte[] NFC100_TYPEA_ANTICOLLISION_PROTOCOL = {
            //
            0x01, /* Add CRC */
            0x00, 0x02, /* Check CRC */
            0x00, 0x04, /* Add parity */
            0x01, 0x06, /* Bitwise anticollision receiving mode */
            0x01, 0x07, /* Valid bit number for last transmit byte */
            0x08};

    public static final byte[] NFC100_TYPEA_SELECT_PROTOCOL = {
            //
            0x01, /* Add CRC */
            0x01, 0x02, /* Check CRC */
            0x01, 0x04, /* Add parity */
            0x01, 0x06, /* Bitwise anticollision receiving mode */
            0x00, 0x07, /* Valid bit number for last transmit byte */
            0x08};

    public static final byte[] ACK_FRAME = {0x00, 0x00, (byte) 0xff, 0x00, (byte) 0xff, 0x00};
    public static final byte[] RESPONSE_HEADER = {0x00, 0x00, (byte) 0xff};
    protected static final byte[] mSendBuffer = new byte[NFC100_COMMAND_BUF_LEN];
    protected static final byte[] mReceiveBuffer = new byte[NFC100_COMMAND_BUF_LEN];
    private static final byte[] mCommandBuffer = new byte[NFC100_COMMAND_BUF_LEN];
    private static final byte[] mResponseBuffer = new byte[NFC100_COMMAND_BUF_LEN];
    private static final byte[] mProtocolBuffer = new byte[64];
    protected static int mReceiveLength;
    private static int mResponsePos;
    private static int mResponseLength;

    // private static final StringBuffer sLogBuffer = new StringBuffer();
    protected final byte[] mPMM = new byte[8];
    // UID_cascade level
    private final byte[] mUID_CLN = new byte[5];
    // Answer To Request acc. to ISO/IEC 14443-4
    private final byte[] mATQA = new byte[2];
    /**
     * Id of the detected card (Felica idm bytes or Mifare Uid bytes)
     */
    protected byte[] mIDM;
    // Select Acknowledge, Type A
    protected byte mSAK;

    /**
     * This function calculates the DCS of a data.
     *
     * @param data     [IN] A data.
     * @param start    [IN] start index.
     * @param data_len [IN] The length of the data.
     * @return The DCS of the data.
     */
    public static byte nfc100_calc_dcs(byte[] data, int start, int data_len) {
        byte sum = 0;
        for (int i = 0; i < data_len; i++) {
            sum += data[start + i];
        }
        byte dcs = (byte) -(sum & 0xff);
        return dcs;
    }

    /**
     * This function converts the status at device to return code.
     *
     * @param status [IN] The status at device.
     * @return ICS_ERROR_SUCCESS No error.<br />
     * ICS_ERROR_DEVICE Error at device.
     */
    public static int nfc100_convert_dev_status(byte status) {
        int rc;
        switch (status) {
            case NFC100_DEV_STATUS_SUCCESS:
                rc = ICS_ERROR_SUCCESS;
                break;
            case NFC100_DEV_STATUS_RFCA_ERROR:
                rc = ICS_ERROR_TIMEOUT;
                break;
            case NFC100_DEV_STATUS_PARAMETER_ERROR:
            case NFC100_DEV_STATUS_PB_ERROR:
            case NFC100_DEV_STATUS_TEMPERATURE_ERROR:
            case NFC100_DEV_STATUS_PWD_ERROR:
            case NFC100_DEV_STATUS_RECEIVE_ERROR:
            case NFC100_DEV_STATUS_COMMANDTYPE_ERROR:
            default:
                logError(status, "status error at device");
                rc = ICS_ERROR_DEVICE;
                break;
        }

        return rc;
    }

    /**
     * This function converts the rf communication status to return code.
     *
     * @param status [IN] The rf communication status.(Bitmap)
     * @return ICS_ERROR_SUCCESS No error.<br />
     * ICS_ERROR_TIMEOUT Time-out.<br />
     * ICS_ERROR_RF_OFF RF was turned off.<br />
     * ICS_ERROR_FRAME_CRC CRC error.<br />
     * ICS_ERROR_DEVICE Error at device.<br />
     */
    public static int nfc100_convert_rf_status(int status) {
        int rc = ICS_ERROR_SUCCESS;
        if (((status & NFC100_RF_STATUS_REC_TIMEOUT_ERROR) != 0) || ((status & NFC100_RF_STATUS_TRA_TIMEOUT_ERROR) != 0)
                || ((status & NFC100_RF_STATUS_RFCA_ERROR) != 0)) {
            rc = ICS_ERROR_TIMEOUT;
            logError(rc, "Time-out at the device.");
        } else if ((status & NFC100_RF_STATUS_RF_OFF) != 0) {
            rc = ICS_ERROR_RF_OFF;
            logError(rc, "RF was turned off.");
        } else if (((status & NFC100_RF_STATUS_PARITY_ERROR) != 0) || ((status & NFC100_RF_STATUS_CRC_ERROR) != 0)) {
            rc = ICS_ERROR_FRAME_CRC;
            logError(rc, "CRC error.");
        } else if (status != NFC100_RF_STATUS_SUCCESS) {
            rc = ICS_ERROR_DEVICE;
            logError(rc, "Error at the device.");
        }
        return rc;
    }

    /**
     * compare the first len bytes of the data b1 and b2
     *
     * @param b1  [IN] data to compare
     * @param b2  [IN] data to compare
     * @param len [IN] data length
     * @return 1, 0, or -1 according as s1 > s2, s1 = s2, or s1 < s2
     */
    public static int utl_memcmp(byte[] b1, byte[] b2, int length) {
        for (int i = 0; i < length; i++) {
            if (b1[i] < b2[i]) {
                return -1;
            }
            if (b1[i] > b2[i]) {
                return 1;
            }
        }
        return 0;
    }

    public static void logDump(String suffix, byte[] buffer, int start, int length) {
        // sLogBuffer.setLength(0);
        // sLogBuffer.append(suffix);
        // sLogBuffer.append(":");
        // for (int i = 0; i < length; i++) {
        // sLogBuffer.append(' ');
        // String hx = Integer.toHexString(0xFF & buffer[start + i]);
        // if (hx.length() == 1) {
        // sLogBuffer.append("0");
        // }
        // sLogBuffer.append(hx);
        // }
        //
        // Util.d(TAG, sLogBuffer.toString());
    }

    public static void logError(int rc, String string) {
        // Util.e(TAG, string + "\nrc=" + rc);
    }

    /**
     * This function cancels the previous command.
     *
     * @return ICS_ERROR_SUCCESS No error.<br />
     * ICS_ERROR_INVALID_PARAM Invalid parameter.<br />
     * ICS_ERROR_TIMEOUT Time-out.<br />
     * ICS_ERROR_IO Other driver error.<br />
     */
    public int nfc100_cancel_command() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            //
        }

		/* send an ACK packet */
        int rc = nfc100_send_ack(NFC100_CANCEL_COMMAND_ACK_TIMEOUT);
        if (rc != ICS_ERROR_SUCCESS) {
            logError(rc, "nfc100_send_ack()");
            // return rc;
        }

        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            //
        }

		/* purge the received packet */
        long time0 = System.currentTimeMillis();
        long rest_timeout = NFC100_CANCEL_COMMAND_PURGE_TIMEOUT;
        do {
            rc = read((int) rest_timeout);
            if ((rc != ICS_ERROR_SUCCESS) && (rc != ICS_ERROR_TIMEOUT)) {
                logError(rc, "icsdrv_raw_func->read()");
                // return rc;
            }

            rest_timeout = NFC100_CANCEL_COMMAND_PURGE_TIMEOUT - (System.currentTimeMillis() - time0);
        } while (rest_timeout > 0);

        return ICS_ERROR_SUCCESS;
    }

    /**
     * This function initializes the device.
     *
     * @param timeout [IN] Time-out period. (ms)
     * @return ICS_ERROR_SUCCESS No error. <br />
     * ICS_ERROR_INVALID_PARAM Invalid parameter. <br />
     * ICS_ERROR_TIMEOUT Time-out. <br />
     * ICS_ERROR_IO Other driver error. <br />
     * ICS_ERROR_INVALID_RESPONSE Invalid response. <br />
     * ICS_ERROR_NOT_SUPPORTED Not supported. <br />
     * ICS_ERROR_DEVICE Error at device.
     */
    public int nfc100_initialize_device(int timeout) {
        /* cancel the previous command */
        int rc = nfc100_cancel_command();
        if (rc != ICS_ERROR_SUCCESS) {
            logError(rc, "nfc100_cacnel_command()");
            return rc;
        }

		/* send a SetCommandType */
        mCommandBuffer[0] = NFC100_COMMAND_CODE;
        mCommandBuffer[1] = NFC100_CMD_SET_COMMAND_TYPE;
        mCommandBuffer[2] = getSupportedCommandType();
        rc = nfc100_execute_command(3, 3, timeout);
        if (rc != ICS_ERROR_SUCCESS) {
            logError(rc, "nfc100_execute_command()");
            if (rc == ICS_ERROR_BUF_OVERFLOW) {
                rc = ICS_ERROR_INVALID_RESPONSE;
                logError(rc, "Buffer overflow.");
            }
            return rc;
        }
        if ((mResponseLength != 3) || (mResponseBuffer[0] != NFC100_RESPONSE_CODE) || (mResponseBuffer[1] != NFC100_RES_SET_COMMAND_TYPE)) {
            rc = ICS_ERROR_INVALID_RESPONSE;
            logError(rc, "Invalid response.");
            return rc;
        }

		/* check the response status */
        rc = nfc100_convert_dev_status(mResponseBuffer[2]);
        if (rc != ICS_ERROR_SUCCESS) {
            logError(rc, "nfc100_convert_dev_status()");
            return rc;
        }

        return ICS_ERROR_SUCCESS;
    }

    /**
     * Support command type for nfc reader
     */
    public abstract byte getSupportedCommandType();

    /**
     * This function opens a port to the device.
     *
     * @return ICS_ERROR_SUCCESS No error. <br />
     * ICS_ERROR_INVALID_PARAM Invalid parameter. <br />
     * ICS_ERROR_BUSY Device busy. <br />
     * ICS_ERROR_PERMISSION Permission denied. <br />
     * ICS_ERROR_IO Other driver error.
     */
    public abstract int open();

    /**
     * This function closes the port.
     *
     * @return ICS_ERROR_SUCCESS No error. <br />
     * ICS_ERROR_INVALID_PARAM Invalid parameter. <br />
     * ICS_ERROR_IO Other driver error.
     */
    public abstract int close();

    /**
     * Polling felica, nfc type A
     */
    public abstract byte[] polling();

    /**
     * This function resets the mode of the driver.
     *
     * @param timeout [IN] Time-out period. (ms)
     * @return ICS_ERROR_SUCCESS No error. <br />
     * ICS_ERROR_INVALID_PARAM Invalid parameter. <br />
     * ICS_ERROR_TIMEOUT Time-out.
     */
    public int nfc100_reset(int timeout) {
        /* reset NFC Port-100 to default protocol setting */
        int rc = nfc100_set_rf_speed(NFC100_DEFAULT_RF_RBT_TX, NFC100_DEFAULT_RF_SPEED_TX, NFC100_DEFAULT_RF_RBT_RX, NFC100_DEFAULT_RF_SPEED_RX, timeout);
        if (rc != ICS_ERROR_SUCCESS) {
            logError(rc, "nfc100_set_rf_speed()");
            return rc;
        }

        return ICS_ERROR_SUCCESS;
    }

    /**
     * This function sends a command to the device and receives response.
     *
     * @param command_len      [IN] The length of the command.
     * @param max_response_len [IN] The size of response buffer.
     * @param timeout          [IN] Time-out period. (ms)
     * @return ICS_ERROR_SUCCESS No error. <br />
     * ICS_ERROR_INVALID_PARAM Invalid parameter. <br />
     * ICS_ERROR_TIMEOUT Time-out. <br />
     * ICS_ERROR_IO Other driver error. <br />
     * ICS_ERROR_INVALID_RESPONSE Invalid response. <br />
     * ICS_ERROR_BUF_OVERFLOW Response buffer overflow.
     */
    public int nfc100_execute_command(int command_len, int max_response_len, int timeout) {
		/* copy the command to the buffer */
        System.arraycopy(mCommandBuffer, 0, mSendBuffer, NFC100_COMMAND_POS, command_len);

		/* execute the command */
        int rc = nfc100_execute_command_internal(command_len, timeout);
        if (rc != ICS_ERROR_SUCCESS) {
            logError(rc, "nfc100_execute_command_internal()");
            return rc;
        }

        if (mResponseLength <= max_response_len) {
            System.arraycopy(mReceiveBuffer, mResponsePos, mResponseBuffer, 0, mResponseLength);
            logDump("nfc100_execute_command_internal()", mResponseBuffer, 0, mResponseLength);
        } else {
            System.arraycopy(mReceiveBuffer, mResponsePos, mResponseBuffer, 0, max_response_len);
            logDump("nfc100_execute_command_internal()", mResponseBuffer, 0, max_response_len);
            rc = ICS_ERROR_BUF_OVERFLOW;
            return rc;
        }

        return ICS_ERROR_SUCCESS;
    }

    /**
     * This function sends a command to RF front-end and receives response.
     *
     * @param command_len      [IN] The length of the command.
     * @param max_response_len [IN] The size of response buffer.
     * @param command_timeout  [IN] Time-out at the device. (ms)
     * @param timeout          [IN] Time-out period. (ms)
     * @return ICS_ERROR_SUCCESS No error. <br />
     * ICS_ERROR_INVALID_PARAM Invalid parameter. <br />
     * ICS_ERROR_TIMEOUT Time-out. <br />
     * ICS_ERROR_IO Other driver error. <br />
     * ICS_ERROR_DEVICE Error at device. <br />
     * ICS_ERROR_INVALID_RESPONSE Invalid response. <br />
     * ICS_ERROR_BUF_OVERFLOW Response buffer overflow.
     */
    public int nfc100_rf_command(int command_len, int max_response_len, int command_timeout, int timeout) {
        int rc;
        int timeout_0_1;
        int nfc100_command_len;

		/* in this case, don't send rf command */
        if (command_timeout == 0) {
            rc = ICS_ERROR_TIMEOUT;
            logError(rc, "Time-out.");
            return rc;
        }

		/* make a command packet for NFC Port-100 */
        if (command_timeout >= (0x10000 / 10)) {
            timeout_0_1 = 0xffff;
        } else {
            timeout_0_1 = (command_timeout * 10);
        }

        mSendBuffer[NFC100_COMMAND_POS + 0] = NFC100_COMMAND_CODE;
        mSendBuffer[NFC100_COMMAND_POS + 1] = NFC100_CMD_IN_COMM_RF;
        mSendBuffer[NFC100_COMMAND_POS + 2] = (byte) ((timeout_0_1 >> 0) & 0xff);
        mSendBuffer[NFC100_COMMAND_POS + 3] = (byte) ((timeout_0_1 >> 8) & 0xff);

        if (command_len > 0) {
            System.arraycopy(mCommandBuffer, 0, mSendBuffer, NFC100_COMMAND_POS + 4, command_len);
            nfc100_command_len = 4 + command_len;
        } else {
            nfc100_command_len = 4;
        }

		/* send the packet to NFC Port-100 */
        rc = nfc100_execute_command_internal(nfc100_command_len, timeout);
        if (rc != ICS_ERROR_SUCCESS) {
            logError(rc, "nfc100_execute_command_internal()");
            return rc;
        }

		/*
		 * when InCommRF command fails, response does not include "RxLastBit"
		 * field
		 */
        if ((mResponsePos != NFC100_RESPONSE_POS) || (mResponseLength < 6) || (mReceiveBuffer[mResponsePos + 0] != NFC100_RESPONSE_CODE)
                || (mReceiveBuffer[mResponsePos + 1] != NFC100_RES_IN_COMM_RF)) {
            rc = ICS_ERROR_INVALID_RESPONSE;
            logError(rc, "Invalid response.");
            return rc;
        }

        int vbit = 0;
        if (mResponseLength > 7) {
            vbit = mReceiveBuffer[mResponsePos + 6];
            if (vbit != 8) {
                rc = ICS_ERROR_INVALID_RESPONSE;
                logError(rc, "Invalid RxLastBit.");
                return rc;
            }

            mResponseLength = mResponseLength - 7;
            if (mResponseLength > max_response_len) {
                System.arraycopy(mReceiveBuffer, mResponsePos + 7, mResponseBuffer, 0, max_response_len);
                logDump("nfc100_execute_command_internal()", mResponseBuffer, 0, max_response_len);

                rc = ICS_ERROR_BUF_OVERFLOW;
                logError(rc, "Buffer overflow.");
                return rc;
            }
            System.arraycopy(mReceiveBuffer, mResponsePos + 7, mResponseBuffer, 0, mResponseLength);
            logDump("nfc100_execute_command_internal()", mResponseBuffer, 0, mResponseLength);

        } else {
            vbit = 0;
            mResponseLength = 0;
        }

        int stat = (((int) mReceiveBuffer[mResponsePos + 2] << 0) | ((int) mReceiveBuffer[mResponsePos + 3] << 8)
                | ((int) mReceiveBuffer[mResponsePos + 4] << 16) | ((int) mReceiveBuffer[mResponsePos + 5] << 24));

		/* check the response status */
        rc = nfc100_convert_rf_status(stat);
        if (rc != ICS_ERROR_SUCCESS) {
            logError(rc, "nfc100_convert_rf_status()");
            return rc;
        }

        return ICS_ERROR_SUCCESS;
    }

    /**
     * This function turns RF on.
     *
     * @param timeout [IN] Time-out period. (ms)
     * @return ICS_ERROR_SUCCESS No error. <br />
     * ICS_ERROR_INVALID_PARAM Invalid parameter. <br />
     * ICS_ERROR_TIMEOUT Time-out. <br />
     * ICS_ERROR_IO Other driver error. <br />
     * ICS_ERROR_INVALID_RESPONSE Received an invalid response. <br />
     * ICS_ERROR_DEVICE Error at device.
     */
    public int nfc100_rf_on(int timeout) {
		/* send a SwitchRF command (RF on) */
        mCommandBuffer[0] = NFC100_COMMAND_CODE;
        mCommandBuffer[1] = NFC100_CMD_SWITCH_RF;
        mCommandBuffer[2] = 0x01; /* RF on */
        int rc = nfc100_execute_command(3, 3, timeout);
        if (rc != ICS_ERROR_SUCCESS) {
            logError(rc, "nfc100_execute_command()");
            if (rc == ICS_ERROR_BUF_OVERFLOW) {
                rc = ICS_ERROR_INVALID_RESPONSE;
                logError(rc, "Buffer overflow.");
            }
            return rc;
        }
        if ((mResponseLength != 3) || (mResponseBuffer[0] != NFC100_RESPONSE_CODE) || (mResponseBuffer[1] != NFC100_RES_SWITCH_RF)) {
            rc = ICS_ERROR_INVALID_RESPONSE;
            logError(rc, "Invalid response.");
            return rc;
        }

		/* check the response status */
        rc = nfc100_convert_dev_status(mResponseBuffer[2]);
        if (rc != ICS_ERROR_SUCCESS) {
            logError(rc, "nfc100_convert_dev_status()");
            return rc;
        }

        return ICS_ERROR_SUCCESS;
    }

    /**
     * This function turns RF off.
     *
     * @param timeout [IN] Time-out period. (ms)
     * @return ICS_ERROR_SUCCESS No error. <br />
     * ICS_ERROR_INVALID_PARAM Invalid parameter. <br />
     * ICS_ERROR_TIMEOUT Time-out. <br />
     * ICS_ERROR_IO Other driver error. <br />
     * ICS_ERROR_INVALID_RESPONSE Received an invalid response. <br />
     * ICS_ERROR_DEVICE Error at device.
     */
    public int nfc100_rf_off(int timeout) {
		/* send a SwitchRF command (RF off) */
        mCommandBuffer[0] = NFC100_COMMAND_CODE;
        mCommandBuffer[1] = NFC100_CMD_SWITCH_RF;
        mCommandBuffer[2] = 0x00; /* RF off */
        int rc = nfc100_execute_command(3, 3, timeout);
        if (rc != ICS_ERROR_SUCCESS) {
            logError(rc, "nfc100_execute_command()");
            if (rc == ICS_ERROR_BUF_OVERFLOW) {
                rc = ICS_ERROR_INVALID_RESPONSE;
                logError(rc, "Buffer overflow.");
            }
            return rc;
        }
        if ((mResponseLength != 3) || (mResponseBuffer[0] != NFC100_RESPONSE_CODE) || (mResponseBuffer[1] != NFC100_RES_SWITCH_RF)) {
            rc = ICS_ERROR_INVALID_RESPONSE;
            logError(rc, "Invalid response.");
            return rc;
        }

		/* check the response status */
        rc = nfc100_convert_dev_status(mResponseBuffer[2]);
        if (rc != ICS_ERROR_SUCCESS) {
            logError(rc, "nfc100_convert_dev_status()");
            return rc;
        }

        return ICS_ERROR_SUCCESS;
    }

	/* FELICA -- */

    /**
     * This function sends an ACK to the device.
     *
     * @param timeout [IN] Time-out period. (ms)
     * @return ICS_ERROR_SUCCESS No error. <br />
     * ICS_ERROR_INVALID_PARAM Invalid parameter. <br />
     * ICS_ERROR_TIMEOUT Time-out. <br />
     * ICS_ERROR_IO Other driver error.
     */
    public int nfc100_send_ack(int timeout) {
		/* send command */
        System.arraycopy(ACK_FRAME, 0, mSendBuffer, 0, ACK_FRAME.length);
        int rc = write(ACK_FRAME.length, timeout);

        if (rc != ICS_ERROR_SUCCESS) {
            logError(rc, "icsdrv_raw_func->write()");
            return rc;
        }

        return ICS_ERROR_SUCCESS;
    }

    /**
     * This function sets the RF speed of the device.
     *
     * @param tx_rbt   [IN] The TX RBT number to set.
     * @param tx_speed [IN] The TX RF speed to set.
     * @param rx_rbt   [IN] The RX RBT number to set.
     * @param rx_speed [IN] The RX RF speed to set.
     * @param timeout  [IN] Time-out period. (ms)
     * @return ICS_ERROR_SUCCESS No error. <br />
     * ICS_ERROR_INVALID_PARAM Invalid parameter. <br />
     * ICS_ERROR_TIMEOUT Time-out. <br />
     * ICS_ERROR_IO Other driver error. <br />
     * ICS_ERROR_INVALID_RESPONSE Invalid response. <br />
     * ICS_ERROR_DEVICE Error at device.
     */
    public int nfc100_set_rf_speed(byte tx_rbt, byte tx_speed, byte rx_rbt, byte rx_speed, int timeout) {
		/* send a InSetRF command */
        mCommandBuffer[0] = NFC100_COMMAND_CODE;
        mCommandBuffer[1] = NFC100_CMD_IN_SET_RF;
        mCommandBuffer[2] = tx_rbt;
        mCommandBuffer[3] = tx_speed;
        mCommandBuffer[4] = rx_rbt;
        mCommandBuffer[5] = rx_speed;
        int command_len = 6;
        int rc = nfc100_execute_command(command_len, NFC100_MAX_RESPONSE_LEN, timeout);
        if (rc != ICS_ERROR_SUCCESS) {
            logError(rc, "nfc100_execute_command()");
            if (rc == ICS_ERROR_BUF_OVERFLOW) {
                rc = ICS_ERROR_INVALID_RESPONSE;
                logError(rc, "Buffer overflow.");
            }
            return rc;
        }
        if ((mResponseLength != 3) || (mResponseBuffer[0] != NFC100_RESPONSE_CODE) || (mResponseBuffer[1] != NFC100_RES_IN_SET_RF)) {
            rc = ICS_ERROR_INVALID_RESPONSE;
            logError(rc, "Invalid response.");
            return rc;
        }

		/* check the response status */
        rc = nfc100_convert_dev_status(mResponseBuffer[2]);
        if (rc != ICS_ERROR_SUCCESS) {
            logError(rc, "nfc100_convert_dev_status()");
            return rc;
        }

        return ICS_ERROR_SUCCESS;
    }

    /**
     * This function sets the rf protocol setting data of the device.
     *
     * @param setting_len [IN] The length of the setting data.
     * @param timeout     [IN] Time-out period. (ms)
     * @return ICS_ERROR_SUCCESS No error. <br />
     * ICS_ERROR_INVALID_PARAM Invalid parameter. <br />
     * ICS_ERROR_TIMEOUT Time-out. <br />
     * ICS_ERROR_IO Other driver error. <br />
     * ICS_ERROR_INVALID_RESPONSE Invalid response. <br />
     * ICS_ERROR_DEVICE Error at device.
     */
    public int nfc100_set_protocol(int setting_len, int timeout) {
		/* send a InSetProtocol command */
        mCommandBuffer[0] = NFC100_COMMAND_CODE;
        mCommandBuffer[1] = NFC100_CMD_IN_SET_PROTOCOL;
        System.arraycopy(mProtocolBuffer, 0, mCommandBuffer, 2, setting_len);
        int command_len = 2 + setting_len;
        int rc = nfc100_execute_command(command_len, 3, timeout);
        if (rc != ICS_ERROR_SUCCESS) {
            logError(rc, "nfc100_execute_command()");
            if (rc == ICS_ERROR_BUF_OVERFLOW) {
                rc = ICS_ERROR_INVALID_RESPONSE;
                logError(rc, "Buffer overflow.");
            }
            return rc;
        }
        if ((mResponseLength != 3) || (mResponseBuffer[0] != NFC100_RESPONSE_CODE) || (mResponseBuffer[1] != NFC100_RES_IN_SET_PROTOCOL)) {
            rc = ICS_ERROR_INVALID_RESPONSE;
            logError(rc, "Invalid response.");
            return rc;
        }

		/* check the response status */
        rc = nfc100_convert_dev_status(mResponseBuffer[2]);
        if (rc != ICS_ERROR_SUCCESS) {
            logError(rc, "nfc100_convert_dev_status()");
            return rc;
        }

        return ICS_ERROR_SUCCESS;
    }

	/* -- FELICA */

	/* NFC Type-A -- */

    private int nfc100_execute_command_internal(int command_len, int timeout) {
        int rc;
        byte dcs;
        int preamble_len = 8; /* default */

		/* send command (extended frame) */
        mSendBuffer[NFC100_COMMAND_POS - 8] = 0x00;
        mSendBuffer[NFC100_COMMAND_POS - 7] = 0x00;
        mSendBuffer[NFC100_COMMAND_POS - 6] = (byte) 0xff;
        mSendBuffer[NFC100_COMMAND_POS - 5] = (byte) 0xff;
        mSendBuffer[NFC100_COMMAND_POS - 4] = (byte) 0xff;
        mSendBuffer[NFC100_COMMAND_POS - 3] = (byte) ((command_len >> 0) & 0xff);
        mSendBuffer[NFC100_COMMAND_POS - 2] = (byte) ((command_len >> 8) & 0xff);
        mSendBuffer[NFC100_COMMAND_POS - 1] = (byte) -(mSendBuffer[NFC100_COMMAND_POS - 3] + mSendBuffer[NFC100_COMMAND_POS - 2]);

        dcs = nfc100_calc_dcs(mSendBuffer, NFC100_COMMAND_POS, command_len);
        mSendBuffer[NFC100_COMMAND_POS + command_len] = dcs;
        mSendBuffer[NFC100_COMMAND_POS + command_len + 1] = 0x00;

        rc = write(preamble_len + command_len + 2, timeout);
        if (rc != ICS_ERROR_SUCCESS) {
            logError(rc, "icsdrv_raw_write()");
            return rc;
        }

        for (int i = 0; i < 3; i++) {
			/* receive ACK, response header */
            rc = read(timeout);
            if (rc != ICS_ERROR_SUCCESS) {
                logError(rc, "icsdrv_raw_read() - ack or response header at " + i);
                return rc;
            }

			/* check ack */
            if (utl_memcmp(mReceiveBuffer, ACK_FRAME, ACK_FRAME.length) != 0) {
                break;
                // /* receive extend frame */
                // rc = read(timeout);
                // if (rc != ICS_ERROR_SUCCESS) {
                // ICSLOG_ERR_STR(rc, "icsdrv_raw_read() - response header");
                // return rc;
                // }
            }
        }

		/* check header */
        if (utl_memcmp(mReceiveBuffer, RESPONSE_HEADER, RESPONSE_HEADER.length) != 0) {
            rc = ICS_ERROR_INVALID_RESPONSE;
            logError(rc, "Invalid response header.");
            return rc;
        }
        if ((mReceiveBuffer[3] == (byte) 0xff) && (mReceiveBuffer[4] == (byte) 0xff)) {
			/* extended frame */
            if (((mReceiveBuffer[5] + mReceiveBuffer[6] + mReceiveBuffer[7]) & 0xff) != 0) {
                rc = ICS_ERROR_INVALID_RESPONSE;
                logError(rc, "Invalid response - lcs");
            }
            preamble_len = 8;
            mResponsePos = 8;
            mResponseLength = (((int) mReceiveBuffer[5] << 0) | ((int) mReceiveBuffer[6] << 8));

        } else {
			/* normal frame */
            if (((mReceiveBuffer[3] + mReceiveBuffer[4]) & 0xff) != 0) {
                rc = ICS_ERROR_INVALID_RESPONSE;
                logError(rc, "Invalid response header.");
                return rc;
            }
            preamble_len = 5;
            mResponsePos = 5;
            mResponseLength = mReceiveBuffer[3];
        }

        if (mResponseLength > NFC100_MAX_RESPONSE_LEN) {
            rc = ICS_ERROR_INVALID_RESPONSE;
            logError(rc, "Too long response length.");
            return rc;
        }

		/* check response */
        dcs = nfc100_calc_dcs(mReceiveBuffer, preamble_len, mResponseLength);
        if ((mReceiveBuffer[preamble_len + mResponseLength + 0] != dcs) || (mReceiveBuffer[preamble_len + mResponseLength + 1] != 0x00)) {
            rc = ICS_ERROR_INVALID_RESPONSE;
            logError(rc, "Invalid response body.");
            return rc;
        }

        return ICS_ERROR_SUCCESS;
    }

    /**
     * This function writes data to the device.
     *
     * @param data_len [IN] The length of the data.
     * @param timeout  [IN] Time-out period. (ms)
     * @return ICS_ERROR_SUCCESS No error. <br />
     * ICS_ERROR_INVALID_PARAM Invalid parameter. <br />
     * ICS_ERROR_TIMEOUT Time-out. <br />
     * ICS_ERROR_IO Other driver error.
     */
    public abstract int write(int length, int timeout);

    /**
     * This function reads data from the device.
     *
     * @param timeout [IN] Time-out period. (ms)
     * @return ICS_ERROR_SUCCESS No error. <br />
     * ICS_ERROR_INVALID_PARAM Invalid parameter. <br />
     * ICS_ERROR_TIMEOUT Time-out. <br />
     * ICS_ERROR_IO Other driver error. <br />
     * ICS_ERROR_BUF_OVERFLOW Response buffer overflow.
     */
    public abstract int read(int timeout);

    /**
     * This function detects a remote card using Polling command.
     *
     * @param timeout [IN] Time-out period.
     * @return ICS_ERROR_SUCCESS No error. <br />
     * ICS_ERROR_INVALID_PARAM Invalid argument. <br />
     * ICS_ERROR_TIMEOUT Not detected. <br />
     * ICS_ERROR_IO Other driver error. <br />
     * ICS_ERROR_DEVICE Error at device. <br />
     * ICS_ERROR_FRAME_CRC CRC error. <br />
     * ICS_ERROR_INVALID_RESPONSE Received an invalid response packet.
     */
    protected int nfc100_felica_polling(int timeout) {
		/* set up NFC Port-100 for initiator mode */
        int rc = nfc100_felica_setup_initiator(1);
        if (rc != ICS_ERROR_SUCCESS) {
            logError(rc, "felica_cc_stub_nfc100_setup_initiator()");
            return rc;
        }

		/* make a Polling command for NFC Port-100 */
        mCommandBuffer[0] = 6;
        mCommandBuffer[1] = 0x00;
        System.arraycopy(FELICA_POLLING_PARAM, 0, mCommandBuffer, 2, FELICA_POLLING_PARAM.length);

		/* send the Polling command to NFC Port-100 */
        rc = nfc100_rf_command(6, 7 + 290, 5, timeout);
        if (rc != ICS_ERROR_SUCCESS) {
            logError(rc, "nfc100_felica_command()");
            if (rc == ICS_ERROR_BUF_OVERFLOW) {
                rc = ICS_ERROR_INVALID_RESPONSE;
                logError(rc, "Buffer overflow.");
            }
            return rc;
        }
        if (mResponseLength < 18) {
            rc = ICS_ERROR_INVALID_RESPONSE;
            logError(rc, "Invalid response.");
            return rc;
        }

        if (((mResponseBuffer[0] != 18) && (mResponseBuffer[0] != 20)) || (mResponseBuffer[1] != 0x01)) {
            rc = ICS_ERROR_INVALID_RESPONSE;
            logError(rc, "Invalid response.");
            return rc;
        }

		/* parse the response from multi cards */
        System.arraycopy(mResponseBuffer, 2, mIDM, 0, 8);
        System.arraycopy(mResponseBuffer, 10, mPMM, 0, 8);

        return ICS_ERROR_SUCCESS;
    }

    /**
     * This function sets up NFC Port-100 for initiator mode.
     *
     * @param max_num_of_cards [IN] The maximum number of cards to detect.
     * @return ICS_ERROR_SUCCESS No error. <br />
     * ICS_ERROR_TIMEOUT Time-out. <br />
     * ICS_ERROR_IO Other driver error. <br />
     * ICS_ERROR_INVALID_RESPONSE Received an invalid response packet.
     */
    private int nfc100_felica_setup_initiator(int max_num_of_cards) {
		/* make and send an InSetRF command for NFC Port-100 */
        int rc = nfc100_set_rf_speed(FELICA_CC_STUB_NFC100_RBT, FELICA_CC_STUB_NFC100_SPEED, FELICA_CC_STUB_NFC100_RBT, FELICA_CC_STUB_NFC100_SPEED,
                FELICA_CC_STUB_NFC100_IN_SET_RF_TIMEOUT);
        if (rc != ICS_ERROR_SUCCESS) {
            logError(rc, "nfc100_set_rf_speed()");
            return rc;
        }

		/* send an InSetProtocol command for NFC Port-100 */
        int setting_len = NFC100_FELICA_DEFAULT_PROTOCOL.length;
        System.arraycopy(NFC100_FELICA_DEFAULT_PROTOCOL, 0, mProtocolBuffer, 0, setting_len);
        if (max_num_of_cards > 1) {
            mProtocolBuffer[7] = 0x01; /* Multi card = on */
        } else {
            mProtocolBuffer[7] = 0x00; /* Multi card = off */
        }

        rc = nfc100_set_protocol(setting_len, FELICA_CC_STUB_NFC100_IN_SET_PROTOCOL_TIMEOUT);

        if (rc != ICS_ERROR_SUCCESS) {
            logError(rc, "nfc100_set_protocol()");
            return rc;
        }

        return ICS_ERROR_SUCCESS;
    }

	/* -- NFC Type-A */

    /**
     * This function pushes to felica device using Push command.
     *
     * @param command [IN] Push command
     * @return ICS_ERROR_SUCCESS No error. <br />
     * ICS_ERROR_INVALID_PARAM Invalid argument. <br />
     * ICS_ERROR_TIMEOUT Not detected. <br />
     * ICS_ERROR_IO Other driver error. <br />
     * ICS_ERROR_DEVICE Error at device. <br />
     * ICS_ERROR_FRAME_CRC CRC error. <br />
     * ICS_ERROR_INVALID_RESPONSE Received an invalid response packet.
     */
    public int nfc100_felica_push(byte[] command) {
        System.arraycopy(command, 0, mCommandBuffer, 0, command.length);

		/* send the Polling command to NFC Port-100 */
        int rc = nfc100_rf_command(command.length, NFC100_MAX_RESPONSE_LEN, NFC100_DEFAULT_TIMEOUT, NFC100_DEFAULT_TIMEOUT);
        return rc;
    }

    /**
     * This function executes anticollision.
     *
     * @param timeout [IN] Time-out period.
     * @return ICS_ERROR_SUCCESS No error. <br />
     * ICS_ERROR_INVALID_PARAM Invalid parameter. <br />
     * ICS_ERROR_TIMEOUT Time-out. <br />
     * ICS_ERROR_IO Other driver error. <br />
     * ICS_ERROR_INVALID_RESPONSE Invalid response. <br />
     * ICS_ERROR_DEVICE Error at device.
     */
    private int nfc100_typea_anticollision(int timeout) {
        int setting_len = NFC100_TYPEA_ANTICOLLISION_PROTOCOL.length;
        System.arraycopy(NFC100_TYPEA_ANTICOLLISION_PROTOCOL, 0, mProtocolBuffer, 0, setting_len);
		/* specify the protocol settings */
        int rc = nfc100_set_protocol(setting_len, timeout);
        if (rc != ICS_ERROR_SUCCESS) {
            logError(rc, "nfc100_typea_set_protocol()");
            return rc;
        }

        mCommandBuffer[0] = (byte) 0x93;
        mCommandBuffer[1] = 0x20;

		/* send a SELECT command and receive a SELECT response */
        rc = nfc100_rf_command(2, 7, NFC100_TYPEA_FDT_LISTEN_MAX, timeout);
        if (rc != ICS_ERROR_SUCCESS) {
            logError(rc, "nfc100_rf_command()");
            if (rc == ICS_ERROR_BUF_OVERFLOW) {
                rc = ICS_ERROR_INVALID_RESPONSE;
            }
            return rc;
        }
        if (mResponseLength != 5) {
            rc = ICS_ERROR_INVALID_RESPONSE;
            logError(rc, "Invalid response");
            return rc;
        }

        // Set IDM
        for (int i = 0; i < mIDM.length; i++) {
            mIDM[i] = 0x00;
        }
        System.arraycopy(mResponseBuffer, 0, mIDM, 0, 4);
        logDump("IDM", mIDM, 0, 4);

        // Set UI_CLN
        System.arraycopy(mResponseBuffer, 0, mUID_CLN, 0, 5);
        logDump("UID_CLN", mUID_CLN, 0, 5);

		/* send a SELECT command */
        rc = nfc100_typea_select(timeout);
        if (rc != ICS_ERROR_SUCCESS) {
            logError(rc, "nfc100_typea_select_internal()");
            return rc;
        }

        return ICS_ERROR_SUCCESS;
    }

    /**
     * This function detects a Type A card.
     *
     * @param timeout [IN] Time-out period. (ms)
     * @return ICS_ERROR_SUCCESS No error. <br />
     * ICS_ERROR_INVALID_PARAM Invalid parameter. <br />
     * ICS_ERROR_TIMEOUT Time-out. <br />
     * ICS_ERROR_IO Other driver error. <br />
     * ICS_ERROR_DEVICE Error at device. <br />
     * ICS_ERROR_INVALID_RESPONSE Invalid response. <br />
     * ICS_ERROR_INVALID_DATA Invalid data.
     */
    protected int nfc100_typea_polling(int timeout) {
		/* set up the device for Type A initiator mode */
        int rc = nfc100_typea_setup_initiator();
        if (rc != ICS_ERROR_SUCCESS) {
            logError(rc, "nfc100_typea_setup_initiator()");
            return rc;
        }

        rc = nfc100_typea_send_reqa_wupa(timeout);
        if (rc != ICS_ERROR_SUCCESS) {
            logError(rc, "nfc100_typea_send_reqa_wupa()");
            return rc;
        }

		/* ISO/IEC 14443 card */
        rc = nfc100_typea_anticollision(timeout);
        if (rc != ICS_ERROR_SUCCESS) {
            logError(rc, "nfc100_typea_anticollision()");
            return rc;
        }

        return ICS_ERROR_SUCCESS;
    }

    /**
     * This function selects the card.
     *
     * @param timeout [IN] Time-out period. (ms)
     * @return ICS_ERROR_SUCCESS No error. <br />
     * ICS_ERROR_INVALID_PARAM Invalid parameter. <br />
     * ICS_ERROR_TIMEOUT Time-out. <br />
     * ICS_ERROR_IO Other driver error. <br />
     * ICS_ERROR_DEVICE Error at device. <br />
     * ICS_ERROR_INVALID_RESPONSE Invalid response. <br />
     * ICS_ERROR_INVALID_DATA Invalid data.
     */
    private int nfc100_typea_select(int timeout) {
        int setting_len = NFC100_TYPEA_SELECT_PROTOCOL.length;
        System.arraycopy(NFC100_TYPEA_SELECT_PROTOCOL, 0, mProtocolBuffer, 0, setting_len);

		/* specify the protocol settings */
        int rc = nfc100_set_protocol(setting_len, timeout);
        if (rc != ICS_ERROR_SUCCESS) {
            logError(rc, "nfc100_typea_set_protocol()");
            return rc;
        }

		/* make a select command */
        mCommandBuffer[0] = (byte) 0x93;
        mCommandBuffer[1] = 0x70;
        System.arraycopy(mUID_CLN, 0, mCommandBuffer, 2, 5);

		/* send the SELECT command */
        rc = nfc100_rf_command(7, 1, NFC100_TYPEA_FDT_LISTEN_MAX, timeout);
        if (rc != ICS_ERROR_SUCCESS) {
            logError(rc, "nfc100_rf_command()");
            if (rc == ICS_ERROR_BUF_OVERFLOW) {
                rc = ICS_ERROR_INVALID_RESPONSE;
            }
            return rc;
        }
        if (mResponseLength != 1) {
            rc = ICS_ERROR_INVALID_RESPONSE;
            logError(rc, "Invalid response");
            return rc;
        }
        mSAK = mResponseBuffer[0];
        // Log.d(TAG, "SAK=" + mSAK);

        return ICS_ERROR_SUCCESS;
    }

    /**
     * This function sends REQA/WUPA command.
     *
     * @param timeout [IN] Time-out period.
     * @return ICS_ERROR_SUCCESS No error. <br />
     * ICS_ERROR_INVALID_PARAM Invalid parameter. <br />
     * ICS_ERROR_TIMEOUT Time-out. <br />
     * ICS_ERROR_IO Other driver error. <br />
     * ICS_ERROR_DEVICE Error at device. <br />
     * ICS_ERROR_INVALID_RESPONSE Invalid response.
     */
    private int nfc100_typea_send_reqa_wupa(int timeout) {
        int setting_len = NFC100_TYPEA_ATQA_PROTOCOL.length;
        System.arraycopy(NFC100_TYPEA_ATQA_PROTOCOL, 0, mProtocolBuffer, 0, setting_len);

		/* specify the protocol settings */
        int rc = nfc100_set_protocol(setting_len, NFC100_TYPEA_IN_SET_PROTOCOL_TIMEOUT);

        if (rc != ICS_ERROR_SUCCESS) {
            logError(rc, "nfc100_set_protocol()");
            return rc;
        }

        mCommandBuffer[0] = NFC100_TYPEA_CMD_REQA;

		/* send a REQA/WUPA command and receive a response (ATQA) */
        rc = nfc100_rf_command(1, 2, NFC100_TYPEA_FDT_LISTEN_MAX, timeout);
        if (rc != ICS_ERROR_SUCCESS) {
            logError(rc, "nfc100_rf_command()");
            if (rc == ICS_ERROR_BUF_OVERFLOW) {
                rc = ICS_ERROR_INVALID_RESPONSE;
            }
            return rc;
        }
        if (mResponseLength != 2) {
            rc = ICS_ERROR_INVALID_RESPONSE;
            logError(rc, "Invalid response");
            return rc;
        }

        mATQA[0] = mResponseBuffer[1];
        mATQA[1] = mResponseBuffer[0];
        logDump("ATQA", mATQA, 0, 2);

        return ICS_ERROR_SUCCESS;
    }

    /**
     * This function sets up NFC Port-100 for Type A initiator mode.
     *
     * @return ICS_ERROR_SUCCESS No error. <br />
     * ICS_ERROR_INVALID_PARAM Invalid parameter. <br />
     * ICS_ERROR_TIMEOUT Time-out. <br />
     * ICS_ERROR_IO Other driver error. <br />
     * ICS_ERROR_INVALID_RESPONSE Invalid response. <br />
     * ICS_ERROR_DEVICE Error at device.
     */
    private int nfc100_typea_setup_initiator() {
		/* make and send an InSetRF command for NFC Port-100 */
        int rc = nfc100_set_rf_speed(NFC100_RBT_INITIATOR_ISO14443A_106K, NFC100_RF_INITIATOR_ISO14443A_106K, NFC100_RBT_INITIATOR_ISO14443A_106K,
                NFC100_RF_INITIATOR_ISO14443A_106K, NFC100_TYPEA_IN_SET_RF_TIMEOUT);
        if (rc != ICS_ERROR_SUCCESS) {
            logError(rc, "nfc100_set_rf_speed()");
            return rc;
        }

        return ICS_ERROR_SUCCESS;
    }
}
