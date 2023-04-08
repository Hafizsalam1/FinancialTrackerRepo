package org.example.Model.Mapper;

import org.example.Model.Entity.CatatanKeuangan;
import org.example.Model.Entity.Pengguna;
import org.example.Util.JenisKegiatan;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PenggunaMapper implements RowMapper<Pengguna> {

    @Override
    public Pengguna mapRow(ResultSet rs, int rowNum) throws SQLException {
        Pengguna pengguna = new Pengguna();
        pengguna.setId(rs.getString("id"));
        pengguna.setNama(rs.getString("nama"));
        pengguna.setEmail(rs.getString("email"));
        pengguna.setSaldoAwal(rs.getInt("saldo"));
        return pengguna;
    }
}
