package ch9;

import java.util.Date;

/**
 * @author: yuliangw
 * @version: v2.0
 * @description: 应该重置一下中断标示，因为抛出异常后，中断标示会自动清除掉！
 * @date:2019/5/3
 */
public class Demo02 {

    public static void main(String[] args) {
        ThreadStop1 ts = new ThreadStop1();
        ts.start();
        // 你超过三秒不醒过来，我就干死你
        try {
             Thread.sleep(3000);
             // ts.
            // ts.stop();
             ts.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


class ThreadStop1 extends Thread {
    @Override
    public void run() {

        Thread th = Thread.currentThread();
        while(true) {
            if(th.isInterrupted()) {
                break;
            }
            // 省略业务代码无数
            try {
                Thread.sleep(100);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
