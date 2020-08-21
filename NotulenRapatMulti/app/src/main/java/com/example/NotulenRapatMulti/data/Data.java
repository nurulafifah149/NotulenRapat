package com.example.NotulenRapatMulti.data;

public class Data {
    private String id2, tanggal, waktu, lokasi, kehadiran, topik, judul, isi;

    public Data() {
    }

    public Data(String id, String nama, String alamat) {
        this.id2 = id2;
        this.tanggal = tanggal;
        this.waktu = waktu;
        this.lokasi = lokasi;
        this.kehadiran = kehadiran;
        this.topik = topik;
        this.judul = judul;
        this.isi = isi;
    }

    public String getId2() {
        return id2;
    }

    public void setId2(String id2) {
        this.id2 = id2;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getKehadiran() {
        return kehadiran;
    }

    public void setKehadiran(String kehadiran) {
        this.kehadiran = kehadiran;
    }

    public String getTopik() {
        return topik;
    }

    public void setTopik(String topik) {
        this.topik = topik;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getIsi() {
        return isi;
    }

    public void setIsi(String isi) {
        this.isi = isi;
    }

}
