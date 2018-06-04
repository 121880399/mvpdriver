package org.zzy.driver.common;

/**
 * Created by zhou on 2018/4/4.
 */

public class AppConfig {

    //测试地址
    public static final String TEST = "http://192.168.1.220:8084";

    //调试地址
    public static final String SHX = "http://192.168.1.127:8080";

    //外网地址
    public static final String RELEASE="http://www.unitransdata.com:8088";

    public static final String DEMONSTRATION="http://39.106.250.181:8084/";

    //总地址
    public static final String BASEURL = RELEASE;

    //服务条款地址
    public static String SERVICEITEM = RELEASE+"/apprest/serviceitem.jsp";

    //图片库地址
    public static final String IMAGE_URL="http://192.168.1.220:8888";

    //电话中心
    public static final String CALLCENTER = "4009006667";

    //当前版本
    public  static final String VERSION_1_1_1="0.0.0.1";
}
