package org.example;

import java.util.*;
import java.io.*;

class Collecting{
    private int max;
    private int min;
    private int size;
    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
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

    float [] cgpa1 = new float[1];
    float [] cgpa2 = new float[1];

    public void store(int i){
        float [] a = new float[i];
        float [] b = new float[i];
        setMax(10);
        setMin(0);
        setSize(i);
        for (int j = 0; j < getSize(); j++){
            float random = (float) (Math.random() * (getMax() - getMin() + 1) + getMin());
            while (random > 10){
                random = (float) (Math.random() * (getMax() - getMin() + 1) + getMin());
            }
            a[j] = Float.parseFloat(String.format("%.3f", random));
            b[j] = Float.parseFloat(String.format("%.3f", random));
        }
        cgpa1 = a;
        cgpa2 = b;
    }

    public void oddeven(){
        boolean sort = false;
        while (!sort){
            sort = true;
            float x = 0;
            for (int i = 0; i < cgpa1.length - 1; i = i+2){
                if (cgpa1[i] < cgpa1[i+1]){
                    x = cgpa1[i];
                    cgpa1[i] = cgpa1[i+1];
                    cgpa1[i+1] = x;
                    sort = false;
                }
            }
            for (int i = 1; i < cgpa1.length - 1; i = i+2){
                if (cgpa1[i] < cgpa1[i+1]){
                    x = cgpa1[i];
                    cgpa1[i] = cgpa1[i+1];
                    cgpa1[i+1] = x;
                    sort = false;
                }
            }
        }
    }

    public void oddtoeven(int phase, int end){
        boolean sort = false;
        while (!sort) {
            sort = true;
            float x = 0;
            for (int i = phase; i < end - 1; i = i + 2) {
                if (cgpa2[i] < cgpa2[i + 1]) {
                    x = cgpa2[i];
                    cgpa2[i] = cgpa2[i + 1];
                    cgpa2[i + 1] = x;
                    sort = false;
                }
            }
            for (int i = phase + 1; i < end - 1; i = i + 2) {
                if (cgpa2[i] < cgpa2[i + 1]) {
                    x = cgpa2[i];
                    cgpa2[i] = cgpa2[i + 1];
                    cgpa2[i + 1] = x;
                    sort = false;
                }
            }
        }
    }

    public void mergesort(){
        int phase = 0;
        int end = cgpa2.length;
        while (phase < end){
            oddtoeven(phase, end);
            phase = phase + 2;
        }
    }

}

