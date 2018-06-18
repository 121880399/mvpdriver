package org.zzy.driver.mvp.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.TextView;

import com.zzy.quick.mvp.ui.BaseActivity;
import com.zzy.quick.utils.StatusBarUtils;

import org.zzy.driver.R;
import org.zzy.driver.mvp.ui.fragment.FindCargoFragment;
import org.zzy.driver.mvp.ui.fragment.PersonCenterFragment;
import org.zzy.driver.mvp.ui.fragment.SellCapacityFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.tv_findCargo)
    TextView tvFindCargo;
    @BindView(R.id.tv_sellCapacity)
    TextView tvSellCapacity;
    @BindView(R.id.tv_personCenter)
    TextView tvPersonCenter;
    private Fragment findCargoFragment,sellCapacityFragment, personCenterFragment;
    private FragmentManager mFragmentManager;
    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        super.initView();
        StatusBarUtils.setImage(this);
    }

    @Override
    public void initData() {
        mFragmentManager=getSupportFragmentManager();
        personCenterFragment=new PersonCenterFragment();
        sellCapacityFragment=new SellCapacityFragment();
        findCargoFragment=new FindCargoFragment();
        tvFindCargo.performClick();
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public Object newPresenter() {
        return null;
    }


    /**
     * @function 点击找货按钮
     * */
    @OnClick(R.id.tv_findCargo)
    public void clickFindCargo(){
        switchView(tvFindCargo,findCargoFragment);
    }


    /**
     * @function 点击出售运力按钮
     * */
    @OnClick(R.id.tv_sellCapacity)
    public void clickSellCapacity(){
        switchView(tvSellCapacity,sellCapacityFragment);
    }

    /**
     * @function 点击个人中心按钮
     */
    @OnClick(R.id.tv_personCenter)
    public void clickPersonCenter() {
        switchView(tvPersonCenter,personCenterFragment);
    }

    /**
     * 点击底部bar后切换界面
     * */
    private void switchView(TextView textView,Fragment fragment){
        FragmentTransaction transaction=mFragmentManager.beginTransaction();
        setSelected();
        textView.setSelected(true);
        transaction.replace(R.id.fl_content,fragment);
        transaction.commitAllowingStateLoss();
    }

    //重置所有文本的选中状态
    private void setSelected() {
        tvFindCargo.setSelected(false);
        tvSellCapacity.setSelected(false);
        tvPersonCenter.setSelected(false);
    }

    @Override
    public void onBackPressed() {
        dealAppBack();
    }
}
