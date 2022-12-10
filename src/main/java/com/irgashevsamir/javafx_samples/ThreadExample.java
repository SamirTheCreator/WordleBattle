package com.irgashevsamir.javafx_samples;

import java.net.Socket;
import java.sql.SQLOutput;

import static java.lang.Thread.sleep;

public class ThreadExample {
    public static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("M");
            System.out.println(".");
        }
    }

    public static class MyRunnable implements Runnable {

        @Override
        public void run() {
            System.out.println("M");
            System.out.println(",");
        }
    }
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " running");
            try {
                sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(threadName + " finished");
        };

        Runnable runnable1 = () -> {
            while (true) {
                try {
                    sleep(1000);
                    System.out.println("running");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        };
        /*
        MyThread thread = new MyThread();
        thread.start();
        */

        /*
        Thread thread1 = new Thread(runnable1, "Thread 1");
        thread1.start();
        sleep(3000);
        System.out.println("main finished");
        */

    }
}
