package com.example.laogao.communication_service.manager;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.RemoteCallbackList;
import android.os.RemoteException;

import com.example.laogao.communication_service.ClientServiceCookie;
import com.example.laogao.communication_service.CommRequest;
import com.example.laogao.communication_service.CommunicationService;
import com.example.laogao.communication_service.IClientService;
import com.example.laogao.communication_service.util.LogUtil;

/**
 * 描述：统一管理发送类
 *
 * @author : 老高
 * @date : 2020/3/29
 **/
public class SenderManager {

    private static final String TAG = SenderManager.class.getSimpleName();
    private static volatile SenderManager instance;
    private Handler mHandler;
    private CommunicationService mService;
    private final HandlerThread mThread;

    private SenderManager() {
        mThread = new HandlerThread("SenderManager-thread");
    }

    public static SenderManager getInstance() {

        if (instance == null) {
            synchronized (SenderManager.class) {
                if (instance == null) {
                    instance = new SenderManager();
                }
            }
        }
        return instance;
    }


    /**
     * 发送
     */
    public static String send(int serviceType, int funType, String data) {
        return doSend(null, serviceType, funType, data);
    }

    public static String send(CommRequest request, int serviceType, int funType, String data) {
        return doSend(request, serviceType, funType, data);
    }

    private static String doSend(CommRequest request, int serviceType, int funType, String data) {
        return instance.finalSend(request == null ? null : request.pkg, serviceType, funType, data, true);
    }

    private String finalSend(String pkg, final int serviceType, final int funType, final String data, boolean sync) {

        if (pkg != null) {
            final IClientService iClientService = mService.getIClientService(pkg);
            return clientSend(iClientService, serviceType, funType, data, sync);
        } else {
            // 表示群发给所有已经绑定本服务的客户端
            RemoteCallbackList remoteCallbackList = mService.getAllClientService();
            int count = remoteCallbackList.beginBroadcast();
            LogUtil.e(TAG, "开始群发，当前连接 的总数是： " + count);
            IClientService iClientService = null;
            ClientServiceCookie cookie = null;
            while (count > 0) {
                count--;
                cookie = (ClientServiceCookie) remoteCallbackList.getBroadcastCookie(count);
                // 当条件都满足的时候在进行发送
                if (cookie != null && cookie.mServiceTypes.contains(serviceType) &&
                        cookie.mIClientService != null) {
                    return clientSend(cookie.mIClientService, serviceType, funType, data, sync);
                } else {
                    LogUtil.e(TAG, "cookie中的条件不满足");
                }
            }

            remoteCallbackList.finishBroadcast();

            return "上面有些条件不满足";
        }
    }

    /**
     * 统一发送封装
     */
    private String clientSend(final IClientService iClientService, final int serviceType, final int funType, final String data, boolean sync) {

        if (iClientService != null) {
            if (sync) {
                try {
                    String request = iClientService.clientService(serviceType, funType, data, true);
                    LogUtil.e(TAG, "发送后得到的结果", request);
                    return request;
                } catch (RemoteException e) {
                    LogUtil.e(TAG, "同步发送失败", e.getMessage());
                    return "error";
                }
            } else {
                if (mHandler == null) {
                    mThread.start();
                    mHandler = new Handler(mThread.getLooper());
                }
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            String request = iClientService.clientService(serviceType, funType, data, true);
                            LogUtil.e(TAG, "发送后得到的结果", request);
                        } catch (RemoteException e) {
                            LogUtil.e(TAG, "同步发送失败", e.getMessage());
                        }
                    }
                });
                return "async";
            }
        } else {
            return "当前包名的客户端是null";
        }

    }


    /**
     * 获取服务
     *
     * @param service
     */
    public void setService(CommunicationService service) {
        mService = service;
    }


    public void onDestroy() {
        mThread.quit();
        instance = null;
        mHandler.removeCallbacksAndMessages(null);
        mHandler= null;
        mService = null;
    }
}
