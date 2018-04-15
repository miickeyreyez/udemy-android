package com.udemy.fragmentapplication;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class DataFragment extends Fragment {

    private EditText text;
    private Button button;
    private DataListener callback;

    public DataFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        try{
            callback = (DataListener) context;
        } catch (Exception e){
            throw new ClassCastException(context.toString() + "should implement DataListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data,container,false);

        text = (EditText)view.findViewById(R.id.editTextDataFragment);
        button = (Button)view.findViewById(R.id.buttonDataFragment);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendText(String.valueOf(text.getText()));
            }
        });

        return view;
    }

    private void sendText(String text2send){
        callback.sendData(text2send);
    }

    public interface DataListener{
        void sendData(String text);
    }
}
