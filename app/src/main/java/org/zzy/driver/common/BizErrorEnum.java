package org.zzy.driver.common;

/**
 * 项目名称: PlusOneLivePush
 * 创建人: 周正一
 * 创建时间：2017/8/3
 */

public enum  BizErrorEnum {
    MESSAGE_ERROR("1000001");//网易返回消息异常

    private String code;

    BizErrorEnum(String code) {
        this.code = code;
    }


    public static boolean isBizError(String code){
        for (BizErrorEnum bizError: BizErrorEnum.values()) {
            if(bizError.getCode().equals(code)){
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
}
