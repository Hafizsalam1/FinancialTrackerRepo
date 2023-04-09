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

    private final String SQL_INSERT_PENGGUNA= "insert into pengguna values(?, ?, ?, ?)";

    private final String SQL_DELETE = "delete from pengguna where id = ?";



    public PenggunaRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }



    public List<Pengguna> getAll() throws Exception {
        return jdbcTemplate.query(SQL_GET_ALL, new PenggunaMapper());
    }

    public Pengguna create(Pengguna pengguna) throws Exception {
        pengguna.setId(randomUUID.Random());
        int result = jdbcTemplate.update(SQL_INSERT_PENGGUNA, pengguna.getId(), pengguna.getNama(), pengguna.getEmail(), pengguna.getSaldoAwal());
        if (result<=0){
            throw new Exception("gagal menambahkan pengguna");
        }
        return pengguna;
    }

    public void delete(String id) throws Exception {
        jdbcTemplate.update(SQL_DELETE, id);
    }


    @Override
    public String toString() {
        return "PenggunaRepository{" +
                "jdbcTemplate=" + jdbcTemplate +
                '}';
    }
}
