package org.zzy.driver.mvp.model.bean.response;

/**
 * Created by zhou on 2018/4/12.
 */

public class ResponsePrice {

    /**
     * price : 3000
     * departureTime : 1530806400000
     */

    //自建参数，用来表示当前的位置
    private int id;
    private String price;
    private long departureTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public long getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(long departureTime) {
        this.departureTime = departureTime;
    }
}
