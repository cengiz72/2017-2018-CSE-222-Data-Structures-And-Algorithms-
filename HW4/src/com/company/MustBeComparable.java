package com.company;
import java.util.ArrayList;
public class MustBeComparable<E extends Comparable> {

    private int numberOfArgs = 0;
    private ArrayList<E> items = new ArrayList<>();
    public MustBeComparable(E... args) {
        numberOfArgs = args.length;
        for (int i = 0; i < numberOfArgs; i++)
            items.add(args[i]);

    }
    public E getItem(int index) {
        return  items.get(index);
    }
    public int getNumberOfArgs() {
        return numberOfArgs;
    }
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof MustBeComparable) {
            for (int i = 0; i < numberOfArgs; i++) {
                if (((MustBeComparable) obj).getItem(i).compareTo(getItem(i)) != 0) return false;
            }
            return true;
        }
        return false;
    }
    @Override
    public String toString() {
        String str = new String();
        str = "(";
        for (int i = 0; i < numberOfArgs - 1; i++)
            str += (getItem(i).toString() + ",");
        str += (getItem(numberOfArgs-1).toString() + ")");
        return str;
    }
}
