package com.example.laboratory.dao;

import com.example.laboratory.models.Computing;
import com.example.laboratory.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class EmployeeDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public EmployeeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public EmployeeDao(){}

    public List<Employee> index() {
        return jdbcTemplate.query("SELECT * FROM employee", new BeanPropertyRowMapper<>(Employee.class));
    }

    public Employee showOneEmployee(int id) {
        return jdbcTemplate.query("SELECT * FROM employee WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Employee.class))
                .stream().findAny().orElse(null);
    }

    public void save(Employee employee) {
        jdbcTemplate.update("INSERT INTO employee " +
                        "VALUES(?,?,?)", employee.getPost(), employee.getUnitNumber(),
                employee.getFio());
    }

    public void update(int id, Employee employee){
        jdbcTemplate.update("UPDATE employee SET post=?, unit_number=?," +
                        "fio=? WHERE id=?",
                employee.getPost(), employee.getUnitNumber(), employee.getFio(), id);
    }

    public void delete(int id){
        jdbcTemplate.update("DELETE FROM employee WHERE id=?", id);
    }
}
