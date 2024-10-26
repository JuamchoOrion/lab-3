package org.example.punto1;

import java.util.Scanner;
import java.util.concurrent.*;

public class ThreadPoolExample {
    public static void main(String[] args) {
        // Número total de tareas
        int numberOfTasks = 50;

        // Solicitar tipo de ThreadPool
        System.out.println("Seleccione el tipo de ThreadPool:");
        System.out.println("1. Fixed Thread Pool");
        System.out.println("2. Cached Thread Pool");
        System.out.println("3. Single Thread Executor");
        System.out.println("4. Scheduled Thread Pool");
        System.out.println("5. Work Stealing Pool");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        scanner.close();

        ExecutorService executor = null;

        // Selección de ThreadPool según opción del usuario
        switch (choice) {
            case 1:
                System.out.println("Usando Fixed Thread Pool:");
                executor = Executors.newFixedThreadPool(10);
                break;
            case 2:
                System.out.println("Usando Cached Thread Pool:");
                executor = Executors.newCachedThreadPool();
                break;
            case 3:
                System.out.println("Usando Single Thread Executor:");
                executor = Executors.newSingleThreadExecutor();
                break;
            case 4:
                System.out.println("Usando Scheduled Thread Pool:");
                executor = Executors.newScheduledThreadPool(10);
                break;
            case 5:
                System.out.println("Usando Work Stealing Pool:");
                executor = Executors.newWorkStealingPool();
                break;
            default:
                System.out.println("Opción no válida.");
                return;
        }

        // Ejecutar tareas
        executeTasks(executor, numberOfTasks);

        // Cierre del ejecutor
        shutdownExecutor(executor);
    }

    private static void executeTasks(ExecutorService executor, int numberOfTasks) {
        for (int i = 1; i <= numberOfTasks; i++) {
            String url = "http://example.com/file" + i;
            executor.execute(new DownloadTask(url));
        }
    }

    private static void shutdownExecutor(ExecutorService executor) {
        executor.shutdown();
        try {
            if (!executor.awaitTermination(1, TimeUnit.HOURS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
        System.out.println("Todas las descargas han finalizado en el executor: " + executor);
    }
}
