package ch10;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: yuliangw
 * @version: v2.0
 * @description: ch10
 * @date:2019/5/12
 */
public class Demo01 {

    private final Lock rtl = new ReentrantLock();  // 可重入锁
    int value;
    public void addOne() {
        // 获取锁
        rtl.lock();
        try {
            value+=1;
        } finally {
            // 保证锁能释放
            rtl.unlock();
        }
    }
}
