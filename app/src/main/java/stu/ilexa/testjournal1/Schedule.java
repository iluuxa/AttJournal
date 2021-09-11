package stu.ilexa.testjournal1;

import java.util.HashSet;
import java.util.TreeSet;

public class Schedule{
    public final static int weekCount = 16;
    public final static int dayCount = 6;
    public final static int classCount = 6;
    public static Subject[][][] schedule = new Subject[weekCount][dayCount][classCount];
    public static TreeSet<Subject> subjects = new TreeSet<>();

    /**
     * Инициализация и обнуление расписания
     */
    public static void eraseSchedule(){
        Subject empty = new Subject("None");
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
        Subject subj1=new Subject("Основы сетевых технологий");
        Subject subj2=new Subject("Тестирование и верификация программного обеспечения");
        Subject subj3=new Subject("Интерфейсы прикладного программирования");
        Subject subj4=new Subject("Моделирование бизнес-процессов");
        Subject subj5=new Subject("Технологические основы интернета вещей");
        Subject subj6=new Subject("Разработка серверных частей интернет ресурсов");
        Subject subj7=new Subject("Моделирование сред и разработка приложений");
        Subject subj8=new Subject("Разработка баз данных");
        Subject subj9=new Subject("Архитектура клиент-серверных приложений");
        Subject subj10=new Subject("Безопасность жизнедеятельности");
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





}
