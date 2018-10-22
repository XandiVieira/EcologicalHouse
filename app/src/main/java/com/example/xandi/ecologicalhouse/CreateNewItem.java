package com.example.xandi.ecologicalhouse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.UUID;

public class CreateNewItem extends AppCompatActivity {

    private ImageView uploadImage;
    private Button criar;

    private Uri filePath;
    private final int PICK_IMAGE_REQUEST = 71;

    private EditText nameET;
    private EditText descriptionET;
    private EditText infoET;
    private EditText recipeET;
    private String partOfHouse;
    private DatabaseReference mDatabaseRef;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_new_item);

        uploadImage = findViewById(R.id.upload_img);
        nameET = findViewById(R.id.nameET);
        descriptionET = findViewById(R.id.descrET);
        infoET = findViewById(R.id.infoET);
        recipeET = findViewById(R.id.recipeET);
        criar = findViewById(R.id.btCreate);

        mDatabaseRef = FirebaseDatabase.getInstance().getReference();

        Bundle bundleTitle = getIntent().getExtras();
        partOfHouse = bundleTitle.getString("partOfHouse");

        //Firebase
        FirebaseStorage storage;

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseImage();
            }
        });

        criar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validar()) {
                    uploadImage();
                }
            }
        });
    }

    private boolean validar() {
        if(nameET.getText() == null){
            Toast.makeText(this, "Preencha o campo nome!", Toast.LENGTH_SHORT).show();
        }else if(descriptionET.getText() == null){
            Toast.makeText(this, "Preencha o campo descrição!", Toast.LENGTH_SHORT).show();
        }else if(infoET.getText() == null){
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

    private void uploadImage() {

        if(filePath != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            final String uuid = UUID.randomUUID().toString();
            StorageReference ref = storageReference.child("images/"+ uuid);
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(CreateNewItem.this, "Uploaded", Toast.LENGTH_SHORT).show();

                            Uri downloadUrl = taskSnapshot.getUploadSessionUri();

                            Item item = new Item(uuid, nameET.getText().toString(), descriptionET.getText().toString(), infoET.getText().toString(), recipeET.getText().toString());
                            mDatabaseRef.child(partOfHouse).push().setValue(item);
                            Intent intent = new Intent(getApplicationContext(), PartOfHouse.class);
                            intent.putExtra("partOfHouse", partOfHouse);
                            startActivity(intent);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(CreateNewItem.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploaded "+(int)progress+"%");
                        }
                    });
        }
    }
}
