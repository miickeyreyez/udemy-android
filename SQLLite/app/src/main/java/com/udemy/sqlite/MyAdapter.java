package com.udemy.sqlite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by angel on 9/10/17.
 */

public class MyAdapter extends BaseAdapter
{
    private Context context;
    private List<Car> list;
    private int layout;

    public MyAdapter(Context context, List<Car> list, int layout)
    {
        this.context = context;
        this.list = list;
        this.layout = layout;
    }

    @Override
    public int getCount()
    {
        return list.size();
    }

    @Override
    public Car getItem(int position)
    {
        return list.get(position);
    }

    @Override
    public long getItemId(int id)
    {
        return id;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup)
    {
        ViewHolder vh;
        if (view == null)
        {
            view = LayoutInflater.from(context).inflate(layout, null);
            vh = new ViewHolder();
            vh.id = (TextView) view.findViewById(R.id.txtID);
            vh.marca = (TextView) view.findViewById(R.id.txtMarca);
            vh.modelo = (TextView) view.findViewById(R.id.txtModelo);
            view.setTag(vh);
        }
        else
        {
            vh = (ViewHolder) view.getTag();
        }

        Car currentCar = list.get(position);

        vh.id.setText(currentCar.getId() + "");
        vh.marca.setText(currentCar.getMarca());
        vh.modelo.setText(currentCar.getModelo());

        return view;
    }

    public class ViewHolder
    {
        TextView id;
        TextView marca;
        TextView modelo;
    }
}
