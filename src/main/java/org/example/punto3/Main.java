package org.example.punto3;

public class Main {
    public static void main(String[] args) {
        ConditionMonitor monitor = new ConditionMonitor();


        Thread t1 = new HiloSecundario(monitor, "Hilo Secundario 1");
        Thread t2 = new HiloSecundario(monitor, "Hilo Secundario 2");
        Thread t3 = new HiloSecundario(monitor, "Hilo Secundario 3");
        t1.start();
        t2.start();
        t3.start();


        HiloPrincipal mainThread = new HiloPrincipal(monitor);
        mainThread.start();
    }
}