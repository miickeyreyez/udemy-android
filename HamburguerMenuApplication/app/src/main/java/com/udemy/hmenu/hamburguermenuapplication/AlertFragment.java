package com.udemy.hmenu.hamburguermenuapplication;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class AlertFragment extends Fragment {
    //Implements View.OnClickListener

    private FloatingActionButton fab;
    private TextView textView;
    private Switch switchAlert;
    private Boolean flag;

    public AlertFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_alert, container, false);

        fab = view.findViewById(R.id.fabAlert);
        textView = view.findViewById(R.id.textViewDialogAlerts);
        flag = false;

        //fab.setOnClickListener(this);
        //El override se hace:
        //@Override
        //public void onClick(View view){}
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                // 1. Instantiate an AlertDialog.Builder with its constructor
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                LayoutInflater layoutInflater = getLayoutInflater();
                final View dialog = layoutInflater.inflate(R.layout.alert_dialog_alerts,null);

                builder.setView(dialog);
                final AlertDialog alertDialog = builder.create();

                TextView textViewDialog = (TextView)dialog.findViewById(R.id.textViewCloseDialog);
                switchAlert = (Switch)dialog.findViewById(R.id.switchAlerts);

                switchAlert.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if(switchAlert.isChecked()) {
                            textView.setText("Alerts enabled");
                            switchAlert.setChecked(true);
                        } else {
                            textView.setText("Alerts disabled");
                            switchAlert.setChecked(false);
                        }
                    }
                });

                textViewDialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });

                alertDialog.show();

            }
        });

        return view;
    }

}
