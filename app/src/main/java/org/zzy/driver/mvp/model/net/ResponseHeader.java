package org.zzy.driver.mvp.model.net;

/**
 * Created by zhou on 2018/4/9.
 */

public class ResponseHeader {
    private String device;
    private int encrpy;
    private String platform;
    private String version;
    private int zip;

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public int getEncrpy() {
        return encrpy;
    }

    public void setEncrpy(int encrpy) {
        this.encrpy = encrpy;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }
}
