package rosie.com.rosiebeauty;


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
public class FavoriteFragment extends Fragment {
    ArrayList<MultiViewModel> gridViewModelArrayList;
    private RecyclerView mRecyclerView;


    public FavoriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_favorite, container, false);
        gridViewModelArrayList = new ArrayList();

        prepareData();

        MultiViewTypeAdapter adapter = new MultiViewTypeAdapter(gridViewModelArrayList, this.getActivity());
        // LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

        mRecyclerView = rootView.findViewById(R.id.favorite_recycler_view);
        StaggeredGridLayoutManager lm =
                new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(lm);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(adapter);
        return rootView;
    }

    void prepareData() {
//        MultiViewModel gridViewModel = null;
//        gridViewModel = new MultiViewModel(MultiViewModel.TYPE_SECTION_TITLE, "Dịch vụ yêu thích");
//        gridViewModelArrayList.add(gridViewModel);
        List<MultiViewModel.FavoriteItem> favoriteItems = new ArrayList<>();
        favoriteItems.add(
                new MultiViewModel.FavoriteItem
                        (R.drawable.ser_gloss_hair, "Trang điểm mặt", "Rose Spa", "Sư Vạn Hạnh, Quận 10", 3.5f));
        favoriteItems.add(
                new MultiViewModel.FavoriteItem
                        (R.drawable.ser_perm, "Massage mặt", "Hoa cúc clinic", "Sư Vạn Hạnh, Quận 10", 3.5f));
        favoriteItems.add(
                new MultiViewModel.FavoriteItem
                        (R.drawable.ser_steaming_hair, "Xăm chân mày", "Hoa Lan Spa", "Sư Vạn Hạnh, Quận 10", 3.5f));

        for (int i = 0; i < favoriteItems.size(); i++) {
            gridViewModelArrayList.add(new MultiViewModel(MultiViewModel.TYPE_FAVORITE_ITEM, favoriteItems.get(i)));
        }
    }
}
