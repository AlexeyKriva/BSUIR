package analysis.buildingmanager.repositories;

import analysis.buildingmanager.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SentencesAnalysisRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public SentencesAnalysisRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(List<VertexOfAnalysis> vertexOfAnalyses, String sentence) {
        List<Integer> idS = jdbcTemplate.queryForList("SELECT id_s FROM sentences WHERE sentence=?", new Object[]{sentence}, Integer.class);
        if (accept(idS.get(0))) {
            for (VertexOfAnalysis vertexOfAnalysis : vertexOfAnalyses) {
                jdbcTemplate.update("INSERT INTO sentence_analysis VALUES(?,?)", idS.get(0),
                        vertexOfAnalysis.getWord());
                List<Pair> pairs = vertexOfAnalysis.getRelations();
                for (Pair pair : pairs) {
                    jdbcTemplate.update("INSERT INTO pairs VALUES(?,?,?,?)", vertexOfAnalysis.getWord(), pair.getWord(), pair.getRelation(), idS.get(0));
                }
            }
        }
    }

    private boolean accept(Integer id) {
        List<Integer> ids = jdbcTemplate.queryForList("SELECT id_s FROM sentence_analysis WHERE id_s=?", new Object[]{id}, Integer.class);
        return ids.isEmpty();
    }

    public List<SentenceAnalysis> select() {
        List<SentenceAnalysis> sentenceAnalyses = jdbcTemplate.query("SELECT sentence, word, word1, relation\n" +
                "FROM pairs\n" +
                "JOIN sentences ON pairs.id_s=sentences.id_s", new BeanPropertyRowMapper<>(SentenceAnalysis.class));
        return sentenceAnalyses;
    }

    public void deleteAll() {
        jdbcTemplate.update("DELETE FROM sentence_analysis; DELETE FROM pairs");
    }
}
