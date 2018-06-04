package org.zzy.driver.mvp.presenter;

import com.zzy.quick.mvp.presenter.BasePresenter;
import com.zzy.quick.utils.SPUtils;

import org.zzy.driver.common.CommonValue;
import org.zzy.driver.mvp.model.bean.response.ResponseUserInfo;
import org.zzy.driver.mvp.ui.activity.LoadingActivity;

/**
 * Created by zhou on 2018/4/4.
 */

public class LoadingPresenter extends BasePresenter<LoadingActivity>{


    /**
     * 判断用户是否登录
     * */
    public boolean isLogin(){
        ResponseUserInfo userInfo= (ResponseUserInfo) SPUtils.getInstance().getObject(CommonValue.USERINFO);
        if(userInfo!=null){
            return true;
        }
        return false;
    }

    /**
     * 是否是第一次使用
     * */
    public boolean isFirstAccess(){
        return SPUtils.getInstance().getBoolean(CommonValue.FIRSTACCESS,false);
    }

    /**
     * 设置已经访问过APP
     * */
    public void setAlreadyAccess(){
        SPUtils.getInstance().put(CommonValue.FIRSTACCESS,true);
    }


}
