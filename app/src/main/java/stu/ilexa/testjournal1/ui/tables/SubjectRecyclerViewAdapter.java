package stu.ilexa.testjournal1.ui.tables;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import stu.ilexa.testjournal1.Subject;
import stu.ilexa.testjournal1.databinding.FragmentTablesListItemBinding;
import stu.ilexa.testjournal1.ui.change.SubjectChangeFragment;


public class SubjectRecyclerViewAdapter extends RecyclerView.Adapter<SubjectRecyclerViewAdapter.ViewHolder> {

    private final Subject[] subjects;
    private Fragment rootFragment;


    public SubjectRecyclerViewAdapter(Subject[] subjects, Fragment rootFragment) {
        this.subjects = subjects;
        this.rootFragment = rootFragment;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentTablesListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.subjectName.setText(subjects[position].getName());
        int x=position;
        if(subjects[position].getLecture()) {
            holder.subjectType.setText("лк");
        }
        else {
            holder.subjectType.setText("пр");
        }
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = TableFragment.newInstance(x);
                FragmentTransaction transaction = rootFragment.getParentFragmentManager().beginTransaction();
                transaction.replace(rootFragment.getId(), fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        };
        holder.subjectType.setOnClickListener(onClickListener);
        holder.subjectName.setOnClickListener(onClickListener);
        holder.linearLayout.setOnClickListener(onClickListener);
    }

    @Override
    public int getItemCount() {
        return subjects.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView subjectType;
        public final TextView subjectName;
        public final LinearLayout linearLayout;

        public ViewHolder(FragmentTablesListItemBinding binding) {
            super(binding.getRoot());
            subjectName = binding.subjectNameTextView;
            subjectType = binding.subjectTypeTextView;
            linearLayout = binding.subjectLayout;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + subjectName.getText() + "'";
        }
    }
}