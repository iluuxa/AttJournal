package stu.ilexa.testjournal1.ui.schedule;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.NavUtils;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import stu.ilexa.testjournal1.MainActivity;
import stu.ilexa.testjournal1.Schedule;
import stu.ilexa.testjournal1.Subject;
import stu.ilexa.testjournal1.SubjectChange;
import stu.ilexa.testjournal1.databinding.FragmentScheduleBinding;

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

        final TextView textViewSubjectSlot1 = binding.textViewSubjectSlot1;
        final TextView textViewSubjectSlot2 = binding.textViewSubjectSlot2;
        final TextView textViewSubjectSlot3 = binding.textViewSubjectSlot3;
        final TextView textViewSubjectSlot4 = binding.textViewSubjectSlot4;
        final TextView textViewSubjectSlot5 = binding.textViewSubjectSlot5;
        final TextView textViewSubjectSlot6 = binding.textViewSubjectSlot6;
        final TextView textViewWeekNumber = binding.textViewWeekNumber;

        scheduleViewModel.getText().observe(getViewLifecycleOwner(), s -> {
            textViewSubjectSlot1.setText(s[0].getName());
            textViewSubjectSlot2.setText(s[1].getName());
            textViewSubjectSlot3.setText(s[2].getName());
            textViewSubjectSlot4.setText(s[3].getName());
            textViewSubjectSlot5.setText(s[4].getName());
            textViewSubjectSlot6.setText(s[5].getName());
            String concatenation = "Неделя "+(scheduleViewModel.getSelectedWeek()+1);
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
        binding.fab.setOnClickListener(this);

        return root;
    }


    @Override
    public void onClick(View view) {
        Log.d(TAG,"clicked");
        switch ((String) view.getTag()){
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
                Log.d(TAG, "onClick: FAB");
                Intent intent = new Intent(getActivity(), SubjectChange.class);
                startActivity(intent);
                break;

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}