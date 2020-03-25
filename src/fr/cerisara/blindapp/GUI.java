package fr.cerisara.blindapp;

import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.app.Activity;
import android.widget.TextView;
import android.widget.ListView;
import android.view.View;
import android.view.KeyEvent;
import android.view.View.OnKeyListener;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ArrayAdapter;

import java.util.List;
import java.util.Arrays;

public class GUI {

    public static interface ItemListener {
        public void itemClicked(int num);
    }
    public static interface StringListener {
        public void enterPressed(String s);
    }

    private static Activity main;

    public GUI(Activity m) {
        main = m;
    }

    // fct() is called whenever the user press Enter: the edittext is then cleared
    public void setEdit(final StringListener fct) {
        main.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // Get ListView object from xml
                final EditText v = (EditText)main.findViewById(R.id.edit);
                v.setLines(8);
                v.setVerticalScrollBarEnabled(true);
                v.requestFocus();

                v.setOnKeyListener(new OnKeyListener() {
                    public boolean onKey(View vv, int keyCode, KeyEvent event) {
                        // If the event is a key-down event on the "enter" button
                        if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                            if (fct!=null) {
                                fct.enterPressed(v.getText().toString());
                                v.setText("");
                            }
                            return true;
                        }
                        return false;
                    }
                });
            }
        });
    }
    public void setList(final String[] items, final ItemListener fct) {
        setList(Arrays.asList(items), fct);
    }
    public void setList(final List<String> items, final ItemListener fct) {
        main.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // Get ListView object from xml
                ListView listView = (ListView)main.findViewById(R.id.list);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(main, android.R.layout.simple_list_item_1, items);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        fct.itemClicked(position);
                    }
                });
            }
        });
    }

    public void setText(final String s) {
        main.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TextView w = (TextView)main.findViewById(R.id.text0);
                w.setText(s);
            }
        });
    }
}

