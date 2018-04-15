package com.udemy.fragmentmail;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsFragment extends Fragment {

    private TextView textViewSubject;
    private TextView textViewSender;
    private TextView textViewMessage;
    private LinearLayout wrapper;

    public DetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_details, container, false);

        textViewSubject = (TextView)view.findViewById(R.id.textViewFragmentSubject);
        textViewMessage = (TextView)view.findViewById(R.id.textViewFragmentMessage);
        textViewSender = (TextView)view.findViewById(R.id.textViewFragmentSenderName);
        wrapper = (LinearLayout)view.findViewById(R.id.fragmentDetailsMailWrapper);

        return view;
    }

    public void renderMail(Mail mail){
        wrapper.setVisibility(View.VISIBLE);
        textViewSubject.setText(mail.getSubject());
        textViewSender.setText(mail.getSenderName());
        textViewMessage.setText(mail.getMessage());
    }

}
