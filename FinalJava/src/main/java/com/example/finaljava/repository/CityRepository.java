package com.example.finaljava.repository;

import com.example.finaljava.models.Check;
import com.example.finaljava.models.City;
import com.example.finaljava.models.PaymentCard;
import com.example.finaljava.repository.interfaces.CityRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CityRepository implements CityRepositoryInterface {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<City> getAll() {
        return jdbcTemplate.query(
                "select * from city",
                (rs, rowNum) ->
                        new City(
                                rs.getInt("city_id"),
                                rs.getString("name"),
                                rs.getDouble("coefficient"),
                                rs.getInt("population")
                        )
        );
    }

    @Override
    public City findById(int id){
        try {
            City city = jdbcTemplate.queryForObject("SELECT * FROM city WHERE city_id=?",
                    BeanPropertyRowMapper.newInstance(City.class), id);
            city.setId(id);
            return city;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int update(City city) {
        return jdbcTemplate.update("UPDATE city SET name=?, coefficient=?, population=? WHERE city_id=?",
                city.getName(), city.getCoefficient(), city.getPopulation(), city.getId());
    }
}
