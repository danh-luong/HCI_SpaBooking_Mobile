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

import rosie.com.rosiebeauty.Adapter.ManagerViewTypeAdapter;
import rosie.com.rosiebeauty.Model.ManagerViewModel;


public class FragmentCreateNewService extends Fragment {
    ArrayList<ManagerViewModel> gridViewModelArrayList;
    private RecyclerView mRecyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.item_recyler_view, container, false);
        //Recycler View
        gridViewModelArrayList = new ArrayList();
        gridViewModelArrayList.add(new ManagerViewModel(ManagerViewModel.CREATE_NEW_SERVICE, "", "", "", "", "", "", "", 1));
        ManagerViewTypeAdapter adapter = new ManagerViewTypeAdapter(gridViewModelArrayList, this.getActivity().getApplicationContext());

        mRecyclerView = rootView.findViewById(R.id.item_recyler_view);
        StaggeredGridLayoutManager lm =
                new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(lm);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(adapter);
        return rootView;
    }
}
