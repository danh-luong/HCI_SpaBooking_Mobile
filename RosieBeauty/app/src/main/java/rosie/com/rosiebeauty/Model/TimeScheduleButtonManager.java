package rosie.com.rosiebeauty.Model;


import android.widget.TextView;

import rosie.com.rosiebeauty.R;

public class TimeScheduleButtonManager {
    public enum ButtonTimeStatus {
        DISABLED,
        UNSELECTED,
        SELECTED
    }
    private TextView btnTime;
    private ButtonTimeStatus btnStatus;

    public void setButtonStatus(ButtonTimeStatus status) {
        switch (status) {
            case DISABLED:
                btnTime.setBackgroundResource(R.drawable.time_disable_item);
                btnStatus = ButtonTimeStatus.DISABLED;
                break;
            case SELECTED:
                btnTime.setBackgroundResource(R.drawable.time_selected_item);
                btnStatus = ButtonTimeStatus.SELECTED;
                break;
            case UNSELECTED:
                btnTime.setBackgroundResource(R.drawable.time_unselected_item);
                btnStatus = ButtonTimeStatus.UNSELECTED;
                break;
        }
    }

    public void selectButton() {

    }

    public TimeScheduleButtonManager(TextView btnTime, ButtonTimeStatus btnStatus) {
        this.btnTime = btnTime;
        this.btnStatus = btnStatus;
    }

    public TextView getBtnTime() {
        return btnTime;
    }

    public void setBtnTime(TextView btnTime) {
        this.btnTime = btnTime;
    }

    public ButtonTimeStatus getBtnStatus() {
        return btnStatus;
    }

    public void setBtnStatus(ButtonTimeStatus btnStatus) {
        this.btnStatus = btnStatus;
    }
}
