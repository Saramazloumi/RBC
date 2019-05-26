package com.midterm.lasalle.loginregistration.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.midterm.lasalle.loginregistration.R;
import com.midterm.lasalle.loginregistration.connector.FragmentEventListener;
import com.midterm.lasalle.loginregistration.dao.UserDao;
import com.midterm.lasalle.loginregistration.dao.UserFactory;
import com.midterm.lasalle.loginregistration.model.User;

public class UpdateFragment extends Fragment {

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
        return inflater.inflate(R.layout.update_user_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String email = getArguments().getString("email");

        User user = userDao.getUserByEmail(email);

        final EditText editTextFirstNameU = view.findViewById(R.id.editTextFirstNameU);
        final EditText editTextLastNameU = view.findViewById(R.id.editTextLastNameU);
        final EditText editTextEmailU = view.findViewById(R.id.editTextEmailU);
        final EditText editTextPasswordU = view.findViewById(R.id.editTextPasswordU);

        editTextFirstNameU.setText(user.getFirstName());
        editTextLastNameU.setText(user.getLastName());
        editTextEmailU.setText(user.getEmail());
        editTextPasswordU.setText(user.getPassword());

        Button btnUpdate = view.findViewById(R.id.btnUpdate);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String firstName = editTextFirstNameU.getText().toString();
                String lastName = editTextLastNameU.getText().toString();
                String email = editTextEmailU.getText().toString();
                String password = editTextPasswordU.getText().toString();

                fragmentEventListener.onUpdateUser(new User(firstName, lastName, password, email));
            }
        });


    }
}
