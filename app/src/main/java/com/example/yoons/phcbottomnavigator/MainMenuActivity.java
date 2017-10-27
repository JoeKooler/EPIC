package com.example.yoons.phcbottomnavigator;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;
//import android.widget.TextView;

public class MainMenuActivity extends AppCompatActivity
{

    //private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener()
    {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item)
        {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            switch (item.getItemId())
            {
                case R.id.navigation_deviceSetup:
                    transaction.replace(R.id.content,new SetupScreenFragment()).commit();
                    return true;
                case R.id.navigation_home:
                    transaction.replace(R.id.content,new HomeFragment()).commit();
                    return true;
                case R.id.navigation_notifications:
                    transaction.replace(R.id.content,new NotificationFragment()).commit();
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        //mTextMessage = (TextView) findViewById(R.id.message);
        ImageView manual_add_button = (ImageView) findViewById(R.id.manual_add_button);
        manual_add_button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(v.getContext(),"Switch lana",Toast.LENGTH_SHORT).show();
                //Intent intent = new Intent(getApplicationContext(),manual_add_activity.class);
            }
        });

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.navigation_home);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);



        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        transaction.replace(R.id.content,new HomeFragment()).commit();

    }

}
