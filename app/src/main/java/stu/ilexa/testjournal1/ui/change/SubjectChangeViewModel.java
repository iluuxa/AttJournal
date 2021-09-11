package stu.ilexa.testjournal1.ui.change;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Arrays;
import java.util.HashSet;

import stu.ilexa.testjournal1.Schedule;
import stu.ilexa.testjournal1.Subject;

public class SubjectChangeViewModel extends ViewModel {
    private static final String TAG = "SubjectChangeViewModel";
    private MutableLiveData<boolean[][]> classChecks;
    private MutableLiveData<boolean[]> weekChecks;
    private boolean type=false;


    public SubjectChangeViewModel(){
        classChecks = new MutableLiveData<>();
        weekChecks = new MutableLiveData<>();
        clear();
    }


    public void findChecks(Subject object){
        clear();
        boolean[] tempWeeks = new boolean[Schedule.weekCount];
        boolean[][] tempClasses = new boolean[Schedule.dayCount][Schedule.classCount];
        for (int i = 0; i < Schedule.weekCount; i++) {
            for (int j = 0; j < Schedule.dayCount; j++) {
                for (int k = 0; k < Schedule.classCount; k++) {
                    if(object == Schedule.schedule[i][j][k]){
                        Log.d(TAG, "findChecks: "+object.getName()+i+j+k);
                        tempWeeks[i]=true;
                        tempClasses[j][k]=true;
                    }
                }
            }
        }
        weekChecks.setValue(tempWeeks);
        classChecks.setValue(tempClasses);

    }


    public void setType(boolean type) {
        this.type = type;
    }


    public void submit(String name, String type, boolean[][] classChecks, boolean[] weekChecks){

    }


    public int collisionCheck(boolean[] weeks){
        int count = 0;
        for (int i = 0; i<Schedule.weekCount;i++){
            if ((weeks[i]) && (weekChecks.getValue()[i])){count++;}
        }
        return count;
    }


    public LiveData<boolean[][]> getClassChecks() {
        return classChecks;
    }


    public MutableLiveData<boolean[]> getWeekChecks() {
        return weekChecks;
    }


    public void clear(){
        boolean[] temp = new boolean[Schedule.weekCount];
        boolean[][] tempClass = new boolean[Schedule.dayCount][Schedule.classCount];
        for (int i = 0; i < Schedule.weekCount; i++) {
            temp[i]=false;
        }

        for (int j = 0; j < Schedule.dayCount; j++) {
            for (int k = 0; k < Schedule.classCount; k++) {
                tempClass[j][k]=false;
                }
            }

        weekChecks.setValue(temp);
    }


    public void checkEven(){
        boolean[] temp = new boolean[Schedule.weekCount];
        for (int i = 1; i < Schedule.weekCount; i+=2) {
            temp[i]=true;
        }
        weekChecks.setValue(temp);

    }


    public void checkOdd(){
        boolean[] temp = new boolean[Schedule.weekCount];
        for (int i = 0; i < Schedule.weekCount; i+=2) {
            temp[i]=true;
        }
        weekChecks.setValue(temp);

    }


    public void checkAll(){
        boolean[] temp = new boolean[Schedule.weekCount];
        for (int i = 0; i < Schedule.weekCount; i++) {
            temp[i]=true;
        }
        weekChecks.setValue(temp);
    }
}


