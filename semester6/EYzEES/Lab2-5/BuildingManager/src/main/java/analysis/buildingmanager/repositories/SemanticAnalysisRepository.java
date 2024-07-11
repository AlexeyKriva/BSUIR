package analysis.buildingmanager.repositories;

import analysis.buildingmanager.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SemanticAnalysisRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public SemanticAnalysisRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveNamedEntity(List<NamedEntity> namedEntities) {
        for (NamedEntity namedEntity: namedEntities) {
            jdbcTemplate.update("INSERT INTO named_entity VALUES (" +
                    "?,?)", namedEntity.getType(), namedEntity.getValue());
        }
    }

    public void saveRelationInformation(List<RelationInformation> relationInformations) {
        for (RelationInformation relationInformation: relationInformations) {
            jdbcTemplate.update("INSERT INTO relation_information VALUES (" +
                    "?,?,?,?)", String.valueOf(relationInformation.getConfidence()), relationInformation.getSubject(),
                    relationInformation.getRelation(), relationInformation.getObject());
        }
    }

    public void saveSemanticAnaphora(SemanticAnaphora semanticAnaphora) {
        for (int i = 0; i < semanticAnaphora.getNamedEntities().size(); i++) {
            for (int j = 0; j < semanticAnaphora.getNamedEntities().get(i).size(); j++)
            jdbcTemplate.update("INSERT INTO semantic_anaphora VALUES (" +
                            "?,?)", semanticAnaphora.getNamedEntities().get(i).get(j),
                    String.valueOf(semanticAnaphora.getNumbersOfFirstSentence().get(i).get(j)));
        }
    }

    public void deleteAll() {
        jdbcTemplate.update("DELETE FROM named_entity;" +
                "DELETE FROM relation_information;" +
                "DELETE FROM semantic_anaphora;");
    }

    public List<NamedEntity> getNamedEntity() {
        return jdbcTemplate.query("SELECT * FROM named_entity", new BeanPropertyRowMapper<>(NamedEntity.class));
    }

    public List<RelationInformation> getRelationInformation() {
        return jdbcTemplate.query("SELECT * FROM relation_information", new BeanPropertyRowMapper<>(RelationInformation.class));
    }

    public List<SimpleSemanticAnaphora> getSimpleSemanticAnaphora() {
        return jdbcTemplate.query("SELECT * FROM semantic_anaphora", new BeanPropertyRowMapper<>(SimpleSemanticAnaphora.class));
    }

    public List<NamedEntity> getNamedEntityWithParameters(String parameter) {
        return jdbcTemplate.query("SELECT * FROM named_entity WHERE type=? OR value=?", new Object[]{parameter, parameter}, new BeanPropertyRowMapper<>(NamedEntity.class));
    }

    public List<RelationInformation> getRelationInformationWithParameters(String parameter) {
        return jdbcTemplate.query("SELECT * FROM relation_information WHERE confidence=? OR subject=? OR relation=? OR object=?", new Object[]{parameter, parameter, parameter, parameter}, new BeanPropertyRowMapper<>(RelationInformation.class));
    }

    public List<SimpleSemanticAnaphora> getSimpleSemanticAnaphoraWithParameters(String parameter) {
        return jdbcTemplate.query("SELECT * FROM semantic_anaphora WHERE named_entity=? OR number_of_sentence=?", new Object[]{parameter, parameter}, new BeanPropertyRowMapper<>(SimpleSemanticAnaphora.class));
    }
}
