package com.example.jeff.mtgpricechecker;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import com.example.jeff.mtgpricechecker.EchoMTG.Card;
import java.util.ArrayList;


public class SetPriceActivity extends Activity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Card> cardlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_price);

        mRecyclerView = (RecyclerView) findViewById(R.id.price_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        cardlist = new ArrayList<Card>();

        for (int i = 0; 10 > i; i++) {
            Card _card = new Card("test " + i, null, null);
            cardlist.add(_card);
        }


        mAdapter = new CardListings(cardlist);
        mRecyclerView.setAdapter(mAdapter);

    }

}
