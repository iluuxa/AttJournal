package stu.ilexa.testjournal1;

import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.TreeSet;

public class Schedule{
    public final static int weekCount = 16;
    public final static int dayCount = 6;
    public final static int classCount = 6;
    public final static String TAG = "MySchedule";
    private static Subject[][][] schedule = new Subject[weekCount][dayCount][classCount];
    private static TreeSet<Subject> subjects = new TreeSet<>();

    /**
     * Инициализация и обнуление расписания
     */
    public static void eraseSchedule(){
        for(int i=0;i<schedule.length;i++){
            for(int j=0;j<schedule[i].length;j++){
                for(int k=0;k<schedule[j].length;k++){
                    schedule[i][j][k]=null;
                }
            }
        }
    }

    public static void importSaveData(Subject[][][] schedule){
        Schedule.subjects=new TreeSet<>();
        Schedule.schedule = schedule;
        for (int i = 0; i < Schedule.weekCount; i++) {
            for (int j = 0; j < Schedule.dayCount; j++) {
                for (int k = 0; k < Schedule.classCount; k++) {
                    if(schedule[i][j][k]!=null){
                        subjects.add(schedule[i][j][k]);
                    }
                }
            }
        }
        for (int i = 0; i < subjects.size(); i++) {
            Log.d(TAG, "importSaveData: "+subjects.toArray(new Subject[0])[i].getName());
        }

    }


    public static void removeSubject(Subject subject){
        for (int i = 0; i < Schedule.weekCount; i++) {
            for (int j = 0; j < Schedule.dayCount; j++) {
                for (int k = 0; k < Schedule.classCount; k++) {
                    if (subject.equals(schedule[i][j][k])) {
                        schedule[i][j][k]=null;
                    }
                }
            }
        }
        subjects.remove(subject);
    }

    public static boolean[] findSubjectWeeks(Subject subject){
        boolean[] tempWeeks = new boolean[Schedule.weekCount];
        for (int i = 0; i < Schedule.weekCount; i++) {
            tempWeeks[i]=false;
        }

        for (int i = 0; i < Schedule.weekCount; i++) {
            for (int j = 0; j < Schedule.dayCount; j++) {
                for (int k = 0; k < Schedule.classCount; k++) {
                    if (subject == schedule[i][j][k]) {
                        tempWeeks[i] = true;
                        break;
                    }
                }
            }
        }
        return tempWeeks;
    }


    public static void rewriteSubject(Subject subject, boolean[] weekChecks, boolean[][] classChecks){
        for (int i = 0; i < Schedule.weekCount; i++) {
            if (weekChecks[i]) {
                for (int j = 0; j < Schedule.dayCount; j++) {
                    for (int k = 0; k < Schedule.classCount; k++) {
                        if (classChecks[j][k]) { Schedule.changeSubject(i, j, k, subject); }
                        else{
                            if(subject==Schedule.getSchedule()[i][j][k]){
                                Schedule.changeSubject(i,j,k,null);
                            }
                        }
                    }
                }
            }
        }

    }

