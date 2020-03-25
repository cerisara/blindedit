package fr.cerisara.blindapp;

import android.speech.tts.TextToSpeech.OnInitListener;
import android.speech.tts.TextToSpeech;

public class TTS {
    private TextToSpeech tts = null;

    public TTS() {
        tts = new TextToSpeech(BlindApp.main, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                speak("speech OK");
            }
        });
    }

    public void endTTS() {
        tts.shutdown();
    }

    public void speak(String s) {
        tts.speak(s, TextToSpeech.QUEUE_ADD, null);
    }
}

