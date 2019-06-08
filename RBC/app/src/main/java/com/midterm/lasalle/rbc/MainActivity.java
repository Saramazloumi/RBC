package com.midterm.lasalle.rbc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.midterm.lasalle.rbc.dao.AccountDao;
import com.midterm.lasalle.rbc.dao.AccountFactory;
import com.midterm.lasalle.rbc.fragment.AccountFragment;
import com.midterm.lasalle.rbc.fragment.InteracFragment;
import com.midterm.lasalle.rbc.model.Account;

public class MainActivity extends AppCompatActivity {

    private static final String ACCOUNT_FRAGMENT = "ACCOUNT_FRAGMENT";
    private static final String INTERAC_FRAGMENT = "INTERAC_FRAGMENT";

    private AccountDao accountDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        accountDao = AccountFactory.getAccountDao();
        accountDao.addUserAccount(new Account(2000, "admin@sara.com"));
        accountDao.addUserAccount(new Account(1000, "fido@gmail.com"));
        accountDao.addUserAccount(new Account(3000, "mami@gmail.com"));
        initialize();
    }

    private void initialize() {

        Button btnAccount = findViewById(R.id.btnAccount);
        btnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AccountFragment accountFragment = new AccountFragment();
                addFragment(R.id.fragmentContainer, accountFragment,ACCOUNT_FRAGMENT, true);
            }
        });

        Button btnInterac = findViewById(R.id.btnInterac);
        btnInterac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InteracFragment interacFragment = new InteracFragment();
                addFragment(R.id.fragmentContainer,interacFragment,INTERAC_FRAGMENT, true);
            }
        });
    }

    public void addFragment(int container, Fragment fragment, String tag, boolean addToBackStack){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.add(container, fragment, tag);
        if(addToBackStack) {
            fragmentTransaction.addToBackStack("gholi");
        }
        fragmentTransaction.commit();
    }

}
