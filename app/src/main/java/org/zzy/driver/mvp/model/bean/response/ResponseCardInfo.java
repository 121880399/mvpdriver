package org.zzy.driver.mvp.model.bean.response;

/**
 * Created by 周正一 on 2016/10/28.
 */

public class ResponseCardInfo {


    /**
     * accountTypeCode : 储蓄卡
     * bcarBin : 621559
     * bcarLen : 19
     * bcarName : 福农灵通卡
     * code : 102100099996
     * id : 3736
     * name : 中国工商银行
     * status : 1
     */
        private String accountTypeCode;
        private String bcarBin;
        private String bcarLen;
        private String bcarName;
        private String code;
        private int id;
        private String name;//银行名称
        private int status;

        private String bankCardNum;//银行卡号
        private String userName;//用户名

        public String getBankCardNum() {
            return bankCardNum;
        }

        public void setBankCardNum(String bankCardNum) {
            this.bankCardNum = bankCardNum;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

    public String getAccountTypeCode() {
            return accountTypeCode;
        }

        public void setAccountTypeCode(String accountTypeCode) {
            this.accountTypeCode = accountTypeCode;
        }

        public String getBcarBin() {
            return bcarBin;
        }

        public void setBcarBin(String bcarBin) {
            this.bcarBin = bcarBin;
        }

        public String getBcarLen() {
            return bcarLen;
        }

        public void setBcarLen(String bcarLen) {
            this.bcarLen = bcarLen;
        }

        public String getBcarName() {
            return bcarName;
        }

        public void setBcarName(String bcarName) {
            this.bcarName = bcarName;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

    @Override
    public String toString() {
        return "ResponseCardInfo{" +
                "accountTypeCode='" + accountTypeCode + '\'' +
                ", bcarBin='" + bcarBin + '\'' +
                ", bcarLen='" + bcarLen + '\'' +
                ", bcarName='" + bcarName + '\'' +
                ", code='" + code + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", bankCardNum='" + bankCardNum + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
