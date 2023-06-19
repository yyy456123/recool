package org.experiment1;

import java.util.LinkedList;

public class ProducerConsumerExample {
    public static void main(String[] args) {
        // 创建缓冲池对象
        Buffer buffer = new Buffer(2);

        // 创建一个生产者线程和两个消费者线程
        Thread producerThread = new Thread(new Producer(buffer));
        Thread consumerThread1 = new Thread(new Consumer(buffer, "Consumer1"));
        Thread consumerThread2 = new Thread(new Consumer(buffer, "Consumer2"));

        // 启动线程
        producerThread.start();
        consumerThread1.start();
        consumerThread2.start();
    }
}

// 缓冲池类
class Buffer {
    private int size;
    private LinkedList<Integer> list;

    public Buffer(int size) {
        this.size = size;
        this.list = new LinkedList<>();
    }

    public synchronized void produce(int value) {
        // 如果缓冲区已满，则等待
        while (list.size() == size) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 生产产品并加入缓冲区
        list.add(value);
        System.out.println("Produced: " + value);
        notifyAll(); // 通知等待的消费者线程
    }

    public synchronized int consume() {
        // 如果缓冲区为空，则等待
        while (list.size() == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 从缓冲区中取出产品并进行消费
        int value = list.removeFirst();
        System.out.println(Thread.currentThread().getName() + " consumed: " + value);
        notifyAll(); // 通知等待的生产者线程
        return value;
    }
}

// 生产者类
class Producer implements Runnable {
    private Buffer buffer;

    public Producer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            buffer.produce(i);
            try {
                Thread.sleep(1000); // 生产一个产品后休眠1秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

// 消费者类
class Consumer implements Runnable {
    private Buffer buffer;
    private String name;

    public Consumer(Buffer buffer, String name) {
        this.buffer = buffer;
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            buffer.consume();
            try {
                Thread.sleep(1000); // 消费一个产品后休眠1秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
