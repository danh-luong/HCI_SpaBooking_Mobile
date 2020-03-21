package rosie.com.rosiebeauty.Model;

public class ManagerViewModel {

    public static final int CREATE_NEW_SERVICE = 0;
    public static final int SHOW_SERVICE_MANAGER = 1;
    public int type;
    public int data;
    public String text;
    public String edtNameService;
    public String edtDesService;
    public String edtPrice;
    public String edtQPromotion;
    public String edtPricePromo;
    public String edtStartDay;
    public String edtEndDay;
    public int spinnerCatagory;

    public ManagerViewModel(int type, String edtNameService, String edtDesService, String edtPrice,
                            String edtQPromotion, String edtPricePromo, String edtStartDay, String edtEndDay, int spinnerCatagory) {
        this.type = type;
        this.edtNameService = edtNameService;
        this.edtDesService = edtDesService;
        this.edtPrice = edtPrice;
        this.edtQPromotion = edtQPromotion;
        this.edtPricePromo = edtPricePromo;
        this.edtStartDay = edtStartDay;
        this.edtEndDay = edtEndDay;
        this.spinnerCatagory = spinnerCatagory;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getEdtNameService() {
        return edtNameService;
    }

    public void setEdtNameService(String edtNameService) {
        this.edtNameService = edtNameService;
    }

    public String getEdtDesService() {
        return edtDesService;
    }

    public void setEdtDesService(String edtDesService) {
        this.edtDesService = edtDesService;
    }

    public String getEdtPrice() {
        return edtPrice;
    }

    public void setEdtPrice(String edtPrice) {
        this.edtPrice = edtPrice;
    }

    public String getEdtQPromotion() {
        return edtQPromotion;
    }

    public void setEdtQPromotion(String edtQPromotion) {
        this.edtQPromotion = edtQPromotion;
    }

    public String getEdtPricePromo() {
        return edtPricePromo;
    }

    public void setEdtPricePromo(String edtPricePromo) {
        this.edtPricePromo = edtPricePromo;
    }

    public String getEdtStartDay() {
        return edtStartDay;
    }

    public void setEdtStartDay(String edtStartDay) {
        this.edtStartDay = edtStartDay;
    }

    public String getEdtEndDay() {
        return edtEndDay;
    }

    public void setEdtEndDay(String edtEndDay) {
        this.edtEndDay = edtEndDay;
    }

    public int getSpinnerCatagory() {
        return spinnerCatagory;
    }

    public void setSpinnerCatagory(int spinnerCatagory) {
        this.spinnerCatagory = spinnerCatagory;
    }
}
