package ru.demo.task.service;

import java.util.List;

public interface SortingService<T extends Comparable<T>> {
    T findNthElement(List<T> list, int n);
}