package ch5;

/**
 * @author: yuliangw
 * @version: v2.0
 * @description: ch5
 * @date:2019/3/24
 */
public class Demo04 {

    class Account {
        private int balance;
        // 转账
        void transfer(Account target, int amt){
            synchronized(Account.class) {
                if (this.balance > amt) {
                    this.balance -= amt;
                    target.balance += amt;
                }
            }
        }
    }

}
