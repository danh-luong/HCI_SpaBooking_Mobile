package rosie.com.rosiebeauty;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.ArrayList;

public class EditBranchActivity extends AppCompatActivity {
    private EditText edtTitle;
    private EditText edtAddress;
    private Spinner spinnerStatus;
    private ImageView itemImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_branch);

        String title = getIntent().getExtras().getString("title");
        String address = getIntent().getExtras().getString("address");
        String status = getIntent().getExtras().getString("status");
        int image = getIntent().getExtras().getInt("image");

        edtTitle = findViewById(R.id.edtTitle);
        edtAddress = findViewById(R.id.edtAddress);
        spinnerStatus = findViewById(R.id.spinnerStatus);
        itemImage = findViewById(R.id.itemImage);

        edtTitle.setHint(title);
        edtAddress.setHint(address);
        ArrayList<String> statusList = new ArrayList<>();
        statusList.add("đạng hoạt động");
        statusList.add("tạm nghỉ");
        ArrayAdapter<String> statusSpinnerAdapter = new ArrayAdapter<String>
                (getApplicationContext(),
                        android.R.layout.simple_spinner_dropdown_item, statusList);
        spinnerStatus.setAdapter(statusSpinnerAdapter);
        spinnerStatus.setSelection(statusList.indexOf(status));
        itemImage.setImageResource(image);

    }


}
