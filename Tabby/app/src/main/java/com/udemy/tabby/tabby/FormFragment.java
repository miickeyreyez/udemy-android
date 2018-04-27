package com.udemy.tabby.tabby;


import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.udemy.tabby.tabby.Util.fillCountries;


/**
 * A simple {@link Fragment} subclass.
 */
public class FormFragment extends Fragment {

    Spinner spinner;

    public FormFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_form, container, false);

        //Lista de países
        HashMap<String,String> maps = Util.fillCountries();

        spinner = (Spinner)view.findViewById(R.id.spinner);

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

        return view;
    }

}
