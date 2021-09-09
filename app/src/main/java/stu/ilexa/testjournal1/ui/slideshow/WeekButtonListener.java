package stu.ilexa.testjournal1.ui.slideshow;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import stu.ilexa.testjournal1.R;
import stu.ilexa.testjournal1.databinding.FragmentSlideshowBinding;

public class WeekButtonListener {
    private ImageButton buttonWeekPrevious;
    private ImageButton buttonWeekNext;

    public WeekButtonListener(FragmentSlideshowBinding fragmentSlideshowBinding, SlideshowViewModel slideshowViewModel) {
        buttonWeekNext = fragmentSlideshowBinding.imageButtonWeekNext;
        buttonWeekNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                slideshowViewModel.incrementSelectedWeek();
            }
        });

        buttonWeekPrevious = fragmentSlideshowBinding.imageButtonWeekPrevious;
        buttonWeekPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                slideshowViewModel.decrementSelectedWeek();
            }
        });
    }
}
