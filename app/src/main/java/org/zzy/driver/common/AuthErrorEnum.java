package org.zzy.driver.common;

/**
 * @function 授权错误枚举
 * 项目名称: mvpDriver
 * 创建人: 周正一
 * 创建时间：2017/8/3
 */

public enum AuthErrorEnum {
    USERNULL_ERROR("10009");//用户不存在

    private String code;

    AuthErrorEnum(String code) {
        this.code = code;
    }

    public static boolean isAuthError(String code){
        for (AuthErrorEnum authError: AuthErrorEnum.values()) {
            if(authError.getCode().equals(code)) {
                return true;
            }else{
                return false;
            }
        }
        return false;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
