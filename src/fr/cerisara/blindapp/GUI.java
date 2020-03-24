package fr.cerisara.blindapp;

import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.app.Activity;
import android.widget.TextView;
import android.widget.ListView;
import android.view.View;
import android.widget.ArrayAdapter;

import java.util.List;
import java.util.Arrays;

public class GUI {

    public static interface ItemListener {
        public void itemClicked(int num);
    }

    private static Activity main;

    public GUI(Activity m) {
        main = m;
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

