package org.example.punto4;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class Productor implements Runnable {
    private final BlockingQueue<Integer> queue;
    private final int maxElements;
    private final Random random = new Random();

    public Productor(BlockingQueue<Integer> queue, int maxElements) {
        this.queue = queue;
        this.maxElements = maxElements;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < maxElements; i++) {
                int number = random.nextInt(100) + 1; // genera el numero aleatorio
                queue.put(number); // Agregar el número
                System.out.println(Thread.currentThread().getName() + " ha producido: " + number);
                //muestra cual es el estado de la cola cada vez que los hilos produccion han generado dos numeros
                System.out.println("Estado de la cola después de producción: " + queue);
                Thread.sleep(500); // Simula el tiempo de producción
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Manejo de interrupciones
        }
    }
}

