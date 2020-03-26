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
public class BookingFragment extends Fragment {
    ArrayList<MultiViewModel> gridViewModelArrayList;
    private RecyclerView mRecyclerView;

    public BookingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_booking, container, false);
        //Recycler View
        gridViewModelArrayList = new ArrayList();

        prepareData();

        MultiViewTypeAdapter adapter = new MultiViewTypeAdapter(gridViewModelArrayList, this.getActivity());

        mRecyclerView = rootView.findViewById(R.id.booking_recycler_view);
        StaggeredGridLayoutManager lm =
                new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(lm);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(adapter);
        return rootView;
    }

    void prepareData() {
        List<MultiViewModel.Appointment> appointments = new ArrayList<>();
        appointments.add(new MultiViewModel.Appointment("BK4051", "Thu Cúc Clinic","05-04-2020 12:00",
                "20-05-2020 15:00", "8.000.000đ"));
        appointments.add(new MultiViewModel.Appointment("BK4052","Hana Beauty", "05-06-2020 12:00",
                "20-07-2020 15:00", "3.000.000đ"));
        appointments.add(new MultiViewModel.Appointment("BK4053", "Venus Spa","05-07-2020 12:00",
                "20-08-2020 15:00", "6.000.000đ"));
        appointments.add(new MultiViewModel.Appointment("BK4054","Derma Spa", "05-08-2020 12:00",
                "20-09-2020 15:00", "10.000.000đ"));
        appointments.add(new MultiViewModel.Appointment("BK4055", "Tropic Spa","05-09-2020 12:00",
                "20-10-2020 15:00", "20.000.000đ"));
        for (int i = 0; i < appointments.size(); i++) {
            gridViewModelArrayList.add(new MultiViewModel(MultiViewModel.TYPE_APPOINTMENT_ITEM, appointments.get(i)));
        }
    }

}
