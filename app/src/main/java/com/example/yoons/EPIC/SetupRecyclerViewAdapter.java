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
 * Created by JoeKooler on 14/11/2017.
 */

public class SetupRecyclerViewAdapter extends RecyclerView.Adapter<SetupRecyclerViewAdapter.SetupRecyclerViewHolder>
{
    List<String> deviceTypeList;

    public SetupRecyclerViewAdapter(List<String> deviceTypeList)
    {
        this.deviceTypeList = deviceTypeList;
    }

    @Override
    public SetupRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        return new SetupRecyclerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.setup_recycle_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(final SetupRecyclerViewHolder holder, int position)
    {
        String devicetype = deviceTypeList.get(position);

        holder.deviceType.setText(devicetype);
        switch (devicetype)
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
        return deviceTypeList.size();
    }

    class SetupRecyclerViewHolder extends RecyclerView.ViewHolder
    {
        TextView deviceType;
        ImageView deviceTypeImage;

        public SetupRecyclerViewHolder(View itemView)
        {
            super(itemView);

            deviceType = (TextView) itemView.findViewById(R.id.deviceType);
            deviceTypeImage = (ImageView) itemView.findViewById(R.id.typeImage);
        }
    }
}
