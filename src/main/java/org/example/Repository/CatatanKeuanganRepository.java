package org.example.Repository;

import org.example.Model.Entity.CatatanKeuangan;
import org.example.Model.Entity.Pengguna;
import org.example.Model.Mapper.CatatanKeuanganMapper;
import org.example.Util.JenisKegiatan;
import org.example.Util.RandomUUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;


public class CatatanKeuanganRepository {

    RandomUUID randomUUID = new RandomUUID();

    private PenggunaRepository penggunaRepository;



    private JdbcTemplate jdbcTemplate;

    private final String SQL_GET_ALL = "select*from finplan";
    private final String SQL_INSERT_FINPLAN= "insert into finplan values(?, ?, ?, ?, ?)";
    private final String SQL_DELETE = "delete from finplan where id = ?";
    private final String SQL_FIND_BY_ID = "select*from finplan where id = ?";
    private final String SQL_DELETE_ALL = "truncate table finplan";
    private final String SQL_CHANGE_SALDO = "update pengguna set saldo = ? where id =?";
    private final String SQL_TOTAL_PEMASUKAN_HARI_INI = "select sum (besarnya) from finplan where pemasukan_atau_pengeluaran = 'pemasukan' and tanggal = ?";
    private final String SQL_TOTAL_PENGELUARAN_HARI_INI = "select sum (besarnya) from finplan where pemasukan_atau_pengeluaran = 'pengeluaran' and tanggal = ?";
    private final String SQL_TOTAL_PEMASUKAN_BULAN_INI = "select sum (BESARNYA) from finplan where pemasukan_atau_pengeluaran = 'pemasukan' and extract(month from tanggal) = ? and extract(year from tanggal) = ?";
    private final String SQL_TOTAL_PENGELUARAN_BULAN_INI = "select sum (BESARNYA) from finplan where pemasukan_atau_pengeluaran = 'pengeluaran' and extract(month from tanggal) = ? and extract(year from tanggal) = ?";
    private final String SQL_LIST_BULAN_INI = "SELECT*from finplan where EXTRACT(MONTH FROM tanggal) = ? AND EXTRACT(YEAR FROM tanggal)=?";





    public CatatanKeuanganRepository(DataSource dataSource, PenggunaRepository penggunaRepository) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.penggunaRepository = penggunaRepository;
    }

    public List<CatatanKeuangan> getAll() throws Exception {
        return jdbcTemplate.query(SQL_GET_ALL, new CatatanKeuanganMapper());

    }

    public CatatanKeuangan create(CatatanKeuangan catatanKeuangan) throws Exception {
        catatanKeuangan.setId(randomUUID.Random());
            int result = jdbcTemplate.update(SQL_INSERT_FINPLAN, catatanKeuangan.getId(), catatanKeuangan.getDate(), catatanKeuangan.getNamaKegiatanFinansial(), catatanKeuangan.getBesarnya(), String.valueOf(catatanKeuangan.getJenisKegiatan()));
            if (result<=0){
                throw new Exception("gagal menambahkan catatan");
            }
            if(catatanKeuangan.getJenisKegiatan().equals(JenisKegiatan.pemasukan)){
                int result1 = jdbcTemplate.update(SQL_CHANGE_SALDO, (penggunaRepository.getAll().get(0).getSaldoAwal() + catatanKeuangan.getBesarnya()), penggunaRepository.getAll().get(0).getId());
            }
            if(catatanKeuangan.getJenisKegiatan().equals(JenisKegiatan.pengeluaran)){
                int result1 = jdbcTemplate.update(SQL_CHANGE_SALDO, (penggunaRepository.getAll().get(0).getSaldoAwal() - catatanKeuangan.getBesarnya()), penggunaRepository.getAll().get(0).getId());
            }


        return catatanKeuangan;
    }


    public void delete(String id) throws Exception {
        Integer saldoDihapus = findByid(id).getBesarnya();
            jdbcTemplate.update(SQL_DELETE, id);
            jdbcTemplate.update(SQL_CHANGE_SALDO, (penggunaRepository.getAll().get(0).getSaldoAwal() + saldoDihapus), penggunaRepository.getAll().get(0).getId());
    }

    public void deleteAll() {
            jdbcTemplate.update(SQL_DELETE_ALL);

    }


    public CatatanKeuangan findByid(String id) {
        return  jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new CatatanKeuanganMapper(), id);
    }

    public Integer pemasukanHarian(Date tanggal){
        try{
            return jdbcTemplate.queryForObject(SQL_TOTAL_PEMASUKAN_HARI_INI, new Object[]{tanggal}, Integer.class);
        }
        catch (DataAccessException e){
            return null;
        }

    }

    public List<CatatanKeuangan>listBulanan(Integer bulan, Integer tahun){
        return jdbcTemplate.query(SQL_LIST_BULAN_INI, new CatatanKeuanganMapper(), bulan, tahun);
    }


    public Integer pengeluaranHarian(Date tanggal){
        try{
            return jdbcTemplate.queryForObject(SQL_TOTAL_PENGELUARAN_HARI_INI, new Object[]{tanggal}, Integer.class);
        }
        catch (DataAccessException e){
            return null;
        }
    }

    public Integer pengeluaranBulanan(Integer bulan, Integer tahun ){

        try{
            return jdbcTemplate.queryForObject(SQL_TOTAL_PENGELUARAN_BULAN_INI, new Object[]{bulan, tahun}, Integer.class);
        }
        catch (DataAccessException e){
            return null;
        }

    }

    public Integer pemasukanBulanan(Integer bulan, Integer tahun ){

        try{
            return jdbcTemplate.queryForObject(SQL_TOTAL_PEMASUKAN_BULAN_INI, new Object[]{bulan, tahun}, Integer.class);
        }
        catch (DataAccessException e){
            return null;
        }

    }










}
