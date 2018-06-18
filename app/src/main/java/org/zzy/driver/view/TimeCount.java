package org.zzy.driver.view;

import android.os.CountDownTimer;


/**
 * 计时器
 *
 * @author xby
 */
public class TimeCount extends CountDownTimer {
    public CountDownTimerListener countDownTimerListener;

    public void setCountDownTimerListener(CountDownTimerListener countDownTimerListener) {
        this.countDownTimerListener = countDownTimerListener;
    }
    public interface CountDownTimerListener{
        void onFinish();
        void onTick(int untilFinished);
    }

    /**
     * 两个参数均以毫秒为单位记
     * @param millisInFuture
     * @param countDownInterval
     */
    public TimeCount(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);// 参数依次为总时长,和计时的时间间隔

    }

    @Override
    public void onFinish() {// 计时完毕时触发
        if(countDownTimerListener!=null){
            countDownTimerListener.onFinish();
        }
    }

    @Override
    public void onTick(long millisUntilFinished) {// 计时过程显示
        if(countDownTimerListener!=null){
            countDownTimerListener.onTick((int) (millisUntilFinished/1000));
        }

    }
}