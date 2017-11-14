package com.example.yoons.EPIC;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ManualDeviceAddingActivity extends AppCompatActivity
{

    TextView changeText;
    Button button1;
    Button button2;

    DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference testRef = rootRef.child("Test");

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(com.example.yoons.EPIC.R.layout.activity_manual_device_adding);
        Toolbar toolbar = (Toolbar) findViewById(com.example.yoons.EPIC.R.id.toolbar);
        setSupportActionBar(toolbar);

        changeText = (TextView) findViewById(R.id.testChangeEditText) ;
        button1 = (Button) findViewById(R.id.changeOne);
        button2 = (Button) findViewById(R.id.changeTwo);

        FloatingActionButton fab = (FloatingActionButton) findViewById(com.example.yoons.EPIC.R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                testRef.setValue("+");
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


    @Override
    protected void onStart()
    {
        super.onStart();

        testRef.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                String text = dataSnapshot.getValue(String.class);
                changeText.setText(text);
            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {

            }

        });

        button1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                testRef.setValue("Juan");
            }
        });

        button2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                testRef.setValue("Tuu");
            }
        });
    }

}
