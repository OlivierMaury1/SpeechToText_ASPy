package com.example.speechtotextatifjava;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_INPUT = 100;


    TextView mTextTv;
    ImageButton mVoiceBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextTv = findViewById(R.id.textTv);
        mVoiceBtn = findViewById(R.id.voiceBtn);

        // button click to show speech to text dialog
        mVoiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speak();
            }
        });


    }

    private void speak() {

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hi say something");



        try {
            startActivityForResult(intent, REQUEST_CODE_INPUT);
        }
        catch (Exception e){
            Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();

        }

    }

    //receive voice input

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case REQUEST_CODE_INPUT:{
                if (resultCode == RESULT_OK && null!=data) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    ImageView Image = (ImageView) findViewById(R.id.ImageView1);
                    if (result.contains("plage")) {
                        Image.setImageResource(R.drawable.plage);
                        //mTextTv.setText(result.get(0));
                    } else if (result.contains("montagne")) {
                        Image.setImageResource(R.drawable.montagne);
                    } else if (result.contains("arbre") || result.contains("fleur")) {
                        Image.setImageResource(R.drawable.arbre_fleur);
                    } else if (result.contains("chat")) {
                        Image.setImageResource(R.drawable.chat);
                    } else if (result.contains("chien")) {
                        Image.setImageResource(R.drawable.chien);
                    } else if (result.contains("étoile")) {
                        Image.setImageResource(R.drawable.montagne_etoiles);
                    } else if (result.contains("chemin")) {
                    Image.setImageResource(R.drawable.chemin);
                    } else if (result.contains("cascade")) {
                        Image.setImageResource(R.drawable.cascade);
                    } else if (result.contains("forêt")) {
                        Image.setImageResource(R.drawable.foret);
                    } else if (result.contains("champs") || result.contains("chant") || result.contains("pâquerette")) {
                        Image.setImageResource(R.drawable.champs_paquerette);
                    }  else if (result.contains("stop")) {
                        Image.setImageDrawable(null);
                    }
                }
                break;
            }
        }
    }
}