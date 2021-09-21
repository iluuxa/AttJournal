package stu.ilexa.testjournal1.ui.schedule;

import android.app.UiAutomation;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import stu.ilexa.testjournal1.DateControl;
import stu.ilexa.testjournal1.R;
import stu.ilexa.testjournal1.Schedule;
import stu.ilexa.testjournal1.databinding.FragmentScheduleBinding;
import stu.ilexa.testjournal1.ui.change.SubjectChangeFragment;

public class ScheduleFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "ScheduleFragment";
    private ScheduleViewModel scheduleViewModel;
    private FragmentScheduleBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        scheduleViewModel =
                new ViewModelProvider(this).get(ScheduleViewModel.class);

        binding = FragmentScheduleBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.textViewLessonStart1.setText(DateControl.minutesToTimeFormat(DateControl.getClassStartTime(0)));
        binding.textViewLessonStart2.setText(DateControl.minutesToTimeFormat(DateControl.getClassStartTime(1)));
        binding.textViewLessonStart3.setText(DateControl.minutesToTimeFormat(DateControl.getClassStartTime(2)));
        binding.textViewLessonStart4.setText(DateControl.minutesToTimeFormat(DateControl.getClassStartTime(3)));
        binding.textViewLessonStart5.setText(DateControl.minutesToTimeFormat(DateControl.getClassStartTime(4)));
        binding.textViewLessonStart6.setText(DateControl.minutesToTimeFormat(DateControl.getClassStartTime(5)));
        binding.textViewLessonEnd1.setText(DateControl.minutesToTimeFormat(DateControl.getClassEndTime(0)));
        binding.textViewLessonEnd2.setText(DateControl.minutesToTimeFormat(DateControl.getClassEndTime(1)));
        binding.textViewLessonEnd3.setText(DateControl.minutesToTimeFormat(DateControl.getClassEndTime(2)));
        binding.textViewLessonEnd4.setText(DateControl.minutesToTimeFormat(DateControl.getClassEndTime(3)));
        binding.textViewLessonEnd5.setText(DateControl.minutesToTimeFormat(DateControl.getClassEndTime(4)));
        binding.textViewLessonEnd6.setText(DateControl.minutesToTimeFormat(DateControl.getClassEndTime(5)));

        final TextView textViewSubjectSlot1 = binding.textViewSubjectSlot1;
        final TextView textViewSubjectSlot2 = binding.textViewSubjectSlot2;
        final TextView textViewSubjectSlot3 = binding.textViewSubjectSlot3;
        final TextView textViewSubjectSlot4 = binding.textViewSubjectSlot4;
        final TextView textViewSubjectSlot5 = binding.textViewSubjectSlot5;
        final TextView textViewSubjectSlot6 = binding.textViewSubjectSlot6;
        final TextView textViewWeekNumber = binding.textViewWeekNumber;

        scheduleViewModel.getText().observe(getViewLifecycleOwner(), s -> {
            if (s[0] != null) {
                textViewSubjectSlot1.setText(s[0].getFullName());
            }
            else {
                textViewSubjectSlot1.setText(R.string.subject_null);
            }
            if (s[1] != null) {
                textViewSubjectSlot2.setText(s[1].getFullName());
            }
            else {
                textViewSubjectSlot2.setText(R.string.subject_null);
            }
            if (s[2] != null) {
                textViewSubjectSlot3.setText(s[2].getFullName());
            }
            else {
                textViewSubjectSlot3.setText(R.string.subject_null);
            }
            if (s[3] != null) {
                textViewSubjectSlot4.setText(s[3].getFullName());
            }
            else {
                textViewSubjectSlot4.setText(R.string.subject_null);
            }
            if (s[4] != null) {
                textViewSubjectSlot5.setText(s[4].getFullName());
            }
            else {
                textViewSubjectSlot5.setText(R.string.subject_null);
            }
            if (s[5] != null) {
                textViewSubjectSlot6.setText(s[5].getFullName());
            }
            else {
                textViewSubjectSlot6.setText(R.string.subject_null);
            }
            String concatenation = "Неделя " + (scheduleViewModel.getSelectedWeek() + 1);
            textViewWeekNumber.setText(concatenation);
        });

        binding.ButtonWeekDay1.setOnClickListener(this);
        binding.ButtonWeekDay2.setOnClickListener(this);
        binding.ButtonWeekDay3.setOnClickListener(this);
        binding.ButtonWeekDay4.setOnClickListener(this);
        binding.ButtonWeekDay5.setOnClickListener(this);
        binding.ButtonWeekDay6.setOnClickListener(this);
        binding.imageButtonWeekNext.setOnClickListener(this);
        binding.imageButtonWeekPrevious.setOnClickListener(this);
        binding.addSubjectFAB.setOnClickListener(this);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int dayClass=0;
                switch ((String) view.getTag()) {
                    case "subjectDisplayed1":
                        dayClass=0;
                        break;
                    case "subjectDisplayed2":
                       dayClass=1;
                        break;
                    case "subjectDisplayed3":
                        dayClass=2;
                        break;
                    case "subjectDisplayed4":
                        dayClass=3;
                        break;
                    case "subjectDisplayed5":
                        dayClass=4;
                        break;
                    case "subjectDisplayed6":
                        dayClass=5;
                        break;
                }
                if(Schedule.getSchedule()[scheduleViewModel.getSelectedWeek()][scheduleViewModel.getSelectedDay()][dayClass]!=null){
                Fragment fragment = SubjectFragment.newInstance(scheduleViewModel.getSelectedWeek(),scheduleViewModel.getSelectedDay(),dayClass);
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(getId(), fragment);
                transaction.addToBackStack(null);
                transaction.commit();
                }
            }
        };

        binding.textViewSubjectSlot1.setOnClickListener(onClickListener);
        binding.textViewSubjectSlot2.setOnClickListener(onClickListener);
        binding.textViewSubjectSlot3.setOnClickListener(onClickListener);
        binding.textViewSubjectSlot4.setOnClickListener(onClickListener);
        binding.textViewSubjectSlot5.setOnClickListener(onClickListener);
        binding.textViewSubjectSlot6.setOnClickListener(onClickListener);

        return root;
    }


    @Override
    public void onClick(View view) {
        Log.d(TAG, "clicked");
        switch ((String) view.getTag()) {
            case "buttonWeekDay1":
                scheduleViewModel.setSelectedDay(0);
                break;
            case "buttonWeekDay2":
                scheduleViewModel.setSelectedDay(1);
                break;
            case "buttonWeekDay3":
                scheduleViewModel.setSelectedDay(2);
                break;
            case "buttonWeekDay4":
                scheduleViewModel.setSelectedDay(3);
                break;
            case "buttonWeekDay5":
                scheduleViewModel.setSelectedDay(4);
                break;
            case "buttonWeekDay6":
                scheduleViewModel.setSelectedDay(5);
                break;
            case "imageButtonWeekNext":
                scheduleViewModel.incrementSelectedWeek();
                break;
            case "imageButtonWeekPrevious":
                scheduleViewModel.decrementSelectedWeek();
                break;
            case "addSubjectFAB":
                Fragment fragment = SubjectChangeFragment.newInstance();
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(getId(), fragment);
                transaction.addToBackStack(null);
                transaction.commit();
                break;

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}