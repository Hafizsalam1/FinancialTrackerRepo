package org.example.Model.Entity;

public class Pengguna {
    private String id;

    private String nama;
    private String Email;
    private Integer saldoAwal;

    public Pengguna() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public Integer getSaldoAwal() {
        return saldoAwal;
    }

    public void setSaldoAwal(Integer saldoAwal) {
        this.saldoAwal = saldoAwal;
    }

    public String getNama() {
        return nama;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }

    @Override
    public String toString() {
        return "Pengguna{" +
                ", nama='" + nama + '\'' +
                ", Email='" + Email + '\'' +
                ", saldoAwal=" + saldoAwal +
                '}';
    }
}
