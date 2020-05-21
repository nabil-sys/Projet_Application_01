package com.example.projetapplication01.presentation.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projetapplication01.R;
import com.example.projetapplication01.Singletons;
import com.example.projetapplication01.presentation.model.ExerciceImage;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DetailActivity extends AppCompatActivity {
    private TextView txtDetail;

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        txtDetail = findViewById(R.id.detail_txt);
        Intent intent = getIntent();
        String exerciceImageJson = intent.getStringExtra("exerciceImageKey");
        ExerciceImage exerciceImage = Singletons.getGson().fromJson(exerciceImageJson, ExerciceImage.class);
        showDetail(exerciceImage);

        imageView = (ImageView) findViewById(R.id.imageView);
        loadImageFromUrl(exerciceImage);
    }

    private void loadImageFromUrl(ExerciceImage exerciceImage) {
        Picasso.with(this).load(exerciceImage.getImage()).placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(imageView, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {

                    }
                });
    }

   private void showDetail(ExerciceImage exerciceImage) {
        txtDetail.setText(exerciceImage.getImage());
    }
}


