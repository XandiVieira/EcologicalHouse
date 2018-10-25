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

        partsOfHouse.add((ImageButton) findViewById(R.id.btRoom1));
        partsOfHouse.add((ImageButton) findViewById(R.id.btRoom2));
        partsOfHouse.add((ImageButton) findViewById(R.id.btRoom3));
        partsOfHouse.add((ImageButton) findViewById(R.id.btRoom4));
        partsOfHouse.add((ImageButton) findViewById(R.id.btRoom5));
        partsOfHouse.add((ImageButton) findViewById(R.id.btRoom6));
        partsOfHouse.add((ImageButton) findViewById(R.id.btRoom7));

        partsOfHouse.get(0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PartOfHouse.class);
                intent.putExtra("partOfHouse", "Sala");
                startActivity(intent);
            }
        });

        partsOfHouse.get(1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PartOfHouse.class);
                intent.putExtra("partOfHouse", "Cozinha");
                startActivity(intent);
            }
        });

        partsOfHouse.get(2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PartOfHouse.class);
                intent.putExtra("partOfHouse", "Garagem");
                startActivity(intent);
            }
        });

        partsOfHouse.get(3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PartOfHouse.class);
                intent.putExtra("partOfHouse", "Quarto");
                startActivity(intent);
            }
        });

        partsOfHouse.get(4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PartOfHouse.class);
                intent.putExtra("partOfHouse", "Banheiro");
                startActivity(intent);
            }
        });

        partsOfHouse.get(5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PartOfHouse.class);
                intent.putExtra("partOfHouse", "Jardim");
                startActivity(intent);
            }
        });

        partsOfHouse.get(6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PartOfHouse.class);
                intent.putExtra("partOfHouse", "Outros");
                startActivity(intent);
            }
        });
    }
}
