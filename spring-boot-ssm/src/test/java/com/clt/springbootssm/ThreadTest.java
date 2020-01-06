package com.clt.springbootssm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThreadTest {
    private static Logger logger = LoggerFactory.getLogger(ThreadTest.class);

    public static void main(String[] args) throws InterruptedException {
        TestThread t1 = new TestThread(100);
        new Thread(t1, "t1").start();
        new Thread(t1, "t2").start();

    }
}

class TestThread implements Runnable {
    Integer total;


    public TestThread(Integer total) {
        this.total = total;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (this) {
                if (total > 0) {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "正在售卖第" + total-- + "张票");
                } else break;
            }
        }
    }
}
