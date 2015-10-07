package com.sunnypoint.felicaexample.utils.nfc;

import com.felicanetworks.mfc.PushStartBrowserSegment;

import net.kazzz.felica.FeliCaException;
import net.kazzz.felica.lib.FeliCaLib.IDm;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public final class PushStartBrowserCommand extends PushCommand {


    private static final byte TYPE = (byte) 2;
    private static final Charset URL_CHARSET = Charset.forName("iso8859-1");
    private static final Charset STARTUP_PARAM_CHARSET = Charset.forName("iso8859-1");

    public PushStartBrowserCommand(IDm idm, PushStartBrowserSegment segment) throws FeliCaException {
        this(idm, segment.getURL(), segment.getBrowserStartupParam());
    }

    public PushStartBrowserCommand(IDm idm, String url,
                                   String browserStartupParam) throws FeliCaException {
        super(idm, buildPushStartBrowserSegment(url, browserStartupParam));
    }

    // From Byte Broswer Target
    private static byte[][] buildPushStartBrowserSegment(String url,
                                                         String browserStartupParam) {
        final byte[] urlByte = (url == null) ? PushCommand.EMPTY_BYTES : url.getBytes(URL_CHARSET);
        final byte[] browserStartupParamByte = (browserStartupParam == null) ? PushCommand.EMPTY_BYTES
                : browserStartupParam.getBytes(STARTUP_PARAM_CHARSET);

        final int capacity = urlByte.length + browserStartupParamByte.length //
                + 5;// type(1byte) + paramSize(2bytes) + urlLength(2bytes)

        final ByteBuffer buffer = ByteBuffer.allocate(capacity);

        buffer.put(TYPE);
        int paramSize = urlByte.length + browserStartupParamByte.length + 2; // urlLength(2bytes)
        putShortAsLittleEndian(paramSize, buffer);

        putShortAsLittleEndian(urlByte.length, buffer);
        // URL
        buffer.put(urlByte);
        buffer.put(browserStartupParamByte);

        return new byte[][]{
                buffer.array()
        };
    }
}
