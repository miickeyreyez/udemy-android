package com.udemy.fragmentmail;

import android.app.ListFragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements com.udemy.fragmentmail.ListFragment.OnFragmentInteractionListener {

    private boolean isMultiPanel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Obtener la vista
        Bundle bundle = getIntent().getExtras();

        if(bundle.getBoolean("isMobile")){
            setContentView(R.layout.activity_main);
        } else {
            setContentView(R.layout.activity_main_tablet);
        }
        setMultiPanel();
    }

    @Override
    public void onListClick(Mail mail){
        if(isMultiPanel){
            DetailsFragment detailsFragment = (DetailsFragment)getFragmentManager().findFragmentById(R.id.fragmentDetailsMail);
            detailsFragment.renderMail(mail);
        } else{
            Intent intent = new Intent(this,DetailsActivity.class);
            intent.putExtra("subject",mail.getSubject());
            intent.putExtra("message",mail.getMessage());
            intent.putExtra("senderName",mail.getSenderName());
            startActivity(intent);
        }
    }

    private void setMultiPanel(){
        isMultiPanel = (getFragmentManager().findFragmentById(R.id.fragmentDetailsMail) != null);
    }
}
