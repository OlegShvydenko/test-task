package ru.demo.task.service;

import java.io.IOException;

public interface FileProcessingService {
    Integer findNthMax(String filePath, Integer n) throws IOException;
}
