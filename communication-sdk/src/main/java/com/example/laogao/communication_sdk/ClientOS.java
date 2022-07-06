package com.example.laogao.communication_sdk;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;

import com.example.laogao.communication_sdk.util.LogUtil;

/**
 * 描述：客户端绑定服务端的统一管理类
 *
 * @author : 老高
 * @date : 2020/3/29
 **/
public class ClientOS {

    private static volatile ClientOS instance;
    private Context mContext;
    private HandlerThread mHandlerThread;
    private Handler mHandler;


    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    private ClientOS() {
    }

    public static ClientOS getInstance() {

        if (instance == null) {
            synchronized (ClientOS.class) {
                if (instance == null) {
                    instance = new ClientOS();
                }
            }
        }
        return instance;
    }

    public ClientOS init(Context context){
        mContext = context;
        mHandlerThread = new HandlerThread("ClientOS-thread");
        mHandlerThread.start();
        mHandler = new Handler(mHandlerThread.getLooper());
        return this;
    }

    public ClientOS setDebug(boolean isDebug){
        LogUtil.DEBUG = isDebug;
        return this;
    }

    public void bindService(){
        String packageName = mContext.getPackageName();
        Intent intent = new Intent();
        intent.setPackage(CommConstant.PACKAGE_NAME);
        intent.addCategory(CommConstant.COMM_SERVICE_ACTION);
        mContext.bindService(intent, mServiceConnection,Context.BIND_AUTO_CREATE);


    }

    public ClientOS registerAccount(){

        return this;
    }
    public ClientOS registerPayment(){

        return this;
    }


}
