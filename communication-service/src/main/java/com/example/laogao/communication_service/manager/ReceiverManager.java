package com.example.laogao.communication_service.manager;

import android.os.RemoteException;

import com.example.laogao.communication_service.CommRequest;
import com.example.laogao.communication_service.CommunicationService;
import com.example.laogao.communication_service.IClientService;
import com.example.laogao.communication_service.base.AbsBaseReceiver;
import com.example.laogao.communication_service.util.LogUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 统一处理接收的信息
 */
public class ReceiverManager {


    private static final String TAG = ReceiverManager.class.getSimpleName();
    private CommunicationService mService;
    private  static Map<Integer, AbsBaseReceiver> mReceiverMap;

    public ReceiverManager(CommunicationService service) {
        mService = service;

        mReceiverMap = new HashMap<>();
    }

    public String onReceiver(final String pkg, final int serviceType, final int funType, final String data, boolean sync) {

        LogUtil.e("收到的 信息", pkg, serviceType, funType, data, sync);
        final AbsBaseReceiver absBaseReceiver = mReceiverMap.get(serviceType);

        if (absBaseReceiver == null) {
            return "";
        }
        final CommRequest request = new CommRequest(pkg, serviceType, funType, data);
        if (sync) {
            try {
                return absBaseReceiver.onReceiver(request, funType, data);
            } catch (Exception e) {
                LogUtil.e("异常处理 " + e.getMessage());
                return "";
            }

        } else {
            absBaseReceiver.getHandler().post(new Runnable() {
                @Override
                public void run() {
                    String result = absBaseReceiver.onReceiver(request, funType, data);
                    LogUtil.e(TAG, "result = " + result);
                    IClientService iClientService = mService.getIClientService(pkg);
                    if (iClientService != null) {
                        try {
                            iClientService.clientService(serviceType, funType, result, false);
                        } catch (RemoteException e) {
                            LogUtil.e("异步发送失败", e.getMessage());
                        }
                    } else {
                        LogUtil.e("获取 客户端中的服务端失败 iClientService == null");
                    }
                }
            });
            return "async";
        }
    }

    /**
     * 本地项目需要注册哪些 服务类型  进行接收
     *
     * @param serviceType
     * @param receiver
     */
    public static void registerService(int serviceType, AbsBaseReceiver receiver) {
        mReceiverMap.put(serviceType, receiver);
    }

    public static void unregisterService(int serviceType) {
        mReceiverMap.remove(serviceType);
    }


    public void onDestroy() {
        mReceiverMap.clear();
        mService = null;
    }
}
