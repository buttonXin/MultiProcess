// IClientService.aidl
package com.example.laogao.communication_service;

// Declare any non-default types here with import statements

interface IClientService {

    /**
     *
     * @param serviceType   当前的服务类型
     * @param type  类型下的某一个功能
     * @param data  数据 json类型的字符串
     * @param sync  是否需要同步
     */
    String clientService(in int serviceType ,in int type ,in  String data ,in boolean sync);
}
