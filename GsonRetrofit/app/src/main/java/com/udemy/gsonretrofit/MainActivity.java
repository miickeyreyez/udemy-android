package com.udemy.gsonretrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public static final String KEY = "508422bfcb0e24cf1fc1bb8eaf5d79ce";

    private EditText editText;
    private TextView cityName;
    private TextView description;
    private TextView temperature;
    private ImageView weather;
    private Button button;
    private weatherService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editTextCity);
        cityName = findViewById(R.id.textViewCity);
        description = findViewById(R.id.textViewDescriptiion);
        temperature = findViewById(R.id.textViewTemperature);
        button = findViewById(R.id.buttonCalculate);
        weather = findViewById(R.id.imageViewWeather);

        service = API.getApi().create(weatherService.class);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String city = editText.getText().toString();
                if(!city.isEmpty() || city.trim().length() > 0) {
                    Call<City> cityCall = service.getCity(city,KEY);

                    cityCall.enqueue(new Callback<City>() {
                        @Override
                        public void onResponse(Call<City> call, Response<City> response) {
                            City city = response.body();
                            setResult(city);
                        }

                        @Override
                        public void onFailure(Call<City> call, Throwable t) {
                            Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();

                        }
                    });
                }
            }
        });
    }

    private void setResult(City city) {
        cityName.setText(city.getName() + " , " + city.getCountry());
        description.setText(city.getDescription());
        temperature.setText(city.getTemperature() + "°C");
        Picasso.get().load(API.BASE_ICONS + city.getIcon() + API.EXTENSION_ICONS).into(weather);
    }
}
