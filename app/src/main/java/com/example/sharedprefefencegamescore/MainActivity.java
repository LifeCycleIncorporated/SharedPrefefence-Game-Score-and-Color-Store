package com.example.sharedprefefencegamescore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{

    private LinearLayout linearLayout;
    private TextView scoreText;
    private Button plus, minus;

    private int score = 0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearLayout = findViewById(R.id.mainLayoutId);

        scoreText = findViewById(R.id.scoreId);

        plus = findViewById(R.id.plusId);

        minus = findViewById(R.id.minusId);

        if (loadColor()!=getResources().getColor(com.google.android.material.R.color.design_default_color_primary)){
            linearLayout.setBackgroundColor(loadColor()
            );
        }

        if (loadScore()!=0){
            scoreText.setText("Score : "+loadScore());
        }

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                score = score + 10;
                scoreText.setText("Score : "+score);
                saveScore(score);
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                score = score - 10;
                scoreText.setText("Score : "+score);
                saveScore(score);
            }
        });



    }


    private void saveScore(int score) {
        SharedPreferences sharedPreferences = getSharedPreferences("gameScore", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("lastScore", score);
        editor.commit();
    }

    private int loadScore() {
        SharedPreferences sharedPreferences = getSharedPreferences("gameScore", Context.MODE_PRIVATE);
        int score = sharedPreferences.getInt("lastScore",0);
        return score;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_layout,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.redId){
            linearLayout.setBackgroundColor(getResources().getColor(R.color.red));
            storeColor(getResources().getColor(R.color.red));
        }
        if (item.getItemId()==R.id.blueId){
            linearLayout.setBackgroundColor(getResources().getColor(R.color.blue));
            storeColor(getResources().getColor(R.color.blue));
        }
        if (item.getItemId()==R.id.yellowId){
            linearLayout.setBackgroundColor(getResources().getColor(R.color.yellow));
            storeColor(getResources().getColor(R.color.yellow));
        }

        return super.onOptionsItemSelected(item);
    }

    private void storeColor(int color) {
        SharedPreferences sharedPreferences = getSharedPreferences("storeColor", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("myColor",color);
        editor.commit();
    }

    private int loadColor(){
        SharedPreferences sharedPreferences = getSharedPreferences("storeColor", Context.MODE_PRIVATE);
        int selectedColor = sharedPreferences.getInt("myColor",getResources().getColor(com.google.android.material.R.color.design_default_color_primary));
        return selectedColor;
    }
}