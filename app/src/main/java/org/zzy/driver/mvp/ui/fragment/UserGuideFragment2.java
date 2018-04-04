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
public class UserGuideFragment2 extends Fragment {
    private ImageView mPop1;
    private ImageView mPop2;
    private ImageView mCloud1;
    private ImageView mCloud2;
    private ImageView mCloud3;
    Animation pop1Anim = null;
    Animation pop2Anim = null;
    Animation cloud1Anim=null;
    Animation cloud2Anim=null;
    Animation cloud3Anim=null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_userguide2, null);
        mPop1= (ImageView) view.findViewById(R.id.iv_pop1);
        mPop2= (ImageView) view.findViewById(R.id.iv_pop2);
        mCloud1= (ImageView) view.findViewById(R.id.iv_cloud1);
        mCloud2= (ImageView) view.findViewById(R.id.iv_cloud2);
        mCloud3= (ImageView) view.findViewById(R.id.iv_cloud2);
        return view;
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (isVisibleToUser) {
            pop1Anim = AnimationUtils.loadAnimation(getActivity(), R.anim.anim_guide_pop);
            pop2Anim = AnimationUtils.loadAnimation(getActivity(), R.anim.anim_guide_pop);
            cloud1Anim = AnimationUtils.loadAnimation(getActivity(), R.anim.anim_guide_cloud_left);
            cloud2Anim = AnimationUtils.loadAnimation(getActivity(), R.anim.anim_guide_cloud_right);
            cloud3Anim = AnimationUtils.loadAnimation(getActivity(),R.anim.anim_guide_cloud_right);
            mCloud1.startAnimation(cloud1Anim);
            mCloud2.startAnimation(cloud2Anim);
            mCloud3.startAnimation(cloud3Anim);
            mPop1.startAnimation(pop1Anim);
            mPop1.setVisibility(View.VISIBLE);
            mPop2.startAnimation(pop2Anim);
            mPop2.setVisibility(View.VISIBLE);
        } else {
            if (mPop1 != null && mPop2 != null) {
                mPop1.setVisibility(View.GONE);
                mPop2.setVisibility(View.GONE);
            }
            if (pop1Anim != null && pop2Anim != null&&cloud1Anim!=null&&cloud2Anim!=null&&cloud3Anim!=null) {
                pop1Anim.cancel();
                pop2Anim.cancel();
                cloud1Anim.cancel();
                cloud2Anim.cancel();
                cloud3Anim.cancel();
            }
        }
    }

}
