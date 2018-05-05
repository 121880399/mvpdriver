package org.zzy.driver.mvp.model.net;

/**
 * Created by zhou on 2018/4/9.
 */

public class ResponseData {
    private String message;
    private Result result;
    private String status;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public  class Result {
        /**
         * data : "{\"token\":\"90b164495ccae2b46821eee91c8bb79a0d3b184452932d5c2beab80d75e3ecf0\"}"
         * pageCount : 0
         * recordCount : 0
         */
        private int pageCount;
        private int currentPage;
        private String data;

        public int getPageCount() {
            return pageCount;
        }

        public void setPageCount(int pageCount) {
            this.pageCount = pageCount;
        }

        public int getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(int currentPage) {
            this.currentPage = currentPage;
        }

        public String getData() {

            return data;
        }

        public void setData(String data) {
            this.data = data;
        }
    }
}
