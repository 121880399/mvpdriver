package org.zzy.driver.common;

/**
 * Created by 邵鸿轩 on 2016/7/8.
 */
public enum UserAuthTypeEnunm {
    /**
     * '1 认证承运商司机 2 签约承运商司机 3 注册用户 4 实名用户
     */
    AUTHED("认证承运商司机", 1), SIGNED("签约承运商司机", 2), REGISTED("注册用户", 3),REALNAMED("实名用户",4);
    // 成员变量
    private String name;
    private int index;
    // 构造方法
    private UserAuthTypeEnunm(String name, int index) {
        this.name = name;
        this.index = index;
    }

    // 普通方法
    public static String getName(int index) {
        for (UserAuthTypeEnunm userAuthTypeEnunm : UserAuthTypeEnunm.values()) {
            if (userAuthTypeEnunm.getIndex() == index) {
                return userAuthTypeEnunm.name;
            }
        }
        return null;
    }

    // get set 方法
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
