package rosie.com.rosiebeauty;


import android.app.Activity;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class ScheduleFragment extends Fragment {

    private CalendarView calendarView;

    private ArrayList<MultiViewModel> timeList;
    private RecyclerView timeHolder;
    private View rootView;
    private int selectedDayOfWeek;


    private String[] monday_schedule = new String[]{
            "7:30", "8:30", "9:30", "13:30", "15:30", "8:30"
    };

    private String[] tuesday_schedule = new String[]{
            "7:30", "8:30", "11:20", "13:30", "16:30", "20:30", "22:30"
    };

    private String[] wednesday_schedule = new String[]{
            "7:30", "8:30", "12:30", "13:30", "15:30"
    };

    private String[] thursday_schedule = new String[]{
            "7:30", "8:30", "10:30", "13:30", "15:30", "20:30", "21:30", "23:00"
    };
    private String[] friday_schedule = new String[]{
            "7:45", "8:50", "9:15", "13:30"
    };
    private String[] saturday_schedule = new String[]{
            "8:00", "8:30", "8:45", "13:30", "15:30", "20:30"
    };
    private String[] sunday_schedule = new String[]{
            "7:00", "8:30", "11:30", "13:30", "15:30", "20:30"
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
        selectedDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        updateSchedule();


        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Date selectedDate = convertCalendarInfoToDate(year, month, dayOfMonth);
                ScheduleFragment.this.selectedDayOfWeek = convertDateToDayOfWeek(selectedDate);
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


    private String[] getScheduleOnDayOfWeek(int dayOfWeek) {
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
        String[] schedule = getScheduleOnDayOfWeek(selectedDayOfWeek);
        MultiViewModel gridViewModel = null;
        for (int i = 0; i < schedule.length; i++) {
            gridViewModel = new MultiViewModel(MultiViewModel.TYPE_BUTTON_TIME_SCHEDULE, schedule[i]);
            timeList.add(gridViewModel);
        }
    }

}
