package com.example.kishorebaktha.firebasetut;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by KISHORE BAKTHA on 7/9/2017.
 */

public class Retrieve extends AppCompatActivity {
    private DatabaseReference mDataBase,mDataBase2;
    TextView t1,t2;
    private ListView mUserList;
    private ArrayList<String> mUserName=new ArrayList<>();
    private ArrayList<String > mKeya=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.retrieve);
        mDataBase= FirebaseDatabase.getInstance().getReference();
        mUserList=(ListView)findViewById(R.id.list);
        final ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,mUserName);
        mUserList.setAdapter(arrayAdapter);
      //  DataSnapshot dataSnapshot=mDataBase.;
               mDataBase.addChildEventListener(new ChildEventListener() {
                   @Override
                   public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                         String value=dataSnapshot.getValue(String.class);
                       mUserName.add(value);
                       String key=dataSnapshot.getKey();
                       mKeya.add(key);
                       arrayAdapter.notifyDataSetChanged();
                   }

                   @Override
                   public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                       String value=dataSnapshot.getValue(String.class);
                       //to get position where value changed-
                        String key=dataSnapshot.getKey();
                       int index=mKeya.indexOf(key);
                       mUserName.set(index,value);
                       arrayAdapter.notifyDataSetChanged();
                   }

                   @Override
                   public void onChildRemoved(DataSnapshot dataSnapshot) {

                   }

                   @Override
                   public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                   }

                   @Override
                   public void onCancelled(DatabaseError databaseError) {

                   }
               });
    }
}
