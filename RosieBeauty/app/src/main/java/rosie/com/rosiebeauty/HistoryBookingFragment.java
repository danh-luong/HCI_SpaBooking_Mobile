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

import rosie.com.rosiebeauty.Adapter.ManagerViewTypeAdapter;
import rosie.com.rosiebeauty.Model.ManagerViewModel;

public class HistoryBookingFragment extends Fragment {
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
        View rootView = inflater.inflate(R.layout.fragment_history_booking, container, false);
        //Recycler View
        gridViewModelArrayList = new ArrayList();

        prepareData();

        ManagerViewTypeAdapter adapter = new ManagerViewTypeAdapter(gridViewModelArrayList, this.getActivity());

        mRecyclerView = rootView.findViewById(R.id.history_booking_recycler_view);
        StaggeredGridLayoutManager lm =
                new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(lm);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(adapter);
        return rootView;
    }

    void prepareData() {
        List<ManagerViewModel.Appointment> appointments = new ArrayList<>();
        appointments.add(new ManagerViewModel.Appointment("BK4051", "Hoàng Thuỳ Linh", "05-04-2020 12:00",
                "20-05-2020 15:00", "8.000.000đ"));
        appointments.add(new ManagerViewModel.Appointment("BK4052", "Ninh Dương Lan Ngọc", "05-06-2020 12:00",
                "20-07-2020 15:00", "3.000.000đ"));
        appointments.add(new ManagerViewModel.Appointment("BK4053", "Thân Thuý Hà", "05-07-2020 12:00",
                "20-08-2020 15:00", "6.000.000đ"));
        appointments.add(new ManagerViewModel.Appointment("BK4054", "Tăng Thanh Hà", "05-08-2020 12:00",
                "20-09-2020 15:00", "10.000.000đ"));
        appointments.add(new ManagerViewModel.Appointment("BK4055", "Lê Phương", "05-09-2020 12:00",
                "20-10-2020 15:00", "20.000.000đ"));
        for (int i = 0; i < appointments.size(); i++) {
            gridViewModelArrayList.add(new ManagerViewModel(ManagerViewModel.TYPE_APPOINTMENT_MANAGER, appointments.get(i)));
        }
    }
}
