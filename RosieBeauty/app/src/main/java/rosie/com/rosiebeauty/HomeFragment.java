package rosie.com.rosiebeauty;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import java.util.Timer;
import java.util.TimerTask;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    ViewPager viewSlideShow;
    SlideshowAdapter slideshowAdapter;
    Timer swipeTimer;

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
                        viewSlideShow.setCurrentItem((viewSlideShow.getCurrentItem()+1)%slideshowAdapter.getCount());
                    }
                });
            }
        };
        swipeTimer = new Timer();
        swipeTimer.schedule(timerTask, 1000,3000);
//        --- End slideshow ---

        return rootView;
    }



}
