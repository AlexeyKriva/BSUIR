package com.example.laboratory.dao;

import java.sql.*;
import java.util.*;

import com.example.laboratory.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class ComputingDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ComputingDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public ComputingDao() {}

    public List<Computing> index() {
        return jdbcTemplate.query("SELECT * FROM computing", new BeanPropertyRowMapper<>(Computing.class));
    }

    public Computing showOneComputing(int id) {
        return jdbcTemplate.query("SELECT * FROM computing WHERE is_id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Computing.class))
                .stream().findAny().orElse(null);
    }

    public void save(Computing computing) {
        jdbcTemplate.update("INSERT INTO computing " +
                        "VALUES(?,?,?,?,?,?,?)", computing.getIsId(), computing.getIsName(), computing.getModel(),
                computing.getAcquisitionDate(), computing.getPrice(), computing.getUnitNumber(),
                computing.getTransferData());
    }

    public void update(int id, Computing computing) {
        jdbcTemplate.update("UPDATE computing SET is_name=?, model=?," +
                        "acquisition_date=?, price=?, unit_number=?, transfer_data=? WHERE is_id=?",
                computing.getIsName(), computing.getModel(), computing.getAcquisitionDate(),
                computing.getPrice(), computing.getUnitNumber(), computing.getTransferData(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM computing WHERE is_id=?", id);
    }
}
