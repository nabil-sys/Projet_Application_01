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
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
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
    private SharedPreferences sharedPreferences;
    private Gson gson;

    private static final String BASE_URL = "https://wger.de";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("application_mobile", Context.MODE_PRIVATE);
        gson = new GsonBuilder()
                .setLenient()
                .create();

        List<ExerciceImage> exerciceImageList = getDataFromCache();

        if(exerciceImageList != null){
            showList(exerciceImageList);
        }else{
            makeApiCall();
        }
    }


    private List<ExerciceImage> getDataFromCache() {

        String jsonExerciceImage =sharedPreferences.getString(Constants.KEY_EXERCICEIMAGE_LIST, null);

        if(jsonExerciceImage == null){
            return null;
        }else {
            Type listType = new TypeToken<List<ExerciceImage>>(){}.getType();
            return gson.fromJson(jsonExerciceImage, listType);
        }
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
                    saveList(exerciceImageList);
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

    private void saveList(List<ExerciceImage> exerciceImageList) {
        String jsonString = gson.toJson(exerciceImageList);

        sharedPreferences
                .edit()
                .putString(Constants.KEY_EXERCICEIMAGE_LIST, jsonString)
                .apply();

        Toast.makeText(getApplicationContext(), "List saved", Toast.LENGTH_SHORT).show();

    }

    private void showError() {
        Toast.makeText(getApplicationContext(), "API Error", Toast.LENGTH_SHORT).show();
    }
}
