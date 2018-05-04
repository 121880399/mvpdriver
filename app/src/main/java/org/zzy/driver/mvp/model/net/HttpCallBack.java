package org.zzy.driver.mvp.model.net;

import com.zzy.quick.net.NetError;

import java.util.Map;

/**
 * Http请求回调接口
 */
public interface HttpCallBack {
    /**
     * 成功回调
     */
    public void doSuccess(HttpResult response, String requestUrl, String method);

    /**
     * 失败回调
     */
    public void doFaild(HttpResult error, String requestUrl, String method);

}