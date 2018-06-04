package org.zzy.driver.mvp.model.net.service;

import android.support.v4.media.VolumeProviderCompat;

import org.zzy.driver.mvp.model.bean.response.ResponseUserInfo;
import org.zzy.driver.mvp.model.net.HttpResult;

import java.util.Map;

import io.reactivex.Flowable;
import okhttp3.RequestBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

/**
 * Created by zhou on 2018/4/8.
 */

public interface BaseService {

    @FormUrlEncoded
    @POST("apprest/exec/")
    Flowable<HttpResult> request(@Field("data")String data);

    @Multipart
    @POST("apprest/exec/")
    Flowable<HttpResult> request(@Part("data") RequestBody data, @PartMap Map<String, RequestBody> files);
}
