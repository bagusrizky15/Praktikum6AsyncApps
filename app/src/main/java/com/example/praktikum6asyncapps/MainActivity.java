package com.example.praktikum6asyncapps;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button btnGo;
    private ProgressBar progressBar;
    private TextView textInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGo = (Button) findViewById(R.id.button_go);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        textInfo = (TextView) findViewById(R.id.textView);

        btnGo.setOnClickListener(v -> {
            progressBar.setProgress(0);
            new MyAsync().execute(10);

        });
    }

    private class MyAsync extends AsyncTask<Integer, Integer, String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
            textInfo.setText(getResources().getString(R.string.text_starting));
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressBar.setProgress(values[0]);
            textInfo.setText(getResources().getString(R.string.text_running)+" "+values[0]+"%");

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }

        @Override
        protected String doInBackground(Integer... integers) {
            try{
                int count = integers[0];
                for(int i=0; i<count; i++){
                    Thread.sleep(1000);
                    int value = (int)(((i+1)/(float)count)*100);
                    publishProgress(value);

                    Log.d("PROGRESS", value+"");
                }
            }catch (Exception e){}

            return null;
        }
    }



}