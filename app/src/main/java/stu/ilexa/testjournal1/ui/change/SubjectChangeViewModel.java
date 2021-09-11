package stu.ilexa.testjournal1.ui.change;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Arrays;
import java.util.HashSet;

import stu.ilexa.testjournal1.Schedule;
import stu.ilexa.testjournal1.Subject;

public class SubjectChangeViewModel extends ViewModel {
    private MutableLiveData<boolean[][]> classChecks;
    private MutableLiveData<boolean[]> weekChecks;
    private boolean type=false;


    public SubjectChangeViewModel(){
        classChecks = new MutableLiveData<>();
        weekChecks = new MutableLiveData<>();

    }


    public void findChecks(Subject object){
        boolean[] tempWeeks = new boolean[Schedule.weekCount];
        boolean[][] tempClasses = new boolean[Schedule.dayCount][Schedule.]
        for (int i = 0; i < Schedule.weekCount; i++) {
            for (int j = 0; j < Schedule.dayCount; j++) {
                for (int k = 0; k < Schedule.classCount; k++) {
                    if(object == Schedule.schedule[i][j][k]){

                    }
                }
            }
        }
    }


    public void setType(boolean type) {
        this.type = type;
    }


    public void submit(String name, boolean type, boolean[][] classChecks, boolean[] weekChecks){

    }


    public LiveData<boolean[][]> getClassChecks() {
        return classChecks;
    }


    public MutableLiveData<boolean[]> getWeekChecks() {
        return weekChecks;
    }


    public void clear(){
        boolean[] temp = new boolean[Schedule.weekCount];
        for (int i = 0; i < Schedule.weekCount; i++) {
            temp[i]=false;
        }
        weekChecks.setValue(temp);
    }


    public void checkEven(){
        boolean[] temp = new boolean[Schedule.weekCount];
        for (int i = 1; i < Schedule.weekCount; i+=2) {
            temp[i]=false;
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


