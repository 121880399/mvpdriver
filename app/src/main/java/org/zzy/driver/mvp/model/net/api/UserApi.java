package org.zzy.driver.mvp.model.net.api;

import com.zzy.quick.net.HttpManager;

import org.zzy.driver.common.AppConfig;
import org.zzy.driver.mvp.model.net.service.UserService;

/**
 * Created by zhou on 2018/4/8.
 */

public class UserApi {

    private static UserService userService;

    public static UserService getUserService(){
        if(userService==null){
            synchronized (UserApi.class) {
                if(userService==null){
                    userService= HttpManager.getInstance().getRetrofit(AppConfig.BASEURL,true).create(UserService.class);
                }
            }
        }
        return userService;
    }



}
