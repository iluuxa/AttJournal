package stu.ilexa.testjournal1.ui.students;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import stu.ilexa.testjournal1.Group;
import stu.ilexa.testjournal1.Student;

public class StudentsViewModel extends ViewModel {

    private MutableLiveData<Student[]> students;

    public StudentsViewModel() {
        students = new MutableLiveData<>();
        students.setValue(Group.getGroup());

    }

    public LiveData<Student[]> getText() {
        return students;
    }
}