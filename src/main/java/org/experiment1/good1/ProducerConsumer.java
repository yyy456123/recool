package org.experiment1.good1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ProducerConsumer {
    private static final int BUFFER_SIZE = 10;
    private final ReentrantLock lock;
    private final Condition notFull;
    private final Condition notEmpty;
    private final ConcurrentLinkedQueue<Integer> buffer;

    public ProducerConsumer() {
        buffer = new ConcurrentLinkedQueue<>();
        lock = new ReentrantLock();
        notFull = lock.newCondition();
        notEmpty = lock.newCondition();
    }

    public void produce() throws InterruptedException {
        int i = 0;
        while (i++ < 50) {
            lock.lock();
            try {
                while (buffer.size() == BUFFER_SIZE) {
                    notFull.await();
                }
                buffer.offer(i);
                System.out.println("Producer inserted " + i);
                notEmpty.signal();
            } finally {
                lock.unlock();
            }
        }
    }

    public void consume() throws InterruptedException {
        int i = 0;
        while (i++ < 50) {
            lock.lock();
            try {
                while (buffer.isEmpty()) {
                    notEmpty.await();
                }
                Integer value = buffer.poll();
                System.out.println("Consumer removed " + value);
                notFull.signal();
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        ProducerConsumer pc = new ProducerConsumer();
        Thread producerThread = new Thread(() -> {
            try {
                pc.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread consumerThread = new Thread(() -> {
            try {
                pc.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        producerThread.start();
        consumerThread.start();
    }
}
