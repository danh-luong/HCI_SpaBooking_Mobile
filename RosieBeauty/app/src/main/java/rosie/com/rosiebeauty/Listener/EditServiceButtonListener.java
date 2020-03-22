package rosie.com.rosiebeauty.Listener;

import android.content.Intent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import rosie.com.rosiebeauty.EditServiceActivity;

public class EditServiceButtonListener implements View.OnClickListener{

    private String title;
    private int price;
    private int promotion;
    private int image;
    private String category;
    private AppCompatActivity currentActivity;

    public EditServiceButtonListener(String title, int price, int promotion, int image, String category, AppCompatActivity currentActivity) {
        this.title = title;
        this.price = price;
        this.promotion = promotion;
        this.image = image;
        this.category = category;
        this.currentActivity = currentActivity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPromotion() {
        return promotion;
    }

    public void setPromotion(int promotion) {
        this.promotion = promotion;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public AppCompatActivity getCurrentActivity() {
        return currentActivity;
    }

    public void setCurrentActivity(AppCompatActivity currentActivity) {
        this.currentActivity = currentActivity;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(currentActivity, EditServiceActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("price", price);
        intent.putExtra("image", image);
        intent.putExtra("promotion", promotion);
        intent.putExtra("category", category);
        currentActivity.startActivity(intent);
    }
}
