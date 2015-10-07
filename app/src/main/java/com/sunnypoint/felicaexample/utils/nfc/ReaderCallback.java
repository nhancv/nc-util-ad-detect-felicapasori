package com.sunnypoint.felicaexample.utils.nfc;

public interface ReaderCallback {

    void onReaderCreated(BaseReader reader, boolean success);

    void onTagDiscovered(BaseReader reader, boolean success);

    void onReaderDetached(BaseReader reader);
}
