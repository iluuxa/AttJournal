package stu.ilexa.testjournal1;

public class Group {
    private static Student[] group= new Student[0];
    private static final String TAG = "MyGroup";


    public static Student[] getGroup() {
        return group;
    }


    public Group() {
        testInit();
    }


    public static void groupSort(){
        Student buff = null;
        boolean buffered=false;
        Student[] temp = new Student[group.length];
        for (Student student : group) {
            if ((student.getOrder() > 0) && (student.getOrder() <= group.length)) {
                temp[student.getOrder() - 1] = student;
            }
        }
        int j=0;
        for (int i = 0; i < group.length; i++) {
            if (!((group[i].getOrder()>0)&&(group[i].getOrder()<= group.length))){

                while ((j< temp.length)&&(temp[j]!=null)){
                    j++;
                }
                if(j>= temp.length){group=temp;return;}
                if(buffered){
                    if(group[i].compareTo(buff)>0){
                        temp[j]=buff;
                        j++;
                        buff=group[i];
                    }
                    else{
                        temp[j]=group[i];
                    }
                }
                else {
                    buff = group[i];
                    buffered=true;
                }
            }
        }
        while ((j< temp.length)&&(temp[j]!=null)){
            j++;
        }
        if(j>= temp.length){group=temp;return;}
        if((temp[j]==null)&&(buff!=null)){temp[j]=buff;}

        group = temp;
        /*
        for (int i = 0; i < group.length; i++) {
            if(group[i]!=null){
            Log.d(TAG, "groupSort: "+(i+1)+" "+group[i].getName());}
            else {
                Log.d(TAG, "groupSort: Null");
            }
        }
        Log.d(TAG, "groupSort: --------------------------------------------------------");*/
    }


    public static void add(Student student) {

        Student[] temp = new Student[group.length+1];
        System.arraycopy(group, 0, temp, 0, group.length);
        temp[group.length]=student;


        group=temp;
        groupSort();
    }


    public static void remove(int position){
        Student[] temp = new Student[group.length-1];
        int shift = 0;
        for (int i = 0; i < group.length; i++) {
            if(i!=position){
                temp[i-shift]=group[i];
            }
            else{
                shift++;
            }
        }

        group = temp;
        groupSort();

    }


    public static void remove(Student student){
        Student[] temp = new Student[group.length-1];
        int shift = 0;
        for (int i = 0; i < group.length; i++) {
            if(group[i]==student){
                temp[i-shift]=group[i];
            }
            else{
                shift++;
            }
        }

        group = temp;
        groupSort();

    }


    public static int orderIsOccupied(int order){
        for (int i = 0; i < Group.getGroup().length; i++) {
            if (Group.getGroup()[i].getOrder()==order){
                return i;
            }
        }
        return -1;
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
