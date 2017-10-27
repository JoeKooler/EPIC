package com.example.yoons.phcbottomnavigator;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SetupScreenFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SetupScreenFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SetupScreenFragment extends Fragment
{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public SetupScreenFragment()
    {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SetupScreenFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SetupScreenFragment newInstance(String param1, String param2)
    {
        SetupScreenFragment fragment = new SetupScreenFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {

        View view = inflater.inflate(R.layout.fragment_setup_screen, container, false);

        Spinner device_spinner = (Spinner)view.findViewById(R.id.device_spinner);
        ArrayAdapter<CharSequence> device_adapter = ArrayAdapter.createFromResource
                (getContext(),R.array.device_array, android.R.layout.simple_spinner_item);
        device_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        device_spinner.setAdapter(device_adapter);

        Spinner brand_spinner = (Spinner)view.findViewById(R.id.brand_spinner);
        ArrayAdapter<CharSequence> brand_adapter = ArrayAdapter.createFromResource
                (getContext(),R.array.tvs_brand_array, android.R.layout.simple_spinner_item);
        brand_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        brand_spinner.setAdapter(brand_adapter);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri)
    {
        if (mListener != null)
        {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener)
        {
            mListener = (OnFragmentInteractionListener) context;
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
    public interface OnFragmentInteractionListener
    {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
