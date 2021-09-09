package stu.ilexa.testjournal1.ui.slideshow;

import android.app.Activity;
import android.location.GnssAntennaInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import stu.ilexa.testjournal1.MainActivity;
import stu.ilexa.testjournal1.R;
import stu.ilexa.testjournal1.databinding.FragmentSlideshowBinding;

public class SlideshowFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "ScheduleFragment";
    private SlideshowViewModel slideshowViewModel;
    private FragmentSlideshowBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textViewSubjectSlot1 = binding.textViewSubjectSlot1;
        final TextView textViewSubjectSlot2 = binding.textViewSubjectSlot2;
        final TextView textViewSubjectSlot3 = binding.textViewSubjectSlot3;
        final TextView textViewSubjectSlot4 = binding.textViewSubjectSlot4;
        final TextView textViewSubjectSlot5 = binding.textViewSubjectSlot5;
        final TextView textViewSubjectSlot6 = binding.textViewSubjectSlot6;
        final TextView textViewWeekNumber = binding.textViewWeekNumber;

        slideshowViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String[]>() {
            @Override
            public void onChanged(@Nullable String[] s) {
                textViewSubjectSlot1.setText(s[0]);
                textViewSubjectSlot2.setText(s[1]);
                textViewSubjectSlot3.setText(s[2]);
                textViewSubjectSlot4.setText(s[3]);
                textViewSubjectSlot5.setText(s[4]);
                textViewSubjectSlot6.setText(s[5]);
                textViewWeekNumber.setText("Неделя "+(slideshowViewModel.getSelectedWeek()+1));
            }
        });

        binding.ButtonWeekDay1.setOnClickListener(this);
        binding.ButtonWeekDay2.setOnClickListener(this);
        binding.ButtonWeekDay3.setOnClickListener(this);
        binding.ButtonWeekDay4.setOnClickListener(this);
        binding.ButtonWeekDay5.setOnClickListener(this);
        binding.ButtonWeekDay6.setOnClickListener(this);
        binding.imageButtonWeekNext.setOnClickListener(this);
        binding.imageButtonWeekPrevious.setOnClickListener(this);

        return root;
    }

    @Override
    public void onClick(View view) {
        Log.d(TAG,"clicked");
        switch (view.getId()){
            case R.id.ButtonWeekDay1:
                slideshowViewModel.setSelectedDay(0);
                break;
            case R.id.ButtonWeekDay2:
                slideshowViewModel.setSelectedDay(1);
                break;
            case R.id.ButtonWeekDay3:
                slideshowViewModel.setSelectedDay(2);
                break;
            case R.id.ButtonWeekDay4:
                slideshowViewModel.setSelectedDay(3);
                break;
            case R.id.ButtonWeekDay5:
                slideshowViewModel.setSelectedDay(4);
                break;
            case R.id.ButtonWeekDay6:
                slideshowViewModel.setSelectedDay(5);
                break;
            case R.id.imageButtonWeekNext:
                slideshowViewModel.incrementSelectedWeek();
                break;
            case R.id.imageButtonWeekPrevious:
                slideshowViewModel.decrementSelectedWeek();
                break;

        }
    }

    public FragmentSlideshowBinding getBinding() {
        return binding;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}