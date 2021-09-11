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
        for (int i = 0;i<weekCount;i++){
            schedule[i][0][0]=new Subject("Основы сетевых технологий");
            schedule[i][1][4]=new Subject("Тестирование и верификация программного обеспечения");
            schedule[i][1][5]=new Subject("Интерфейсы прикладного программирования");
            schedule[i][2][4]=new Subject("Моделирование бизнес-процессов");
            schedule[i][2][5]=schedule[i][2][4];
            schedule[i][4][2]=new Subject("Технологические основы интернета вещей");
            schedule[i][5][2]=new Subject("Разработка серверных частей интернет ресурсов");
            schedule[i][5][3]=new Subject("Моделирование сред и разработка приложений");
            schedule[i][5][5]=new Subject("Разработка баз данных");
            if ((i+1)%2==0){
                schedule[i][0][1]= schedule[i][2][4];
                schedule[i][0][2]= schedule[i][5][5];
                schedule[i][0][3]= schedule[i][1][4];
                schedule[i][2][3]=new Subject("Архитектура клиент-серверных приложений");
                schedule[i][3][0]=schedule[i][5][3];
                schedule[i][3][1]= schedule[i][2][3];
                schedule[i][3][2]= schedule[i][1][5];
                schedule[i][5][4]= schedule[i][5][5];
                schedule[i][5][1]=schedule[i][5][2];
            }
            else{
                schedule[i][0][1]=schedule[i][4][2];
                schedule[i][0][2]=new Subject("Безопасность жизнедеятельности");
                schedule[i][1][3]=schedule[i][0][0];
                schedule[i][3][0]=schedule[i][5][2];
                schedule[i][5][4]= schedule[i][5][3];
            }
        }
    }





}
