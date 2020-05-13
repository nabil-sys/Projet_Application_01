package com.example.projetapplication01.presentation.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.projetapplication01.R;
import com.example.projetapplication01.Singletons;
import com.example.projetapplication01.presentation.controller.MainController;
import com.example.projetapplication01.presentation.model.ExerciceImage;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private MainController controller;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        controller = new MainController(
                this,
                Singletons.getGson(),
                Singletons.getSharedPreferences(getApplicationContext())
        );
        controller.onStart();
    }

    public void showList(List<ExerciceImage> exerciceImageList) {

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // define an adapter
        mAdapter = new ListAdapter(exerciceImageList, new ListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ExerciceImage item) {
                controller.onItemClick(item);
            }
        });
        recyclerView.setAdapter(mAdapter);
    }

    public void showError() {
        Toast.makeText(getApplicationContext(), "API Error", Toast.LENGTH_SHORT).show();
    }

    public void navigateToDetails(ExerciceImage exerciceImage) {
        Intent myIntent = new Intent(MainActivity.this, DetailActivity.class);
 //       myIntent.putExtra("key", value); //Optional parameters
        MainActivity.this.startActivity(myIntent);

    }
}
