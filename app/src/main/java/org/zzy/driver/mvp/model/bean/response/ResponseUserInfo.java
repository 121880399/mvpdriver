package org.zzy.driver.mvp.model.bean.response;

import java.io.Serializable;

/**
 * Created by 邵鸿轩 on 2016/8/18.
 */
public class ResponseUserInfo  implements Serializable {
    private int id;//用户id
    private int driverId; //司机id
    private String login_name; //登录名
    private String real_name; //真实姓名
    private int userStatus; //用户状态
    private int driverStatus;//司机状态
    private String password;//密码
    private String id_card_code;//身份证号码
    private String id_card_front_url;//身份证正面照
    private String id_card_back_url;//身份证背面照
    private String driver_license_url;//驾驶证
    private String certificate_url;//从业资格证
    private String icon;
    private String phone;//手机号
    private String region_code;
    private String region_name;
    private int auth_type;//用户认证类型
    private int userType;//用户类型
    private int identStatus;//身份认证状态
    private int quaStatus;//资格认证状态
    private String identFailReson;//身份认证失败原因
    private String quaFailReson;//资质认证失败原因
    private String organization_name;//组织名称
    private int hasPayPassword;//是否设置了支付密码
    private int company_id;//公司id

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }

    public int getHasPayPassword() {
        return hasPayPassword;
    }

    public void setHasPayPassword(int hasPayPassword) {
        this.hasPayPassword = hasPayPassword;
    }

    public int getOrganization_id() {
        return organization_id;
    }

    public void setOrganization_id(int organization_id) {
        this.organization_id = organization_id;
    }

    private int organization_id;//组织id
    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getIdentFailReson() {
        return identFailReson;
    }

    public void setIdentFailReson(String identFailReson) {
        this.identFailReson = identFailReson;
    }

    public String getQuaFailReson() {
        return quaFailReson;
    }

    public void setQuaFailReson(String quaFailReson) {
        this.quaFailReson = quaFailReson;
    }

    public String getOrganization_name() {
        return organization_name;
    }

    public void setOrganization_name(String organization_name) {
        this.organization_name = organization_name;
    }

    public int getIdentStatus() {
        return identStatus;

    }

    public void setIdentStatus(int identStatus) {
        this.identStatus = identStatus;
    }

    public int getQuaStatus() {
        return quaStatus;
    }

    public void setQuaStatus(int quaStatus) {
        this.quaStatus = quaStatus;
    }
    public String getRegion_name() {
        return region_name;
    }

    public void setRegion_name(String region_name) {
        this.region_name = region_name;
    }

    public int getAuth_type() {
        return auth_type;
    }

    public void setAuth_type(int auth_type) {
        this.auth_type = auth_type;
    }

    public String getRegion_code() {
        return region_code;
    }

    public void setRegion_code(String region_code) {
        this.region_code = region_code;
    }

    public String getReal_name() {
        return real_name;
    }

    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }

    public String getId_card_code() {
        return id_card_code;
    }

    public void setId_card_code(String id_card_code) {
        this.id_card_code = id_card_code;
    }

    public String getId_card_front_url() {
        return id_card_front_url;
    }

    public void setId_card_front_url(String id_card_front_url) {
        this.id_card_front_url = id_card_front_url;
    }

    public String getId_card_back_url() {
        return id_card_back_url;
    }

    public void setId_card_back_url(String id_card_back_url) {
        this.id_card_back_url = id_card_back_url;
    }

    public String getDriver_license_url() {
        return driver_license_url;
    }

    public void setDriver_license_url(String driver_license_url) {
        this.driver_license_url = driver_license_url;
    }

    public String getCertificate_url() {
        return certificate_url;
    }

    public void setCertificate_url(String certificate_url) {
        this.certificate_url = certificate_url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin_name() {
        return login_name;
    }

    public void setLogin_name(String login_name) {
        this.login_name = login_name;
    }

    public int getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(int userStatus) {
        this.userStatus = userStatus;
    }

    public int getDriverStatus() {
        return driverStatus;
    }

    public void setDriverStatus(int driverStatus) {
        this.driverStatus = driverStatus;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }
}
