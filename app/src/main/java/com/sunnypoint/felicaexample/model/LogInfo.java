package com.sunnypoint.felicaexample.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.sunnypoint.felicaexample.BR;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Created by NhanCao on 08-Oct-15.
 */
public class LogInfo extends BaseObservable {
    private String msg;
    private Queue<String> list;
    private static LogInfo instance = new LogInfo("Greate");

    public static LogInfo getInstance() {
        return instance;
    }

    public LogInfo(String msg) {
        this.msg = msg;
        this.list = new ArrayDeque<>(10);
        this.list.add(msg);
    }

    @Bindable
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        try {
            this.list.add(msg);
            if (list.size() > 10) {
                list.remove();
                this.msg = "";
            }
            for (String item : list) {
                this.msg += item + "\n";
            }
        } catch (Exception e) {
            this.msg = msg;
            e.printStackTrace();
        }
        notifyPropertyChanged(BR.msg);
    }
}
