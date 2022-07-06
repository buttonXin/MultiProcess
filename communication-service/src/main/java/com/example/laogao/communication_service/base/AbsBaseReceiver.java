package com.example.laogao.communication_service.base;

import android.os.Handler;

import com.example.laogao.communication_service.CommRequest;

public abstract class AbsBaseReceiver {

    public AbsBaseReceiver() {
    }

    public abstract Handler getHandler();

    public abstract String onReceiver(CommRequest request, int funType, String data);
}
