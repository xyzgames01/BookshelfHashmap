package com.main.bookshelffinal;

import java.util.LinkedList;

public class Bookshelf<K, V> {

    private class Node{  // contains two parameters - key and value
        K key;
        V value;

        Node(K key, V value) {
            this.key = key;  // initialising the key
            this.value = value;   // initialising the value
        }
    }

    private int size;
    private LinkedList<Node>[] shelves; // N = buckets.length

    public Bookshelf() {
        InitializeShelves(4);
        size = 0;
    }

    public Bookshelf(int capacity) {
        InitializeShelves(capacity);
        size = 0;
    }

    private void InitializeShelves(int N) {
        shelves = new LinkedList[N];
        for (int i = 0; i < shelves.length; i++) {
            shelves[i] = new LinkedList<>();
        }
    }

    public void put(K key, V value)
    {
        int shelfIndex = HashFunction(key);   // getting the value of index of shelf
        int di = getIndexWithinShelf(key,shelfIndex);   // getting the value of index
        // inside the shelf
        if(di!=-1)
        {
            // if the key is already present
            Node node = shelves[shelfIndex].get(di);  // node at index di in the bucket shelfIndex
            node.value = value; // updating the value
        }
        else
        {
            // if the key is not already present
            Node node = new Node(key,value);  // make a new node with that key-value
            shelves[shelfIndex].add(node);  // add it on index shelfIndex
            size++;  // increase the size
        }
    }




    private int getIndexWithinShelf(K key, int bi)
    {
        int di = 0;
        for(Node node : shelves[bi])
        {
            if(node.key.equals(key))
            {
                // if key==key, return the index
                return di;
            }
            di++; // otherwise, keep moving to the next index
        }

        return -1;  // if we've searched the whole linked list and the key is not
        // present, return -1.
    }

    public V get(K key) {
        int shelfIndex = HashFunction(key);    // index of the shelf
        int di = getIndexWithinShelf(key,shelfIndex); //index of the position in that
        //shelf
        if(di!=-1){ // if the key is present at di, return its value. Otherwise,
            // Return null
            Node node = shelves[shelfIndex].get(di);
            return node.value;
        }
        else{
            return null;
        }
    }

    public int size() {
        return size;  // return the value of size variable
    }

    public int HashFunction(K k) {
        String kString = k.toString();
        char ch[];
        ch = kString.toCharArray();
        int i, sum;
        for (sum=0, i=0; i<kString.length(); i++)
            sum += ch[i];
        return sum % shelves.length;
    }

}
