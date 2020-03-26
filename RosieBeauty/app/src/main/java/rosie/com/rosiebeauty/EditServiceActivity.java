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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

import rosie.com.rosiebeauty.Model.Service;

public class EditServiceActivity extends AppCompatActivity {
    private static int RESULT_LOAD_IMAGE = 1;

    private ImageView viewPhoto;
    private EditText edtNameService;
    private EditText edtPrice;
    private EditText edtQPromotion;
    private Spinner spinnerCatagory;
    private static final String IMG1 = "spa_01.jpeg";
    private static final String IMG2 = "spa_02.jpg";
    private static final String IMG3 = "spa_03.jpeg";

    private String title, oldTitle, category;
    private int image = 0, promotion, price;
    float pricePromote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_service);
        setStatusBarGradiant(this);

        title = getIntent().getExtras().getString("title");
        oldTitle = getIntent().getExtras().getString("title");
        price = getIntent().getExtras().getInt("price");
        promotion = getIntent().getExtras().getInt("promotion");
        image = getIntent().getExtras().getInt("image");
        category = getIntent().getExtras().getString("category");
        pricePromote = price - price * promotion / 100;


        viewPhoto = findViewById(R.id.viewPhoto);
        edtNameService = findViewById(R.id.edtNameService);
        edtPrice = findViewById(R.id.edtPrice);
        edtQPromotion = findViewById(R.id.edtQPromotion);
        spinnerCatagory = findViewById(R.id.spinnerCatagory);

        viewPhoto.setImageResource(image);
        edtNameService.setText(title);
        edtNameService.setHint(title);
        edtPrice.setText(price + "");
        edtPrice.setHint(price + "");
        edtQPromotion.setText(promotion + "");
        edtQPromotion.setHint(promotion + "");
        ArrayList<String> categoriesList = new ArrayList<>();
        categoriesList.add("massage");
        categoriesList.add("làm tóc");
        categoriesList.add("dưỡng da");
        categoriesList.add("làm móng");
        categoriesList.add("trang điểm");
        ArrayAdapter<String> categorySpinnerAdapter = new ArrayAdapter<String>
                (getApplicationContext(),
                        android.R.layout.simple_spinner_dropdown_item, categoriesList);
        spinnerCatagory.setAdapter(categorySpinnerAdapter);
        spinnerCatagory.setSelection(categoriesList.indexOf(category));
        viewPhoto.setImageResource(image);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                viewPhoto = findViewById(R.id.viewPhoto);
                viewPhoto.setImageBitmap(selectedImage);
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

    public void clickToUploadImage(View view) {
        Intent i = new Intent(
                Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(i, RESULT_LOAD_IMAGE);
    }


    public void clickToUpdateService(View view) {
        if(image == 0) return;
        title = edtNameService.getText().toString();
        price = Integer.parseInt(edtPrice.getText().toString());
        promotion = Integer.parseInt(edtQPromotion.getText().toString());
        category = spinnerCatagory.getSelectedItem().toString();
        ArrayList<Service> services = ServiceManagementFragment.getServices();
        Service selectedService = null;
        for (Service service : services) {
            if (service.getTitle().equals(oldTitle) ) {
                selectedService = service;
                break;
            }
        }
        selectedService.setImage(image);
        selectedService.setTitle(title);
        selectedService.setPrice(price);
        selectedService.setPromotion(promotion);
        selectedService.setCategory(category);
        ServiceManagementFragment.status = ServiceManagementFragment.STATUS_UPDATE;
        onBackPressed();
    }

    private String getImageName(Uri imgUri) {
        String[] filePathColumn = { MediaStore.Images.Media.DATA };
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
}
