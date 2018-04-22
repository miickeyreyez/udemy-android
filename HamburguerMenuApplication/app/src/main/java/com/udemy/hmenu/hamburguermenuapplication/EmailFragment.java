package com.udemy.hmenu.hamburguermenuapplication;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */

public class EmailFragment extends Fragment implements View.OnClickListener, DialogInterface.OnClickListener {

    FloatingActionButton fab;
    TextView textViewEmail;
    EditText editText;

    public EmailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_email, container, false);;

        fab = view.findViewById(R.id.fabEmail);
        textViewEmail = view.findViewById(R.id.email_label);

        /*
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 1. Instantiate an AlertDialog.Builder with its constructor
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                LayoutInflater layoutInflater = getLayoutInflater();
                final View dialog = layoutInflater.inflate(R.layout.alert_dialog,null);

                builder.setView(dialog);

                Button btnPossitive = (Button)dialog.findViewById(R.id.dialog_positive_btn);
                Button btnNegative = (Button)dialog.findViewById(R.id.dialog_negative_btn);
                final EditText editTextMail = (EditText)dialog.findViewById(R.id.dialog_input_text);

                final AlertDialog alertDialog = builder.create();

                btnPossitive.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        textViewEmail.setText("Your email: " + editTextMail.getText().toString());
                        alertDialog.dismiss();
                    }
                });

                btnNegative.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });

                alertDialog.show();

            }
        });
        */
        fab.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Email");
        builder.setMessage("Type your email");
        editText = new EditText(getContext());
        editText.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        builder.setView(editText);
        builder.setPositiveButton("OK",this);
        builder.setNegativeButton("Cancel",this);
        builder.show();
        //AlertDialog dialog = builder.create();
        //dialog.show();
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        if(i == DialogInterface.BUTTON_POSITIVE){
            if(!editText.getText().toString().trim().isEmpty()){
                textViewEmail.setText(editText.getText().toString());
            } else if(i == DialogInterface.BUTTON_NEGATIVE){
                dialogInterface.cancel();
            }
        }
    }
}
