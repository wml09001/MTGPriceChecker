package com.example.jeff.mtgpricechecker.Scryfall;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import com.larvalabs.svgandroid.SVG;
import com.larvalabs.svgandroid.SVGParser;

import java.io.InputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;


/**
 * Created by Jeff on 11/28/2018.
 */
/*


public class HttpImageRequestTask extends AsyncTask<Void, Void, Drawable> {
    @Override
    protected Drawable doInBackground(Void... params) {
        try {
            final URL url = new URL("");
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
