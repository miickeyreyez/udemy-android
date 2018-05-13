package com.udemy.gson;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

public class MainActivity extends AppCompatActivity {


    private TextView jsonO;
    private TextView jsonP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        jsonO = findViewById(R.id.jsonO);
        jsonP = findViewById(R.id.jsonP);

        //String json = "{ \"id\":1, \"name\":\"New York\" }";
        String json = "{ " +
                "id: 0," +
                "city: [{" +
                    "id: 1," +
                    "name: 'London'" +
                    "}," +
                    "{" +
                    "id: 2," +
                    "name: 'Barcelona'" +
                    "}," +
                    "{" +
                    "id: 3," +
                    "name: 'CDMX'" +
                    "}]" +
                " }";

        jsonO.setText(json);

        City cityObject = null;

        /**
        try {

            JSONObject myJson = new JSONObject(json);
            String id = myJson.getString("id");
            String city = myJson.getString("name");

            cityObject = new City(id,city);
            jsonP.setText(cityObject.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        City cityGson = gson.fromJson(json,City.class);
        jsonP.setText(cityGson.toString());

        Gson gsonB = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        City cityGsonB = gsonB.fromJson(json,City.class);
        jsonP.setText(cityGsonB.toString());
         */

        Gson gson = new GsonBuilder().create();
        Town town = gson.fromJson(json,Town.class);
        jsonP.setText(gson.toString());

    }
}
