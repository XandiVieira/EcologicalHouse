package com.example.xandi.ecologicalhouse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;

public class CreateNewItem extends AppCompatActivity {

    private ImageView uploadImage;
    private Button criar;
    private Uri filePath;
    private final int PICK_IMAGE_REQUEST = 71;

    private Image image;

    private EditText nameET;
    private EditText descriptionET;
    private EditText infoET;
    private EditText recipeET;
    private String partOfHouse;
    private DatabaseReference mDatabaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_new_item);

        uploadImage = findViewById(R.id.upload_img);
        nameET = findViewById(R.id.nameET);
        descriptionET = findViewById(R.id.descrET);
        infoET = findViewById(R.id.infoET);
        recipeET = findViewById(R.id.recipeET);
        mDatabaseRef = FirebaseDatabase.getInstance().getReference();

        Bundle bundleTitle = getIntent().getExtras();
        partOfHouse = bundleTitle.getString("partOfHouse");

        uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseImage();
            }
        });

        criar = findViewById(R.id.btCreate);

        criar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validar()) {
                    Item item = new Item(image, nameET.getText().toString(), descriptionET.getText().toString(), infoET.getText().toString(), recipeET.getText().toString());
                    mDatabaseRef.child(partOfHouse).setValue(item);
                }
            }
        });
    }

    private boolean validar() {
        if(nameET.getText() != null){
            Toast.makeText(this, "Preencha o campo nome!", Toast.LENGTH_SHORT).show();
        }else if(descriptionET.getText() != null){
            Toast.makeText(this, "Preencha o campo descrição!", Toast.LENGTH_SHORT).show();
        }else if(infoET.getText() != null){
            Toast.makeText(this, "Preencha o campo informações!", Toast.LENGTH_SHORT).show();
        }else{
            return true;
        }
        return false;
    }

    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null )
        {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                uploadImage.setImageBitmap(bitmap);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
