package org.zzy.driver.utils;

import org.zzy.driver.mvp.model.bean.custom.CitySortData;

import java.util.Comparator;

/**
 * @author king
 */
public class PinyinComparator implements Comparator<CitySortData> {

    public int compare(CitySortData o1, CitySortData o2) {
        if (o1.getSortLetters().equals("@")
                || o2.getSortLetters().equals("#")) {
            return -1;
        } else if (o1.getSortLetters().equals("#")
                || o2.getSortLetters().equals("@")) {
            return 1;
        } else {
            return o1.getSortLetters().compareTo(o2.getSortLetters());
        }
    }

}
