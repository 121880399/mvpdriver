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
public class UserGuideFragment1 extends Fragment{
    private ImageView mPop1;
    private ImageView mPop2;
    private ImageView mPop3;
    private ImageView mCloud1;
    private ImageView mCloud2;
    private Animation pop1Anim = null;
    private Animation pop2Anim = null;
    private Animation pop3Anim = null;
    private Animation cloud1Anim = null;
    private Animation cloud2Anim = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_userguide1, null);
        mPop1= (ImageView) view.findViewById(R.id.iv_pop1);
        mPop2= (ImageView) view.findViewById(R.id.iv_pop2);
        mPop3= (ImageView) view.findViewById(R.id.iv_pop3);
        mCloud1= (ImageView) view.findViewById(R.id.iv_cloud1);
        mCloud2= (ImageView) view.findViewById(R.id.iv_cloud2);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        pop1Anim = AnimationUtils.loadAnimation(getActivity(), R.anim.anim_guide_pop);
        pop2Anim = AnimationUtils.loadAnimation(getActivity(), R.anim.anim_guide_pop);
        pop3Anim = AnimationUtils.loadAnimation(getActivity(), R.anim.anim_guide_pop);
        cloud1Anim = AnimationUtils.loadAnimation(getActivity(), R.anim.anim_guide_cloud_left);
        cloud2Anim = AnimationUtils.loadAnimation(getActivity(), R.anim.anim_guide_cloud_right);
        mCloud1.startAnimation(cloud1Anim);
        mCloud2.startAnimation(cloud2Anim);
        mPop1.startAnimation(pop1Anim);
        mPop1.setVisibility(View.VISIBLE);
        mPop2.startAnimation(pop2Anim);
        mPop2.setVisibility(View.VISIBLE);
        mPop3.startAnimation(pop3Anim);
        mPop3.setVisibility(View.VISIBLE);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if (mCloud1 != null && mCloud2 != null && mPop1 != null && mPop2 != null && mPop3 != null) {
                    mCloud1.startAnimation(cloud1Anim);
                    mCloud2.startAnimation(cloud2Anim);
                    mPop1.startAnimation(pop1Anim);
                    mPop1.setVisibility(View.VISIBLE);
                    mPop2.startAnimation(pop2Anim);
                    mPop2.setVisibility(View.VISIBLE);
                    mPop3.startAnimation(pop3Anim);
                    mPop3.setVisibility(View.VISIBLE);
//                }
            }
        } else {
            if (mPop1 != null && mPop2 != null && mPop3 != null) {
                mPop1.setVisibility(View.GONE);
                mPop2.setVisibility(View.GONE);
                mPop3.setVisibility(View.GONE);
            }
            if (pop1Anim != null && pop2Anim != null && pop3Anim != null && cloud1Anim != null && cloud2Anim != null) {
                pop1Anim.cancel();
                pop2Anim.cancel();
                pop3Anim.cancel();
                cloud1Anim.cancel();
                cloud2Anim.cancel();
            }
        }
    }



}
