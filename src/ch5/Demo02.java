package ch5;

/**
 * @author: yuliangw
 * @version: v2.0
 * @description: ch5
 * @date:2019/3/24
 */
public class Demo02 {
    // 账户类
    class Account {

        private int balance;
        // 转账   target用户
       synchronized void transfer(Account target, int amt){
            if (this.balance > amt) {
                this.balance -= amt;
                target.balance += amt;
            }
        }
    }
}
