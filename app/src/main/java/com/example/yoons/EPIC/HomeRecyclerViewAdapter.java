package com.example.yoons.EPIC;

import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
        return new HomeRecyclerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.mini_card_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(final HomeRecyclerViewHolder holder, int position)
    {
        Device device = deviceList.get(position);

        holder.DeviceBrand.setText(device.Brand);
        holder.DeviceVersion.setText(device.Version);
        switch (device.Type)
        {
            case "TV":
                holder.DeviceType.setImageResource(R.drawable.tvicon);
                break;
            case "Airconditioner":
                holder.DeviceType.setImageResource(R.drawable.airicon);
                break;
            case "Projector":
                holder.DeviceType.setImageResource(R.drawable.projectoricon);
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
    }

    @Override
    public int getItemCount()
    {
        return deviceList.size();
    }

    class HomeRecyclerViewHolder extends RecyclerView.ViewHolder
    {
        TextView DeviceBrand,DeviceVersion;
        ImageView DeviceType;

        public HomeRecyclerViewHolder(View itemView)
        {
            super(itemView);

            DeviceBrand = (TextView) itemView.findViewById(R.id.DevicaName);
            DeviceVersion = (TextView) itemView.findViewById(R.id.DevicaVersion);
            DeviceType = (ImageView) itemView.findViewById(R.id.DeviceType);
        }
    }
}
