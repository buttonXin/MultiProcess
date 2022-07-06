package com.example.laogao.communication_service;

import java.util.List;

/**
 * 保存客户端中的信息
 */
public class ClientServiceCookie {

    public  String mPkg;

    public List<Integer> mServiceTypes ;

    public IClientService mIClientService;

    public ClientServiceCookie(String pkg, List<Integer> serviceTypes, IClientService IClientService) {
        mPkg = pkg;
        mServiceTypes = serviceTypes;
        mIClientService = IClientService;
    }


}
