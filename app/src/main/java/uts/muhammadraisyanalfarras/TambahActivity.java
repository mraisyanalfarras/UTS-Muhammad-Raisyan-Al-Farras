package uts.muhammadraisyanalfarras;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import uts.muhammadraisyanalfarras.helpers.AgendaRepository;
import uts.muhammadraisyanalfarras.models.Agenda;

public class TambahActivity extends AppCompatActivity {

    private EditText etNamaAgenda, etDeskripsi;
    private Spinner spStatus;
    private Button btnSimpan;
    private AgendaRepository agendaRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);
        // Inisialisasi komponen UI
        etNamaAgenda = findViewById(R.id.et_namaagenda);
        etDeskripsi = findViewById(R.id.et_deskripsi);
        spStatus = findViewById(R.id.et_status);
        btnSimpan = findViewById(R.id.btn_simpan);

        // Inisialisasi AgendaRepository
        agendaRepository = new AgendaRepository(this);

        // Setup Spinner untuk status
        setupSpinner();

        // Listener untuk tombol Simpan
        btnSimpan.setOnClickListener(view -> {
            String namaAgenda = etNamaAgenda.getText().toString().trim();
            String deskripsi = etDeskripsi.getText().toString().trim();
            String status = spStatus.getSelectedItem().toString(); // Mengambil status yang dipilih

            // Validasi input dan simpan ke database
            if (validateInput(namaAgenda, deskripsi)) {
                Agenda agenda = new Agenda(namaAgenda, deskripsi, status);
                long id = agendaRepository.tambahAgenda(agenda); // Menambahkan agenda ke database

                if (id > 0) {
                    finish(); // Kembali ke Activity sebelumnya
                } else {
                    // Tampilkan pesan gagal
                    showToast("Gagal menambahkan agenda.");
                }
            }
        });
    }

    // Fungsi untuk mengatur item pada Spinner
    private void setupSpinner() {
        String[] statuses = {"selesai", "belum selesai"}; // Contoh status
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, statuses);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spStatus.setAdapter(adapter);
    }

    // Fungsi untuk memvalidasi input
    private boolean validateInput(String namaAgenda, String deskripsi) {
        if (namaAgenda.isEmpty()) {
            etNamaAgenda.setError("Nama agenda tidak boleh kosong");
            return false;
        }
        if (deskripsi.isEmpty()) {
            etDeskripsi.setError("Deskripsi tidak boleh kosong");
            return false;
        }
        return true;
    }

    // Fungsi untuk menampilkan toast
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }}