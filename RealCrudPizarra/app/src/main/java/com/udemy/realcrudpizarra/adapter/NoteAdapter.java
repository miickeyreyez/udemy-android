package com.udemy.realcrudpizarra.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.udemy.realcrudpizarra.NoteActivity;
import com.udemy.realcrudpizarra.R;
import com.udemy.realcrudpizarra.models.Note;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import io.realm.RealmList;

/**
 * Created by INSPIRON on 12/11/2017.
 */

public class NoteAdapter extends BaseAdapter
{
    private Context context;
    private List<Note> lista;
    private int layout;

    public NoteAdapter(Context context, List<Note> notes, int layout)
    {
        this.context = context;
        this.lista = notes;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int i) {
        return lista.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup)
    {
        viewHolder vh;
        if(view == null)
        {
            view = LayoutInflater.from(context).inflate(layout,null);
            vh = new viewHolder();
            vh.description = (TextView) view.findViewById(R.id.textViewNoteTitle);
            vh.createdAt = (TextView) view.findViewById(R.id.textViewCreatedAt);
            view.setTag(vh);
        }
        else
        {
            vh = (viewHolder) view.getTag();
        }

        Note note = lista.get(position);

        vh.description.setText(note.getDescription());
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String date = df.format(note.getCreatedAt());
        vh.createdAt.setText(date);

        return view ;
    }

    public class viewHolder
    {
        TextView description;
        TextView createdAt;
    }

}
