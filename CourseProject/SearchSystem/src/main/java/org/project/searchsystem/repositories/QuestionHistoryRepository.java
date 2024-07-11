package org.project.searchsystem.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.project.searchsystem.models.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuestionHistoryRepository {
    private EntityManager entityManager;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public QuestionHistoryRepository(EntityManager entityManager, JdbcTemplate jdbcTemplate) {
        this.entityManager = entityManager;
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Question> selectQuestions() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Question> criteriaQuery = criteriaBuilder.createQuery(Question.class);
        Root<Question> root = criteriaQuery.from(Question.class);
        criteriaQuery.orderBy(criteriaBuilder.desc(root.get("id"))); // Сортировка по убыванию id
        return entityManager.createQuery(criteriaQuery)
                .setMaxResults(10) // Устанавливаем максимальное количество результатов
                .getResultList();
    }

    public void saveQuestion(String request, String response) {
        jdbcTemplate.update("INSERT INTO question_history (question, response) VALUES (?,?)", request, response);
    }
}
