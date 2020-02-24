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

import rosie.com.rosiebeauty.Adapter.MultiViewTypeAdapter;
import rosie.com.rosiebeauty.Model.MultiViewModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    ArrayList<MultiViewModel> gridViewModelArrayList;
    private RecyclerView mRecyclerView;
    private String[] activity_names;
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
        activity_names = new String[]{
                "",
                "Facial Treatment",
                "Makeup",
                "Hair",
                "Nails",
                "Massage",
                "Waxing",
                "Steam Bath",
                "Burn Fat",
                "Tattoo Makeup",
        };
        icons = new int[]{
                R.drawable.slideshow1,
                R.drawable.facial_treatment,
                R.drawable.makeup,
                R.drawable.hair,
                R.drawable.nails,
                R.drawable.massage,
                R.drawable.waxing,
                R.drawable.steam_bath,
                R.drawable.burn_fat,
                R.drawable.tattoo_makeup
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

        for (int i = 0; i < activity_names.length; i++) {
            if (i == 0) {
                gridViewModel = new MultiViewModel(MultiViewModel.TYPE_SECTIN_TITLE, "Hot Deals");
                gridViewModelArrayList.add(gridViewModel);
                gridViewModel = new MultiViewModel(MultiViewModel.TYPE_SLIDESHOW, "", R.drawable.slideshow1);
                gridViewModelArrayList.add(gridViewModel);
                gridViewModel = new MultiViewModel(MultiViewModel.TYPE_SECTIN_TITLE, "Category");
                gridViewModelArrayList.add(gridViewModel);
                gridViewModel =
                        new MultiViewModel(MultiViewModel.TYPE_RECYLERVIEW, MultiViewModel.ORIENTATION_HORIZONTAL,
                                2, recyclerChildItems);
                gridViewModelArrayList.add(gridViewModel);
                gridViewModel = new MultiViewModel(MultiViewModel.TYPE_SECTIN_TITLE, "Top Services");
                gridViewModelArrayList.add(gridViewModel);
            } else {

                gridViewModel = new MultiViewModel(MultiViewModel.TYPE_IMAGE_WITH_TEXT, activity_names[i], icons[i]);
                gridViewModelArrayList.add(gridViewModel);
            }
        }

    }
}
