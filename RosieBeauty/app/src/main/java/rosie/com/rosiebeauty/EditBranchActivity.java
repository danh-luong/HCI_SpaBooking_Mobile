package rosie.com.rosiebeauty;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;

import rosie.com.rosiebeauty.Data.User;
import rosie.com.rosiebeauty.Data.UserRepository;
import rosie.com.rosiebeauty.Model.Branch;

public class EditBranchActivity extends AppCompatActivity {
    private static int RESULT_LOAD_IMAGE = 1;
    private EditText edtTitle;
    private EditText edtAddress;
    private EditText edtManagerName;
    private Spinner spinnerStatus;
    private ImageView itemImage;
    private Button btnUploadImage, btnActiveSpa, btnDeactiveSpa, btnUpdateSpa;
    private static final String IMG1 = "spa_01.jpeg";
    private static final String IMG2 = "spa_02.jpg";
    private static final String IMG3 = "spa_03.jpeg";

    private String title, address, status, oldTitle;
    private int image = 0;
    private User manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_branch);
        setStatusBarGradiant(this);

        Intent intent = getIntent();

        title = getIntent().getExtras().getString("title");
        oldTitle = getIntent().getExtras().getString("title");
        address = getIntent().getExtras().getString("address");
        status = getIntent().getExtras().getString("status");
        image = getIntent().getExtras().getInt("image");

        manager = (User) intent.getSerializableExtra("manager");

        edtTitle = findViewById(R.id.edtTitle);
        edtTitle.setEnabled(false);
        edtAddress = findViewById(R.id.edtAddress);
        edtAddress.setEnabled(false);
        spinnerStatus = findViewById(R.id.spinnerStatus);
        spinnerStatus.setEnabled(false);
        itemImage = findViewById(R.id.itemImage);
        btnUploadImage = findViewById(R.id.btnUploadImage);
        edtManagerName = findViewById(R.id.edtManagerName);

        edtManagerName.setText(manager.getName());

        btnActiveSpa = findViewById(R.id.btnActiveSpa);
        btnDeactiveSpa = findViewById(R.id.btnDeactiveSpa);
        btnUpdateSpa = findViewById(R.id.btnUpdateSpa);

        btnUpdateSpa.setVisibility(View.GONE);
        if(status.equalsIgnoreCase("vô hiệu hóa")) {
            btnActiveSpa.setVisibility(View.VISIBLE);
        } else {
            btnDeactiveSpa.setVisibility(View.VISIBLE);
        }

        edtTitle.setHint(title);
        edtTitle.setText(title);
        edtAddress.setHint(address);
        edtAddress.setText(address);
        btnUploadImage.setVisibility(View.GONE);

        ArrayList<String> statusList = new ArrayList<>();
        statusList.add("đang hoạt động");
        statusList.add("vô hiệu hóa");
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
        if (image == 0) return;
        title = edtTitle.getText().toString();
        address = edtAddress.getText().toString();
        status = spinnerStatus.getSelectedItem().toString();
        ArrayList<Branch> branches = BranchManagementFragment.getBranches();
        Branch selectedBranch = new Branch();
        for (Branch branch : branches) {
            if (branch.getTitle().equals(oldTitle)) {
                selectedBranch = branch;
                break;
            }
        }
        selectedBranch.setImage(image);
        selectedBranch.setTitle(title);
        selectedBranch.setStatus(status);
        selectedBranch.setAddress(address);
        BranchManagementFragment.status = BranchManagementFragment.STATUS_UPDATE;
        Intent result = new Intent();
        result.putExtra("branch", (Serializable) selectedBranch);
        setResult(AppCompatActivity.RESULT_OK, result);
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
                String imgName = getImageName(imageUri);
                switch (imgName) {
                    case IMG1:
                        image = R.drawable.spa_01;
                        break;
                    case IMG2:
                        image = R.drawable.spa_02;
                        break;
                    case IMG3:
                        image = R.drawable.spa_03;
                        break;
                }
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

    private String getImageName(Uri imgUri) {
        String[] filePathColumn = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(imgUri,
                filePathColumn, null, null, null);
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        String picturePath = cursor.getString(columnIndex);
        cursor.close();
        File f = new File(picturePath);
        String imageName = f.getName();
        return imageName;
    }

    public void clickToActive(View view) {
        Toast.makeText(this, "Đã kích hoạt thành công", Toast.LENGTH_LONG);
        status = "đang hoạt động";
        ArrayList<Branch> branches = BranchManagementFragment.getBranches();
        Branch selectedBranch = new Branch();
        for (Branch branch : branches) {
            if (branch.getTitle().equals(oldTitle)) {
                selectedBranch = branch;
                break;
            }
        }
        UserRepository.userList.get(manager.getUsername()).setStatus("active");
        selectedBranch.setStatus(status);
        BranchManagementFragment.status = BranchManagementFragment.STATUS_UPDATE;
        Intent result = new Intent();
        result.putExtra("branch", (Serializable) selectedBranch);
        setResult(AppCompatActivity.RESULT_OK, result);
        onBackPressed();
    }

    public void clickToDeactive(View view) {
        Toast.makeText(this, "Vô hiệu hóa thành công", Toast.LENGTH_LONG);
        status = "vô hiệu hóa";
        ArrayList<Branch> branches = BranchManagementFragment.getBranches();
        Branch selectedBranch = new Branch();
        for (Branch branch : branches) {
            if (branch.getTitle().equals(oldTitle)) {
                selectedBranch = branch;
                break;
            }
        }
        UserRepository.userList.get(manager.getUsername()).setStatus("deactive");
        selectedBranch.setStatus(status);
        BranchManagementFragment.status = BranchManagementFragment.STATUS_UPDATE;
        Intent result = new Intent();
        result.putExtra("branch", (Serializable) selectedBranch);
        setResult(AppCompatActivity.RESULT_OK, result);
        onBackPressed();
    }

    public void clickToWatchManagerDetail(View view) {
        Intent intent = new Intent(this, ProfileUserActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        intent.putExtra("key", UserRepository.userList.get(manager.getUsername()).getUsername());
        startActivity(intent);
    }
}
