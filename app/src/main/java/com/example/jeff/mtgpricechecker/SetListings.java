package com.example.jeff.mtgpricechecker;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jeff.mtgpricechecker.EchoMTG.Set;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Jeff on 12/3/2018.
 */

public class SetListings extends RecyclerView.Adapter<SetListings.ViewHolder> {
    private ArrayList<Set> setlist;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView msetName;

        public ViewHolder (View v) {
            super(v);
            msetName = (TextView) v.findViewById(R.id.setname);
        }
    }
    public SetListings(ArrayList<Set> array) { setlist = array; }

    public SetListings.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.set_recycler_list, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }
    public void onBindViewHolder(ViewHolder holder, int position) {
        Set _set = setlist.get(position);
        holder.msetName.setText(_set.getSetname());

    }
    public int getItemCount() { return setlist.size(); }

    public void updateData(ArrayList<Set> array) { setlist = array; }
}
