package com.lina.kolina.javisapp;

public class ModelData {


    String id_order, id_client, nama_proyek, lokasi_proyek, deskripsi_proyek, status, deadline, detail_komplain, id_komplain;

    public ModelData(){}

    public ModelData(String id_order, String id_client, String nama_proyek, String lokasi_proyek, String deskripsi_proyek, String status, String deadline, String id_komplain, String detail_komplain) {
        this.id_order= id_order;
        this.id_client = id_client;
        this.nama_proyek = nama_proyek;
        this.lokasi_proyek= lokasi_proyek;
        this.deskripsi_proyek = deskripsi_proyek;
        this.status = status;
        this.deadline= deadline;
        this.id_komplain =id_komplain;
        this.detail_komplain = detail_komplain;
    }

    public String getId_order() {
        return id_order;
    }

    public void setId_order(String id_order) {
        this.id_order= id_order;
    }

    public String getId_client() {
        return id_client;
    }

    public void setId_client(String id_client) {
        this.id_client = id_client;
    }

    public String getNama_proyek() {
        return nama_proyek;
    }

    public void setNama_proyek(String nama_proyek) {
        this.nama_proyek = nama_proyek;
    }

    public String getLokasi_proyek() {
        return lokasi_proyek;
    }

    public void setLokasi_proyek(String lokasi_proyek) {
        this.lokasi_proyek = lokasi_proyek;
    }


    public String getDeskripsi_proyek() {
        return deskripsi_proyek;
    }

    public void setDeskripsi_proyek(String deskripsi_proyek) {
        this.deskripsi_proyek = deskripsi_proyek;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getId_komplain() {
        return id_komplain;
    }

    public void setId_komplain(String id_komplain) {
        this.id_komplain = id_komplain;
    }

    public String getDetail_komplain() {
        return detail_komplain;
    }

    public void setDetail_komplain(String detail_komplain) {
        this.detail_komplain = detail_komplain;
    }
}
