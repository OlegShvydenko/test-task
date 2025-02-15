package ru.demo.task.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.demo.task.service.FileProcessingService;

import java.io.IOException;

@Validated
@RestController
@RequestMapping("/api/v1/file-processing")
public class FileProcessingController {

    private final FileProcessingService fileProcessingService;

    public FileProcessingController(FileProcessingService fileProcessingService) {
        this.fileProcessingService = fileProcessingService;
    }

    @GetMapping("/xlsx-nth-max")
    @Operation(summary = "Возвращает N-ое максимальное число из XLSX файла",
            description = "Принимает путь к файлу и число N, возвращает N-ое максимальное число из файла.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешный запрос"),
            @ApiResponse(responseCode = "400", description = "Некорректные параметры запроса"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера")
    })
    public Integer getNthMax(@RequestParam @NotBlank String filePath,
                             @RequestParam @NotNull Integer n) throws IOException {
        return fileProcessingService.findNthMax(filePath, n);
    }
}