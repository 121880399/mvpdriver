package org.zzy.driver.mvp.model.net.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.zzy.quick.json.JsonFactory;
import com.zzy.quick.net.HttpManager;
import com.zzy.quick.net.HttpSubscriber;
import com.zzy.quick.net.NetError;
import com.zzy.quick.utils.ToastUtils;

import org.zzy.driver.common.AppConfig;
import org.zzy.driver.common.CommonValue;
import org.zzy.driver.mvp.model.net.HttpCallBack;
import org.zzy.driver.mvp.model.net.HttpHeader;
import org.zzy.driver.mvp.model.net.HttpRequest;
import org.zzy.driver.mvp.model.net.HttpResult;
import org.zzy.driver.mvp.model.net.RequestCenter;
import org.zzy.driver.mvp.model.net.service.BaseService;

/**
 * @function 用retrofit得到BaseService
 * Created by zhou on 2018/4/8.
 */

public class BaseApi {

    private static BaseService baseService;

    public static BaseService getBaseService(){
        if(baseService ==null){
            synchronized (BaseApi.class) {
                if(baseService ==null){
                    baseService = HttpManager.getInstance().getRetrofit(AppConfig.BASEURL,true).create(BaseService.class);
                }
            }
        }
        return baseService;
    }


    public void doPost(final HttpRequest request, final HttpCallBack callBack){
        HttpHeader.setDefaultHeader(request);
        String strEntity= JsonFactory.getJsonUtils().toJson(request);
        BaseApi.getBaseService().request(strEntity)
                .compose(HttpManager.<HttpResult>getErrorTransformer())
                .compose(HttpManager.<HttpResult>getFlowableScheduler())
                .subscribe(new HttpSubscriber<HttpResult>() {
                    @Override
                    protected void onFail(NetError error) {
                        ToastUtils.showShort(error.getMessage());
                    }

                    @Override
                    public void onNext(HttpResult httpResult) {
                        if(httpResult.getResponse().getStatus().equals(CommonValue.SUCCESS_STATUS)) {
                            callBack.doSuccess(httpResult, request.getAction(), request.getMethod());
                        }else{
                            callBack.doFaild(httpResult, request.getAction(),request.getMethod());
                        }
                    }
                });
    }


}
