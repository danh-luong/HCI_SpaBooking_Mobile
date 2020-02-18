package rosie.com.rosiebeauty;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    ViewPager viewSlideShow;
    SlideshowAdapter slideshowAdapter;
    public HomeFragment() {
        // Required empty public constructor
    }

    @SuppressLint("WrongViewCast")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        viewSlideShow = rootView.findViewById(R.id.home_slideshow);
        slideshowAdapter = new SlideshowAdapter(this.getActivity());

        viewSlideShow.setAdapter(slideshowAdapter);
        return rootView;
    }

}
