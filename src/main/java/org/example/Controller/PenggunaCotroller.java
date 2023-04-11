package org.example.Controller;

import org.example.Model.Entity.CatatanKeuangan;
import org.example.Model.Entity.Pengguna;
import org.example.Service.CatatanKeuanganService;
import org.example.Service.PenggunaService;

import java.util.List;

public class PenggunaCotroller {

    private PenggunaService penggunaService;

    public PenggunaCotroller(PenggunaService penggunaService) {
        this.penggunaService = penggunaService;
    }

    public void findAll() {
        List<Pengguna> pengguna = penggunaService.getAll();
        System.out.println(pengguna);
    }

    public void create (Pengguna pengguna) throws Exception{
        penggunaService.create(pengguna);
        System.out.println("Berhasil menambahkan pengguna!");
        System.out.println(pengguna);
    }

    public void delete() throws Exception{
        penggunaService.delete();
        System.out.println("Berhasil menghapus data pengguna");
    }

}
