package stu.ilexa.testjournal1;

import android.provider.ContactsContract;
import android.provider.Telephony;

public class Student {
    String firstName;
    String lastName;
    String patronymic;


    public Student(String lastName, String firstName,  String patronymic) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.patronymic = patronymic;
    }

    public String getName(){
        return lastName + firstName + patronymic;
    }

}
