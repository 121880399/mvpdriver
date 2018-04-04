package org.zzy.driver.mvp.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import org.zzy.driver.R;


/**
 * Created by admin on 2016/3/29.
 */
public class UserGuideFragment3 extends Fragment {
    private ImageView mCar1;
    private ImageView mCar2;
    private ImageView mCar3;
    private ImageView mCar4;
    private ImageView mCloud1;
    private ImageView mCloud2;
    private ImageView mCloud3;
    Animation car1Anim = null;
    Animation car2Anim = null;
    Animation car3Anim = null;
    Animation car4Anim = null;
    Animation cloud1Anim = null;
    Animation cloud2Anim = null;
    Animation cloud3Anim = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_userguide3, null);
        mCar1= (ImageView) view.findViewById(R.id.iv_car1);
        mCar2= (ImageView) view.findViewById(R.id.iv_car2);
        mCar3= (ImageView) view.findViewById(R.id.iv_car3);
        mCar4= (ImageView) view.findViewById(R.id.iv_car4);
        mCloud1= (ImageView) view.findViewById(R.id.iv_cloud1);
        mCloud2= (ImageView) view.findViewById(R.id.iv_cloud2);
        mCloud3= (ImageView) view.findViewById(R.id.iv_cloud3);
        return view;
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (isVisibleToUser) {
            car1Anim = AnimationUtils.loadAnimation(getActivity(), R.anim.anim_guide3_carleft);
            car2Anim = AnimationUtils.loadAnimation(getActivity(), R.anim.anim_guide3_carleft);
            car3Anim = AnimationUtils.loadAnimation(getActivity(), R.anim.anim_guide3_carright);
            car4Anim = AnimationUtils.loadAnimation(getActivity(), R.anim.anim_guide3_carleft);
            cloud1Anim = AnimationUtils.loadAnimation(getActivity(), R.anim.anim_guide_cloud_left);
            cloud2Anim = AnimationUtils.loadAnimation(getActivity(), R.anim.anim_guide_cloud_right);
            cloud3Anim = AnimationUtils.loadAnimation(getActivity(), R.anim.anim_guide_cloud_right);
            mCar1.startAnimation(car1Anim);
            mCar1.setVisibility(View.VISIBLE);
            mCar2.startAnimation(car2Anim);
            mCar2.setVisibility(View.VISIBLE);
            mCar3.startAnimation(car3Anim);
            mCar3.setVisibility(View.VISIBLE);
            mCar4.startAnimation(car4Anim);
            mCar4.setVisibility(View.VISIBLE);


            mCloud1.startAnimation(cloud1Anim);
            mCloud2.startAnimation(cloud2Anim);
            mCloud3.startAnimation(cloud3Anim);
        } else {
            if (mCar1 != null) {
                mCar1.setVisibility(View.GONE);
            }
            if (cloud1Anim != null) {
                cloud1Anim.cancel();
            }
            if (cloud2Anim != null) {
                cloud2Anim.cancel();
            }
            if (cloud3Anim != null) {
                cloud3Anim.cancel();
            }
            if (car1Anim != null) {
                car1Anim.cancel();
            }
            if (car2Anim != null) {
                car2Anim.cancel();
            }
            if (car3Anim != null) {
                car3Anim.cancel();
            }
            if (car4Anim != null) {
                car4Anim.cancel();
            }

        }
    }


}
