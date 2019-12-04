package com.example.jeff.mtgpricechecker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.jeff.mtgpricechecker.Containers.Card;
import com.victor.loading.rotate.RotateLoading;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class SetPriceActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Card> cardlist;
    private ArrayList<Card> setlist;
    private String msetcode;
    private RotateLoading rotateLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_set_price);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);

        rotateLoading = findViewById(R.id.rotateloading);

 /*       if(rotateLoading.isStart()){
            rotateLoading.stop();
        }else{
            rotateLoading.start();
        }*/

        setSupportActionBar(toolbar);


        Intent intent = getIntent();
        msetcode = intent.getStringExtra("SETCODE");
        setTitle(msetcode);

        mRecyclerView = (RecyclerView) findViewById(R.id.price_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        cardlist = new ArrayList<Card>();
        setlist = new ArrayList<Card>();

        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        mAdapter = new CardListings(cardlist);
        mRecyclerView.setAdapter(mAdapter);

        testQuery("https://api.scryfall.com/cards/search?q=set%3A" + msetcode);
    }

    private void populateList() {
        runOnUiThread(new Runnable(){
            @Override
            public void run() {

                for (int i = 0; 10 > i; i++) {
                    Card _card = new Card("test " + i, null, null);
                    cardlist.add(_card);
                }
                mAdapter = new CardListings(cardlist);
                mRecyclerView.setAdapter(mAdapter);
            }
        });

    }

    private void updateList() {
/*        runOnUiThread(new Runnable(){
            @Override
            public void run() {
                Log.i("ARRAY SIZE updating" , Integer.toString(cardlist.size()));
                mAdapter = new CardListings(cardlist);
                mRecyclerView.setAdapter(mAdapter);
            }
        });*/


    }

    public void testQuery(String test) {

        rotateLoading.start();

        RequestQueue mRequestQueue = Volley.newRequestQueue(this);


        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, test, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            int size = 0;
                            String nextpage = null;
                            Card _card;

                            if (response.has("total_cards")) {
                                size = response.getInt("total_cards");

                                // Scryfall API only fetches 175 cards at a time.
                                //Log.i("has more",response.getString("has_more"));

                                if (response.getString("has_more").matches("true")) {
                                    nextpage = response.getString("next_page");
/*
                                    Log.i("Next Page", nextpage);
*/
                                }

/*
                                Log.i("Num", Integer.toString(size));
*/
                                JSONArray cardslist = response.getJSONArray("data");



                                for (int i = 0; i < cardslist.length(); i++) {
                                    JSONObject card = (JSONObject) cardslist.get(i);
                                    //Log.i("Card Name", card.getString("name"));
                                    _card = new Card();
                                    _card.setCardname(card.getString("name"));
                                    JSONObject pricelist = card.getJSONObject("prices");


                                    if (!pricelist.has("usd") ) {
                                        //Log.i("USD Price", card.getString("usd"));
                                        _card.setPrice("0");
                                    }
                                    else {
                                        _card.setPrice(pricelist.getString("usd"));
                                        //Log.i("card price",pricelist.getString("usd") );
                                    }
                                    cardlist.add(_card);
                                }
                                if (nextpage != null) {
                                    testQuery(nextpage);
                                }

/*
                                Log.i("Set List Size", Integer.toString(cardlist.size()));
*/
                                mAdapter.notifyDataSetChanged();

                            } else {
                                Log.i("Query fail", "0 total cards in query");
                            }
                        }
                        catch (JSONException e){

                        } finally {
                            rotateLoading.stop();
                        }
                    }
                },
                    new Response.ErrorListener() {
                        public void onErrorResponse(VolleyError error) {

                        }
                });
        mRequestQueue.add(stringRequest);

    }


}
