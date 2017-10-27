package com.udemy.realcrudpizarra.models;

import com.udemy.realcrudpizarra.app.MyApplication;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by angel on 22/10/17.
 */

//Para que la maneje como tabla
public class Note extends RealmObject
{
    @PrimaryKey
    private int id;
    @Required
    private String description;
    @Required
    private Date createdAt;

    //Constructor vacío, lo necesita Realm
    public Note()
    {

    }

    public Note(String description)
    {
        this.id = MyApplication.noteID.incrementAndGet();
        this.description = description;
        this.createdAt = new Date();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
