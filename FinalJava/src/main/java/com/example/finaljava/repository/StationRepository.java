package com.example.finaljava.repository;

import com.example.finaljava.models.Manager;
import com.example.finaljava.models.Station;
import com.example.finaljava.models.StationManager;
import com.example.finaljava.repository.interfaces.StationRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Repository
public class StationRepository implements StationRepositoryInterface {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Station> getAll() {
        return jdbcTemplate.query(
                "select * from station",
                (rs, rowNum) ->
                        new Station(
                                rs.getInt("station_id"),
                                rs.getInt("firm_id"),
                                rs.getInt("station_type_id"),
                                rs.getInt("max_performance"),
                                rs.getBoolean("is_active")
                        )
        );
    }

    @Override
    public Station findById(int id){
        try {
            Station station = jdbcTemplate.queryForObject("SELECT * FROM station WHERE station_id=?",
                    BeanPropertyRowMapper.newInstance(Station.class), id);
            station.setId(id);
            return station;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int save(Station station) {
        return jdbcTemplate.update("INSERT INTO station (firm_id, station_type_id, max_performance, is_active) VALUES(?,?,?,?)",
                station.getFirmId(), station.getStationTypeId(), station.getMaxPerformance(), station.isActive());
    }

    public Map<Station, Manager> getAllManagersWithStations(){
        List<Station>  stations = jdbcTemplate.query("select st.*, mn.* from station st inner join stationManager stm on st.station_id=stm.station_id \n" +
                "inner join manager mn on stm.manager_id = mn.manager_id"
                ,(rs, rowNum) ->
                        new Station(
                                rs.getInt("station_id"),
                                rs.getInt("firm_id"),
                                rs.getInt("station_type_id"),
                                rs.getInt("max_performance"),
                                rs.getBoolean("is_active")
                        ));
        List<Manager> managers = jdbcTemplate.query("select st.*, mn.* from station st inner join stationManager stm on st.station_id=stm.station_id \n" +
                        "inner join manager mn on stm.manager_id = mn.manager_id"
                ,(rs, rowNum) ->
                        new Manager(
                                rs.getInt("manager_id"),
                                rs.getString("first_name"),
                                rs.getString("last_name")
                        ));
        Iterator<Station> keyIter = stations.iterator();
        Iterator<Manager> valIter = managers.iterator();
        return IntStream.range(0, stations.size()).boxed()
                .collect(Collectors.toMap(_i -> keyIter.next(), _i -> valIter.next()));
    }
}
