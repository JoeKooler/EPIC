package com.example.yoons.EPIC;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.util.List;
/**
 * Created by JoeKooler on 14/11/2017.
 */

public class SetupRecyclerViewAdapter extends RecyclerView.Adapter<SetupRecyclerViewAdapter.SetupRecyclerViewHolder>
{
    List<String> deviceTypeList;

    private Context context;

    SetupScreenFragment setupScreenFragment;



    public SetupRecyclerViewAdapter(Context context, List<String> deviceTypeList, SetupScreenFragment setupScreenFragment)
    {
        this.context = context;
        this.deviceTypeList = deviceTypeList;
        this.setupScreenFragment = setupScreenFragment;
    }

    @Override
    public SetupRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        return new SetupRecyclerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.setup_recycle_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(final SetupRecyclerViewHolder holder, int position)
    {
        final String deviceType = deviceTypeList.get(position);



        holder.deviceType.setText(deviceType);
        switch (deviceType)
        {
            case "Television":
                holder.deviceTypeImage.setImageResource(R.drawable.tvimg);
                break;
            case "Air Conditioner":
                holder.deviceTypeImage.setImageResource(R.drawable.airimg);
                break;
            case "Projector":
                holder.deviceTypeImage.setImageResource(R.drawable.projectimg);
                break;
        }

        holder.setupScreenLayout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                android.support.v4.app.Fragment selectBrandFragment = new SelectBrandFragment();
                Bundle bundle = new Bundle();
                bundle.putString("deviceType",deviceType);
                selectBrandFragment.setArguments(bundle);
                android.support.v4.app.FragmentTransaction transaction = setupScreenFragment.getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.content,selectBrandFragment);
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });


    }

    @Override
    public int getItemCount()
    {
        return deviceTypeList.size();
    }

    class SetupRecyclerViewHolder extends RecyclerView.ViewHolder
    {
        TextView deviceType;
        ImageView deviceTypeImage;
        LinearLayout setupScreenLayout;

        public SetupRecyclerViewHolder(View itemView)
        {
            super(itemView);

            deviceType = (TextView) itemView.findViewById(R.id.deviceType);
            deviceTypeImage = (ImageView) itemView.findViewById(R.id.typeImage);
            setupScreenLayout = (LinearLayout) itemView.findViewById(R.id.setup_recycler_layout);
        }
    }
}
