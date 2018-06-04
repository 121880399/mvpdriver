package org.zzy.driver.mvp.ui.activity;

import com.zzy.quick.mvp.ui.BaseActivity;
import com.zzy.quick.router.Router;

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
        //判断是否登录，登录直接跳转主页面，没登录的要判断是否是第一次使用APP，如果第一次使用跳转到引导页面，否则跳转登录页面

            Observable.timer(2000, TimeUnit.MILLISECONDS)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<Long>() {
                        @Override
                        public void accept(Long aLong) throws Exception {
                            if (getPresenter().isLogin()) {
                                Router.newIntent(LoadingActivity.this)
                                        .to(MainActivity.class)
                                        .launch();
                            }else{
                                if(getPresenter().isFirstAccess()){
                                    Router.newIntent(LoadingActivity.this)
                                            .to(UserGuideActivity.class)
                                            .launch();
                                }else{
                                    Router.newIntent(LoadingActivity.this)
                                            .to(LoginActivity.class)
                                            .launch();
                                }
                            }
                            finish();
                        }
                    });
     }


    @Override
    public void showError(String msg) {

    }

}
