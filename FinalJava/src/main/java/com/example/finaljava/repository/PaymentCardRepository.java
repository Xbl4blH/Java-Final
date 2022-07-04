package com.example.finaljava.repository;

import com.example.finaljava.models.Check;
import com.example.finaljava.models.PaymentCard;
import com.example.finaljava.repository.interfaces.PaymentCardRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PaymentCardRepository implements PaymentCardRepositoryInterface {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<PaymentCard> getAll() {
        return jdbcTemplate.query(
                "select * from paymentcard",
                (rs, rowNum) ->
                        new PaymentCard(
                                rs.getInt("payment_card_id"),
                                rs.getInt("customer_id"),
                                rs.getString("number"),
                                rs.getInt("cvv"),
                                rs.getBoolean("valid_thru")
                        )
        );
    }

    @Override
    public PaymentCard findById(int id){
        try {
             PaymentCard paymentCard = jdbcTemplate.queryForObject("SELECT * FROM paymentcard WHERE payment_card_id=?",
                    BeanPropertyRowMapper.newInstance(PaymentCard.class), id);
            paymentCard.setId(id);
            return paymentCard;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int update(PaymentCard paymentCard) {
        return jdbcTemplate.update("UPDATE paymentcard SET customer_id=?, number=?, cvv=?, valid_thru=? WHERE payment_card_id=?",
                paymentCard.getCustomerId(), paymentCard.getNumber(), paymentCard.getCvv(), paymentCard.isValidThru(), paymentCard.getId());
    }
}
