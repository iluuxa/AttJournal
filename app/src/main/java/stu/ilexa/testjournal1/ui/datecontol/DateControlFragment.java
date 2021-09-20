package stu.ilexa.testjournal1.ui.datecontol;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import stu.ilexa.testjournal1.DateControl;
import stu.ilexa.testjournal1.Group;
import stu.ilexa.testjournal1.R;
import stu.ilexa.testjournal1.Schedule;
import stu.ilexa.testjournal1.databinding.FragmentAttendanceBinding;
import stu.ilexa.testjournal1.databinding.FragmentDateControlBinding;


public class DateControlFragment extends Fragment {


    private FragmentDateControlBinding binding;
    private int firstYear = DateControl.getFirstYear();
    private int firstMonth = DateControl.getFirstMonth();
    private int firstDay = DateControl.getFirstDay();
    private int firstHour = DateControl.getClassBeginningHour();
    private int firstMinute = DateControl.getClassBeginningMinute();
    private final static String TAG = "MyDateControlFragment";

    public DateControlFragment() {
    }


    public static DateControlFragment newInstance() {
        DateControlFragment fragment = new DateControlFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDateControlBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        final TextView firstClassDate = binding.firstClassDate;
        final TextView firstClassTime = binding.firstClassTime;
        firstClassDate.setText(DateControl.getDateFormat(firstYear,firstMonth,firstDay));
        firstClassTime.setText(DateControl.minutesToTimeFormat(DateControl.getClassBeginningHour()*60+DateControl.getClassBeginningMinute()));
        binding.changeDateButton.setOnClickListener(view -> new DatePickerDialog(getContext(),
                (datePicker, i, i1, i2) -> {
                    firstYear = i;
                    firstMonth = i1+1;
                    firstDay = i2;
                    firstClassDate.setText(DateControl.getDateFormat(firstYear,firstMonth,firstDay));
                },
                firstYear,
                firstMonth-1,
                firstDay)
                .show());
        binding.changeTimeButton.setOnClickListener(view -> new TimePickerDialog(getContext(),
                (timePicker, i, i1) -> {
                    firstHour = i;
                    firstMinute = i1;
                    firstClassTime.setText(DateControl.minutesToTimeFormat(firstHour*60+firstMinute));
                },
                DateControl.getClassBeginningHour(),
                DateControl.getClassBeginningMinute(),
                true )
                .show());
        binding.eraseScheduleButton.setOnClickListener(view -> new AlertDialog.Builder(getContext())
                .setTitle(getResources().getString(R.string.student_delete_alert))
                .setMessage("Вы точно хотите стереть расписание? Это действие нельзя будет отменить.")
                .setPositiveButton(android.R.string.yes,(dialog,which) -> Schedule.eraseSchedule())
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show());
        binding.initButton.setOnClickListener(view -> {
            Group.testInit();
            Schedule.testInit();
        });

        binding.breakTimeEditTextNumber.setText(String.valueOf(DateControl.getBreakDurationMinute()));
        binding.bigBreakTimeEditTextNumber.setText(String.valueOf(DateControl.getBigBreakDurationMinute()));
        binding.bigBreakAfterEditText.setText(DateControl.getBigBreaksString());
        binding.classDurationEditTextNumber.setText(String.valueOf(DateControl.getClassDuration()));

        binding.dateControlSubmit.setOnClickListener(view -> {
            DateControl.setFirstYear(firstYear);
            DateControl.setFirstMonth(firstMonth);
            DateControl.setFirstDay(firstDay);
            DateControl.setClassBeginningHour(firstHour);
            DateControl.setClassBeginningMinute(firstMinute);
            DateControl.setBreakDurationMinute(Integer.parseInt(binding.breakTimeEditTextNumber.getText().toString()));
            DateControl.setBigBreakDurationMinute(Integer.parseInt(binding.bigBreakTimeEditTextNumber.getText().toString()));
            DateControl.setClassDuration(Integer.parseInt(binding.classDurationEditTextNumber.getText().toString()));
            String breaks = binding.bigBreakAfterEditText.getText().toString();
            ArrayList<Integer> list= new ArrayList<>();
            boolean sequence = false;
            for (int i = 0; i < breaks.length(); i++) {
                if((breaks.charAt(i) == ' ')||(breaks.charAt(i) == ',')){
                    sequence=false;
                }
                else{
                    final int parsed = Integer.parseInt(breaks.substring(i, i + 1));
                    if(sequence){
                        list.set(list.size()-1, list.get(list.size()-1)*10+ parsed);
                    }
                    else {
                        list.add(parsed);
                        sequence = true;
                    }
                }
            }

            int shift = 0;
            for (int i = 0; i < list.size()+shift-1; i++) {
                Log.d(TAG, "onCreateView: "+list.get(i-shift)+" "+list.get(i+1-shift));
                if (list.get(i-shift) >= list.get(i+1-shift)){
                    list.remove(i+1-shift);
                    shift++;
                }
            }
            DateControl.setBigBreakAfterClass(list.toArray(new Integer[0]));
            binding.bigBreakAfterEditText.setText(DateControl.getBigBreaksString());

        });

        return root;
    }

}