package org.project.searchsystem.repositories;

import jakarta.persistence.EntityManager;
import org.project.searchsystem.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UserRepository {
    private EntityManager entityManager;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepository(EntityManager entityManager, JdbcTemplate jdbcTemplate) {
        this.entityManager = entityManager;
        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveUser(User user) {
        entityManager.persist(user);
    }

    public boolean isUserExist(User user) {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM users WHERE email=? AND password=?",
                Integer.class, user.getEmail(), user.getPassword()) > 0;
    }
}
