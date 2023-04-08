package org.example.Repository;

import org.example.Model.Entity.CatatanKeuangan;
import org.example.Model.Entity.Pengguna;
import org.example.Model.Mapper.CatatanKeuanganMapper;
import org.example.Model.Mapper.PenggunaMapper;
import org.example.Util.RandomUUID;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class PenggunaRepository {

    private JdbcTemplate jdbcTemplate;
    RandomUUID randomUUID = new RandomUUID();

    private final String SQL_GET_ALL = "select*from pengguna";

    private final String SQL_INSERT_FINPLAN= "insert into pengguna values(?, ?, ?, ?)";

    public PenggunaRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }



    public Pengguna getAll() throws Exception {
        return jdbcTemplate.queryForObject(SQL_GET_ALL, new PenggunaMapper());

    }

    public Pengguna create(Pengguna pengguna) throws Exception {
        pengguna.setId(randomUUID.Random());
        int result = jdbcTemplate.update(SQL_INSERT_FINPLAN, pengguna.getId(), pengguna.getNama(), pengguna.getEmail(), pengguna.getSaldoAwal());
        if (result<=0){
            throw new Exception("gagal menambahkan pengguna");
        }
        return pengguna;
    }



}
