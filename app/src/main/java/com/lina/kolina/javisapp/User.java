package com.lina.kolina.javisapp;


public class User {
    private int id_client;
    private String nama_perusahaan,alamat,nama_client,email,no_hp ;

    public User(int id_client, String nama_perusahaan, String alamat, String nama_client, String email, String no_hp) {
        this.id_client = id_client;
        this.nama_perusahaan = nama_perusahaan;
        this.alamat = alamat;
        this.nama_client = nama_client;
        this.email =email;
        this.no_hp = no_hp;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public String getNama_perusahaan() {
        return nama_perusahaan;
    }

    public void setNama_perusahaan(String nama_perusahaan) {
        this.nama_perusahaan = nama_perusahaan;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }


    public String getNama_client() {
        return nama_client;
    }

    public void setNama_client(String nama_client) {
        this.nama_client = nama_client;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNo_hp() {
        return no_hp;
    }

    public void setNo_hp(String no_hp) {
        this.no_hp = no_hp;
    }
}