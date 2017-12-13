package com.example.yoons.EPIC;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class FixedRemoteActivity extends AppCompatActivity
{

    //DatabaseReference
    DatabaseReference currentReference,mydeviceReference;
    FirebaseDatabase firebaseDatabase;
    private String status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fixed_remote);

        Intent intent = getIntent();

        String deviceType = intent.getStringExtra("deviceType");
        String deviceBrand = intent.getStringExtra("deviceBrand");
        String deviceVersion = intent.getStringExtra("deviceVersion");
        String deviceUID = intent.getStringExtra("deviceUID");

        TextView deviceInfo = (TextView) findViewById(R.id.deviceInfoinRemote);
        final TextView powerStatus = (TextView) findViewById(R.id.powerStatusinRemote);
        TextView uniqueStatus = (TextView) findViewById(R.id.otherStatusinRemote);

        TextView menuButton = (TextView) findViewById(R.id.menuButtoninRemote);
        TextView videoButton = (TextView) findViewById(R.id.videoButtoninRemote);
        TextView okButton = (TextView) findViewById(R.id.okButtoninRemote);

        ImageView powerButton = (ImageView) findViewById(R.id.powerButtoninRemote);
        ImageView upButton = (ImageView) findViewById(R.id.upButtoninRemote);
        ImageView downButton = (ImageView) findViewById(R.id.downButtoninRemote);
        ImageView leftButton = (ImageView) findViewById(R.id.leftButtoninRemote);
        ImageView rightButton = (ImageView) findViewById(R.id.rightButtoninRemote);

        firebaseDatabase = FirebaseDatabase.getInstance();
        currentReference = firebaseDatabase.getReference("TempUnit").child("CurrentRemote");
        mydeviceReference = firebaseDatabase.getReference("MyDevice");

        switch (deviceType)
        {
            case "Airconditioner":
            {
                currentReference.child("Status").child("Temperature").setValue("still");
                currentReference.child("Status").child("Mode").setValue("still");
                currentReference.child("Status").child("Power").setValue("still");
            }
            case "Television":
            {
                currentReference.child("Status").child("Volume").setValue("still");
                currentReference.child("Status").child("Channel").setValue("still");
                currentReference.child("Status").child("Menu").setValue("still");
                currentReference.child("Status").child("Source").setValue("still");
                currentReference.child("Status").child("Power").setValue("still");
            }
            case "Projector":
            {
                currentReference.child("Status").child("Channel").setValue("still");
                currentReference.child("Status").child("Menu").setValue("still");
                currentReference.child("Status").child("Source").setValue("still");
                currentReference.child("Status").child("Power").setValue("still");
            }
        }

        deviceInfo.setText(deviceType + " " + deviceBrand + " " + deviceVersion);
        mydeviceReference.child(deviceUID).child("Status").child("Power").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                status = dataSnapshot.getValue(String.class);
                powerStatus.setText("Power: " + status);
                System.out.println(powerStatus);
            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {

            }
        });


    }

}
