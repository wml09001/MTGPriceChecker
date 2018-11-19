package com.example.jeff.mtgpricechecker;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import android.view.View;
import com.example.jeff.mtgpricechecker.EchoMTG.Card;

import java.util.ArrayList;

/**
 * Created by Jeff on 4/6/2018.
 */

public class CardListings extends RecyclerView.Adapter<CardListings.ViewHolder> {
    private String[] mDataset;
    private ArrayList<Card> cardlist;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextView;
        public ViewHolder (View v) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.cardname);
        }
    }

    public CardListings(ArrayList<Card> array) {
        cardlist = array;
    }

    public CardListings.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.card_recycler_list, parent, false);


        //TextView v = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_recycler_list, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
//        holder.mTextView.setText(mDataset[position]);
        Card _card = cardlist.get(position);
        holder.mTextView.setText(_card.getCardname());
    }

    public int getItemCount() {
        return cardlist.size();
    }

    public void updateData(ArrayList<Card> array) { cardlist = array; }
}
