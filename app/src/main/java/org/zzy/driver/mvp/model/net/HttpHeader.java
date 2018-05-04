package org.zzy.driver.mvp.model.net;

import com.zzy.quick.utils.SPUtils;

import org.zzy.driver.common.AppConfig;
import org.zzy.driver.common.CommonValue;

/**
 * Created by zhou on 2018/4/9.
 */

public class HttpHeader {

    public static HttpRequest setDefaultHeader(HttpRequest request){
        request.addHeader("version", AppConfig.VERSION_1_1_1);
        request.addHeader("clientid", SPUtils.getInstance().getString(CommonValue.PUSH_CID));
//        request.addHeader("userId", SPUtils.getInstance().getString(CommonValue.USERID));
        request.addHeader("userId", "");
        request.addHeader("token", SPUtils.getInstance().getString(CommonValue.TOKENCODE));
        request.addHeader("device", "xiaomi");
        request.addHeader("platform","android");
        return request;
    }
}
