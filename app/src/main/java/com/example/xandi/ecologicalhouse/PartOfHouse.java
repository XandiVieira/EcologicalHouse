package com.example.xandi.ecologicalhouse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PartOfHouse extends AppCompatActivity {

    private ArrayList<Item> itemArrayList;
    private ItemAdapter itemAdapter;
    private ListView itemsLV;
    private String partOfHouse;
    private DatabaseReference mDatabaseRef;
    private FirebaseDatabase mFirebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startFirebase();

        itemsLV = findViewById(R.id.cardsLV);

        Bundle bundleTitle = getIntent().getExtras();
        partOfHouse = bundleTitle.getString("partOfHouse");
        bundleTitle.getString("partOfHouse");

        setTitle("hdsjkhskjhsjkchkjds");

        mDatabaseRef = FirebaseDatabase.getInstance().getReference();
        itemArrayList = new ArrayList<Item>();

        if(partOfHouse!=null)
        mDatabaseRef.child(partOfHouse).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snap : dataSnapshot.getChildren()) {
                    itemArrayList.add(snap.getValue(Item.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        if(itemArrayList!=null)
        itemAdapter = new ItemAdapter(this, itemArrayList);
        if(itemsLV!=null)
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

    private void startFirebase() {
        FirebaseApp.initializeApp(getApplicationContext());
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseRef = mFirebaseDatabase.getReference();
    }
}
