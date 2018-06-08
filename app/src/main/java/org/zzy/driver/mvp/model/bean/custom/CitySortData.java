package org.zzy.driver.mvp.model.bean.custom;

/**
 * Created by zhou on 2018/6/8.
 */

public class CitySortData  {

    private String name;   //显示的数据
    private String sortLetters;  //显示数据拼音的首字母
    private String nameCode;//编码

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSortLetters() {
        return sortLetters;
    }

    public void setSortLetters(String sortLetters) {
        this.sortLetters = sortLetters;
    }

    public String getNameCode() {
        return nameCode;
    }

    public void setNameCode(String nameCode) {
        this.nameCode = nameCode;
    }
}
