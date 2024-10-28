package uts.muhammadraisyanalfarras;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import uts.muhammadraisyanalfarras.TambahActivity;
import uts.muhammadraisyanalfarras.adapter.AgendaAdapter;
import uts.muhammadraisyanalfarras.helpers.AgendaRepository;
import uts.muhammadraisyanalfarras.models.Agenda;
import uts.muhammadraisyanalfarras.R;

public class AgendaActivity extends AppCompatActivity {

    private ListView listViewAgenda;
    private FloatingActionButton fabAddAgenda;
    private ArrayList<Agenda> agendaList;
    private AgendaAdapter agendaAdapter;
    private AgendaRepository agendaRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);

        // Inisialisasi view dan variabel
        listViewAgenda = findViewById(R.id.lvagenda);
        fabAddAgenda = findViewById(R.id.fabAddTransaction);

        // Inisialisasi repository dan daftar agenda
        agendaRepository = new AgendaRepository(this);
        agendaList = new ArrayList<>();

        // Menampilkan agenda dari database
        tampilAgenda();

        // Menetapkan adapter ke ListView
        agendaAdapter = new AgendaAdapter(this, agendaList);
        listViewAgenda.setAdapter(agendaAdapter);

        // Menangani klik untuk menambah agenda baru
        fabAddAgenda.setOnClickListener(v -> {
            Intent intent = new Intent(AgendaActivity.this, TambahActivity.class);
            startActivityForResult(intent, 1); // Mulai TambahActivity untuk hasil
        });

        // Menangani klik pada item di ListView untuk mengedit agenda
//        "}listViewAgenda.setOnItemClickListener((parent, view, position, id) -> {
//            Agenda agenda = agendaList.get(position);
//            Intent intent = new Intent(AgendaActivity.this, EditActivity.class);
//            intent.putExtra("agenda", agenda);
//            startActivityForResult(intent, 2); // Kembali untuk mengedit
//        });"

        // Menangani klik panjang pada item di ListView untuk menghapus agenda
        listViewAgenda.setOnItemLongClickListener((parent, view, position, id) -> {
            onDelete(agendaList.get(position).getId(), position);
            return true; // Mengindikasikan bahwa event telah ditangani
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        agendaList.clear();
        tampilAgenda(); // Memuat ulang agenda ketika kembali
        agendaAdapter.notifyDataSetChanged();
    }

    private void tampilAgenda() {
        agendaList = agendaRepository.tampilAgenda(); // Mengambil semua agenda dari database
        agendaAdapter = new AgendaAdapter(this, agendaList); // Inisialisasi adapter
        listViewAgenda.setAdapter(agendaAdapter); // Set adapter ke ListView
    }

    private void onDelete(int id, int position) {
        agendaRepository.deleteAgenda(id); // Menghapus agenda dari database
        agendaList.remove(position); // Menghapus agenda dari list
        agendaAdapter.notifyDataSetChanged(); // Memberitahu adapter untuk memperbarui tampilan
    }
}
