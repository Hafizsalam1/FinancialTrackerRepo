package org.example.Model.DTO;

import org.example.Model.Entity.CatatanKeuangan;

import java.util.List;

public class LaporanBulanan {
    private List<CatatanKeuangan> laporanBulanan;
    private Integer pemasukanSatuBulan;
    private Integer pengeluaranSatuBulan;

    public LaporanBulanan() {
    }

    public List<CatatanKeuangan> getLaporanBulanan() {
        return laporanBulanan;
    }

    public void setLaporanBulanan(List<CatatanKeuangan> laporanBulanan) {
        this.laporanBulanan = laporanBulanan;
    }

    public Integer getPemasukanSatuBulan() {
        return pemasukanSatuBulan;
    }

    public void setPemasukanSatuBulan(Integer pemasukanSatuBulan) {
        this.pemasukanSatuBulan = pemasukanSatuBulan;
    }

    public Integer getPengeluaranSatuBulan() {
        return pengeluaranSatuBulan;
    }

    public void setPengeluaranSatuBulan(Integer pengeluaranSatuBulan) {
        this.pengeluaranSatuBulan = pengeluaranSatuBulan;
    }
}
