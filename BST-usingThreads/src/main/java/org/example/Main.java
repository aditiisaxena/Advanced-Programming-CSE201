package org.example;

import java.util.*;
import java.io.*;

class tree {
    private TreeMap<Integer, Integer> map;
    private int key;
    private int max;
    private int min;
    private int size;

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public TreeMap<Integer, Integer> getMap() {
        return map;
    }

    public tree() {
        map = new TreeMap<Integer, Integer>();
        setMax(1000000000);
        setMin(-1000000000);
        setKey(0);
    }

    public void insert(int i) {
        setSize(i);
        for (int j = 0; j < i; j++) {
            int value = (int) (Math.random() * (getMax() - getMin() + 1) + getMin());
            map.put(value, getKey());
            setKey(getKey() + 1);
        }
    }

    public boolean find(int value) {
        if (map.containsKey(value)) {
            return true;
        } else {
            return false;
        }
    }

    public void mergeTreeA(TreeMap<Integer, Integer> map1, TreeMap<Integer, Integer> map2) {
        TreeMap<Integer, Integer> map3 = new TreeMap<Integer, Integer>();
        map3.putAll(map1);
        map3.putAll(map2);
    }

    public void mergeTreeB(TreeMap<Integer, Integer> map1, TreeMap<Integer, Integer> map2, TreeMap<Integer, Integer> map3, TreeMap<Integer, Integer> map4) {
        TreeMap<Integer, Integer> map5 = new TreeMap<Integer, Integer>();
        map5.putAll(map1);
        map5.putAll(map2);
        map5.putAll(map3);
        map5.putAll(map4);
    }

}

class thread1 extends Thread implements Runnable {
    tree tree;
    int size;

    public thread1(tree tree, int size) {
        this.tree = tree;
        this.size = size;
    }

    public void run() {
        tree.insert(size);
        }
}

class thread2 extends Thread implements Runnable {
    tree tree;
    int value;

    public thread2(tree tree, int value) {
        this.tree = tree;
        this.value = value;
    }

