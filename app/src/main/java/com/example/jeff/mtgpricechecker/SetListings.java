package com.example.jeff.mtgpricechecker;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/*import com.bumptech.glide.Glide;*/
import com.example.jeff.mtgpricechecker.Containers.Set;

import java.util.ArrayList;

/**
 * Created by Jeff on 12/3/2018.
 */

public class SetListings extends RecyclerView.Adapter<SetListings.ViewHolder> {
    private ArrayList<Set> setlist;
    private onSetListener mOnSetListener;


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView msetName;
        public TextView msetCode;
        public ImageView msetIcon;
        Context context;

        onSetListener _onSetListener;

        public ViewHolder (View v, onSetListener onSetListener) {
            super(v);
            msetName = (TextView) v.findViewById(R.id.setname);
            msetCode = (TextView) v.findViewById(R.id.setcode);
            msetIcon = (ImageView) v.findViewById(R.id.seticon);
            this._onSetListener = onSetListener;

            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            _onSetListener.onSetClick(getAdapterPosition());
        }
    }
    public SetListings(ArrayList<Set> array, onSetListener onSetListener) {
        setlist = array;
        this.mOnSetListener = onSetListener;
    }

    public SetListings.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.set_recycler_list, parent, false);
        ViewHolder vh = new ViewHolder(v, mOnSetListener);
        return vh;
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        Set _set = setlist.get(position);
        holder.msetCode.setText(_set.getSetCode());
        holder.msetName.setText(_set.getSetName());
        holder.msetIcon.setImageResource(_set.getImage());

    }

    public int getItemCount() { return setlist.size(); }

    public interface onSetListener {
        void onSetClick(int position);
    }

    public void updateData(ArrayList<Set> array) { setlist = array; }
}
