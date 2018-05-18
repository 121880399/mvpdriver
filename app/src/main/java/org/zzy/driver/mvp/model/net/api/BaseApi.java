package org.zzy.driver.mvp.model.net.api;

import android.util.Base64;

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
import org.zzy.driver.utils.UserInfoUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * @function 用retrofit得到BaseService
 * 如果在此类以外不会使用BaseService实例，可以不使用单利模式。
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
                            callBack.doSuccess(httpResult, request.readAction(), request.readMethod());
                        }else{
                            callBack.doFaild(httpResult, request.readAction(),request.readMethod());
                        }
                    }
                });
    }

    /**
     * 上传文件使用这个接口
     * */
    public void uploadeFile(final HttpRequest request, Map<String,File> fileMap, final HttpCallBack callBack){
        HttpHeader.setDefaultHeader(request);
        String strEntity= JsonFactory.getJsonUtils().toJson(request);
        Map<String,RequestBody> files=new HashMap<>();
        for (String key : fileMap.keySet()) {
            //userid+字段名称用base64编码+.jpg
            String filename= new String(Base64.encode((UserInfoUtils.getUserInfo().getId()+key).getBytes(),Base64.DEFAULT))+".jpg";
            files.put(key+"; filename=\""+filename+"\"",RequestBody.create(MediaType.parse("image/jpeg"),fileMap.get(key)));
        }
        BaseApi.getBaseService().request(strEntity,files)
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
                            callBack.doSuccess(httpResult, request.readAction(), request.readMethod());
                        }else{
                            callBack.doFaild(httpResult, request.readAction(),request.readMethod());
                        }
                    }
                });
    }


}
