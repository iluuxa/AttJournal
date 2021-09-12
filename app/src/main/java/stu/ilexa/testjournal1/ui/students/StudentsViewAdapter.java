package stu.ilexa.testjournal1.ui.students;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

import stu.ilexa.testjournal1.R;
import stu.ilexa.testjournal1.Student;

public class StudentsViewAdapter extends RecyclerView.Adapter<StudentsViewAdapter.ViewHolder> {

    private Student[] students;

    public StudentsViewAdapter(Student[] students) {
        this.students = students;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_students, viewGroup, false);
        return new ViewHolder(v);
    }


    public void add(Student student){
        Student[] temp = new Student[students.length+1];
        for (int i = 0; i < students.length; i++) {
            temp[i]=students[i];
        }
        temp[students.length]=student;
        students=temp;
    }


    @Override
    public void onBindViewHolder(@NonNull StudentsViewAdapter.ViewHolder holder, int position) {
        Student student = students[position];
        holder.name.setText(student.getName());
    }


    @Override
    public int getItemCount() {
        return students.length;
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.itemTextView);
        }
    }
}
