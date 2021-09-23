package stu.ilexa.testjournal1.ui.tables;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableRow;
import android.widget.TextView;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Objects;

import stu.ilexa.testjournal1.DateControl;
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
        ShapeDrawable border = new ShapeDrawable(new RectShape());
        border.getPaint().setStyle(Paint.Style.STROKE);
        border.getPaint().setColor(Color.BLACK);
        binding.tableSubjectName.setText(subject.getFullName());
        binding.tableSubjectName.setBackground(border);
        ArrayList<int[]> dates = Schedule.getSubjectDates(subject);
        ArrayList<int[]> index = Schedule.getSubjectIndex(subject);
        int shift =0;

        for (int i = 0; i < dates.size(); i++) {
            Log.d(TAG, "onCreateView: "+dates.get(i)[0]+"."+dates.get(i)[1]+"   "+index.get(i)[0]+index.get(i)[1]+index.get(i)[2]);
        }
        for (int i = 0; i < dates.size(); i++) {
            //AttributeSet attributeSet
            TextView textView = new TextView(getContext());
            textView.setPadding(5,0,5,0);
            if ((dates.get(i)[1]> DateControl.getFirstMonth()) ||((dates.get(i)[1]==DateControl.getFirstMonth())&&(dates.get(i)[0]>=DateControl.getFirstDay()))) {

                String x = "" + dates.get(i)[0] + "." + dates.get(i)[1];
                Log.d(TAG, "onCreateView: " + x);
                textView.setText(x);
                textView.setBackground(border);
                binding.datesRow.addView(textView);
            }
            else {
                shift++;
            }
        }

        for (int i = 0; i < Group.getGroup().length; i++) {
            TableRow tableRow = new TableRow(getContext());
            TextView textView = new TextView(getContext());
            Student student = Group.getStudent(i);
            textView.setText(student.getName());
            textView.setBackground(border);
            tableRow.addView(textView);
            for (int j = 0; j < index.size()-shift; j++) {
                TextView childTextView = new TextView(getContext());
                childTextView.setText(student.getAttendance(index.get(j+shift)[0],index.get(j+shift)[1],index.get(j+shift)[2])?"+":"-");
                childTextView.setBackground(border);
                childTextView.setGravity(Gravity.CENTER);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    childTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                }
                tableRow.addView(childTextView);
            }
            binding.mainTable.addView(tableRow);
        }
        
        binding.tableDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions((Activity) requireContext(),
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);

                    return;
                }

                HSSFWorkbook workbook = new HSSFWorkbook();
                HSSFSheet sheet = workbook.createSheet(subject.getFullName());
                HSSFRow dateRow = sheet.createRow(0);
                HSSFCell hssfCell = dateRow.createCell(0);

                for (int i = 0; i < dates.size(); i++) {
                    HSSFCell dateCell = dateRow.createCell(i+1);
                    String x =dates.get(i)[0]+"."+dates.get(i)[1];
                    //Log.d(TAG, "onCreateView: "+x);
                    dateCell.setCellValue(x);
                }

                for (int i = 0; i < Group.getGroup().length; i++) {
                    HSSFRow studentRow = sheet.createRow(i+1);
                    HSSFCell studentCell = studentRow.createCell(0);
                    Student student = Group.getStudent(i);
                    studentCell.setCellValue(student.getName());
                    for (int j = 0; j < index.size(); j++) {
                        HSSFCell attendanceCell = studentRow.createCell(j+1);
                        attendanceCell.setCellValue(student.getAttendance(index.get(j)[0],index.get(j)[1],index.get(j)[2])?"+":"-");
                    }
                }
                File filePath = new File(requireContext().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)+"/"+subject.getFullName()+".xls");

                Log.d(TAG, "onClick: "+filePath);
                try {
                    if (!filePath.exists()){
                        filePath.createNewFile();
                    }
                    else{
                        filePath = new File(requireContext().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)+"/"+subject.getFullName()+"("+1+").xls");
                        for (int i = 2;filePath.exists(); i++) {
                            filePath = new File(requireContext().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)+"/"+subject.getFullName()+"("+i+").xls");
                        }
                        filePath.createNewFile();
                    }
                    Log.d(TAG, "onClick: "+filePath);
                    FileOutputStream fileOutputStream= new FileOutputStream(filePath);
                    workbook.write(fileOutputStream);

                    if (fileOutputStream!=null){
                        fileOutputStream.flush();
                        fileOutputStream.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        
        
        
        return root;
    }
}