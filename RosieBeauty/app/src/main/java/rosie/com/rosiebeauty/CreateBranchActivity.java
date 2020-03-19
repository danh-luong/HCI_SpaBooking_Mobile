package rosie.com.rosiebeauty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class CreateBranchActivity extends AppCompatActivity {
    private static int RESULT_LOAD_IMAGE = 1;
    private EditText edtTitle;
    private EditText edtAddress;
    private Spinner spinnerStatus;
    private ImageView itemImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_branch);

        edtTitle = findViewById(R.id.edtTitle);
        edtAddress = findViewById(R.id.edtAddress);
        spinnerStatus = findViewById(R.id.spinnerStatus);
        itemImage = findViewById(R.id.itemImage);

        ArrayList<String> statusList = new ArrayList<>();
        statusList.add("lựa chọn trạng thái");
        statusList.add("đạng hoạt động");
        statusList.add("tạm nghỉ");
        ArrayAdapter<String> statusSpinnerAdapter = new ArrayAdapter<String>
                (getApplicationContext(),
                        android.R.layout.simple_spinner_dropdown_item, statusList);
        spinnerStatus.setAdapter(statusSpinnerAdapter);
        spinnerStatus.setSelection(statusList.indexOf("lựa chọn trạng thái"));
    }

    public void clickToCreate(View view) {
    }

    public void clickToUploadImage(View view) {
        Intent i = new Intent(
                Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(i, RESULT_LOAD_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                itemImage = findViewById(R.id.itemImage);
                itemImage.setImageBitmap(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
            }

        } else {
            Toast.makeText(this, "You haven't picked Image", Toast.LENGTH_LONG).show();
        }
    }
}
