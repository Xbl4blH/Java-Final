package com.example.finaljava.repository;

import com.example.finaljava.models.Firm;
import com.example.finaljava.models.TelephoneNumber;
import com.example.finaljava.repository.interfaces.TelephoneNumberRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TelephoneNumberRepository implements TelephoneNumberRepositoryInterface {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<TelephoneNumber> getAll() {
        return jdbcTemplate.query(
                "select * from telephonenumber",
                (rs, rowNum) ->
                        new TelephoneNumber(
                                rs.getInt("telephone_number_id"),
                                rs.getInt("manager_id"),
                                rs.getString("number"),
                                rs.getString("operator")
                        )
        );
    }

    @Override
    public TelephoneNumber findById(int id){
        try {
            TelephoneNumber telephoneNumber = jdbcTemplate.queryForObject("SELECT * FROM telephonenumber WHERE telephone_number_id=?",
                    BeanPropertyRowMapper.newInstance(TelephoneNumber.class), id);
            telephoneNumber.setId(id);
            return telephoneNumber;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int deleteById(int id) {
        return jdbcTemplate.update("DELETE FROM telephonenumber WHERE telephone_number_id=?", id);
    }

    @Override
    public int save(TelephoneNumber telephoneNumber) {
        return jdbcTemplate.update("INSERT INTO telephonenumber (manager_id, number, operator) VALUES(?,?,?)",
                new Object[] { telephoneNumber.getManagerId(), telephoneNumber.getNumber(), telephoneNumber.getOperator()});
    }

    @Override
    public int update(TelephoneNumber telephoneNumber) {
        try {
            return jdbcTemplate.update("UPDATE telephonenumber SET manager_id=?, number=?, operator=? WHERE telephone_number_id=?",
                    telephoneNumber.getManagerId(), telephoneNumber.getNumber(), telephoneNumber.getOperator(), telephoneNumber.getId());
        } catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }
}
