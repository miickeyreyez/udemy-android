package com.udemy.tabby.tabbyMultilanguage;


import android.content.Context;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FormFragment extends Fragment {

    private EditText editTextName;
    private Button buttonCreate;
    private Spinner spinner;
    private List<Country> countries;
    private OnPersonCreated onPersonCreated;
    private TextView textViewCountry;

    public FormFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_form, container, false);

        editTextName = (EditText)view.findViewById(R.id.editTextName);
        spinner = (Spinner)view.findViewById(R.id.spinner);
        buttonCreate = (Button)view.findViewById(R.id.buttonCreate);
        textViewCountry = view.findViewById(R.id.textViewCountry);

        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNewPerson();
            }
        });

        countries = Util.getCountries(getContext());

        final ArrayAdapter<Country> adapter = new ArrayAdapter<Country>(getContext(),android.R.layout.simple_spinner_dropdown_item,countries);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                textViewCountry.setText(getResources().getString(R.string.country) + ": " + adapter.getItem(i).getName());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //
            }
        });

        /*

        //Lista de países
        HashMap<String,String> maps = Util.fillCountries();



        int size = maps.size();
        ArrayList<Country> countries = new ArrayList<>();

        for(Map.Entry<String,String> mapEntry: maps.entrySet()){
            countries.add(new Country(mapEntry.getKey(),mapEntry.getValue()));
        }

        System.out.println("PAISES: " + countries.toString());

        //fill data in spinner
        ArrayAdapter<Country> adapter = new ArrayAdapter<Country>(getContext(), android.R.layout.simple_spinner_dropdown_item, countries);
        spinner.setAdapter(adapter);
        spinner.setSelection(adapter.getPosition(countries.get(0)));//Optional to set the selected item.

        */

        return view;
    }

    private void createNewPerson() {
        if(!editTextName.getText().toString().isEmpty()) {
            Country country = (Country)spinner.getSelectedItem();
            Person person = new Person(editTextName.getText().toString(),country);
            onPersonCreated.createPerson(person);
        }
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        if(context instanceof OnPersonCreated){
            onPersonCreated = (OnPersonCreated)context;
        } else {
            throw new RuntimeException((context.toString()) + " must implement OnPersonCreated");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        onPersonCreated = null;
    }

}
