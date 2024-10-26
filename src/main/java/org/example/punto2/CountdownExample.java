package org.example.punto2;

public class CountdownExample {
    public static void main(String[] args) {
        Countdown countdown = new Countdown();

        // Crear y empezar el hilo contador
        Thread countdownThread = new Thread(() -> countdown.startCountdown());
        countdownThread.start();

        // Crear varios hilos trabajadores
        for (int i = 1; i <= 5; i++) {
            Thread worker = new Thread(new WorkerThread(countdown), "Hilo trabajador " + i);
            worker.start();
        }

        // Esperar a que el hilo del contador termine
        try {
            countdownThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Todos los hilos han terminado.");
    }
}