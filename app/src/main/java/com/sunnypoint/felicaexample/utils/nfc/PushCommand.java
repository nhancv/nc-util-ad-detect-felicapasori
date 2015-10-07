package com.sunnypoint.felicaexample.utils.nfc;

import com.felicanetworks.mfc.PushSegment;
import com.felicanetworks.mfc.PushStartBrowserSegment;

import net.kazzz.felica.FeliCaException;
import net.kazzz.felica.lib.FeliCaLib;
import net.kazzz.felica.lib.FeliCaLib.CommandPacket;
import net.kazzz.felica.lib.FeliCaLib.IDm;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;


public abstract class PushCommand extends CommandPacket {

    public static final byte PUSH = (byte) 0xb0;
    protected static final byte[] EMPTY_BYTES = new byte[0];

    static {
        FeliCaLib.commandMap.put(PUSH, "Push");
    }

    protected PushCommand(IDm idm, byte[][] segments) throws FeliCaException {
        super(PUSH, notNull(idm, "idm"), packContent(packSegments(segments)));
    }

    public static PushCommand create(IDm idm, PushSegment segment) throws FeliCaException {
        if (idm == null) {
            throw new IllegalArgumentException("'idm' must not be null.");
        }
        if (segment == null) {
            throw new IllegalArgumentException("'segment' must not be null.");
        }

        if (segment instanceof PushStartBrowserSegment) {
            return new PushStartBrowserCommand(idm, (PushStartBrowserSegment) segment);
        }

        throw new FeliCaException("unsupported push segment: " + segment.getClass().getSimpleName());
    }

    private static <T> T notNull(T target, String argName) {
        if (target == null) {
            throw new IllegalArgumentException("'" + argName + "' must not be null.");
        }
        return target;
    }

    // From buffer [10] Data Size
    private static byte[] packContent(byte[] segments) {
        byte[] buffer = new byte[segments.length + 1];
        buffer[0] = (byte) segments.length;
        System.arraycopy(segments, 0, buffer, 1, segments.length);
        return buffer;
    }

    // From Buffer [11] Num of Data block
    private static byte[] packSegments(byte[]... segments) {
        int bytes = 3; // (1byte) + (2bytes)
        for (int i = 0; i < segments.length; ++i) {
            bytes += segments[i].length;
        }

        final ByteBuffer buffer = ByteBuffer.allocate(bytes);

        // number of segments
        buffer.put((byte) segments.length);

        // data (segments)
        for (int i = 0; i < segments.length; ++i) {
            buffer.put(segments[i]);
        }

        // check sum
        int sum = segments.length;
        for (int i = 0; i < segments.length; ++i) {
            byte[] e = segments[i];
            for (int j = 0; j < e.length; ++j) {
                sum += e[j];
            }
        }
        final int checksum = -sum & 0xffff;
        putShortAsBigEndian(checksum, buffer);

        return buffer.array();
    }

    protected static byte[] getBytes(String str, Charset charset) {
        if (str == null) {
            return EMPTY_BYTES;
        }
        return str.getBytes(charset);
    }

    protected static void putShortAsLittleEndian(int value, ByteBuffer buffer) {
        buffer.put((byte) ((value >> 0) & 0xff));
        buffer.put((byte) ((value >> 8) & 0xff));
    }


    protected static void putShortAsBigEndian(int value, ByteBuffer buffer) {
        buffer.put((byte) ((value >> 8) & 0xff));
        buffer.put((byte) ((value >> 0) & 0xff));
    }
}
