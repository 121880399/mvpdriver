package org.zzy.driver.mvp.model.net.api;

import org.zzy.driver.mvp.model.net.HttpCallBack;
import org.zzy.driver.mvp.model.net.HttpRequest;
import org.zzy.driver.mvp.model.net.RequestCenter;
import org.zzy.driver.utils.MD5Util;

/**
 * @function 提供与用户相关的API接口
 * Created by zhou on 2018/5/4.
 */

public class UserApi extends BaseApi{

    public void login(String userName, String password, HttpCallBack callBack){
        final HttpRequest request=new HttpRequest();
        request.addHeader("action", RequestCenter.USER_ACTION);
        request.addHeader("method",RequestCenter.LOGIN_METHOD);
        request.putParams("username",userName);
        //密码要两次加密，加密算法使用APP工程下utils包中的类
        request.putParams("password", MD5Util.md5Encode(MD5Util.md5Encode(password)));
        doPost(request,callBack);
    }

}
