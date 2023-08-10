package sg.edu.rp.c346.id22025566.anightatthemovies;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

    private ListView moviesListView;
    private List<Movie> movieList = new ArrayList<>();
    private MovieAdapter movieAdapter;
    private Button btnShowPG13Movies;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        moviesListView = findViewById(R.id.moviesListView);
        btnShowPG13Movies = findViewById(R.id.btnShowPG13Movies);

        // Initialize the MovieAdapter with an empty movie list
        movieAdapter = new MovieAdapter(this, movieList);
        moviesListView.setAdapter(movieAdapter);

        // Fetch all movies from the database and update the adapter
        updateMovieList();

        btnShowPG13Movies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPG13Movies();
            }
        });

        moviesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected movie from the list
                Movie selectedMovie = movieList.get(position);

                // Create an intent to launch ThirdActivity
                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);

                // Pass the selected movie as an extra to ThirdActivity
                intent.putExtra("movie", selectedMovie);

                // Start ThirdActivity
                startActivity(intent);
            }
        });


    }
    private void showPG13Movies() {
        List<Movie> pg13Movies = new ArrayList<>();
        for (Movie movie : movieList) {
            if ("PG13".equals(movie.getRating())) {
                pg13Movies.add(movie);
            }
        }

        // Clear the current list and add the PG-13 movies
        movieList.clear();
        movieList.addAll(pg13Movies);

        // Notify the adapter that the data has changed
        movieAdapter.notifyDataSetChanged();
    }



    private void updateMovieList() {
        // Fetch all movies from the database using MovieDbHelper

        MovieDbHelper dbHelper = new MovieDbHelper(this);
        List<Movie> fetchedMovies = dbHelper.getAllMovies();


        // Clear the current list and add the fetched movies
        movieList.clear();
        movieList.addAll(fetchedMovies);

        // Notify the adapter that the data has changed
        movieAdapter.notifyDataSetChanged();
    }




}
