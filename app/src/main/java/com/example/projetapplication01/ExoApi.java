package com.example.projetapplication01;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ExoApi {
    @GET("/api/v2/exerciseimage/")
    Call<RestExerciceImageResponse> getExerciceImageResponse();
}
