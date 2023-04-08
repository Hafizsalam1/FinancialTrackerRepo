package org.example.Repository;

import org.example.Model.Entity.CatatanKeuangan;
import org.example.Model.Mapper.CatatanKeuanganMapper;
import org.example.Util.RandomUUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.util.List;


public class CatatanKeuanganRepository {

    RandomUUID randomUUID = new RandomUUID();



    private JdbcTemplate jdbcTemplate;

    private final String SQL_GET_ALL = "select*from finplan";
    private final String SQL_INSERT_FINPLAN= "insert into finplan values(?, ?, ?, ?, ?)";
    private final String SQL_DELETE = "delete from finplan where id = ?";
    private final String SQL_UPDATE_FINPLAN= "update finplan set namaKegiatanFinansial = ?, tanggal = ?, besarnya = ?, pemasukan_atau_pengeluaran = ? where id =?";
    private final String SQL_FIND_BY_ID = "select*from finplan where id = ?";
    private final String SQL_DELETE_ALL = "truncate table finplan";


    public CatatanKeuanganRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
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
            return catatanKeuangan;
    }

    public void update(CatatanKeuangan catatanKeuangan, String id) throws Exception {

            jdbcTemplate.update(SQL_UPDATE_FINPLAN, catatanKeuangan.getNamaKegiatanFinansial(), catatanKeuangan.getDate(),catatanKeuangan.getBesarnya(),String.valueOf(catatanKeuangan.getJenisKegiatan()), id );

    }

    public void delete(String id) throws Exception {
            jdbcTemplate.update(SQL_DELETE, id);

    }

    public void deleteAll() throws Exception {
            jdbcTemplate.update(SQL_DELETE_ALL);
    }


    public CatatanKeuangan findByid(String id) throws Exception {
        CatatanKeuangan catatanKeuangan = (CatatanKeuangan) jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new CatatanKeuanganMapper(), new Object[]{id});
        return catatanKeuangan;
    }





}
