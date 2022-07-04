package com.example.finaljava.repository;

import com.example.finaljava.models.Check;
import com.example.finaljava.models.StationManager;
import com.example.finaljava.repository.interfaces.StationManagerRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StationManagerRepository implements StationManagerRepositoryInterface {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<StationManager> getAll() {
        return jdbcTemplate.query(
                "select * from stationmanager",
                (rs, rowNum) ->
                        new StationManager(
                                rs.getInt("station_manager_id"),
                                rs.getInt("station_id"),
                                rs.getInt("manager_id")
                        )
        );
    }

    @Override
    public StationManager findById(int id){
        try {
            StationManager stationManager = jdbcTemplate.queryForObject("SELECT * FROM stationmanager WHERE station_manager_id=?",
                    BeanPropertyRowMapper.newInstance(StationManager.class), id);
            stationManager.setId(id);
            return stationManager;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
