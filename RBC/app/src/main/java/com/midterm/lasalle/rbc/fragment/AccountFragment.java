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
import android.widget.TextView;

import com.midterm.lasalle.rbc.R;
import com.midterm.lasalle.rbc.dao.AccountDao;
import com.midterm.lasalle.rbc.dao.AccountFactory;
import com.midterm.lasalle.rbc.model.Account;


public class AccountFragment extends Fragment {

    private AccountDao accountDao;


    public AccountFragment() {
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
        return inflater.inflate(R.layout.fragment_account, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final TextView textViewBalance = view.findViewById(R.id.textViewBalance);
        final EditText editTextNewAmount = view.findViewById(R.id.editTextNewAmount);
        Button btnAddFunds = view.findViewById(R.id.btnAddFunds);

        textViewBalance.setText("Total Balance = $" + String.valueOf(accountDao.getTotalBalance()));

        btnAddFunds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String newFund = editTextNewAmount.getText().toString();
                accountDao.updateBalance("admin@sara.com", Double.valueOf(newFund));
                textViewBalance.setText("Total Balance = $" + String.valueOf(accountDao.getTotalBalance()));
            }
        });

    }
}
