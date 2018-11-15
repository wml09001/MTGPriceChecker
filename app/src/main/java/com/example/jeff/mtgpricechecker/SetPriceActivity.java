package com.example.jeff.mtgpricechecker;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.jeff.mtgpricechecker.EchoMTG.Card;
import com.example.jeff.mtgpricechecker.Scryfall.ScryfallAPI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

        populateList();




        new Thread(new Runnable() {
            public void run() {
                try {
                    initializeScryfall();
                    //updateList();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                }
            }
        }).start();


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
        runOnUiThread(new Runnable(){
            @Override
            public void run() {
                Log.i("ARRAY SIZE updating" , Integer.toString(cardlist.size()));
                mAdapter = new CardListings(cardlist);
                mRecyclerView.setAdapter(mAdapter);
            }
        });

    }

    public void setQuery() {
        int size;



        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://api.scryfall.com/cards/search?q=fatal";
        String test = "https://api.scryfall.com/cards/search?q=set%3Agrn";

        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, test, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //Log.i("Response", response.toString());
                        try {
                            int size = 0;

                            if (response.has("total_cards")) {
                                size = response.getInt("total_cards");

                                // Scryfall API only fetches 175 cards at a time.
                                //Log.i("has more",response.getString("has_more"));

                                if (response.getString("has_more").matches("true")) {
                                    String nextpage = response.getString("next_page");
                                    Log.i("Next Page", nextpage);
                                }

                                Log.i("Num", Integer.toString(size));
                                JSONArray cardslist = response.getJSONArray("data");

                                //ArrayList<Card> setlist = new ArrayList<Card>();

                                for (int i = 0; i < size; i++) {
                                    JSONObject card = (JSONObject) cardslist.get(i);
                                    Log.i("Card Name", card.getString("name"));
                                    //setlist.add(new Card(card.getString("name"), null, null));

                                }
                                //Log.i("ARRAY SIZE" , Integer.toString(setlist.size()));
                            } else {
                                Log.i("Query fail", "0 total cards in query");
                            }



                        }
                        catch(JSONException e){

                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley Error", "failed Volley response");
            }
        });
        queue.add(stringRequest);
    }

    public void initializeScryfall() {
        testQuery("https://api.scryfall.com/cards/search?q=set%3Agrn");
    }

    public void testQuery(String test) {
        RequestQueue mRequestQueue;

        Cache cache = new DiskBasedCache(getCacheDir(), 1024*1024);
        Network network = new BasicNetwork(new HurlStack());
        mRequestQueue = new RequestQueue(cache, network);
        mRequestQueue.start();

        //String test = "https://api.scryfall.com/cards/search?q=set%3Agrn";

        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, test, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            int size = 0;
                            String nextpage = null;

                            if (response.has("total_cards")) {
                                size = response.getInt("total_cards");

                                // Scryfall API only fetches 175 cards at a time.
                                //Log.i("has more",response.getString("has_more"));

                                if (response.getString("has_more").matches("true")) {
                                    nextpage = response.getString("next_page");
                                    Log.i("Next Page", nextpage);
                                }

                                Log.i("Num", Integer.toString(size));
                                JSONArray cardslist = response.getJSONArray("data");



                                for (int i = 0; i < size; i++) {
                                    JSONObject card = (JSONObject) cardslist.get(i);
                                    Log.i("Card Name", card.getString("name"));


                                }


                                if (nextpage != null) {
                                    testQuery(nextpage);
                                }

                            } else {
                                Log.i("Query fail", "0 total cards in query");
                            }



                        }
                        catch(JSONException e){

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
