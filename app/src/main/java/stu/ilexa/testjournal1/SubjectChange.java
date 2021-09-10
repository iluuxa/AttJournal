package stu.ilexa.testjournal1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import stu.ilexa.testjournal1.ui.change.SubjectChangeFragment;

public class SubjectChange extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subject_change_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, SubjectChangeFragment.newInstance())
                    .commitNow();
        }
    }
}