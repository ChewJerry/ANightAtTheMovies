package sg.edu.rp.c346.id22025566.anightatthemovies;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MovieAdapter extends ArrayAdapter<Movie> {

    private Context context;
    private List<Movie> movies;

    public MovieAdapter(Context context, List<Movie> movies) {
        super(context, 0, movies);
        this.context = context;
        this.movies = movies;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_movie, parent, false);
        }

        Movie currentMovie = getItem(position);

        TextView titleTextView = convertView.findViewById(R.id.titleTextView);
        TextView genreTextView = convertView.findViewById(R.id.genreTextView);
        TextView releaseYearTextView = convertView.findViewById(R.id.releaseYearTextView);
        ImageView ratingImageView = convertView.findViewById(R.id.ratingImageView);

        titleTextView.setText(currentMovie.getTitle());
        genreTextView.setText(currentMovie.getGenre());
        releaseYearTextView.setText(String.valueOf(currentMovie.getReleaseYear()));

        // Set the appropriate image for the rating
        int ratingImageResource = R.drawable.rating_g; // Default to G rating
        String rating = currentMovie.getRating();
        if ("PG".equals(rating)) {
            ratingImageResource = R.drawable.rating_pg;
        } else if ("PG13".equals(rating)) {
            ratingImageResource = R.drawable.rating_pg13;
        } else if ("NC16".equals(rating)) {
            ratingImageResource = R.drawable.rating_nc16;
        } else if ("M18".equals(rating)) {
            ratingImageResource = R.drawable.rating_m18;
        } else if ("R21".equals(rating)) {
            ratingImageResource = R.drawable.rating_r21;
        }
        ratingImageView.setImageResource(ratingImageResource);

        return convertView;
    }
}
