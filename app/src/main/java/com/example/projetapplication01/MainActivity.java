package com.example.projetapplication01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private static final String BASE_URL = "https://wger.de";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        makeApiCall();
    }

    private void showList(List<ExerciceImage> exerciceImageList) {

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // define an adapter
        mAdapter = new ListAdapter(exerciceImageList);
        recyclerView.setAdapter(mAdapter);
    }

    private void makeApiCall(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        ExoApi exoApi = retrofit.create(ExoApi.class);

        Call<RestExerciceImageResponse> call = exoApi.getExerciceImageResponse();
        call.enqueue(new Callback<RestExerciceImageResponse>() {
            @Override
            public void onResponse(Call<RestExerciceImageResponse> call, Response<RestExerciceImageResponse> response) {
                if(response.isSuccessful() && response.body() != null){
                    List<ExerciceImage> exerciceImageList = response.body().getResults();
                    showList(exerciceImageList);
                }
                else {
                    showError();
                }
            }

            @Override
            public void onFailure(Call<RestExerciceImageResponse> call, Throwable t) {
                showError();

            }
        });
    }


    private void showError() {
        Toast.makeText(getApplicationContext(), "API Error", Toast.LENGTH_SHORT).show();
    }
}
