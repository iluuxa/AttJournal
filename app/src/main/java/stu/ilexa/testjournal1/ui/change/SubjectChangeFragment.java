package stu.ilexa.testjournal1.ui.change;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import stu.ilexa.testjournal1.R;
import stu.ilexa.testjournal1.Schedule;
import stu.ilexa.testjournal1.Subject;
import stu.ilexa.testjournal1.databinding.FragmentScheduleBinding;
import stu.ilexa.testjournal1.databinding.SubjectChangeFragmentBinding;

public class SubjectChangeFragment extends Fragment implements View.OnClickListener {

    private SubjectChangeViewModel subjectChangeViewModel;
    private SubjectChangeFragmentBinding binding;
    private MutableLiveData<String> input;

    public static SubjectChangeFragment newInstance() {
        return new SubjectChangeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.subject_change_fragment, container, false);
        subjectChangeViewModel = new ViewModelProvider(this).get(SubjectChangeViewModel.class);
        binding = SubjectChangeFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        //val items = listOf("Option 1", "Option 2", "Option 3", "Option 4")
        //val adapter = ArrayAdapter(requireContext(), R.layout.list_item, items)
        //(textField.editText as? AutoCompleteTextView)?.setAdapter(adapter)

        Object[] objects = Schedule.subjects.toArray();
        String[] list = new String[objects.length];
        for (int i = 0; i < objects.length; i++) {
            list[i]=((Subject)(objects[i])).getName();
        }
        ArrayAdapter<String> subjectsAdapter = new ArrayAdapter<String>(root.getContext(),
                android.R.layout.simple_list_item_1,
                list);
        binding.subjectInputField.setAdapter(subjectsAdapter);
        binding.subjectTypeInputField.setAdapter(new ArrayAdapter<String>(root.getContext(), android.R.layout.simple_list_item_1,new String[]{"пр","лк"}));

        subjectChangeViewModel.getClassChecks().observe(getViewLifecycleOwner(), s -> {

        });

        binding.subjectInputField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String compared = charSequence.toString();
                for (int j = 0; j < list.length; j++) {
                    if (compared.equals(list[j])){
                        subjectChangeViewModel.findChecks((Subject)(objects[j]));
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        binding.subjectTypeInputField.setText("пр");
        binding.subjectTypeInputField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().equals("лк")){
                    subjectChangeViewModel.setType(true);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        return root;
    }




    @Override
    public void onClick(View view) {

    }
}