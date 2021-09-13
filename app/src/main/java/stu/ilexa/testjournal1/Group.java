package stu.ilexa.testjournal1;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

public class Group {
    private static Student[] group;
    private static final String TAG="MyGroup";


    public static Student[] getGroup() {
        return group;
    }


    public Group() {
        testInit();
    }


    public static void sort(){
        Student buff;
        ArrayList<Student> disorderedStudents=new ArrayList<>();
        ArrayList<Student> orderedStudents=new ArrayList<>();
        for (int i = 0; i < group.length; i++) {
            if (group[i].getOrder()>0){
                orderedStudents.add(group[i]);
            }
            else{
                disorderedStudents.add(group[i]);
            }
        }

    }

    public static boolean add(Student student){
        //Log.d(TAG, "add: student: " +student.getName()+"\n"+ Arrays.toString(group));
        //Log.d(TAG, "testInit: "+student.getName()+" "+student.getOrder());
        Student[] temp = new Student[group.length+1];
        Student buff = student;
        boolean ordered = false;
        int j = 0;
        if(student.getOrder()>0){
            if(student.getOrder()<= group.length){
                if (group[student.getOrder()-1].getOrder()>0
                        &&group[student.getOrder()-1].getOrder()<= group.length)
                {
                    return false;
                }
                temp[student.getOrder()-1] = student;
                ordered=true;
            }
        }

        for (int i = 0; i < group.length; i++) {
            if(group[i].getOrder()>0
                    &&group[i].getOrder()!=(i+1)
                    &&group[i].getOrder()<=group.length+1)
            {
                temp[group[i].getOrder()-1]=group[i];
            }
            else {
                if (group[i].getOrder() == (i + 1)) {
                    temp[i] = group[i];
                }
                else {
                    while ((temp[j] != null) && (j<temp.length-1)) {
                        j++;
                    }
                    if(!ordered) {
                        if (buff.compareTo(group[i]) < 0) {
                            temp[j] = buff;
                            buff = group[i];
                        } else {
                            temp[j] = group[i];
                        }
                    }
                    else {
                        temp[j]=group[i];
                    }
                    j++;
                }
            }
        }

        if(temp[group.length]==null){
            temp[group.length]=buff;
        }

        for (int i = 0; i < temp.length; i++) {
            if(temp[i]!=null){
            Log.d(TAG, "add: "+(i+1)+"   "+temp[i].getName());}
            else{
                Log.d(TAG, "add: Null");
            }

        }
        Log.d(TAG, "--------------------------------------------------------");
        group=temp;
        return true;
    }


    public static void testInit(){
        group = new Student[0];
        add(new Student("Акопян","Максим","Игоревич"));
        add(new Student("Аралкина","Евгения","Максимовна"));
        add(new Student("Буланов","Владимир","Алексеевич",3));
        add(new Student("Глазков","Максим","Дмитриевич",4));
        add(new Student("Морозов","Лев","Александрович",29));
        add(new Student("Глухов","Владимир","Игоревич",5));
        add(new Student("Гриненко","Елизавета","Андреевна",6));
        add(new Student("Дараган","Федор","Алексеевич"));
        add(new Student("Ирицкий","Александр","Игоревич"));
        add(new Student("Колесников","Вадим","Вячеславович"));
        add(new Student("Корепанова","Диана","Сергеевна"));
        add(new Student("Кравцова","Екатерина","Юрьевна"));
        add(new Student("Магин","Константин","Андреевич"));
        add(new Student("Литвинов","Карим","Максимович",31));
        add(new Student("Максимова","Ольга","Евгеньевна"));
        add(new Student("Мамонов","Сергей","Александрович"));
        add(new Student("Михина","Ирина","Павловна"));
        add(new Student("Нечипоренко","Дмитрий","Олегович",16));
        add(new Student("Николаев","Даниил","Александрович"));
        add(new Student("Парамошкин","Егор","Валерьевич"));
        add(new Student("Полетаев","Владимир","Викторович",19));
        add(new Student("Прикупец","Александр","Андреевич"));
        add(new Student("Сороков","Артём","Сергеевич"));
        add(new Student("Старовойтов","Фёдор","Дмитриевич"));
        add(new Student("Сычёв","Владимир","Константинович"));
        add(new Student("Тимофеева","Софья","Леонидовна"));
        add(new Student("Титов","Феликс","Александрович"));
        add(new Student("Харитонов","Дмитрий","Михайлович"));
        add(new Student("Ясько","Илья","Артёмович"));
        add(new Student("Мамедли","Эмиль","Вугар оглы",28));
        add(new Student("Баженов","Владимир","Сергеевич",30));



    }
}
