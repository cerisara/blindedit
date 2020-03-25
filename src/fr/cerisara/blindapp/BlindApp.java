package fr.cerisara.blindapp;

import android.widget.Toast;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.content.Context;
import android.app.Activity;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public class BlindApp extends Activity {

    private String fromshare = null;
    private TTS tts;
    public static BlindApp main = null;
    private GUI gui = new GUI(this);
    private final String[] menuitems = {
        ""
    };
    private ArrayList<String> txt = new ArrayList<String>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        main = this;
        setContentView(R.layout.main);

        Intent in = this.getIntent();
        fromshare = null;
        if (in.getAction().equals(Intent.ACTION_SEND)) getNewStringFromShareMenu(in);

        tts = new TTS();
        showedit();
        // menu();
    }

    @Override
    public void onNewIntent(Intent in) {
        fromshare = null;
        if (in.getAction().equals(Intent.ACTION_SEND)) getNewStringFromShareMenu(in);
    }

    private void getNewStringFromShareMenu(Intent in) {
        fromshare = null;
        Object o = in.getExtras().get("android.intent.extra.TEXT");
        if (o!=null) {
            String s=(String)o;
            fromshare = ""+s;
            main.msg("Share OK "+s.length());
        } else {
            main.msg("WARNING: nothing to share");
        }
    }

    private void showedit() {
        gui.setEdit(new GUI.StringListener() {
            public void enterPressed(String s) {
                gotNewLine(s);
            }
        });
    }

    private void msg(final String s) {
        main.runOnUiThread(new Runnable() {
            public void run() {
                Toast.makeText(main, s, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void gotNewLine(String s) {
        if (s.startsWith("aa")) {
            // this is a command
            if (s.startsWith("aapush")) {
                tts.speak("pushing to git");
                // TODO
            } else if (s.startsWith("aadel")) {
                tts.speak("del last string");
                if (txt.size()>0) {
                    txt.remove(txt.size()-1);
                }
            }
        } else {
            // it's a text
            tts.speak(s);
            txt.add(s);
            saveAppend(s);
        }
    }

    private void saveAppend(String s) {
        try {
            File d = getExternalCacheDir();
            File f = new File(d+"/texts.txt");
            PrintWriter ff = new PrintWriter(new FileWriter(f, true));
            ff.println(s);
            ff.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void tts() {
        // TODO: shutdown
    }

    public void menu() {
        gui.setText("Menu");
        gui.setList(menuitems,new GUI.ItemListener() {
            public void itemClicked(int num) {
                switch(num) {
                    default:
                        break;
                }
            }
        });
    }

    public void but1(View v) {
    }
    public void but2(View v) {
    }
    public void but3(View v) {
    }

}

