package com.udemy.realcrudpizarra.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.udemy.realcrudpizarra.MainActivity;
import com.udemy.realcrudpizarra.R;
import com.udemy.realcrudpizarra.models.Board;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import io.realm.RealmResults;

/**
 * Created by INSPIRON on 8/11/2017.
 */

public class BoardAdapter extends BaseAdapter {

    private Context context;
    private List<Board> boards;
    private int layout;

    public BoardAdapter(Context context, List<Board> boards, int layout)
    {
        this.context = context;
        this.boards = boards;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return this.boards.size();
    }

    @Override
    public Object getItem(int i) {
        return this.boards.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        ViewHolder vh;
        if(view == null)
        {
            view = LayoutInflater.from(context).inflate(layout,null);
            vh = new ViewHolder();
            vh.title = (TextView) view.findViewById(R.id.textViewBoardTitle);
            vh.notes = (TextView) view.findViewById(R.id.textViewBoardNotes);
            vh.createdAt = (TextView) view.findViewById(R.id.textViewBoardDate);
            view.setTag(vh);
        }
        else
        {
            vh = (ViewHolder) view.getTag();
        }
        Board board = boards.get(i);
        vh.title.setText(board.getTitle());
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String created = df.format(board.getCreatedAt());
        vh.createdAt.setText(created);
        int numberOfNotes = board.getNotes().size();
        String textForNotes = "";
        if(numberOfNotes == 1)
        {
            textForNotes = numberOfNotes  + " " + " Note. ";
        }
        else
        {
            textForNotes = numberOfNotes + " " + " Notes. ";
        }
        vh.notes.setText(textForNotes);

        return view;
    }

    public class ViewHolder
    {
        TextView title;
        TextView notes;
        TextView createdAt;
    }
}
