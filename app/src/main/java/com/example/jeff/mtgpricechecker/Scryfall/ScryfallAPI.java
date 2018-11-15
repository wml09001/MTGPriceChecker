package com.example.jeff.mtgpricechecker.Scryfall;

import android.content.Context;
import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Jeff on 9/15/2018.
 */

/*Singleton Scryfall Connection*/

public class ScryfallAPI {

    private static ScryfallAPI instance = null;
    private RequestQueue mRequestQueue;
    private static Context mCtx;



    private String test = "https://api.scryfall.com/cards/search?q=set%3Aaer";

    private final String base = "https://api.scryfall.com";
    private String cards = "/cards";
    private String search = "/search?q=";
    private String setq = "set%3A";
    private String setcode = "aer";


    private ScryfallAPI (Context context) {
        mCtx = context;
        mRequestQueue = getRequestQueue();
    }

    public static synchronized ScryfallAPI getInstance(Context context) {
        if (instance == null) instance = new ScryfallAPI(context);
        return instance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return mRequestQueue;
    }
    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

/*

    public void connection() {
        try {
//            URL scryfall = new URL(base + cards + search + setq + setcode);
            //URL scryfall = new URL("https://api.scryfall.com/cards/search?q=fatal");
            URL scryfall = new URL(base);
            HttpsURLConnection myConnection = (HttpsURLConnection) scryfall.openConnection();

            //URLConnection myConnection = scryfall.openConnection();

            if (myConnection.getResponseCode() == 200) {
                InputStream response = myConnection.getInputStream();
                InputStreamReader data = new InputStreamReader(response, "UTF-8");
                JsonReader jsonreader = new JsonReader(data);
                jsonreader.beginArray();
                while (jsonreader.hasNext()) {
                    String key = jsonreader.nextName();
                    Log.i("data:" , key);
                }



*/
/*                jsonreader.beginArray();
                while(jsonreader.hasNext()) {

                }
*//*

            }
            else {
                Log.i("Denied", "Https code != 200");
            }
            myConnection.disconnect();

        } catch (Exception e) {
            Log.e("ERR", "URL syntax");
        }
*/

}
