package com.example.pkk_sip;

public class Player {
    String nama, time, ukuran, id;
    Long skor;

    public Player() {

    }

    public Player(String nama, Long skor, String time, String ukuran, String id) {
        this.nama = nama;
        this.id = id;
        this.skor = skor;
        this.ukuran = ukuran;
        this.time = time;
    }

    public Long getSkor() {
        return skor;
    }

    public void setSkor(Long skor) {
        this.skor = skor;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUkuran() {
        return ukuran;
    }

    public void setUkuran(String ukuran) {
        this.ukuran = ukuran;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
}
