package org.zzy.driver.mvp.model.net.api;

import org.zzy.driver.mvp.model.net.HttpCallBack;
import org.zzy.driver.mvp.model.net.HttpRequest;
import org.zzy.driver.mvp.model.net.RequestCenter;

/**
 * @function 跟系统相关的API
 * Created by zhou on 2018/5/14.
 */

public class SystemApi extends BaseApi{
    private static final int ANDROID_PLATFORM=0;

    public void checkUpdate(int versionCode, String versionName, HttpCallBack callBack){
        HttpRequest request=new HttpRequest();
        request.addHeader("action", RequestCenter.VERSION_ACTION);
        request.addHeader("method",RequestCenter.CHECKVERSION);
        request.putParams("versionCode",versionCode);
        request.putParams("versionName",versionName);
        request.putParams("platform",ANDROID_PLATFORM);
        doPost(request,callBack);
    }
}
