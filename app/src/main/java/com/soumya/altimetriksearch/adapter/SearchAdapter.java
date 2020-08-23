package com.soumya.altimetriksearch.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.soumya.altimetriksearch.R;
import com.soumya.altimetriksearch.model.Search;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Search> mSearchList;
    public SearchAdapter() {
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.search_list_item,
                viewGroup, false);
        return new SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        ((SearchViewHolder)viewHolder).artistName.setText(mSearchList.get(i).getArtistName());
        ((SearchViewHolder)viewHolder).trackName.setText(mSearchList.get(i).getTrackName());
        ((SearchViewHolder)viewHolder).collectionName.setText(mSearchList.get(i).getCollectionName());
        ((SearchViewHolder)viewHolder).collectionPrice.setText(mSearchList.get(i).getCollectionPrice());
        ((SearchViewHolder)viewHolder).releaseDate.setText(mSearchList.get(i).getReleaseDate());
        ((SearchViewHolder)viewHolder).artworkUrl100.setText(mSearchList.get(i).getArtworkUrl100());
    }

    @Override
    public int getItemCount() {
        if (mSearchList != null){
            return mSearchList.size();
        }
        return 0;
    }

    public void setSearch(List<Search> searchList){
        mSearchList = searchList;
        notifyDataSetChanged();
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder {

        TextView artistName;
        TextView trackName;
        TextView collectionName;
        TextView collectionPrice;
        TextView releaseDate;
        TextView artworkUrl100;

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            artistName = itemView.findViewById(R.id.artistName);
            trackName = itemView.findViewById(R.id.trackName);
            collectionName = itemView.findViewById(R.id.collectionName);
            collectionPrice = itemView.findViewById(R.id.collectionPrice);
            releaseDate = itemView.findViewById(R.id.releaseDate);
            artworkUrl100 = itemView.findViewById(R.id.artworkUrl100);
        }
    }
}
