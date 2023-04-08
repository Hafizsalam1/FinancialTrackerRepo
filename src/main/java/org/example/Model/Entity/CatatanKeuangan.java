package org.example.Model.Entity;

import org.example.Util.JenisKegiatan;

import java.util.Date;

public class CatatanKeuangan {
    private String id;
    private String namaKegiatanFinansial;
    private JenisKegiatan jenisKegiatan;
    private Integer besarnya;
    private Date date;


    public CatatanKeuangan() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNamaKegiatanFinansial() {
        return namaKegiatanFinansial;
    }

    public void setNamaKegiatanFinansial(String namaKegiatanFinansial) {
        this.namaKegiatanFinansial = namaKegiatanFinansial;
    }

    public JenisKegiatan getJenisKegiatan() {
        return jenisKegiatan;
    }

    public void setJenisKegiatan(JenisKegiatan jenisKegiatan) {
        this.jenisKegiatan = jenisKegiatan;
    }

    public Integer getBesarnya() {
        return besarnya;
    }

    public void setBesarnya(Integer besarnya) {
        this.besarnya = besarnya;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "CatatanKeuangan{" +
                "id='" + id + '\'' +
                ", namaKegiatanFinansial='" + namaKegiatanFinansial + '\'' +
                ", jenisKegiatan=" + jenisKegiatan +
                ", besarnya=" + besarnya +
                ", date=" + date +
                '}';
    }


}
