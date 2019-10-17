package io.sci_f;

class Queue {

    private static Queue instance;
    private Node head,tail;

    private Queue(){
        head = null;
        tail = head;
    }

    static void init(){if (instance == null) instance = new Queue();}

    static Queue get(){if (instance == null) init();return instance;}

    static void end(){instance = null;}

    void enqueue(double value){
        if (head == tail){
            head = new Node(value);
            tail = head;
        } else {
            tail = new Node(value);
            head.setNext(tail);
        }
    }

    double dequeue(){
        double value = head.value;
        if (head == tail){
            head = null;
        } else {
            head = head.next;
        }
        return value;
    }

    private class Node{
        private Node next;
        private double value;

        Node(double value){
            this.value = value;
        }

        void setNext(Node next) {
            this.next = next;
        }
    }
}
