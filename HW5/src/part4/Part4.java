package part4;

import sort.*;

import java.util.LinkedList;
import java.util.Random;

public class Part4 {
    /**
     * call one of sorts algorithms according to option
     * @param array input for other sort algorithms except dll.
     * @param mergeSort input for merge sort wit dll
     * @param option determine which sort algorithm is working
     */
    public static void sorts(Integer[] array,LinkedList<Integer> mergeSort,int option) {
        switch (option) {
            case 0: MyMergeSort.sort(mergeSort,0,mergeSort.size() -1);
                    break;
            case 1: MergeSort.sort(array);
                    break;
            case 2: QuickSort.sort(array);
                    break;
            case 3: HeapSort.sort(array);
                    break;
            case 4: InsertionSort.sort(array);
                    break;
        }
    }
    public static void main(String args[]) {
        Random random = new Random();
        System.out.println("outputlar ekrana biraz gec gelebilir.");
        long undesire,time;
        long avarage;
        int arraysSize[] = {10,20,40,100,200,400,800,1600,3200,4000,7000,10000};
        int size = arraysSize.length;
        Integer arrays[][] = new Integer[size][];
        Integer temps[][] = new Integer[size][];
        long times[][][] = new long[5][12][];
        String[] sort_types = {"MyMergeSort","MergeSort","QuickSort","HeapSort","InsertionSort"};
        LinkedList<Integer> tempLinkedList = new LinkedList<>();
        LinkedList<LinkedList<Integer>> merges = new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 12; j++)
                times[i][j] = new long[12];
        }
        for (int i = 0; i < arraysSize.length; i++) {
            arrays[i] = new Integer[arraysSize[i]];
            temps[i] = new Integer[arraysSize[i]];
            merges.add(new LinkedList<>());

        }
        for (int i = 0; i < arraysSize.length; i++) {
            for (int j = 0; j < arraysSize[i]; j++) {
                arrays[i][j] = random.nextInt(10000);
                merges.get(i).add(arrays[i][j]);
            }
        }
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < size; j++) {
                if (i == 0) {
                    for (int k = 0; k < 12; k++) {
                        tempLinkedList.addAll(merges.get(j));
                        undesire = System.nanoTime();
                        sorts(null,tempLinkedList,i);
                        time = System.nanoTime();
                        times[i][j][k] = (time - undesire)/(1000);
                        tempLinkedList.clear();
                    }
                }
                else{
                    for (int k = 0; k < 12; k++) {
                        temps[j] = arrays[j].clone();
                        undesire = System.nanoTime();
                        sorts(temps[j],null,i);
                        time = System.nanoTime();
                        times[i][j][k] = (time - undesire)/(1000);
                    }
                }
            }
        }

        for (int i = 0; i < 5; i++) {
            System.out.printf("Sort type : %s \n",sort_types[i]);
            for (int j = 0; j < size; j++) {
                int k = 1;
                avarage = 0;
                System.out.printf(" Run time(Micro seconds) of %d.array : %d %d %d %d %d %d %d %d %d %d %d\n",j+1,
                        times[i][j][k++],times[i][j][k++],times[i][j][k++],times[i][j][k++],
                        times[i][j][k++],times[i][j][k++],times[i][j][k++],times[i][j][k++],
                        times[i][j][k++],times[i][j][k++],times[i][j][k]);
                k = 1;
                avarage = (times[i][j][k++] + times[i][j][k++] + times[i][j][k++] + times[i][j][k++] +
                           times[i][j][k++] + times[i][j][k++] + times[i][j][k++] + times[i][j][k++] +
                           times[i][j][k++] + times[i][j][k++] + times[i][j][k])/(size - 1);
                System.out.printf(" Average Time : %d\n",avarage);


            }
        }
    }

}
