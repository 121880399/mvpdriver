package org.zzy.driver.common;

/**
 * 项目名称: PlusOneLivePush
 * 创建人: 周正一
 * 创建时间：2017/8/3
 */

public enum AuthErrorEnum {
    USERORPASSWORD_ERROR("1000002"),//用户名或密码错误
    USERNULL_ERROR("1000003"),//用户不存在
    USERALREADY_ERROR("1000004");//用户已经存在


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
