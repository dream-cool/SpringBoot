package com.clt.springbootssm;

import java.util.LinkedList;

public class ProducterAndConsumer {

    public static void main(String[] args) {
        Flush flush =new Flush();
        new Thread(new Producter(flush),"1号生产").start();
        new Thread(new Producter(flush),"2号生产").start();
        new Thread(new Producter(flush),"3号生产").start();
        new Thread(new Consumer(flush),"1号消费").start();
        new Thread(new Consumer(flush),"2号消费").start();
        new Thread(new Consumer(flush),"3号消费").start();
    }
}

class Producter implements Runnable{
    Flush flush;

    public Producter(Flush flush) {
        this.flush = flush;
    }

    @Override
    public void run() {
        for (int i = 0 ; i<20 ; i++ ){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            flush.push(new Steamedbun(i),i);
        }
    }
}

class Consumer implements Runnable {
    Flush flush;

    public Consumer(Flush flush) {
        this.flush = flush;
    }

    public void run() {
        for (int i = 0 ; i<20 ; i++ ){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Steamedbun steamedbun=flush.pop(i);
        }
    }
}

class Flush{
    int index=0;
    LinkedList<Steamedbun> steamedbunList=new LinkedList<>();

    public synchronized Steamedbun pop(int id){
        if (index==0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try{
            this.notifyAll();
            index--;
            return steamedbunList.removeFirst();
        }
        finally{
            System.out.println(Thread.currentThread().getName()+"了id为"+(id+1)+"的馒头");
        }
    }

    public synchronized void  push(Steamedbun steamedbun,int id){
        if (index==5){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try{
            steamedbunList.add(steamedbun);
            index++;
            this.notifyAll();
        }
        finally{
            System.out.println(Thread.currentThread().getName()+"了id为"+(id+1)+"的馒头");
        }
    }
}

class Steamedbun{
    public Steamedbun(int id) {
        this.id = id;
    }

    int id;
}
