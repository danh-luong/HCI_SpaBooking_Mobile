package rosie.com.rosiebeauty;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import rosie.com.rosiebeauty.Adapter.BranchServiceAdapter;

public class BrachServiceListFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TextView txtTerm;
    private BranchServiceAdapter branchServiceAdapter;
    Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_brach_service_list, container, false);
        tabLayout = rootView.findViewById(R.id.tabCategory);
        viewPager = rootView.findViewById(R.id.brachViewPager);
        branchServiceAdapter = new BranchServiceAdapter();
        return rootView;
    }
}
