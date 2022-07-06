package com.example.laogao.twowaycommunication;

import android.app.Application;
import android.content.Intent;
import android.os.Build;

import com.example.laogao.account_info.AccountReceiver;
import com.example.laogao.communication_service.CommConstant;
import com.example.laogao.communication_service.CommunicationService;
import com.example.laogao.communication_service.manager.ReceiverManager;
import com.example.laogao.payment_info.PaymentReceiver;

public class App extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        // 启动服务
        Intent intent = new Intent(this, CommunicationService.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(intent);
        } else {
            startService(intent);
        }

        config();
    }

    private void config() {

        // 注册服务类型
        ReceiverManager.registerService(CommConstant.ACCOUNT_SERVICE, new AccountReceiver());
        ReceiverManager.registerService(CommConstant.ACCOUNT_SERVICE, new PaymentReceiver());


    }
}
