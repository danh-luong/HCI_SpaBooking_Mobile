package rosie.com.rosiebeauty.Model;

public class Service {
    private  int image;
    private String title;
    private int price;
    private int promotion;
    private String category;

    public Service(int image, String title, int price, int promotion, String category) {
        this.image = image;
        this.title = title;
        this.price = price;
        this.promotion = promotion;
        this.category = category;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
