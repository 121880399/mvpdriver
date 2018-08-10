package org.zzy.driver.mvp.ui.activity;

import com.zzy.quick.mvp.ui.BaseActivity;
import com.zzy.quick.router.Router;
import com.zzy.quick.utils.ToastUtils;

import org.zzy.driver.R;
import org.zzy.driver.mvp.presenter.LoadingPresenter;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

/**
 * Created by zhou on 2018/4/4.
 */

public class LoadingActivity extends BaseActivity<LoadingPresenter> {


    @Override
    public LoadingPresenter newPresenter() {
        return new LoadingPresenter();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_loading;
    }

    @Override
    public void initData() {
        //判断是否登录，如果登录还需要再此获取用户信息，更新SP.因为在多平台情况下，有可能web端修改了数据，
        // APP端没修改，就只能在登录的时候刷新用户修改的数据，保证web端与APP端的数据一致性.
        // 没登录的要判断是否是第一次使用APP，如果第一次使用跳转到引导页面，否则跳转登录页面

            Observable.timer(2000, TimeUnit.MILLISECONDS)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<Long>() {
                        @Override
                        public void accept(Long aLong) throws Exception {
                            if (getPresenter().isLogin()) {
                                getPresenter().getUserInfo();
                            }else{
                                if(getPresenter().isFirstAccess()){
                                    getPresenter().setAlreadyAccess();
                                    Router.newIntent(LoadingActivity.this)
                                            .to(UserGuideActivity.class)
                                            .launch();
                                }else{
                                    Router.newIntent(LoadingActivity.this)
                                            .to(LoginActivity.class)
                                            .launch();
                                }
                            }
                        }
                    });
     }


    @Override
    public void showError(String msg) {

    }


    public void goMain(){
        Router.newIntent(this)
                .to(MainActivity.class)
                .launch();
        finish();
    }


    /**
     * 获取用户信息失败
     * */
    public void getUserInfoFaild() {
        ToastUtils.showShort("用户信息已过期请重新登录");
        Router.newIntent(this)
                .to(LoginActivity.class)
                .launch();
    }
}
