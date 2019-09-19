package com.example.UrTextView;

import android.app.Activity;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.widget.TextView;

import java.util.Locale;

// This is the class of Text to Speech.
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

    // Read texts.
    public void playAudio() {
        final TextView resultView = activity.findViewById(R.id.resultView);
        String data = resultView.getText().toString();
        // Every time pressing the button, the speaker starts over.
        textToSpeech.speak(data, TextToSpeech.QUEUE_FLUSH, null);
    }

    // Stop the speaker.
    public void playStop() {
        if (textToSpeech != null) {
            textToSpeech.stop();
        }
    }
}
