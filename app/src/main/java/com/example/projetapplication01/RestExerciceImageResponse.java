package com.example.projetapplication01;

import java.util.List;

public class RestExerciceImageResponse {
    private Integer count;
    private String next;
    private String previous;
    private List<ExerciceImage> results;

    public Integer getCount() {
        return count;
    }

    public String getNext() {
        return next;
    }

    public String getPrevious() {
        return previous;
    }

    public List<ExerciceImage> getResults() {
        return results;
    }
}
