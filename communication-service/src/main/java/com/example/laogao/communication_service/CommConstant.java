package com.example.laogao.communication_service;

/**
 * 常量的配置
 */
public class CommConstant {


    public static final String PACKAGE_NAME = "com.example.laogao.twowaycommunication";
    public static final String COMM_SERVICE_ACTION = "com.example.laogao.communication_service.CommunicationService";


    // -----------------------
    // -------账号-------------
    // -----------------------

    // 账号的服务
    public static final int ACCOUNT_SERVICE = 1000;

    // ---------client--------------
    // 客户端获取用户数据
    public static final int CLIENT_GET_ACCOUNT_DATA = ACCOUNT_SERVICE + 1;
    // 客户端获取授权信息
    public static final int CLIENT_GET_AUTH_DATA = ACCOUNT_SERVICE + 2;
    // 客户端请求登录
    public static final int CLIENT_REQUEST_LOGIN = ACCOUNT_SERVICE + 3;


    // ---------service--------------

    // 服务端发送 获取用户数据
    public static final int SERVICE_GET_ACCOUNT_DATA = ACCOUNT_SERVICE + 1;
    // 服务端发送 获取授权信息
    public static final int SERVICE_GET_AUTH_DATA = ACCOUNT_SERVICE + 2;
    // 服务端发送 退出登录
    public static final int SERVICE_LOGOUT = ACCOUNT_SERVICE + 3;





    // -----------------------
    // -------支付-------------
    // -----------------------
    // 支付的服务
    public static final int PAYMENT_SERVICE = 2000;

    // ---------client--------------
    // 客户端获取用户订单信息
    public static final int CLIENT_GET_ORDER_INFO = PAYMENT_SERVICE + 1;
    // 客户端获取订单列表
    public static final int CLIENT_GET_ORDER_LIST = PAYMENT_SERVICE + 2;
    // 客户端请求 支付
    public static final int CLIENT_TO_PAY = PAYMENT_SERVICE + 3;



}
