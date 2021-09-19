package stu.ilexa.testjournal1.ui.datecontol;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import stu.ilexa.testjournal1.R;


public class DateControlFragment extends Fragment {




    public DateControlFragment() {
    }


    public static DateControlFragment newInstance(String param1, String param2) {
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_date_control, container, false);
    }
}