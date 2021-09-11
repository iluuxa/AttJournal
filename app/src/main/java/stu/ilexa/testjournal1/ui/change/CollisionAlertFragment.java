package stu.ilexa.testjournal1.ui.change;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Objects;

import stu.ilexa.testjournal1.R;


public class CollisionAlertFragment extends AppCompatDialogFragment {

    private static final String collisions = "collisions";
    private int count;

    public CollisionAlertFragment() {
        // Required empty public constructor
    }

    @NonNull
    @Override
    public AlertDialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        builder.setTitle("Важное сообщение!")
                .setMessage("Покормите кота!")
                .setIcon(R.drawable.ic_menu_manage)
                .setPositiveButton("ОК, иду на кухню", (dialog, id) -> {
                    dialog.cancel();
                });
        return builder.create();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            count = getArguments().getInt(collisions);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_collision_alert, container, false);
    }
}