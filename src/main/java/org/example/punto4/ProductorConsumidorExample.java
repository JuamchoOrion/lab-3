package org.example.punto4;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProductorConsumidorExample {
    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);
        int maxElements = 20;

        Thread productor1 = new Thread(new Productor(queue, maxElements), "Productor 1");
        Thread productor2 = new Thread(new Productor(queue, maxElements), "Productor 2");

        Thread consumidor1 = new Thread(new Consumidor(queue), "Consumidor 1");
        Thread consumidor2 = new Thread(new Consumidor(queue), "Consumidor 2");

        productor1.start();
        productor2.start();
        consumidor1.start();
        consumidor2.start();

        try {
            productor1.join();
            productor2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }


        consumidor1.interrupt();
        consumidor2.interrupt();


        try {
            consumidor1.join();
            consumidor2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Producción y consumo han finalizado.");
    }
}