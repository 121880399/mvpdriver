package org.zzy.driver.mvp.model.bean.response;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by 周正一 on 2016/11/7.
 */

public class ResponseIncome implements Parcelable {

    /**
     * accountting_type : T111
     * before_trade_amount : 0.0
     * remark : 小胖扎
     * customer_account : C0000000081
     * after_trade_amount : 500.0
     * id : 1
     * trade_order_no : BON2016110713515441200099
     * create_date : 1478499257000
     * update_time : 1478499261000
     * trade_date : 1478499216000
     * customer_account_id : 120
     * seq : 1
     * trade_amount : 500.0
     */

    private String accountting_type;
    private double before_trade_amount;
    private String remark;
    private String customer_account;
    private double after_trade_amount;
    private int id;
    private String trade_order_no;
    private long create_date;
    private long update_time;
    private long trade_date;
    private int customer_account_id;
    private int seq;
    private double trade_amount;

    public String getAccountting_type() {
        return accountting_type;
    }

    public void setAccountting_type(String accountting_type) {
        this.accountting_type = accountting_type;
    }

    public double getBefore_trade_amount() {
        return before_trade_amount;
    }

    public void setBefore_trade_amount(double before_trade_amount) {
        this.before_trade_amount = before_trade_amount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCustomer_account() {
        return customer_account;
    }

    public void setCustomer_account(String customer_account) {
        this.customer_account = customer_account;
    }

    public double getAfter_trade_amount() {
        return after_trade_amount;
    }

    public void setAfter_trade_amount(double after_trade_amount) {
        this.after_trade_amount = after_trade_amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTrade_order_no() {
        return trade_order_no;
    }

    public void setTrade_order_no(String trade_order_no) {
        this.trade_order_no = trade_order_no;
    }

    public long getCreate_date() {
        return create_date;
    }

    public void setCreate_date(long create_date) {
        this.create_date = create_date;
    }

    public long getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(long update_time) {
        this.update_time = update_time;
    }

    public long getTrade_date() {
        return trade_date;
    }

    public void setTrade_date(long trade_date) {
        this.trade_date = trade_date;
    }

    public int getCustomer_account_id() {
        return customer_account_id;
    }

    public void setCustomer_account_id(int customer_account_id) {
        this.customer_account_id = customer_account_id;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public double getTrade_amount() {
        return trade_amount;
    }

    public void setTrade_amount(double trade_amount) {
        this.trade_amount = trade_amount;
    }

    @Override
    public String toString() {
        return "ResponseIncome{" +
                "accountting_type='" + accountting_type + '\'' +
                ", before_trade_amount=" + before_trade_amount +
                ", remark='" + remark + '\'' +
                ", customer_account='" + customer_account + '\'' +
                ", after_trade_amount=" + after_trade_amount +
                ", id=" + id +
                ", trade_order_no='" + trade_order_no + '\'' +
                ", create_date=" + create_date +
                ", update_time=" + update_time +
                ", trade_date=" + trade_date +
                ", customer_account_id=" + customer_account_id +
                ", seq=" + seq +
                ", trade_amount=" + trade_amount +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.accountting_type);
        dest.writeDouble(this.before_trade_amount);
        dest.writeString(this.remark);
        dest.writeString(this.customer_account);
        dest.writeDouble(this.after_trade_amount);
        dest.writeInt(this.id);
        dest.writeString(this.trade_order_no);
        dest.writeLong(this.create_date);
        dest.writeLong(this.update_time);
        dest.writeLong(this.trade_date);
        dest.writeInt(this.customer_account_id);
        dest.writeInt(this.seq);
        dest.writeDouble(this.trade_amount);
    }

    public ResponseIncome() {
    }

    protected ResponseIncome(Parcel in) {
        this.accountting_type = in.readString();
        this.before_trade_amount = in.readDouble();
        this.remark = in.readString();
        this.customer_account = in.readString();
        this.after_trade_amount = in.readDouble();
        this.id = in.readInt();
        this.trade_order_no = in.readString();
        this.create_date = in.readLong();
        this.update_time = in.readLong();
        this.trade_date = in.readLong();
        this.customer_account_id = in.readInt();
        this.seq = in.readInt();
        this.trade_amount = in.readDouble();
    }

    public static final Parcelable.Creator<ResponseIncome> CREATOR = new Parcelable.Creator<ResponseIncome>() {
        @Override
        public ResponseIncome createFromParcel(Parcel source) {
            return new ResponseIncome(source);
        }

        @Override
        public ResponseIncome[] newArray(int size) {
            return new ResponseIncome[size];
        }
    };
}
