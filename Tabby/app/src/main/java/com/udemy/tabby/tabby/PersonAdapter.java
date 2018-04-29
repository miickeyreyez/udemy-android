package com.udemy.tabby.tabby;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by INSPIRON on 29/4/2018.
 */

public class PersonAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Person> persons;

    public PersonAdapter(Context context, int layout, List<Person> persons){
        this.context = context;
        this.layout = layout;
        this.persons = persons;
    }

    @Override
    public int getCount() {
        return persons.size();
    }

    @Override
    public Object getItem(int i) {
        return persons.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder;

        if(view == null) {
            view = LayoutInflater.from(context).inflate(layout,null);
            holder = new ViewHolder();
            holder.name = (TextView)view.findViewById(R.id.textViewPersonName);
            holder.country = (TextView)view.findViewById(R.id.textViewPersonCountry);
            holder.image = (ImageView)view.findViewById(R.id.imageViewFlag);
            view.setTag(holder);
        } else {
            holder = (ViewHolder)view.getTag();
        }

        Person currentPerson = (Person) getItem(i);
        holder.name.setText(currentPerson.getName());
        holder.country.setText(currentPerson.getCountry().getName());

        String url = currentPerson.getCountry().getFlagUrl();
        Picasso.get().load(url).into(holder.image);
        //Picasso.get().load("http://i.imgur.com/DvpvklR.png").into(holder.image);


        return view;
    }

    static class ViewHolder {
        private TextView name;
        private TextView country;
        private ImageView image;
    }
}
