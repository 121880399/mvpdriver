package org.zzy.driver.mvp.model.net.api;

import com.zzy.quick.utils.TimeUtils;

import org.zzy.driver.mvp.model.bean.request.RequestAddCard;
import org.zzy.driver.mvp.model.bean.request.RequestSellCapacity;
import org.zzy.driver.mvp.model.net.HttpCallBack;
import org.zzy.driver.mvp.model.net.HttpRequest;
import org.zzy.driver.mvp.model.net.RequestCenter;
import org.zzy.driver.utils.MD5Util;

/**
 * @function 业务API
 * Created by zhou on 2018/5/30.
 */

public class BusinessApi  extends BaseApi{

    private static BusinessApi api;

    public static BusinessApi getInstance(){
        if(api==null){
            synchronized (BusinessApi.class){
                if(api==null){
                    api=new BusinessApi();
                }
            }
        }
        return  api;
    }

    private BusinessApi(){

    }

    /**
     * 抢单接口
     * */
    public void acceptOrder(int driverId, int capacityApplyOrderId, int waybillGroupId,int support40GP, HttpCallBack callBack){
        HttpRequest request=new HttpRequest();
        request.addHeader("action", RequestCenter.WAYBILL_ACTION);
        request.addHeader("method", RequestCenter.ACCESSORDER_METHOD);
        request.putParams("driverId",driverId);
        request.putParams("capacityApplyOrderId",capacityApplyOrderId);
        request.putParams("waybillGroupId",waybillGroupId);
        request.putParams("support40GP", support40GP);
        doPost(request,callBack);
    }


    /**
     * 出售运力接口
     * */
    public void sellCapacity(RequestSellCapacity capacity,int driverId,HttpCallBack callBack){
        HttpRequest request=new HttpRequest();
        request.addHeader("action", RequestCenter.CAPACITY_ACTION);
        request.addHeader("method", RequestCenter.SELL_TRANSE_CAPACAITY_METHOD);
        request.putParams("price", capacity.getPrice());
        request.putParams("driverId", driverId);
        request.putParams("vehicleId", capacity.getVehicleId());
        request.putParams("vehiclecode", capacity.getVehiclecode());
        request.putParams("startRegionCode", capacity.getStartCode());
        request.putParams("endRegionCode", capacity.getEndCode());
        request.putParams("startTime",  TimeUtils.strToDate(capacity.getStartTime()));
        request.putParams("vehicleType", capacity.getVehicleTypeCode());
        request.putParams("suport40", capacity.getSuport40());
        doPost(request,callBack);
    }


    /**
     * 获取城市列表
     * */
    public void getCityList(HttpCallBack callBack){
        HttpRequest request=new HttpRequest();
        request.addHeader("action", RequestCenter.USER_ACTION);
        request.addHeader("method", RequestCenter.GET_CITY_LIST_METHOD);
        doPost(request,callBack);
    }

    ///////////////钱包接口////////////////////
    /**
     * 获取钱包信息
     * */
    public void getWalletInfo(int driverId,HttpCallBack callBack){
        HttpRequest request=new HttpRequest();
        request.addHeader("action", RequestCenter.WALLET_ACTION);
        request.addHeader("method", RequestCenter.GET_WALLETINFO_METHOD);
        request.putParams("driverId", driverId);
        doPost(request,callBack);
    }

    /***
     * 获取银行信息
     *
     */

    public void getBankRelative(String bankCardNum,HttpCallBack callBack){
        HttpRequest request=new HttpRequest();
        request.addHeader("action", RequestCenter.WALLET_ACTION);
        request.addHeader("method", RequestCenter.GET_BANKRELATIVE_METHOD);
        request.putParams("bankCardNum", bankCardNum);
        doPost(request,callBack);
    }

    /***
     *绑卡获取验证码接口
     */
    public void getBindSms(String phone,HttpCallBack callBack){
        HttpRequest request=new HttpRequest();
        request.addHeader("action", RequestCenter.WALLET_ACTION);
        request.addHeader("method", RequestCenter.GET_BINDSMS_METHOD);
        request.putParams("phone", phone);
        doPost(request,callBack);
    }

    /***
     * 添加银行卡接口
     */
    public void bindingBankCard(int driverId,RequestAddCard addcard, HttpCallBack callBack){
        HttpRequest request=new HttpRequest();
        request.addHeader("action", RequestCenter.WALLET_ACTION);
        request.addHeader("method", RequestCenter.BINDING_BANKCARD_METHOD);
        request.putParams("driverId", driverId);
        request.putParams("userName", addcard.getUserName());
        request.putParams("bankCardNum", addcard.getBankCardNum());
        request.putParams("bankCardName", addcard.getBankCardName());
        request.putParams("bankCardCode", addcard.getBankCardCode());
        request.putParams("verifyCode", addcard.getVerifyCode());
        doPost(request,callBack);
    }

