package org.example.punto3;

import java.util.Random;

class HiloPrincipal extends Thread {
    private final ConditionMonitor monitor;

    public HiloPrincipal(ConditionMonitor monitor) {
        this.monitor = monitor;
    }

    @Override
    public void run() {
        Random random = new Random();
        int count = 0;

        // Ciclo que genera numeros del 1 al 100
        while (count < 100) {
            int number = random.nextInt(100) + 1;
            System.out.println("Hilo principal genera número: " + number);
            monitor.setCondition(number);
            count++;

            try {
                Thread.sleep(500); // Pausa para simular un retraso en la generación de números
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Se han generado 100 números. Finalizando hilo principal.");

        // Interrumpir hilos secundarios al finalizar el hilo principal
        Thread.currentThread().interrupt();
    }
}