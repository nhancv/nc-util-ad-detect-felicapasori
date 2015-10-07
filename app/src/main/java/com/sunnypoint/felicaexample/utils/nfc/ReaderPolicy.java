package com.sunnypoint.felicaexample.utils.nfc;

public interface ReaderPolicy {

    void createReader();

    void destroyReader();

    boolean isSupported(Object device);

    boolean isReaderAvailable();

    byte[] openReader(String portName);

    void closeReader(String log);

    void startPolling(String log);

    void stopPolling(String log);

    void pushUrl(final Object tag);

    String getName();

    int getPolledType();

    byte[] getIDM();
}
