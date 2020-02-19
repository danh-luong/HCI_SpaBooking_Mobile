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
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import rosie.com.rosiebeauty.Adapter.MultiViewTypeAdapter;
import rosie.com.rosiebeauty.Model.MultiViewModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    ViewPager viewSlideShow;
    SlideshowAdapter slideshowAdapter;
    Timer swipeTimer;
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

//        --- Start lideshow ---
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        viewSlideShow = rootView.findViewById(R.id.home_slideshow);
        slideshowAdapter = new SlideshowAdapter(this.getActivity());
        viewSlideShow.setAdapter(slideshowAdapter);
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                viewSlideShow.post(new Runnable() {
                    @Override
                    public void run() {
                        viewSlideShow.setCurrentItem((viewSlideShow.getCurrentItem() + 1) % slideshowAdapter.getCount());
                    }
                });
            }
        };
        swipeTimer = new Timer();
        swipeTimer.schedule(timerTask, 1000, 3000);


        gridViewModelArrayList = new ArrayList();

        prepareData();

        MultiViewTypeAdapter adapter = new MultiViewTypeAdapter(gridViewModelArrayList, this.getActivity().getApplicationContext());
        // LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

        mRecyclerView = rootView.findViewById(R.id.recyclerView);
        StaggeredGridLayoutManager lm =
                new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(lm);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(adapter);

//        --- End slideshow ---
        return rootView;
    }

    void prepareData() {
        activity_names = new String[]{
                "AAAA", "BBBBB", "CCCCCC", "AAAA", "BBBBB", "CCCCCC", "AAAA", "BBBBB", "CCCCCC"
        };
        icons = new int[]{R.drawable.ic_action_book, R.drawable.ic_action_favorites, R.drawable.ic_action_home, R.drawable.ic_action_book, R.drawable.ic_action_favorites, R.drawable.ic_action_home, R.drawable.ic_action_book, R.drawable.ic_action_favorites, R.drawable.ic_action_home};


        MultiViewModel gridViewModel = null;
        for (int i = 0; i < activity_names.length; i++) {
            if (i == 6) {
                gridViewModel = new MultiViewModel(MultiViewModel.TYPE_BANNER, "", R.drawable.slideshow1);
                gridViewModelArrayList.add(gridViewModel);
                gridViewModel = new MultiViewModel(MultiViewModel.TYPE_IMAGE_WITH_TEXT, activity_names[i], icons[i]);
                gridViewModelArrayList.add(gridViewModel);
            } else {
                gridViewModel = new MultiViewModel(MultiViewModel.TYPE_IMAGE_WITH_TEXT, activity_names[i], icons[i]);
                gridViewModelArrayList.add(gridViewModel);
            }
        }
    }
}
