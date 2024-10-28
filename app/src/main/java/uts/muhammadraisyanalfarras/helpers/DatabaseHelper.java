package uts.muhammadraisyanalfarras.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Nama dan versi database
    public static final String DATABASE_NAME = "agenda.db";
    public static final int DATABASE_VERSION = 1;

    // Nama tabel dan nama kolom
    public static final String TABLE_AGENDA = "agenda";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TITLE = "nama"; // Mengubah dari "title" ke "nama"
    public static final String COLUMN_DESCRIPTION = "deskripsi"; // Mengubah dari "description" ke "deskripsi"
    public static final String COLUMN_STATUS = "status"; // Kolom untuk menyimpan status agenda

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Membuat tabel agenda dengan kolom yang diperlukan
        String createTable = "CREATE TABLE " + TABLE_AGENDA + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " TEXT NOT NULL, " + // Nama agenda tidak boleh null
                COLUMN_DESCRIPTION + " TEXT NOT NULL, " + // Deskripsi agenda tidak boleh null
                COLUMN_STATUS + " TEXT NOT NULL" + // Status agenda tidak boleh null
                ");";
        db.execSQL(createTable); // Menjalankan perintah untuk membuat tabel
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Menghapus tabel jika sudah ada dan membuat ulang
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_AGENDA);
        onCreate(db); // Membuat tabel baru
    }
}
