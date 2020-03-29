package rosie.com.rosiebeauty.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import rosie.com.rosiebeauty.BrachServiceListTabFragment;

public class BranchServiceAdapter extends FragmentPagerAdapter {
    private String[] tabTitles = new String[]{
            "Chăm sóc da",
            "Làm tóc",
            "Massage",
            "Trang điểm",
            "Làm móng",
            "Khác"
    };

    public BranchServiceAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new BrachServiceListTabFragment(tabTitles[position]);
        return fragment;
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
