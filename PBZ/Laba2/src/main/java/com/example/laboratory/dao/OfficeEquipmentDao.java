package com.example.laboratory.dao;

import com.example.laboratory.models.OfficeEquipment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OfficeEquipmentDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public OfficeEquipmentDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public OfficeEquipmentDao(){}


    public List<OfficeEquipment> index() {
        return jdbcTemplate.query("SELECT * FROM office_equipment", new BeanPropertyRowMapper<>(OfficeEquipment.class));
    }

    public OfficeEquipment showOneOfficeEquipment(int id) {
        return jdbcTemplate.query("SELECT * FROM office_equipment WHERE is_id1=?", new Object[]{id}, new BeanPropertyRowMapper<>(OfficeEquipment.class))
                .stream().findAny().orElse(null);
    }

    public void save(OfficeEquipment officeEquipment) {
        jdbcTemplate.update("INSERT INTO office_equipment " +
                        "VALUES(?,?,?,?,?,?,?)", officeEquipment.getIsId1(), officeEquipment.getIsName1(), officeEquipment.getPrice1(),
                officeEquipment.getAcquisitionDate1(), officeEquipment.getPrice1(), officeEquipment.getUnitNumber1(),
                officeEquipment.getTransferData1());
    }

    public void update(int id, OfficeEquipment officeEquipment){
        jdbcTemplate.update("UPDATE office_equipment SET is_name1=?, model1=?," +
                        "acquisition_date1=?, price1=?, unit_number1=?, transfer_data1=? WHERE is_id1=?",
                officeEquipment.getIsName1(), officeEquipment.getModel1(), officeEquipment.getAcquisitionDate1(),
                officeEquipment.getPrice1(), officeEquipment.getUnitNumber1(), officeEquipment.getTransferData1(), id);
    }

    public void delete(int id){
        jdbcTemplate.update("DELETE FROM office_equipment WHERE is_id1=?", id);
    }
}
