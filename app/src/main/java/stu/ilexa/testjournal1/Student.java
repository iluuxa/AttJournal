package stu.ilexa.testjournal1;

import android.provider.ContactsContract;
import android.provider.Telephony;

public class Student implements Comparable {
    private String firstName;
    private String lastName;
    private String patronymic;
    private int order=0;


    public Student(String lastName, String firstName, String patronymic,int order) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.order = order;
    }


    public Student(String lastName, String firstName, String patronymic) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.patronymic = patronymic;
    }

    public String getName(){
        return lastName +" "+ firstName +" "+ patronymic;
    }


    @Override
    public int compareTo(Object o) {
        return getName().compareTo(((Student)(o)).getName());
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
