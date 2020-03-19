package rosie.com.rosiebeauty.Model;

public class Branch {
    private int image;
    private String title;
    private String address;
    private String status;

    public Branch(int image, String title, String address, String status) {
        this.image = image;
        this.title = title;
        this.address = address;
        this.status = status;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

