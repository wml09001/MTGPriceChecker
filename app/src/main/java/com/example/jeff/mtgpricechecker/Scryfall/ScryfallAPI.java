package com.example.jeff.mtgpricechecker.Scryfall;

import android.content.Context;
import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.*;

import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

/**
 * Boilerplate Custom Volley implementation
 * Currently not in use in case until i need a unstandard response
 *
 * Created by Jeff on 9/15/2018.
 */

/*Singleton Scryfall Connection*/

public class ScryfallAPI<T>  extends Request<T> {

/*
    private static ScryfallAPI instance = null;
    private RequestQueue mRequestQueue;
    private static Context mCtx;
*/

    private final Gson gson = new Gson();
    private final Class<T> clazz;
    private final Map<String, String> headers;
    private final Response.Listener<T> listener;

/*
    private String test = "https://api.scryfall.com/cards/search?q=set%3Aaer";

    private final String base = "https://api.scryfall.com";
    private String cards = "/cards";
    private String search = "/search?q=";
    private String setq = "set%3A";
    private String setcode = "aer";
*/

    public ScryfallAPI(String url, Class<T> clazz, Map<String, String> headers,
                       Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(Method.GET, url, errorListener);
        this.clazz = clazz;
        this.headers = headers;
        this.listener = listener;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return headers != null ? headers : super.getHeaders();
    }


    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(
                    response.data,
                    HttpHeaderParser.parseCharset(response.headers));
            return Response.success(
                    gson.fromJson(json, clazz),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError (e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError (e));
        }
    }

    @Override
    protected void deliverResponse(T response) {
        listener.onResponse(response);
    }

}
