package com.example.finaljava.repository;

import com.example.finaljava.models.Check;
import com.example.finaljava.models.Customer;
import com.example.finaljava.repository.interfaces.CheckRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CheckRepository implements CheckRepositoryInterface {
    private JdbcTemplate jdbcTemplate;
    private CustomerRepository customerRepository;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate, CustomerRepository customerRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Check> getAll() {
        return jdbcTemplate.query(
                "select * from checks",
                (rs, rowNum) ->
                        new Check(
                                rs.getInt("checks_id"),
                                rs.getInt("customer_id"),
                                rs.getInt("month"),
                                rs.getInt("price")
                        )
        );
    }

    @Override
    public Check findById(int id){
        try {
            Check check = jdbcTemplate.queryForObject("SELECT * FROM checks WHERE checks_id=?",
                    BeanPropertyRowMapper.newInstance(Check.class), id);
            check.setId(id);
            return check;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int deleteById(int id) {

        return jdbcTemplate.update("DELETE FROM checks WHERE checks_id=?", id);
    }

    @Override
    public int save(Check check) {
        return jdbcTemplate.update("INSERT INTO checks (customer_id, month, price) VALUES(?,?,?)",
                new Object[] {check.getCustomerId(), check.getMonth(), check.getPrice() });
    }

    @Override
    public int[] sendMonthlyCheck() {
        List<Customer> customers = customerRepository.getAll();
        int[] updateCustomers = jdbcTemplate.batchUpdate(
                "update customer set consumption=? ",
                new BatchPreparedStatementSetter() {
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setInt(1, 0);
                    }
                    public int getBatchSize() {
                        return customers.size();
                    }
                } );
        return updateCustomers;
    }
}
