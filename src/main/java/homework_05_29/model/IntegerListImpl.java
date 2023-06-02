package homework_05_29.model;

import homework_05_29.exception.InvalidIndexException;
import homework_05_29.exception.NullItemException;

import java.util.Arrays;


public class IntegerListImpl implements IntegerList {

    private Integer[] integerList;
    private int size;
    private int counter;

    public IntegerListImpl(int size) {
        integerList = new Integer[size];
        this.size = size;
        counter = 0;
    }

    @Override
    public Integer add(Integer item) {
        checkItem(item);
        grow();
        integerList[counter] = item;
        counter++;
        return item;
    }

    @Override
    public Integer add(int index, Integer item) {
        checkItem(item);
        checkIndex(index);
        grow();
        System.arraycopy(integerList, index, integerList, index + 1, counter - index);
        integerList[index] = item;
        counter++;
        return item;
    }

    @Override
    public Integer set(int index, Integer item) {
        checkItem(item);
        checkIndex(index);
        integerList[index] = item;
        return item;
    }

    @Override
    public Integer remove(Integer item) {
        checkItem(item);
        for (int i = 0; i < counter; i++) {
            if (integerList[i].equals(item)) {
                for (int j = i; j < counter; j++) {
                    integerList[j] = integerList[j + 1];
                }
                counter--;
                return item;
            }
        }
        throw new NullItemException();
    }

    @Override
    public Integer remove(int index) {
        checkIndex(index);
        Integer item = integerList[index];
        for (int j = index; j < counter; j++) {
            integerList[j] = integerList[j + 1];
        }
        counter--;
        return item;
    }

    @Override
    public boolean contains(Integer item) {
        checkItem(item);
        return find(sort(integerList), item);
    }

    @Override
    public int indexOf(Integer item) {
        checkItem(item);
        for (int i = 0; i < counter; i++) {
            if (integerList[i].equals(item)) return i;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        checkItem(item);
        for (int i = counter - 1; i >= 0; i--) {
            if (integerList[i].equals(item)) return i;
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        checkIndex(index);
        return integerList[index];
    }

    @Override
    public boolean equals(IntegerList otherList) {
        for (int i = 0; i < counter; i++) {
            if (!integerList[i].equals(otherList.get(i))) return false;
        }
        return true;
    }

    @Override
    public int size() {
        return counter;
    }

    @Override
    public boolean isEmpty() {
        return counter == 0;
    }

    @Override
    public void clear() {
        counter = 0;
    }

    @Override
    public Integer[] toArray() {
        Integer[] newArray = new Integer[counter];
        System.arraycopy(integerList, 0, newArray, 0, counter);
        return newArray;
    }

    private void checkItem(Integer item) {
        if (item == null) {
            throw new NullItemException();
        }
    }

    private void grow() {
        if (counter == size) {
            Integer[] temp = new Integer[size];
            System.arraycopy(integerList, 0, temp, 0, size);
            size = (int) (size * 1.5);
            integerList = new Integer[size];
            System.arraycopy(temp, 0, integerList, 0, temp.length);
        }
    }

    private void checkIndex(int index) {
        if (index < 0 | index >= counter) {
            throw new InvalidIndexException();
        }
    }

    private Integer[] sort(Integer[] original) {
        Integer[] list = new Integer[counter];
        System.arraycopy(original, 0, list, 0, counter);
        quickSort(list, 0, list.length-1);
        return list;
    }

    public static void quickSort(Integer[] arr, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);
            quickSort(arr, begin, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, end);
        }
    }

    private static int partition(Integer[] arr, int begin, int end) {
        int pivot = arr[end];
        int i = (begin - 1);
        for (int j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;
                swapElements(arr, i, j);
            }
        }
        swapElements(arr, i + 1, end);
        return i + 1;
    }
    private static void swapElements(Integer[] arr, int left, int right) {
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }

    private boolean find(Integer[] list, Integer item) {
        int min = 0;
        int max = list.length - 1;
        while (min <= max) {
            int mid = (min + max) / 2;
            if (item.equals(list[mid])) {
                return true;
            }
            if (item < list[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }
}
