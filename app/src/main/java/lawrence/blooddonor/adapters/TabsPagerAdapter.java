package lawrence.blooddonor.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import lawrence.blooddonor.fragments.SearchTabFragment;
import lawrence.blooddonor.fragments.BioDataTabFragment;
import lawrence.blooddonor.fragments.BloodBanksTabFragment;



public class TabsPagerAdapter extends FragmentStatePagerAdapter {

	int mNumOfTabs;

	public TabsPagerAdapter(FragmentManager fm, int NumOfTabs) {
		super(fm);
		this.mNumOfTabs = NumOfTabs;
	}

	@Override
	public Fragment getItem(int position) {

		switch (position) {
			case 0:
				return new SearchTabFragment();
			case 1:
				return new BloodBanksTabFragment();
			case 2:
				return new BioDataTabFragment();
			default:
				return null;
		}
	}

	@Override
	public int getCount() {
		return mNumOfTabs;
	}
}
