package com.example.jeff.mtgpricechecker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import com.example.jeff.mtgpricechecker.Containers.*;
//import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;
//import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;
//import com.github.twocoffeesoneteam.glidetovectoryou.SvgDecoder;
//import com.github.twocoffeesoneteam.glidetovectoryou.SvgDrawableTranscoder;
//import com.github.twocoffeesoneteam.glidetovectoryou.SvgSoftwareLayerSetter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/*

import javax.net.ssl.HttpsURLConnection;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.util.Preconditions;
import com.bumptech.glide.load.model.StreamEncoder;
import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;
import android.net.Uri;
*/

public class MainActivity extends AppCompatActivity implements SetListings.onSetListener {


    SharedPreferences sharedPref;

    String[] setcodes = {"RNA", "GRN", "M20", "ELD", "WAR"};
    private static final String TAG = "SVGActivity";
    ArrayList<Set> svgcodes;

    private ImageView mImageView;

    //private RequestBuilder<PictureDrawable> requestBuilder;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<String> setlist;

    private RequestQueue mRequestQueue;



    public void populateSet(String[] setcodes) {
        List<String> setlist = Arrays.asList(setcodes);
        String scryfallset = "https://api.scryfall.com/sets/";


        for (final String code : setlist) {
            //Log.i("Set Code", code);
            String query = scryfallset + code;



            RequestQueue queue = Volley.newRequestQueue(this);
            JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, query, null,
                    new Response.Listener<JSONObject>() {
                        public void onResponse(JSONObject response) {
                            String setcode;
                            String setname;
                            try {
                                //SvgUri = response.getString("icon_svg_uri");
                                //Log.i("Icon url" , SvgUri);
                                Set _set = new Set();
                                setcode = response.getString("code");
                                _set.setSetCode(setcode.toUpperCase());
                                int imgID = getResources().getIdentifier(setcode, "drawable", getPackageName());
                                _set.setImage(imgID);
                                _set.setSetName(response.getString("name"));


                                svgcodes.add(_set);

                                mAdapter.notifyDataSetChanged();

                            }
                            catch (JSONException e) {

                            }
                        }
                    },
                        new Response.ErrorListener() {
                            public void onErrorResponse(VolleyError error) {

                            }
                    });
            queue.add(stringRequest);
        }
        //Log.i("size", Integer.toString(svgcodes.size()));
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
/*
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mImageView = (ImageView) findViewById(R.id.setimage);

        GlideToVectorYou.justLoadImage(this, Uri.parse("https://img.scryfall.com/sets/grn.svg?1543208400"), mImageView);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
*/
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("MTG Price Checker");




        mRecyclerView = (RecyclerView) findViewById(R.id.set_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        svgcodes = new ArrayList<Set>();


        mAdapter = new SetListings(svgcodes, this);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));



        populateSet(setcodes);



/*        ImageButton rix = (ImageButton) findViewById(R.id.button_rix);

        rix.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SetPriceActivity.class);
                startActivity(intent);
            }
        });*/
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
/*    public void priceCheckRIX(View view) {
        Intent intent = new Intent(this, SetPriceActivity.class);
        startActivity(intent);
    }*/

    @Override
    public void onSetClick(int position) {
        Intent intent = new Intent(this, SetPriceActivity.class);

        String setcode = svgcodes.get(position).getSetCode();
        intent.putExtra("SETCODE", setcode);
        startActivity(intent);
    }
}


