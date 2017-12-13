package com.example.yoons.EPIC;

import android.content.Context;
import android.graphics.Color;
import android.media.Image;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.TimeUnit;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RemoteSelectFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RemoteSelectFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RemoteSelectFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String uniqueKey;
    private int patternNumber = 1;

    private DatabaseReference maxPatternReference, tempRemoteReference, myDeviceReference;
    private FirebaseDatabase firebaseDatabase;

    private long maxPattern;

    private OnFragmentInteractionListener mListener;

    public RemoteSelectFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RemoteSelectFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RemoteSelectFragment newInstance(String param1, String param2) {
        RemoteSelectFragment fragment = new RemoteSelectFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_remote_select, container, false);

        Bundle bundle = getArguments();
        final String deviceType = bundle.getString("deviceType");
        final String deviceBrand = bundle.getString("deviceBrand");

        ImageView backButtonInSelectBrand = (ImageView) view.findViewById(R.id.backButtonInSelectRemote);
        final ImageView selectRemoteNextButton = (ImageView) view.findViewById(R.id.selectRemoteNextButton);
        final ImageView selectRemoteBackButton = (ImageView) view.findViewById(R.id.selectRemoteBackButton);
        ImageView powerButton = (ImageView) view.findViewById(R.id.selectRemotePower);
        ImageView menuButton = (ImageView) view.findViewById(R.id.selectRemoteMenu);
        ImageView volumeUpButton = (ImageView) view.findViewById(R.id.selectRemoteVolumeUp);
        ImageView volumeDownButton = (ImageView) view.findViewById(R.id.selectRemoteVolumeDown);
        Button confirmRemoteSelectionButton = (Button) view.findViewById(R.id.confirmRemoteSelection);

        TextView deviceBrandTest = (TextView) view.findViewById(R.id.deviceBrandinRemote);
        TextView deviceTypeTest = (TextView) view.findViewById(R.id.deviceTypeinRemote);
        TextView downType = (TextView) view.findViewById(R.id.selectRemoteVolumeDownText);
        final TextView upType = (TextView) view.findViewById(R.id.selectRemoteVolumeUpText);
        final TextView versionNumberRemoteSelection = (TextView) view.findViewById(R.id.versionNumberRemoteSelection);

        if(deviceType == "Airconditioner")
        {
            downType.setText("TEMP DOWN");
            upType.setText("TEMP UP");
        }

        firebaseDatabase = FirebaseDatabase.getInstance();

        tempRemoteReference = firebaseDatabase.getReference("TempUnit").child("CurrentRemote");
        tempRemoteReference.child("Device").child("Brand").setValue(deviceBrand);
        tempRemoteReference.child("Device").child("Type").setValue(deviceType);
        tempRemoteReference.child("Device").child("Version").setValue(patternNumber);

        // get reference to 'users' node
        maxPatternReference = firebaseDatabase.getReference("AllVersion").child(deviceType).child(deviceBrand);
        System.out.println(deviceType+""+deviceBrand);

        myDeviceReference = firebaseDatabase.getReference("MyDevice");

        maxPatternReference.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                maxPattern = dataSnapshot.getValue(long.class);
                versionNumberRemoteSelection.setText(patternNumber + "/" + maxPattern);
            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {

            }
        });



        backButtonInSelectBrand.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Fragment selectBrandFragment = new SelectBrandFragment();
                Bundle bundle2 = getArguments();
                String tempDeviceType = bundle2.getString("deviceType");
                bundle2.putString("deviceType",tempDeviceType);
                selectBrandFragment.setArguments(bundle2);
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.content,selectBrandFragment).commit();

            }
        });

        deviceBrandTest.setText(deviceBrand);
        deviceTypeTest.setText(deviceType);

        if (patternNumber == 1)
            selectRemoteBackButton.setColorFilter(Color.WHITE);
        if (maxPattern == 1)
        {
            selectRemoteNextButton.setColorFilter(Color.WHITE);
            selectRemoteBackButton.setColorFilter(Color.WHITE);
        }

        selectRemoteNextButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(patternNumber<maxPattern) {
                    patternNumber++;
                    versionNumberRemoteSelection.setText(patternNumber + "/" + maxPattern);
                    tempRemoteReference.child("Device").child("Version").setValue(patternNumber);
                    selectRemoteBackButton.setColorFilter(Color.BLACK);
                    if(patternNumber>=maxPattern)
                        selectRemoteNextButton.setColorFilter(Color.WHITE);
                }
            }

        });

        selectRemoteBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(patternNumber<=maxPattern && patternNumber > 1)
                {
                    patternNumber--;
                    versionNumberRemoteSelection.setText(patternNumber + "/" + maxPattern);
                    tempRemoteReference.child("Device").child("Version").setValue(patternNumber);
                    selectRemoteNextButton.setColorFilter(Color.BLACK);
                    if(patternNumber<=1)
                        selectRemoteBackButton.setColorFilter(Color.WHITE);
                }
            }
        });


        powerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                tempRemoteReference.child("Status").child("Power").setValue("Clicked");
                try
                {
                    Thread.sleep(500);
                }
                catch(InterruptedException ex)
                {
                    Thread.currentThread().interrupt();
                }
                tempRemoteReference.child("Status").child("Power").setValue("Still");
            }
        });

        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                tempRemoteReference.child("Status").child("Menu").setValue("Clicked");
                try
                {
                    Thread.sleep(500);
                }
                catch(InterruptedException ex)
                {
                    Thread.currentThread().interrupt();
                }
                tempRemoteReference.child("Status").child("Menu").setValue("Still");
            }
        });

        volumeDownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tempRemoteReference.child("Status").child("Volume").setValue("down");
                try
                {
                    Thread.sleep(500);
                }
                catch(InterruptedException ex)
                {
                    Thread.currentThread().interrupt();
                }
                tempRemoteReference.child("Status").child("Volume").setValue("Still");
            }
        });

        volumeUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tempRemoteReference.child("Status").child("Volume").setValue("up");
                try
                {
                    Thread.sleep(500);
                }
                catch(InterruptedException ex)
                {
                    Thread.currentThread().interrupt();
                }
                tempRemoteReference.child("Status").child("Volume").setValue("Still");
            }
        });

        confirmRemoteSelectionButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                uniqueKey = myDeviceReference.push().getKey();
                Device device = new Device(deviceType,deviceBrand,""+patternNumber,uniqueKey);
                myDeviceReference.child(uniqueKey).setValue(device);
                myDeviceReference.child(uniqueKey).child("Status").child("Power").setValue("Off");
                switch (deviceType)
                {
                    case "Airconditioner":
                    {
                        myDeviceReference.child(uniqueKey).child("Status").child("Temperature").setValue(25);
                        myDeviceReference.child(uniqueKey).child("Status").child("Mode").setValue("Freeze");
                    }
                    case "Television":
                    {
                        myDeviceReference.child(uniqueKey).child("Status").child("Volume").setValue(50);
                        myDeviceReference.child(uniqueKey).child("Status").child("Channel").setValue(1);
                    }
                    case "Projector":
                    {

                    }
                }
            }
        });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        if (context instanceof RemoteSelectFragment.OnFragmentInteractionListener)
        {
            mListener = (RemoteSelectFragment.OnFragmentInteractionListener) context;
        }
        else
        {
            Toast.makeText(context, "Setup Attached", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
