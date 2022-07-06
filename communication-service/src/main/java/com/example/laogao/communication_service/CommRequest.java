package com.example.laogao.communication_service;

/**
 * 将接收到的数据包装起来，以便后续进行发送的时候使用
 */
public class CommRequest {


    public String pkg;
    public int funType;
    public int serviceType;
    public String data;

    public CommRequest(String pkg, int serviceType, int funType, String data) {
        this.pkg = pkg;
        this.funType = funType;
        this.serviceType = serviceType;
        this.data = data;
    }


    @Override
    public String toString() {
        return "CommRequest{" +
                "pkg='" + pkg + '\'' +
                ", type=" + funType +
                ", serviceType=" + serviceType +
                ", data='" + data + '\'' +
                '}';
    }
}
