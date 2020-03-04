package rosie.com.rosiebeauty;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;

import rosie.com.rosiebeauty.Adapter.MultiViewTypeAdapter;
import rosie.com.rosiebeauty.Model.MultiViewModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class SpaServiceDetailFragment extends Fragment {
    private ArrayList<MultiViewModel> commentList;
    private RecyclerView commentsHolder;


    private String[] user_names = new String[]{
            "Mud Bath", "Salt Stone", "SOOTHE FACIAL", "ORGANIC FACIAL"
    };

    private String[] comments = new String[]{
            "This is a really good service 10/10 recommended", "This is a really good service 9/10 recommended", "This is a really good service 8/10 recommended", "This is a really good service 7/10 recommended"
    };
    private int[] avatars = new int[]{
            R.drawable.avatar1,
            R.drawable.avatar2,
            R.drawable.avatar4,
            R.drawable.avatar5
    };

    public SpaServiceDetailFragment() {
        // Required empty public constructor
        
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_spa_service_detail, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        prepareData();
        MultiViewTypeAdapter commentAdapter = new MultiViewTypeAdapter(commentList, this.getActivity().getApplicationContext());
        commentsHolder = rootView.findViewById(R.id.comments_holder);
        StaggeredGridLayoutManager lmForSuggestion =
                new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        commentsHolder.setLayoutManager(lmForSuggestion);
        commentsHolder.setItemAnimator(new DefaultItemAnimator());
        commentsHolder.setAdapter(commentAdapter);

        Button btnViewSchedule = (Button) rootView.findViewById(R.id.btnViewSchedule);
        btnViewSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SpaServiceDetailFragment.this.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ScheduleFragment()).commit();
            }
        });
        return rootView;

    }

    void prepareData() {
        commentList = new ArrayList<>();

        MultiViewModel gridViewModel = null;
        for (int i = 0; i < user_names.length; i++) {
            gridViewModel = new MultiViewModel(MultiViewModel.TYPE_FACEBOOK_COMMENT, user_names[i], comments[i], avatars[i]);
            commentList.add(gridViewModel);
        }

    }

}
