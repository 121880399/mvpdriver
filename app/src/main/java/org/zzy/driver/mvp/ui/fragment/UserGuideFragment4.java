package org.zzy.driver.mvp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import org.zzy.driver.R;
import org.zzy.driver.mvp.ui.LoginActivity;


/**
 * Created by admin on 2016/3/29.
 */
public class UserGuideFragment4 extends Fragment {
    private ImageView mStart;
    private ImageView mPop1;
    private ImageView mPop2;
    private ImageView mPop3;
    private ImageView mPop4;
    private ImageView mCloud1;
    private ImageView mCloud2;
    private ImageView mCloud3;
    Animation pop1Anim = null;
    Animation pop2Anim = null;
    Animation pop3Anim = null;
    Animation pop4Anim = null;
    Animation cloud1Anim = null;
    Animation cloud2Anim = null;
    Animation cloud3Anim = null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_userguide4, null);
        mStart= (ImageView) view.findViewById(R.id.iv_start);
        mPop1= (ImageView) view.findViewById(R.id.iv_pop1);
        mPop2= (ImageView) view.findViewById(R.id.iv_pop2);
        mPop3= (ImageView) view.findViewById(R.id.iv_pop3);
        mPop4= (ImageView) view.findViewById(R.id.iv_pop4);
        mCloud1= (ImageView) view.findViewById(R.id.iv_cloud1);
        mCloud2= (ImageView) view.findViewById(R.id.iv_cloud2);
        mCloud3= (ImageView) view.findViewById(R.id.iv_cloud3);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), LoginActivity.class));
                getActivity().finish();
            }
        });
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (isVisibleToUser) {
            pop1Anim = AnimationUtils.loadAnimation(getActivity(), R.anim.anim_guide_pop);
            pop2Anim = AnimationUtils.loadAnimation(getActivity(), R.anim.anim_guide_pop);
            pop3Anim = AnimationUtils.loadAnimation(getActivity(), R.anim.anim_guide_pop);
            pop4Anim = AnimationUtils.loadAnimation(getActivity(), R.anim.anim_guide_pop);
            cloud1Anim = AnimationUtils.loadAnimation(getActivity(), R.anim.anim_guide_cloud_left);
            cloud2Anim = AnimationUtils.loadAnimation(getActivity(), R.anim.anim_guide_cloud_right);
            cloud3Anim = AnimationUtils.loadAnimation(getActivity(), R.anim.anim_guide_cloud_right);
            mPop1.startAnimation(pop1Anim);
            mPop1.setVisibility(View.VISIBLE);
            mPop2.startAnimation(pop2Anim);
            mPop2.setVisibility(View.VISIBLE);
            mPop3.startAnimation(pop3Anim);
            mPop3.setVisibility(View.VISIBLE);
            mPop4.startAnimation(pop4Anim);
            mPop4.setVisibility(View.VISIBLE);
            mCloud1.startAnimation(cloud1Anim);
            mCloud2.startAnimation(cloud2Anim);
            mCloud3.startAnimation(cloud3Anim);
        } else {
            if (cloud1Anim != null) {
                cloud1Anim.cancel();
            }
            if (cloud2Anim != null) {
                cloud2Anim.cancel();
            }
            if (cloud3Anim != null) {
                cloud3Anim.cancel();
            }
            if (mPop1 != null) {
                mPop1.setVisibility(View.GONE);
            }
            if (mPop2 != null) {
                mPop2.setVisibility(View.GONE);
            }
            if (mPop3 != null) {
                mPop3.setVisibility(View.GONE);
            }
            if (mPop4 != null) {
                mPop4.setVisibility(View.GONE);
            }
        }
    }

}
