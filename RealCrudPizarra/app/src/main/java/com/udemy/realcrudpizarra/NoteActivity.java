package com.udemy.realcrudpizarra;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.udemy.realcrudpizarra.adapter.NoteAdapter;
import com.udemy.realcrudpizarra.models.Board;
import com.udemy.realcrudpizarra.models.Note;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * Created by INSPIRON on 9/11/2017.
 */

public class NoteActivity extends AppCompatActivity implements RealmChangeListener<Board>
{

    private ListView listView;
    private FloatingActionButton fab;
    private NoteAdapter adapter;
    private RealmList<Note> notes;
    private Realm realm;
    private int boardId;
    private Board board;

    @Override
    protected void onCreate(Bundle saveInstanceState)
    {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_note);
        realm = Realm.getDefaultInstance();

        if(getIntent().getExtras() != null)
        {
            boardId = getIntent().getExtras().getInt("id");
        }
        else
        {
            boardId = 0;
        }

        //Encontramos el registro
        board = realm.where(Board.class).equalTo("id",boardId).findFirst();
        //Adjuntar el onChange
        board.addChangeListener(this);
        //Para obtener las notas, debe de ser un RealmList
        notes = board.getNotes();

        //Cambiar el nombre del activity
        this.setTitle(board.getTitle());

        fab = (FloatingActionButton) findViewById(R.id.fabNote);
        listView = (ListView) findViewById(R.id.listViewNote);

        //Cast de RealmList to List
        List<Note> notas = realm.copyFromRealm(notes);

        adapter = new NoteAdapter(getApplicationContext(), notes, R.layout.list_view_note);

        listView.setAdapter(adapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertFromCreatingNote("Add new note","Type a new note for " + board.getTitle());
            }
        });

        registerForContextMenu(listView);

    }

    private void showAlertFromCreatingNote(String title, String message)
    {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        if(title != null)
        {
            builder.setTitle(title);
        }

        if(message != null)
        {
            builder.setMessage(message);
        }

        View viewInflated = LayoutInflater.from(this).inflate(R.layout.dialog_create_note,null);
        final EditText input = (EditText) viewInflated.findViewById(R.id.editNote);
        builder.setView(viewInflated);

        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String note = "";

                if(input.getText().length() > 0)
                {
                    note = input.getText().toString().trim();
                }

                if(note.length() > 0)
                {
                    createNewNote(note);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"There are no description for new note", Toast.LENGTH_SHORT).show();
                }
            }
        });

        builder.create().show();
    }

    private void showAlertForEditNote(String title, String message, final Note note)
    {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        if(title != null)
        {
            builder.setTitle(title);
        }

        if(message != null)
        {
            builder.setMessage(message);
        }

        View viewInflated = LayoutInflater.from(this).inflate(R.layout.dialog_create_note,null);
        final EditText input = (EditText) viewInflated.findViewById(R.id.editNote);
        input.setText(note.getDescription());
        builder.setView(viewInflated);

        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String noteName = "";

                if(input.getText().length() > 0)
                {
                    noteName = input.getText().toString().trim();
                }

                if(noteName.equals(note.getDescription()))
                {
                    Toast.makeText(getApplicationContext(),"The description is the same to the current description", Toast.LENGTH_SHORT).show();
                }
                else if(noteName.length() > 0 && !noteName.equals(note.getDescription()))
                {
                    editNote(note,noteName);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"There are no description for current description", Toast.LENGTH_SHORT).show();
                }
            }
        });

        builder.create().show();
    }

    public void createNewNote(String note)
    {
        realm.beginTransaction();
        Note note_ = new Note(note);
        realm.copyToRealm(note_);
        //Esto es para actualizar las notas que tiene el tablero
        board.getNotes().add(note_);
        realm.commitTransaction();
    }

    @Override
    public void onChange(Board board) {
        adapter.notifyDataSetChanged();
    }

    public void editNote(Note note, String noteDescription)
    {
        realm.beginTransaction();
        note.setDescription(noteDescription);
        realm.copyToRealmOrUpdate(note);
        realm.commitTransaction();
    }

    public void deleteNote(Note note)
    {
        realm.beginTransaction();
        note.deleteFromRealm();
        realm.commitTransaction();
    }

    public void deleteAll()
    {
        realm.beginTransaction();
        board.getNotes().deleteAllFromRealm();
        realm.commitTransaction();
    }

    /*Eventos*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_note_activity,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.delete_all_notes:
                deleteAll();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo)
    {
        getMenuInflater().inflate(R.menu.context_menu_note_activity,menu);
        //super.onCreateContextMenu(menu,view,menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item)
    {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId())
        {
            case R.id.delete_note:
                deleteNote(notes.get(info.position));
                return true;
            case R.id.edit_note:
                showAlertForEditNote("Edit board","Change board name",notes.get(info.position));
            default:
                return super.onContextItemSelected(item);
        }
    }

}
