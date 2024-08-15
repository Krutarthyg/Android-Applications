package com.example.texttospeech;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    EditText pitchrate, setspeechrate, text;
    TextToSpeech t;
    Button waterButton, foodButton, helpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pitchrate = findViewById(R.id.pitch);
        setspeechrate = findViewById(R.id.speechrate);
        text = findViewById(R.id.texttospeak);

        // New buttons for specific phrases
        waterButton = findViewById(R.id.waterButton);
        foodButton = findViewById(R.id.foodButton);
        helpButton = findViewById(R.id.helpButton);

        t = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    t.setLanguage(Locale.ENGLISH);
                } else {
                    Toast.makeText(MainActivity.this, "TextToSpeech Initialization Failed",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        // Set onClickListeners for the buttons
        waterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakPhrase("I want water");
            }
        });

        foodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakPhrase("I want food");
            }
        });

        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakPhrase("I need help");
            }
        });
    }

    public void speak(View v) {
        float pr, sr;
        String txt;

        pr = pitchrate.getText().toString().equals("") ? 1.0f : Float.parseFloat(pitchrate.getText().toString());
        sr = setspeechrate.getText().toString().equals("") ? 1.0f : Float.parseFloat(setspeechrate.getText().toString());

        t.setPitch(pr);
        t.setSpeechRate(sr);
        t.speak(text.getText().toString(), TextToSpeech.QUEUE_FLUSH, null, null);
    }

    private void speakPhrase(String phrase) {
        t.speak(phrase, TextToSpeech.QUEUE_FLUSH, null, null);
    }
}
