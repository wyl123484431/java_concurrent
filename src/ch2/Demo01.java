package ch2;

/**
 * @author: yuliangw
 * @version: v2.0
 * @description: ch2
 * @date:2019/3/22
 */
@SuppressWarnings("all")
public class Demo01 {

    private static long count = 0;

    private void add10K() {
        int idx = 0;
        while (idx++ < 50000) {
            count += 1;
        }
    }

    public static long calc() throws Exception{
        Demo01 test = new Demo01();
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
        return count;
    }

    public static void main(String[] args) throws Exception {
        long calc = Demo01.calc();
        System.out.println(calc);
    }
}



