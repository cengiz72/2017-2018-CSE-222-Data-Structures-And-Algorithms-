package part3;

import sort.MyMergeSort;

import java.util.LinkedList;

public class Part3 {
    public static void main(String[] args) {
        LinkedList<Integer> arr = new LinkedList<>();
        LinkedList<Integer> arr1 = new LinkedList<>();
        MyMergeSort.RandomGenerator(arr,15);
        System.out.println("Unsorted Array:");
        MyMergeSort.print(arr);
        System.out.println();
        MyMergeSort.sort(arr,0,arr.size() - 1);
        System.out.println("Sorted Array:");
        MyMergeSort.print(arr);
        System.out.println();
        MyMergeSort.RandomGenerator(arr1,24);
        System.out.println("Unsorted Array:");
        MyMergeSort.print(arr1);
        System.out.println();
        MyMergeSort.sort(arr1,0,arr1.size() - 1);
        System.out.println("Sorted Array:");
        MyMergeSort.print(arr1);
        System.out.println();
    }
}
