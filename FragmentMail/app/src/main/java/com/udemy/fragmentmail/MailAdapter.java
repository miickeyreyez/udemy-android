package com.udemy.fragmentmail;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by INSPIRON on 15/4/2018.
 */

public class MailAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Mail> list;
    private int SUBJECT_MAX_LENGTH = 40;
    private int MESSAGE_MAX_LENGTH = 60;

    public MailAdapter(Context context, int layout, List<Mail> list){
        this.context = context;
        this.layout = layout;
        this.list = list;
    }

    @Override
    public int getCount(){
        return list.size();
    }

    @Override
    public Mail getItem(int position){
        return list.get(position);
    }

    @Override
    public long getItemId(int id){
        return id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup){
        ViewHolder holder;

        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(layout,null);
            holder = new ViewHolder();
            holder.subject = (TextView)convertView.findViewById(R.id.textViewListSubject);
            holder.message = (TextView)convertView.findViewById(R.id.textViewListMessage);
            holder.sender = (TextView)convertView.findViewById(R.id.textViewListSenderName);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }

        Mail currentMail = getItem(position);

        //Cortar el título y reemplazar por puntos suspensivos
        String shortSubject;
        String shortMessage;

        if(currentMail.getSubject().length() > SUBJECT_MAX_LENGTH){
            shortSubject = currentMail.getSubject().substring(0,SUBJECT_MAX_LENGTH) + "...";
        } else {
            shortSubject = currentMail.getSubject();
        }

        holder.subject.setText(shortSubject);

        if(currentMail.getMessage().length() > MESSAGE_MAX_LENGTH){
            shortMessage = currentMail.getMessage().substring(0,MESSAGE_MAX_LENGTH) + "...";
        } else {
            shortMessage = currentMail.getMessage();
        }

        holder.message.setText(shortMessage);

        //Coger solamente la primer letra del correo
        holder.sender.setText(currentMail.getSenderName().substring(0,1));
        holder.sender.getBackground().setColorFilter(Color.parseColor(currentMail.getColor()), PorterDuff.Mode.SRC);

        return convertView;
    }

    static class ViewHolder{
        private TextView subject;
        private TextView message;
        private TextView sender;
    }
}
