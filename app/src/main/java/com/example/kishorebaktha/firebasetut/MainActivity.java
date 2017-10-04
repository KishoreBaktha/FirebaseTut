package com.example.kishorebaktha.firebasetut;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class MainActivity extends AppCompatActivity {
    private DatabaseReference mDataBase;
     EditText email,password;
    private FirebaseAuth mAuth;
    //to check if user has already logged in
    private FirebaseAuth.AuthStateListener mAuthListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        email=(EditText)findViewById(R.id.email);
        password=(EditText)findViewById(R.id.passsword);
        mAuth=FirebaseAuth.getInstance();
        mAuthListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
               if(mAuth.getCurrentUser()!=null)
               {
                   startActivity(new Intent(MainActivity.this,AuthenticationValid.class));
               }
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    public void REGISTER(View view)
    {
        String email2=email.getText().toString();
        String password2=password.getText().toString();
        if(TextUtils.isEmpty(email2)||TextUtils.isEmpty(password2))
        {
            Toast.makeText(MainActivity.this,"Fields are empty",Toast.LENGTH_SHORT).show();
        }
        else
        {
            mAuth.signInWithEmailAndPassword(email2,password2).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(!task.isSuccessful())
                        Toast.makeText(MainActivity.this,"SIGN IN FAILED",Toast.LENGTH_SHORT).show();
                    else
                    {
                        email.setText("");
                        password.setText("");
                    }
                }
            });
        }
    }
}