package com.example.laboratory.dao;

import com.example.laboratory.models.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UnitDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UnitDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public UnitDao(){}

    public List<Unit> index() {
        return jdbcTemplate.query("SELECT * FROM unit", new BeanPropertyRowMapper<>(Unit.class));
    }

    public Unit showOneUnit(int id) {
        return jdbcTemplate.query("SELECT * FROM unit WHERE unit_number=?", new Object[]{id}, new BeanPropertyRowMapper<>(Unit.class))
                .stream().findAny().orElse(null);
    }

    public void save(Unit unit) {
        jdbcTemplate.update("INSERT INTO unit " +
                        "VALUES(?,?,?)", unit.getUnitNumber(), unit.getFullNameUnit(), unit.getShortNameUnit());
    }

    public void update(int id, Unit unit){
        jdbcTemplate.update("UPDATE unit SET full_name_unit=?, short_name_unit=? WHERE unit_number=?",
                unit.getFullNameUnit(), unit.getShortNameUnit(), id);
    }

    public void delete(int id){
        jdbcTemplate.update("DELETE FROM unit WHERE unit_number=?", id);
    }
}
