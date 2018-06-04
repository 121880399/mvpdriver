package org.zzy.driver.utils;

import com.zzy.quick.utils.SPUtils;

import org.zzy.driver.common.CommonValue;
import org.zzy.driver.mvp.model.bean.response.ResponseUserInfo;

/**
 * Created by zhou on 2018/5/11.
 */

public class UserInfoUtils {

    /**
     * 从sp中得到用户信息
     * */
    public static ResponseUserInfo getUserInfo(){
        return (ResponseUserInfo)SPUtils.getInstance().getObject(CommonValue.USERINFO);
    }
}
