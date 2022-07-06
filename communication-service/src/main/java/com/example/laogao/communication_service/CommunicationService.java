package com.example.laogao.communication_service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;

import com.example.laogao.communication_service.manager.ReceiverManager;
import com.example.laogao.communication_service.manager.SenderManager;

import java.util.List;

import androidx.annotation.RequiresApi;


public class CommunicationService extends Service {

    private static final int NOTIFICATION_123 = 123;
    /**
     * 专门保存aidl的集合
     */
    private RemoteCallbackList<IClientService> mRemoteCallbackList = new RemoteCallbackList<>();
    private ReceiverManager mReceiverManager;
    private NotificationManager mManager;

    @Override
    public void onCreate() {
        super.onCreate();

        mReceiverManager = new ReceiverManager(this);
        SenderManager.getInstance().setService(this);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
            needStartForeground();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void needStartForeground() {
        String channelId = "Communication-two-way";
        String channelName = "Communication-two-way";

        NotificationChannel channel = new NotificationChannel(channelId, channelName,
                NotificationManager.IMPORTANCE_NONE);
        channel.setLightColor(Color.TRANSPARENT);
        channel.setLockscreenVisibility(0);
        mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (mManager != null) {
            mManager.createNotificationChannel(channel);
            Notification notification = new Notification.Builder(this, channelId)
                    .setOngoing(true)
                    .setSmallIcon(android.R.mipmap.sym_def_app_icon)
                    .setContentText(channelId)
                    .setCategory("service")
                    .build();
            startForeground(NOTIFICATION_123, notification);
            mManager.notify(NOTIFICATION_123, notification);
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new ExternalService();
    }

    public RemoteCallbackList getAllClientService() {
        return mRemoteCallbackList;
    }


    class ExternalService extends IService.Stub {
        @Override
        public String service(String pkg, int serviceType, int funType, String data, boolean sync) throws RemoteException {
            return mReceiverManager.onReceiver(pkg, serviceType, funType, data, sync);
        }

        @Override
        public void register(String pkg, List serviceTypes, IClientService callback) throws RemoteException {
            mRemoteCallbackList.register(callback, new ClientServiceCookie(pkg, serviceTypes, callback));
        }

        @Override
        public void unregister(IClientService callback) throws RemoteException {
            mRemoteCallbackList.unregister(callback);
        }
    }

    /**
     * 获取客户端中的服务端接口
     *
     * @param pkg 通过包名获取
     * @return
     */
    public IClientService getIClientService(String pkg) {
        // 调用begin的时候，必须要有finish结尾，否则报错（后续优化可以从单独的一个线程中调用）
        int count = mRemoteCallbackList.beginBroadcast();
        IClientService iClientService = null;
        ClientServiceCookie cookie = null;
        // 从最后一个开始取数据
        while (count > 0) {
            count--;
            cookie = (ClientServiceCookie) mRemoteCallbackList.getBroadcastCookie(count);
            if (cookie != null && cookie.mPkg.equals(pkg)) {
                iClientService = cookie.mIClientService;
                break;
            }
        }
        mRemoteCallbackList.finishBroadcast();
        return iClientService;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mRemoteCallbackList.kill();
        SenderManager.getInstance().onDestroy();
        mReceiverManager.onDestroy();
        if (mManager != null) {
            mManager.cancel(NOTIFICATION_123);
        }
    }
}
