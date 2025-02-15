package ru.demo.task.service.impl;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class QuickSelectSortingServiceTest {

    private static QuickSelectSortingService<Integer> quickSelectService;

    @BeforeAll
    static void setUp() {
        quickSelectService = new QuickSelectSortingService<>();
    }

    @Test
    void testFindNthElementWithValidData() {
        List<Integer> numbers = Arrays.asList(10, 1, 7, 3, 5);
        Integer result = quickSelectService.findNthElement(numbers, 3);
        assertEquals(5, result, "Третий по величине элемент должен быть равен 5");
    }

    @Test
    void testFindNthElementWithNGreaterThanListSize() {
        List<Integer> numbers = Arrays.asList(10, 1, 7, 3, 5);
        assertThrows(IllegalArgumentException.class, () ->
                        quickSelectService.findNthElement(numbers, 10),
                "Должно быть исключение, если N больше размера списка");
    }

    @Test
    void testFindNthElementWithEmptyList() {
        List<Integer> numbers = new ArrayList<>();
        assertThrows(IllegalArgumentException.class, () ->
                        quickSelectService.findNthElement(numbers, 1),
                "Должно быть исключение, если список пуст");
    }

    @Test
    void testFindNthElementWithSingleElement() {
        List<Integer> numbers = List.of(10);
        Integer result = quickSelectService.findNthElement(numbers, 1);
        assertEquals(10, result, "Должен быть возвращен единственный элемент в списке");
    }

    @Test
    void testFindNthElementWithNullValues() {
        List<Integer> numbers = Arrays.asList(10, null, 20, 5, null);
        assertThrows(NullPointerException.class, () ->
                        quickSelectService.findNthElement(numbers, 3),
                "Должно быть исключение, если в списке есть null значения");
    }

    @Test
    void testFindNthElementWithSortedList() {
        List<Integer> sortedList = Arrays.asList(1, 2, 3, 4, 5);
        Integer result = quickSelectService.findNthElement(sortedList, 3);
        assertEquals(3, result, "Третий по величине элемент должен быть равен 3");
    }

    @Test
    void testFindNthElementWithReverseSortedList() {
        List<Integer> reverseSortedList = Arrays.asList(5, 4, 3, 2, 1);
        Integer result = quickSelectService.findNthElement(reverseSortedList, 3);
        assertEquals(3, result, "Третий по величине элемент должен быть равен 3");
    }

    @Test
    void testFindNthElementWithDuplicates() {
        List<Integer> listWithDuplicates = Arrays.asList(1, 2, 2, 3, 4, 5, 5, 5);
        Integer result = quickSelectService.findNthElement(listWithDuplicates, 4);
        assertEquals(4, result, "Четвертый по величине элемент должен быть равен 4");
    }

    @Test
    void testFindNthElementWithAllEqualValues() {
        List<Integer> equalList = Arrays.asList(5, 5, 5, 5, 5);
        Integer result = quickSelectService.findNthElement(equalList, 3);
        assertEquals(5, result, "Любой элемент должен быть равен 5, так как все элементы одинаковые");
    }

    @Test
    void testFindNthElementWithLargeList() {
        List<Integer> largeList = new ArrayList<>();
        for (int i = 1; i <= 1000000; i++) {
            largeList.add(i);
        }
        Integer result = quickSelectService.findNthElement(largeList, 500000);
        assertEquals(500001, result, "500000-й по величине элемент должен быть равен 500001");
    }
}
