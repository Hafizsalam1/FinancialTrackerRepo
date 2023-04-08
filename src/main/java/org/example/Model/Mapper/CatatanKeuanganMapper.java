package org.example.Model.Mapper;

import org.example.Model.Entity.CatatanKeuangan;
import org.example.Util.JenisKegiatan;
import org.springframework.jdbc.core.RowMapper;

import javax.swing.tree.TreePath;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CatatanKeuanganMapper implements RowMapper<CatatanKeuangan> {


    @Override
    public CatatanKeuangan mapRow(ResultSet rs, int rowNum) throws SQLException {
        CatatanKeuangan catatanKeuangan = new CatatanKeuangan();
        catatanKeuangan.setId(rs.getString("id"));
        catatanKeuangan.setNamaKegiatanFinansial(rs.getString("namaKegiatanFinansial"));
        catatanKeuangan.setDate(rs.getDate("tanggal"));
        catatanKeuangan.setBesarnya(rs.getInt("besarnya"));
        catatanKeuangan.setJenisKegiatan(JenisKegiatan.valueOf(rs.getString("pemasukan_atau_pengeluaran")));
        return catatanKeuangan;
    }
}
