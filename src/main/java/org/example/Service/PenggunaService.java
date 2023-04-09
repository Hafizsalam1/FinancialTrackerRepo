package org.example.Service;

import org.example.Model.Entity.CatatanKeuangan;
import org.example.Model.Entity.Pengguna;
import org.example.Repository.CatatanKeuanganRepository;
import org.example.Repository.PenggunaRepository;

import java.util.List;

public class PenggunaService {


    private PenggunaRepository penggunaRepository;

    public PenggunaService() {
    }

    public PenggunaService(PenggunaRepository penggunaRepository) {
        this.penggunaRepository = penggunaRepository;
    }

    public List<Pengguna> getAll() {
        try{return penggunaRepository.getAll();}
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public Pengguna create(Pengguna pengguna) {
        try{
            return penggunaRepository.create(pengguna);
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public void delete(String Id) {
        try{
            penggunaRepository.delete(Id);
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }


}
