package org.zzy.driver.common;

/**
 * Created by zhou on 2018/4/4.
 */

public class CommonValue {

    //用户信息
    public static final String USERINFO="userInfo";
    //车辆信息
    public static final String VEHICLEINFO="vehicleInfo";

    //标识是否是第一次登录
    public static final String FIRSTACCESS = "firstAccess";

    /**
     * 推送的cid
     */
    public static final String PUSH_CID = "PUSH_CID";

    public static final String USERID="userId";

    /**
     * token
     */
    public static final String TOKENCODE = "token";

    /**
     * 成功标识
     */
    public static final String SUCCESS_STATUS = "10000";


    /**
     * 选择城市所用标识
     * **/
    public static final int CHOOSE_START_CITY=0; //起始城市
    public static final int CHOOSE_END_CITY=1; //结束城市
    public static final int CHOOSE_BASE=1; //选择驻地城市

    /**
     * 用户类型 userType
     * */
    public static final int SIGN_CARRIER=1;//签约承运商
    public static final int AUTHENTICATION_CARRIER=2;//认证承运商

    /**
     * 车辆状态
     * */
    public static final int AUTHENTICATION_VEHICLE=2;//认证车辆

    /**
     * 司机身份认证状态 及 资格认证状态
     * **/
    public static final int UNCOMMITTED=0;//未提交
    public static final int WAITCONFIRM=1;//待审核
    public static final int CHECKPASS=2;//审核通过
    public static final int DISQUALIFICATION=3;//审核不通过



}
