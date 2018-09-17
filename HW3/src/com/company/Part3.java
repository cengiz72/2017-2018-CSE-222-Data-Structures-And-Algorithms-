package com.company;

public class Part3 {

    private MySpecialLinkedList head;
    private MySpecialLinkedList current[];
    private MySpecialLinkedList last[]; //for nextInSameSemester()
    private MySpecialLinkedList first[];//for nextInSameSemester()
    private int size;
    public class MySpecialLinkedList {
        public Course item;
        public MySpecialLinkedList next;
        public MySpecialLinkedList forCircular;
        public MySpecialLinkedList() {
            next = null;
            forCircular = null;
        }
    }

    public Part3(){
        size = 0;
        head = null;
        last = new MySpecialLinkedList[8];
        first = new MySpecialLinkedList[8];
        current = new MySpecialLinkedList[10];
        for (int i = 0; i < 8; i++) {
            last[i] = null;
            first[i] = null;
            current[i] = null;
        }
    }

    /**
     * Helper function add elements to list circularly.
     * @param node node that will be added
     * @param begin node that is head or first node in the same semester.
     */
    private void addCircularly(MySpecialLinkedList node ,MySpecialLinkedList begin) {
        MySpecialLinkedList iter = new MySpecialLinkedList();
        iter = begin;
        if (begin.forCircular != null) {
            node.forCircular = first[node.item.getSemester() -1];
            last[node.item.getSemester() -1].forCircular = node;
            last[node.item.getSemester() -1] = node;
        }
        else {
            begin.forCircular = node;
            first[node.item.getSemester() -1] = begin;
            current[node.item.getSemester() -1]=first[node.item.getSemester() -1];
            node.forCircular = begin;
            last[node.item.getSemester() -1] = node;
        }
    }
    /**
     * Helper function remove element from list circularly.
     * @param node node that will be removed
     */
    private void removeCircularly(MySpecialLinkedList node) {

        MySpecialLinkedList iter = new MySpecialLinkedList();
        Course temp = new Course();
        if (first[node.item.getSemester() - 1] == node &&
            first[node.item.getSemester() - 1] == last[node.item.getSemester() - 1]) {
            first[node.item.getSemester() - 1]   = null;
            last[node.item.getSemester() - 1]    = null;
            current[node.item.getSemester() - 1] = null;
        }
        else if (first[node.item.getSemester() - 1] == node) {

            if (current[node.item.getSemester() - 1] == first[node.item.getSemester() - 1]) {
                current[node.item.getSemester() - 1] = first[node.item.getSemester() - 1].forCircular;

            }

            first[node.item.getSemester() - 1] = first[node.item.getSemester() - 1].forCircular;
            last[node.item.getSemester() - 1].forCircular = first[node.item.getSemester() - 1];
        }

        else if (last[node.item.getSemester() - 1] == node) {

            iter = first[node.item.getSemester() - 1];
            while (iter.forCircular != last[node.item.getSemester() - 1])
                iter = iter.forCircular;
            if (current[node.item.getSemester() - 1] == last[node.item.getSemester() - 1])
                current[node.item.getSemester() - 1] = iter;
            last[node.item.getSemester() - 1] = iter;
            last[node.item.getSemester() - 1].forCircular = first[node.item.getSemester() - 1];

        }
        else {
            iter = first[node.item.getSemester() - 1];

            while (iter.forCircular != node)
                iter = iter.forCircular;
            if (current[node.item.getSemester() - 1] == iter.forCircular)
                current[node.item.getSemester() - 1] = iter.forCircular.forCircular;
            iter.forCircular = iter.forCircular.forCircular;
            first[node.item.getSemester() - 1] = iter;
        }
    }

    /**
     * This function add an elements to end of list.
     * @param item item that will be added element
     */
    public void add(Course item) {
        MySpecialLinkedList node = new MySpecialLinkedList();
        MySpecialLinkedList iter = new MySpecialLinkedList();
        node.item = item;
        if (head == null) {
            head = current[8] = current[9] = node;
            ++size;
        }
        else {
            current[8].next = node;
            current[8] = node;
            iter = head;
            //for adding circular the item with respect to semester number.
            while(iter != null) {
                if (iter.item.getSemester() == item.getSemester()) {
                    addCircularly(node,iter);
                    break; // element is added circularly.
                }
                iter = iter.next;
            }
            ++size;
        }

    }

    /**
     * This function remove given element from list.
     * @param item item that will be removed from the list.
     * @return return true if item is deleted ,if not,return false.
     */
    public Course remove(Course item) {

        MySpecialLinkedList iter = new MySpecialLinkedList();
        Course temp = new Course();
        iter = head;

        if (head == null)
            return null; // Empty list.
        if (head.item.getSemester() == item.getSemester() &&
            head.item.getCourseCode().equals(item.getCourseCode()))
        {
            removeCircularly(head);
            temp = head.item;
            head = head.next;
            current[9] = head; // for updating next()
            --size;
            return temp;
        }

        while (head.next!= null) {
            if (head.next.item.getSemester() == item.getSemester() &&
                head.next.item.getCourseCode().equals(item.getCourseCode()))
            {
                removeCircularly(head.next);
                head.next = head.next.next;
                head = iter;
                --size;
                return item;
            }
            head = head.next;
        }

        return null; //Element no found.
    }

    /**
     * This function return count of elements of list.
     * @return return number of elements of list.
     */
    public int size() {
        return size;
    }

    /**
     * This function return next element of list.
     * @return return next element if next element is not null,if not,return null
     */
    public Course next() {
        if (current[9] != null) {
            Course item = new Course();
            item = current[9].item;
            current[9] = current[9].next;
            return  item;
        }
        return null;
    }

    /**
     * This function return next element in the circular list.
     * @param semester semester number.
     * @return return a course in the semester if semester has courses,if not ,return null;
     * @throws IndexOutOfBoundsException
     */
    public Course nextInSemester(int semester) {
        Course temp = new Course();
        if (semester < 1 || semester > 8)
            throw new IndexOutOfBoundsException("Invalid semester!");
        if (first[semester-1] == null)
            return null; // this semester is no available yet.
        temp = current[semester - 1].item;
        current[semester - 1] = current[semester - 1].forCircular;
        return temp;
    }
}
