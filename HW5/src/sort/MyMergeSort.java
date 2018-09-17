package sort;
import java.util.LinkedList;
import java.util.Random;

public class MyMergeSort {
    /**
     * function generate numbers between 0 and 10000 ,and add them to linked list.
     * @param arr hold the generated numbers
     * @param size number of generated numbers
     */
    public static void RandomGenerator(LinkedList<Integer> arr, int size) {
            Random random = new Random();
            for (int i = 0; i < size; i++) {
                arr.add(random.nextInt(10000));
            }
    }

    /**
     * function sort and merge left and right sub array
     * @param arr linked list that will be merged.
     * @param left left sub array
     * @param middlePoint middle point of linked list
     * @param right right sub array
     */
    public static void merge (LinkedList<Integer> arr, int left, int middlePoint, int right)
    {
        int L = middlePoint - left + 1;
        int R = right - middlePoint;

        LinkedList<Integer> Left = new LinkedList<>();
        LinkedList<Integer> Right = new LinkedList<>();

        for (int i=0; i<L; ++i)
            Left.add(arr.get(left + i));
        for (int j=0; j<R; ++j)
            Right.add(arr.get(middlePoint + 1 + j));

        int i = 0, j = 0;
        int k = left;
        while (i < L && j < R)
        {
            if (Left.get(i).compareTo(Right.get(j)) <= 0) {
                arr.set(k,Left.get(i));
                i++;
            }
            else {

                arr.set(k,Right.get(j));
                j++;
            }
            k++;
        }
        while (i < L)
        {
            arr.set(k,Left.get(i));
            i++;
            k++;
        }

        while (j < R)
        {
            arr.set(k,Right.get(j));
            j++;
            k++;
        }
    }

    /**
     * this function divide the array two parts named left and right sub array
     * ,and then merge and sort them.
     * @param arr whole array
     * @param left size of left sub array
     * @param right size of right sub array
     */
    public static  void sort(LinkedList<Integer> arr, int left, int right) {
        if (left < right)
        {
            int middlePoint = (left+right)/2;

            sort(arr, left, middlePoint);
            sort(arr , middlePoint+1, right);

            merge(arr, left, middlePoint, right);
        }
    }

    /**
     *  print the all elements of linked list.
     * @param arr linked list which all elements are printed.
     */
    public static void print(LinkedList<Integer> arr) {

        System.out.println();
        for (int i = 0; i < arr.size(); i++) {
            System.out.printf("%d",arr.get(i));
            System.out.print(" ");
        }
        System.out.println();
    }
}
