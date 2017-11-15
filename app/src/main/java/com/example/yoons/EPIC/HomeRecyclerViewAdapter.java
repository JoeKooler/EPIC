package com.example.yoons.EPIC;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by yoons on 14/11/2017.
 */

public class HomeRecyclerViewAdapter extends RecyclerView.Adapter<HomeRecyclerViewAdapter.HomeRecyclerViewHolder>
{
    List<Device> deviceList;

    public HomeRecyclerViewAdapter(List<Device> deviceList)
    {
        this.deviceList = deviceList;
    }

    @Override
    public HomeRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        return new HomeRecyclerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_recycler_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(final HomeRecyclerViewHolder holder, int position)
    {
        final Device device = deviceList.get(position);


        holder.deviceBrand.setText(device.Brand);
        holder.deviceVersion.setText(device.Version);
        switch (device.Type)
        {
            case "TV":
                holder.deviceType.setImageResource(R.drawable.tvicon);
                break;
            case "Airconditioner":
                holder.deviceType.setImageResource(R.drawable.airicon);
                break;
            case "Projector":
                holder.deviceType.setImageResource(R.drawable.projectoricon);
                break;
        }

        holder.itemView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener()
        {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
            {
                menu.add(holder.getAdapterPosition(),0,0,"edit");
                menu.add(holder.getAdapterPosition(),1,0,"delete");
            }
        });

        holder.homeScreenLayout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(v.getContext(),FixedRemoteActivity.class);
                intent.putExtra("deviceType", device.Type);
                intent.putExtra("deviceBrand", device.Brand);
                intent.putExtra("deviceVersion", device.Version);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return deviceList.size();
    }

    class HomeRecyclerViewHolder extends RecyclerView.ViewHolder
    {
        TextView deviceBrand;
        TextView deviceVersion;
        ImageView deviceType;
        LinearLayout homeScreenLayout;


        public HomeRecyclerViewHolder(View itemView)
        {
            super(itemView);

            deviceBrand = (TextView) itemView.findViewById(R.id.devicaNameInHome);
            deviceVersion = (TextView) itemView.findViewById(R.id.deviceVersionInHome);
            deviceType = (ImageView) itemView.findViewById(R.id.deviceTypeInHome);
            homeScreenLayout = (LinearLayout) itemView.findViewById(R.id.home_recycler_main_layout);
        }
    }
}
