package cn.leetcode.Problems1100_1200;

import org.junit.Test;

/**
 * Created by leslie on 2019/11/15.
 */
public class Problem1114 {

    @Test
    public void test1() {
        Foo foo = new Foo();
        new Thread(new One()).start();
        new Thread(new Two()).start();
        new Thread(new Three()).start();
    }
}

class One implements Runnable {

    @Override
    public void run() {
        System.out.println("one");
    }
}

class Two implements Runnable {

    @Override
    public void run() {
        System.out.println("two");
    }
}

class Three implements Runnable {

    @Override
    public void run() {
        System.out.println("three");
    }
}

class Foo {

    public Foo(){

    }

    public void first(Runnable printFirst) throws InterruptedException {

        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
    }

    public void second(Runnable printSecond) throws InterruptedException {

        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
    }

    public void third(Runnable printThird) throws InterruptedException {

        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }
}
