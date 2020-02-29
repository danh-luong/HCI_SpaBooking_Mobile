package rosie.com.rosiebeauty.Model;

import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.List;

public class MultiViewModel {
    public static final int TYPE_SLIDESHOW = 0;
    public static final int TYPE_IMAGE_WITH_TEXT = 1;
    public static final int TYPE_IMAGE_INLINE_WITH_TEXT = 2;
    public static final int TYPE_TEXT_INSIDE_IMAGE = 3;
    public static final int TYPE_IMG_TEXT_PRICE = 4;
    public static final int TYPE_SECTION_TITLE = 5;
    public static final int TYPE_RECYLERVIEW = 6;
    public static final int TYPE_SQUARE_ICON_TEXT_BELOW = 7;
    public static final int TYPE_APPOINTMENT_ITEM = 8;

    public static final int ORIENTATION_HORIZONTAL = 0;
    public static final int ORIENTATION_VERTICAL = 1;

    public static final int HAS_PROMOTION = 1;
    public static final int NO_PROMOTION = 0;

    public static final int WRAP_CONTENT = -2;
    public static final int MATCH_PARENT = -1;

    public int styleOfContent;
    public int type;
    public int data;
    public String text;
    public int iconId;
    public int orientation;
    public int reclyerSpan;
    public String price;
    public String priceAfterPromotion;
    public List<MultiViewModel> multiViewModels;
    public Appointment appointment;
    public int hasPromotion;
    public int width;
    public int height;

    public static class Appointment {
        public String appointmentCode;
        public String bookingDate;
        public String appointmentDate;
        public String payPrice;

        public Appointment(String appointmentCode, String bookingDate, String appointmentDate, String payPrice) {
            this.appointmentCode = appointmentCode;
            this.bookingDate = bookingDate;
            this.appointmentDate = appointmentDate;
            this.payPrice = payPrice;
        }
    }


    public static class RecyclerChildItem {
        public int image;
        public String text;
        public int type;

        public RecyclerChildItem(int image, String text, int type) {
            this.image = image;
            this.text = text;
            this.type = type;
        }
    }

    public MultiViewModel(int type, Appointment appointment) {
        this.type = type;
        this.appointment = appointment;
    }
    public MultiViewModel(int type, int data) {
        this.type = type;
        this.data = data;
    }
    public MultiViewModel(int type, String text, int data) {
        this.type = type;
        this.data = data;
        this.text = text;
    }

    public MultiViewModel(int type, String text, int data, String price) {
        this.type = type;
        this.data = data;
        this.text = text;
        this.price = price;
    }

    public MultiViewModel(int type, String text, int data, String price, int hasPromotion, String priceAfterPromotion, int width) {
        this.type = type;
        this.data = data;
        this.text = text;
        this.price = price;
        this.hasPromotion = hasPromotion;
        this.priceAfterPromotion = priceAfterPromotion;
        this.width = width;
    }

    public MultiViewModel(int type, int orientation, int reclyerSpan, List<MultiViewModel> multiViewModels) {
        this.type = type;
        this.orientation = orientation;
        this.multiViewModels = multiViewModels;
        this.reclyerSpan = reclyerSpan;
    }

    public MultiViewModel(int type, String text) {
        this.type = type;
        this.text = text;
    }

    public MultiViewModel(int type, String text, int data, int iconId, int styleOfContent) {
        this.type = type;
        this.data = data;
        this.text = text;
        this.iconId = iconId;
        this.styleOfContent = styleOfContent;
    }


}
