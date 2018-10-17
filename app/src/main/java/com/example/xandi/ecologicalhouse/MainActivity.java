package com.example.xandi.ecologicalhouse;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<ImageButton> partsOfHouse = new ArrayList<ImageButton>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*partsOfHouse.add(findViewById(R.id.btRoom1));
        partsOfHouse.add(findViewById(R.id.btRoom2));
        partsOfHouse.add(findViewById(R.id.btRoom3));
        partsOfHouse.add(findViewById(R.id.btRoom4));
        partsOfHouse.add(findViewById(R.id.btRoom5));
        partsOfHouse.add(findViewById(R.id.btRoom6));
        partsOfHouse.add(findViewById(R.id.btRoom7));
        partsOfHouse.add(findViewById(R.id.btRoom8));
        partsOfHouse.add(findViewById(R.id.btRoom9));
        partsOfHouse.add(findViewById(R.id.btRoom10));*/

        for(int i=0; i<partsOfHouse.size(); i++){
            final int finalI = i;
            partsOfHouse.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, PartOfHouse.class);
                    intent.putExtra("partOfHouse", partsOfHouse.get(finalI).getId());
                    startActivity(intent);
                }
            });
        }
    }
}
