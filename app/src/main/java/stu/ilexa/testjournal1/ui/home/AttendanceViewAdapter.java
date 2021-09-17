package stu.ilexa.testjournal1.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import stu.ilexa.testjournal1.DateControl;
import stu.ilexa.testjournal1.R;
import stu.ilexa.testjournal1.Student;

public class AttendanceViewAdapter extends RecyclerView.Adapter<AttendanceViewAdapter.ViewHolder> {

    private final Student[] students;
    private int week=0;
    private int day=0;
    private int currentClass=0;

    public AttendanceViewAdapter(Student[] students) {
        this.students = students;
    }

    public void setTime(int week, int day, int currentClass){
        this.week = week;
        this.day = day;
        this.currentClass = currentClass;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.attendance_list_item, viewGroup, false);
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull AttendanceViewAdapter.ViewHolder holder, int position) {
        Student student = students[position];
        holder.checkedName.setText(student.getName());
        holder.number.setText(String.valueOf(position+1));
        if(student.getAttendance(week,day,currentClass)){
            holder.checkedName.setChecked(true);
        }
        else {
            holder.checkedName.setChecked(false);
        }
        View.OnClickListener onClickListener = view -> {
            if(student.getAttendance(week,day,currentClass)){
                student.setAttendance(week,day,currentClass,false);
                holder.checkedName.setChecked(false);
            }
            else {
                student.setAttendance(week,day,currentClass,true);
                holder.checkedName.setChecked(true);
            }
            notifyItemChanged(holder.getBindingAdapterPosition());
        };
        holder.linearLayout.setOnClickListener(onClickListener);
        holder.checkedName.setOnClickListener(onClickListener);
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

        private CheckBox checkedName;
        private TextView number;
        private LinearLayout linearLayout;

        //TODO: optimize with public Student student;
        public ViewHolder(View itemView) {
            super(itemView);
            checkedName = (CheckBox) itemView.findViewById(R.id.attendance_list_item_checkbox);
            number = (TextView) itemView.findViewById(R.id.attendance_list_item_number);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.attendance_list_item_layout);
        }
    }
}
