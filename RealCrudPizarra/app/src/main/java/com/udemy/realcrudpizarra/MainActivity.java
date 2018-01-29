package com.udemy.realcrudpizarra;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
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

        registerForContextMenu(listView);
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

    private void editBoard(Board board,String boardname)
    {
        realm.beginTransaction();
        board.setTitle(boardname);
        realm.copyToRealmOrUpdate(board);
        realm.commitTransaction();
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

    private void showAlertForEditingBoard(String title, String message, final Board board)
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
        input.setText(board.getTitle());
        builder.setView(viewInflated);

        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String boardName = "";

                if(input.getText().length() > 0)
                {
                    boardName = input.getText().toString().trim();
                }

                if(boardName.equals(board.getTitle()))
                {
                    Toast.makeText(getApplicationContext(),"The name is the same to the current name", Toast.LENGTH_SHORT).show();
                }
                else if(boardName.length() > 0 && !boardName.equals(board.getTitle()))
                {
                    editBoard(board,boardName);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"There are no name for current board", Toast.LENGTH_SHORT).show();
                }
            }
        });

        builder.create().show();
    }

    /*Eventos*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_board_activity,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.deleteAll:
                realm.beginTransaction();
                realm.deleteAll();
                realm.commitTransaction();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view,ContextMenu.ContextMenuInfo menuInfo)
    {
        //Obtener la información para sacar la posición y hacer override del título
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        menu.setHeaderTitle(boards.get(info.position).getTitle());
        getMenuInflater().inflate(R.menu.context_menu_board_activity,menu);
        //super.onCreateContextMenu(menu,view,menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item)
    {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId())
        {
            case R.id.delete_board:
                realm.beginTransaction();
                boards.get(info.position).deleteFromRealm();
                realm.commitTransaction();
                return true;
            case R.id.edit_board:
                showAlertForEditingBoard("Edit board","Change board name",boards.get(info.position));
            default:
                return super.onContextItemSelected(item);
        }
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
