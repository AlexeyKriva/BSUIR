package analysis.buildingmanager.repositories;

import analysis.buildingmanager.models.ParsedWord;
import analysis.buildingmanager.models.Sentence1;

import analysis.buildingmanager.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TextHullRepository {
    private JdbcTemplate jdbcTemplate;
    private SearchService searchService;

    @Autowired
    public TextHullRepository(JdbcTemplate jdbcTemplate, SearchService searchService) {
        this.jdbcTemplate = jdbcTemplate;
        this.searchService = searchService;
    }

    public List<Sentence1> select() {
        List<Sentence1> markedHull = new ArrayList<>();
        List<String> sentences = jdbcTemplate.query("SELECT sentence FROM sentences", new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getString("sentence");
            }
        });
        List<Integer> ides = jdbcTemplate.queryForList("SELECT id_s FROM sentences", Integer.class);
        for (int id: ides) {
            List<ParsedWord> parsedWords = jdbcTemplate.query("SELECT word, lemma, frequencyofoccurrence, parameters FROM words WHERE id_s=?",
                    new Object[]{id}, new BeanPropertyRowMapper<>(ParsedWord.class));
            markedHull.add(new Sentence1(sentences.get(id - 1), parsedWords));
        }

        return markedHull;
    }

    public void save(List<Sentence1> sentences) {
        for (Sentence1 sentence: sentences) {
            if (accept(sentence.getSentence())) {
                int id = selectMax();
                jdbcTemplate.update("INSERT INTO sentences VALUES(?,?)", id,
                        sentence.getSentence());
                List<ParsedWord> parsedWords = sentence.getListOfWords();
                for (ParsedWord parsedWord: parsedWords) {
                    if (!searchService.checkSigns(parsedWord.getWord())) {
                        String parameter = parsedWord.getParameters();
                        if (parameter != null) {
                            parameter = parsedWord.getParameters().toLowerCase();
                        }                        jdbcTemplate.update("INSERT INTO words VALUES(?,?,?,?,?)", id, parsedWord.getWord(),
                                parsedWord.getLemma(), parsedWord.getFrequencyOfOccurrence(), parameter);
                    }
                }
            }
        }
    }

    public void deleteAll() {
        jdbcTemplate.update("DELETE FROM sentences");
        jdbcTemplate.update("DELETE FROM words");
    }

    public void deleteNull() {
        jdbcTemplate.update("DELETE FROM words WHERE parameters IS NULL");
    }

    private boolean accept(String sentence){
        Integer id_s = null;
        try {
            id_s = jdbcTemplate.queryForObject("SELECT id_s FROM sentences WHERE sentence=?", new Object[]{sentence}, Integer.class);
            if (id_s != null) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return true;
        }
    }

    private int selectMax() {
        Integer result = jdbcTemplate.queryForObject("SELECT MAX(id_s) FROM sentences", Integer.class);
        return (result != null) ? result.intValue() + 1 : 1;
    }
}