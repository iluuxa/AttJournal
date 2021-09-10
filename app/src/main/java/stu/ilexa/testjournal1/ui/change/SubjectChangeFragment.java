package stu.ilexa.testjournal1.ui.change;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import stu.ilexa.testjournal1.R;

public class SubjectChangeFragment extends Fragment {

    private SubjectChangeViewModel mViewModel;

    public static SubjectChangeFragment newInstance() {
        return new SubjectChangeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.subject_change_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(SubjectChangeViewModel.class);
        // TODO: Use the ViewModel
    }

}