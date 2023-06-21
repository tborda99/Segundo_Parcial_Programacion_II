package Entities;

import uy.edu.um.adt.heap.MyHeapImpl;

public class HeapSortImpl<T extends Comparable<T>> {

    /**
     * Dado un Array, lo ordena usando Heap Sort.
     */
    public static <T extends Comparable<T>> void sort(T[] array) {
        MyHeapImpl<T> heap = new MyHeapImpl<>(array, false); // Crear un max heap using MyHeapImpl
        for (int i = array.length - 1; i >= 0; i--) {
            array[i] = heap.delete(); // Delete el maximum del heap and guardarlo en el array
        }
    }

}
