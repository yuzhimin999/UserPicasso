package com.zm.userpicasso;

/**
 * Created by yuzhimin on 17-7-4.
 */

public class NoVisibility {
    private static boolean ready;
    private static int number;
    private static class ReaderThread extends Thread {
        @Override
        public void run() {
            while(!ready) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Thread.yield();
            }
            System.out.println(number);
        }
    }
    public static void main(String[] args) {
        new ReaderThread().start();
        number = 42;
        ready = true;

    }
}
