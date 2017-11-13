package com.udemy.realcrudpizarra;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.udemy.realcrudpizarra.adapter.BoardAdapter;
import com.udemy.realcrudpizarra.models.Board;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmList;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity implements RealmChangeListener<RealmResults<Board>>, AdapterView.OnItemClickListener
{
    private FloatingActionButton fab;
    private Realm realm;
    private ListView listView;
    private BoardAdapter adapter;
    private RealmResults<Board> boards;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inicializar Realm
        realm = Realm.getDefaultInstance();
        //Obtener todas las notas
        boards = realm.where(Board.class).findAll();

        //Listener para eventos del board
        boards.addChangeListener(this);

       // List<Board> copyBoards = realm.copyFromRealm(boards);
        adapter = new BoardAdapter(getApplicationContext(), boards, R.layout.list_view_board);
        listView = (ListView)findViewById(R.id.listViewBoard);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertFromCreatingBoard("Add new board","Board name");
            }
        });

        /*
        realm.beginTransaction();
        realm.deleteAll();
        realm.commitTransaction();
        */
    }

    private void createNewBoard(String name)
    {
        realm.beginTransaction();
        Board board = new Board(name);
        realm.copyToRealm(board);
        realm.commitTransaction();
        /*realm.executeTransaction(new Realm.Transaction(){
        * @Override
        * public void execute(Realm realm){
        *       Board board = new Board(name); //Debe de ser final el name
        *       realm.copyToRealm(board);
        *   }
        * }*/
    }

    private void showAlertFromCreatingBoard(String title, String message)
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

        View viewInflated = LayoutInflater.from(this).inflate(R.layout.dialog_create_board,null);
        final EditText input = (EditText) viewInflated.findViewById(R.id.editBoard);
        builder.setView(viewInflated);

        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String boardName = "";

                if(input.getText().length() > 0)
                {
                    boardName = input.getText().toString().trim();
                }

                if(boardName.length() > 0)
                {
                    createNewBoard(boardName);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"There are no name for new board", Toast.LENGTH_SHORT).show();
                }
            }
        });

        builder.create().show();
    }

    @Override
    public void onChange(RealmResults<Board> boards) {
        //Refrescar el adaptador
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Intent intent = new Intent(MainActivity.this,NoteActivity.class);
        intent.putExtra("id",boards.get(position).getId());
        startActivity(intent);
    }
}
