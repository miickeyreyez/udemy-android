package com.udemy.sqlrealm.application;

/**
 * Created by angel on 22/10/17.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.udemy.sqlrealm.R;
import com.udemy.sqlrealm.models.Dog;
import com.udemy.sqlrealm.models.Person;

import java.util.List;

public class MyAdapter extends BaseAdapter
{

    private Context context;
    private int layout;
    private List<Person> people;

    //Constructor
    public MyAdapter(Context context, int layout, List<Person> people)
    {
        this.context = context;
        this.layout = layout;
        this.people = people;
    }

    @Override
    public int getCount() {
        return this.people.size();
    }

    @Override
    public Object getItem(int i) {
        return this.people.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;//this.getItemId(i);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {

        //Necesitamos un view holder para mantener la vista siempre
        ViewHolder holder;

        if(view == null)
        {
            LayoutInflater layoutInflater = LayoutInflater.from(this.context);
            view = layoutInflater.inflate(this.layout, null);
            holder = new ViewHolder();
            holder.nameTextView = (TextView) view.findViewById(R.id.textViewPersonName);
            holder.dogTextView = (TextView) view.findViewById(R.id.textViewDogName);
            view.setTag(holder);
        }

        else
        {
            holder = (ViewHolder)view.getTag();
        }

        String currentName = people.get(i).getName();
        holder.nameTextView.setText(people.get(i).getId() + ") " + currentName);
        String dogsInfo = "";
        for(Dog dog : people.get(i).getDogs())
        {
            dogsInfo += dog.getId() + ") " + dog.getName();
        }
        holder.dogTextView.setText(dogsInfo);

        return view;
    }

    static class ViewHolder
    {
        private TextView nameTextView;
        private TextView dogTextView;
    }
}
