package com.example.laboratory.dao;

import com.example.laboratory.models.Output2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Output2Dao {
    List<Output2> output2s;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public Output2Dao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public Output2Dao(){}

    public List<Output2> index(int id) {
        return jdbcTemplate.query("SELECT t1.is_id, t1.is_name, t1.model, t1.acquisition_date,\n" +
                "       t2.is_id1, t2.is_name1, t2.model1, t2.acquisition_date1,\n" +
                "       t3.full_name_unit\n" +
                "FROM computing t1\n" +
                "INNER JOIN office_equipment t2 ON t1.unit_number = t2.unit_number1\n" +
                "INNER JOIN unit t3 ON t3.unit_number = t2.unit_number1\n" +
                "WHERE t1.unit_number = ?;", new Object[]{id}, new BeanPropertyRowMapper<>(Output2.class));
    }

    public Output2 showoneResult(int id) {
        for (Output2 output2: output2s){
            if (output2.getIsId() == id){
                return output2;
            }
        }
        return null;
    }
}
