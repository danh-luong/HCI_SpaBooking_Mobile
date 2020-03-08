package rosie.com.rosiebeauty;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class ListProduct extends Fragment implements ActionBar.TabListener{
    private List<Fragment> sortTabs = new ArrayList<>();
    private TabNearly tabNearly;
    private TabRating tabRating;
    private TabPromotion tabPromotion;
    private String username;
    public ListProduct() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar bar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        ActionBar.Tab tabNearly = bar.newTab();
        tabNearly.setText("Gần đây");
        tabNearly.setTabListener(this);
        bar.addTab(tabNearly);

        ActionBar.Tab tabPromotion = bar.newTab();
        tabPromotion.setText("Giảm giá");
        tabPromotion.setTabListener(this);
        bar.addTab(tabPromotion);

        ActionBar.Tab tabRating = bar.newTab();
        tabRating.setText("Đánh giá");
        tabRating.setTabListener(this);
        bar.addTab(tabRating);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_product, container, false);
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }
}
