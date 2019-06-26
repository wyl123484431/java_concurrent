package ch5;

/**
 * @author: yuliangw
 * @version: v2.0
 * @description: 细粒度锁。使用细粒度锁可以提高并行度，是性能优化的一个重要手段。
 * @date:2019/3/24
 */
public class Demo05 {

    class Account {
        private int balance;
        // 转账
        void transfer(Account target, int amt){
            // 锁定转出账户
            synchronized(this) {
                // 锁定转入账户
                synchronized(target) {
                    if (this.balance > amt) {
                        this.balance -= amt;
                        target.balance += amt;
                    }
                }
            }
        }
    }

}
