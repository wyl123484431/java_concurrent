package ch5;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: yuliangw
 * @version: v2.0
 * @description: 破坏占用且等待条件
 * @date:2019/3/24
 */
public class Demo06 {

    class Allocator {
        private List<Object> als =
                new ArrayList<>();
        // 一次性申请所有资源
        synchronized boolean apply(
                Object from, Object to){
            if(als.contains(from) ||
                    als.contains(to)){
                return false;
            } else {
                als.add(from);
                als.add(to);
            }
            return true;
        }
        // 归还资源
        synchronized void free(
                Object from, Object to){
            als.remove(from);
            als.remove(to);
        }
    }

    class Account {
        // actr 应该为单例
        private Allocator actr;
        private int balance;

        // 转账
        void transfer(Account target, int amt) {
            // 一次性申请转出账户和转入账户，直到成功
            while (!actr.apply(this, target)) {

                // 加锁因为可能还有其他业务
                try {
                    // 锁定转出账户
                    synchronized (this) {
                        // 锁定转入账户
                        synchronized (target) {
                            if (this.balance > amt) {
                                this.balance -= amt;
                                target.balance += amt;
                            }
                        }
                    }
                } finally {
                    actr.free(this, target);
                }
            }
        }
    }
}
