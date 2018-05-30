package org.zzy.driver;

import android.app.Application;

import com.zzy.quick.net.HttpManager;
import com.zzy.quick.net.NetError;
import com.zzy.quick.net.NetProvider;
import com.zzy.quick.net.RequestHandler;
import com.zzy.quick.utils.Utils;
import com.zzy.quick.utils.log.LogFactory;

import org.zzy.driver.mvp.model.bean.response.MyObjectBox;

import io.objectbox.BoxStore;
import okhttp3.CookieJar;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.converter.fastjson.FastJsonConverterFactory;

/**
 * Created by zhou on 2018/4/4.
 */

public class BaseApplication extends Application {

    private static BoxStore mBoxStore;

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化数据库，MyObjectBox必须在有实体类，并且添加了@Entity 注解，Rebuild后才会生成
        mBoxStore= MyObjectBox.builder().androidContext(this).build();
        //设置全局Log的Tag
        LogFactory.getLogUtil().setGlobalTag("Driver");
        //初始化utils
        Utils.init(this);
        //创建默认的Provider
        HttpManager.registerProvider(new NetProvider() {
            @Override
            public Interceptor[] configInterceptors() {
                return new Interceptor[0];
            }

            @Override
            public void configHttps(OkHttpClient.Builder builder) {

            }

            @Override
            public CookieJar configCookie() {
                return null;
            }

            @Override
            public RequestHandler configHandler() {
                return null;
            }

            @Override
            public long configConnectTimeoutMills() {
                return 10 * 1000L;
            }

            @Override
            public long configReadTimeoutMills() {
                return 10 * 1000L;
            }

            @Override
            public boolean configLogEnable() {
                return true;
            }

            @Override
            public boolean handleError(NetError error) {
                return false;
            }

            @Override
            public boolean useProgress() {
                return false;
            }

            @Override
            public Converter.Factory jsonParseFactory() {
                return FastJsonConverterFactory.create();
            }
        });
    }

    public static BoxStore getBoxStore(){
        return mBoxStore;
    }
}