    public static void testInit(){
        eraseSchedule();
        Subject subj1=new Subject("Основы сетевых технологий", false);
        Subject subj2=new Subject("Тестирование и верификация программного обеспечения", false);
        Subject subj3=new Subject("Интерфейсы прикладного программирования", false);
        Subject subj4=new Subject("Моделирование бизнес-процессов", false);
        Subject subj5=new Subject("Технологические основы интернета вещей", false);
        Subject subj6=new Subject("Разработка серверных частей интернет ресурсов", false);
        Subject subj7=new Subject("Моделирование сред и разработка приложений", false);
        Subject subj8=new Subject("Разработка баз данных", false);
        Subject subj9=new Subject("Архитектура клиент-серверных приложений", false);
        Subject subj10=new Subject("Безопасность жизнедеятельности", false);
        for (int i = 0;i<weekCount;i++){
            schedule[i][0][0]=subj1;
            schedule[i][1][4]=subj2;
            schedule[i][1][5]=subj3;
            schedule[i][2][4]=subj4;
            schedule[i][2][5]=subj4;
            schedule[i][4][2]=subj5;
            schedule[i][5][2]=subj6;
            schedule[i][5][3]=subj7;
            schedule[i][5][5]=subj8;
            if ((i+1)%2==0){
                schedule[i][0][1]= subj4;
                schedule[i][0][2]= subj8;
                schedule[i][0][3]= subj2;
                schedule[i][2][3]= subj9;
                schedule[i][3][0]= subj7;
                schedule[i][3][1]= subj9;
                schedule[i][3][2]= subj3;
                schedule[i][5][4]= subj8;
                schedule[i][5][1]= subj6;
            }
            else{
                schedule[i][0][1]=subj5;
                schedule[i][0][2]=subj10;
                schedule[i][1][3]=subj1;
                schedule[i][3][0]=subj6;
                schedule[i][5][4]= subj7;
            }
        }
    }

    public static Subject[][][] getSchedule() {
        return schedule;
    }


    public static int[] getLastClass(){
        int i = DateControl.getCurrentWeek();
        int j = DateControl.getCurrentDay();
        int k = DateControl.getCurrentClass();
        for (;i>=0;i--){
            for (;j>=0;j--){
                for (;k>=0;k--){
                    if (schedule[i][j][k]!=null)
                    {return new int[]{i,j,k};}
                }
            }
        }
        return getFutureClass();
    }


    public static int[] getPreviousClass(int week, int day, int dayClass){
        int j = day;
        int k = dayClass-1;
        for (int i = week;i>=0;i--){
            for (;j>=0;j--){
                for (;k>=0;k--){
                    if (schedule[i][j][k]!=null)
                    {return new int[]{i,j,k};}
                }
                k=classCount-1;
            }
            j=dayCount-1;
        }
        return null;
    }


    public static int[] getNextClass(int week, int day, int dayClass){
        int j = day;
        int k = dayClass+1;
        for (int i = week;i<weekCount;i++){
            for (;j<dayCount;j++){
                for (;k<classCount;k++){
                    if (schedule[i][j][k]!=null)
                    {return new int[]{i,j,k};}
                }
                k=0;
            }
            j=0;
        }
        return null;
    }


    public static int[] getFutureClass(){
        int i = DateControl.getCurrentWeek();
        int j = DateControl.getCurrentDay();
        int k = DateControl.getCurrentClass();
        for (;i<weekCount;i++){
            for (;j<dayCount;j++){
                for (;k<classCount;k++){
                    if (schedule[i][j][k]!=null)
                    {return new int[]{i,j,k};}
                }
            }
        }
        return null;
    }


    public static ArrayList<int[]> getSubjectDates(Subject subject){
        ArrayList<int[]> dates= new ArrayList<>();
        for (int i = 0; i < Schedule.weekCount; i++) {
            for (int j = 0; j < Schedule.dayCount; j++) {
                for (int k = 0; k < Schedule.classCount; k++) {
                    if(subject.equals(schedule[i][j][k])){
                        dates.add(DateControl.getSelectedDate(i,j));
                    }
                }
            }
        }
        return dates;
    }


    public static ArrayList<int[]> getSubjectIndex(Subject subject){
        ArrayList<int[]> dates= new ArrayList<>();
        for (int i = 0; i < Schedule.weekCount; i++) {
            for (int j = 0; j < Schedule.dayCount; j++) {
                for (int k = 0; k < Schedule.classCount; k++) {
                    if(subject.equals(schedule[i][j][k])){
                        dates.add(new int[]{i,j,k});
                    }
                }
            }
        }
        return dates;
    }


    public static void changeSubject(int week, int day, int dayClass,@Nullable Subject subject){
        Schedule.schedule[week][day][dayClass]=subject;
    }

    public static TreeSet<Subject> getSubjects() {
        return subjects;
    }

}
