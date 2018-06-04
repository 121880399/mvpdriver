package org.zzy.driver.mvp.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class FragmentAdapter extends FragmentStatePagerAdapter {
		private ArrayList<Fragment> mList;

		public FragmentAdapter(FragmentManager fragmentManager,
                               ArrayList<Fragment> mList) {
			super(fragmentManager);
			if (mList == null) {
				mList = new ArrayList<Fragment>();
			} else {
				this.mList = mList;
			}
		}
		@Override
		public Fragment getItem(int arg0) {
			return mList.get(arg0);
		}

		@Override
		public int getCount() {
			return mList.size();
		}
		
	}