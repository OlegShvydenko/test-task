package ru.demo.task.service.impl;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import ru.demo.task.service.FileProcessingService;
import ru.demo.task.service.SortingService;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileProcessingServiceImpl implements FileProcessingService {

    private final SortingService<Integer> sortingService = new QuickSelectSortingService<>();

    @Override
    public Integer findNthMax(String filePath, Integer n) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new IllegalArgumentException("Файл не найден: " + filePath);
        }

        List<Integer> numbers = extractNumbersFromFile(file);

        if (numbers.isEmpty()) {
            throw new IllegalArgumentException("Файл пустой или не содержит чисел");
        }

        if (n < 1 || n > numbers.size()) {
            throw new IllegalArgumentException("N должно быть в пределах от 1 до размера списка");
        }

        return sortingService.findNthElement(numbers, n);
    }

    private List<Integer> extractNumbersFromFile(File file) throws IOException {
        List<Integer> numbers = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(file);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            boolean hasNumbers = false;

            for (Row row : sheet) {
                for (Cell cell : row) {
                    if (cell.getCellType() == CellType.NUMERIC) {
                        numbers.add((int) cell.getNumericCellValue());
                        hasNumbers = true;
                    }
                }
            }

            if (!hasNumbers) {
                throw new IllegalArgumentException("Файл не содержит чисел");
            }
        }

        return numbers;
    }
}