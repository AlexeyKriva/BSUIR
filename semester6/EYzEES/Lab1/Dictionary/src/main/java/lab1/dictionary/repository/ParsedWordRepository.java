package lab1.dictionary.repository;

import lab1.dictionary.models.ParsedWord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Repository
public class ParsedWordRepository {
    JdbcTemplate jdbcTemplate;

    @Autowired
    public ParsedWordRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Set<ParsedWord> scannedText){
        for (ParsedWord parsedWord: scannedText){
            if (parsedWord.getWord() == null) parsedWord.setWord("");
            if (parsedWord.getFrequencyOfOccurrence() == 0) parsedWord.setFrequencyOfOccurrence(0);
            if (parsedWord.getParameters() == null) parsedWord.setParameters("");
            if (accept(parsedWord.getWord())) jdbcTemplate.update("INSERT INTO dicts VALUES(?,?,?)", parsedWord.getWord(),parsedWord.getFrequencyOfOccurrence(), parsedWord.getParameters());
        }
    }

    private boolean accept(String parsedWord){
        List<ParsedWord> word = jdbcTemplate.query("SELECT * FROM dicts WHERE word=?", new Object[]{parsedWord}, new BeanPropertyRowMapper<>(ParsedWord.class));
        return word.size() == 0 ? true : false;
    }

    public List<ParsedWord> select(){
        return jdbcTemplate.query("SELECT * FROM dicts", new BeanPropertyRowMapper<>(ParsedWord.class));
    }

    public List<ParsedWord> selectDefiniteWithWord(String word){
        return jdbcTemplate.query("SELECT * FROM dicts WHERE word=?",new Object[]{word},  new BeanPropertyRowMapper<>(ParsedWord.class));
    }

    public List<ParsedWord> selectDefiniteWithNumber(int number){
        return jdbcTemplate.query("SELECT * FROM dicts WHERE frequency_of_occurrence=?",new Object[]{number},  new BeanPropertyRowMapper<>(ParsedWord.class));
    }
}