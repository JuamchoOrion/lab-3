package org.example.punto1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.Random;

class DownloadTask implements Runnable {
    private final String url;

    public DownloadTask(String url) {
        this.url = url;
    }

    @Override
    public void run() {
        try {
            int waitTime = new Random().nextInt(26) + 5;
            System.out.println("Iniciando descarga desde: " + url + " (Esperando " + waitTime + " segundos)");
            TimeUnit.SECONDS.sleep(waitTime);
            System.out.println("Descarga completada desde: " + url);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Descarga interrumpida desde: " + url);
        }
    }
}
