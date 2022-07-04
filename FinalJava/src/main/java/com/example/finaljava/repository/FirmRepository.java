package com.example.finaljava.repository;

import com.example.finaljava.models.Check;
import com.example.finaljava.models.Customer;
import com.example.finaljava.models.Firm;
import com.example.finaljava.repository.interfaces.FirmRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class FirmRepository implements FirmRepositoryInterface {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Firm> getAll() {
        return jdbcTemplate.query(
                "select * from firm",
                (rs, rowNum) ->
                        new Firm(
                                rs.getInt("firm_id"),
                                rs.getString("name"),
                                rs.getInt("workers")
                        )
        );
    }

    @Override
    public Firm findById(int id){
        try {
            Firm firm = jdbcTemplate.queryForObject("SELECT * FROM firm WHERE firm_id=?",
                    BeanPropertyRowMapper.newInstance(Firm.class), id);
            firm.setId(id);
            return firm;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int[] batchUpdate(final List<Firm> firms) {
        int[] updateFirms = jdbcTemplate.batchUpdate(
                "update firm set name = ?, workers = ? where firm_id = ?",
                new BatchPreparedStatementSetter() {
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setString(1, firms.get(i).getName());
                        ps.setInt(2, firms.get(i).getWorkers());
                        ps.setInt(3, firms.get(i).getId());
                    }

                    public int getBatchSize() {
                        return firms.size();
                    }
                } );
        return updateFirms;
    }

    @Override
    public int save(Firm firm) {
        return jdbcTemplate.update("INSERT INTO firm (name, workers) VALUES(?,?)",
                new Object[] { firm.getName(), firm.getWorkers() });
    }
}