    public void run() {
        tree.find(value);
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        File file = new File("trees.txt");
        tree tree = new tree();
        long start = System.currentTimeMillis();
        tree.insert(10);
        long end = System.currentTimeMillis();
        System.out.println("Time taken to insert elements(10) without thread: " + (end - start) + "ms");
        int height = (int) (Math.log(10 + 1) / Math.log(2));
        System.out.println("Height of the tree: " + height);
        start = System.currentTimeMillis();
        tree.find(48);
        end = System.currentTimeMillis();
        System.out.println("Time taken to find element without thread: " + (end - start) + "ms");
        tree tree1 = new tree();
        tree tree2 = new tree();
        thread1 thread1 = new thread1(tree1, 5);
        thread1 thread2 = new thread1(tree2, 5);
        start = System.currentTimeMillis();
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tree.mergeTreeA(tree1.getMap(), tree2.getMap());
        end = System.currentTimeMillis();
        System.out.println("Time taken to insert elements(10) with thread(2): " + (end - start) + "ms");
        thread2 thread3 = new thread2(tree1, 48);
        thread2 thread4 = new thread2(tree2, 48);
        start = System.currentTimeMillis();
        thread3.start();
        thread4.start();
        try {
            thread3.join();
            thread4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        end = System.currentTimeMillis();
        System.out.println("Time taken to find element with thread(2): " + (end - start) + "ms");
        tree tree3 = new tree();
        tree tree4 = new tree();
        tree tree5 = new tree();
        tree tree6 = new tree();
        thread1 thread5 = new thread1(tree3, 2);
        thread1 thread6 = new thread1(tree4, 3);
        thread1 thread7 = new thread1(tree5, 3);
        thread1 thread8 = new thread1(tree6, 2);
        start = System.currentTimeMillis();
        thread5.start();
        thread6.start();
        thread7.start();
        thread8.start();
        try {
            thread5.join();
            thread6.join();
            thread7.join();
            thread8.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tree.mergeTreeB(tree3.getMap(), tree4.getMap(), tree5.getMap(), tree6.getMap());
        end = System.currentTimeMillis();
        System.out.println("Time taken to insert elements(10) with thread(4): " + (end - start) + "ms");
        thread2 thread9 = new thread2(tree3, 48);
        thread2 thread10 = new thread2(tree4, 48);
        thread2 thread11 = new thread2(tree5, 48);
        thread2 thread12 = new thread2(tree6, 48);
        start = System.currentTimeMillis();
        thread9.start();
        thread10.start();
        thread11.start();
        thread12.start();
        try {
            thread9.join();
            thread10.join();
            thread11.join();
            thread12.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        end = System.currentTimeMillis();
        System.out.println("Time taken to find element with thread(4): " + (end - start) + "ms");
        tree tree7 = new tree();
        start = System.currentTimeMillis();
        tree7.insert(1000);
        end = System.currentTimeMillis();
        System.out.println("Time taken to insert elements(1000) without thread: " + (end - start) + "ms");
        height = (int) (Math.log(1000 + 1) / Math.log(2));
        System.out.println("Height of the tree: " + height);
        start = System.currentTimeMillis();
        tree7.find(48);
        end = System.currentTimeMillis();
        System.out.println("Time taken to find element without thread: " + (end - start) + "ms");
        tree tree8 = new tree();
        tree tree9 = new tree();
        thread1 thread13 = new thread1(tree8, 500);
        thread1 thread14 = new thread1(tree9, 500);
        start = System.currentTimeMillis();
        thread13.start();
        thread14.start();
        try {
            thread13.join();
            thread14.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tree.mergeTreeA(tree8.getMap(), tree9.getMap());
        end = System.currentTimeMillis();
        System.out.println("Time taken to insert elements(1000) with thread(2): " + (end - start) + "ms");
        thread2 thread15 = new thread2(tree8, 48);
        thread2 thread16 = new thread2(tree9, 48);
        start = System.currentTimeMillis();
        thread15.start();
        thread16.start();
        try {
            thread15.join();
            thread16.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        end = System.currentTimeMillis();
        System.out.println("Time taken to find element with thread(2): " + (end - start) + "ms");
        tree tree10 = new tree();
        tree tree11 = new tree();
        tree tree12 = new tree();
        tree tree13 = new tree();
        thread1 thread17 = new thread1(tree10, 250);
        thread1 thread18 = new thread1(tree11, 250);
        thread1 thread19 = new thread1(tree12, 250);
        thread1 thread20 = new thread1(tree13, 250);
        start = System.currentTimeMillis();
        thread17.start();
        thread18.start();
        thread19.start();
        thread20.start();
        try {
            thread17.join();
            thread18.join();
            thread19.join();
            thread20.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tree.mergeTreeB(tree10.getMap(), tree11.getMap(), tree12.getMap(), tree13.getMap());
        end = System.currentTimeMillis();
        System.out.println("Time taken to insert elements(1000) with thread(4): " + (end - start) + "ms");
        thread2 thread21 = new thread2(tree10, 48);
        thread2 thread22 = new thread2(tree11, 48);
        thread2 thread23 = new thread2(tree12, 48);
        thread2 thread24 = new thread2(tree13, 48);
        start = System.currentTimeMillis();
        thread21.start();
        thread22.start();
        thread23.start();
        thread24.start();
        try {
            thread21.join();
            thread22.join();
            thread23.join();
            thread24.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        end = System.currentTimeMillis();
        System.out.println("Time taken to find element with thread(4): " + (end - start) + "ms");
        tree tree14 = new tree();
        start = System.currentTimeMillis();
        tree14.insert(1000000);
        end = System.currentTimeMillis();
        System.out.println("Time taken to insert elements(1000000) without thread: " + (end - start) + "ms");
        height = (int) (Math.log(1000000 + 1) / Math.log(2));
        System.out.println("Height of the tree: " + height);
        tree tree15 = new tree();
        tree tree16 = new tree();
        thread1 thread25 = new thread1(tree15, 500000);
        thread1 thread26 = new thread1(tree16, 500000);
        start = System.currentTimeMillis();
        thread25.start();
        thread26.start();
        try {
            thread25.join();
            thread26.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tree.mergeTreeA(tree15.getMap(), tree16.getMap());
        end = System.currentTimeMillis();
        System.out.println("Time taken to insert elements(1000000) with thread(2): " + (end - start) + "ms");
        tree tree17 = new tree();
        tree tree18 = new tree();
        thread2 thread27 = new thread2(tree17, 500000);
        thread2 thread28 = new thread2(tree18, 500000);
        start = System.currentTimeMillis();
        thread27.start();
        thread28.start();
        try {
            thread27.join();
            thread28.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        end = System.currentTimeMillis();
        System.out.println("Time taken to find element with thread(2): " + (end - start) + "ms");
        tree tree19 = new tree();
        tree tree20 = new tree();
        tree tree21 = new tree();
        tree tree22 = new tree();
        thread1 thread29 = new thread1(tree19, 250000);
        thread1 thread30 = new thread1(tree20, 250000);
        thread1 thread31 = new thread1(tree21, 250000);
        thread1 thread32 = new thread1(tree22, 250000);
        start = System.currentTimeMillis();
        thread29.start();
        thread30.start();
        thread31.start();
        thread32.start();
        try {
            thread29.join();
            thread30.join();
            thread31.join();
            thread32.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tree.mergeTreeB(tree19.getMap(), tree20.getMap(), tree21.getMap(), tree22.getMap());
        end = System.currentTimeMillis();
        System.out.println("Time taken to insert elements(1000000) with thread(4): " + (end - start) + "ms");
        thread2 thread33 = new thread2(tree19, 48);
        thread2 thread34 = new thread2(tree20, 48);
        thread2 thread35 = new thread2(tree21, 48);
        thread2 thread36 = new thread2(tree22, 48);
        start = System.currentTimeMillis();
        thread33.start();
        thread34.start();
        thread35.start();
        thread36.start();
        try {
            thread33.join();
            thread34.join();
            thread35.join();
            thread36.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        end = System.currentTimeMillis();
        System.out.println("Time taken to find element with thread(4): " + (end - start) + "ms");
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(file));
            for (Map.Entry<Integer, Integer> i : tree.getMap().entrySet()) {
                writer.write(i.getKey() + " ");
            }
            writer.newLine();
            writer.newLine();
            for (Map.Entry<Integer, Integer> i : tree7.getMap().entrySet()) {
                writer.write(i.getKey() + " ");
            }
            writer.newLine();
            writer.newLine();
            for (Map.Entry<Integer, Integer> i : tree14.getMap().entrySet()) {
                writer.write(i.getKey() + " ");
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert writer != null;
                writer.close();
            } catch (Exception ignored) {
            }
        }
    }
}

