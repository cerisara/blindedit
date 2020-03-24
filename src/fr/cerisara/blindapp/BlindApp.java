package fr.cerisara.blindapp;

import android.widget.Toast;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.content.Context;
import android.app.Activity;

public class BlindApp extends Activity {

    private String fromshare = null;
    public static BlindApp main = null;
    private GUI gui = new GUI(this);
    private final String[] menuitems = {
        "tts"
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        main = this;
        setContentView(R.layout.main);

        Intent in = this.getIntent();
        fromshare = null;
        if (in.getAction().equals(Intent.ACTION_SEND)) getNewStringFromShareMenu(in);

        menu();
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

    private void msg(final String s) {
        main.runOnUiThread(new Runnable() {
            public void run() {
                Toast.makeText(main, s, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void tts() {
        TTS tts = new TTS();
        // TODO: shutdown
    }

    public void menu() {
        gui.setText("Menu");
        gui.setList(menuitems,new GUI.ItemListener() {
            public void itemClicked(int num) {
                switch(num) {
                    case 0:
                        tts();
                        break;
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

