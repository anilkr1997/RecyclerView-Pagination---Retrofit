package com.thereza.Retrofitpagination;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.thereza.Retrofitpagination.R;
import com.thereza.Retrofitpagination.models.Result;
import com.thereza.Retrofitpagination.models.Topodderss;

import java.util.ArrayList;
import java.util.List;


public class PaginationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int ITEM = 0;
    private static final int LOADING = 1;
    private static final String BASE_URL_IMG = "https://image.tmdb.org/t/p/w150";

    private List<Topodderss.Datuma> Results;
    private Context context;

    private boolean isLoadingAdded = false;

    public PaginationAdapter(Context context) {
        this.context = context;
        Results = new ArrayList<>();
    }

    public List<Topodderss.Datuma> getMovies() {
        return Results;
    }

    public void setMovies(List<Topodderss.Datuma> movieResults) {
        this.Results = movieResults;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case ITEM:
                viewHolder = getViewHolder(parent, inflater);
                break;
            case LOADING:
                View v2 = inflater.inflate(R.layout.item_progress, parent, false);
                viewHolder = new LoadingVH(v2);
                break;
        }
        return viewHolder;
    }

    @NonNull
    private RecyclerView.ViewHolder getViewHolder(ViewGroup parent, LayoutInflater inflater) {
        RecyclerView.ViewHolder viewHolder;
        View v1 = inflater.inflate(R.layout.item_list, parent, false);
        viewHolder = new MovieVH(v1);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        Topodderss.Datuma result = Results.get(position); // Movie

        switch (getItemViewType(position)) {
            case ITEM:
                final MovieVH movieVH = (MovieVH) holder;

                movieVH.mMovieTitle.setText(result.getOrderCode());


                movieVH.mYear.setText(
                        ""+result.getDeliveryStatus()  // we want the year only

                );
                movieVH.mMovieDesc.setText(result.getMessage());

                /**
                 * Using Glide to handle image loading.
                 */

                break;

            case LOADING:
//                Do nothing
                break;


        }

    }

    @Override
    public int getItemCount() {
        return Results == null ? 0 : Results.size();
    }

    @Override
    public int getItemViewType(int position) {
        return (position == Results.size() - 1 && isLoadingAdded) ? LOADING : ITEM;
    }


    /*
   Helpers
   _________________________________________________________________________________________________
    */

    public void add(Topodderss.Datuma r) {
        Results.add(r);
        notifyItemInserted(Results.size() - 1);
    }

    public void addAll(List<Topodderss.Datuma> moveResults) {
        for (Topodderss.Datuma result : moveResults) {
            add(result);
        }
    }

    public void remove(Result r) {
        int position = Results.indexOf(r);
        if (position > -1) {
            Results.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        isLoadingAdded = false;

    }

    public boolean isEmpty() {
        return getItemCount() == 0;
    }


    public void addLoadingFooter() {
        isLoadingAdded = true;
        add(new Topodderss.Datuma());
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = Results.size() - 1;
        Topodderss.Datuma result = getItem(position);

        if (result != null) {
            Results.remove(position);
            notifyItemRemoved(position);
        }
    }

    public Topodderss.Datuma getItem(int position) {
        return Results.get(position);
    }


   /*
   View Holders
   _________________________________________________________________________________________________
    */

    protected class MovieVH extends RecyclerView.ViewHolder {
        private TextView mMovieTitle;
        private TextView mMovieDesc;
        private TextView mYear; // displays "year | language"
        private ImageView mPosterImg;
        private ProgressBar mProgress;

        public MovieVH(View itemView) {
            super(itemView);

            mMovieTitle = (TextView) itemView.findViewById(R.id.movie_title);
            mMovieDesc = (TextView) itemView.findViewById(R.id.movie_desc);
            mYear = (TextView) itemView.findViewById(R.id.movie_year);
            mPosterImg = (ImageView) itemView.findViewById(R.id.movie_poster);
            mProgress = (ProgressBar) itemView.findViewById(R.id.movie_progress);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent i = new Intent(context, DetailsActivity.class);
                    i.putExtra("mMovieTitle",mMovieTitle.getText());
                    i.putExtra("mMovieDesc",mMovieDesc.getText());
                    i.putExtra("mYear",mYear.getText());
                    //i.putExtra("mPosterImg",extras);
                    context.startActivity(i);

                }
            });
        }
    }


    protected class LoadingVH extends RecyclerView.ViewHolder {

        public LoadingVH(View itemView) {
            super(itemView);
        }
    }


}
