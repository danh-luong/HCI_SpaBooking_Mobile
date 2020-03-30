package rosie.com.rosiebeauty;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import rosie.com.rosiebeauty.Adapter.BranchRecycleViewAdapter;
import rosie.com.rosiebeauty.Data.UserRepository;
import rosie.com.rosiebeauty.Model.Branch;


/**
 * A simple {@link Fragment} subclass.
 */
public class BranchManagementFragment extends Fragment {
    public static final int STATUS_UPDATE = 10;
    public static final int STATUS_CREATE = 9;
    public static final int STATUS_NORMAL = 8;
    public static  int status = STATUS_NORMAL;


    private View rootView;
    private RecyclerView recycleViewBranch;
    private static ArrayList<Branch> branches = new ArrayList<>();
    private BranchRecycleViewAdapter adapter;
//    private BootstrapButton btnCreateNewBranch;

    public static ArrayList<Branch> getBranches() {
        return branches;
    }

    public void setBranches(ArrayList<Branch> branches) {
        this.branches = branches;
    }

    public BranchManagementFragment() {
        // Required empty public constructor
    }

    private void loadBranches() {
        branches.clear();
        branches.add(new Branch(R.drawable.spa2batrung,
                "Rose Spa Hai Bà Trưng",
                "235B - Hai Bà Trưng - Quận 1 ",
                "Đang hoạt động", UserRepository.userList.get("manager")));

        branches.add(new Branch(R.drawable.spa_hoang_van_thu,
                "Hoa Cúc Hoàng Văn Thụ",
                "41 - Hoàng Văn Thụ - Quận 1 ",
                "Đang hoạt động", UserRepository.userList.get("manager1")));

        branches.add(new Branch(R.drawable.spa_le_duan,
                "Hoàng Kim Lê Duẩn",
                "75 - Lê Duẩn - Quận 1 ",
                "Đang hoạt động", UserRepository.userList.get("manager2")));

        branches.add(new Branch(R.drawable.spa_le_loi,
                "Hàn Quốc Spa Lê lợi",
                "216 - Hai Bà Trưng - Quận 1 ",
                "Vô hiệu hóa", UserRepository.userList.get("manager3")));

        branches.add(new Branch(R.drawable.spa_quangtrung,
                "Mộc Spa Quang Trung",
                "115 - Quang Trung - Gò Vấp ",
                "Đang hoạt động", UserRepository.userList.get("manager4")));
    }


    public void loadRecycleView() {
        adapter = new BranchRecycleViewAdapter(branches, (AdminActivity) getActivity(), this);
        recycleViewBranch = rootView.findViewById(R.id.recycle_view_services);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recycleViewBranch.setAdapter(adapter);
        recycleViewBranch.setLayoutManager(layoutManager);
    }

    @Override
    public void onStart() {
        super.onStart();
        loadRecycleView();
        if(status != STATUS_NORMAL) {
            switch (status) {
                case STATUS_CREATE:
                    Toast.makeText(getActivity(), "Tạo spa thành công", Toast.LENGTH_SHORT).show();
                    break;
                case STATUS_UPDATE:
                    Toast.makeText(getActivity(), "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
        status = STATUS_NORMAL;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        rootView = inflater.inflate(R.layout.fragment_branch_management, container, false);
        loadBranches();

//        btnCreateNewBranch = rootView.findViewById(R.id.btnCreateBranch);
//        btnCreateNewBranch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), CreateBranchActivity.class);
//                startActivity(intent);
//            }
//        });
        FloatingActionButton btnSearchUser = (FloatingActionButton) rootView.findViewById(R.id.btnSearchUser);
        final EditText edtSearchName = (EditText) rootView.findViewById(R.id.edtSearchName);
        btnSearchUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textSearch = edtSearchName.getText().toString();
                List<Branch> searchBranch = new ArrayList<>();
                for (int i = 0; i < branches.size(); i++) {
                    if (branches.get(i).getTitle().toLowerCase().contains(textSearch.toLowerCase())) {
                        searchBranch.add(branches.get(i));
                    }
                }
                adapter = new BranchRecycleViewAdapter((ArrayList<Branch>) searchBranch, (AdminActivity) getActivity(), BranchManagementFragment.this);
                recycleViewBranch = rootView.findViewById(R.id.recycle_view_services);
                LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                recycleViewBranch.setAdapter(adapter);
                recycleViewBranch.setLayoutManager(layoutManager);
            }
        });
        return rootView;
    }

}
