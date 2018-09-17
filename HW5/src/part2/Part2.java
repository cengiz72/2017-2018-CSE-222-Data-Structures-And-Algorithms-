package part2;

import java.util.*;

public class Part2<E> implements Set{
    private static class Node<E>{
        private E element;
        private Node<E>[] table;
        public Node<E>[] getTable(){return table;}
        public E getElement() { return element;}
        public Node(E item) {
            element = item;
            table = null;
        }
        public void createTable(int size) {
            if (size < 1) table = (Node<E>[]) new Node[1];
            else table = (Node<E>[]) new Node[size];
        }
    }
    private Node<E>[] table;
    private int PRIME = 11;
    private int numberOfItems;
    public Part2() {
        table = (Node<E>[])new Node[PRIME];
        numberOfItems = 0;
    }

    /**
     * traverse all elements in set and print value of them.
     */
    public void print() {
        Queue<Node<E>> queue = new LinkedList<>();
        Node<E> node = new Node<>(null);
        node.createTable(table.length);
        queue.add(node);
        do {
            Node<E> table = queue.poll();
            for (int i = 0; i < table.getTable().length; i++) {
                if (table.getTable()[i] != null) {
                    if (table.getTable()[i].getElement() != null)
                        System.out.printf("Item : %s index : %d table size : %d\n",
                                table.getTable()[i].getElement().toString(),i,table.getTable().length);
                    if (table.getTable()[i].getTable() != null) queue.add(table.getTable()[i]);
                }

            }
        }while (!queue.isEmpty());
    }

    /**
     *  find a prime number with respect to number and options args.
     * @param number
     * @param option determine prime number is smaller or bigger than given number.
     *        option 0 : the biggest prime that is smaller than given number
     *        option 1 : the smallest prime number that is bigger than given number.
     * @return prime number is bigger than or smaller than number with respect to option
     */
    private int findPrime(int number, int option) {
        int count = 0;
        switch (option) {
            case 0:
                for (int i = number; i > 2; i--) {
                    count = 0;
                    for (int j = 2; j < i; j++) {
                        if (i%j==0) ++count;
                    }
                    if (count==0) return i;
                }
                return 2;
            case 1:
                for (int i = number; ; ++i) {
                    count = 0;
                    for (int j = 2; j < i; j++) {
                        if (i%j==0) ++count;
                    }
                    if (count==0) return i;
                }
        }

        return 3;
    }
    /**
     * return number of items in set.
     */
    @Override
    public int size() {
        return numberOfItems;
    }

    /**
     *  check whether set is empty or not.
     * @return true if set is empty,otherwise false
     */
    @Override
    public boolean isEmpty() {
        return (numberOfItems == 0);
    }

    /**
     * helper method for contain method.
     * @param array
     * @param hashcode
     * @param target
     * @return true if target is found ,otherwise false.
     */
    private boolean containsHelper(Node<E>[] array,int hashcode,E target) {
        if (array == null) return false;
        int index = hashcode % array.length;
        if (array[index] != null && array[index].getElement().equals(target)) return true;
        if (array[index] != null)
            return containsHelper(array[index].getTable(),hashcode,target);
        return false;
    }

    /**
     * @param o searched object in the set
     * @return true if target is found ,otherwise false.
     */
    @Override
    public boolean contains(Object o) {
        E obj = (E)o;
        int hashcode = obj.hashCode();
        if (hashcode < 0) hashcode *= -1;
        return containsHelper(table,hashcode,obj);
    }

    @Override
    public Iterator iterator() {
        throw new UnsupportedOperationException("This function is not implemented");
    }

    /**
     * helper method for add method.
     * @param array
     * @param length
     * @param hashcode
     * @param target
     * @return return true if target is added if target is already present return false.
     */
    private boolean addHelper(Node<E>[] array,int length,int originIndex,int hashcode,E target) {
        int index;
        index = hashcode % array.length;
        if (array[index] != null && array[index].getElement().equals(target))
            return false;
        else if (array[index] == null) {
            array[index] = new Node<>(target);
            ++numberOfItems;
            System.out.printf("Original index : %d original table size : %d index : %d item : %s table size : %d\n",
                                                            originIndex,length,index,target.toString(),array.length);
            return true;
        }
        else if (array[index] != null && array[index].getTable() != null) {
            return addHelper(array[index].getTable(),array.length,originIndex,hashcode,target);
        }
        else if (array[index].getTable() == null) {
            if (length < 1) array[index].createTable(1);
            else array[index].createTable(findPrime(length - 1,0));
            int newIndex = target.hashCode()%array[index].getTable().length;
            if ( newIndex < 0)  newIndex *= -1;
            array[index].getTable()[newIndex] = new Node<>(target);
            System.out.printf("Original index : %d original table size : %d index : %d item : %s table size : %d\n",
                                      originIndex,length,newIndex,target.toString(),array[index].getTable().length);
            ++numberOfItems;
            return true;
        }
        return false;
    }

    /**
     *
     * @param o added element.
     * @return return true if target is added if target is already present return false.
     */
    @Override
    public boolean add(Object o) {
        E obj = (E)o;
        int hashcode = obj.hashCode();
        if (hashcode < 0) hashcode *= -1;
        int index = hashcode%table.length;
        return addHelper(table,table.length,index,hashcode,obj);
    }

    /**
     * helper method for remove method.
     * @param array
     * @param hashcode
     * @param target
     * @return true if target is deleted ,otherwise false.
     */
    private boolean removeHelper(Node<E>[] array,int hashcode,E target) {
        if (array == null) return false;
        int index = hashcode % array.length;
        if (array[index] != null && array[index].getElement().equals(target)) {
            array[index] = null;
            --numberOfItems;
            return true;
        }
        if (array[index] != null)
            return removeHelper(array[index].getTable(),hashcode,target);
        return false;
    }

    /**
     * @param o to be deleted element.
     * @return true if target is deleted ,otherwise false.
     */
    @Override
    public boolean remove(Object o) {
        E obj = (E)o;
        int hashcode = obj.hashCode();
        if (hashcode < 0) hashcode *= -1;
        return removeHelper(table,hashcode,obj);
    }
    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException("This function is not implemented");
    }
    @Override
    public boolean addAll(Collection c) {
        throw new UnsupportedOperationException("This function is not implemented");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("This function is not implemented");
    }

    @Override
    public boolean removeAll(Collection c) {
        throw new UnsupportedOperationException("This function is not implemented");
    }

    @Override
    public boolean retainAll(Collection c) {
        throw new UnsupportedOperationException("This function is not implemented");
    }

    @Override
    public boolean containsAll(Collection c) {
        throw new UnsupportedOperationException("This function is not implemented");
    }

    @Override
    public Object[] toArray(Object[] a) {
        throw new UnsupportedOperationException("This function is not implemented");
    }
    public static void main(String[] args) {
        Part2<String> set = new Part2<>();
        set.add("Jack");
        set.add("George");
        set.add("Victory");
        set.add("Jennifer");
        set.add("Hector");
        set.add("Richard");
        set.add("Teresa");
        set.add("Pillow");
        set.add("Frank");
        set.add("David");
        set.add("Edward");
        set.add("Elisabet");
        set.add("Baron");
        set.add("Alexis");
        set.add("Caroline");
        set.add("Jacky");
        set.add("Banner");
        set.add("Culver");
        set.add("Denver");
        set.add("Eleanor");
        set.add("Forrest");
        set.add("Grayson");
        set.add("Harley");
        set.add("Hawthorne");
        set.add("Jefferson");
        set.add("Keaton");
        set.add("Kimberley");
        set.add("Perry");
    }
}
