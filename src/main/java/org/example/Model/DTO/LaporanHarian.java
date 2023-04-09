package org.example.Model.DTO;

import org.example.Model.Entity.CatatanKeuangan;

import java.util.List;

public class LaporanHarian {
    private List<CatatanKeuangan> laporanHarian;
    private Integer pengeluaranAkhirHari;
    private Integer pemasukanAkhirHari;



    public LaporanHarian() {
    }

    public List<CatatanKeuangan> getLaporanHarian() {
        return laporanHarian;
    }

    public void setLaporanHarian(List<CatatanKeuangan> laporanHarian) {
        this.laporanHarian = laporanHarian;
    }

    public Integer getPengeluaranAkhirHari() {
        return pengeluaranAkhirHari;
    }

    public void setPengeluaranAkhirHari(Integer pengeluaranAkhirHari) {
        this.pengeluaranAkhirHari = pengeluaranAkhirHari;
    }

    public Integer getPemasukanAkhirHari() {
        return pemasukanAkhirHari;
    }

    public void setPemasukanAkhirHari(Integer pemasukanAkhirHari) {
        this.pemasukanAkhirHari = pemasukanAkhirHari;
    }

    @Override
    public String toString() {
        return "LaporanHarian{" +
                "laporanHarian=" + laporanHarian +
                ", pengeluaranAkhirHari=" + pengeluaranAkhirHari +
                ", pemasukanAkhirHari=" + pemasukanAkhirHari +
                '}';
    }
}
