package ch9;

import java.util.Date;

/**
 * @author: yuliangw
 * @version: v2.0
 * @description: ch9
 * @date:2019/5/3
 */
public class Demo01 {
        public static void main(String[] args) {
            ThreadStop ts = new ThreadStop();
            ts.start();
            try {
                Thread.sleep(3000);
                //ts.stop();
                 ts.interrupt();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
}

class ThreadStop extends Thread {
    @Override
    public void run() {
        System.out.println("开始执行：" + new Date());
            System.out.println(1111);
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("结束执行：" + new Date());
    }
}
