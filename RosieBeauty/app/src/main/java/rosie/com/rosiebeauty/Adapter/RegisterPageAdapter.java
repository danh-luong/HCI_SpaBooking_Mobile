package rosie.com.rosiebeauty.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import rosie.com.rosiebeauty.RegisterFragment;

public class RegisterPageAdapter extends FragmentPagerAdapter {
    private String[] tabTitles = new String[]{
            "Khách hàng",
            "Đối tác"
    };
    public RegisterPageAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new RegisterFragment(tabTitles[position]);
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
