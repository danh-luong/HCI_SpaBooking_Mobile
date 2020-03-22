package rosie.com.rosiebeauty;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ListServiceFragment extends Fragment {

    private View rootView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_list_service, container, false);
        final EditText edtSearchService = (EditText) rootView.findViewById(R.id.edtSearchService);
        FloatingActionButton btnSearchService = (FloatingActionButton) rootView.findViewById(R.id.btnSearchService);
        btnSearchService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchService = edtSearchService.getText().toString();

            }
        });
        return rootView;
    }
}
