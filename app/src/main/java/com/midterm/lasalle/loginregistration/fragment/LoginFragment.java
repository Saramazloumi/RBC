package com.midterm.lasalle.loginregistration.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.midterm.lasalle.loginregistration.R;
import com.midterm.lasalle.loginregistration.connector.FragmentEventListener;
import com.midterm.lasalle.loginregistration.dao.UserDao;
import com.midterm.lasalle.loginregistration.dao.UserFactory;
import com.midterm.lasalle.loginregistration.model.User;

public class LoginFragment extends Fragment {

    private FragmentEventListener fragmentEventListener;
    private UserDao userDao;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fragmentEventListener = (FragmentEventListener) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userDao = UserFactory.getUserDao();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.login_user_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final EditText editTextUserName = view.findViewById(R.id.editTextUserName);
        final EditText editTextPassword = view.findViewById(R.id.editTextPassword);
        Button btnLogin = view.findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = editTextUserName.getText().toString();
                String password = editTextPassword.getText().toString();

              if (userDao.contains(email)){
                  User user = userDao.getUserByEmail(email);
                  if (password.equals(user.getPassword())){
                      Toast.makeText(view.getContext(), "Logged in", Toast.LENGTH_SHORT).show();
                  }else{
                      Toast.makeText(view.getContext(), "password is wrong!", Toast.LENGTH_SHORT).show();
                  }
              }else{
                  Toast.makeText(view.getContext(), "Email is not exist, register first!", Toast.LENGTH_SHORT).show();
              }
                fragmentEventListener.onLoginUser(email, password);
            }
        });
    }
}
