package com.example.projetapplication01.presentation.controller;

import android.content.SharedPreferences;

import com.example.projetapplication01.Constants;
import com.example.projetapplication01.Singletons;
import com.example.projetapplication01.presentation.model.ExerciceImage;
import com.example.projetapplication01.presentation.model.RestExerciceImageResponse;
import com.example.projetapplication01.presentation.view.MainActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainController {

    private SharedPreferences sharedPreferences;
    private Gson gson;
    private MainActivity view;

    public MainController(MainActivity mainActivity, Gson gson, SharedPreferences sharedPreferences) {
        this.view = mainActivity;
        this.gson = gson;
        this.sharedPreferences = sharedPreferences;
    }

    public void onStart(){
        List<ExerciceImage> exerciceImageList = getDataFromCache();

        if(exerciceImageList != null){
            view.showList(exerciceImageList);
        }else{
            makeApiCall();
        }
    }


    private void makeApiCall(){
        Call<RestExerciceImageResponse> call = Singletons.getExoApi().getExerciceImageResponse();
        call.enqueue(new Callback<RestExerciceImageResponse>() {
            @Override
            public void onResponse(Call<RestExerciceImageResponse> call, Response<RestExerciceImageResponse> response) {
                if(response.isSuccessful() && response.body() != null){
                    List<ExerciceImage> exerciceImageList = response.body().getResults();
                    saveList(exerciceImageList);
                    view.showList(exerciceImageList);
                }
                else {
                    view.showError();
                }
            }

            @Override
            public void onFailure(Call<RestExerciceImageResponse> call, Throwable t) {
                view.showError();

            }
        });
    }

    private void saveList(List<ExerciceImage> exerciceImageList) {
        String jsonString = gson.toJson(exerciceImageList);

        sharedPreferences
                .edit()
                .putString(Constants.KEY_EXERCICEIMAGE_LIST, jsonString)
                .apply();
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

    public void onItemClick(ExerciceImage exerciceImage){

    }

    public void onButtonAClick(){

    }

}
