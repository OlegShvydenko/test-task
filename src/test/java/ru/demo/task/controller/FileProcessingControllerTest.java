package ru.demo.task.controller;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.stream.Stream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class FileProcessingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @ParameterizedTest
    @MethodSource("provideValidInputs")
    void testValidFileProcessingParameters(String filePath, Integer n) throws Exception {
        mockMvc.perform(get("/api/v1/file-processing/xlsx-nth-max")
                        .param("filePath", filePath)
                        .param("n", String.valueOf(n)))
                .andExpect(status().isOk());
    }

    @ParameterizedTest
    @MethodSource("provideInvalidInputs")
    void testInvalidFileProcessingParameters(String filePath, Integer n, int expectedStatus) throws Exception {
        mockMvc.perform(get("/api/v1/file-processing/xlsx-nth-max")
                        .param("filePath", filePath)
                        .param("n", String.valueOf(n)))
                .andExpect(status().is(expectedStatus));
    }

    private static Stream<Arguments> provideValidInputs() {
        return Stream.of(
                Arguments.of("src/test/resources/test_files/file_with_numbers.xlsx", 2)
        );
    }

    private static Stream<Arguments> provideInvalidInputs() {
        return Stream.of(
                Arguments.of("src/test/resources/test_files/nonexistent_file.xlsx", 2, 400),
                Arguments.of("src/test/resources/test_files/empty_file.xlsx", 1, 400),
                Arguments.of("src/test/resources/test_files/file_with_no_numbers.xlsx", 1, 400),
                Arguments.of("src/test/resources/test_files/file_with_numbers.xlsx", -1, 400),
                Arguments.of("src/test/resources/test_files/file_with_numbers.xlsx", 10, 400),
                Arguments.of(null, 2, 400),
                Arguments.of("src/test/resources/test_files/file_with_numbers.xlsx", null, 400)
        );
    }

}