package rosie.com.rosiebeauty;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.gms.vision.barcode.Barcode;
import com.notbytes.barcode_reader.BarcodeReaderActivity;

public class ScanFragment extends Fragment {
    private static final int BARCODE_READER_ACTIVITY_REQUEST = 1000;
    TextView checkin;

    public ScanFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_scan, container, false);
        checkin = rootView.findViewById(R.id.checkin);
        checkin.setVisibility(View.GONE);
        Intent launchIntent = BarcodeReaderActivity.getLaunchIntent(this.getContext(), true, false);
        startActivityForResult(launchIntent, BARCODE_READER_ACTIVITY_REQUEST);
        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != Activity.RESULT_OK) {
            checkin.setVisibility(View.VISIBLE);
            Toast.makeText(this.getContext(), "error in  scanning", Toast.LENGTH_SHORT).show();
            return;
        }

        if (requestCode == BARCODE_READER_ACTIVITY_REQUEST && data != null) {
            checkin.setText("Check in thành công");
            checkin.setTextColor(Color.parseColor("#008000"));
            checkin.setVisibility(View.VISIBLE);
            Barcode barcode = data.getParcelableExtra(BarcodeReaderActivity.KEY_CAPTURED_BARCODE);
            Toast.makeText(this.getContext(), barcode.rawValue, Toast.LENGTH_SHORT).show();

        }

    }
}
