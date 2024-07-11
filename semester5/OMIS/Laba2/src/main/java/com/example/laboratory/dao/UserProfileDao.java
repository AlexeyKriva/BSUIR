package com.example.laboratory.dao;

import com.example.laboratory.models.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserProfileDao {
    private final String POST = "Employer";
    private JdbcTemplate jdbcTemplate;
    @Autowired
    public UserProfileDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<UserProfile> showOneEmployer(String emailAddress){
        List<UserProfile> userProfile1 = jdbcTemplate.query("SELECT * FROM user_profile WHERE emailAddress=?", new Object[]{emailAddress}, new BeanPropertyRowMapper<>(UserProfile.class));
        return userProfile1;
    }

    public boolean save(UserProfile userProfile){
        jdbcTemplate.update("INSERT INTO user_profile (fullName, education, emailAddress, birthdayDate," +
                        " post, isPassword) VALUES (?,?,?,?,?,?)", userProfile.getFullName(),
                userProfile.getEducation(), userProfile.getEmailAddress(), userProfile.getBirthdayDate(),
                userProfile.getPost(), userProfile.getIsPassword());
        jdbcTemplate.update("INSERT INTO shift_schedule VALUES(?, ARRAY['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday'], ARRAY['9:00', '9:00', '9:00', '9:00', '9:00', 'Weekend', 'Weekend'], ARRAY['15:00', '15:00', '15:00', '15:00', '15:00', 'Weekend', 'Weekend'])", userProfile.getEmailAddress());
        if (userProfile.getPost().equals(POST)) return true;
        return false;
    }

    public boolean[] check(String emailAddress, String isPassword){
        List<UserProfile> query = jdbcTemplate.query("SELECT emailAddress, post, isPassword FROM user_profile WHERE emailAddress=? AND isPassword=?", new Object[]{emailAddress, isPassword}, new BeanPropertyRowMapper<>(UserProfile.class));;
        boolean[] keys = new boolean[2];
        if (!query.isEmpty() && query.get(0).getEmailAddress().equals(emailAddress)
        && query.get(0).getIsPassword().equals(isPassword)) keys[0] = true;
        else{
            keys[0] = false;
            keys[1] = false;
        }
        if (keys[0] && query.get(0).getPost().equals(POST)) keys[1] = true;
        else keys[1] = false;
        return keys;
    }

    public List<UserProfile> findEmployees(){
        return jdbcTemplate.query("SELECT fullname, education, emailAddress, birthdaydate, post FROM user_profile WHERE post='Employee'", new BeanPropertyRowMapper<>(UserProfile.class));
    }
}
