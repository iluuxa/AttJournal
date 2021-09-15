package stu.ilexa.testjournal1.ui.students;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import stu.ilexa.testjournal1.R;
import stu.ilexa.testjournal1.Student;

public class StudentsViewAdapter extends RecyclerView.Adapter<StudentsViewAdapter.ViewHolder> {

    private Student[] students;
    private Fragment rootFragment;

    public StudentsViewAdapter(Student[] students, Fragment fragment) {
        this.students = students;
        rootFragment = fragment;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.student_list_item, viewGroup, false);
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull StudentsViewAdapter.ViewHolder holder, int position) {
        Student student = students[position];
        int x = position;
        holder.name.setText(student.getName());
        String string = String.valueOf(position+1);
        holder.number.setText(string);
        View.OnClickListener onClickListener= new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = StudentFragment.newInstance(x);
                FragmentTransaction transaction = rootFragment.getParentFragmentManager().beginTransaction();
                transaction.replace(rootFragment.getId(), fragment ); // give your fragment container id in first parameter
                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                transaction.commit();
            }
        };
        holder.name.setOnClickListener(onClickListener);
        holder.number.setOnClickListener(onClickListener);
    }





    @Override
    public int getItemCount() {
        return students.length;
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView number;
        private LinearLayout linearLayoutl;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.student_list_item_text);
            number = (TextView) itemView.findViewById(R.id.student_list_item_number);
        }
    }
}
