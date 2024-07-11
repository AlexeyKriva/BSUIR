package org.project.searchsystem.services;

import org.project.searchsystem.models.Question;
import org.project.searchsystem.repositories.QuestionHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;

@Service
public class OstisService {
    private final String[] HOW_MANY = {"How", "many"};

    private QuestionHistoryRepository questionHistoryRepository;

    @Autowired
    public OstisService(QuestionHistoryRepository questionHistoryRepository) {
        this.questionHistoryRepository = questionHistoryRepository;
    }

    public String getAnswerForUserQuestion(String question) {
        String agentAnswer = "";
        String[] words = question.trim().replaceAll("\\s+", " ").split(" ");
        File shFile = createFile(question);
        try {
            ProcessBuilder pb = new ProcessBuilder("/bin/bash", shFile.getAbsolutePath());

            Process process = pb.start();

            int exitCode = process.waitFor();

            System.out.println("Выполнение завершено с кодом: " + exitCode);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return agentAnswer;
    }

    private String printFileContents(File shFile) {
        String fileContent = "";
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(shFile));
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                fileContent += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return fileContent;
    }

    private File createFile(String question) {
        File shFile = new File("/Users/aliaksei/Desktop/BSUIR/CourseProject/agent.sh");
        try {
            if (shFile.exists()) {
                if (shFile.delete()) {
                    System.out.println("Yes");
                }
            }
            if (shFile.createNewFile()) {
                System.out.println("Yes");
            }
            FileWriter writer = new FileWriter(shFile.getAbsolutePath());
            writer.write("#!/bin/bash\n" +
                    "echo \"Вызов агента!\"");
            writer.close();
            return shFile;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return shFile;
    }

    public List<Question> getHistoryQuestions() {
        return questionHistoryRepository.selectQuestions();
    }
}
