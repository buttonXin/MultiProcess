package com.example.laogao.account_info.manager;

import com.example.laogao.communication_service.util.LogUtil;

public class AccountManager {

    private static final String TAG = AccountManager.class.getSimpleName();
    private static volatile AccountManager instance;

    private AccountManager() {
    }

    public static AccountManager getInstance() {

        if (instance == null) {
            synchronized (AccountManager.class) {
                if (instance == null) {
                    instance = new AccountManager();
                }
            }
        }
        return instance;
    }


    public void getAccountData() {
        LogUtil.e(TAG, "client 请求获取用户数据");
    }

    public void getAccountAuthData() {
        LogUtil.e(TAG, "client 请求获取授权数据");

    }

    public void login() {
        LogUtil.e(TAG, "client 请求登录");

    }
}
