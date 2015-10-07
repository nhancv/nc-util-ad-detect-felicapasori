package com.sunnypoint.felicaexample.utils;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringWriter;

/**
 * Created by NhanCao on 07-Oct-15.
 */
public class StreamUtils {

    public static final int IO_BUFFER_SIZE = 8 * 1024;

    private static final int END_OF_STREAM = -1;

    public static final String readFully(final InputStream pInputStream)
            throws IOException {
        final StringWriter writer = new StringWriter();
        final char[] buf = new char[StreamUtils.IO_BUFFER_SIZE];
        try {
            final Reader reader = new BufferedReader(new InputStreamReader(
                    pInputStream, "UTF-8"));
            int read;
            while ((read = reader.read(buf)) != StreamUtils.END_OF_STREAM) {
                writer.write(buf, 0, read);
            }
        } finally {
            StreamUtils.close(pInputStream);
        }
        return writer.toString();
    }

    public static final void copy(final InputStream pInputStream,
                                  final OutputStream pOutputStream) throws IOException {
        StreamUtils
                .copy(pInputStream, pOutputStream, StreamUtils.END_OF_STREAM);
    }

    /**
     * Copy the content of the input stream into the output stream, using a
     * temporary byte array buffer whose size is defined by
     * {@link #IO_BUFFER_SIZE}.
     *
     * @param pInputStream  The input stream to copy from.
     * @param pOutputStream The output stream to copy to.
     * @param pByteLimit    not more than so much bytes to read, or unlimited if
     *                      {@link StreamUtils#END_OF_STREAM}.
     * @throws IOException If any error occurs during the copy.
     */
    public static final void copy(final InputStream pInputStream,
                                  final OutputStream pOutputStream, final int pByteLimit)
            throws IOException {
        if (pByteLimit == StreamUtils.END_OF_STREAM) {
            final byte[] buf = new byte[StreamUtils.IO_BUFFER_SIZE];
            int read;
            while ((read = pInputStream.read(buf)) != StreamUtils.END_OF_STREAM) {
                pOutputStream.write(buf, 0, read);
            }
        } else {
            final byte[] buf = new byte[StreamUtils.IO_BUFFER_SIZE];
            final int bufferReadLimit = Math.min(pByteLimit,
                    StreamUtils.IO_BUFFER_SIZE);
            long pBytesLeftToRead = pByteLimit;

            int read;
            while ((read = pInputStream.read(buf, 0, bufferReadLimit)) != StreamUtils.END_OF_STREAM) {
                if (pBytesLeftToRead > read) {
                    pOutputStream.write(buf, 0, read);
                    pBytesLeftToRead -= read;
                } else {
                    pOutputStream.write(buf, 0, (int) pBytesLeftToRead);
                    break;
                }
            }
        }
        pOutputStream.flush();
    }

    public static final boolean copyAndClose(final InputStream pInputStream,
                                             final OutputStream pOutputStream) {
        try {
            StreamUtils.copy(pInputStream, pOutputStream,
                    StreamUtils.END_OF_STREAM);
            return true;
        } catch (final IOException e) {
            return false;
        } finally {
            StreamUtils.close(pInputStream);
            StreamUtils.close(pOutputStream);
        }
    }

    /**
     * Closes the specified stream.
     *
     * @param pCloseable The stream to close.
     */
    public static final void close(final Closeable pCloseable) {
        if (pCloseable != null) {
            try {
                pCloseable.close();
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }
    }
}