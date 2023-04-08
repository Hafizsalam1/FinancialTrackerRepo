package org.example.Controller;

import org.example.Model.Request.CatatanKeuanganKeseluruhan;
import org.example.Model.Entity.CatatanKeuangan;
import org.example.Service.CatatanKeuanganService;
import org.example.Service.PenggunaService;
import org.example.Util.JenisKegiatan;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class CatatanKeuanganController {

    @Autowired
    ModelMapper modelMapper;

    private CatatanKeuanganService catatanKeuanganService;

    private PenggunaService penggunaService;



    Integer Saldo = penggunaService.getAll().getSaldoAwal();

    public CatatanKeuanganController(CatatanKeuanganService catatanKeuanganService) {
        this.catatanKeuanganService = catatanKeuanganService;
    }

    public void findAll() {
        List<CatatanKeuangan> catatanKeuangan = catatanKeuanganService.getAll();
        System.out.println(catatanKeuangan);

    }

    public void create (CatatanKeuangan catatanKeuangan) throws Exception{
         CatatanKeuangan catatanKeuangan1 = catatanKeuanganService.create(catatanKeuangan);
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        CatatanKeuanganKeseluruhan catatanKeuanganKeseluruhan  = modelMapper.map(catatanKeuangan1, CatatanKeuanganKeseluruhan.class);
        if(catatanKeuangan.getJenisKegiatan().equals(JenisKegiatan.pemasukan)){
            Saldo = Saldo + catatanKeuangan.getBesarnya();
            penggunaService.getAll().setSaldoAwal(Saldo);
            System.out.println("Berhasil menambahkan catatan!");
            System.out.println(catatanKeuanganKeseluruhan);
        }
        else if(catatanKeuangan.getJenisKegiatan().equals(JenisKegiatan.pengeluaran)){
            Saldo = Saldo - catatanKeuangan.getBesarnya();
            penggunaService.getAll().setSaldoAwal(Saldo);
            System.out.println("Berhasil menambahkan catatan!");
            System.out.println(catatanKeuanganKeseluruhan);

        }

}

    public void update (CatatanKeuangan catatanKeuangan, String id) throws Exception {
        Integer besarUpdateSebelumnya = catatanKeuanganService.findById(id).getBesarnya();
        catatanKeuanganService.update(catatanKeuangan, id);
        CatatanKeuangan catatanKeuangan1 = catatanKeuanganService.findById(id);
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        CatatanKeuanganKeseluruhan catatanKeuanganKeseluruhan  = modelMapper.map(catatanKeuangan1, CatatanKeuanganKeseluruhan.class);
        if(catatanKeuangan.getJenisKegiatan().equals(JenisKegiatan.pemasukan)){
            Saldo = Saldo + catatanKeuangan.getBesarnya();
            penggunaService.getAll().setSaldoAwal(Saldo);
            System.out.println("Berhasil menambahkan catatan!");
            System.out.println(catatanKeuanganKeseluruhan);
        }
        else if(catatanKeuangan.getJenisKegiatan().equals(JenisKegiatan.pengeluaran)){
            Saldo = Saldo - catatanKeuangan.getBesarnya();
            penggunaService.getAll().setSaldoAwal(Saldo);
            System.out.println("Berhasil menambahkan catatan!");
            System.out.println(catatanKeuanganKeseluruhan);

        }
            System.out.println("Berhasil memperbarui catatan!");
            System.out.println(catatanKeuangan1);
    }


    public void delete (String id) throws Exception {
            CatatanKeuangan catatanKeuangan = catatanKeuanganService.findById(id);
            catatanKeuanganService.delete(id);
            System.out.println("Berhasil menghapus catatan!");
            System.out.println(catatanKeuangan);
    }

    public void laporanHarian (Date date) throws Exception {
            List<CatatanKeuangan> catatanKeuangan = catatanKeuanganService.laporanHarian(date);
            System.out.println("Berhasil mendapatkan laporan harian!");
            System.out.println(catatanKeuangan);
    }

    public void findId(String id) throws Exception {
        CatatanKeuangan catatanKeuangan = catatanKeuanganService.findById(id);
        System.out.println("Berhasil mendapatkan catatan kegiatan dengan id " + id);
        System.out.println(catatanKeuangan);
    }

    public void deleteAll() throws Exception{
        catatanKeuanganService.deleteAll();
        System.out.println("Berhasil menghapus semua data catatan");
    }





}
