package com.example.xandi.ecologicalhouse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PartOfHouse extends AppCompatActivity {

    private ArrayList<Item> itemArrayList = new ArrayList<Item>();
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;

    private ListView itemsLV;
    private ItemAdapter itemAdapter;
    private String partOfHouse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.part_of_house);

        itemsLV = findViewById(R.id.cardsLV);

        startFirebase();

        Bundle bundleTitle = getIntent().getExtras();
        partOfHouse = bundleTitle.getString("partOfHouse");
        bundleTitle.getString("partOfHouse");

        setTitle(partOfHouse);

        Toast.makeText(this, partOfHouse, Toast.LENGTH_SHORT).show();

        if(partOfHouse!=null)
        mDatabaseReference.child(partOfHouse).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snap : dataSnapshot.getChildren()){
                    itemArrayList.add(snap.getValue(Item.class));
                }
                itemAdapter = new ItemAdapter(getApplicationContext(), itemArrayList);
                itemsLV.setAdapter(itemAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
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

    private void startFirebase() {
        FirebaseApp.initializeApp(this);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference();
    }
}
