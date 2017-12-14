package com.example.yoons.EPIC;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FixedRemoteActivity extends AppCompatActivity
{

    //DatabaseReference
    DatabaseReference currentReference,mydeviceReference;
    FirebaseDatabase firebaseDatabase;
    private String powerStatus;
    private String setRemoteUpDownReference;
    private int uniqueValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fixed_remote);

        Intent intent = getIntent();

        final String deviceType = intent.getStringExtra("deviceType");
        String deviceBrand = intent.getStringExtra("deviceBrand");
        String deviceVersion = intent.getStringExtra("deviceVersion");
        final String deviceUID = intent.getStringExtra("deviceUID");

        TextView deviceBrandinRemote = (TextView) findViewById(R.id.deviceBrandinRemote);
        TextView deviceTypeinRemote = (TextView) findViewById(R.id.deviceTypeinRemote);
        final TextView powerStatusTextView = (TextView) findViewById(R.id.powerStatusinRemote);
        final TextView uniqueStatus = (TextView) findViewById(R.id.otherStatusinRemote);

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

        currentReference.child("Device").child("Type").setValue(deviceType);
        currentReference.child("Device").child("Brand").setValue(deviceBrand);
        currentReference.child("Device").child("Version").setValue(deviceVersion);

        powerButton.setColorFilter(Color.WHITE);
        upButton.setColorFilter(Color.WHITE);
        downButton.setColorFilter(Color.WHITE);
        leftButton.setColorFilter(Color.WHITE);
        rightButton.setColorFilter(Color.WHITE);

        switch (deviceType)
        {
            case "Airconditioner":
            {
                currentReference.child("Status").child("Temperature").setValue("Still");
                currentReference.child("Status").child("Mode").setValue("Still");
                currentReference.child("Status").child("Power").setValue("Still");
                setRemoteUpDownReference = "Temperature";
                break;
            }
            case "Television":
            {
                currentReference.child("Status").child("Volume").setValue("Still");
                currentReference.child("Status").child("Channel").setValue("Still");
                currentReference.child("Status").child("Menu").setValue("Still");
                currentReference.child("Status").child("Source").setValue("Still");
                currentReference.child("Status").child("Power").setValue("Still");
                currentReference.child("Status").child("OK").setValue("Still");
                setRemoteUpDownReference = "Channel";
                break;
            }
            case "Projector":
            {
                currentReference.child("Status").child("Channel").setValue("Still");
                currentReference.child("Status").child("Menu").setValue("Still");
                currentReference.child("Status").child("Source").setValue("Still");
                currentReference.child("Status").child("Power").setValue("Still");
                currentReference.child("Status").child("OK").setValue("Still");
                setRemoteUpDownReference = "Direction";
                break;
            }
        }

        deviceBrandinRemote.setText(deviceBrand);
        deviceTypeinRemote.setText(deviceType);

        mydeviceReference.child(deviceUID).child("Status").child("Power").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                powerStatus = dataSnapshot.getValue(String.class);
                powerStatusTextView.setText("Power: " + powerStatus);
                System.out.println(powerStatus);
            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {

            }
        });

        uniqueStatus.setText("");

        if (deviceType.equals("Television")||deviceType.equals("Airconditioner")){
        mydeviceReference.child(deviceUID).child("Status").child(setRemoteUpDownReference).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                uniqueValue = dataSnapshot.getValue(int.class);

                switch (deviceType) {
                    case "Airconditioner":
                        uniqueStatus.setText(uniqueValue+"°C");
                        break;
                    case "Television":
                        uniqueStatus.setText(uniqueValue+"");
                        break;
                }
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        }

        powerButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                currentReference.child("Status").child("Power").setValue("Clicked");
                if(powerStatus=="off") {
                    powerStatus = "on";
                    mydeviceReference.child(deviceUID).child("Status").child("Power").setValue("on");
                }
                else {
                    powerStatus = "off";
                    mydeviceReference.child(deviceUID).child("Status").child("Power").setValue("off");
                }
                try
                {
                    Thread.sleep(500);
                }
                catch(InterruptedException ex)
                {
                    Thread.currentThread().interrupt();
                }
                currentReference.child("Status").child("Power").setValue("Still");
            }
        });

        upButton.setOnClickListener(new View.OnClickListener()
        {
            int upValue;
            @Override
            public void onClick(View v)
            {
                switch (deviceType)
                {
                    case "Airconditioner": {
                        setRemoteUpDownReference = "Temperature";
                        break;
                    }
                    case "Television": {
                        setRemoteUpDownReference = "Channel";
                        break;
                    }
                    case "Projector": {
                        setRemoteUpDownReference = "Direction";
                        break;
                    }
                }
                currentReference.child("Status").child(setRemoteUpDownReference).setValue("Up");
                try
                {
                    Thread.sleep(500);
                }
                catch(InterruptedException ex)
                {
                    Thread.currentThread().interrupt();
                }
                currentReference.child("Status").child(setRemoteUpDownReference).setValue("Still");
                mydeviceReference.child(deviceUID).child("Status").child(setRemoteUpDownReference).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(deviceType.equals("Television")||deviceType.equals("Aircondition"))
                        {
                            upValue = dataSnapshot.getValue(int.class);
                            upValue++;
                            System.out.println(deviceType+":"+upValue);
                            if(deviceType.equals("Airconditioner") &&upValue>=30) {
                                System.out.println(upValue);
                                mydeviceReference.child(deviceUID).child("Status").child(setRemoteUpDownReference).setValue(30);
                            }
                            else if(deviceType.equals("Television") &&upValue>=100) {
                                mydeviceReference.child(deviceUID).child("Status").child(setRemoteUpDownReference).setValue(100);
                            }
                            else
                            {
                                mydeviceReference.child(deviceUID).child("Status").child(setRemoteUpDownReference).setValue(upValue);
                            }
                        }
                        else
                            System.out.println("Nope LuL");
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

        downButton.setOnClickListener(new View.OnClickListener()
        {
            int downValue;
            @Override
            public void onClick(View v)
            {

                currentReference.child("Status").child(setRemoteUpDownReference).setValue("Down");
                try
                {
                    Thread.sleep(500);
                }
                catch(InterruptedException ex)
                {
                    Thread.currentThread().interrupt();
                }
                currentReference.child("Status").child(setRemoteUpDownReference).setValue("Still");
                mydeviceReference.child(deviceUID).child("Status").child(setRemoteUpDownReference).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        downValue = dataSnapshot.getValue(int.class);
                        downValue--;
                        System.out.println(deviceType+":"+downValue);
                        if(deviceType.equals("Airconditioner") &&downValue<=15) {
                            System.out.println(downValue);
                            mydeviceReference.child(deviceUID).child("Status").child(setRemoteUpDownReference).setValue(15);
                        }
                        else if(deviceType.equals("Television") &&downValue<=0) {
                            mydeviceReference.child(deviceUID).child("Status").child(setRemoteUpDownReference).setValue(0);
                        }
                        else
                        {
                            mydeviceReference.child(deviceUID).child("Status").child(setRemoteUpDownReference).setValue(downValue);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

        rightButton.setOnClickListener(new View.OnClickListener()
        {
            int upValue;
            @Override
            public void onClick(View v)
            {
                if(deviceType.equals("Television")){
                currentReference.child("Status").child("Volume").setValue("Up");
                try
                {
                    Thread.sleep(500);
                }
                catch(InterruptedException ex)
                {
                    Thread.currentThread().interrupt();
                }
                currentReference.child("Status").child("Volume").setValue("Still");
                mydeviceReference.child(deviceUID).child("Status").child("Volume").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot)
                    {
                        upValue = dataSnapshot.getValue(int.class);
                        upValue++;
                        System.out.println(deviceType+":"+upValue);
                        if(upValue>=100) {
                            mydeviceReference.child(deviceUID).child("Status").child("Volume").setValue(100);
                        }
                        else
                        {
                            mydeviceReference.child(deviceUID).child("Status").child("Volume").setValue(upValue);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
            else
                System.out.println("Nope LuL");
            }
        });

        leftButton.setOnClickListener(new View.OnClickListener()
        {
            int downValue;
            @Override
            public void onClick(View v)
            {
                if(deviceType.equals("Television")){
                currentReference.child("Status").child("Volume").setValue("Down");
                try
                {
                    Thread.sleep(500);
                }
                catch(InterruptedException ex)
                {
                    Thread.currentThread().interrupt();
                }
                currentReference.child("Status").child("Volume").setValue("Still");
                mydeviceReference.child(deviceUID).child("Status").child("Volume").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        downValue = dataSnapshot.getValue(int.class);
                        downValue--;
                        System.out.println(deviceType+":"+downValue);
                        if(downValue<=0){
                            System.out.println(downValue);
                            mydeviceReference.child(deviceUID).child("Status").child("Volume").setValue(0);
                        }
                        else
                        {
                            mydeviceReference.child(deviceUID).child("Status").child("Volume").setValue(downValue);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
            else
                System.out.println("Nope LuL");
        }});

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentReference.child("Status").child("OK").setValue("Clicked");
                try
                {
                    Thread.sleep(500);
                }
                catch(InterruptedException ex)
                {
                    Thread.currentThread().interrupt();
                }
                currentReference.child("Status").child("OK").setValue("Still");
            }
        });

        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentReference.child("Status").child("Menu").setValue("Clicked");
                try
                {
                    Thread.sleep(500);
                }
                catch(InterruptedException ex)
                {
                    Thread.currentThread().interrupt();
                }
                currentReference.child("Status").child("Menu").setValue("Still");
            }
        });

        videoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentReference.child("Status").child("Source").setValue("Clicked");
                try
                {
                    Thread.sleep(500);
                }
                catch(InterruptedException ex)
                {
                    Thread.currentThread().interrupt();
                }
                currentReference.child("Status").child("Source").setValue("Still");
            }
        });
    }

}
