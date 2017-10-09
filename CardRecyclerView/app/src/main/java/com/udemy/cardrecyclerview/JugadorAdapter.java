package com.udemy.cardrecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.udemy.models.Jugador;

import java.util.List;

/**
 * Created by angel on 8/10/17.
 */

public class JugadorAdapter extends RecyclerView.Adapter<JugadorAdapter.ViewHolder>
{
    private List<Jugador> jugadores;
    private int layout;
    private OnItemClickListener listener;
    private Context context;

    public JugadorAdapter(List<Jugador> jugadores, int layout, OnItemClickListener listener)
    {
        this.jugadores = jugadores;
        this.layout = layout;
        this.listener = listener;
    }

    //Creamos el viewHolder
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        context = parent.getContext();
        View v = LayoutInflater.from(parent.getContext()).inflate(layout,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    //Cambiar los datos del textView
    @Override
    public void onBindViewHolder(JugadorAdapter.ViewHolder holder, int position)
    {
        holder.bind(jugadores.get(position),listener);
    }

    //Obtener el tamaño de la lista
    @Override
    public int getItemCount()
    {
        return jugadores.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView name;
        public ImageView avatar;

        public ViewHolder(View v)
        {
            super(v);
            name = (TextView) itemView.findViewById(R.id.txtJugadorCardView);
            avatar = (ImageView) itemView.findViewById(R.id.imageViewJugador);
        }

        public void bind(final Jugador jugador, final OnItemClickListener listener)
        {
            name.setText(jugador.getName());
            //avatar.setImageResource(jugador.getAvatar());
            Picasso.with(context).load(jugador.getAvatar()).fit().into(avatar);
            itemView.setOnClickListener(new View.OnClickListener()
                                        {
                                            @Override
                                            public void onClick(View view)
                                            {
                                                //Método para obtener la posición
                                                listener.onItemClick(jugador,getAdapterPosition());
                                            }
                                        }
            );
        }
    }

    public interface OnItemClickListener
    {
        void onItemClick(Jugador jugador, int position);
    }

}
