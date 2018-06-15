package org.zzy.driver.mvp.model.net.api;

import org.zzy.driver.mvp.model.net.HttpCallBack;
import org.zzy.driver.mvp.model.net.HttpRequest;
import org.zzy.driver.mvp.model.net.RequestCenter;
import org.zzy.driver.utils.MD5Util;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @function 提供与用户相关的API接口
 * Created by zhou on 2018/5/4.
 */

public class UserApi extends BaseApi{

    private static UserApi api;

    public static UserApi newInstance(){
        if(api==null){
            synchronized (UserApi.class){
                if(api==null){
                    api=new UserApi();
                }
            }
        }
        return api;
    }

    private UserApi(){}

    /**
     * 登录接口
     * */
    public void login(String userName, String password, HttpCallBack callBack){
        HttpRequest request=new HttpRequest();
        request.addHeader("action", RequestCenter.USER_ACTION);
        request.addHeader("method",RequestCenter.LOGIN_METHOD);
        request.putParams("username",userName);
        //密码要两次加密，加密算法使用APP工程下utils包中的类
        request.putParams("password", MD5Util.md5Encode(MD5Util.md5Encode(password)));
        doPost(request,callBack);
    }

    /**
     * 更改头像接口
     * */
    public void changeAvatar(File avatar,int userId ,HttpCallBack callBack){
        HttpRequest request=new HttpRequest();
        request.addHeader("action", RequestCenter.USER_ACTION);
        request.addHeader("method",RequestCenter.CHANGEAVATAR_METHOD);
        request.putParams("userId",userId);
        Map<String, File> fileMap = new HashMap<>();
        if (avatar.exists()) {
            fileMap.put("avatorImage", avatar);
        }
        uploadeFile(request,fileMap,callBack);
    }

    /**
     * 获取用户信息接口
     * */
    public void getUserInfo(int userId,HttpCallBack callBack){
        HttpRequest request=new HttpRequest();
        request.addHeader("action", RequestCenter.USER_ACTION);
        request.addHeader("method",RequestCenter.GET_USERINFO_METHOD);
        if(0!=userId){
            request.putParams("userId",userId);
        }
        doPost(request,callBack);
    }


    /**
     * 获取绑定的车辆信息
     * */
    public void getBindVehicle(int driverId,HttpCallBack callBack){
        HttpRequest request=new HttpRequest();
        request.addHeader("action", RequestCenter.VEHICLE_ACTION);
        request.addHeader("method",RequestCenter.GET_BINDVEHICLE_METHOD);
        request.putParams("driverId",driverId);
        doPost(request,callBack);
    }

    /**
     * 钱包验证支付密码接口
     * */
    public void checkPayPassword(String password,HttpCallBack callBack){
        HttpRequest request=new HttpRequest();
        request.addHeader("action", RequestCenter.WALLET_ACTION);
        request.addHeader("method",RequestCenter.CHECK_PAYPASSWORD_METHOD);
        request.putParams("payPassword",MD5Util.md5Encode(MD5Util.md5Encode(password)));
        doPost(request,callBack);
    }

}
