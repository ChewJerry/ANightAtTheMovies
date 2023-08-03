package sg.edu.rp.c346.id22025566.anightatthemovies;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

public class MovieDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "movies.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_MOVIES = "movies";
    public static final String COLUMN_ID = BaseColumns._ID;
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_GENRE = "genre";
    public static final String COLUMN_RELEASE_YEAR = "release_year";
    public static final String COLUMN_RATING = "rating";

    private static final String CREATE_TABLE_MOVIES =
            "CREATE TABLE " + TABLE_MOVIES + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_TITLE + " TEXT NOT NULL, " +
                    COLUMN_GENRE + " TEXT NOT NULL, " +
                    COLUMN_RELEASE_YEAR + " INTEGER NOT NULL, " +
                    COLUMN_RATING + " TEXT NOT NULL)";

    public MovieDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_MOVIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVIES);
        onCreate(db);
    }

    // Method to fetch all movies from the database
    public List<Movie> getAllMovies() {
        List<Movie> movieList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;

        try {
            cursor = db.query(TABLE_MOVIES, null, null, null, null, null, null);
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    int idIndex = cursor.getColumnIndex(COLUMN_ID);
                    int titleIndex = cursor.getColumnIndex(COLUMN_TITLE);
                    int genreIndex = cursor.getColumnIndex(COLUMN_GENRE);
                    int releaseYearIndex = cursor.getColumnIndex(COLUMN_RELEASE_YEAR);
                    int ratingIndex = cursor.getColumnIndex(COLUMN_RATING);

                    int id = cursor.getInt(idIndex);
                    String title = cursor.getString(titleIndex);
                    String genre = cursor.getString(genreIndex);
                    int releaseYear = cursor.getInt(releaseYearIndex);
                    String rating = cursor.getString(ratingIndex);

                    Movie movie = new Movie();
                    movie.setId(id);
                    movie.setTitle(title);
                    movie.setGenre(genre);
                    movie.setReleaseYear(releaseYear);
                    movie.setRating(rating);

                    movieList.add(movie);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }

        return movieList;
    }


    public void updateMovie(Movie movie) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, movie.getTitle());
        values.put(COLUMN_GENRE, movie.getGenre());
        values.put(COLUMN_RELEASE_YEAR, movie.getReleaseYear());
        values.put(COLUMN_RATING, movie.getRating());

        try {
            db.update(TABLE_MOVIES, values, COLUMN_ID + "=?", new String[]{String.valueOf(movie.getId())});
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
    }

    public void deleteMovie(int movieId) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            db.delete(TABLE_MOVIES, COLUMN_ID + "=?", new String[]{String.valueOf(movieId)});
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
    }
    public long insertMovie(Movie movie) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, movie.getTitle());
        values.put(COLUMN_GENRE, movie.getGenre());
        values.put(COLUMN_RELEASE_YEAR, movie.getReleaseYear());
        values.put(COLUMN_RATING, movie.getRating());

        long result = -1; // Default value in case insertion fails
        try {
            result = db.insert(TABLE_MOVIES, null, values);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }

        return result;
    }



}
