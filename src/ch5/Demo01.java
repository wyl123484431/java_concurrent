package ch5;

/**
 * @author: yuliangw
 * @version: v2.0
 * @description: ch5
 * @date:2019/3/24
 */
class Demo01 {

    class Account {
        // 账户锁
        private final Object balLock
                = new Object();
        // 密码锁
        private final Object pwLock
                = new Object();


        // 账户余额
        private Integer balance;

        // 账户密码
        private String password;

        // 取款
        void withdraw(Integer amt) {
            synchronized(balLock) {
                if (this.balance > amt){
                    this.balance -= amt;
                }
            }
        }
        // 查看余额
        Integer getBalance() {
            synchronized(balLock) {
                return balance;
            }
        }

        // 更改密码
        void updatePassword(String pw){
            synchronized(pwLock) {
                this.password = pw;
            }
        }
        // 查看密码
        String getPassword() {
            synchronized(pwLock) {
                return password;
            }
        }
    }
}


