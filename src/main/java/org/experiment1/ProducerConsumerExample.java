package org.experiment1;

import java.util.LinkedList;

public class ProducerConsumerExample {
    public static void main(String[] args) {
        // ��������ض���
        Buffer buffer = new Buffer(2);

        // ����һ���������̺߳������������߳�
        Thread producerThread = new Thread(new Producer(buffer));
        Thread consumerThread1 = new Thread(new Consumer(buffer, "Consumer1"));
        Thread consumerThread2 = new Thread(new Consumer(buffer, "Consumer2"));

        // �����߳�
        producerThread.start();
        consumerThread1.start();
        consumerThread2.start();
    }
}

// �������
class Buffer {
    private int size;
    private LinkedList<Integer> list;

    public Buffer(int size) {
        this.size = size;
        this.list = new LinkedList<>();
    }

    public synchronized void produce(int value) {
        // �����������������ȴ�
        while (list.size() == size) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // ������Ʒ�����뻺����
        list.add(value);
        System.out.println("Produced: " + value);
        notifyAll(); // ֪ͨ�ȴ����������߳�
    }

    public synchronized int consume() {
        // ���������Ϊ�գ���ȴ�
        while (list.size() == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // �ӻ�������ȡ����Ʒ����������
        int value = list.removeFirst();
        System.out.println(Thread.currentThread().getName() + " consumed: " + value);
        notifyAll(); // ֪ͨ�ȴ����������߳�
        return value;
    }
}

// ��������
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
                Thread.sleep(1000); // ����һ����Ʒ������1��
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

// ��������
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
                Thread.sleep(1000); // ����һ����Ʒ������1��
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
