package part1;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class Part1<K,V> implements Map{
    private static class Entry < K, V > {

        /** The key */
        private K key;

        /** The value */
        private V value;

        private boolean deleted;
        /** Creates a new key-value pair.
         @param key The key
         @param value The value
         */
        public Entry(K key, V value,boolean state) {
            this.key = key;
            this.value = value;
            deleted = state;
        }

        /** Retrieves the key.
         @return The key
         */
        public K getKey() {
            return key;
        }

        /** Retrieves the value.
         @return The value
         */
        public V getValue() {
            return value;
        }

        /**
         *
         * @return return state of entry whether is deleted or not.
         */
        public boolean getState() {
            return deleted;
        }
        /** Sets the value.
         @param val The new value
         @return The old value
         */
        public V setValue(V val) {
            V oldVal = value;
            value = val;
            return oldVal;
        }

        /**
         *
         * @param state set state of entry.
         */
         public void setState(boolean state) {
            deleted = state;
         }
    }
    private double LOAD_FACTOR = 0.65;
    private final int DEFAULT_CAPACITY = 11;
    private int PRIME;
    private int numbersOfItems;
    private int numberOfDeletedItems;
    private Entry[] table;
    public Part1() {
        table = new Entry[DEFAULT_CAPACITY];
        PRIME = findPrime(DEFAULT_CAPACITY-1,0);
        numbersOfItems = 0;
        numberOfDeletedItems = 0;
    }

    /**
     * traverse all table and print values of all element
     */
    public void printAllEntries() {
        for (int i = 0; i < table.length; i++)
            if (table[i] != null && !table[i].getState())
                System.out.printf("Entry : %s <-----> %s Index : %d\n",
                table[i].getKey().toString(),table[i].getValue().toString(),i);
    }

    /**
     * increase table size and add all element in the old table to new table properly.
     */
    public void rehash() {
        int size = findPrime(2*table.length + 1,1);
        Entry<K,V>[] oldTable = table;
        table = new Entry[size];
        numbersOfItems = 0;
        numberOfDeletedItems = 0;
        for (int i = 0; i < oldTable.length; i++) {
            if (oldTable[i] != null && !oldTable[i].getState())
                put(oldTable[i].getKey(),oldTable[i].getValue());
        }
        PRIME = findPrime(size - 1,0);
        System.out.printf("Prime : %d Size : %d \n",PRIME,table.length);
    }
    private int findPrime(int number,int option) {
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
                    return 3;
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
     * @return number of items in the table.
     */
    @Override
    public int size() {
        return numbersOfItems;
    }
    /**
     * check whether table is empty or not.
     * @return true is table is empty,otherwise false.
     */
    @Override
    public boolean isEmpty() {
        return (numbersOfItems == 0);
    }

    /**
     * @param key item that will be searched in the table.
     * @return true if key is found at table,otherwise false.
     */
    @Override
    public boolean containsKey(Object key) {
        return (get(key) != null);
    }

    @Override
    public boolean containsValue(Object value) {
        throw new UnsupportedOperationException("This function is not implemented");
    }

    /**
     * @param key item that will be searched in the table.
     * @return true if key is found at table,otherwise false.
     */
    @Override
    public Object get(Object key) {
        int index = key.hashCode();
        int temp;
        if (index < 0) index *= -1;
        index %= table.length;
        temp = index;
        while (true) {
            if (table[index] != null && !table[index].getState()
                                     && table[index].getKey().equals(key))
                return table[index].getValue();
            index = (index + 1)%table.length;
            if (index == temp) return null;
        }
    }

    /**
     * @param key   K of Entry that will be inserted
     * @param value V of Entry that will be inserted
     * @return return V of inserted entry if entry is not present in the table,if K is already
     *          present in the table ,return old value of V.
     */
    @Override
    public Object put(Object key, Object value) {
        int index =  key.hashCode();
        int hash2,i = 0;
        int newIndex = 0;
        if (index < 0) index *= -1;
        index = index % table.length;
        if (IsRequiredRehash()) {
            rehash();
        }
        if (table[index] == null) {
            table[index]=new Entry(key,value,false);
            System.out.printf("Index : %d Key : %s Table size : %d \n",index,key.toString(),table.length);
            ++numbersOfItems;
            return table[index].getValue();
        }
        else if (table[index].getKey().equals(key)){
            Object oldValue = table[index].getValue();
            table[index].setValue(value);
            System.out.printf("Index : %d Key : %s Table size : %d \n",index,key.toString(),table.length);
            return oldValue;
        }
        hash2 = doubleHash(index);
        System.out.println("Collision occurs:");
        while (true) {
            newIndex = (index + i*hash2)%table.length;
            if (table[newIndex] == null) {
                table[newIndex] = new Entry(key,value,false);
                System.out.printf("Index : %d New Index : %d Key : %s Table size : %d \n",index,newIndex,key.toString(),table.length);
                ++numbersOfItems;
                break;
            }
            ++i;
        }
        return  table[newIndex].getValue();
    }

    /**
     * @param key key that will be deleted
     * @return return Value if given key is deleted ,otherwise null
     */
    @Override
    public Object remove(Object key) {
        int index = key.hashCode();
        int temp;
        if (index < 0) index *= -1;
        index %= table.length;
        temp = index;
        if (table[index] != null && table[index].getKey().equals(key)) {
            Object value = table[index].getValue();
            table[index].setState(true);
            ++numberOfDeletedItems;
            --numbersOfItems;
            return value;
        }
        while (true) {
            if ((index = (index + 1) % table.length) == temp) return null;
            if (table[index] != null && !table[index].getState()
                                     &&  table[index].getKey().equals(key)) {

                Object value = table[index].getValue();
                table[index].setState(true);
                ++numberOfDeletedItems;
                --numbersOfItems;
                return value;
            }

        }
    }

    @Override
    public void putAll(Map m) {
        throw new UnsupportedOperationException("This function is not implemented");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("This function is not implemented");
    }

    @Override
    public Set keySet() {
        throw new UnsupportedOperationException("This function is not implemented");
    }

    @Override
    public Collection values() {
        throw new UnsupportedOperationException("This function is not implemented");
    }

    @Override
    public Set<Entry> entrySet() {
        throw new UnsupportedOperationException("This function is not implemented");
    }

    /**
     * @return true if fullness rate exceeds ,otherwise false.
     */
    private boolean IsRequiredRehash() {

        if ((numbersOfItems + numberOfDeletedItems)/((double)table.length) > LOAD_FACTOR)
            return true;
        return false;
    }

    /**
     * @param index index of key
     * @return return a second hashing according to this formula: PRIME - (index % PRIME)
     */
    private int doubleHash(int index) {
        return (PRIME - (index % PRIME));
    }
    public static void main(String[] args) {
        Part1 doubleHashMap = new Part1();
        doubleHashMap.put("Henry",1);
        doubleHashMap.put("Hercule",2);
        doubleHashMap.put("Austin",3);
        doubleHashMap.put("Beckham",4);
        doubleHashMap.put("Camber",5);
        doubleHashMap.put("Darwin",6);
        doubleHashMap.put("Elberta",7);
        doubleHashMap.put("Fletcher",8);
        doubleHashMap.put("Garrison",9);
        doubleHashMap.put("Davidson",10);
        doubleHashMap.put("Edward",11);
        doubleHashMap.put("Chancellor",12);
        doubleHashMap.put("Baron",13);
        System.out.println("Test for get :");
        System.out.println("Camber value : 5, return 5");
        System.out.printf("Value : %s \n",doubleHashMap.get("Camber").toString());
        System.out.println("Baron is not in the table return null");
        System.out.printf("Value : %s \n",doubleHashMap.get("Baron"));
        System.out.println("Test for remove:");
        System.out.println("Camber value : 5");
        System.out.printf("Value : %s \n",doubleHashMap.remove("Camber").toString());
        System.out.println("Zeck is not in the table  ,return null");
        System.out.printf("Value : %s \n",doubleHashMap.remove("Zeck"));
        System.out.println("Print all entries");
        doubleHashMap.printAllEntries();
    }
}
