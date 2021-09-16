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
    private static int[] bigBreakAfterClass = {1,3};
    private static final Calendar beginning = new GregorianCalendar(firstYear,firstMonth-1,firstDay,classBeginningHour,classBeginningMinute);

    public DateControl() {
    }

    public static int getCurrentWeek(){
        Calendar now = Calendar.getInstance();
        beginning.setFirstDayOfWeek(Calendar.MONDAY);
        now.setFirstDayOfWeek(Calendar.MONDAY);
        //Log.d(TAG, "getCurrentWeek: "+now.get(Calendar.WEEK_OF_YEAR)+"   beg: "+beginning.get(Calendar.WEEK_OF_YEAR));
        int sub = now.get(Calendar.WEEK_OF_YEAR) - beginning.get(Calendar.WEEK_OF_YEAR);
        Log.d(TAG, "getCurrentWeek: "+sub);
        if(sub<0){return 0;}
        if(sub>15){return 15;}
        return sub;

    }

    public static int getCurrentDay(){
        Calendar now = Calendar.getInstance();
        int res = now.get(Calendar.DAY_OF_WEEK)-2;
        Log.d(TAG, "getCurrentDay: "+res);
        if(res<0){return 5;}
        else {
            return res;
        }
    }

    public static int getCurrentClass(){
        Calendar now = Calendar.getInstance();
        if(now.get(Calendar.HOUR_OF_DAY)-classBeginningHour<0){return 0;}
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
        return res;

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



}
