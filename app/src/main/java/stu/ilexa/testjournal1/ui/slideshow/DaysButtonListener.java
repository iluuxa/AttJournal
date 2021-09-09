package stu.ilexa.testjournal1.ui.slideshow;

import android.app.Activity;
import android.view.View;
import android.widget.Button;

import androidx.lifecycle.ViewModel;

import stu.ilexa.testjournal1.R;
import stu.ilexa.testjournal1.databinding.FragmentSlideshowBinding;

public class DaysButtonListener{
    private final Button buttonWeekDay1;
    private final Button buttonWeekDay2;
    private final Button buttonWeekDay3;
    private final Button buttonWeekDay4;
    private final Button buttonWeekDay5;
    private final Button buttonWeekDay6;

    public DaysButtonListener(FragmentSlideshowBinding fragmentSlideshowBinding, SlideshowViewModel slideshowViewModel) {

        buttonWeekDay1 = fragmentSlideshowBinding.ButtonWeekDay1;
        buttonWeekDay1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                slideshowViewModel.setSelectedDay(1);
            }
        });

        buttonWeekDay2 = fragmentSlideshowBinding.ButtonWeekDay2;
        buttonWeekDay2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                slideshowViewModel.setSelectedDay(2);
            }
        });

        buttonWeekDay3 = fragmentSlideshowBinding.ButtonWeekDay3;
        buttonWeekDay3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                slideshowViewModel.setSelectedDay(3);
            }
        });

        buttonWeekDay4 = fragmentSlideshowBinding.ButtonWeekDay4;
        buttonWeekDay4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                slideshowViewModel.setSelectedDay(4);
            }
        });

        buttonWeekDay5 = fragmentSlideshowBinding.ButtonWeekDay5;
        buttonWeekDay5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                slideshowViewModel.setSelectedDay(5);
            }
        });

        buttonWeekDay6 = fragmentSlideshowBinding.ButtonWeekDay6;;
        buttonWeekDay6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                slideshowViewModel.setSelectedDay(6);
            }
        });
    }
}