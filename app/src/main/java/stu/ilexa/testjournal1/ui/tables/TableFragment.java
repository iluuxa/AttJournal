package stu.ilexa.testjournal1.ui.tables;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

import stu.ilexa.testjournal1.Group;
import stu.ilexa.testjournal1.R;
import stu.ilexa.testjournal1.Schedule;
import stu.ilexa.testjournal1.Student;
import stu.ilexa.testjournal1.Subject;
import stu.ilexa.testjournal1.databinding.FragmentStudentBinding;
import stu.ilexa.testjournal1.databinding.FragmentTableBinding;

public class TableFragment extends Fragment {


    private static final String ARG_POSITION = "position";


    private Subject subject;
    private int position;
    private FragmentTableBinding binding;
    private final static String TAG = "MyTable";

    public TableFragment() {
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
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTableBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        ArrayList<int[]> dates = Schedule.getSubjectDates(subject);
        ArrayList<int[]> index = Schedule.getSubjectIndex(subject);
        for (int i = 0; i < dates.size(); i++) {
            //AttributeSet attributeSet
            TextView textView = new TextView(getContext());
            String x =""+dates.get(i)[0]+"."+dates.get(i)[1];
            Log.d(TAG, "onCreateView: "+x);
            textView.setText(x);
            binding.datesRow.addView(textView);
        }

        for (int i = 0; i < Group.getGroup().length; i++) {
            TableRow tableRow = new TableRow(getContext());
            TextView textView = new TextView(getContext());
            Student student = Group.getStudent(i);
            textView.setText(student.getName());
            tableRow.addView(textView);
            for (int j = 0; j < index.size(); j++) {
                TextView childTextView = new TextView(getContext());
                childTextView.setText(student.getAttendance(index.get(j)[0],index.get(j)[1],index.get(j)[2])?"+":"-");
                tableRow.addView(childTextView);
            }
            binding.mainTable.addView(tableRow);
        }
        
        
        
        
        
        return root;
    }
}