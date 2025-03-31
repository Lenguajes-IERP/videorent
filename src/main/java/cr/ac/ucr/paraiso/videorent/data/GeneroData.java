package cr.ac.ucr.paraiso.videorent.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import cr.ac.ucr.paraiso.videorent.domain.Genero;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class GeneroData {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Genero> findAll() {
        String selectSql = "SELECT genero_id, nombre_genero FROM Genero";
        List<Genero> generos = jdbcTemplate.query(selectSql, new GeneroRowMapper());
        return generos;
    }

    private static final class GeneroRowMapper implements RowMapper<Genero> {
        @Override
        public Genero mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Genero(rs.getInt("genero_id"), rs.getString("nombre_genero"));
        }
    }
}

