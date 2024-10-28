package uts.muhammadraisyanalfarras;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText emailEditText;
    private EditText passwordEditText;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inisialisasi komponen dari layout
        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);

        // Event klik untuk tombol login
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                // Validasi email dan password
                if (email.equals("admin") && password.equals("admin")) {
                    // Jika login berhasil, pindah ke HomeActivity
                    Toast.makeText(MainActivity.this, "Login Berhasil", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, AgendaActivity.class);
                    startActivity(intent);
                    finish(); // Menutup LoginActivity agar tidak bisa kembali ke halaman login setelah berhasil
                } else {
                    // Jika login gagal
                    Toast.makeText(MainActivity.this, "Email atau Password salah", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}