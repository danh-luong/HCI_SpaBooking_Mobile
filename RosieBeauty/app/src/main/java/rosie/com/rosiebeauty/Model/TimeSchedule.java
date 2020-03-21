package rosie.com.rosiebeauty.Model;

public class TimeSchedule {
    private String time;
    private String promotion;

    public TimeSchedule(String time, String promotion) {
        this.time = time;
        this.promotion = promotion;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPromotion() {
        return promotion;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }
}
