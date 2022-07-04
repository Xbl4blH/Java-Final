package com.example.finaljava.repository;

import com.example.finaljava.models.Station;
import com.example.finaljava.models.StationManager;
import com.example.finaljava.models.StationType;
import com.example.finaljava.repository.interfaces.StationTypeRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StationTypeRepository implements StationTypeRepositoryInterface {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<StationType> getAll() {
        return jdbcTemplate.query(
                "select * from stationtype",
                (rs, rowNum) ->
                        new StationType(
                                rs.getInt("station_type_id"),
                                rs.getString("name"),
                                rs.getInt("price")
                        )
        );
    }

    @Override
    public StationType findById(int id){
        try {
            StationType stationType = jdbcTemplate.queryForObject("SELECT * FROM stationType WHERE station_type_id=?",
                    BeanPropertyRowMapper.newInstance(StationType.class), id);
            stationType.setId(id);
            return stationType;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
