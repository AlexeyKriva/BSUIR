package org.example.uploadingfilesserver.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class FindStorageService {
    @Value("${file.target-path}")
    private String targetPath;

    @Value("${file.target-directory}")
    private String targetDirectory;

    private List<File> documentsFromStorage = new ArrayList<>();

    public List<File> findAndGetDocumentsFromStorage() {
        processFilesFromFolder(new File(targetPath), false);

        return documentsFromStorage;
    }

    private void processFilesFromFolder(File storage, boolean isFind)
    {
        File[] folderEntries = storage.listFiles();
        if( folderEntries != null) {
            for (File entry : folderEntries) {
                //System.out.println(entry);
                if (entry.isDirectory()) {
                    if (entry.getName().equals(targetDirectory)) {
                        System.out.println("Найдена нужная папка: " + entry.getAbsolutePath());
                        saveDocumentsFromStorage(entry);
                        return;
                    }
                    processFilesFromFolder(entry, false);
                    if (isFind) {
                        return;
                    }
                }
            }
        }
    }

    private void saveDocumentsFromStorage(File folder) {
        File[] folderEntries = folder.listFiles();
        if (folderEntries != null) {
            for (File file : folderEntries) {
                if (file.isFile() && file.getName().endsWith(".json")) {
                    documentsFromStorage.add(file);
                    System.out.println("Добавлен файл: " + file.getAbsolutePath());
                }
            }
        }
    }
}