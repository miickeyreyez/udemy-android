package com.udemy.cardrecyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by angel on 7/10/17.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>
{
    private List<String> names;
    private int layout;
    private OnItemClickListener listener;

    public MyAdapter(List<String> names, int layout, OnItemClickListener listener)
    {
        this.names = names;
        this.layout = layout;
        this.listener = listener;
    }

    //Creamos el viewHolder
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    //Cambiar los datos del textView
    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        holder.bind(names.get(position),listener);
    }

    //Obtener el tamaño de la lista
    @Override
    public int getItemCount()
    {
        return names.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView name;

        public ViewHolder(View v)
        {
            super(v);
            //Obtenemos el textView de la vista que nos llega
            this.name = (TextView) v.findViewById(R.id.textView);
        }

        public void bind(final String name, final OnItemClickListener listener)
        {
            //Cambiar el texto al text view
            this.name.setText(name);
            itemView.setOnClickListener(new View.OnClickListener()
                                        {
                                            @Override
                                            public void onClick(View view)
                                            {
                                                //Método para obtener la posición
                                                listener.onItemClick(name,getAdapterPosition());
                                            }
                                        }
            );
        }
    }

    public interface OnItemClickListener
    {
        void onItemClick(String name, int position);
    }

}
