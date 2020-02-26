package rosie.com.rosiebeauty;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import rosie.com.rosiebeauty.Adapter.MultiViewTypeAdapter;
import rosie.com.rosiebeauty.Model.MultiViewModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    ArrayList<MultiViewModel> gridViewModelArrayList;
    private RecyclerView mRecyclerView;
    private String[] service_names;
    private int[] icons;

    public HomeFragment() {
        // Required empty public constructor
    }

    @SuppressLint("WrongViewCast")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        //Recycler View
        gridViewModelArrayList = new ArrayList();

        prepareData();

        MultiViewTypeAdapter adapter = new MultiViewTypeAdapter(gridViewModelArrayList, this.getActivity().getApplicationContext());
        // LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

        mRecyclerView = rootView.findViewById(R.id.suggestionView);
        StaggeredGridLayoutManager lm =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(lm);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(adapter);

        return rootView;
    }

    void prepareData() {
        service_names = new String[]{
                "Blow Dry",
                "Female Hair Cutting",
                "Male Hair Cutting",
                "Hair Dye",
                "Long Hair Dye",
                "Male Hair Dye",
                "Hair Glossing",
                "Hair Highlight",
                "Hair Perm",
                "Hair Steaming",
                "Hair Straightening",
        };
        icons = new int[]{
                R.drawable.ser_blow_dry,
                R.drawable.ser_cutting_hair_girl,
                R.drawable.ser_cutting_hair_man,
                R.drawable.ser_dye_hair,
                R.drawable.ser_dye_long_hair,
                R.drawable.ser_dye_man_hair,
                R.drawable.ser_gloss_hair,
                R.drawable.ser_highlight_hair,
                R.drawable.ser_perm,
                R.drawable.ser_steaming_hair,
                R.drawable.ser_straighten_hair,
        };


        MultiViewModel gridViewModel = null;
        List<MultiViewModel.RecyclerChildItem> recyclerChildItems = new ArrayList<>();
        recyclerChildItems.add(new MultiViewModel.RecyclerChildItem(R.drawable.facial_treatment, "Facial Treatment", MultiViewModel.TYPE_SQUARE_ICON_TEXT_BELOW));
        recyclerChildItems.add(new MultiViewModel.RecyclerChildItem(R.drawable.makeup, "Makeup", MultiViewModel.TYPE_SQUARE_ICON_TEXT_BELOW));
        recyclerChildItems.add(new MultiViewModel.RecyclerChildItem(R.drawable.hair, "Hair", MultiViewModel.TYPE_SQUARE_ICON_TEXT_BELOW));
        recyclerChildItems.add(new MultiViewModel.RecyclerChildItem(R.drawable.nails, "Nails", MultiViewModel.TYPE_SQUARE_ICON_TEXT_BELOW));
        recyclerChildItems.add(new MultiViewModel.RecyclerChildItem(R.drawable.massage, "Massage", MultiViewModel.TYPE_SQUARE_ICON_TEXT_BELOW));
        recyclerChildItems.add(new MultiViewModel.RecyclerChildItem(R.drawable.waxing, "Waxing", MultiViewModel.TYPE_SQUARE_ICON_TEXT_BELOW));
        recyclerChildItems.add(new MultiViewModel.RecyclerChildItem(R.drawable.steam_bath, "Steam Bath", MultiViewModel.TYPE_SQUARE_ICON_TEXT_BELOW));
        recyclerChildItems.add(new MultiViewModel.RecyclerChildItem(R.drawable.burn_fat, "Burn Fat", MultiViewModel.TYPE_SQUARE_ICON_TEXT_BELOW));
        recyclerChildItems.add(new MultiViewModel.RecyclerChildItem(R.drawable.tattoo_makeup, "Tattoo Makeup", MultiViewModel.TYPE_SQUARE_ICON_TEXT_BELOW));

        for (int i = 0; i < service_names.length; i++) {
            if (i == 0) {
                gridViewModel = new MultiViewModel(MultiViewModel.TYPE_SECTION_TITLE, "Hot Deals");
                gridViewModelArrayList.add(gridViewModel);
                gridViewModel = new MultiViewModel(MultiViewModel.TYPE_SLIDESHOW, "", R.drawable.slideshow1);
                gridViewModelArrayList.add(gridViewModel);
                gridViewModel = new MultiViewModel(MultiViewModel.TYPE_SECTION_TITLE, "Category");
                gridViewModelArrayList.add(gridViewModel);
                gridViewModel =
                        new MultiViewModel(MultiViewModel.TYPE_RECYLERVIEW, MultiViewModel.ORIENTATION_HORIZONTAL,
                                2, recyclerChildItems);
                gridViewModelArrayList.add(gridViewModel);
                gridViewModel = new MultiViewModel(MultiViewModel.TYPE_SECTION_TITLE, "Top Services");
                gridViewModelArrayList.add(gridViewModel);
            } else {
                Random random = new Random();
                int min = 100;
                int max = 500;
                int price = random.nextInt((max - min) + 1) + min;
                if (i % 2 == 0) {
                    gridViewModel = new MultiViewModel(MultiViewModel.TYPE_IMG_TEXT_PRICE, service_names[i], icons[i], "$" + price + ".00", MultiViewModel.NO_PROMOTION, "");
                    gridViewModelArrayList.add(gridViewModel);
                } else {
                    gridViewModel = new MultiViewModel(MultiViewModel.TYPE_IMG_TEXT_PRICE, service_names[i], icons[i], "$" + price + ".00", MultiViewModel.HAS_PROMOTION, "$" + (price - 50) + ".00");
                    gridViewModelArrayList.add(gridViewModel);
                }
            }
        }

    }
}
