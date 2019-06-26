package ch5;

/**
 * @author: yuliangw
 * @version: v2.0
 * @description: ch5
 * @date:2019/3/24
 */
public class Demo03 {

    class Account {

        private Object lock;
        private int balance;

        // 创建 Account 时传入同一个 lock 对象
        public Account(Object lock) {
            this.lock = lock;
        }
        // 转账
        void transfer(Account target, int amt){
            // 此处检查所有对象共享的锁
            synchronized(lock) {
                if (this.balance > amt) {
                    this.balance -= amt;
                    target.balance += amt;
                }
            }
        }
    }
}
