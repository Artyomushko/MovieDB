package com.example.moviedb.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.moviedb.activity.ActivityDetails;
import com.example.moviedb.Const;
import com.example.moviedb.converter.DateConverter;
import com.example.moviedb.R;
import com.example.moviedb.internet.TestInternetConnection;
import com.example.moviedb.model.genre.GenreDetails;
import com.example.moviedb.model.genre.Result;
import com.example.moviedb.retrofit.ApiClient;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterForGenreDetails extends RecyclerView.Adapter<AdapterForGenreDetails.Holder> {

    List<Result> movies;
    private Context context;
    LayoutInflater layoutInflater;
    ImageLoader imageLoader;
    private static final int FOOTER_VIEW = 1;
    int pageNumber;
    private final int CacheSize = 52428800;
    private final int MinFreeSpace = 2048;
    int id;

    public AdapterForGenreDetails(Context context, List<Result> movies, int id) {
        this.context = context;
        this.movies = movies;
        this.id = id;
        pageNumber = 2;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v;
        if (viewType == FOOTER_VIEW) {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.footer, parent, false);
            FooterViewHolder vh = new FooterViewHolder(v);
            return vh;
        }
        return new Holder(layoutInflater.inflate(R.layout.item_for_recycler_view_standart, parent, false));
    }

    public class FooterViewHolder extends Holder {
        Button button;

        public FooterViewHolder(View itemView) {
            super(itemView);
            button = (Button) itemView.findViewById(R.id.button_footer);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!TestInternetConnection.checkConnection(context)) {
                        Toast.makeText(context, "no internet connection", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "loading more films...", Toast.LENGTH_SHORT).show();
                        Call<GenreDetails> call = ApiClient.getClient().getGenreDetails(id ,pageNumber, Const.API_KEY);
                        pageNumber++;
                        call.enqueue(new Callback<GenreDetails>() {
                            @Override
                            public void onResponse(Call<GenreDetails> call, Response<GenreDetails> response) {
                                try {
                                    movies.addAll(response.body().getResults());
                                    updateList(movies);
                                } catch (NullPointerException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onFailure(Call<GenreDetails> call, Throwable t) {
                            }
                        });
                    }
                }
            });
        }
    }

    @Override
    public void onBindViewHolder(final Holder holder, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ActivityDetails.class);
                intent.putExtra("id", movies.get(position).getId());
                intent.putExtra("title", movies.get(position).getTitle());
                context.startActivity(intent);
            }
        });
        File cacheDir = StorageUtils.getCacheDirectory(context);
        long size = 0;
        File[] filesCache = cacheDir.listFiles();
        for (File file : filesCache) {
            size += file.length();
        }
        if (cacheDir.getUsableSpace() < MinFreeSpace || size > CacheSize) {
            ImageLoader.getInstance().getDiskCache().clear();
        }
        try {
            setImage(Const.IMAGE_POSTER_PATH_URL + movies.get(position).getPosterPath(), holder.imageView);
            holder.textViewName.setText(movies.get(position).getTitle());
            holder.textViewYear.setText(DateConverter.formateDateFromstring("yyyy-MM-dd", "dd, MMMM, yyy",
                    movies.get(position).getReleaseDate()));
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }

    public void setImage(final String url, final ImageView imageView) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                Glide
                        .with(context)
                        .load(url)
                        .override(110, 110)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .placeholder(R.drawable.placeholder_item_recycler_view)
                        .crossFade()
                        .into(imageView);
            }
        });
        t.run();
    }

    @Override
    public int getItemCount() {
        return movies.size() + 1;
    }

    public void updateList(List<Result> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == movies.size()) {
            return FOOTER_VIEW;
        }
        return super.getItemViewType(position);
    }

    static class Holder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textViewName;
        TextView textViewYear;

        public Holder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.image_view_for_item);
            textViewName = (TextView) itemView.findViewById(R.id.item_text_view_name);
            textViewYear = (TextView) itemView.findViewById(R.id.item_text_view_year);
        }
    }
}