package rosie.com.rosiebeauty.Model;

public class MultiViewModel {
    public static final int TYPE_SLIDESHOW = 0;
    public static final int TYPE_IMAGE_WITH_TEXT = 1;
    public static final int TYPE_IMAGE_INLINE_WITH_TEXT = 2;
    public static final int TYPE_TEXT_INSIDE_IMAGE = 3;
    public static final int TYPE_IMG_TEXT_PRICE = 4;

    public int type;
    public int data;
    public String text;
    public int iconId;

    public MultiViewModel(int type, String text, int data) {
        this.type = type;
        this.data = data;
        this.text = text;
    }

    public MultiViewModel(int type, String text, int data, int iconId) {
        this.type = type;
        this.data = data;
        this.text = text;
        this.iconId = iconId;
    }




}
