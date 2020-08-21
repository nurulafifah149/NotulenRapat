package com.example.NotulenRapatMulti.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.NotulenRapatMulti.R;
import com.example.NotulenRapatMulti.app.AppController;
import com.example.NotulenRapatMulti.data.Data;

import java.util.List;

public class Adapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Data> items;

    public Adapter(Activity activity, List<Data> items) {
        this.activity = activity;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int location) {
        return items.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_row, null);

        TextView id2 = (TextView) convertView.findViewById(R.id.id2);
        TextView tanggal = (TextView) convertView.findViewById(R.id.txt_tanggal);
        TextView waktu = (TextView) convertView.findViewById(R.id.txt_waktu);
        TextView lokasi = (TextView) convertView.findViewById(R.id.txt_lokasi);
        TextView kehadiran = (TextView) convertView.findViewById(R.id.txt_kehadiran);
        TextView topik = (TextView) convertView.findViewById(R.id.txt_topik);
        TextView judul = (TextView) convertView.findViewById(R.id.txt_judul);
        TextView isi = (TextView) convertView.findViewById(R.id.txt_isi);

        Data data = items.get(position);

        id2.setText(data.getId2());
        tanggal.setText(data.getTanggal());
        waktu.setText(data.getWaktu());
        lokasi.setText(data.getLokasi());
        kehadiran.setText(data.getKehadiran());
        topik.setText(data.getTopik());
        judul.setText(data.getJudul());
        isi.setText(data.getIsi());

        return convertView;
    }

}