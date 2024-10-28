package uts.muhammadraisyanalfarras.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import uts.muhammadraisyanalfarras.models.Agenda;

public class AgendaRepository {

    private static DatabaseHelper databaseHelper;

    public AgendaRepository(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    // Mengambil semua agenda dari database
    public ArrayList<Agenda> tampilAgenda() {
        ArrayList<Agenda> agendaList = new ArrayList<>();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_AGENDA, null);

        while (cursor.moveToNext()) {
            agendaList.add(new Agenda(
                    // ID
                    cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_TITLE)),
                    cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_DESCRIPTION)),
                    cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_STATUS))
            ));
        }
        cursor.close(); // Pastikan untuk menutup cursor setelah selesai
        return agendaList;
    }

    // Menambahkan agenda ke database
    public long tambahAgenda(Agenda agenda) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_TITLE, agenda.getNama());
        values.put(DatabaseHelper.COLUMN_DESCRIPTION, agenda.getDeskripsi());
        values.put(DatabaseHelper.COLUMN_STATUS, agenda.getStatus()); // Menyimpan status

        long id = db.insert(DatabaseHelper.TABLE_AGENDA, null, values); // Menyimpan menggunakan ContentValues
        return id; // Mengembalikan ID dari agenda yang baru ditambahkan
    }

    // Mengupdate agenda berdasarkan ID
    public void updateAgenda(int id, Agenda agenda) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_TITLE, agenda.getNama());
        values.put(DatabaseHelper.COLUMN_DESCRIPTION, agenda.getDeskripsi());
        values.put(DatabaseHelper.COLUMN_STATUS, agenda.getStatus()); // Mengupdate status

        db.update(DatabaseHelper.TABLE_AGENDA, values, DatabaseHelper.COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
    }

    // Menghapus agenda berdasarkan ID
    public void deleteAgenda(int id) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        db.delete(DatabaseHelper.TABLE_AGENDA, DatabaseHelper.COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
    }


    public Agenda getAgendaById(int id) {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_AGENDA + " WHERE id = ?", new String[]{String.valueOf(id)});
        Agenda agenda = null;

        if (cursor.moveToFirst()) {
            agenda = new Agenda(
                    // ID
                    cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_TITLE)),
                    cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_DESCRIPTION)),
                    cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_STATUS))
            );
        }
        cursor.close(); // Pastikan untuk menutup cursor
        return agenda; // Mengembalikan agenda yang ditemukan
    }
}
