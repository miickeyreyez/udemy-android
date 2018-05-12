package com.udemy.picasso;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import javax.security.auth.callback.Callback;

import com.squareup.picasso.Picasso;

/**
 * Created by INSPIRON on 8/5/2018.
 */

public class AnimalAdapter extends RecyclerView.Adapter<AnimalAdapter.ViewHolder> {

    private Context context;
    private String[] animals;
    private int layout;

    public AnimalAdapter(Context context,String[] animals, int layout) {
        this.context = context;
        this.animals = animals;
        this.layout = layout;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Picasso.get().load(animals[position]).fit().placeholder(R.drawable.spinner).into(holder.image, new com.squareup.picasso.Callback() {
            @Override
            public void onSuccess() {
                //holder.image.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onError(Exception e) {
                Toast.makeText(context, "Error!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return animals.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;

        public ViewHolder(View view) {
            super(view);
            this.image = (ImageView)view.findViewById(R.id.imageViewLayout);
        }
    }
}
