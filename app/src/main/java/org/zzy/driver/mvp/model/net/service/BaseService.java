package org.zzy.driver.mvp.model.net.service;

import org.zzy.driver.mvp.model.bean.response.ResponseUserInfo;
import org.zzy.driver.mvp.model.net.HttpResult;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by zhou on 2018/4/8.
 */

public interface BaseService {

    @FormUrlEncoded
    @POST("apprest/exec/")
    Flowable<HttpResult> request(@Field("data")String data);
}
