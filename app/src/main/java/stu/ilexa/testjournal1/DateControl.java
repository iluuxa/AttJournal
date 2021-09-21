package stu.ilexa.testjournal1;

import android.util.Log;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class DateControl {
    private static final String TAG = "MyDateControl";
    private static int firstDay = 1;
    private static int firstMonth = 9;
    private static int firstYear = 2021;
    private static int classBeginningHour = 9;
    private static int classBeginningMinute = 0;
    private static int classDuration = 90;
    private static int breakDurationMinute = 10;
    private static int bigBreakDurationMinute = 30;
    private static Integer[] bigBreakAfterClass = {1,3};
    private static final Calendar beginning = new GregorianCalendar(firstYear,firstMonth-1,firstDay,classBeginningHour,classBeginningMinute);

    public DateControl() {
    }

    public static void importSaveData(int firstDay,int firstMonth,int firstYear,int classBeginningHour,int classBeginningMinute,int classDuration,int breakDurationMinute,int bigBreakDurationMinute,Integer[] bigBreakAfterClass){
        DateControl.setFirstDay(firstDay);
        DateControl.setFirstMonth(firstMonth);
        DateControl.setFirstYear(firstYear);
        DateControl.setClassBeginningHour(classBeginningHour);
        DateControl.setClassBeginningMinute(classBeginningMinute);
        DateControl.setClassDuration(classDuration);
        DateControl.setBreakDurationMinute(breakDurationMinute);
        DateControl.setBigBreakDurationMinute(bigBreakDurationMinute);
        DateControl.setBigBreakAfterClass(bigBreakAfterClass);

    }

    public static int getCurrentWeek(){
        Calendar now = Calendar.getInstance();
        beginning.setFirstDayOfWeek(Calendar.MONDAY);
        now.setFirstDayOfWeek(Calendar.MONDAY);
        //Log.d(TAG, "getCurrentWeek: "+now.get(Calendar.WEEK_OF_YEAR)+"   beg: "+beginning.get(Calendar.WEEK_OF_YEAR));
        int sub = now.get(Calendar.WEEK_OF_YEAR) - beginning.get(Calendar.WEEK_OF_YEAR);
        Log.d(TAG, "getCurrentWeek: "+sub);
        if(sub<0){return 0;}
        if(sub>(Schedule.weekCount-1)){return Schedule.weekCount-1;}
        return sub;

    }

    public static int getCurrentDay(){
        Calendar now = Calendar.getInstance();
        int res = now.get(Calendar.DAY_OF_WEEK)-2;
        Log.d(TAG, "getCurrentDay: "+res);
        if(res<0){return Schedule.dayCount-1;}
        else {
            return res;
        }
    }

    public static int getCurrentClass(){
        Calendar now = Calendar.getInstance();
        if(now.get(Calendar.HOUR_OF_DAY)-beginning.get(Calendar.HOUR_OF_DAY)<0){return 0;}
        int minutes = now.get(Calendar.MINUTE)-classBeginningMinute+(now.get(Calendar.HOUR_OF_DAY)-classBeginningHour)*60;
        int res =0;
        int check = classDuration;
        while (minutes>=check){
            res++;
            check+=classDuration+breakDurationMinute;
            for (int i = 0; i < bigBreakAfterClass.length; i++) {
                if(res==bigBreakAfterClass[i]){check+=(bigBreakDurationMinute-breakDurationMinute);}
            }
        }
        if(res>=Schedule.classCount){return Schedule.classCount-1;}
        return res;

    }


    public static int getClassStartTime(int dayClass){
        int minutes=classBeginningMinute+classBeginningHour*60;
        for (int i = 0; i < dayClass; i++) {
            minutes+=classDuration;
            minutes+=breakDurationMinute;
            for (int j = 0; j < bigBreakAfterClass.length; j++) {
                if(i==bigBreakAfterClass[j]){
                    minutes+=(bigBreakDurationMinute-breakDurationMinute);
                }
            }
        }
        return minutes;

    }

    public static int getClassEndTime(int dayClass){
        int minutes=classBeginningMinute+classBeginningHour*60;
        for (int i = 0; i < dayClass; i++) {
            minutes+=classDuration;
            minutes+=breakDurationMinute;
            for (int j = 0; j < bigBreakAfterClass.length; j++) {
                if(i==bigBreakAfterClass[j]){
                    minutes+=(bigBreakDurationMinute-breakDurationMinute);
                }
            }
        }
        return minutes+classDuration;

    }


    public static int[] getSelectedDate(int week, int day){
        Calendar result = (Calendar) beginning.clone();
        result.setFirstDayOfWeek(Calendar.MONDAY);
        if(week>=1){
        result.add(Calendar.DAY_OF_YEAR, (week-1)*7+day+(7-(beginning.get(Calendar.DAY_OF_WEEK)-2)));}
        else {
            result.add(Calendar.DAY_OF_YEAR,day-(beginning.get(Calendar.DAY_OF_WEEK)-2));
        }
        return new int[]{result.get(Calendar.DAY_OF_MONTH),result.get(Calendar.MONTH)+1};
    }


    public static String minutesToTimeFormat(int minutes){
        int hours = (int)(Math.floor(minutes/60));
        minutes %= 60;
        String hoursString = "";
        String minutesString = "";
        if(hours<10){hoursString+="0";}
        if(minutes<10){minutesString+="0";}
        hoursString+=String.valueOf(hours);
        minutesString+=String.valueOf(minutes);
        return hoursString+":"+minutesString;
    }


    public static String getDateFormat(int year, int month, int day){
        String date = "";
        if(day<10){
            date+="0";
        }
        date+=day+".";
        if(month<10){
            date+="0";
        }
        return date+month+"."+year;
    }

    public static String getBigBreaksString(){
        if(bigBreakAfterClass.length==0){return "-";}
        StringBuilder result = new StringBuilder(String.valueOf(bigBreakAfterClass[0]+1));
        for (int i = 1; i < bigBreakAfterClass.length; i++) {
            result.append(", ").append(bigBreakAfterClass[i]+1);
        }
        return result.toString();
    }


    public static int getFirstDay() {
        return firstDay;
    }

    public static void setFirstDay(int firstDay) {
        DateControl.firstDay = firstDay;
        beginning.set(Calendar.DAY_OF_MONTH,firstDay);
    }

    public static int getFirstMonth() {
        return firstMonth;
    }

    public static void setFirstMonth(int firstMonth) {
        DateControl.firstMonth = firstMonth;
        beginning.set(Calendar.MONTH,firstMonth-1);
    }

    public static int getFirstYear() {
        return firstYear;
    }

    public static void setFirstYear(int firstYear) {
        DateControl.firstYear = firstYear;
        beginning.set(Calendar.YEAR,firstYear);
    }

    public static int getClassBeginningHour() {
        return classBeginningHour;
    }

    public static void setClassBeginningHour(int classBeginningHour) {
        DateControl.classBeginningHour = classBeginningHour;
        beginning.set(Calendar.HOUR_OF_DAY,classBeginningHour);
    }

    public static int getClassBeginningMinute() {
        return classBeginningMinute;
    }

    public static void setClassBeginningMinute(int classBeginningMinute) {
        DateControl.classBeginningMinute = classBeginningMinute;
        beginning.set(Calendar.MINUTE,classBeginningMinute);
    }

    public static int getClassDuration() {
        return classDuration;
    }

    public static void setClassDuration(int classDuration) {
        DateControl.classDuration = classDuration;
    }

    public static int getBreakDurationMinute() {
        return breakDurationMinute;
    }

    public static void setBreakDurationMinute(int breakDurationMinute) {
        DateControl.breakDurationMinute = breakDurationMinute;
    }

    public static int getBigBreakDurationMinute() {
        return bigBreakDurationMinute;
    }

    public static void setBigBreakDurationMinute(int bigBreakDurationMinute) {
        DateControl.bigBreakDurationMinute = bigBreakDurationMinute;
    }

    public static Integer[] getBigBreakAfterClass() {
        return bigBreakAfterClass;
    }

    public static void setBigBreakAfterClass(Integer[] bigBreakAfterClass) {
        for (int i = 0; i < bigBreakAfterClass.length; i++) {
            bigBreakAfterClass[i]--;
        }
        DateControl.bigBreakAfterClass = bigBreakAfterClass;
    }
}
