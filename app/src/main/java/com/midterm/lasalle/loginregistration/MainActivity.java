package com.midterm.lasalle.loginregistration;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.midterm.lasalle.loginregistration.connector.FragmentEventListener;
import com.midterm.lasalle.loginregistration.dao.UserDao;
import com.midterm.lasalle.loginregistration.dao.UserFactory;
import com.midterm.lasalle.loginregistration.fragment.LoginFragment;
import com.midterm.lasalle.loginregistration.fragment.RegisterFragment;
import com.midterm.lasalle.loginregistration.fragment.UpdateFragment;
import com.midterm.lasalle.loginregistration.model.User;

public class MainActivity extends AppCompatActivity implements FragmentEventListener {

    EditText editTextUpdate;

    private static final String REGISTER_USER_TAG = "REGISTER_USER_TAG";
    private static final String LOGIN_USER_TAG = "LOGIN_USER_TAG";
    private static final String UPDATE_USER_TAG = "UPDATE_USER_TAG";

    private UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextUpdate = findViewById(R.id.editTextUpdate);
        editTextUpdate.setVisibility(EditText.INVISIBLE);
        userDao = UserFactory.getUserDao();
        userDao.addUser(new User("Sara", "Mazloumi", "123456", "admin@gmail.com"));
        initialize();
    }

    private void initialize() {

        Button btnRegisterMain = findViewById(R.id.btnRegisterMain);
        btnRegisterMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextUpdate.setVisibility(EditText.INVISIBLE);
                RegisterFragment registerFragment = new RegisterFragment();
                addFragment(R.id.fragmentContainer, registerFragment, REGISTER_USER_TAG);
            }
        });

         Button btnLoginMain = findViewById(R.id.btnLoginMain);
         btnLoginMain.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 editTextUpdate.setVisibility(EditText.INVISIBLE);
                 LoginFragment loginFragment = new LoginFragment();
                 addFragment(R.id.fragmentContainer, loginFragment,LOGIN_USER_TAG);
             }
         });

         Button btnUpdateUser = findViewById(R.id.btnUpdateUser);
         btnUpdateUser.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                editTextUpdate.setVisibility(EditText.VISIBLE);
                String email = editTextUpdate.getText().toString();

                if (userDao.contains(email)){
                    UpdateFragment updateFragment = new UpdateFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("email", email);
                    updateFragment.setArguments(bundle);

                    InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getRootView().getWindowToken(),0);

                    addFragment(R.id.fragmentContainer, updateFragment, UPDATE_USER_TAG);
                }else{
                    Toast.makeText(v.getContext(), "Email is not exist!", Toast.LENGTH_SHORT).show();
                }
             }
         });
    }

    @Override
    public void onRegisterUser(User user) {
        userDao.addUser(user);
        removeFragment(REGISTER_USER_TAG);
    }

    @Override
    public void onLoginUser(String email, String password) {
        if (userDao.loginUser(email, password)) {
            removeFragment(LOGIN_USER_TAG);
        }
    }

    @Override
    public void onUpdateUser(User newUser) {
        userDao.updateUser(userDao.getUserByEmail(editTextUpdate.getText().toString()), newUser);
        removeFragment(UPDATE_USER_TAG);
        editTextUpdate.setText("");
        editTextUpdate.setVisibility(EditText.INVISIBLE);
    }

    public void addFragment(int container, Fragment fragment, String tag){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.add(container, fragment, tag);
        fragmentTransaction.commit();
    }

    public void removeFragment(String tag){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
        fragmentTransaction.remove(fragmentManager.findFragmentByTag(tag));
        fragmentTransaction.commit();
    }
}
