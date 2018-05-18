package org.zzy.driver.mvp.model.bean.response;

/**
 * Created by 周正一 on 2016/9/28.
 */
public class ResponseUpdate {

    /**
     * code : 2
     * createTime : 1475040668000
     * createUserId : 1
     * id : 1
     * isForce : 0
     * name : 1.1.1
     * platform : 0
     * remark : 测试更新
     * status : 0
     * updateContent : 修复了很多bug
     * updateTime : 1475040674000
     * updateUserId : 1
     * url : http: //www.baidu.com
     */
        private String code;
        private long createTime;
        private int createUserId;
        private int id;
        private int isForce;
        private String name;
        private int platform;
        private String remark;
        private int status;
        private String updateContent;
        private long updateTime;
        private int updateUserId;
        private String url;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getCreateUserId() {
            return createUserId;
        }

        public void setCreateUserId(int createUserId) {
            this.createUserId = createUserId;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIsForce() {
            return isForce;
        }

        public void setIsForce(int isForce) {
            this.isForce = isForce;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getPlatform() {
            return platform;
        }

        public void setPlatform(int platform) {
            this.platform = platform;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getUpdateContent() {
            return updateContent;
        }

        public void setUpdateContent(String updateContent) {
            this.updateContent = updateContent;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }

        public int getUpdateUserId() {
            return updateUserId;
        }

        public void setUpdateUserId(int updateUserId) {
            this.updateUserId = updateUserId;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
}
