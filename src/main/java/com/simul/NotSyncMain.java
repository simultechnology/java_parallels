package com.simul;

public class NotSyncMain {
    static final int MAX = 100000;

    public static void main(String[] args) {
        System.out.println("start!");
        long startTime = System.currentTimeMillis();

        ThreadedHello hello = new ThreadedHello(5);
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

class ThreadedHello implements Runnable {

    private int counter;

    public ThreadedHello(int counter) {
        this.counter = counter;
    }

    public void run() {

        for (int i = 0; i < NotSyncMain.MAX; i++) {
//            try {
//                Thread.sleep(50);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            counter++;

            //System.out.printf("%s : %d\n", Thread.currentThread(), counter);
        }
    }
}
