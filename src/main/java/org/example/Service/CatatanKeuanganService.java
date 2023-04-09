package org.example.Service;

import org.example.Model.Entity.CatatanKeuangan;
import org.example.Repository.CatatanKeuanganRepository;
import org.example.Repository.PenggunaRepository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

    public void update(CatatanKeuangan catatanKeuangan, String Id) {
        try{
            catatanKeuanganRepository.update(catatanKeuangan, Id);
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public CatatanKeuangan findById(String id) throws Exception {
        try{
            CatatanKeuangan catatanKeuangan = catatanKeuanganRepository.findByid(id);
            return catatanKeuangan;
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

    public List<CatatanKeuangan> laporanHarian(Date date) throws Exception {
        try{
            List<CatatanKeuangan> catatanKeuangan = getAll();
            List<CatatanKeuangan>laporanHarian = new ArrayList<>();
            for (CatatanKeuangan cat: catatanKeuangan) {
                if(cat.getDate().equals(date)){
                    laporanHarian.add(cat);
                }
            }
            if(laporanHarian.isEmpty()){
                throw new RuntimeException("Tidak ada catatan pada tanggal ini");
            }
            else{
                return laporanHarian;
            }
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    public List<CatatanKeuangan> laporanBulanan(Integer bulan, Integer tahun) throws Exception {
        try{
            List<CatatanKeuangan> catatanKeuangan = getAll();
            List<CatatanKeuangan>laporanBulanan = new ArrayList<>();
            for (CatatanKeuangan cat: catatanKeuangan) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(cat.getDate());

                if(calendar.get(Calendar.MONTH)-1==bulan && calendar.get(Calendar.YEAR)==tahun){
                    laporanBulanan.add(cat);
                }
            }
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







}
