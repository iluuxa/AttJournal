package stu.ilexa.testjournal1.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import stu.ilexa.testjournal1.DateControl;
import stu.ilexa.testjournal1.Group;
import stu.ilexa.testjournal1.Schedule;
import stu.ilexa.testjournal1.Student;

public class AttendanceViewModel extends ViewModel {

    private MutableLiveData<int[]> index;
    private AttendanceViewAdapter attendanceViewAdapter = new AttendanceViewAdapter(Group.getGroup());

    public AttendanceViewModel() {
        index = new MutableLiveData<>();
        int[] temp = Schedule.getLastClass();
        if(temp != null) {
            index.setValue(temp);
        }
        else {
            index.setValue(new int[]{0,0,0});
        }

    }


    public AttendanceViewAdapter getAttendanceViewAdapter() {
        return attendanceViewAdapter;
    }


    public LiveData<int[]> getIndex() {
        return index;
    }


    public void nextSubject(){
        int[] next = Schedule.getNextClass(index.getValue()[0],index.getValue()[1],index.getValue()[2]);
        if(next!=null){
            index.setValue(next);
        }
    }


    public void previousSubject(){
        int[] previous = Schedule.getPreviousClass(index.getValue()[0],index.getValue()[1],index.getValue()[2]);
        if(previous!=null){
            index.setValue(previous);
        }
    }
}