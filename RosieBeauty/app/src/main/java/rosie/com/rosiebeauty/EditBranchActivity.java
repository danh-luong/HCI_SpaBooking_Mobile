package rosie.com.rosiebeauty;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

import rosie.com.rosiebeauty.Model.Branch;

public class EditBranchActivity extends AppCompatActivity {
    private static int RESULT_LOAD_IMAGE = 1;
    private EditText edtTitle;
    private EditText edtAddress;
    private Spinner spinnerStatus;
    private ImageView itemImage;


    private String title, address, status, oldTitle;
    private int image = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_branch);
        setStatusBarGradiant(this);

        title = getIntent().getExtras().getString("title");
        oldTitle = getIntent().getExtras().getString("title");
        address = getIntent().getExtras().getString("address");
        status = getIntent().getExtras().getString("status");
        image = getIntent().getExtras().getInt("image");

        edtTitle = findViewById(R.id.edtTitle);
        edtAddress = findViewById(R.id.edtAddress);
        spinnerStatus = findViewById(R.id.spinnerStatus);
        itemImage = findViewById(R.id.itemImage);

        edtTitle.setHint(title);
        edtTitle.setText(title);
        edtAddress.setHint(address);
        edtAddress.setText(address);
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


    public void clickToUploadImage(View view) {
        Intent i = new Intent(
                Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(i, RESULT_LOAD_IMAGE);
    }

    public void clickToUpdateBranch(View view) {
        if(image == 0) return;
        title = edtTitle.getText().toString();
        address = edtAddress.getText().toString();
        status = spinnerStatus.getSelectedItem().toString();
        ArrayList<Branch> branches = BranchManagementFragment.getBranches();
        Branch selectedBranch = null;
        for (Branch branch : branches) {
            if (branch.getTitle().equals(oldTitle) ) {
                selectedBranch = branch;
                break;
            }
        }
        selectedBranch.setImage(image);
        selectedBranch.setTitle(title);
        selectedBranch.setStatus(status);
        selectedBranch.setAddress(address);
        BranchManagementFragment.status = BranchManagementFragment.STATUS_UPDATE;
        onBackPressed();
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

    protected void setStatusBarGradiant(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            Drawable background = activity.getResources().getDrawable(R.drawable.gradient_statusbar);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(activity.getResources().getColor(android.R.color.transparent));
            window.setBackgroundDrawable(background);
        }
    }
}
