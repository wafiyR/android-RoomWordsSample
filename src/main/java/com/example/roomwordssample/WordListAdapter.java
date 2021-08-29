package com.example.roomwordssample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/*
 * adapter class for recyclerView
 * */

/*
 * Adapter is used to connect data to view items
 * */
public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {

    private final LayoutInflater mInflater;
    private List<Word> mWords;
    private static ClickListener clickListener;

    WordListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    // inflate a view item and return the viewHolder containing it
    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new WordViewHolder(itemView);
    }

    // set content of view item at a certain position
    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
        if (mWords != null){
            Word current = mWords.get(position);
            holder.wordItemView.setText(current.getWord());
        }
        else{
            holder.wordItemView.setText(R.string.no_word);
        }
    }

    //
    void setWords(List<Word> words){
        mWords = words;
        notifyDataSetChanged();
    }

    //get word at position
    public Word getWordAtPosition (int position) {
        return mWords.get(position);
    }

    // return the number of items in the RecyclerView
    @Override
    public int getItemCount() {
        if (mWords != null) {
            return mWords.size();
        }
        else {
            return 0;
        }
    }

    /*
     * ViewHolder is in charge of displaying one item using the separate recyclerView layout
     * */
    class WordViewHolder extends RecyclerView.ViewHolder {
        private final TextView wordItemView;

        private WordViewHolder(View itemView) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.textView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onItemClick(v, getAdapterPosition());
                }
            });
        }
    }

    public void setOnItemClickListener(ClickListener clickListener){
        WordListAdapter.clickListener = clickListener;
    }

    public interface ClickListener{
        void onItemClick(View v, int position);
    }
}
