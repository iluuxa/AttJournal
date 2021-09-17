package stu.ilexa.testjournal1.ui.tables;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import stu.ilexa.testjournal1.Subject;
import stu.ilexa.testjournal1.databinding.FragmentTablesListItemBinding;
import stu.ilexa.testjournal1.ui.change.SubjectChangeFragment;


public class SubjectRecyclerViewAdapter extends RecyclerView.Adapter<SubjectRecyclerViewAdapter.ViewHolder> {

    private final Subject[] subjects;


    public SubjectRecyclerViewAdapter(Subject[] subjects) {
        this.subjects = subjects;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentTablesListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.subjectName.setText(subjects[position].getName());
        int x;
        if(subjects[position].getLecture()) {
            holder.subjectType.setText("лк");
        }
        else {
            holder.subjectType.setText("пр");
        }
        holder.subjectType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = SubjectChangeFragment.newInstance();
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(getId(), fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return subjects.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView subjectType;
        public final TextView subjectName;

        public ViewHolder(FragmentTablesListItemBinding binding) {
            super(binding.getRoot());
            subjectName = binding.subjectNameTextView;
            subjectType = binding.subjectTypeTextView;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + subjectName.getText() + "'";
        }
    }
}