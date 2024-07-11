package com.example.laboratory.dao;

import com.example.laboratory.models.Output1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Output1Dao {
    List<Output1> output1s;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public Output1Dao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public Output1Dao(){}

    public List<Output1> index(int id) {
        return jdbcTemplate.query("SELECT t1.is_id, t1.is_name, t1.model, t1.acquisition_date,\n" +
                "       t2.is_id1, t2.is_name1, t2.model1, t2.acquisition_date1,\n" +
                "       t3.full_name_unit\n" +
                "FROM computing t1\n" +
                "INNER JOIN office_equipment t2 ON t1.unit_number = t2.unit_number1\n" +
                "INNER JOIN unit t3 ON t3.unit_number = t2.unit_number1\n" +
                "WHERE t1.unit_number = ?;", new Object[]{id}, new BeanPropertyRowMapper<>(Output1.class));
    }

    public Output1 showoneResult(int id) {
        for (Output1 output1: output1s){
            if (output1.getIsId() == id){
                return output1;
            }
        }
        return null;
    }
}
