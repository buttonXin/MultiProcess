package com.example.laogao.account_info;

import android.os.Handler;
import android.os.HandlerThread;

import com.example.laogao.account_info.manager.AccountManager;
import com.example.laogao.communication_service.CommConstant;
import com.example.laogao.communication_service.CommRequest;
import com.example.laogao.communication_service.base.AbsBaseReceiver;
import com.example.laogao.communication_service.util.LogUtil;

public class AccountReceiver extends AbsBaseReceiver {

    private static final String TAG = AccountReceiver.class.getSimpleName();
    private Handler mHandler;

    public AccountReceiver() {
        HandlerThread handlerThread = new HandlerThread("AccountReceiver-thread");
        handlerThread.start();
        mHandler = new Handler(handlerThread.getLooper());
    }

    @Override
    public Handler getHandler() {
        return mHandler;
    }

    @Override
    public String onReceiver(CommRequest request, int funType, String data) {
        LogUtil.e(TAG, "收到的账号相关的信息", request.toString(), funType, data);

        switch (funType) {
            case CommConstant.CLIENT_GET_ACCOUNT_DATA:
                AccountManager.getInstance().getAccountData();
                break;
            case CommConstant.CLIENT_GET_AUTH_DATA:
                AccountManager.getInstance().getAccountAuthData();
                break;
            case CommConstant.CLIENT_REQUEST_LOGIN:
                AccountManager.getInstance().login();
                break;


            default:
                break;
        }


        return "";
    }
}
