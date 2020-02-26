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
        List<MultiViewModel> childMultiViewModels = new ArrayList<>();
        childMultiViewModels.add(new MultiViewModel(MultiViewModel.TYPE_SQUARE_ICON_TEXT_BELOW, "Facial Treatment", R.drawable.facial_treatment));
        childMultiViewModels.add(new MultiViewModel(MultiViewModel.TYPE_SQUARE_ICON_TEXT_BELOW, "Makeup", R.drawable.makeup));
        childMultiViewModels.add(new MultiViewModel(MultiViewModel.TYPE_SQUARE_ICON_TEXT_BELOW, "Hair", R.drawable.hair));
        childMultiViewModels.add(new MultiViewModel(MultiViewModel.TYPE_SQUARE_ICON_TEXT_BELOW, "Nails", R.drawable.nails));
        childMultiViewModels.add(new MultiViewModel(MultiViewModel.TYPE_SQUARE_ICON_TEXT_BELOW, "Massage", R.drawable.massage));
        childMultiViewModels.add(new MultiViewModel(MultiViewModel.TYPE_SQUARE_ICON_TEXT_BELOW, "Waxing", R.drawable.waxing));
        childMultiViewModels.add(new MultiViewModel(MultiViewModel.TYPE_SQUARE_ICON_TEXT_BELOW, "Steam Bath", R.drawable.steam_bath));
        childMultiViewModels.add(new MultiViewModel(MultiViewModel.TYPE_SQUARE_ICON_TEXT_BELOW, "Burn Fat", R.drawable.burn_fat));
        childMultiViewModels.add(new MultiViewModel(MultiViewModel.TYPE_SQUARE_ICON_TEXT_BELOW, "Tattoo Makeup", R.drawable.tattoo_makeup));

        for (int i = 0; i < service_names.length; i++) {
            if (i == 0) {
                gridViewModel = new MultiViewModel(MultiViewModel.TYPE_SECTION_TITLE, "Hot Deals");
                gridViewModelArrayList.add(gridViewModel);
                gridViewModel = new MultiViewModel(MultiViewModel.TYPE_SLIDESHOW, "", R.drawable.slideshow1);
                gridViewModelArrayList.add(gridViewModel);
                gridViewModel = new MultiViewModel(MultiViewModel.TYPE_SECTION_TITLE, "Top Sale");
                gridViewModelArrayList.add(gridViewModel);
                Random random = new Random();
                int min = 200;
                int max = 500;
                int price = 0;
                List<MultiViewModel> childTopPromotions = new ArrayList<>();
                int sale[] = new int[]{
                        100, 80, 60, 55, 40, 33, 20,10
                };
                for (int j = 0; j < 8; j++) {
                    price = random.nextInt((max - min) + 1) + min;
                    childTopPromotions.add(new MultiViewModel(MultiViewModel.TYPE_IMG_TEXT_PRICE, service_names[j], icons[j], "$" + price + ".00", MultiViewModel.HAS_PROMOTION, "$" + (price - sale[j]) + ".00"));
                }
                gridViewModel =
                        new MultiViewModel(MultiViewModel.TYPE_RECYLERVIEW, MultiViewModel.ORIENTATION_HORIZONTAL,
                                2, childTopPromotions);
                gridViewModelArrayList.add(gridViewModel);
                gridViewModel = new MultiViewModel(MultiViewModel.TYPE_SECTION_TITLE, "Category");
                gridViewModelArrayList.add(gridViewModel);
                gridViewModel =
                        new MultiViewModel(MultiViewModel.TYPE_RECYLERVIEW, MultiViewModel.ORIENTATION_HORIZONTAL,
                                2, childMultiViewModels);
                gridViewModelArrayList.add(gridViewModel);
                gridViewModel = new MultiViewModel(MultiViewModel.TYPE_SECTION_TITLE, "Top Trending");
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
