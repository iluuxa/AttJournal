package stu.ilexa.testjournal1.ui.schedule;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import stu.ilexa.testjournal1.DateControl;
import stu.ilexa.testjournal1.Schedule;
import stu.ilexa.testjournal1.Subject;

public class ScheduleViewModel extends ViewModel {

    private MutableLiveData<Subject[]> mText;
    private int selectedDay;
    private int selectedWeek;

    public ScheduleViewModel() {
        mText = new MutableLiveData<>();
        selectedDay=DateControl.getCurrentDay();
        selectedWeek=DateControl.getCurrentWeek();
        mText.setValue(Schedule.getSchedule()[selectedWeek][selectedDay]);
    }

    public void setSelectedDay(int selectedDay) {
        this.selectedDay = selectedDay;
        mText.setValue(Schedule.getSchedule()[selectedWeek][selectedDay]);
    }

    public void decrementSelectedWeek() {
        if(selectedWeek>0){ this.selectedWeek -=1;
            mText.setValue(Schedule.getSchedule()[selectedWeek][selectedDay]);}
    }

    public void incrementSelectedWeek() {
        if(selectedWeek<15){ this.selectedWeek +=1;
            mText.setValue(Schedule.getSchedule()[selectedWeek][selectedDay]);}
    }

    public LiveData<Subject[]> getText() {
        return mText;
    }

    public int getSelectedDay() {
        return selectedDay;
    }

    public int getSelectedWeek() {
        return selectedWeek;
    }
}