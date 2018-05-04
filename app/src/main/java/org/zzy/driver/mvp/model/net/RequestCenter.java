package org.zzy.driver.mvp.model.net;

/**
 * Created by zhou on 2018/5/4.
 */

public class RequestCenter {

    /**
     * 注意除了登录 注册 修改密码 都要在RequestData中添加userID和token字段
     */
    //==============AppUserAction====================//
    public static final String USER_ACTION = "user";//用户相关
    public static final String LOGIN_METHOD = "login";//用户登录
    public final static String DRIVER_REGIST_METHOD = "register";//用户注册
    public final static String RESET_PASSWORD_METHOD = "resetPassword";//重置密码
    public final static String SET_DRIVER_INFO_METHOD = "improvedUserInfo";//设置个人信息
    public final static String GET_CITY_LIST_METHOD = "getCityList";//获取起点城市列表
    public final static String GET_USERINFO_METHOD = "getUserInfo";//获取用户信息
    public final static String CHANGEAVATAR_METHOD = "changeAvatar";//修改头像
    public final static String UPDATEPHONE_METHOD = "updatePhone";//更换手机号
    public final static String GETSMS_METHOD="getSms";//获取验证码
    public final static String RESET_REGION_METHOD="resetRegion";//修改基地
    //==============AppUserAction====================//

    //==================PathAction===================//
    public final static String PATH_ACTION = "path";//路径相关
    public final static String GET_PATH_LIST_METHOD = "getPathList";
    //==================PathAction===================//

    //==================AppUserLocationAction===================//
    public final static String LOCATION_ACTION = "position";//上传定位信息
    public final static String UPLOAD_GPS_METHOD = "positionUpdate";
    //==================AppUserLocationAction===================//

    //======================AppPoiAction=======================//
    public static final String POI_ACTION = "poi";//定位服务相关
    public static final String NEAR_SERVICE_METHOD = "getNearServiceStation";
    //======================AppPoiAction=======================//

    //======================AppVehicleAction===================//
    public final static String VEHICLE_ACTION = "vehicle";//车辆相关
    public final static String ADD_CAR_METHOD = "addVehicle";//添加车辆
    public final static String GET_CAR_INFO_METHOD = "getCarBasicData";//车辆信息--车辆类型，拖挂类型，箱型箱类
    public final static String GET_BINDVEHICLE_METHOD = "getBindVehicle";//获取绑定车辆信息
    public final static String GET_VEHICLELIST_METHOD = "getVehicleList";//获取车辆列表
    public final static String BIND_VEHICLE_METHOD = "bindVehicle";//绑定车辆，设置当前车辆
    public final static String DELETE_VEHICLE_METHOD = "deleteVehicle";//删除车辆
    //======================AppVehicleAction===================//

    //===============AppTransportCapacityAction================//
    public final static String CAPACITY_ACTION = "transportCapacity";
    public final static String SELL_TRANSE_CAPACAITY_METHOD = "sellTransportCapacity";//出售运力
    public final static String GET_CAPACITYLIST_METHOD = "getTransportCapacityList";//获取运力列表
    public final static String GET_CAPACITY_DETAILS_METHOD = "getTransportCapcityDetail";//出售运力详情
    public final static String UPDATE_PRICE_METHOD = "updatePrice";//更改运力价格
    public final static String CANCLE_CAPACITY_METHOD = "cancle";//取消运力
    //===============AppTransportCapacityAction================//

    //=====================AppBillAction=======================//
    public final static String WAYBILL_ACTION = "waybill";
    public final static String GET_BILL_LIST_METHOD = "getWaybillList";//获取运单列表
    public final static String GET_WAYBILLDETAIL_METHED = "getWaybillDetail";//查询运单详情
    public final static String SCAN_CODE_METHOD = "scanCode";//装载扫描/抵达扫描
    public final static String ACCESSORDER_METHOD = "acceptWaybill";//抢订单
    public final static String FAULTALARM_METHOD = "faultAlarm";//故障报警
    public final static String CONFIRMTASK_METHOD="confirmTask";//确认任务
    //=====================AppBillAction=======================//

    //=====================SysCheckVersionAction=======================//
    public final static String VERSION_ACTION="checkVersion";
    public final static String CHECKVERSION = "get_newVersion"; //检查新版本
//=====================SysCheckVersionAction=======================//


    public final static String GET_TRANSE_LINE_LIST_URL = "transeLine";//获取路线列表
    public final static String GET_TRANSE_LINE_LIST_METHED = "getTranseLine";
    public final static String ADD_FAVOUR_ROUTE_URL = "personCenter";//添加偏爱路线
    public final static String ADD_FAVOUR_ROUTE_METHED = "addFavourRotes";
    public final static String DELETE_FAVOUR_ROUTE_URL = "personCenter";//删除偏爱路线
    public final static String DELETE_FAVOUR_ROUTE_METHED = "deleteFavourRotes";

    //==========================wallet========================================//
    public final static String WALLET_ACTION="wallet";
    public final static String BINDING_BANKCARD_METHOD="bindingBankCard";//绑定银行卡
    public final static String GET_WALLETINFO_METHOD="getWalletInfo";//获取钱包信息
    public final static String GET_ACCOUNTBALANCELIST_METHOD="getAccountBalanceList";//查询收入明细列表
    public final static String GET_ACCOUNTBALANCE_METHOD="getAccountBalance";//查询收入明细
    public final static String SET_PAYPASSWORD_METHOD="setPayPassword";//设置支付密码
    public final static String CHANGE_PAYPASSWORD_METHOD="changePayPassword";//更改支付密码
    public final static String CHECK_IDCARD_METHOD="checkIdCard";//验证身份证
    public final static String GET_BINDINGBANKCARD_METHOD="getBindingBankCard";//获取绑定银行卡
    public final static String GET_BANKRELATIVE_METHOD="getBankRelative";//获取银行信息
    public final static String GET_BINDSMS_METHOD="getBindSms";//绑卡获取验证码
    public final static String GET_PAYSMS_METHOD="getPaySms";//绑卡获取验证码
    public final static String UNBINDING_BANKCARD_METHOD="unBindingBankCard";//绑卡获取验证码
    public final static String CHECK_PAYPASSWORD_METHOD="checkPayPassword";//验证支付密码
    public final static String WITHDRAW_METHOD="withdraw";//提现
}
