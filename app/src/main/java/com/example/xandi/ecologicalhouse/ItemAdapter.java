package com.example.xandi.ecologicalhouse;

import android.app.ActionBar;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class ItemAdapter extends ArrayAdapter<Item> {

    private Context context;
    private ArrayList<Item> itemsList;
    private int height = 0;
    private int headerHeight = 0;

    FirebaseStorage storage = FirebaseStorage.getInstance();

    // Create a reference to a file from a Google Cloud Storage URI
    StorageReference gsReference = storage.getReferenceFromUrl("gs://ecologicalhouse-a52b3.appspot.com/images/");

    public ItemAdapter(Context c, ArrayList<Item> objects) {
        super(c, 0, objects);

        this.context = c;
        this.itemsList = objects;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View view = null;

        if (itemsList != null) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

            final Item item = itemsList.get(position);

            view = inflater.inflate(R.layout.item_card, parent, false);

            final ImageView cardImage = (ImageView) view.findViewById(R.id.cardImage);
            TextView cardTitle = (TextView) view.findViewById(R.id.cardTitle);
            TextView cardDescription = (TextView) view.findViewById(R.id.cardDescription);
            TextView cardInfo = (TextView) view.findViewById(R.id.cardInfo);
            TextView cardRecipe = (TextView) view.findViewById(R.id.cardRecipe);
            final RelativeLayout card_view = view.findViewById(R.id.card_view);
            final LinearLayout header = view.findViewById(R.id.header);
            final TextView btSeeMore = view.findViewById(R.id.btSeeMore);

            cardTitle.setText(item.getName());
            cardDescription.setText(item.getDescription());
            cardInfo.setText(item.getInfo());
            cardRecipe.setText(item.getRecipe());

            gsReference.child(item.getImage()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    Glide.with(getContext())
                            .load(uri)
                            .into(cardImage);
                }
            });

            btSeeMore.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if(btSeeMore.getText().toString().equals("Ver Mais")){
                        height = card_view.getHeight();
                        btSeeMore.setText("Reduzir");
                        card_view.getLayoutParams().height = RelativeLayout.LayoutParams.WRAP_CONTENT;
                        return false;
                    }else{
                        btSeeMore.setText("Ver Mais");
                        headerHeight = header.getHeight();
                        card_view.getLayoutParams().height = headerHeight;
                        return false;
                    }
                }
            });
        }

        return view;
    }
}
