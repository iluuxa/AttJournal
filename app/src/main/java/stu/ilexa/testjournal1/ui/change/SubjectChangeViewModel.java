package stu.ilexa.testjournal1.ui.change;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.TreeSet;

import stu.ilexa.testjournal1.Schedule;
import stu.ilexa.testjournal1.Subject;

public class SubjectChangeViewModel extends ViewModel {
    private static final String TAG = "SubjectChangeViewModel";
    private MutableLiveData<boolean[]> weekChecks;


    public SubjectChangeViewModel(){
        weekChecks = new MutableLiveData<>();
        checksClear();
    }


    public void findChecks(Subject object){
        checksClear();
        weekChecks.setValue(Schedule.findSubjectWeeks(object));

    }


    public void submit(Subject subject, boolean[] weekChecks, boolean[][] classChecks){
        Schedule.rewriteSubject(subject,weekChecks,classChecks);

    }


    public void submit(String name, String type,  boolean[] weekChecks, boolean[][] classChecks){
        boolean temp = !type.equals("пр");
        Subject subject = new Subject(name, temp);
        Schedule.rewriteSubject(subject,weekChecks,classChecks);

    }


    public int collisionCheck(boolean[] weeks){
        int count = 0;
        for (int i = 0; i<Schedule.weekCount;i++){
            if(weeks[i]&&weekChecks.getValue()[i]){
                count++;
            }
        }
        return count;
    }

    public TreeSet<String> otherCollisionCheck(boolean[] weeks, boolean[][] classes){

        TreeSet<String> changedSubjects = new TreeSet<>();
        Subject[][][] schedule = Schedule.getSchedule();
        for (int i = 0; i<Schedule.weekCount;i++){
            if(weeks[i]){
                for (int j = 0; j < Schedule.dayCount; j++) {
                    for (int k = 0; k < Schedule.classCount; k++) {
                        if ((classes[j][k])&&(schedule[i][j][k]!=null)){
                            if(schedule[i][j][k].getLecture()){
                                changedSubjects.add(schedule[i][j][k].getName()+"(лк)");}
                            else{
                                changedSubjects.add(schedule[i][j][k].getName()+"(пр)");}
                            }
                        }
                    }
                }
            }

        return changedSubjects;
    }


    public MutableLiveData<boolean[]> getWeekChecks() {
        return weekChecks;
    }


    public void checksClear(){
        boolean[] temp = new boolean[Schedule.weekCount];
        for (int i = 0; i < Schedule.weekCount; i++) {
            temp[i]=false;
        }

        weekChecks.setValue(temp);
    }
}


