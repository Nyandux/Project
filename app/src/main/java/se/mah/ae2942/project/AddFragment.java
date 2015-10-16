package se.mah.ae2942.project;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Add fragment, creates a new expense.
 * Worked on: Ragnar Einestam
 */
public class AddFragment extends Fragment {

    private View view;
    private EditText etTitle, etAmount;
    private Controller controller;

    /**
     * Contstructor.
     */
    public AddFragment() {}

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add, container, false);
        initiate();
        return view;
    }

    /**
     * Initiates variables.
     */
    public void initiate(){
        etTitle = (EditText)view.findViewById(R.id.fragment_add_edittext_title);
        etAmount = (EditText)view.findViewById(R.id.fragment_add_edittext_amount);
    }

    /**
     * Sets local controller.
     * @param controller input
     */
    public void setController(Controller controller){
        this.controller = controller;
    }

    /**
     * Returns the title input from EditText.
     * @return String title.
     */
    public String getTitle(){
        return etTitle.getText().toString();
    }

    /**
     * Returns the amount input from EditText.
     * @return String amount.
     */
    public String getAmount(){
        return etAmount.getText().toString();
    }
}
