package stu.ilexa.testjournal1;

import java.util.HashSet;
import java.util.TreeSet;

public class Schedule{
    public final static int weekCount = 16;
    public final static int dayCount = 6;
    public final static int classCount = 6;
    private static Subject[][][] schedule = new Subject[weekCount][dayCount][classCount];
    private static TreeSet<Subject> subjects = new TreeSet<>();

    /**
     * Инициализация и обнуление расписания
     */
    public static void eraseSchedule(){
        Subject empty = new Subject("None",false);
        for(int i=0;i<schedule.length;i++){
            for(int j=0;j<schedule[i].length;j++){
                for(int k=0;k<schedule[j].length;k++){
                    schedule[i][j][k]=empty;
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
                schedule[i][5][4]= subj8;
            }
        }
    }

    public static Subject[][][] getSchedule() {
        return schedule;
    }
    

    public static void changeSubject(int week, int day, int dayClass, Subject subject){
        Schedule.schedule[week][day][dayClass]=subject;
    }

    public static TreeSet<Subject> getSubjects() {
        return subjects;
    }

}
