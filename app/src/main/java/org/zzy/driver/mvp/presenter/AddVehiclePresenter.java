package org.zzy.driver.mvp.presenter;

import android.net.Uri;
import android.text.TextUtils;

import com.alibaba.fastjson.JSONObject;
import com.zzy.quick.json.JsonFactory;
import com.zzy.quick.mvp.presenter.BasePresenter;
import com.zzy.quick.utils.ImageUtils;

import org.zzy.driver.mvp.model.bean.request.RequestVehicleInfo;
import org.zzy.driver.mvp.model.bean.response.ResponseVehicle;
import org.zzy.driver.mvp.model.bean.response.ResponseVehicleType;
import org.zzy.driver.mvp.model.bean.response.ResponseContainerType;
import org.zzy.driver.mvp.model.bean.response.ResponseLinkType;
import org.zzy.driver.mvp.model.net.HttpCallBack;
import org.zzy.driver.mvp.model.net.HttpResult;
import org.zzy.driver.mvp.model.net.RequestCenter;
import org.zzy.driver.mvp.model.net.api.BusinessApi;
import org.zzy.driver.mvp.ui.activity.AddVehicleActivity;
import org.zzy.driver.utils.UserInfoUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zhou on 2018/6/25.
 */

public class AddVehiclePresenter extends BasePresenter<AddVehicleActivity> implements HttpCallBack {


    /**
     * 获取车辆的基本信息
     * */
    public void getVehicleBasicInfo(){
        BusinessApi.getInstance().getCarBaseData(this);
    }

    /**
     *  通过uri返回一个File
     */
    public File uri2File(Uri imageUri,boolean isCamera){
        String img_path=imageUri.getPath();
        //对文件进行压缩
        File file =ImageUtils.compress(img_path);
        if(isCamera){
            String path=getView().getExternalCacheDir().getPath();
            String newFilePath=path.concat("/").concat(String.valueOf(System.currentTimeMillis())).concat(".jpg");
            File newFile=new File(newFilePath);
            file.renameTo(newFile);
            return newFile;
        }else {
            return file;
        }
    }


    /**
     * 添加车辆
     * */
    public void addVehicle(RequestVehicleInfo vehicle){
        if(verifyData(vehicle)){
            //注册用户传driverid 签约承运商填companyid
            BusinessApi.getInstance().addVehicle(UserInfoUtils.getUserInfo().getDriverId(),vehicle,this);
        }
    }


    /**
     * 添加车辆前，验证数据
     * */
    private boolean verifyData(RequestVehicleInfo vehicle){
        if(TextUtils.isEmpty(vehicle.getVehicleCode())){
            getView().showError("请输入车牌号");
            return false;
        }
        if(TextUtils.isEmpty(vehicle.getVehicleType())){
            getView().showError("请选择车辆类型");
            return false;
        }
        if(TextUtils.isEmpty(vehicle.getLinkType())){
            getView().showError("请选择拖挂形式");
            return false;
        }
        if(TextUtils.isEmpty(vehicle.getContainerType())){
            getView().showError("请选择箱型");
            return false;
        }
        if(TextUtils.isEmpty(vehicle.getVehicleLength())){
            getView().showError("请输入车长");
            return false;
        }
        if(TextUtils.isEmpty(vehicle.getVehicleWeight())){
            getView().showError("请输入车重");
            return false;
        }
        if(Double.valueOf(vehicle.getVehicleLength())<=0){
            getView().showError("请输入正确的车长");
            return false;
        }
        if(Double.valueOf(vehicle.getVehicleWeight())<=0){
            getView().showError("请输入正确的车重");
            return false;
        }
        if(vehicle.getImageFileMap().size()<3){
            getView().showError("请将图片上传完整");
            return false;
        }
        return true;
    }

    @Override
    public void doSuccess(HttpResult response, String requestUrl, String method) {
        if (requestUrl.equals(RequestCenter.VEHICLE_ACTION) && method.equals(RequestCenter.GET_CAR_INFO_METHOD)) {
            JSONObject mainData = response.getMainData();
            ArrayList<ResponseVehicleType> vehicleTypeList= (ArrayList<ResponseVehicleType>) JsonFactory.getJsonUtils().parseArray(mainData.getString("carTypeMap"),ResponseVehicleType.class);
            ArrayList<ResponseContainerType> containerTypeList= (ArrayList<ResponseContainerType>) JsonFactory.getJsonUtils().parseArray(mainData.getString("boxTypeMap"),ResponseContainerType.class);
            ArrayList<ResponseLinkType> linkTypeList= (ArrayList<ResponseLinkType>) JsonFactory.getJsonUtils().parseArray(mainData.getString("linkTypeMap"),ResponseLinkType.class);
            getView().setVehicleTypeList(vehicleTypeList);
            getView().setContainerTypeList(containerTypeList);
            getView().setLinkTypeList(linkTypeList);
        }
        if(requestUrl.equals(RequestCenter.VEHICLE_ACTION) && method.equals(RequestCenter.ADD_CAR_METHOD)){
            getView().addVehicleSuccess();
        }
    }

    @Override
    public void doFaild(HttpResult error, String requestUrl, String method) {

    }
}
