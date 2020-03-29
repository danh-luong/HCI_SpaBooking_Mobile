package rosie.com.rosiebeauty;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import rosie.com.rosiebeauty.Adapter.ServiceRecycleViewAdapter;
import rosie.com.rosiebeauty.Model.Service;


/**
 * A simple {@link Fragment} subclass.
 */
public class ServiceManagementFragment extends Fragment {
    public static final int STATUS_UPDATE = 10;
    public static final int STATUS_CREATE = 9;
    public static final int STATUS_NORMAL = 8;
    public static  int status = STATUS_NORMAL;

    private View rootView;
    private RecyclerView recycleViewService;
    private static ArrayList<Service> services = new ArrayList<>();
    private ServiceRecycleViewAdapter adapter;

    public static ArrayList<Service> getServices() {
        return services;
    }

    public ServiceManagementFragment() {
        // Required empty public constructor
    }

    private void loadServices() {

        services.clear();
        services.add(new Service(R.drawable.img_trang_diem_han_quoc,
                "Trang điểm Hàn Quốc",
                135000,
                20, "Trang điểm"));

        services.add(new Service(R.drawable.ser_sonmong,
                "Sơn màu móng tay",
                105000,
                20,
                "Làm móng"));

        services.add(new Service(R.drawable.ser_highlight_hair,
                "Nhuộm highlight",
                228000,
                25, "Làm tóc"));

        services.add(new Service(R.drawable.ser_trimun,
                "Điều trị mụn",
                92000,
                15,
                "Dưỡng da"));

        services.add(new Service(R.drawable.xonghoinu,
                "Xông hơi thải độc",
                324000,
                30,
                "Dưỡng da"));
        services.add(new Service(R.drawable.ser1_acne,
                "Mặt nạ thải độc",
                117000,
                10,
                "Dưỡng da"));
    }

    public void loadRecycleView() {
        adapter = new ServiceRecycleViewAdapter(services, (ManagerActivity) getActivity(), this);
        recycleViewService = rootView.findViewById(R.id.recycle_view_services);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recycleViewService.setAdapter(adapter);
        recycleViewService.setLayoutManager(layoutManager);
    }

    @Override
    public void onStart() {
        super.onStart();
        loadRecycleView();
        if(status != STATUS_NORMAL) {
            switch (status) {
                case STATUS_CREATE:
                    Toast.makeText(getActivity(), "Tạo dịch vụ thành công", Toast.LENGTH_SHORT).show();
                    break;
                case STATUS_UPDATE:
                    Toast.makeText(getActivity(), "Cập nhật dịch vụ thành công", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
        status = STATUS_NORMAL;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_service_management, container, false);
        loadServices();

        FloatingActionButton btnSearchService = (FloatingActionButton) rootView.findViewById(R.id.btnSearchService);
        final EditText edtSearchName = (EditText) rootView.findViewById(R.id.edtSearchName);
        btnSearchService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textSearch = edtSearchName.getText().toString();
                List<Service> searchService = new ArrayList<>();
                for (int i = 0; i < services.size(); i++) {
                    if (services.get(i).getTitle().toLowerCase().contains(textSearch.toLowerCase())) {
                        searchService.add(services.get(i));
                    }
                }
                adapter = new ServiceRecycleViewAdapter((ArrayList<Service>) searchService, (ManagerActivity) getActivity(), ServiceManagementFragment.this);
                recycleViewService = rootView.findViewById(R.id.recycle_view_services);
                LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                recycleViewService.setAdapter(adapter);
                recycleViewService.setLayoutManager(layoutManager);
            }
        });
        return rootView;
    }

}
