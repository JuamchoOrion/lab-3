package org.example.punto2;

class WorkerThread implements Runnable {
    private final Countdown countdown;

    public WorkerThread(Countdown countdown) {
        this.countdown = countdown;
    }

    @Override
    public void run() {
        countdown.waitForCountdown();
    }
}