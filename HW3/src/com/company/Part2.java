package com.company;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @author Cengiz Toprak
 * @param <E>
 */
public class Part2<E> extends LinkedList<E>{

    private ArrayList<Integer> disables;
    public  Part2() {
       disables = new ArrayList<>();
    }

    /**
     * This function make disable item from some function
     * like set,get,remove and size.
     * @param item element that will be made disable.
     * @return true if item has been disable ,if not ,return false;
     */
    public  boolean disable(E item) {
        if (disables.size() == 0) {
            for (int i = 0; i < super.size(); i++) {
                if (super.get(i).equals(item)) {
                    disables.add(i);
                    System.out.printf("%s is disable anymore\n",item.toString());
                    return  true;
                }
            }
        }
        for (int i = 0; i < super.size(); i++) {
            for (int j = 0; j < disables.size(); j++) {
                if (super.get(i).equals(item) && i != disables.get(j)) {
                    disables.add(i); //location
                    System.out.printf("%s is disable anymore\n",item.toString());
                    return true; // operation succesful.
                }
                else if (super.get(i).equals(item) && i == disables.get(j)) {
                    System.out.printf("The %s is already disable!\n",item.toString());
                    return false; // operation failed
                }
            }
        }
        System.out.printf("There is no such a item ==> %s!\n" ,item.toString());
        return  false; // operation fail.
    }

    /**
     * This function convert disables element into enable element.
     * @param item element that will be made enable.
     * @return true if item has been enable ,if not ,return false;
     */
    public  boolean enable(E item) {
        for (int i = 0; i < disables.size(); i++)
            if (super.get(disables.get(i)).equals(item)) {
                disables.remove(i);
                System.out.printf("%s is visible anymore\n",item);
                return  true;
            }
        System.out.printf("%s is already visible or is not available in the list!|\n",item);
        return false;
    }

    /**
     * This function print the disable elements to screen.
     */
    public void showDisable() {
        System.out.println("Disables items:");
        for (int i = 0; i < disables.size(); i++)
            System.out.printf("%d -) %s\n",i+1,super.get(disables.get(i)).toString());
    }

    /**
     * This function print the enable elements to screen.
     */
    public void showEnable() {
        boolean flag = true;
        System.out.println("Enables items:");
        if (disables.size() < 1) {
            for (int i = 0; i < super.size(); i++)
                System.out.printf("Location : %d  item : %s\n",i,super.get(i).toString());
        }
        else {

            for (int i = 0; i < super.size(); i++) {
                flag = true;
                for (int j = 0; j < disables.size(); j++) {
                    if (super.get(i).equals(super.get(disables.get(j)))) flag = false;
                }
                if(flag)
                    System.out.printf("Location : %d item : %s\n",i,super.get(i).toString());
            }
        }
    }

    @Override
    public E getFirst() {
        return super.getFirst();
    }

    @Override
    public E getLast() {
        return super.getLast();
    }

    @Override
    public E removeFirst() {
        for (int i = 0; i < disables.size(); i++) {
            if (disables.get(i) == super.get(0)) {
                System.out.println("The first element is not visible!");
                return null;
            }
        }
        for (int i = 0; i < disables.size(); i++)
            disables.set(i,disables.get(i) - 1);
        return super.removeFirst();
    }

    @Override
    public E removeLast() {
        for (int i = 0; i < disables.size(); i++)
            if (disables.get(i) == super.get(super.size() - 1)) {
                System.out.println("The last element is not visible!");
                return  null;
            }
        return super.removeLast();
    }

    @Override
    public void addFirst(E e) {
        for (int i = 0; i < disables.size(); i++)
            disables.set(i,disables.get(i) + 1);
        super.addFirst(e);
    }

    @Override
    public void addLast(E e) {
          super.addLast(e);
    }

    @Override
    public int size() {
        return super.size() - disables.size();
    }

    @Override
    public boolean add(E e) {
       return super.add(e);
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < super.size(); i++) {
            for (int j = 0; j < disables.size(); j++) {
                if (super.get(i).equals(o) && i != disables.get(j)) {
                    for (int k = 0; k < disables.size(); k++)
                        if (i < disables.get(k))
                            disables.set(k,disables.get(k) - 1);
                    return super.remove(o);
                }
                else if (super.get(i).equals(o) && i == disables.get(j)) {
                    System.out.printf("%s is not visible\n",o.toString());
                    return false;
                }
            }
        }
        return false;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= super.size()) {
            System.out.printf("The index is out of bound  index : %d\n",index);
            return null;
        }
        for (int i = 0; i < disables.size(); i++) {
            if (disables.get(i) == index) {
                System.out.printf("The %d. indexed element is not visible\n",index);
                return null;
            }
        }
        return super.get(index);
    }

    @Override
    public E set(int index, E element) {
        if (index < 0 || index >= super.size()) {
            System.out.printf("The index is out of bound  index : %d\n",index);
            return  null;
        }
        for (int i = 0; i < super.size(); i++) {
            for (int j = 0; j < disables.size(); j++) {
                if (super.get(i).equals(element) && i != disables.get(j)) {
                    for (int k = 0; k < disables.size(); k++)
                        if (index <= disables.get(k))
                            disables.set(k,disables.get(k) + 1);
                    return super.set(index,element);
                }
                else if(index == disables.get(j)) {
                    System.out.printf("The %d.indexed element is disable!\n",index);
                    return null;
                }
            }
        }
        return null;
    }

    @Override
    public void add(int index, E element) {

        if (index < 0 || index >= super.size())
            System.out.println("The index is out of bound!");

        else {
            for (int i = 0; i < disables.size(); i++)
                if (index <= disables.get(i))
                    disables.set(i,disables.get(i)+1);
            super.add(index,element);
        }
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= super.size()) {
            System.out.println("The index is out of bound!");
            return null;
        }
        for (int i = 0; i < disables.size(); i++) {
            if (index == disables.get(i)) {
                System.out.printf("The %d. indexed element is not visible!\n",index);
                return null;
            }
        }
        for (int i = 0; i < disables.size(); i++)
            disables.set(i,disables.get(i) - 1);

        return super.remove(index);
    }

    @Override
    public E remove() {
        if (disables.size() > 0){
            for (int i = 0; i < disables.size(); i++) {
                if (disables.get(i) == 0) {
                    System.out.println("The first element is not visible!");
                    return null;
                }
            }
        }
        for (int i = 0; i < disables.size(); i++)
            disables.set(i,disables.get(i) - 1);
        return super.remove();

    }

}
