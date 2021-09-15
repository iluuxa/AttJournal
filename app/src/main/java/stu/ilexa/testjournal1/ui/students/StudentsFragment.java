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
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.AdapterListUpdateCallback;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import stu.ilexa.testjournal1.Group;
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
       // Group.testInit();
        StudentsViewAdapter studentsArrayAdapter = new StudentsViewAdapter(Group.getGroup(), this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(root.getContext(), RecyclerView.VERTICAL, false);
        studentsScroll.setLayoutManager(linearLayoutManager);
        studentsScroll.setAdapter(studentsArrayAdapter);
        binding.studentAddFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = StudentFragment.newInstance();
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(getId(), fragment); // give your fragment container id in first parameter
                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                transaction.commit();
            }
        });

        studentsViewModel.getText().observe(getViewLifecycleOwner(), s -> {
            studentsArrayAdapter.notifyDataSetChanged();
        });


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}