class withThread extends Thread implements Runnable{
    Collecting c;
    int phase;
    int end;
    public withThread(Collecting c, int aphase, int end){
        this.c=c;
        this.phase=aphase;
        this.end=end;
    }
    @Override
    public void run(){
        c.oddtoeven(phase, end);
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        FileWriter file = new FileWriter("cgpa.txt");
        Collecting c = new Collecting();
        c.store(1);
        withThread withThread1 = new withThread(c, 0, c.getSize()/2);
        withThread withThread2 = new withThread(c, c.getSize()/2, c.getSize());
        file.write("Before Odd Even Sort without thread for 1 student: \n");
        file.write(Arrays.toString(c.cgpa1) +"\n");
        long start = System.currentTimeMillis();
        c.oddeven();
        long end = System.currentTimeMillis();
        file.write("After Odd Even Sort without thread for 1 student: \n");
        file.write(Arrays.toString(c.cgpa1) +"\n");
        System.out.println("Time taken without Thread(1 student): " + (end - start) + "ms");
        file.write("Before Odd Even Sort with thread for 1 student: \n");
        file.write(Arrays.toString(c.cgpa2) +"\n");
        start = System.currentTimeMillis();
        withThread1.start();
        withThread2.start();
        try {
            withThread1.join();
            withThread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        c.mergesort();
        end = System.currentTimeMillis();
        file.write("After Odd Even Sort with thread for 1 student: \n");
        file.write(Arrays.toString(c.cgpa2) +"\n");
        System.out.println("Time taken with Thread(1 student): " + (end - start) + "ms");
        Collecting c1 = new Collecting();
        c1.store(10);
        withThread withThread3 = new withThread(c1, 0, c1.getSize()/2);
        withThread withThread4 = new withThread(c1, c1.getSize()/2, c1.getSize());
        file.write("Before Odd Even Sort without thread for 10 student: \n");
        file.write(Arrays.toString(c1.cgpa1) +"\n");
        start = System.currentTimeMillis();
        c1.oddeven();
        end = System.currentTimeMillis();
        file.write("After Odd Even Sort without thread for 10 student: \n");
        file.write(Arrays.toString(c1.cgpa1) +"\n");
        System.out.println("Time taken without Thread(10 students): " + (end - start) + "ms");
        file.write("Before Odd Even Sort with thread for 10 student: \n");
        file.write(Arrays.toString(c1.cgpa2) +"\n");
        start = System.currentTimeMillis();
        withThread3.start();
        withThread4.start();
        try {
            withThread3.join();
            withThread4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        c1.mergesort();
        end = System.currentTimeMillis();
        file.write("After Odd Even Sort with thread for 10 student: \n");
        file.write(Arrays.toString(c1.cgpa2) +"\n");
        System.out.println("Time taken with Thread(10 students): " + (end - start) + "ms");
        Collecting c2 = new Collecting();
        c2.store(100);
        withThread withThread5 = new withThread(c2, 0, c2.getSize()/2);
        withThread withThread6 = new withThread(c2, c2.getSize()/2, c2.getSize());
        file.write("Before Odd Even Sort without thread for 100 student: \n");
        file.write(Arrays.toString(c2.cgpa1) +"\n");
        start = System.currentTimeMillis();
        c2.oddeven();
        end = System.currentTimeMillis();
        file.write("After Odd Even Sort without thread for 100 student: \n");
        file.write(Arrays.toString(c2.cgpa1) +"\n");
        System.out.println("Time taken without Thread(100 students): " + (end - start) + "ms");
        file.write("Before Odd Even Sort with thread for 100 student: \n");
        file.write(Arrays.toString(c2.cgpa2) +"\n");
        start = System.currentTimeMillis();
        withThread5.start();
        withThread6.start();
        try {
            withThread5.join();
            withThread6.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        c2.mergesort();
        end = System.currentTimeMillis();
        file.write("After Odd Even Sort with thread for 100 student: \n");
        file.write(Arrays.toString(c2.cgpa2) +"\n");
        System.out.println("Time taken with Thread(100 students): " + (end - start) + "ms");
        Collecting c3 = new Collecting();
        c3.store(1000);
        withThread withThread7 = new withThread(c3, 0, c3.getSize()/2);
        withThread withThread8 = new withThread(c3, c3.getSize()/2, c3.getSize());
        file.write("Before Odd Even Sort without thread for 1000 student: \n");
        file.write(Arrays.toString(c3.cgpa1) +"\n");
        start = System.currentTimeMillis();
        c3.oddeven();
        end = System.currentTimeMillis();
        file.write("After Odd Even Sort without thread for 1000 student: \n");
        file.write(Arrays.toString(c3.cgpa1) +"\n");
        System.out.println("Time taken without Thread(1000 students): " + (end - start) + "ms");
        file.write("Before Odd Even Sort with thread for 1000 student: \n");
        file.write(Arrays.toString(c3.cgpa2) +"\n");
        start = System.currentTimeMillis();
        withThread7.start();
        withThread8.start();
        try {
            withThread7.join();
            withThread8.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        c3.mergesort();
        end = System.currentTimeMillis();
        file.write("After Odd Even Sort with thread for 1000 student: \n");
        file.write(Arrays.toString(c3.cgpa2) +"\n");
        System.out.println("Time taken with Thread(1000 students): " + (end - start) + "ms");
        Collecting c4 = new Collecting();
        c4.store(10000);
        withThread withThread9 = new withThread(c4, 0, c4.getSize()/2);
        withThread withThread10 = new withThread(c4, c4.getSize()/2, c4.getSize());
        file.write("Before Odd Even Sort without thread for 10000 student: \n");
        file.write(Arrays.toString(c4.cgpa1) +"\n");
        start = System.currentTimeMillis();
        c4.oddeven();
        end = System.currentTimeMillis();
        file.write("After Odd Even Sort without thread for 10000 student: \n");
        file.write(Arrays.toString(c4.cgpa1) +"\n");
        System.out.println("Time taken without Thread(10000 students): " + (end - start) + "ms");
        file.write("Before Odd Even Sort with thread for 10000 student: \n");
        file.write(Arrays.toString(c4.cgpa2) +"\n");
        start = System.currentTimeMillis();
        withThread9.start();
        withThread10.start();
        try {
            withThread9.join();
            withThread10.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        c4.mergesort();
        end = System.currentTimeMillis();
        file.write("After Odd Even Sort with thread for 10000 student: \n");
        file.write(Arrays.toString(c4.cgpa2) +"\n");
        System.out.println("Time taken with Thread(10000 students): " + (end - start) + "ms");
    }
}