package org.zzy.driver.mvp.ui;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import org.zzy.driver.R;
import org.zzy.driver.mvp.presenter.adapter.FragmentAdapter;
import org.zzy.driver.mvp.ui.fragment.UserGuideFragment1;
import org.zzy.driver.mvp.ui.fragment.UserGuideFragment2;
import org.zzy.driver.mvp.ui.fragment.UserGuideFragment3;
import org.zzy.driver.mvp.ui.fragment.UserGuideFragment4;

import java.util.ArrayList;

/**
 * Created by zhou on 2018/4/4.
 */

public class UserGuideActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private FragmentAdapter mFragmentAdapter;
    private ArrayList<Fragment> mFragmentList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userguide);
        mViewPager= (ViewPager) findViewById(R.id.vp_guidepager);
        mFragmentList = new ArrayList<>();
        Fragment fragment1 = new UserGuideFragment1();
        Fragment fragment2 = new UserGuideFragment2();
        Fragment fragment3 = new UserGuideFragment3();
        Fragment fragment4 = new UserGuideFragment4();
        mFragmentList.add(fragment1);
        mFragmentList.add(fragment2);
        mFragmentList.add(fragment3);
        mFragmentList.add(fragment4);
        mFragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), mFragmentList);
        mViewPager.setAdapter(mFragmentAdapter);
    }
}
