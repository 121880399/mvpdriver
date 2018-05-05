package org.zzy.driver.mvp.model.net;

import android.util.ArrayMap;

/**
 * 项目名称: mvpDriver
 * 创建人: 周正一
 * 创建时间：2017/7/27
 */

public class HttpRequest {
    private ArrayMap header;//请求头
    //比较特殊，这个项目的服务端请求需要再多包一层
    private Request request;


    /**
     * Instantiates a new Request.
     */
    public HttpRequest(){
        header =new ArrayMap();
        request =new Request();
    }

    /**
     * 设置Action
     *
     * @param action the action
     * @return the request
     */
    public HttpRequest setAction(String action){
        header.put("action",action);
        return this;
    }

    /**
     * 设置method
     *
     * @param method the method
     * @return the request
     */
    public HttpRequest setMethod(String method){
        header.put("method",method);
        return this;
    }

    /**
     * 提供添加头方法
     *
     * @param key   the key
     * @param value the value
     * @return the request
     */
    public HttpRequest addHeader(String key,Object value){
        header.put(key,value);
        return this;
    }

    /**
     * 设置是否加密
     *
     * @param isEncrpy the is encrpy
     * @return the request
     */
    public HttpRequest setEncrpy(boolean isEncrpy){
        if (isEncrpy) {
            return  addHeader("encrpy", 1);
        } else {
            return addHeader("encrpy", 0);
        }
    }

    /**
     * 是否进行ZIP压缩
     *
     * @param isZip the is zip
     * @return the request
     */
    public HttpRequest setZip(boolean isZip){
        if (isZip) {
            return addHeader("zip", 1);
        } else {
            return addHeader("zip", 0);
        }
    }

    /**
     * 添加请求数据
     *
     * @param key   the key
     * @param value the value
     * @return the request
     */
    public HttpRequest putParams(String key,Object value){
        request.getParams().put(key,value);
        return this;
    }


    /**
     * Gets request date.
     *
     * @return the request date
     */
    public ArrayMap getParams() {
        return request.getParams();
    }

    /**
     * 是否采用zip压缩
     *
     * @return the boolean
     */
    public boolean isZip() {
        int zip= header.get("zip")==null?0: (int) header.get("zip");
        return zip== 1;
    }


    /**
     * Gets action.
     *
     * @return the action
     */
    public String getAction() {
        return (String) header.get("action");
    }


    /**
     * Gets method.
     *
     * @return the method
     */
    public String getMethod() {
        return (String) header.get("method");
    }

    class Request{
        private ArrayMap params;//请求数据

        public Request(){
            params=new ArrayMap();
        }

        public ArrayMap getParams() {
            return params;
        }

        public void setParams(ArrayMap params) {
            this.params = params;
        }
    }

}
