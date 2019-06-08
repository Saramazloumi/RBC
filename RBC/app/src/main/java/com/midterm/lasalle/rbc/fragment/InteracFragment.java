package com.midterm.lasalle.rbc.fragment;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.midterm.lasalle.rbc.R;
import com.midterm.lasalle.rbc.dao.AccountDao;
import com.midterm.lasalle.rbc.dao.AccountFactory;
import com.midterm.lasalle.rbc.model.Account;


public class InteracFragment extends Fragment {

    private AccountDao accountDao;


    public InteracFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        accountDao = AccountFactory.getAccountDao();
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_interac, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final EditText editTextEmail = view.findViewById(R.id.editTextEmail);
        final EditText editTextFund = view.findViewById(R.id.editTextFund);
        Button btnSend = view.findViewById(R.id.btnSend);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = editTextEmail.getText().toString();
                String fund = editTextFund.getText().toString();

                if (accountDao.contain(email)){
                    Account account = accountDao.getUserByEmail(email);

                    accountDao.updateBalance(email, Double.valueOf(fund));
                    Toast.makeText(v.getContext(), "Transfer Successfully", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(v.getContext(),"The email is not exist", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
