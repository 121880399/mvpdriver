package org.zzy.driver.mvp.model.net;

import android.text.TextUtils;
import android.widget.ExpandableListView;

import com.alibaba.fastjson.JSONObject;
import com.zzy.quick.json.JsonFactory;
import com.zzy.quick.mvp.model.BaseModel;

import org.zzy.driver.common.AuthErrorEnum;
import org.zzy.driver.common.BizErrorEnum;


/**
 * 项目名称: mvpdriver
 * 创建人: 周正一
 * 创建时间：2017/7/25
 */

public class HttpResult extends BaseModel {
    private ResponseHeader header;
    private ResponseData response;

    public ResponseHeader getHeader() {
        return header;
    }

    public void setHeader(ResponseHeader header) {
        this.header = header;
    }

    public ResponseData getResponse() {
        return response;
    }

    public void setResponse(ResponseData response) {
        this.response = response;
    }

    @Override
    public String getErrorMsg() {
        return response.getMessage();
    }

    @Override
    public boolean isAuthError() {
        return AuthErrorEnum.isAuthError(response.getStatus());
    }

    @Override
    public boolean isBizError() {
        return BizErrorEnum.isBizError(response.getStatus());
    }

    /**
     * 得到返回json中data数据
     * */
    public JSONObject getMainData(){
        try {
            String data=response.getResult().getData();
            if(data.length()==0|| TextUtils.isEmpty(data)){
                return null;
            }else {
                JSONObject object = (JSONObject) JsonFactory.getJsonUtils().parseObject(data);
                return object;
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
