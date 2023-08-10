package sg.edu.rp.c346.id22025566.anightatthemovies;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
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
                updateConfirmationDialog();
            }

        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletionConfirmationDialog();
            }
        });
    }
    private void updateConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm Update");
        builder.setMessage("Are you sure you want to update the movie?");
        builder.setCancelable(false);
        builder.setPositiveButton("UPDATE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                updateMovie();
            }
        });
        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {

            }
        });
        builder.show();
    }
    private void deletionConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm Delete");
        builder.setMessage("Are you sure you want to delete this movie?");
        builder.setCancelable(false);
        builder.setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                deleteMovie();
            }
        });
        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {

            }
        });
        builder.show();
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
        MovieDbHelper dbHelper = new MovieDbHelper(this);
        dbHelper.updateMovie(movie);

        // Create an intent to pass back the updated movie data to SecondActivity
        Intent intent = new Intent();
        intent.putExtra("updatedMovie", movie);
        setResult(RESULT_OK, intent);

        Toast.makeText(this, "Movie updated successfully.", Toast.LENGTH_SHORT).show();
        finish(); // Close ThirdActivity after updating the movie
    }

    private void deleteMovie() {
        // Delete the movie from the database using MovieDbHelper
        MovieDbHelper dbHelper = new MovieDbHelper(this);
        dbHelper.deleteMovie(movie.getId()); // Assuming you have a method to delete by ID

        // Create an intent to pass back the movie ID to SecondActivity
        Intent intent = new Intent();
        intent.putExtra("deletedMovieId", movie.getId());
        setResult(RESULT_OK, intent);

        Toast.makeText(this, "Movie deleted successfully.", Toast.LENGTH_SHORT).show();
        finish(); // Close ThirdActivity after deleting the movie
    }
}
