// IService.aidl
package com.example.laogao.communication_service;
import com.example.laogao.communication_service.IClientService;
// Declare any non-default types here with import statements

interface IService {

    /**
     *
     * @param pkg 当前的包名
     * @param serviceType   当前的服务类型
     * @param type  类型下的某一个功能
     * @param data  数据 json类型的字符串
     * @param sync  是否需要同步
     */
    String  service(in String pkg , in int serviceType ,in int funType ,in String data,in boolean sync);

    /**
    * 将客户端的服务注册到 服务端中
    * pkg 根据包名进行注册
    * serviceTypes 客户端注册有哪些服务类型
    * callback 客户端中的服务端
    */
    void register(in String pkg ,in List serviceTypes , in IClientService callback);

    /**
    * 通过客户端的服务进行解注册
    */
    void unregister(in IClientService callback);

}
