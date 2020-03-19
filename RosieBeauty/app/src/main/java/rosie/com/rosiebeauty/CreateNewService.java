package rosie.com.rosiebeauty;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import rosie.com.rosiebeauty.Model.MultiViewModel;


public class CreateNewService extends Fragment {
    ArrayList<MultiViewModel> gridViewModelArrayList;
    private RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.item_recyler_view, container, false);
        gridViewModelArrayList = new ArrayList();

        MultiViewModel gridViewModel = null;
//        gridViewModel = new MultiViewModel();

        gridViewModelArrayList.add(gridViewModel);
        return rootView;
    }
}
