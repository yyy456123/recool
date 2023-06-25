package org.experiment1;

public class ProducerConsumerDemo {
    public static void main(String[] args) {
        Buffer buffer = new Buffer(10);
        Producer producer = new Producer(buffer);
        Consumer consumer = new Consumer(buffer);

        Thread producerThread = new Thread(producer);
        Thread consumerThread = new Thread(consumer);

        producerThread.start();
        consumerThread.start();
    }
}

class Buffer {
    private int[] data;
    private int size;
    private int count;
    private int in;
    private int out;

    public Buffer(int size) {
        this.size = size;
        data = new int[size];
        count = 0;
        in = 0;
        out = 0;
    }

    public synchronized void insert(int value) {
        while (count == size) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        data[in] = value;
        in = (in + 1) % size;
        count++;
        System.out.println("Producer inserted " + value);
        notifyAll();
    }

    public synchronized int remove() {
        while (count == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        int value = data[out];
        out = (out + 1) % size;
        count--;
        System.out.println("Consumer removed " + value);
        notifyAll();
        return value;
    }
}

class Producer implements Runnable {
    private Buffer buffer;

    public Producer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        for (int i = 0; i < 50; i++) {
            buffer.insert(i);
            try {
                Thread.sleep((int)(Math.random() * 500));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Consumer implements Runnable {
    private Buffer buffer;

    public Consumer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        for (int i = 0; i < 50; i++) {
            buffer.remove();
            try {
                Thread.sleep((int)(Math.random() * 500));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
