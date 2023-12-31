package com.example.laboratory.dao;

import com.example.laboratory.models.Penalty;
import com.example.laboratory.models.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Array;
import java.util.Arrays;
import java.util.List;

@Component
public class PenaltyDao {
    private JdbcTemplate jdbcTemplate;
    @Autowired
    public PenaltyDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Penalty> showPenalty(){
        List<Penalty> penalty = jdbcTemplate.query("SELECT * FROM penaltyes", new BeanPropertyRowMapper<>(Penalty.class));
        return penalty;
    }

    public void save(Penalty penalty){
        jdbcTemplate.update("INSERT INTO penaltyes VALUES (?,?,?,?)", penalty.getEmailAddress(),
                penalty.getDescription(), penalty.getPenaltyNumber(), penalty.getPenaltyAppointTime());
    }
}
