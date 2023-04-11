package org.example.Service;

import org.example.Model.Entity.CatatanKeuangan;
import org.example.Repository.CatatanKeuanganRepository;
import org.example.Repository.PenggunaRepository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CatatanKeuanganService {

    private CatatanKeuanganRepository catatanKeuanganRepository;

    public CatatanKeuanganService(CatatanKeuanganRepository catatanKeuanganRepository) {
        this.catatanKeuanganRepository = catatanKeuanganRepository;
    }

    public List<CatatanKeuangan> getAll() {
        try{return catatanKeuanganRepository.getAll();}
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public CatatanKeuangan create(CatatanKeuangan catatanKeuangan) {
        try{
            return catatanKeuanganRepository.create(catatanKeuangan);
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public CatatanKeuangan findById(String id) throws Exception {
        try{
            return catatanKeuanganRepository.findByid(id);
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    public void delete(String Id) {
        try{
            catatanKeuanganRepository.delete(Id);
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public void deleteAll(){
        try{
            catatanKeuanganRepository.deleteAll();
        }
        catch (Exception e){
            throw new RuntimeException("delet all gagal");
        }
    }

    public List<CatatanKeuangan> laporanHarian(Date tanggal) throws Exception {
        try{
            List<CatatanKeuangan>laporanHarian = catatanKeuanganRepository.getAll();
            List<CatatanKeuangan>laporanHarian1 = laporanHarian.stream()
                    .filter(e->e.getDate().equals(tanggal))
                    .collect(Collectors.toList());

            if(laporanHarian1.isEmpty()){
                throw new RuntimeException("Tidak ada catatan pada tanggal ini");
            }
            else{
                return laporanHarian1;
            }
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    public List<CatatanKeuangan> laporanBulanan(Integer bulan, Integer tahun) throws Exception {
        try{
            List<CatatanKeuangan>laporanBulanan = catatanKeuanganRepository.listBulanan(bulan, tahun);
            if(laporanBulanan.isEmpty()){
                throw new RuntimeException("Tidak ada catatan bulan ini");
            }
            else{
                return laporanBulanan;
            }
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    public Integer pemasukanHarian(Date tanggal){
        Integer pemasukanHarian =  catatanKeuanganRepository.pemasukanHarian(tanggal);
        if(pemasukanHarian ==null){
            return 0;
        }
        else {
            return pemasukanHarian;
        }
    }

    public Integer pengeluaranHarian(Date tanggal){
        Integer pengeluaranHarian =  catatanKeuanganRepository.pengeluaranHarian(tanggal);
        if(pengeluaranHarian ==null){
            return 0;
        }
        else {
            return pengeluaranHarian;
        }
    }

    public Integer pemasukanBulanan(Integer bulan, Integer tahun){
        Integer pemasukanBulanan = catatanKeuanganRepository.pemasukanBulanan(bulan, tahun);
        if(pemasukanBulanan == null){
            return 0;
        }
        else {
            return pemasukanBulanan;
        }
    }

    public Integer pengeluaranBulanan(Integer bulan, Integer tahun){
        Integer pengeluaranBulanan = catatanKeuanganRepository.pengeluaranBulanan(bulan, tahun);
        if(pengeluaranBulanan == null){
            return 0;
        }
        else {
            return pengeluaranBulanan;
        }
    }







}
