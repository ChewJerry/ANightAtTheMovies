package sg.edu.rp.c346.id22025566.anightatthemovies;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import sg.edu.rp.c346.id22025566.anightatthemovies.Movie;

public class MainActivity extends AppCompatActivity {

    private EditText enterMovieTitle;
    private EditText enterGenre;
    private EditText enterReleaseDate;
    private Spinner spinnerRating;
    private Button btnInsert;
    private Button btnShowList;

    // Initialize the movie list
    private List<Movie> movieList = new ArrayList<>();

    // Initialize the database helper
    private MovieDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new MovieDbHelper(this);

        enterMovieTitle = findViewById(R.id.enterMovieTitle);
        enterGenre = findViewById(R.id.enterGenre);
        enterReleaseDate = findViewById(R.id.enterReleaseDate);
        spinnerRating = findViewById(R.id.spinnerRating);
        btnInsert = findViewById(R.id.btnInsert);
        btnShowList = findViewById(R.id.btnShowList);

        // Populate the spinner with rating options
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.movie_ratings,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRating.setAdapter(adapter);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertMovie();
            }
        });

        btnShowList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMovieList();
            }
        });
    }

    private void insertMovie() {
        String title = enterMovieTitle.getText().toString().trim();
        String genre = enterGenre.getText().toString().trim();
        String releaseYearStr = enterReleaseDate.getText().toString().trim();
        String rating = spinnerRating.getSelectedItem().toString();

        if (title.isEmpty() || genre.isEmpty() || releaseYearStr.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields.", Toast.LENGTH_SHORT).show();
            return;
        }

        int releaseYear = Integer.parseInt(releaseYearStr);

        Movie movie = new Movie(title, genre, releaseYear, rating);
        long result = dbHelper.insertMovie(movie);

        if (result != -1) {
            Toast.makeText(this, "Movie inserted successfully.", Toast.LENGTH_SHORT).show();
            // Clear input fields after successful insertion
            enterMovieTitle.setText("");
            enterGenre.setText("");
            enterReleaseDate.setText("");
        } else {
            Toast.makeText(this, "Failed to insert movie.", Toast.LENGTH_SHORT).show();
        }
    }

    private void showMovieList() {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        startActivity(intent);
    }
}
