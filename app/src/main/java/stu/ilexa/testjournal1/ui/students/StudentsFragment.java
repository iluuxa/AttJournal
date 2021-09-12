package stu.ilexa.testjournal1.ui.students;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.AdapterListUpdateCallback;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import stu.ilexa.testjournal1.Student;
import stu.ilexa.testjournal1.databinding.FragmentStudentsBinding;

public class StudentsFragment extends Fragment {

    private StudentsViewModel studentsViewModel;
    private FragmentStudentsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        studentsViewModel =
                new ViewModelProvider(this).get(StudentsViewModel.class);

        binding = FragmentStudentsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        RecyclerView studentsScroll = binding.studentsScroll;

        StudentsViewAdapter stringArrayAdapter= new StudentsViewAdapter(new Student[]{new Student("Ясько","Илья","Артёмович")});
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(root.getContext());
        studentsScroll.setLayoutManager(linearLayoutManager);
        studentsScroll.setAdapter(stringArrayAdapter);

        studentsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String[]>() {

            @Override
            public void onChanged(@Nullable String[] s) {
                //stringArrayAdapter.add();
            }
        });


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}