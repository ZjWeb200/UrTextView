package com.example.UrTextView.utilities;

import android.app.Activity;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.widget.TextView;

import com.example.UrTextView.R;

import java.util.Locale;

public class MainActivitySpeechController {
    private final Activity activity;
    private TextToSpeech textToSpeech;

    public MainActivitySpeechController (Activity activity) {
        this.activity = activity;
        textToSpeech = new TextToSpeech(this.activity.getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int ttsLang = textToSpeech.setLanguage(Locale.US);

                    if (ttsLang == TextToSpeech.LANG_MISSING_DATA
                            || ttsLang == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "The Language is not supported!");
                    } else {
                        Log.i("TTS", "Language Supported.");
                    }
                    Log.i("TTS", "Initialization success.");
                } else {
                    Log.i("TTS", "TTS Initialization failed!");
                }
            }
        });
    }

    public void playAudio() {
        final TextView resultView = activity.findViewById(R.id.resultView);
        String data = resultView.getText().toString();
        textToSpeech.speak(data, TextToSpeech.QUEUE_FLUSH, null);
    }

    public void playStop() {
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
    }
}
