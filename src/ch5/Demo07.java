package ch5;

/**
 * @author: yuliangw
 * @version: v2.0
 * @description: ch5
 * @date:2019/6/24
 */
public class Demo07 {
    class Account {
        private int id;
        private int balance;
        // 转账
        void transfer(Account target, int amt){
            Account left = this;
            Account right = target;
            if (this.id > target.id) {
                left = target;
                right = this;
            }
            // 锁定序号小的账户
            synchronized(left){
                // 锁定序号大的账户
                synchronized(right){
                    if (this.balance > amt){
                        this.balance -= amt;
                        target.balance += amt;
                    }
                }
            }
        }
    }
}



