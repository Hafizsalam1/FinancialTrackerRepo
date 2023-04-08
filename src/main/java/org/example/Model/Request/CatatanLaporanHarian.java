package org.example.Model.Request;

import org.example.Model.Entity.CatatanKeuangan;

import java.util.List;

public class CatatanLaporanHarian {
    private List<CatatanKeuangan> laporanHarian;


    private Integer totalSaldo;


    public CatatanLaporanHarian(Integer totalSaldo) {
        this.totalSaldo = totalSaldo;
    }

    public List<CatatanKeuangan> getLaporanHarian() {
        return laporanHarian;
    }

    public void setLaporanHarian(List<CatatanKeuangan> laporanHarian) {
        this.laporanHarian = laporanHarian;
    }

    public Integer getTotalSaldo() {
        return totalSaldo;
    }

    public void setTotalSaldo(Integer totalSaldo) {
        this.totalSaldo = totalSaldo;
    }
}
