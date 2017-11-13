package com.udemy.realcrudpizarra;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
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
}