    /***
     * 获取绑定银行卡
     */
    public void getBindingBankCard(int driverId, HttpCallBack callBack){
        HttpRequest request=new HttpRequest();
        request.addHeader("action", RequestCenter.WALLET_ACTION);
        request.addHeader("method", RequestCenter.GET_BINDINGBANKCARD_METHOD);
        request.putParams("driverId", driverId);
        doPost(request,callBack);
    }

    /***
     * 解绑银行卡
     */
    public void unBindingBankCard(int driverId,String payPassword, HttpCallBack callBack){
        HttpRequest request=new HttpRequest();
        request.addHeader("action", RequestCenter.WALLET_ACTION);
        request.addHeader("method", RequestCenter.UNBINDING_BANKCARD_METHOD);
        request.putParams("driverId", driverId);
        request.putParams("payPassword", MD5Util.md5Encode(MD5Util.md5Encode(payPassword)));
        doPost(request,callBack);
    }

    /***
     *获取设置密码验证码
     */
    public void getPaySms(String phone,HttpCallBack callBack){
        HttpRequest request=new HttpRequest();
        request.addHeader("action", RequestCenter.WALLET_ACTION);
        request.addHeader("method", RequestCenter.GET_PAYSMS_METHOD);
        request.putParams("phone", phone);
        doPost(request,callBack);
    }

    /**
     * 钱包验证支付密码接口
     * */
    public void checkPayPassword(String password,HttpCallBack callBack){
        HttpRequest request=new HttpRequest();
        request.addHeader("action", RequestCenter.WALLET_ACTION);
        request.addHeader("method",RequestCenter.CHECK_PAYPASSWORD_METHOD);
        request.putParams("payPassword",MD5Util.md5Encode(MD5Util.md5Encode(password)));
        doPost(request,callBack);
    }

    /**
     * 钱包 设置支付密码与重设支付密码接口
     * */
    public void setPayPassword(String idCard,String password,String verifyCode,HttpCallBack callBack){
        HttpRequest request=new HttpRequest();
        request.addHeader("action", RequestCenter.WALLET_ACTION);
        request.addHeader("method",RequestCenter.SET_PAYPASSWORD_METHOD);
        request.putParams("password",MD5Util.md5Encode(MD5Util.md5Encode(password)));
        request.putParams("idCard",idCard);
        request.putParams("verifyCode",verifyCode);
        doPost(request,callBack);
    }

    /**
     * 钱包 设置支付密码与重设支付密码接口
     * */
    public void getAccountBalanceList(int driverId,HttpCallBack callBack){
        HttpRequest request=new HttpRequest();
        request.addHeader("action", RequestCenter.WALLET_ACTION);
        request.addHeader("method",RequestCenter.GET_ACCOUNTBALANCELIST_METHOD);
        request.putParams("driverId",driverId);
        doPost(request,callBack);
    }

    /**
     * 钱包 设置支付密码与重设支付密码接口
     * */
    public void changePayPassword(String oldPassword,String newPassword,String verifyCode,HttpCallBack callBack){
        HttpRequest request=new HttpRequest();
        request.addHeader("action", RequestCenter.WALLET_ACTION);
        request.addHeader("method",RequestCenter.CHANGE_PAYPASSWORD_METHOD);
        request.putParams("oldPassword",MD5Util.md5Encode(MD5Util.md5Encode(oldPassword)));
        request.putParams("newPassword",MD5Util.md5Encode(MD5Util.md5Encode(newPassword)));
        request.putParams("verifyCode",verifyCode);
        doPost(request,callBack);
    }

    /**
     * 钱包 提现接口
     * */
    public void withdraw(int driverId,String amount,String payPassword,HttpCallBack callBack){
        HttpRequest request=new HttpRequest();
        request.addHeader("action", RequestCenter.WALLET_ACTION);
        request.addHeader("method",RequestCenter.WITHDRAW_METHOD);
        request.putParams("driverId",driverId);
        request.putParams("amount",amount);
        request.putParams("payPassword",MD5Util.md5Encode(MD5Util.md5Encode(payPassword)));
        doPost(request,callBack);
    }
    //////////////////////钱包接口完//////////////////////////


    /////////////////////车辆管理接口/////////////////////////

    /**
     *  获取车辆列表信息
     * */
    public void getVehicleList(int driverId,HttpCallBack callBack){
        HttpRequest request=new HttpRequest();
        request.addHeader("action", RequestCenter.VEHICLE_ACTION);
        request.addHeader("method",RequestCenter.GET_VEHICLELIST_METHOD);
        request.putParams("driverId",driverId);
        doPost(request,callBack);
    }

}
