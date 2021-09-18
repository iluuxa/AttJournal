package stu.ilexa.testjournal1.ui.tables;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import stu.ilexa.testjournal1.Group;
import stu.ilexa.testjournal1.R;
import stu.ilexa.testjournal1.Schedule;
import stu.ilexa.testjournal1.Subject;
import stu.ilexa.testjournal1.databinding.FragmentStudentBinding;
import stu.ilexa.testjournal1.databinding.FragmentTableBinding;

public class TableFragment extends Fragment {


    private static final String ARG_POSITION = "position";


    private Subject subject;
    private int position;
    private FragmentTableBinding binding;

    public TableFragment() {
        // Required empty public constructor
    }


    public static TableFragment newInstance(int position) {
        TableFragment fragment = new TableFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            position = getArguments().getInt(ARG_POSITION);
            subject = Schedule.getSubjects().toArray(new Subject[0])[position];
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTableBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        ArrayList<int[]> dates = Schedule.getSubjectDates(subject);
        for (int i = 0; i < dates.size(); i++) {
            AttributeSet attributeSet
            TextView textView = new TextView(getContext());
        }
        
        
        
        
        
        return root;
    }
}