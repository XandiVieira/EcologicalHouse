package com.example.xandi.ecologicalhouse;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ItemAdapter extends ArrayAdapter<Item> {

    private Context context;
    private ArrayList<Item> itemsList;

    public ItemAdapter(Context c, ArrayList<Item> objects) {
        super(c, 0, objects);

        this.context = c;
        this.itemsList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = null;

        if (itemsList != null) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

            Item item = itemsList.get(position);

            view = inflater.inflate(R.layout.item_card, parent, false);

            ImageView cardImage = (ImageView) view.findViewById(R.id.cardImage);
            TextView cardTitle = (TextView) view.findViewById(R.id.cardTitle);
            TextView cardDescription = (TextView) view.findViewById(R.id.cardDescription);
            TextView cardInfo = (TextView) view.findViewById(R.id.cardInfo);
            TextView cardRecipe = (TextView) view.findViewById(R.id.cardRecipe);

            cardTitle.setText(item.getName());
            cardDescription.setText(item.getDescription());
            cardInfo.setText(item.getInfo());
            cardRecipe.setText(item.getRecipe());
            Glide.with(getContext())
                    .load(item.getImage())
                    .into(cardImage);
        }

        return view;
    }
}
