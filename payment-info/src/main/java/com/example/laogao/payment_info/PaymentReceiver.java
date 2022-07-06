package com.example.laogao.payment_info;

import android.os.Handler;
import android.os.HandlerThread;

import com.example.laogao.communication_service.CommRequest;
import com.example.laogao.communication_service.base.AbsBaseReceiver;

public class PaymentReceiver extends AbsBaseReceiver {

    private Handler mHandler;

    public PaymentReceiver() {
        HandlerThread handlerThread = new HandlerThread("PaymentReceiver-thread");
        handlerThread.start();
        mHandler = new Handler(handlerThread.getLooper());
    }

    @Override
    public Handler getHandler() {
        return mHandler;
    }

    @Override
    public String onReceiver(CommRequest request, int funType, String data) {
        return null;
    }
}
