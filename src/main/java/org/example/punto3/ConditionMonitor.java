package org.example.punto3;

class ConditionMonitor {
    private int condition = -1; //empezar con un valor que no sea multiplo de 5

    public synchronized void waitForCondition() throws InterruptedException {
        int lastCondition = this.condition; // Almacena cual e sla ultima condicion

        while (condition % 5 != 0) {
            wait(); // Espera a que notifique
        }

        // Imprime solo si la condición ha cambiado a multiplo de 5
        if (lastCondition != condition) {
            System.out.println(Thread.currentThread().getName() + " puede continuar, condición = " + condition);
        }
    }

    public synchronized void setCondition(int value) {
        condition = value;
        System.out.println("Condición establecida: " + condition);
        if (condition % 5 == 0) {
            notifyAll(); // Notificar a los hilos secundarios
        }
    }
}