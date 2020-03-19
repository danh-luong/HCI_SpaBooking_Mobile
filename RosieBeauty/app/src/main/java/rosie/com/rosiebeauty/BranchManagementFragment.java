package rosie.com.rosiebeauty;


import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.beardedhen.androidbootstrap.BootstrapButton;

import java.util.ArrayList;

import rosie.com.rosiebeauty.Adapter.BranchRecycleViewAdapter;
import rosie.com.rosiebeauty.Model.Branch;


/**
 * A simple {@link Fragment} subclass.
 */
public class BranchManagementFragment extends Fragment {

    private View rootView;
    private RecyclerView recycleViewBranch;
    private static ArrayList<Branch> branches = new ArrayList<>();
    private BranchRecycleViewAdapter adapter;
    private BootstrapButton btnCreateNewBranch;

    public ArrayList<Branch> getBranches() {
        return branches;
    }

    public void setBranches(ArrayList<Branch> branches) {
        this.branches = branches;
    }

    public BranchManagementFragment() {
        // Required empty public constructor
    }

    private void loadBranches() {
        branches.add(new Branch(R.drawable.spa2batrung,
                "Rose Spa Hai Bà Trưng",
                "235B - Hai Bà Trưng - Quận 1 ",
                "đang hoạt động"));

        branches.add(new Branch(R.drawable.spa_hoang_van_thu,
                "Rose Spa Hoàng Văn Thụ",
                "41 - Hoàng Văn Thụ - Quận 1 ",
                "đang hoạt động"));

        branches.add(new Branch(R.drawable.spa_le_duan,
                "Rose Spa Lê Duẩn",
                "75 - Lê Duẩn - Quận 1 ",
                "đang hoạt động"));

        branches.add(new Branch(R.drawable.spa_le_loi,
                "Rose Spa Lê lợi",
                "216 - Hai Bà Trưng - Quận 1 ",
                "tạm nghỉ"));

        branches.add(new Branch(R.drawable.spa_quangtrung,
                "Rose Spa Quang Trung",
                "115 - Quang Trung - Gò Vấp ",
                "đang hoạt động"));
    }


    public void loadRecycleView() {
        adapter = new BranchRecycleViewAdapter(branches, (AdminActivity) getActivity(), this);
        recycleViewBranch = rootView.findViewById(R.id.recycle_view_branches);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recycleViewBranch.setAdapter(adapter);
        recycleViewBranch.setLayoutManager(layoutManager);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        rootView = inflater.inflate(R.layout.fragment_branch_management, container, false);
        loadBranches();
        loadRecycleView();
        btnCreateNewBranch = rootView.findViewById(R.id.btnCreateBranch);
        btnCreateNewBranch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CreateBranchActivity.class);
                startActivity(intent);
            }
        });
        return rootView;
    }

}
