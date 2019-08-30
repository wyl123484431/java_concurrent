package ch4;

import ch2.Demo01;

/**
 * @author: yuliangw
 * @version: v2.0
 * @description: ch4
 * @date:2019/3/22
 */
class SafeCalc {
    static long value = 0L;

    long get() {
        return value;
    }

    private synchronized void addOne() {
        value += 1;
    }
    private void add10K() {
        int idx = 0;
        while (idx++ < 50000) {
            addOne();
        }
    }

    public static long calc() throws Exception{
        SafeCalc test = new SafeCalc();
        // 创建两个线程，执行 add() 操作
        Thread th1 = new Thread(() -> {
            test.add10K();
        });
        Thread th2 = new Thread(() -> {
            test.add10K();
        });
        // 启动两个线程
        th1.start();
        th2.start();
        // 等待两个线程执行结束
        th1.join();
        th2.join();
        return value;
    }

    public static void main(String[] args) throws Exception {
        long calc = SafeCalc.calc();
        long l = new SafeCalc().get();
        System.out.println(calc);
    }
}

