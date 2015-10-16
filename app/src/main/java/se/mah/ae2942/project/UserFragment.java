package se.mah.ae2942.project;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


/**
 * User fragment, for creating a new user or log in to a existing one.
 * Worked on: Ragnar Einestam
 */
public class UserFragment extends Fragment {

    private View view;
    private EditText etUsername, etPassword;

    /**
     * Contstructor
     */
    public UserFragment() {}

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_user, container, false);
        initiate();
        return view;
    }

    /**
     * Initiates variables.
     */
    public void initiate(){
        etUsername = (EditText)view.findViewById(R.id.fragment_user_edittext_username);
        etPassword = (EditText)view.findViewById(R.id.fragment_user_edittext_password);
    }

    /**
     * Returns the username input from EditText.
     * @return String username.
     */
    public String getUsername(){
        return etUsername.getText().toString();
    }

    /**
     * Returns the password input from EditText.
     * @return
     */
    public String getPassword(){
        return etPassword.getText().toString();
    }
}
