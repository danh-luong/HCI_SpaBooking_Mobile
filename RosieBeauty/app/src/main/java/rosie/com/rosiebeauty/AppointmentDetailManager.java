package rosie.com.rosiebeauty;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;


/**
 * A simple {@link Fragment} subclass.
 */
public class AppointmentDetailManager extends Fragment {
    Button btnCancle, btnCheckin;

    public AppointmentDetailManager() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        View rootView = inflater.inflate(R.layout.fragment_appointment_detail_manager, container, false);
        btnCancle = (Button) rootView.findViewById(R.id.btnCancel);
        btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar snackBar = Snackbar.make(getActivity().findViewById(android.R.id.content),
                        "Bạn đã huỷ hẹn với khách hàng!!", Snackbar.LENGTH_LONG);
                snackBar.show();
            }
        });

        btnCheckin = (Button) rootView.findViewById(R.id.btnCheckin);
        btnCheckin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_manager, new CheckinByButtonFragment()).commit();
                getActivity().setTitle("Checkin");
            }
        });
        return rootView;
    }

}
