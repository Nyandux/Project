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
    private Controller controller;
    private MainActivity mainActivity;

    /**
     * Constructor
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
        etPassword.setEnabled(false);
        btnLogIn = (Button)view.findViewById(R.id.fragment_user_button_login);
        btnCreateUser = (Button)view.findViewById(R.id.fragment_user_button_create_user);
        btnLogIn.setOnClickListener(new ButtonLogInOnClick());
        btnLogIn.setEnabled(false);
        btnCreateUser.setOnClickListener(new ButtonCreateUserOnClick());
        mainActivity = (MainActivity)getActivity();
        sharedPreferences = getActivity().getSharedPreferences("MainActivity",
                Activity.MODE_PRIVATE);
    }

    /**
     * Returns the username input from EditText.
     * @return String username.
     */
    private String getUsername(){
        return etUsername.getText().toString();
    }


    /**
     * Returns the password input from EditText.
     * @return
     */
    private String getPassword(){

        return etPassword.getText().toString();
    }

    /**
     * Creates username, adds username to sharedPreferences.
     */
    private class ButtonCreateUserOnClick implements View.OnClickListener{

        public void onClick(View v) {
            if((!getUsername().matches(""))){
                sharedPreferences.edit().putString("username", getUsername()).apply();
                mainActivity.setViewFragment("listfragment");
            }
            else{
                Toast.makeText(view.getContext().getApplicationContext(),
                        "Remember to correctly fill out the fields", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * Log in, checks username and password.
     * <Not implemented></Not>
     */
    private class ButtonLogInOnClick implements View.OnClickListener{

        public void onClick(View v) {
            if((!getUsername().equals("")) && getPassword().equals("")){
                String password = sharedPreferences.getString(getUsername(), null);

                if(getUsername().equals("") || getPassword().equals("")){
                    Toast.makeText(view.getContext().getApplicationContext(),
                            "Remember to fill out the password correctly", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(password.equals(getPassword())){
                        sharedPreferences.edit().putString("username", getUsername());
                        mainActivity.setViewFragment("listfragment");
                    }
                    else{
                        Toast.makeText(view.getContext().getApplicationContext(),
                                "Remember to fill out the password correctly", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            else{
                Toast.makeText(view.getContext().getApplicationContext(),
                        "Remember to fill out the password correctly", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
