package uts.muhammadraisyanalfarras.models;

import java.io.Serializable;

public class Agenda implements Serializable {
    private int id; // ID agenda
    private String nama; // Judul agenda
    private String deskripsi; // Deskripsi agenda
    private String status; // Status agenda

    // Constructor
    public Agenda(String nama, String deskripsi, String status) {
        this.id = id;
        this.nama = nama;
        this.deskripsi = deskripsi;
        this.status = status; // Menambahkan status ke constructor
    }

    // Getter dan Setter untuk ID
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter dan Setter untuk Judul
    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    // Getter dan Setter untuk Deskripsi
    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    // Getter dan Setter untuk Status
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public int getStatusColor() {
        switch (status) {
            case "Aktif":
                return 0xFF00FF00; // Warna Hijau
            case "Tidak Aktif":
                return 0xFFFF0000; // Warna Merah
            case "Pending":
                return 0xFFFFFF00; // Warna Kuning
            default:
                return 0xFF000000; // Warna Hitam
        }
    }
}
