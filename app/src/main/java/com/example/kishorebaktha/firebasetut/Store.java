package com.example.kishorebaktha.firebasetut;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by KISHORE BAKTHA on 7/9/2017.
 */

public class Store extends AppCompatActivity {
    Button mFirebaseButton;
    EditText t1,t2;
    private DatabaseReference mDataBase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFirebaseButton=(Button)findViewById(R.id.firebase_btn);
        t1=(EditText)findViewById(R.id.editText);
        //point to root directory
        mDataBase= FirebaseDatabase.getInstance().getReference();
        mFirebaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String m=t1.getText().toString();
                mDataBase.push().setValue(m).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                            Toast.makeText(getApplicationContext(),"SUCCESS",Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(getApplicationContext(),"ERROR",Toast.LENGTH_SHORT).show();
                    }
                });;
                //HashMap<String ,String> dataMap=new HashMap<String, String>();
                //dataMap.put("name",m);
                //dataMap.put("email",m2);
                //1-Create child in root directory
                //2-Asssign some value to child object
//                mDataBase.push().setValue(dataMap).addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        if(task.isSuccessful())
//                            Toast.makeText(getApplicationContext(),"SUCCESS",Toast.LENGTH_SHORT).show();
//                        else
//                            Toast.makeText(getApplicationContext(),"ERROR",Toast.LENGTH_SHORT).show();
//                    }
//                });

            }
        });
    }
}

