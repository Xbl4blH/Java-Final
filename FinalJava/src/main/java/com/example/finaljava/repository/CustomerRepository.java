package com.example.finaljava.repository;

import com.example.finaljava.models.Check;
import com.example.finaljava.models.Customer;
import com.example.finaljava.repository.interfaces.CustomerRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestHeader;

//import javax.persistence.criteria.Order;
//import java.awt.print.Pageable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class CustomerRepository implements CustomerRepositoryInterface {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Customer> getAll() {
        return jdbcTemplate.query(
                "select * from customer",
                (rs, rowNum) ->
                        new Customer(
                                rs.getInt("customer_id"),
                                rs.getInt("station_id"),
                                rs.getInt("city_id"),
                                rs.getString("first_name"),
                                rs.getString("last_name"),
                                rs.getInt("consumption")
                        )
        );
    }

//    @Override
//    public List<Customer> getAll(Pageable page) {
//        return null;
//    }

    @Override
    public Page<Customer> getAllPaging(Pageable page) {
        Sort.Order order = !page.getSort().isEmpty() ? page.getSort().toList().get(0) : Sort.Order.by("customer_id");

        List<Customer> customers = jdbcTemplate.query("SELECT * FROM CUSTOMER ORDER BY " + order.getProperty() + " "
                        + order.getDirection().name() + " LIMIT " + page.getPageSize() + " OFFSET " + page.getOffset(),
                (rs, rowNum) ->  new Customer(
                        rs.getInt("customer_id"),
                        rs.getInt("station_id"),
                        rs.getInt("city_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getInt("consumption")
                ));
        return new PageImpl<Customer>(customers, page, getAll().size());
    }

    @Override
    public Customer findById(int id){
        try {
            Customer customer= jdbcTemplate.queryForObject("SELECT * FROM customer WHERE customer_id=?",
                    BeanPropertyRowMapper.newInstance(Customer.class), id);
            customer.setId(id);
            return customer;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int deleteById(int id) {
        return jdbcTemplate.update("DELETE FROM customer WHERE customer_id=?", id);
    }

    @Override
    public int save(Customer customer) {
        return jdbcTemplate.update("INSERT INTO customer (station_id, city_id, first_name, last_name, consumption) VALUES(?,?,?,?,?)",
                new Object[] { customer.getStationId(), customer.getCityId(), customer.getFirstName(), customer.getLastName(), customer.getConsumption() });
    }

    @Override
    public int update(Customer customer) {
        return jdbcTemplate.update("UPDATE customer SET station_id=?, city_id=?, first_name=?, last_name=? WHERE customer_id=?",
                customer.getStationId(), customer.getCityId(), customer.getFirstName(), customer.getLastName(), customer.getId());
    }
}
