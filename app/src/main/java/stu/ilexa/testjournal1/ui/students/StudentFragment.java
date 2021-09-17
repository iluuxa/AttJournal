package stu.ilexa.testjournal1.ui.students;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import stu.ilexa.testjournal1.Group;
import stu.ilexa.testjournal1.R;
import stu.ilexa.testjournal1.Student;
import stu.ilexa.testjournal1.databinding.FragmentStudentBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StudentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StudentFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_IDENTIFIER = "groupIdentifier";
    private static final String TAG = "MyStudent";

    private Student student = null;
    private FragmentStudentBinding binding;


    public StudentFragment() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param groupIdentifier Parameter 1.
     * @return A new instance of fragment StudentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StudentFragment newInstance(int groupIdentifier) {
        StudentFragment fragment = new StudentFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_IDENTIFIER, groupIdentifier);
        fragment.setArguments(args);
        return fragment;
    }

    public static StudentFragment newInstance() {
        StudentFragment fragment = new StudentFragment();
        fragment.setArguments(null);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            int groupIdentifier = getArguments().getInt(ARG_IDENTIFIER);
            student = Group.getGroup()[groupIdentifier];
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentStudentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        View.OnClickListener saveButtonListener = saveButton -> {
            boolean studentAdded = (student == null);
            if (studentAdded) {
                student = new Student();
            }
            if (isFilled(binding.lastNameText))
                student.setLastName(binding.lastNameText.getText().toString());
            else {
                insufficientInputAlert();
                if(studentAdded){
                    student=null;
                }
                return;
            }
            if (isFilled(binding.firstNameText))
                student.setFirstName(binding.firstNameText.getText().toString());
            else {
                insufficientInputAlert();
                if(studentAdded){
                    student=null;
                }
                return;
            }
            if (isFilled(binding.patrynomicText))
                student.setPatronymic(binding.patrynomicText.getText().toString());
            else {
                insufficientInputAlert();
                if(studentAdded){
                    student=null;
                }
                return;
            }
            try {
                if (isFilled(binding.orderNumber)) {
                    if (Group.orderIsOccupied(Integer.parseInt(binding.orderNumber.getText().toString())) >= 0) {
                        wrongOrderAlert(Group.orderIsOccupied(Integer.parseInt(binding.orderNumber.getText().toString())));
                        binding.orderNumber.setText("");
                        if (studentAdded) {
                            student = null;
                        }
                        return;
                    } else {
                        student.setOrder(Integer.parseInt(binding.orderNumber.getText().toString()));
                    }
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            if (isFilled(binding.telephoneNumber)) {
                student.setTelephone(binding.telephoneNumber.getText().toString());
            }
            if (isFilled(binding.emailText)) {
                student.setEmail(binding.emailText.getText().toString());
            }
            if (studentAdded) {
                Group.add(student);
            } else {
                Group.groupSort();
            }
            getParentFragmentManager().popBackStack();
        };


        if (student != null) {
            Log.d(TAG, "onCreateView: " + student.getName());
            binding.saveButton.setVisibility(View.INVISIBLE);
            binding.studentDeleteFAB.show();
            binding.studentDeleteFAB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new AlertDialog.Builder(getContext())
                            .setTitle(getResources().getString(R.string.student_delete_alert))
                            .setMessage("Вы точно хотите удалить студента "+student.getName()+"? Это действие нельзя будет отменить.")
                            .setPositiveButton(android.R.string.yes,(dialog,which) -> {
                                if(student!=null){Group.remove(student);}
                                getParentFragmentManager().popBackStack();
                            })
                            .setNegativeButton(android.R.string.no, null)
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }
            });
            binding.studentChangeFAB.show();
            binding.studentChangeFAB.setOnClickListener(fab -> {

                setHints();
                binding.studentDeleteFAB.hide();
                binding.firstNameText.setEnabled(true);
                binding.lastNameText.setEnabled(true);
                binding.patrynomicText.setEnabled(true);
                binding.orderNumber.setEnabled(true);
                binding.telephoneNumber.setEnabled(true);
                binding.emailText.setEnabled(true);
                //binding.studentChangeFAB.setVisibility(View.INVISIBLE);
                binding.studentChangeFAB.hide();
                binding.saveButton.setVisibility(View.VISIBLE);
                binding.saveButton.setOnClickListener(saveButtonListener);
            });

            binding.lastNameText.setText(student.getLastName());
            binding.firstNameText.setText(student.getFirstName());
            binding.patrynomicText.setText(student.getPatronymic());
            if (student.getOrder() > 0) {
                binding.orderNumber.setText(String.valueOf(student.getOrder()));
            }
            if (student.getTelephone() != null) {
                binding.telephoneNumber.setText(student.getTelephone());
            }
            if (student.getEmail() != null) {
                binding.emailText.setText(student.getEmail());
            }
            setDisabled(binding.firstNameText);
            setDisabled(binding.lastNameText);
            setDisabled(binding.patrynomicText);
            setDisabled(binding.orderNumber);
            setDisabled(binding.telephoneNumber);
            setDisabled(binding.emailText);
        } else {
            binding.saveButton.setVisibility(View.VISIBLE);
            binding.saveButton.setOnClickListener(saveButtonListener);
            binding.studentChangeFAB.hide();
            binding.studentDeleteFAB.hide();
            setHints();
        }


        return view;
        //return inflater.inflate(R.layout.fragment_student, container, false);

    }


    private void setDisabled(EditText editText) {
        editText.setEnabled(false);
    }

    private void setHints() {
        binding.lastNameText.setHint(R.string.input_student_last_name);
        binding.firstNameText.setHint(R.string.input_student_first_name);
        binding.patrynomicText.setHint(R.string.input_student_patronymic);
        binding.orderNumber.setHint(R.string.input_student_order);
        binding.telephoneNumber.setHint(R.string.input_student_telephone);
        binding.emailText.setHint(R.string.input_student_email);
    }

    private boolean isFilled(EditText etText) {
        return etText.getText().toString().trim().length() > 0;
    }

    private void insufficientInputAlert() {
        new AlertDialog.Builder(getContext())
                .setTitle(getResources().getString(R.string.student_insufficient_input))
                .setMessage("Поля \"Фамилия\", \"Имя\" и \"Отчество\" обязательны к заполнению")
                .setNeutralButton(android.R.string.ok, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private void wrongOrderAlert(int collision) {
        new AlertDialog.Builder(getContext())
                .setTitle(getResources().getString(R.string.student_wrong_order_input))
                .setMessage("Этот порядковый номер уже занят студентом " + Group.getGroup()[collision].getName() + ". Введите другой номер или измените номер указанного студента")
                .setNeutralButton(android.R.string.ok, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

}
