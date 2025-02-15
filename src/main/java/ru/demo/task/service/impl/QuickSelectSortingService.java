package ru.demo.task.service.impl;

import ru.demo.task.service.SortingService;
import java.util.List;
import java.util.Random;

public class QuickSelectSortingService<T extends Comparable<T>> implements SortingService<T> {

    private final Random random = new Random();

    @Override
    public T findNthElement(List<T> list, int n) {
        if (list.isEmpty()) {
            throw new IllegalArgumentException("Список пуст.");
        }

        if (n < 1 || n > list.size()) {
            throw new IllegalArgumentException("N должно быть в пределах от 1 до размера списка");
        }

        return quickSelect(list, 0, list.size() - 1, n - 1);
    }

    private T quickSelect(List<T> nums, int left, int right, int k) {
        if (left == right) return nums.get(left);

        int pivotIndex = left + random.nextInt(right - left + 1);
        pivotIndex = partition(nums, left, right, pivotIndex);

        if (k == pivotIndex) {
            return nums.get(k);
        } else if (k < pivotIndex) {
            return quickSelect(nums, left, pivotIndex - 1, k);
        } else {
            return quickSelect(nums, pivotIndex + 1, right, k);
        }
    }

    private int partition(List<T> nums, int left, int right, int pivotIndex) {
        T pivotValue = nums.get(pivotIndex);
        swap(nums, pivotIndex, right);
        int storeIndex = left;

        for (int i = left; i < right; i++) {
            if (nums.get(i).compareTo(pivotValue) > 0) {
                swap(nums, storeIndex, i);
                storeIndex++;
            }
        }

        swap(nums, storeIndex, right);
        return storeIndex;
    }

    private void swap(List<T> nums, int i, int j) {
        T temp = nums.get(i);
        nums.set(i, nums.get(j));
        nums.set(j, temp);
    }
}