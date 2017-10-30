package com.udemy.realcrudpizarra;

import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.udemy.realcrudpizarra.models.Board;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity
{
    private FloatingActionButton fab;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inicializar Realm
        realm = Realm.getDefaultInstance();

        fab = (FloatingActionButton) findViewById(R.id.fab);
        showAlertFromCreatingBoard("title","message");
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
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        if(title != null)
        {
            builder.setTitle(title);
        }

        if(message != null)
        {
            builder.setMessage(message);
        }

        View viewInflated = LayoutInflater.from(this).inflate(R.layout.dialog_create_board,null);
        builder.setView(viewInflated);

        final EditText input = (EditText) findViewById(R.id.editBoard);

        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String boardName = input.getText().toString().trim();

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
}
