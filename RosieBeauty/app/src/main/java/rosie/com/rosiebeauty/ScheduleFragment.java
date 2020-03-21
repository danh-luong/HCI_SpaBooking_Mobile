package rosie.com.rosiebeauty;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import rosie.com.rosiebeauty.Adapter.MultiViewTypeAdapter;
import rosie.com.rosiebeauty.Model.MultiViewModel;
import rosie.com.rosiebeauty.Model.TimeSchedule;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScheduleFragment extends Fragment {

    private CalendarView calendarView;

    private ArrayList<MultiViewModel> timeList;
    private RecyclerView timeHolder;
    private View rootView;
    private int selectedDayOfWeek;


    private TimeSchedule[] monday_schedule = new TimeSchedule[]{
           new TimeSchedule("7:30", "-20%") , new TimeSchedule("8:30", "-20%"), new TimeSchedule("9:30", ""), new TimeSchedule("13:30", ""), new TimeSchedule("15:30", ""), new TimeSchedule("20:30", "")
    };

    private TimeSchedule[] tuesday_schedule = new TimeSchedule[]{
            new TimeSchedule("7:30", "-15%") , new TimeSchedule("8:30", "-25%"), new TimeSchedule("9:30", ""), new TimeSchedule("13:30", ""), new TimeSchedule("15:30", ""), new TimeSchedule("20:30", "")
    };

    private TimeSchedule[] wednesday_schedule = new TimeSchedule[]{
            new TimeSchedule("7:30", "-30%") , new TimeSchedule("8:30", "-15"), new TimeSchedule("9:30", ""), new TimeSchedule("13:30", ""), new TimeSchedule("15:30", ""), new TimeSchedule("20:30", "")
    };

    private TimeSchedule[] thursday_schedule = new TimeSchedule[]{
            new TimeSchedule("7:30", "-15%") , new TimeSchedule("8:30", ""), new TimeSchedule("9:30", ""), new TimeSchedule("13:30", ""), new TimeSchedule("15:30", ""), new TimeSchedule("20:30", "")
    };
    private TimeSchedule[] friday_schedule = new TimeSchedule[]{
            new TimeSchedule("7:00", "") , new TimeSchedule("8:30", ""), new TimeSchedule("9:30", ""), new TimeSchedule("13:30", ""), new TimeSchedule("15:30", ""), new TimeSchedule("20:30", "")
    };
    private TimeSchedule[] saturday_schedule = new TimeSchedule[]{
            new TimeSchedule("5:30", "") , new TimeSchedule("8:30", ""), new TimeSchedule("9:30", ""), new TimeSchedule("13:30", ""), new TimeSchedule("15:30", ""), new TimeSchedule("20:30", "")
    };
    private TimeSchedule[] sunday_schedule = new TimeSchedule[]{
            new TimeSchedule("6:30", "") , new TimeSchedule("8:30", ""), new TimeSchedule("9:30", ""), new TimeSchedule("13:30", ""), new TimeSchedule("15:30", ""), new TimeSchedule("20:30", "")
    };


    public ScheduleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.activity_schedule_fragment, container, false);
        calendarView = rootView.findViewById(R.id.calviewSchedule);

        long todayTime = Calendar.getInstance().getTimeInMillis();
        calendarView.setDate(todayTime, false, true);
        Calendar calendar = Calendar.getInstance();
        selectedDayOfWeek = 1;
        updateSchedule();


        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Date selectedDate = convertCalendarInfoToDate(year, month, dayOfMonth);
                ScheduleFragment.this.selectedDayOfWeek = convertDateToDayOfWeek(selectedDate) - 2;
                if(selectedDayOfWeek == -1) selectedDayOfWeek = 7;
                updateSchedule();
                setDateOfBookButton(year, month, dayOfMonth);

            }
        });

        return rootView;
    }

    private void setDateOfBookButton(int year, int month, int dayOfMonth) {
        MainActivity.schedule_button_date = dayOfMonth + "-" + month + "-" + year;
        SpaServiceDetailFragment.updateButtonBook();
    }

    private void updateSchedule() {
        prepareData();
        MultiViewTypeAdapter commentAdapter = new MultiViewTypeAdapter(ScheduleFragment.this.timeList, ScheduleFragment.this.getActivity().getApplicationContext());
        ScheduleFragment.this.timeHolder = ScheduleFragment.this.rootView.findViewById(R.id.time_chooser_holder);
        StaggeredGridLayoutManager lmForSuggestion =
                new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        ScheduleFragment.this.timeHolder.setLayoutManager(lmForSuggestion);
        ScheduleFragment.this.timeHolder.setItemAnimator(new DefaultItemAnimator());
        ScheduleFragment.this.timeHolder.setAdapter(commentAdapter);
    }


    private TimeSchedule[] getScheduleOnDayOfWeek(int dayOfWeek) {
        switch (dayOfWeek) {
            case 1:
                return this.monday_schedule;
            case 2:
                return this.tuesday_schedule;
            case 3:
                return this.wednesday_schedule;
            case 4:
                return this.thursday_schedule;
            case 5:
                return this.friday_schedule;
            case 6:
                return this.saturday_schedule;
            case 7:
                return this.sunday_schedule;
        }
        return null;
    }

    private Date convertCalendarInfoToDate(int year, int month, int dayOfMonth) {
        Date date = new Date();
        date.setMonth(month);
        date.setYear(year);
        date.setDate(dayOfMonth);
        return date;
    }


    private int convertDateToDayOfWeek (Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }




    void prepareData() {
        timeList = new ArrayList<>();
        TimeSchedule[] schedule = getScheduleOnDayOfWeek(selectedDayOfWeek);
        MultiViewModel gridViewModel = null;
        for (int i = 0; i < schedule.length; i++) {
            gridViewModel = new MultiViewModel(MultiViewModel.TYPE_BUTTON_TIME_SCHEDULE, schedule[i]);
            timeList.add(gridViewModel);
        }
    }

}
