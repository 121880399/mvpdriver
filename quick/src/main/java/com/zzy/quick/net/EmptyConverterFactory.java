package com.zzy.quick.net;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.annotation.Nullable;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by zhou on 2018/4/9.
 */

public class EmptyConverterFactory extends Converter.Factory {


    /**
     * @param type 接口中定义的方法的返回类型
     * @param annotations 接口中方法定义的注解
     * @param retrofit Retrofit对象
     * */
    @Nullable
    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        //下一面一句不能省略，尤其是skipPast参数设置为this.
        final Converter<ResponseBody, ?> delegate = retrofit.nextResponseBodyConverter(this, type, annotations);
        return new Converter<ResponseBody,Object>() {
            @Override
            public Object convert(ResponseBody body) throws IOException {
                if (body.contentLength() == 0) return null;
                return delegate.convert(body);
            }
        };
    }
}
