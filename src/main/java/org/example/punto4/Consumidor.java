package org.example.punto4;

import java.util.concurrent.BlockingQueue;

class Consumidor implements Runnable {
    private final BlockingQueue<Integer> queue;

    public Consumidor(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Integer number = queue.take();
                System.out.println(Thread.currentThread().getName() + " ha consumido: " + number);
                System.out.println("Estado de la cola después de consumo: " + queue);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

