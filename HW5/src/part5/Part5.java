package part5;

import part4.Part4;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Random;
public class Part5 {
    /**
     * reverse the given array or linked list.
     * @param OriginArray  input array
     * @param reverseArray output array
     * @param option linklist or array
     * @param OriginLinkedList input array
     * @param reverseLinkedList output array
     */
    private static void reverse(Integer[] OriginArray,Integer[] reverseArray,int option,
                                LinkedList<Integer> OriginLinkedList,LinkedList<Integer> reverseLinkedList) {
        if (option == 0)
            for (int i = 0; i < OriginArray.length; i++)
                reverseArray[i] = OriginArray[OriginArray.length - 1 - i];
        else if (option == 1) {
            ListIterator<Integer> it = OriginLinkedList.listIterator(OriginLinkedList.size() - 1);
            while (it.hasPrevious()) reverseLinkedList.add(it.previous());
        }
    }
    public static void main(String args[]) {
        Random random = new Random();
        long undesire,time;
        int arraysSize[] = {50,100,1000,2000,5000,10000};
        int size = arraysSize.length;
        Integer arrays[][] = new Integer[size][];
        Integer reverse[][] = new Integer[size][];
        Integer reverse_temp[][] = new Integer[size][];
        long times[][] = new long[size][];
        String[] sort_types = {"MyMergeSort","MergeSort","QuickSort","HeapSort","InsertionSort"};
        LinkedList<LinkedList<Integer>> reverseLinkedList = new LinkedList<>();
        LinkedList<LinkedList<Integer>> merges = new LinkedList<>();

        for (int i = 0; i < size; i++) {
            arrays[i] = new Integer[arraysSize[i]];
            reverse[i] = new Integer[arraysSize[i]];
            reverse_temp[i] = new Integer[arraysSize[i]];
            merges.add(new LinkedList<>());
            reverseLinkedList.add(new LinkedList<>());
            times[i] = new long[size];
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < arraysSize[i]; j++) {
                arrays[i][j] = random.nextInt(10000);
                merges.get(i).add(arrays[i][j]);
            }
        }
        for (int i = 0; i < size; i++) {
            Part4.sorts(arrays[i],null,3);
            Part4.sorts(null,merges.get(i),0);
        }
        for (int i = 0; i < size; i++) {
            reverse(arrays[i],reverse[i],0,merges.get(i),reverseLinkedList.get(i));
            reverse(null,null,1,merges.get(i),reverseLinkedList.get(i));
        }
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < size; j++) {
                reverse_temp[j] = reverse[j].clone();
                if (i == 0) {
                    undesire = System.nanoTime();
                    Part4.sorts(null,reverseLinkedList.get(j),i);
                    time = System.nanoTime();
                    times[i][j] = (time - undesire)/1000;
                }
                else {
                    undesire = System.nanoTime();
                    Part4.sorts(reverse_temp[j],null,i);
                    time = System.nanoTime();
                    times[i][j] = (time - undesire)/1000;
                }
            }
            System.out.printf("Sort type : %s \n",sort_types[i]);
            int j = 1;
                System.out.printf("Run time(Micro seconds) of %d.array : %d , of %d.array : %d , of %d.array : %d " +
                                  ", of %d.array :%d , of  %d.array : %d\n",j, times[i][j++],j,times[i][j++],j,
                                  times[i][j++],j,times[i][j++],j,times[i][j++],j);
        }
    }
}
