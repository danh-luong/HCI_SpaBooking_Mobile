package rosie.com.rosiebeauty;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;

import rosie.com.rosiebeauty.Adapter.MultiViewTypeAdapter;
import rosie.com.rosiebeauty.Model.MultiViewModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {

    ArrayList<MultiViewModel> gridViewModelArrayList;
    private RecyclerView mRecyclerView;
    private String[] activity_names = new String[]{
            "AAAA", "BBBBB", "CCCCCC"
    };
    private int[] icons = new int[]{
        R.drawable.ic_action_book,
                R.drawable.ic_action_favorites,
                R.drawable.ic_action_home
    };
    private MaterialSearchView searchView;


    public SearchFragment() {
        // Required empty public constructor
    }


    public void setSearchView(MaterialSearchView searchView) {
        this.searchView = searchView;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_search, container, false);

        //Recycler View
        gridViewModelArrayList = new ArrayList();

        prepareData();

        MultiViewTypeAdapter adapter = new MultiViewTypeAdapter(gridViewModelArrayList, this.getActivity().getApplicationContext());
        adapter.setSearchView(searchView);
        mRecyclerView = rootView.findViewById(R.id.recyclerView);
        StaggeredGridLayoutManager lm =
                new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(lm);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(adapter);

        return rootView;

    }

    public void changeContentOnQueryTextChange(String searchText) {
        MultiViewModel gridViewModel = null;
        gridViewModelArrayList = new ArrayList<>();
        if (searchText.equals("")) {
            for (int i = 0; i < activity_names.length; i++) {
                gridViewModel = new MultiViewModel(MultiViewModel.TYPE_IMAGE_INLINE_WITH_TEXT, activity_names[i], icons[i]);
                gridViewModelArrayList.add(gridViewModel);
            }
        } else {
            for (int i = 0; i < activity_names.length; i++) {
                if (activity_names[i].toLowerCase().contains(searchText.toLowerCase())) {
                    gridViewModel = new MultiViewModel(MultiViewModel.TYPE_IMAGE_INLINE_WITH_TEXT, activity_names[i], icons[i]);
                    gridViewModelArrayList.add(gridViewModel);
                }
            }
        }
        if(this.getActivity() != null) {
            MultiViewTypeAdapter adapter = new MultiViewTypeAdapter(gridViewModelArrayList, this.getActivity().getApplicationContext());
            adapter.setSearchView(searchView);
            StaggeredGridLayoutManager lm =
                    new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
            mRecyclerView.setLayoutManager(lm);
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
            mRecyclerView.setAdapter(adapter);
        }
    }



    void prepareData() {
        gridViewModelArrayList.clear();



        MultiViewModel gridViewModel = null;
        for (int i = 0; i < activity_names.length; i++) {
                gridViewModel = new MultiViewModel(MultiViewModel.TYPE_IMAGE_INLINE_WITH_TEXT, activity_names[i], icons[i]);
                gridViewModelArrayList.add(gridViewModel);
        }
    }

}
