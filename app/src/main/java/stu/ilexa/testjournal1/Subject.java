package stu.ilexa.testjournal1;

import android.util.Log;

import java.util.Comparator;

public class Subject implements Comparable{
    private String name;
    private boolean isLecture;
    private static final String TAG = "Subject";


    public boolean getLecture() {
        return isLecture;
    }


    public void setLecture(boolean lecture) {
        isLecture = lecture;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public Subject(String name,boolean type) {
        this.name = name;
        this.isLecture = type;
        boolean temp = Schedule.getSubjects().add(this);
    }


    public Subject(String name) {
        this.name = name;
        boolean temp = Schedule.getSubjects().add(this);
        Log.d(TAG, name+temp);
    }


    @Override
    public int compareTo(Object o) {
        Subject temp = (Subject) o;
        return (this.name+this.isLecture).compareTo(temp.getName()+temp.isLecture);
    }
}
