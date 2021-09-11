package stu.ilexa.testjournal1.ui.change;

import androidx.core.app.NavUtils;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.ColorSpace;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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
    private static final String TAG ="SubjectChangeFragment";

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

        Object[] objects = Schedule.subjects.toArray();
        String[] list = new String[objects.length];
        for (int i = 0; i < objects.length; i++) {
            list[i]=((Subject)(objects[i])).getName();
        }
        ArrayAdapter<String> subjectsAdapter = new ArrayAdapter<String>(root.getContext(),
                android.R.layout.simple_list_item_1,
                list);
        binding.subjectInputField.setAdapter(subjectsAdapter);
        String[] type = {"пр","лк"};
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<String>(root.getContext(), android.R.layout.simple_list_item_1,type);
        binding.subjectTypeInputField.setAdapter(typeAdapter);
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

        //binding.subjectTypeInputField.setText("пр");
        binding.subjectTypeInputField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().equals("лк")){
                    subjectChangeViewModel.setType(true);
                }
                else {subjectChangeViewModel.setType(false);}
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        subjectChangeViewModel.getWeekChecks().observe(getViewLifecycleOwner(), s -> {
            Log.d(TAG, "onCreateView: "+s[0]+s[1]+s[3]+s[4]+s[5]);
            if (s[0]) {binding.subjectAddWeek1.setTextColor(Color.RED);}
            else{binding.subjectAddWeek1.setTextColor(Color.BLACK);}
            if (s[1]) {binding.subjectAddWeek2.setTextColor(Color.RED);}
            else{binding.subjectAddWeek2.setTextColor(Color.BLACK);}
            if (s[2]) {binding.subjectAddWeek3.setTextColor(Color.RED);}
            else{binding.subjectAddWeek3.setTextColor(Color.BLACK);}
            if (s[3]) {binding.subjectAddWeek4.setTextColor(Color.RED);}
            else{binding.subjectAddWeek4.setTextColor(Color.BLACK);}
            if (s[4]) {binding.subjectAddWeek5.setTextColor(Color.RED);}
            else{binding.subjectAddWeek5.setTextColor(Color.BLACK);}
            if (s[5]) {binding.subjectAddWeek6.setTextColor(Color.RED);}
            else{binding.subjectAddWeek6.setTextColor(Color.BLACK);}
            if (s[6]) {binding.subjectAddWeek7.setTextColor(Color.RED);}
            else{binding.subjectAddWeek7.setTextColor(Color.BLACK);}
            if (s[7]) {binding.subjectAddWeek8.setTextColor(Color.RED);}
            else{binding.subjectAddWeek8.setTextColor(Color.BLACK);}
            if (s[8]) {binding.subjectAddWeek9.setTextColor(Color.RED);}
            else{binding.subjectAddWeek9.setTextColor(Color.BLACK);}
            if (s[9]) {binding.subjectAddWeek10.setTextColor(Color.RED);}
            else{binding.subjectAddWeek10.setTextColor(Color.BLACK);}
            if (s[10]) {binding.subjectAddWeek11.setTextColor(Color.RED);}
            else{binding.subjectAddWeek11.setTextColor(Color.BLACK);}
            if (s[11]) {binding.subjectAddWeek12.setTextColor(Color.RED);}
            else{binding.subjectAddWeek12.setTextColor(Color.BLACK);}
            if (s[12]) {binding.subjectAddWeek13.setTextColor(Color.RED);}
            else{binding.subjectAddWeek13.setTextColor(Color.BLACK);}
            if (s[13]) {binding.subjectAddWeek14.setTextColor(Color.RED);}
            else{binding.subjectAddWeek14.setTextColor(Color.BLACK);}
            if (s[14]) {binding.subjectAddWeek15.setTextColor(Color.RED);}
            else{binding.subjectAddWeek15.setTextColor(Color.BLACK);}
            if (s[15]) {binding.subjectAddWeek16.setTextColor(Color.RED);}
            else{binding.subjectAddWeek16.setTextColor(Color.BLACK);}
        });


        /*subjectChangeViewModel.getClassChecks().observe(getViewLifecycleOwner(), s -> {
            if (s[0][0]) {binding.subjectAddDay1Class1.setChecked(true);}
            else{binding.subjectAddDay1Class1.setChecked(false);}
            if (s[0][1]) {binding.subjectAddDay1Class2.setChecked(true);}
            else{binding.subjectAddDay1Class2.setChecked(false);}
            if (s[0][2]) {binding.subjectAddDay1Class3.setChecked(true);}
            else{binding.subjectAddDay1Class3.setChecked(false);}
            if (s[0][3]) {binding.subjectAddDay1Class4.setChecked(true);}
            else{binding.subjectAddDay1Class4.setChecked(false);}
            if (s[0][4]) {binding.subjectAddDay1Class5.setChecked(true);}
            else{binding.subjectAddDay1Class5.setChecked(false);}
            if (s[0][5]) {binding.subjectAddDay1Class6.setChecked(true);}
            else{binding.subjectAddDay1Class6.setChecked(false);}
            if (s[1][0]) {binding.subjectAddDay2Class1.setChecked(true);}
            else{binding.subjectAddDay2Class1.setChecked(false);}
            if (s[1][1]) {binding.subjectAddDay2Class2.setChecked(true);}
            else{binding.subjectAddDay2Class2.setChecked(false);}
            if (s[1][2]) {binding.subjectAddDay3Class3.setChecked(true);}
            else{binding.subjectAddDay3Class3.setChecked(false);}
            if (s[1][3]) {binding.subjectAddDay3Class4.setChecked(true);}
            else{binding.subjectAddDay3Class4.setChecked(false);}
            if (s[1][4]) {binding.subjectAddDay3Class5.setChecked(true);}
            else{binding.subjectAddDay3Class5.setChecked(false);}
            if (s[1][5]) {binding.subjectAddDay3Class6.setChecked(true);}
            else{binding.subjectAddDay3Class6.setChecked(false);}
            if (s[2][0]) {binding.subjectAddDay4Class1.setChecked(true);}
            else{binding.subjectAddDay4Class1.setChecked(false);}
            if (s[2][1]) {binding.subjectAddDay4Class2.setChecked(true);}
            else{binding.subjectAddDay4Class2.setChecked(false);}
            if (s[2][2]) {binding.subjectAddDay4Class3.setChecked(true);}
            else{binding.subjectAddDay4Class3.setChecked(false);}
            if (s[2][3]) {binding.subjectAddDay4Class4.setChecked(true);}
            else{binding.subjectAddDay4Class4.setChecked(false);}
            if (s[2][4]) {binding.subjectAddDay4Class5.setChecked(true);}
            else{binding.subjectAddDay4Class5.setChecked(false);}
            if (s[2][5]) {binding.subjectAddDay4Class6.setChecked(true);}
            else{binding.subjectAddDay4Class6.setChecked(false);}
            if (s[3][0]) {binding.subjectAddDay5Class1.setChecked(true);}
            else{binding.subjectAddDay5Class1.setChecked(false);}
            if (s[3][1]) {binding.subjectAddDay5Class2.setChecked(true);}
            else{binding.subjectAddDay5Class2.setChecked(false);}
            if (s[3][2]) {binding.subjectAddDay5Class3.setChecked(true);}
            else{binding.subjectAddDay5Class3.setChecked(false);}
            if (s[3][3]) {binding.subjectAddDay5Class4.setChecked(true);}
            else{binding.subjectAddDay5Class4.setChecked(false);}
            if (s[4][4]) {binding.subjectAddDay5Class5.setChecked(true);}
            else{binding.subjectAddDay5Class5.setChecked(false);}
            if (s[4][5]) {binding.subjectAddDay5Class6.setChecked(true);}
            else{binding.subjectAddDay5Class6.setChecked(false);}
            if (s[5][0]) {binding.subjectAddDay6Class1.setChecked(true);}
            else{binding.subjectAddDay6Class1.setChecked(false);}
            if (s[5][1]) {binding.subjectAddDay6Class2.setChecked(true);}
            else{binding.subjectAddDay6Class2.setChecked(false);}
            if (s[5][2]) {binding.subjectAddDay6Class3.setChecked(true);}
            else{binding.subjectAddDay6Class3.setChecked(false);}
            if (s[5][3]) {binding.subjectAddDay6Class4.setChecked(true);}
            else{binding.subjectAddDay6Class4.setChecked(false);}
            if (s[5][4]) {binding.subjectAddDay6Class5.setChecked(true);}
            else{binding.subjectAddDay6Class5.setChecked(false);}
            if (s[5][5]) {binding.subjectAddDay6Class6.setChecked(true);}
            else{binding.subjectAddDay6Class6.setChecked(false);}
        });*/

        binding.subjectAddAll.setOnClickListener(this);
        binding.subjectAddOdd.setOnClickListener(this);
        binding.subjectAddEven.setOnClickListener(this);
        binding.subjectAddClear.setOnClickListener(this);
        binding.subjectAddConfirm.setOnClickListener(this);


        return root;
    }


    @Override
    public void onClick(View view) {
        switch ((String)view.getTag()){
            case "subjectAddAll":
                subjectChangeViewModel.checkAll();
                break;
            case "subjectAddEven":
                subjectChangeViewModel.checkEven();
                break;
            case "subjectAddOdd":
                subjectChangeViewModel.checkOdd();
                break;
            case "subjectAddClear":
                subjectChangeViewModel.clear();
                break;
            case "subjectAddConfirm":
                boolean[][] classChecks = new boolean[Schedule.dayCount][Schedule.classCount];
                boolean[] weekChecks = new boolean[Schedule.weekCount];


                weekChecks[0]=binding.subjectAddWeek1.isChecked();
                weekChecks[1]=binding.subjectAddWeek2.isChecked();
                weekChecks[2]=binding.subjectAddWeek3.isChecked();
                weekChecks[3]=binding.subjectAddWeek4.isChecked();
                weekChecks[4]=binding.subjectAddWeek5.isChecked();
                weekChecks[5]=binding.subjectAddWeek6.isChecked();

                int collisions = subjectChangeViewModel.collisionCheck(weekChecks);
                if(collisions>0){
                    CollisionAlertFragment myDialogFragment = new CollisionAlertFragment();
                    FragmentManager manager = getParentFragmentManager();
                    //myDialogFragment.show(manager, "dialog");

                    FragmentTransaction transaction = manager.beginTransaction();
                    myDialogFragment.show(transaction, "dialog");
                }
                else{
                    classChecks[0][0]=binding.subjectAddDay1Class1.isChecked();
                    classChecks[0][1]=binding.subjectAddDay1Class2.isChecked();
                    classChecks[0][2]=binding.subjectAddDay1Class3.isChecked();
                    classChecks[0][3]=binding.subjectAddDay1Class4.isChecked();
                    classChecks[0][4]=binding.subjectAddDay1Class5.isChecked();
                    classChecks[0][5]=binding.subjectAddDay1Class6.isChecked();
                    classChecks[1][0]=binding.subjectAddDay2Class1.isChecked();
                    classChecks[1][1]=binding.subjectAddDay2Class2.isChecked();
                    classChecks[1][2]=binding.subjectAddDay2Class3.isChecked();
                    classChecks[1][3]=binding.subjectAddDay2Class4.isChecked();
                    classChecks[1][4]=binding.subjectAddDay2Class5.isChecked();
                    classChecks[1][5]=binding.subjectAddDay2Class6.isChecked();
                    classChecks[2][0]=binding.subjectAddDay3Class1.isChecked();
                    classChecks[2][1]=binding.subjectAddDay3Class2.isChecked();
                    classChecks[2][2]=binding.subjectAddDay3Class3.isChecked();
                    classChecks[2][3]=binding.subjectAddDay3Class4.isChecked();
                    classChecks[2][4]=binding.subjectAddDay3Class5.isChecked();
                    classChecks[2][5]=binding.subjectAddDay3Class6.isChecked();
                    classChecks[3][0]=binding.subjectAddDay4Class1.isChecked();
                    classChecks[3][1]=binding.subjectAddDay4Class2.isChecked();
                    classChecks[3][2]=binding.subjectAddDay4Class3.isChecked();
                    classChecks[3][3]=binding.subjectAddDay4Class4.isChecked();
                    classChecks[3][4]=binding.subjectAddDay4Class5.isChecked();
                    classChecks[3][5]=binding.subjectAddDay4Class6.isChecked();
                    classChecks[4][0]=binding.subjectAddDay5Class1.isChecked();
                    classChecks[4][1]=binding.subjectAddDay5Class2.isChecked();
                    classChecks[4][2]=binding.subjectAddDay5Class3.isChecked();
                    classChecks[4][3]=binding.subjectAddDay5Class4.isChecked();
                    classChecks[4][4]=binding.subjectAddDay1Class5.isChecked();
                    classChecks[4][5]=binding.subjectAddDay5Class6.isChecked();
                    classChecks[5][0]=binding.subjectAddDay6Class1.isChecked();
                    classChecks[5][1]=binding.subjectAddDay6Class2.isChecked();
                    classChecks[5][2]=binding.subjectAddDay6Class3.isChecked();
                    classChecks[5][3]=binding.subjectAddDay6Class4.isChecked();
                    classChecks[5][4]=binding.subjectAddDay6Class5.isChecked();
                    classChecks[5][5]=binding.subjectAddDay6Class6.isChecked();


                    subjectChangeViewModel.submit(binding.subjectInputField.getText().toString(),binding.subjectTypeInputField.getText().toString(),classChecks,weekChecks);
                    NavUtils.navigateUpFromSameTask(getActivity());
                }



                break;
        }

    }
}