package stu.ilexa.testjournal1.ui.schedule;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import stu.ilexa.testjournal1.DateControl;
import stu.ilexa.testjournal1.R;
import stu.ilexa.testjournal1.Schedule;
import stu.ilexa.testjournal1.Subject;
import stu.ilexa.testjournal1.databinding.FragmentSubjectBinding;


public class SubjectFragment extends Fragment {


    private static final String ARG_WEEK = "week";
    private static final String ARG_DAY = "day";
    private static final String ARG_CLASS = "class";


    private int week=0;
    private int day=0;
    private int dayClass=0;
    private FragmentSubjectBinding binding;

    public SubjectFragment() {

    }

    public static SubjectFragment newInstance(int week, int day, int dayClass) {
        SubjectFragment fragment = new SubjectFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_WEEK, week);
        args.putInt(ARG_DAY, day);
        args.putInt(ARG_CLASS,dayClass);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            week = getArguments().getInt(ARG_WEEK);
            day = getArguments().getInt(ARG_DAY);
            dayClass = getArguments().getInt(ARG_CLASS);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSubjectBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        displayTime();
        binding.subjectNameTextEdit.setEnabled(false);
        binding.subjectChangeConfirmButton.setVisibility(View.INVISIBLE);
        binding.subjectChangeFab.setOnClickListener(view -> {
            binding.subjectDeleteFab.hide();
            binding.subjectChangeConfirmButton.setVisibility(View.VISIBLE);
            binding.subjectNameTextEdit.setEnabled(true);
        });

        binding.subjectChangeConfirmButton.setOnClickListener(view -> {
            if(binding.subjectNameTextEdit.getText()!=null){
                String name = binding.subjectNameTextEdit.getText().toString();
                int col = Schedule.hasSubject(name);
                if(col>-1){
                    Schedule.getSchedule()[week][day][dayClass]=Schedule.getSubjects().toArray(new Subject[0])[col];
                }
                Schedule.getSchedule()[week][day][dayClass].setName(binding.subjectNameTextEdit.getText().toString());
                displayTime();
                binding.subjectDeleteFab.show();
                binding.subjectNameTextEdit.setEnabled(false);
                binding.subjectChangeConfirmButton.setVisibility(View.INVISIBLE);

            }
        });

        binding.subjectDeleteFab.setOnClickListener(view -> new AlertDialog.Builder(getContext())
                .setTitle(getResources().getString(R.string.student_delete_alert))
                .setMessage("Вы точно хотите удалить предмет "+Schedule.getSchedule()[week][day][dayClass].getName()+"? Это действие нельзя будет отменить.")
                .setPositiveButton(android.R.string.yes,(dialog,which) -> {
                    Schedule.removeSubject(Schedule.getSchedule()[week][day][dayClass]);
                    getParentFragmentManager().popBackStack();
                })
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show());

        binding.AttendanceListNextSubject.setOnClickListener(view -> {
            int[] temp = Schedule.getNextClass(week,day,dayClass);
            if (temp != null) {
                week = temp[0];
                day = temp[1];
                dayClass = temp[2];
                displayTime();
            }
        });
        binding.AttendanceListPreviousSubject.setOnClickListener(view -> {
            int[] temp = Schedule.getPreviousClass(week,day,dayClass);
            if (temp != null) {
                week = temp[0];
                day = temp[1];
                dayClass = temp[2];
                displayTime();
            }
        });
        return root;
    }

    private void displayTime(){

        int[] date = DateControl.getSelectedDate(week,day);
        String dateText = date[0]+" ";
        switch (date[1]){
            case 1:
                dateText += getResources().getString(R.string.month_1);
                break;
            case 2:
                dateText += getResources().getString(R.string.month_2);
                break;
            case 3:
                dateText += getResources().getString(R.string.month_3);
                break;
            case 4:
                dateText += getResources().getString(R.string.month_4);
                break;
            case 5:
                dateText += getResources().getString(R.string.month_5);
                break;
            case 6:
                dateText += getResources().getString(R.string.month_6);
                break;
            case 7:
                dateText += getResources().getString(R.string.month_7);
                break;
            case 8:
                dateText += getResources().getString(R.string.month_8);
                break;
            case 9:
                dateText += getResources().getString(R.string.month_9);
                break;
            case 10:
                dateText += getResources().getString(R.string.month_10);
                break;
            case 11:
                dateText += getResources().getString(R.string.month_11);
                break;
            case 12:
                dateText += getResources().getString(R.string.month_12);
                break;
        }
        binding.AttendanceListDate.setText(dateText);
        String weekText = getResources().getString(R.string.week) +" "+ (week+1);
        binding.AttendanceListWeek.setText(weekText);
        switch (day){
            case 0:
                binding.AttendanceListDay.setText(getResources().getString(R.string.day_of_week_1));
                break;
            case 1:
                binding.AttendanceListDay.setText(getResources().getString(R.string.day_of_week_2));
                break;
            case 2:
                binding.AttendanceListDay.setText(getResources().getString(R.string.day_of_week_3));
                break;
            case 3:
                binding.AttendanceListDay.setText(getResources().getString(R.string.day_of_week_4));
                break;
            case 4:
                binding.AttendanceListDay.setText(getResources().getString(R.string.day_of_week_5));
                break;
            case 5:
                binding.AttendanceListDay.setText(getResources().getString(R.string.day_of_week_6));
                break;
        }
        String classText = (1+dayClass)+getResources().getString(R.string.class_number_additional_text);
        binding.AttendanceListClass.setText(classText);
        if(Schedule.getSchedule()[week][day][dayClass]!=null){
            binding.AttendanceListSubjectName.setText(Schedule.getSchedule()[week][day][dayClass].getFullName());
            binding.subjectNameTextEdit.setText(Schedule.getSchedule()[week][day][dayClass].getName());}
        else{
            binding.AttendanceListSubjectName.setText(getResources().getString(R.string.subject_null));
        }
    }
}