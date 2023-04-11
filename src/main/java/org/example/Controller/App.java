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
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class App {


    AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(BeanConfiguration.class);
    CatatanKeuanganController catatanKeuanganController = ctx.getBean(CatatanKeuanganController.class);
    PenggunaCotroller penggunaCotroller = ctx.getBean(PenggunaCotroller.class);
    PenggunaService penggunaService = ctx.getBean(PenggunaService.class);




    public void Run() throws Exception {

        if(penggunaService.getAll().isEmpty()){
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

        }
        else {
            menuUtama();
        }

            }

        public void menuUtama() throws Exception {

            while (true) {
                System.out.println("1. Lihat laporan keseluruhan");
                System.out.println("2. Lihat laporan harian");
                System.out.println("3. Lihat laporan bulanan");
                System.out.println("4. Tambah catatan finansial");
                System.out.println("5. Hapus catatan finansial berdasarkan id");
                System.out.println("6. Cari catatan berdasarkan id");
                System.out.println("7. Info Pengguna");
                System.out.println("8. Hapus Pengguna");
                System.out.println("9. Keluar");
                System.out.println("Masukkan menu yang diinginkan!");





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
                        System.out.println("Masukkan bulan dari catatan finansial yang ingin dilihat (contoh: 02)");
                        Scanner input4 = new Scanner(System.in);
                        Integer bulan= Integer.parseInt(input4.nextLine());
                        System.out.println("Masukkan tahun dari catatan finansial yang ingin dilihat (contoh: 2020)");
                        Scanner tahun = new Scanner(System.in);
                        Integer tahun1 = Integer.parseInt(tahun.nextLine());
                        catatanKeuanganController.laporanBulanan(bulan, tahun1);
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
                        System.out.println("Masukkan tanggal(YYY-MM-DD)");
                        Scanner input10 = new Scanner(System.in);
                        Date input11 = org.example.Util.stringToDate.generate(new String(input10.nextLine()));
                        catatanKeuangan.setDate(input11);
                        catatanKeuangan.setNamaKegiatanFinansial(nama);
                        catatanKeuangan.setJenisKegiatan(jenisKegiatan);
                        catatanKeuangan.setBesarnya(besarnya);
                        catatanKeuanganController.create(catatanKeuangan);
                        break;

                    case 5:
                        System.out.println("Masukkan id dari catatan finansial yang ingin dihapus");
                        Scanner input18 = new Scanner(System.in);
                        String id1 = new String(input18.nextLine());
                        catatanKeuanganController.delete(id1);
                        break;


                    case 6:
                        System.out.println("Masukkan id dari catatan finansial yang dicari");
                        Scanner input19 = new Scanner(System.in);
                        String id2 = new String(input19.nextLine());
                        catatanKeuanganController.findId(id2);
                        break;

                    case 7:
                        System.out.println(penggunaService.getAll().get(0));
                        break;
                    case 8:
                        penggunaCotroller.delete();
                        catatanKeuanganController.deleteAll();
                        Run();
                        break;

                    case 9:
                        System.exit(0);
                }


            }
        }










}
