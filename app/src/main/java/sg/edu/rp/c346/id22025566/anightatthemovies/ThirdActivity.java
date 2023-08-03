package sg.edu.rp.c346.id22025566.anightatthemovies;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivity extends AppCompatActivity {

    private EditText etEditTitle;
    private EditText etEditGenre;
    private EditText etEditReleaseDate;
    private Button btnUpdate;
    private Button btnDelete;

    private Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        etEditTitle = findViewById(R.id.etEditTitle);
        etEditGenre = findViewById(R.id.etEditGenre);
        etEditReleaseDate = findViewById(R.id.etEditReleaseDate);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);

        // Get the movie object passed from SecondActivity
        Intent intent = getIntent();
        movie = (Movie) intent.getSerializableExtra("movie");

        if (movie != null) {
            // Set the initial values for editing
            etEditTitle.setText(movie.getTitle());
            etEditGenre.setText(movie.getGenre());
            etEditReleaseDate.setText(String.valueOf(movie.getReleaseYear()));
        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateMovie();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteMovie();
            }
        });
    }

    private void updateMovie() {
        String title = etEditTitle.getText().toString().trim();
        String genre = etEditGenre.getText().toString().trim();
        String releaseYearStr = etEditReleaseDate.getText().toString().trim();

        if (title.isEmpty() || genre.isEmpty() || releaseYearStr.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields.", Toast.LENGTH_SHORT).show();
            return;
        }

        int releaseYear = Integer.parseInt(releaseYearStr);

        // Update the movie object with new values
        movie.setTitle(title);
        movie.setGenre(genre);
        movie.setReleaseYear(releaseYear);

        // Update the movie in the database using MovieDbHelper


        Toast.makeText(this, "Movie updated successfully.", Toast.LENGTH_SHORT).show();
    }

    private void deleteMovie() {
        // Delete the movie from the database using MovieDbHelper


        Toast.makeText(this, "Movie deleted successfully.", Toast.LENGTH_SHORT).show();
        finish(); // Close ThirdActivity after deleting the movie
    }
}
