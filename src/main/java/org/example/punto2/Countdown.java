package org.example.punto2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class Countdown {
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    private int count = 10;

    public void startCountdown() {
        lock.lock();
        try {
            while (count > 0) {
                System.out.println("Contador: " + count);
                count--;
                condition.signalAll();
                Thread.sleep(1000);
            }
            // Avisa o notifica cunado el contador llega a 0
            condition.signalAll();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }
    }

    public void waitForCountdown() {
        lock.lock();
        try {
            while (count > 0) {
                condition.await();
            }
            // Se ejecuta luego de que el contador llega a cero
            System.out.println(Thread.currentThread().getName() + " comienza su tarea!");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }
    }
}