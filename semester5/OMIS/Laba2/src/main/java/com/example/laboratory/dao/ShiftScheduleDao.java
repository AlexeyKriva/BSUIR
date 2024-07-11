package com.example.laboratory.dao;

import com.example.laboratory.models.HelpShiftSchedule;
import com.example.laboratory.models.ShiftSchedule;
import com.example.laboratory.models.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Array;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

@Component
public class ShiftScheduleDao {
    private JdbcTemplate jdbcTemplate;
    @Autowired
    public ShiftScheduleDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<ShiftSchedule> showShiftSchedule(String emailAddress){
        List<ShiftSchedule> shiftSchedule = jdbcTemplate.query(
                "SELECT * FROM shift_schedule WHERE emailAddress = ?",
                new Object[]{emailAddress},
                (resultSet, rowNum) -> {
                    ShiftSchedule schedule = new ShiftSchedule();
                    Array workDaysArray = resultSet.getArray("shiftschedule");
                    Array startTimeArray = resultSet.getArray("starttime");
                    Array finishTimeArray = resultSet.getArray("finishtime");

                    // Преобразование массивов из базы данных в List<String>
                    schedule.setWorkDays(Arrays.asList((String[]) workDaysArray.getArray()));
                    schedule.setStartTime(Arrays.asList((String[]) startTimeArray.getArray()));
                    schedule.setFinishTime(Arrays.asList((String[]) finishTimeArray.getArray()));

                    return schedule;
                }
        );
        return shiftSchedule;
    }

    public void save(HelpShiftSchedule shiftSchedule){
        String workDays = shiftSchedule.getWorkDays();
        String startTime = shiftSchedule.getStartTime();
        String finishTime = shiftSchedule.getFinishTime();
        System.out.println("1");
        String[] daysArray = workDays.split(", "); // Разделяем строку по запятой с пробелом

        StringJoiner arrayJoiner = new StringJoiner(", ", "", "");

        for (String day : daysArray) {
            arrayJoiner.add("'" + day + "'");
        }
        System.out.println(arrayJoiner);
        String[] daysArray1 = startTime.split(", "); // Разделяем строку по запятой с пробелом

        StringJoiner arrayJoiner1 = new StringJoiner(", ", "", "");

        for (String day : daysArray1) {
            arrayJoiner1.add("'" + day + "'");
        }
        System.out.println(arrayJoiner1);
        String[] daysArray2 = finishTime.split(", "); // Разделяем строку по запятой с пробелом

        StringJoiner arrayJoiner2 = new StringJoiner(", ", "", "");

        for (String day : daysArray2) {
            arrayJoiner2.add("'" + day + "'");
        }
        System.out.println(arrayJoiner2);
        jdbcTemplate.update("DELETE FROM shift_schedule; INSERT INTO shift_schedule VALUES(?,?,?,?)", new Object[]{shiftSchedule.getEmailAddress(),arrayJoiner.toString(), arrayJoiner1.toString(), arrayJoiner2.toString()});
    }
}
