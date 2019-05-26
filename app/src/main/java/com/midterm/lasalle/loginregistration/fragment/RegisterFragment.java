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
import com.midterm.lasalle.loginregistration.model.User;

public class RegisterFragment extends Fragment {

    private FragmentEventListener fragmentEventListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fragmentEventListener = (FragmentEventListener) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.register_user_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final EditText editTextFirstName = view.findViewById(R.id.editTextFirstName);
        final EditText editTextLastName = view.findViewById(R.id.editTextLastName);
        final EditText editTextEmail = view.findViewById(R.id.editTextEmail);
        final EditText editTextPassword = view.findViewById(R.id.editTextPassword);
        final EditText editTextConfirm = view.findViewById(R.id.editTextConfirm);
        Button btnRegister = view.findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String firstName = editTextFirstName.getText().toString();
                String lastName = editTextLastName.getText().toString();
                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();

                if(!(password.equals(editTextConfirm.getText().toString()))){
                    Toast.makeText(view.getContext(), "Password not matched!", Toast.LENGTH_SHORT).show();
                } else{
                    fragmentEventListener.onRegisterUser(new User(firstName, lastName,password, email));
                    Toast.makeText(view.getContext(), "Successfully Registered!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
