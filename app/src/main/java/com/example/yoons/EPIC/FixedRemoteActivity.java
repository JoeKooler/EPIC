package com.example.yoons.EPIC;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class FixedRemoteActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fixed_remote);

        Intent intent = getIntent();

        String deviceType = intent.getStringExtra("deviceType");
        String deviceBrand = intent.getStringExtra("deviceBrand");
        String deviceVersion = intent.getStringExtra("deviceVersion");

        TextView ddeviceType = (TextView) findViewById(R.id.testDeviceTypeinFixed);
        TextView ddeviceBrand = (TextView) findViewById(R.id.testDeviceVersioninFixed);
        TextView ddeviceVersion = (TextView) findViewById(R.id.testDeviceBrandinFixed);

        ddeviceType.setText(deviceType);
        ddeviceBrand.setText(deviceBrand);
        ddeviceVersion.setText(deviceVersion);
    }

}
