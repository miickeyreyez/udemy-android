package com.udemy.notifications;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EmptyActivity extends AppCompatActivity {

    @BindView(R.id.textViewTitleDetails)
    TextView title;
    @BindView(R.id.textViewMessageDetails)
    TextView message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);
        ButterKnife.bind(this);
        setIntentValues();
    }

    private void setIntentValues() {
        if(getIntent() != null) {
            title.setText(getIntent().getStringExtra("title"));
            message.setText(getIntent().getStringExtra("message"));
        }
    }
}
