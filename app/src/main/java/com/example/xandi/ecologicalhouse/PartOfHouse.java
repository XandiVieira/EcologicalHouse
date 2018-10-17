package com.example.xandi.ecologicalhouse;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

public class PartOfHouse extends AppCompatActivity {

    private ArrayList<Item> itemArrayList;
    private ItemAdapter itemAdapter;
    private ListView itemsLV;
    private String partOfHouse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        itemsLV = findViewById(R.id.cardsLV);

        Bundle bundleTitle = getIntent().getExtras();
        partOfHouse = bundleTitle.getString("partOfHouse");
        bundleTitle.getString("partOfHouse");

        setTitle(partOfHouse);

        itemArrayList = new ArrayList<Item>();
        itemAdapter = new ItemAdapter(this, itemArrayList);
        itemsLV.setAdapter(itemAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                Intent intent = new Intent(this, CreateNewItem.class);
                intent.putExtra("partOfHouse", partOfHouse);
                startActivity(intent);

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
