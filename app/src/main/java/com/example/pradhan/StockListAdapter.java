package com.example.pradhan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import javax.annotation.Nullable;

import androidx.annotation.NonNull;

public class StockListAdapter  extends ArrayAdapter<ItemSstockPPojo> {

    Context context;
    int resources;
    TextView name,quantity;

    public StockListAdapter(@Nullable Context context,int resources){
        super(context,resources);
        this.context=context;
        this.resources=resources;
    }



    @NonNull
    @Override
    public View getView(int position, @androidx.annotation.Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(resources,parent,false);
            ItemSstockPPojo sstockPPojo=getItem(position);


            name=convertView.findViewById(R.id.name_stock_list_item);
            quantity=convertView.findViewById(R.id.initial_qty_stock_list_item);


            name.setText(sstockPPojo.getName());
            quantity.setText(sstockPPojo.getQuantity());


        }
        return convertView;
    }
}
