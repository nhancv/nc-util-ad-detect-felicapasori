package com.sunnypoint.felicaexample.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.sunnypoint.felicaexample.BR;

/**
 * Created by NhanCao on 08-Oct-15.
 */
public class LogInfo extends BaseObservable {
    private String msg;

    private static LogInfo instance=new LogInfo("Greate");

    public static LogInfo getInstance(){
        return instance;
    }

    public LogInfo(String msg) {
        this.msg = msg;
    }

    @Bindable
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
        notifyPropertyChanged(BR.msg);
    }
}
