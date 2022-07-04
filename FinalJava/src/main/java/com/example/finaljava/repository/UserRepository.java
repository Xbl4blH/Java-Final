package com.example.finaljava.repository;

import com.example.finaljava.models.TelephoneNumber;
import com.example.finaljava.models.User;
import com.example.finaljava.repository.interfaces.UserRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository implements UserRepositoryInterface {
    JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User findByLogin(String login){
        try {
            User user= jdbcTemplate.queryForObject("SELECT * FROM users WHERE login=?",
                    BeanPropertyRowMapper.newInstance(User.class), login);
            user.setLogin(login);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
