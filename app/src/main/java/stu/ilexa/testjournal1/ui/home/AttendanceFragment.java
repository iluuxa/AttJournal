package stu.ilexa.testjournal1.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.Calendar;

import stu.ilexa.testjournal1.DateControl;
import stu.ilexa.testjournal1.Group;
import stu.ilexa.testjournal1.R;
import stu.ilexa.testjournal1.Schedule;
import stu.ilexa.testjournal1.databinding.FragmentAttendanceBinding;


public class AttendanceFragment extends Fragment {

    private AttendanceViewModel attendanceViewModel;
    private FragmentAttendanceBinding binding;
    private AttendanceViewAdapter attendanceViewAdapter;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        attendanceViewModel =
                new ViewModelProvider(this).get(AttendanceViewModel.class);
        if(attendanceViewAdapter==null){attendanceViewAdapter = attendanceViewModel.getAttendanceViewAdapter();}
        binding = FragmentAttendanceBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        binding.AttendanceList.setAdapter(attendanceViewAdapter);
        binding.AttendanceList.setLayoutManager(linearLayoutManager);

        binding.AttendanceListNextSubject.setOnClickListener(view -> attendanceViewModel.nextSubject());
        binding.AttendanceListPreviousSubject.setOnClickListener(view -> attendanceViewModel.previousSubject());
        attendanceViewModel.getIndex().observe(getViewLifecycleOwner(), index -> {
            attendanceViewAdapter.setTime(index[0],index[1],index[2]);
            displayTime(index);
        });
        return root;
    }


    private void displayTime(int[] index){
        int[] date = DateControl.getSelectedDate(index[0],index[1]);
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
        String weekText = getResources().getString(R.string.week) +" "+ (index[0]+1);
        binding.AttendanceListWeek.setText(weekText);
        switch (index[1]){
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
        String classText = (1+index[2])+getResources().getString(R.string.class_number_additional_text);
        binding.AttendanceListClass.setText(classText);
        binding.AttendanceListSubjectName.setText(Schedule.getSchedule()[index[0]][index[1]][index[2]].getFullName());
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}