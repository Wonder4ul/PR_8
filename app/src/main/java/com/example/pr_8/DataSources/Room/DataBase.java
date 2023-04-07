package com.example.pr_8.DataSources.Room;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.pr_8.Book;
import com.example.pr_8.R;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Book.class}, version = 1, exportSchema = false)
public abstract class DataBase extends RoomDatabase {

    public abstract BookDao bookDao();

    private static volatile DataBase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static DataBase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (DataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    DataBase.class, "book_database")
                            .allowMainThreadQueries()
                            .addCallback(dataBaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
    private static final RoomDatabase.Callback dataBaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);


            databaseWriteExecutor.execute(() -> {
                BookDao dao = INSTANCE.bookDao();
                for (int i = 0; i < 10; i++) {
                    Book book = new Book("Dune" + i, R.drawable.book_svgrepo_com);
                    dao.insert(book);
                }

            });
        }
    };
}
