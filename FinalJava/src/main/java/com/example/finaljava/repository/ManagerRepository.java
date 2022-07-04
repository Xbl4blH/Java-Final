package com.example.finaljava.repository;

import com.example.finaljava.models.Check;
import com.example.finaljava.models.Manager;
import com.example.finaljava.repository.interfaces.ManagerRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ManagerRepository implements ManagerRepositoryInterface {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Manager> getAll() {
        return jdbcTemplate.query(
                "select * from manager",
                (rs, rowNum) ->
                        new Manager(
                                rs.getInt("manager_id"),
                                rs.getString("first_name"),
                                rs.getString("last_name")
                        )
        );
    }

    @Override
    public Manager findById(int id){
        try {
            Manager manager = jdbcTemplate.queryForObject("SELECT * FROM manager WHERE manager_id=?",
                    BeanPropertyRowMapper.newInstance(Manager.class), id);
            manager.setId(id);
            return manager;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int deleteById(int id) {
        return jdbcTemplate.update("DELETE FROM manager WHERE manager_id=?", id);
    }

    @Override
    public int save(Manager manager) {
        return jdbcTemplate.update("INSERT INTO manager (first_name, last_name) VALUES(?,?)",
                new Object[] { manager.getFirstName(), manager.getLastName()});
    }

    @Override
    public int update(Manager manager) {
        return jdbcTemplate.update("UPDATE manager SET first_name=?, last_name=? WHERE manager_id=?",
                manager.getFirstName(), manager.getLastName(), manager.getId());
    }
}
