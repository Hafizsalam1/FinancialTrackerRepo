package org.example.Controller;

import org.example.Model.DTO.CatatanKeuanganKeseluruhan;
import org.example.Model.DTO.LaporanBulanan;
import org.example.Model.DTO.LaporanHarian;
import org.example.Model.Entity.CatatanKeuangan;
import org.example.Repository.CatatanKeuanganRepository;
import org.example.Service.CatatanKeuanganService;
import org.example.Service.PenggunaService;
import org.example.Util.JenisKegiatan;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import java.util.Date;
import java.util.List;



public class CatatanKeuanganController {

    ModelMapper modelMapper;

    private CatatanKeuanganService catatanKeuanganService;

    private PenggunaService penggunaService;

    public CatatanKeuanganController(ModelMapper modelMapper, CatatanKeuanganService catatanKeuanganService, PenggunaService penggunaService) {
        this.modelMapper = modelMapper;
        this.catatanKeuanganService = catatanKeuanganService;
        this.penggunaService = penggunaService;
    }

    public void findAll() {
        List<CatatanKeuangan> catatanKeuangan = catatanKeuanganService.getAll();
        catatanKeuangan.stream().forEach(System.out::println);
    }

    public void create (CatatanKeuangan catatanKeuangan) throws Exception{
         CatatanKeuangan catatanKeuangan1 = catatanKeuanganService.create(catatanKeuangan);
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        CatatanKeuanganKeseluruhan catatanKeuanganKeseluruhan  = modelMapper.map(catatanKeuangan1, CatatanKeuanganKeseluruhan.class);
        catatanKeuanganKeseluruhan.setSaldoTotal(penggunaService.getAll().get(0).getSaldoAwal());
        System.out.println("Berhasil menambahkan catatan!");
        System.out.println(catatanKeuanganKeseluruhan);
}


    public void delete (String id) throws Exception {
            CatatanKeuangan catatanKeuangan = catatanKeuanganService.findById(id);
            catatanKeuanganService.delete(id);
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
            CatatanKeuanganKeseluruhan catatanKeuanganKeseluruhan  = modelMapper.map(catatanKeuangan, CatatanKeuanganKeseluruhan.class);
            catatanKeuanganKeseluruhan.setSaldoTotal(penggunaService.getAll().get(0).getSaldoAwal());
            System.out.println("Berhasil menghapus catatan!");
            System.out.println(catatanKeuanganKeseluruhan);
    }

    public void laporanHarian (Date tanggal) throws Exception {
        List<CatatanKeuangan> catatanKeuangan = catatanKeuanganService.laporanHarian(tanggal);
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        LaporanHarian laporanHarian = modelMapper.map(catatanKeuangan, LaporanHarian.class);
        Integer pemasukHarian = catatanKeuanganService.pemasukanHarian(tanggal);
        Integer pengeluaranHarian = catatanKeuanganService.pengeluaranHarian(tanggal);

        laporanHarian.setLaporanHarian(catatanKeuangan);
        laporanHarian.setPemasukanAkhirHari(pemasukHarian);
        laporanHarian.setPengeluaranAkhirHari(pengeluaranHarian);
        System.out.println("Berhasil mendapatkan laporan harian!");
        laporanHarian.getLaporanHarian().stream().forEach(System.out::println);
        System.out.println("Total pemasukan pada hari yang dicari: "  + " " + laporanHarian.getPemasukanAkhirHari());
        System.out.println("Total pengeluaran pada hari yang dicari:"  + " " + laporanHarian.getPengeluaranAkhirHari());
    }

    public void laporanBulanan (Integer bulan, Integer tahun) throws Exception {
        List<CatatanKeuangan> catatanKeuangan = catatanKeuanganService.laporanBulanan(bulan, tahun);
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        LaporanBulanan laporanBulanan = modelMapper.map(catatanKeuangan, LaporanBulanan.class);
        Integer PemasukanSatuBulan = catatanKeuanganService.pemasukanBulanan(bulan, tahun);
        Integer PengeluaranSatuBulan = catatanKeuanganService.pengeluaranBulanan(bulan, tahun);
        laporanBulanan.setLaporanBulanan(catatanKeuangan);
        laporanBulanan.setPemasukanSatuBulan(PemasukanSatuBulan);
        laporanBulanan.setPengeluaranSatuBulan(PengeluaranSatuBulan);
        System.out.println("Berhasil mendapatkan laporan bulanan!");
        laporanBulanan.getLaporanBulanan().stream().forEach(System.out::println);
        System.out.println("Total pemasukan selama bulan dan tahun yang dicari: "  + " " + laporanBulanan.getPemasukanSatuBulan());
        System.out.println("Total pengeluaran selama bulan dan tahun yang dicari: "  + " " + laporanBulanan.getPengeluaranSatuBulan());
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
