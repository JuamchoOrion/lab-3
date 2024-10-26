package org.example.punto3;

class HiloSecundario extends Thread {
    private final ConditionMonitor monitor;

    public HiloSecundario(ConditionMonitor monitor, String name) {
        super(name);
        this.monitor = monitor;
    }

    @Override
    public void run() {
        while (true) {
            try {
                monitor.waitForCondition();
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}