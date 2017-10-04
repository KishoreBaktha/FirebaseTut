package com.example.kishorebaktha.firebasetut;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;

/**
 * Created by KISHORE BAKTHA on 7/12/2017.
 */

public class AuthenticationValid extends AppCompatActivity {
    Button mFirebaseButton;
    EditText t1,t2;
    RelativeLayout activity_main;
    private DatabaseReference mDataBase;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.menu_id)
        {
            AuthUI.getInstance().signOut(this).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Snackbar.make(activity_main,"Successfully signed out",Snackbar.LENGTH_SHORT).show();
                    finish();
                }
            });
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);
        activity_main=(RelativeLayout)findViewById(R.id.main);
    }
    public void STORE(View view)
    {
        Intent intent=new Intent(getApplicationContext(),Store.class);
        startActivity(intent);
    }
    public void RETRIEVE(View view)
    {
        Intent intent=new Intent(getApplicationContext(),Retrieve.class);
        startActivity(intent);
    }
}


