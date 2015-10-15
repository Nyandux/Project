package se.mah.ae2942.project;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Add fragment, creates a new expense.
 */
public class AddFragment extends Fragment {

    private View view;
    private EditText etTitle, etAmount;

    public AddFragment() {

    }

    public void initiate(){
        etTitle = (EditText)view.findViewById(R.id.fragment_add_edittext_title);
        etAmount = (EditText)view.findViewById(R.id.fragment_add_edittext_amount);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add, container, false);
        initiate();
        return view;
    }
}
