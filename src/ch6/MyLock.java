package ch6;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author: yuliangw
 * @version: v2.0
 * @description: ch6
 * @date:2019/3/25
 */
public class MyLock {

    public static void main(String[] args) throws InterruptedException {
        Account src = new Account(10000);
        Account target = new Account(10000);
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                src.transactionToTarget(1,target);
                countDownLatch.countDown();
            }).start();
        }
        countDownLatch.await();
        System.out.println("src="+src.getBanalce() );
        System.out.println("target="+target.getBanalce() );
    }
    // 账户类
    static class Account{
        public Account(Integer banalce) {
            this.banalce = banalce;
        }
        private Integer banalce;
        // 转账方法
        public void transactionToTarget(Integer money,Account target) {
            Allocator.getInstance().apply(this, target);
            this.banalce -= money;
            target.setBanalce(target.getBanalce() + money);
            Allocator.getInstance().release(this, target);
        }
        public Integer getBanalce() {
            return banalce;
        }
        public void setBanalce(Integer banalce) {
            this.banalce = banalce;
        }
    }
    // 单例锁类
    static class Allocator {
        private Allocator(){
        }
        private List<Account> locks = new ArrayList<>();
        public synchronized void apply(Account src,Account tag){
            System.out.println("src: "+src+"  ---   tag: "+tag);
            while (locks.contains(src)||locks.contains(tag)) {
                try {
                    System.out.println(Thread.currentThread().getName()+"调用了 wait");
                    this.wait();
                    // System.out.println(Thread.currentThread().getName()+"被唤醒了");
                } catch (InterruptedException e) {
                }
            }
            // System.out.println(Thread.currentThread().getName()+"添加集合");
            locks.add(src);
            locks.add(tag);
        }
        public synchronized void release(Account src,Account tag){
            locks.remove(src);
            locks.remove(tag);
            // System.out.println(Thread.currentThread().getName()+"调用 notifyAll");
            this.notifyAll();
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // System.out.println(Thread.currentThread().getName()+"退出 同步块");
        }

        public static Allocator getInstance(){
            return AllocatorSingle.install;
        }
        static class AllocatorSingle{
            public static Allocator install = new Allocator();
        }
    }
}














