package com.udemy.realcrudpizarra.models;

import com.udemy.realcrudpizarra.app.MyApplication;

import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by angel on 22/10/17.
 */

public class Board extends RealmObject
{
    @PrimaryKey
    private int id;
    @Required
    private String title;
    @Required
    private Date createdAt;
    //Relación
    private RealmList<Note> notes;

    public Board()
    {

    }

    public Board(String title)
    {
        this.id = MyApplication.boardID.incrementAndGet();
        this.title = title;
        this.createdAt = new Date();
        this.notes = new RealmList<Note>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public RealmList<Note> getNotes() {
        return notes;
    }

    public void setNotes(RealmList<Note> notes) {
        this.notes = notes;
    }
}
