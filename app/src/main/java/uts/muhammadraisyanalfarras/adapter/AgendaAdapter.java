package uts.muhammadraisyanalfarras.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import uts.muhammadraisyanalfarras.models.Agenda;
import uts.muhammadraisyanalfarras.R;

public class AgendaAdapter extends BaseAdapter {

    private final Context context;
    private final ArrayList<Agenda> agendaList;

    public AgendaAdapter(Context context, ArrayList<Agenda> agendaList) {
        this.context = context;
        this.agendaList = agendaList;
    }

    @Override
    public int getCount() {
        return agendaList.size(); // Mengembalikan jumlah item dalam daftar
    }

    @Override
    public Object getItem(int position) {
        return agendaList.get(position); // Mengembalikan item pada posisi tertentu
    }

    @Override
    public long getItemId(int position) {
        return agendaList.get(position).getId(); // Mengembalikan ID item
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Agenda agenda = agendaList.get(position);
        ViewHolder viewHolder;

        if (convertView == null) {
            // Inflate layout item_agenda.xml jika convertView null
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.item, parent, false);

            // Menginisialisasi ViewHolder dan menyimpan referensi tampilan
            viewHolder = new ViewHolder();
            viewHolder.textViewJudul = convertView.findViewById(R.id.tv_nama);
            viewHolder.textViewDeskripsi = convertView.findViewById(R.id.tv_deskripsi);
            viewHolder.textViewStatus = convertView.findViewById(R.id.tv_status);

            convertView.setTag(viewHolder); // Simpan ViewHolder di tag untuk akses lebih lanjut
        } else {
            viewHolder = (ViewHolder) convertView.getTag(); // Ambil ViewHolder yang sudah ada
        }

        // Mengatur teks untuk TextView sesuai dengan agenda
        viewHolder.textViewJudul.setText(agenda.getNama()); // Mengambil nama agenda
        viewHolder.textViewDeskripsi.setText(agenda.getDeskripsi()); // Mengambil deskripsi agenda
        viewHolder.textViewStatus.setText(agenda.getStatus()); // Mengambil status agenda

        // Mengatur warna status
        viewHolder.textViewStatus.setTextColor(agenda.getStatusColor()); // Set warna status sesuai dengan status

        return convertView; // Kembalikan tampilan untuk item agenda
    }

    // ViewHolder untuk menyimpan referensi tampilan
    static class ViewHolder {
        TextView textViewJudul;
        TextView textViewDeskripsi;
        TextView textViewStatus;
    }
}
