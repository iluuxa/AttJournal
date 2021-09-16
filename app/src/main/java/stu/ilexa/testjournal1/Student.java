package stu.ilexa.testjournal1;

import android.provider.ContactsContract;
import android.provider.Telephony;

public class Student implements Comparable {
    private String firstName;
    private String lastName;
    private String patronymic;
    private String telephone;
    private String email;
    private int order=0;
    private boolean[][][] attendance = new boolean[Schedule.weekCount][Schedule.weekCount][Schedule.dayCount];

    public Student() {
        for (int i = 0; i < Schedule.weekCount; i++) {
            for (int j = 0; j < Schedule.dayCount; j++) {
                for (int k = 0; k < Schedule.classCount; k++) {
                    attendance[i][j][k]=false;
                }
            }
        }
    }

    public Student(String lastName, String firstName, String patronymic, int order) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.order = order;
        for (int i = 0; i < Schedule.weekCount; i++) {
            for (int j = 0; j < Schedule.dayCount; j++) {
                for (int k = 0; k < Schedule.classCount; k++) {
                    attendance[i][j][k]=false;
                }
            }
        }
    }


    public Student(String lastName, String firstName, String patronymic) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.patronymic = patronymic;
        for (int i = 0; i < Schedule.weekCount; i++) {
            for (int j = 0; j < Schedule.dayCount; j++) {
                for (int k = 0; k < Schedule.classCount; k++) {
                    attendance[i][j][k]=false;
                }
            }
        }
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean getAttendance(int week, int day, int dayClass) {
        return attendance[week][day][dayClass];
    }

    public void setAttendance(int week, int day, int dayClass, boolean isHere) {
        this.attendance[week][day][dayClass] = isHere;
    }
}
