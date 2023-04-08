package org.example.Controller;

import org.example.Config.BeanConfiguration;
import org.example.Model.Entity.CatatanKeuangan;
import org.example.Model.Entity.Pengguna;
import org.example.Service.CatatanKeuanganService;
import org.example.Service.PenggunaService;
import org.example.Util.JenisKegiatan;
import org.example.Util.stringToDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class App {

    @Autowired
    private stringToDate stringToDate;
    AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(BeanConfiguration.class);
    CatatanKeuanganController catatanKeuanganController = ctx.getBean(CatatanKeuanganController.class);
    PenggunaCotroller penggunaCotroller = ctx.getBean(PenggunaCotroller.class);
    PenggunaService penggunaService = ctx.getBean(PenggunaService.class);

    public void Run() throws Exception {


        if (penggunaService.getAll() == null) {
            System.out.println("Selamat Datang di applikasi financial tracker");
            System.out.println("Masukkan nama lengkapmu");
            Scanner nama_lengkap = new Scanner(System.in);
            String nama_lengkap1 = new String(nama_lengkap.nextLine());
            System.out.println("Masukkan emailmu");
            Scanner email = new Scanner(System.in);
            String email1 = new String(email.nextLine());
            System.out.println("Masukkan Saldo Awal");
            Scanner saldo = new Scanner(System.in);
            int saldo1 = Integer.parseInt(saldo.nextLine());

            Pengguna pengguna = new Pengguna();
            pengguna.setNama(nama_lengkap1);
            pengguna.setEmail(email1);
            pengguna.setSaldoAwal(saldo1);
            penggunaCotroller.create(pengguna);
            menuUtama();
        } else {
            menuUtama();

        }

    }
        public void menuUtama() throws Exception {

            while (true) {
                System.out.println("1. Lihat laporan keseluruhan");
                System.out.println("2. Lihat laporan harian");
                System.out.println("3. Lihat laporan bulanan");
                System.out.println("4. Tambah catatan finansial");
                System.out.println("5. Update catatan finansial berdasarkan id");
                System.out.println("6. Hapus catatan finansial berdasarkan id");
                System.out.println("7. Hapus semua catatan finansial");
                System.out.println("8. Cari catatan berdasarkan id");
                System.out.println("9. Set catatan rutin bulanan");
                System.out.println("10. Hapus catatan rutin bulanan");


                Scanner input = new Scanner(System.in);
                int input1 = Integer.parseInt(input.nextLine());

                switch (input1) {
                    case 1:
                        catatanKeuanganController.findAll();
                        break;
                    case 2:
                        System.out.println("Masukkan tanggal dari catatan finansial yang ingin dilihat");
                        Scanner input2 = new Scanner(System.in);
                        Date input3 = org.example.Util.stringToDate.generate(new String(input2.nextLine()));
                        catatanKeuanganController.laporanHarian(input3);
                        break;
                    case 3:
                        Scanner input4 = new Scanner(System.in);
                        int input6 = Integer.parseInt(input4.nextLine());
                        break;
                    case 4:
                        CatatanKeuangan catatanKeuangan = new CatatanKeuangan();
                        System.out.println("Masukkan nama kegiatan finansial");
                        Scanner input7 = new Scanner(System.in);
                        String nama = new String(input7.nextLine());
                        System.out.println("Masukkan jenis catatan (pemasukan atau pengeluaran)");
                        Scanner input8 = new Scanner(System.in);
                        org.example.Util.JenisKegiatan jenisKegiatan = org.example.Util.JenisKegiatan.valueOf(input8.nextLine());
                        System.out.println("Masukkan besarnya(dalam rupiah)");
                        Scanner input9 = new Scanner(System.in);
                        Integer besarnya = Integer.parseInt(input9.nextLine());
                        System.out.println("Masukkan tanggal");
                        Scanner input10 = new Scanner(System.in);
                        Date input11 = org.example.Util.stringToDate.generate(new String(input10.nextLine()));
                        catatanKeuangan.setDate(input11);
                        catatanKeuangan.setNamaKegiatanFinansial(nama);
                        catatanKeuangan.setJenisKegiatan(jenisKegiatan);
                        catatanKeuangan.setBesarnya(besarnya);
                        catatanKeuanganController.create(catatanKeuangan);
                        break;

                    case 5:
                        System.out.println("Masukkan id dari catatan finansial yang ingin diupdate");
                        Scanner input17 = new Scanner(System.in);
                        String id = new String(input17.nextLine());
                        CatatanKeuangan catatanKeuangan1 = new CatatanKeuangan();
                        System.out.println("Masukkan nama kegiatan finansial");
                        Scanner input12 = new Scanner(System.in);
                        String nama1 = new String(input12.nextLine());
                        System.out.println("Masukkan jenis catatan (pemasukan atau pengeluaran)");
                        Scanner input13 = new Scanner(System.in);
                        org.example.Util.JenisKegiatan jenisKegiatan1 = org.example.Util.JenisKegiatan.valueOf(input13.nextLine());
                        System.out.println("Masukkan besarnya(dalam rupiah)");
                        Scanner input14 = new Scanner(System.in);
                        int besarnya1 = Integer.parseInt(input14.nextLine());
                        System.out.println("Masukkan tanggal");
                        Scanner input15 = new Scanner(System.in);
                        Date input16 = org.example.Util.stringToDate.generate(new String(input15.nextLine()));
                        catatanKeuangan1.setDate(input16);
                        catatanKeuangan1.setNamaKegiatanFinansial(nama1);
                        catatanKeuangan1.setBesarnya(besarnya1);
                        catatanKeuangan1.setJenisKegiatan(jenisKegiatan1);
                        catatanKeuanganController.update(catatanKeuangan1, id);
                        break;

                    case 6:
                        System.out.println("Masukkan id dari kegiatan finansial yang ingin dihapus");
                        Scanner input18 = new Scanner(System.in);
                        String id1 = new String(input18.nextLine());
                        catatanKeuanganController.delete(id1);
                        break;

                    case 7:
                        catatanKeuanganController.deleteAll();
                        break;
                }


            }
        }










}
