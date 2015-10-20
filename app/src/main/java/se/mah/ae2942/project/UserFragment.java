package se.mah.ae2942.project;


import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * User fragment, for creating a new user or log in to a existing one.
 * Worked on: Ragnar Einestam
 */
public class UserFragment extends Fragment {

    private View view;
    private EditText etUsername, etPassword;
    private Button btnLogIn, btnCreateUser;
    private SharedPreferences sharedPreferences;

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
        btnLogIn = (Button)view.findViewById(R.id.fragment_user_button_login);
        btnCreateUser = (Button)view.findViewById(R.id.fragment_user_button_create_user);

        sharedPreferences = getActivity().getSharedPreferences("MainActivity",
                Activity.MODE_PRIVATE);
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

    private class ButtonCreateUserOnClick implements View.OnClickListener{

        public void onClick(View v) {
            if((getUsername() != null) || getPassword() != null){
                sharedPreferences.edit().putString(getUsername(), getPassword());
            }
        }
    }

    private class ButtonLogInOnClick implements View.OnClickListener{

        public void onClick(View v) {
            if((getUsername() != null) || getPassword() != null){
                String password = sharedPreferences.getString(getUsername(), null);
                if(password.equals(getPassword())){

                }
                else{
                    Toast.makeText(view.getContext().getApplicationContext(),
                            "Remember to fill out the password correctly", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
