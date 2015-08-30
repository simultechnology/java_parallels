package com.simul;

public class SyncMain {
    static final int MAX = 30000;

    public static void main(String[] args) {
        System.out.println("start!");
        long startTime = System.currentTimeMillis();

        SyncThreadedHello hello = new SyncThreadedHello(5);
        Thread[] threads = new Thread[MAX];
        for (int i = 0; i < MAX; i++) {
            threads[i] = new Thread(hello);
        }

        for (int i = 0; i < MAX; i++) {
            threads[i].start();
        }

        for (int i = 0; i < MAX; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long endTime = System.currentTimeMillis();
        System.out.printf("time : %d", endTime - startTime);
    }
}

class SyncThreadedHello implements Runnable {

    private int counter;

    public SyncThreadedHello(int counter) {
        this.counter = counter;
    }

    public void run() {

        for (int i = 0; i < SyncMain.MAX; i++) {
//            try {
//                Thread.sleep(50);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            synchronized (this) {
                counter++;
            }

            //System.out.printf("%s : %d\n", Thread.currentThread(), counter);
        }
    }
}
