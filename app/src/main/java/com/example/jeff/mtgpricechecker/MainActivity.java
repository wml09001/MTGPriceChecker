package com.example.jeff.mtgpricechecker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.LruCache;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.example.jeff.mtgpricechecker.EchoMTG.*;
import com.example.jeff.mtgpricechecker.Scryfall.*;
//import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;
import com.larvalabs.svgandroid.SVG;
import com.larvalabs.svgandroid.SVGParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {


    UserAuth login = new UserAuth();
    SharedPreferences sharedPref;

    String[] setcodes = {"GRN", "DOM", "RIX", "XLN"};
    ArrayList<Set> svgcodes = new ArrayList<Set>();

    private ImageView mImageView;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<String> setlist;

    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;

/*

    private class HttpImageRequestTask extends AsyncTask<Void, Void, Drawable> {
        String svgurl = null;

        public void setSvgurl(String url) {
            svgurl = url;
        }

        @Override
        protected Drawable doInBackground(Void... params) {
            try {
                URL url = new URL(svgurl);
                HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
                InputStream inputStream = urlConnection.getInputStream();
                SVG svg = SVGParser.getSVGFromInputStream(inputStream);
                Drawable drawable = svg.createPictureDrawable();
                return drawable;

            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Drawable drawable) {
            updateImageView(drawable);
        }
        @SuppressLint("NewApi")
        private void updateImageView(Drawable drawable) {
            if(drawable != null) {
                mImageView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
                mImageView.setImageDrawable(drawable);
            }
        }
    }
*/

    public void HttpImageRequest(String uri) {
        RequestQueue queue = Volley.newRequestQueue(this);

    }



    public void populateSetCodes(String[] setcodes) {
        List<String> setlist = Arrays.asList(setcodes);
        String scryfallset = "https://api.scryfall.com/sets/";

        for (final String code : setlist) {
            Log.i("Set Code", code);
            String query = scryfallset + code;



            RequestQueue queue = Volley.newRequestQueue(this);
            JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, query, null,
                    new Response.Listener<JSONObject>() {
                        public void onResponse(JSONObject response) {
                            String SvgUri = null;
                            try {
                                SvgUri = response.getString("icon_svg_uri");
                                Log.i("Icon url" , SvgUri);
                                Set _set = new Set();
                                _set.setSetname(response.getString("code"));
                                _set.setSvguri(SvgUri);

                                svgcodes.add(_set);

                                mAdapter.notifyDataSetChanged();
/*
                                HttpImageRequestTask imageRequestTask = new HttpImageRequestTask();
                                imageRequestTask.setSvgurl(SvgUri);
                                imageRequestTask.doInBackground();
                                */
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
        mRecyclerView = (RecyclerView) findViewById(R.id.set_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new SetListings(svgcodes);
        mRecyclerView.setAdapter(mAdapter);




        populateSetCodes(setcodes);
/*

        EchoMTGJavaAPIWrapper.getInstance(getApplicationContext()).authRequest(new EchoMTGJavaAPIWrapper.EchoCallback() {
            @Override
            public void onSuccess(JSONObject result) {
                JSONObject auth = result;
                String token = null;
                try {
                    token = auth.getString("token");
                    Log.i("Tag", "token");
                } catch (JSONException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(String error) {
                Log.e("failure: ", "UHOH");
            }
        }, login.getUser(), login.getPw());

*/


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
    public void priceCheckRIX(View view) {
        Intent intent = new Intent(this, SetPriceActivity.class);
        startActivity(intent);
    }

}
